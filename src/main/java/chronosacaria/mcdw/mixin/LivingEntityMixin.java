package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.AOEHelper;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.*;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow
    @Nullable
    protected PlayerEntity attackingPlayer;

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
    @Inject(at = @At("HEAD"), method = "tick")
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

    @Inject(at = @At("HEAD"), method = "tick")
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

    @Inject(at = @At("HEAD"), method = "tick")
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

    @Inject(method = "applyDamage", at = @At("HEAD"))
    public void onHuntersPromiseCompanionDamage(DamageSource source, float amount, CallbackInfo into){
        LivingEntity target = (LivingEntity) (Object) this;
        Entity petSource = source.getSource();

        if (petSource == null) return;

        if (petSource.world instanceof ServerWorld serverWorld && petSource instanceof TameableEntity){
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

    @Inject(at = @At("HEAD"), method = "onDeath")
    private void onProspectorEnchantmentKill(DamageSource source, CallbackInfo ci) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.PROSPECTOR)) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.PROSPECTOR, mainHandStack) >= 1 )) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.PROSPECTOR, mainHandStack);
                float prospectorChance = 0.05F * level;
                float prospectorRand = user.getRandom().nextFloat();
                if (prospectorRand <= prospectorChance) {
                    if (target instanceof Monster){
                        ItemEntity emeraldDrop = new ItemEntity(target.world, target.getX(), target.getY(),
                                target.getZ(),
                                new ItemStack(Items.EMERALD, 1));
                        user.world.spawnEntity(emeraldDrop);
                    }
                }
            }
        }
    }

    //TODO Figure out how to make more than one, but less than four bottles convert to potions
    @Inject(at = @At("HEAD"), method = "onDeath")

    private void onRefreshmentEnchantmentKill(DamageSource source, CallbackInfo ci) {
        if(!(source.getSource() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getSource();
        if (source.getSource() instanceof PlayerEntity){
            ItemStack mainHandStack = null;
            if (user != null) {
                mainHandStack = user.getMainHandStack();
            }
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.REFRESHMENT)) {
                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.REFRESHMENT, mainHandStack) > 0 )) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.REFRESHMENT, mainHandStack);
                    PlayerInventory playerInventory = ((PlayerEntity)user).getInventory();
                    for (int slotID = 0; slotID < playerInventory.size(); slotID++){
                        ItemStack currentStack = playerInventory.getStack(slotID);
                        if (currentStack.getItem() instanceof GlassBottleItem && currentStack.getCount() < 2){
                            ItemStack healthPotion = PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.HEALING);
                            playerInventory.setStack(slotID, healthPotion);
                            if (healthPotion.getCount() <= level){
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "onDeath")
    private void onRushdownEnchantmentKill(DamageSource source, CallbackInfo ci) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;
        LivingEntity user = (LivingEntity) source.getAttacker();
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.RUSHDOWN)) {

            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.RUSHDOWN, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.RUSHDOWN, mainHandStack);
                float rushdownRand = user.getRandom().nextFloat();
                if (rushdownRand <= 0.1F) {
                    StatusEffectInstance rushdown = new StatusEffectInstance(StatusEffects.SPEED, level * 100, 2,
                            false, false);
                    user.addStatusEffect(rushdown);
                }
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applySmitingEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if(target instanceof PlayerEntity) return;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.SMITING)) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SMITING, mainHandStack) >= 1 && !(EnchantmentHelper.getLevel(Enchantments.SMITE, mainHandStack) >= 1))) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.SMITING, mainHandStack);
                        if (target.isUndead()) {
                            AOEHelper.causeSmitingAttack(
                                    (PlayerEntity) user,
                                    target,
                                    3.0f * level,
                                    amount);
                        }
                    }
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "onDeath")
    private void onSoulSiphonEnchantmentKill(DamageSource source, CallbackInfo ci) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;
        LivingEntity user = (LivingEntity) source.getAttacker();
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.SOUL_SIPHON)) {

            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_SIPHON, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_SIPHON, mainHandStack);
                float siphonRand = user.getRandom().nextFloat();
                if (siphonRand <= 0.1F) {
                    if (attackingPlayer != null) {
                        attackingPlayer.addExperience(level * 3);
                    }
                }
            }
        }
    }
}