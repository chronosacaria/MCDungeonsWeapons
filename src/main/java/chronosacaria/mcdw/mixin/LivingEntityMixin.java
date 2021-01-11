package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.api.util.AOECloudHelper;
import chronosacaria.mcdw.api.util.AOEHelper;
import chronosacaria.mcdw.api.util.AbilityHelper;
import chronosacaria.mcdw.api.util.ProjectileEffectHelper;
import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enchants.summons.entity.SummonedBeeEntity;
import chronosacaria.mcdw.enchants.summons.registry.SummonedEntityRegistry;
import chronosacaria.mcdw.items.ItemRegistry;
import chronosacaria.mcdw.sounds.McdwSoundEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
            PlayerEntity entity = (PlayerEntity) (Object) this;
            ItemStack mainHand = getMainHandStack();


            if (EnchantmentHelper.getLevel(EnchantsRegistry.POISON_CLOUD, mainHand) >= 1
                    || mainHand.getItem() == ItemRegistry.getItem("sickle_nightmares_bite").asItem()
                    || mainHand.getItem() == ItemRegistry.getItem("spear_venom_glaive").asItem())
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
            PlayerEntity entity = (PlayerEntity) (Object) this;
            ItemStack mainHand = getMainHandStack();

            if (EnchantmentHelper.getLevel(EnchantsRegistry.STUNNING, mainHand) >= 1
                    || mainHand.getItem() == ItemRegistry.getItem("axe_highland").asItem()) {
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
            PlayerEntity entity = (PlayerEntity) (Object) this;
            ItemStack mainHand = getMainHandStack();

            if (EnchantmentHelper.getLevel(EnchantsRegistry.WEAKENING, mainHand) >= 1
                    || mainHand.getItem() == ItemRegistry.getItem("sword_nameless_blade").asItem()) {
                this.removeStatusEffect(StatusEffects.WEAKNESS);
            }
        }
    }
}