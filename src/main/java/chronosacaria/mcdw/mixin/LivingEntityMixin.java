package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.*;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow
    public abstract ItemStack getMainHandStack();

    @Shadow
    public abstract boolean damage(DamageSource source, float amount);

    @Shadow
    public abstract boolean removeStatusEffect(StatusEffect type);

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }


    /* * * * * * * * * * * |
    |****STATUS REMOVAL****|
    | * * * * * * * * * * */
// Remove Poison Effect if Player has weapon with Poison Cloud Enchantment
    @Inject(
            at = @At("HEAD"),
            method = "tick",
            cancellable = true)

    private void removePoisonIfPCEnchant(CallbackInfo ci) {
        if ((Object) this instanceof PlayerEntity) {
            ItemStack mainHand = getMainHandStack();


            if (EnchantmentHelper.getLevel(EnchantsRegistry.POISON_CLOUD, mainHand) >= 1
                    || mainHand.getItem() == ItemsInit.sickleItems.get(SicklesID.SICKLE_NIGHTMARES_BITE).asItem()
                    || mainHand.getItem() == ItemsInit.glaiveItems.get(GlaivesID.SPEAR_VENOM_GLAIVE).asItem())
            {
                this.removeStatusEffect(StatusEffects.POISON);
            }
        }
    }
    // Remove Stunned Effects if Player has weapon with Stunning Enchantment

    @Inject(
            at = @At("HEAD"),
            method = "tick",
            cancellable = true)

    private void removeStunnedIfPCEnchant(CallbackInfo ci) {
        if ((Object) this instanceof PlayerEntity) {
            ItemStack mainHand = getMainHandStack();

            if (EnchantmentHelper.getLevel(EnchantsRegistry.STUNNING, mainHand) >= 1
                    || mainHand.getItem() == ItemsInit.axeItems.get(AxesID.AXE_HIGHLAND).asItem()) {
                this.removeStatusEffect(StatusEffects.NAUSEA);
                this.removeStatusEffect(StatusEffects.SLOWNESS);
            }
        }
    }

    // Remove Weakness Effect if Player has weapon with Weakening Enchantment

    @Inject(
            at = @At("HEAD"),
            method = "tick",
            cancellable = true)

    private void removeWeakenedIfPCEnchant(CallbackInfo ci) {
        if ((Object) this instanceof PlayerEntity) {
            ItemStack mainHand = getMainHandStack();

            if (EnchantmentHelper.getLevel(EnchantsRegistry.WEAKENING, mainHand) >= 1
                    || mainHand.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_NAMELESS_BLADE).asItem()) {
                this.removeStatusEffect(StatusEffects.WEAKNESS);
            }
        }
    }

    /* * * * * * * * * * * * * * * * *|
    |****HUNTER'S COMPANION EFFECT****|
    |* * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage", at = @At("HEAD"), cancellable = true)
    public void onHuntersPromiseCompanionDamage(DamageSource source, float amount, CallbackInfo into){
        LivingEntity target = (LivingEntity) (Object) this;
        Entity petSource = source.getSource();

        if (petSource == null) return;

        if (petSource.world instanceof ServerWorld && petSource instanceof TameableEntity){
            ServerWorld serverWorld = (ServerWorld) petSource.world;
            PlayerEntity owner = (PlayerEntity) ((TameableEntity) petSource).getOwner();
            if (owner != null){
                UUID petOwnerUUID = owner.getUuid();
                ItemStack mainHandStack = owner.getMainHandStack();

                if (mainHandStack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_HUNTERS_PROMISE).asItem()){
                    if (petOwnerUUID != null){
                        Entity petOwner = serverWorld.getEntity(petOwnerUUID);
                        if (petOwner instanceof LivingEntity){
                            float huntersPromiseCompanionFactor = 1.5f;
                            float newDamage = amount * huntersPromiseCompanionFactor;
                            float h = target.getHealth();
                            target.setHealth(h - newDamage);
                        }
                    }
                }
            }
        }
    }
}