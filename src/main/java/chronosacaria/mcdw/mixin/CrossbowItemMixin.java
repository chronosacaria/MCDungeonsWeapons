package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IMcdwEnchantedArrow;
import chronosacaria.mcdw.api.util.ProjectileEffectHelper;
import chronosacaria.mcdw.api.util.RangedAttackHelper;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.CrossbowsID;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {

    @Inject(method = "createArrow", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void applyCrossbowEnchantmentLevel(World world, LivingEntity user, ItemStack crossbow, ItemStack arrow,
                                    CallbackInfoReturnable<PersistentProjectileEntity> cir, ArrowItem arrowItem,
                                    PersistentProjectileEntity ppe){
        if (CrossbowItem.isCharged(crossbow)) {

            int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ACCELERATE, crossbow);
            if (accelerateLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setAccelerateLevel(accelerateLevel);
            }
            int chainReactionLevel = EnchantmentHelper.getLevel(EnchantsRegistry.CHAIN_REACTION, crossbow);
            if (chainReactionLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setChainReactionLevel(chainReactionLevel);
            }
            int chargeLevel = EnchantmentHelper.getLevel(EnchantsRegistry.CHARGE, crossbow);
            if (chargeLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setChargeLevel(chargeLevel);
            }
            int cobwebShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.COBWEB_SHOT, crossbow);
            if (cobwebShotLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setCobwebShotLevel(cobwebShotLevel);
            }
            int enigmaResonatorLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ENIGMA_RESONATOR, crossbow);
            if (enigmaResonatorLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setEnigmaResonatorLevel(enigmaResonatorLevel);
            }
            int fuseShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.FUSE_SHOT, crossbow);
            if (fuseShotLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setFuseShotLevel(fuseShotLevel);
            }
            int gravityLevel = EnchantmentHelper.getLevel(EnchantsRegistry.GRAVITY, crossbow);
            if (gravityLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setGravityLevel(gravityLevel);
            }
            int growingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.GROWING, crossbow);
            if (growingLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setGrowingLevel(growingLevel);
            }
            int levitationShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.LEVITATION_SHOT, crossbow);
            if (levitationShotLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setLevitationShotLevel(levitationShotLevel);
            }
            // For Drag in Water for Nautilus Crossbow
            ((IMcdwEnchantedArrow)ppe).setNautilusBoolean(crossbow.isOf(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW)));
            int phantomsMarkLevel = EnchantmentHelper.getLevel(EnchantsRegistry.PHANTOMS_MARK, crossbow);
            if (phantomsMarkLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setPhantomsMarkLevel(phantomsMarkLevel);
            }
            int poisonCloudLevel = EnchantmentHelper.getLevel(EnchantsRegistry.POISON_CLOUD, crossbow);
            if (poisonCloudLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setPoisonCloudLevel(poisonCloudLevel);
            }
            int radianceLevel = EnchantmentHelper.getLevel(EnchantsRegistry.RADIANCE, crossbow);
            if (radianceLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setRadianceLevel(radianceLevel);
            }
            int replenishLevel = EnchantmentHelper.getLevel(EnchantsRegistry.REPLENISH, crossbow);
            if (replenishLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setReplenishLevel(replenishLevel);
            }
            int ricochetLevel = EnchantmentHelper.getLevel(EnchantsRegistry.RICOCHET, crossbow);
            if (ricochetLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setRicochetLevel(ricochetLevel);
            }
            int tempoTheftLevel = EnchantmentHelper.getLevel(EnchantsRegistry.TEMPO_THEFT, crossbow);
            if (tempoTheftLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setTempoTheftLevel(tempoTheftLevel);
            }
            int voidShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.VOID_SHOT, crossbow);
            if (voidShotLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setVoidShotLevel(voidShotLevel);
            }

            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.BONUS_SHOT)) {
                int bonusShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.BONUS_SHOT, crossbow);
                if (bonusShotLevel > 0) {
                    float damageMultiplier = 0.1F + ((bonusShotLevel - 1) * 0.07F);

                    float arrowVelocity = RangedAttackHelper.getVanillaOrModdedCrossbowArrowVelocity(crossbow);
                    ProjectileEffectHelper.fireBonusShotTowardsOtherEntity(user, 10, damageMultiplier,
                            arrowVelocity);
                }
            }
        }
    }

    /*@ModifyArg(method = "onStoppedUsing", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/item/CrossbowItem;getPullProgress(ILnet/minecraft/item/ItemStack;)F"))
    private int mcdw$acceleratedPullProgress(int useTicks) {
        //return 100 * useTicks;
        //return (int) (value * (1 + ((6.0f + 2.0f * accelerateLevel) / 100)));
    }*/
}
