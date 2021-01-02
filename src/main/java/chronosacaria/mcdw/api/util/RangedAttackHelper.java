package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.bases.McdwCrossbow;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.weapons.Bows;
import chronosacaria.mcdw.weapons.Crossbows;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import static net.minecraft.item.CrossbowItem.hasProjectile;
import static chronosacaria.mcdw.bases.McdwBow.getPullProgress;

public class RangedAttackHelper {

    public static float getVanillaArrowVelocity(ItemStack stack, int charge){
        float bowChargeTime = getVanillaBowChargeTime(stack);
        if (bowChargeTime <= 0){
            bowChargeTime = 1;
        }
        float arrowVelocity = (float) charge / bowChargeTime;
        arrowVelocity = (arrowVelocity * arrowVelocity + arrowVelocity * 2.0F) / 3.0F;
        if (arrowVelocity > 1.0F){
            arrowVelocity = 1.0F;
        }
        return arrowVelocity;
    }

    public static float getVanillaBowChargeTime(ItemStack stack){
        int quickChargeLevel = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
        //int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ACCELERATE, stack);

        float bowChargeTime = McdwBow.chargeTime * (Math.max(20.0F -5 * quickChargeLevel, 0));
        long lastFiredtime = (long)(McdwBow.getPullProgress(22) * (Math.max(20.0F -5 * quickChargeLevel, 0)));

        if (/*accelerateLevel > 0 &&*/ lastFiredtime > 0){
            return Math.max(bowChargeTime - 5 * quickChargeLevel, 0);
        } else {
            return Math.max(20.0F - 5 * quickChargeLevel, 0);
        }
    }

    public static void addWeaponTags(ArrowEntity arrowEntity, ItemStack stack){
        if (stack.getItem() == Bows.BOW_ANCIENT_BOW.asItem()) arrowEntity.addScoreboardTag("AncientBow");
        if (stack.getItem() == Bows.BOW_BONEBOW.asItem()) arrowEntity.addScoreboardTag("Bonebow");
        if (stack.getItem() == Bows.BOW_ELITE_POWER_BOW.asItem()) arrowEntity.addScoreboardTag("ElitePowerBow");
        if (stack.getItem() == Bows.BOW_GUARDIAN_BOW.asItem()) arrowEntity.addScoreboardTag("GuardianBow");
        if (stack.getItem() == Bows.BOW_HUNTERS_PROMISE.asItem()) arrowEntity.addScoreboardTag("HuntersPromise");
        if (stack.getItem() == Bows.BOW_MASTERS_BOW.asItem()) arrowEntity.addScoreboardTag("MastersBow");
        if (stack.getItem() == Bows.BOW_NOCTURNAL_BOW.asItem()) arrowEntity.addScoreboardTag("NocturnalBow");
        if (stack.getItem() == Bows.BOW_RED_SNAKE.asItem()) arrowEntity.addScoreboardTag("RedSnake");
        if (stack.getItem() == Bows.BOW_SABREWING.asItem()) arrowEntity.addScoreboardTag("Sabrewing");
        if (stack.getItem() == Bows.BOW_GREEN_MENACE.asItem()) arrowEntity.addScoreboardTag("GreenMenace");
        if (stack.getItem() == Bows.BOW_PINK_SCOUNDREL.asItem()) arrowEntity.addScoreboardTag("PinkScoundrel");

        // CROSSBOWS
        //if(stack.getItem() == DeferredItemInit.AUTO_CROSSBOW.get()) arrowEntity.addTag("AutoCrossbow");
        if(stack.getItem() == Crossbows.CROSSBOW_AZURE_SEEKER.asItem()) arrowEntity.addScoreboardTag("AzureSeeker");
        if(stack.getItem() == Crossbows.CROSSBOW_BUTTERFLY_CROSSBOW.asItem()) arrowEntity.addScoreboardTag("ButterflyCrossbow");
        if(stack.getItem() == Crossbows.CROSSBOW_DOOM_CROSSBOW.asItem()) arrowEntity.addScoreboardTag("DoomCrossbow");
        if(stack.getItem() == Crossbows.CROSSBOW_FERAL_CROSSBOW.asItem()) arrowEntity.addScoreboardTag("FeralSoulCrossbow");
        if(stack.getItem() == Crossbows.CROSSBOW_FIREBOLT_THROWER.asItem()) arrowEntity.addScoreboardTag("FireboltThrower");
        if(stack.getItem() == Crossbows.CROSSBOW_HARP_CROSSBOW.asItem()) arrowEntity.addScoreboardTag("HarpCrossbow");
        if(stack.getItem() == Crossbows.CROSSBOW_LIGHTNING_HARP_CROSSBOW.asItem()) arrowEntity.addScoreboardTag("LightningHarpCrossbow");
        if(stack.getItem() == Crossbows.CROSSBOW_SLAYER_CROSSBOW.asItem()) arrowEntity.addScoreboardTag("SlayerCrossbow");
        if(stack.getItem() == Crossbows.CROSSBOW_THE_SLICER_CROSSBOW.asItem()) arrowEntity.addScoreboardTag("TheSlicer");
        if(stack.getItem() == Crossbows.CROSSBOW_VOID_CALLER_CROSSBOW.asItem()) arrowEntity.addScoreboardTag("Voidcaller");
        if(stack.getItem() == Crossbows.CROSSBOW_DUAL_CROSSBOW.asItem()) arrowEntity.addScoreboardTag("DualCrossbow");
        if(stack.getItem() == Crossbows.CROSSBOW_BABY_CROSSBOW.asItem()) arrowEntity.addScoreboardTag("BabyCrossbow");
        if(stack.getItem() == Crossbows.CROSSBOW_EXPLODING_CROSSBOW.asItem()) arrowEntity.addScoreboardTag("ExplodingCrossbow");
        if(stack.getItem() == Crossbows.CROSSBOW_HEAVY_CROSSBOW.asItem()) arrowEntity.addScoreboardTag("HeavyCrossbow");
        if(stack.getItem() == Crossbows.CROSSBOW_RAPID_CROSSBOW.asItem()) arrowEntity.addScoreboardTag("RapidCrossbow");
        if(stack.getItem() == Crossbows.CROSSBOW_SCATTER_CROSSBOW.asItem()) arrowEntity.addScoreboardTag("ScatterCrossbow");
        if(stack.getItem() == Crossbows.CROSSBOW_SOUL_CROSSBOW.asItem()) arrowEntity.addScoreboardTag("SoulCrossbow");
        if(stack.getItem() == Crossbows.CROSSBOW_IMPLODING_CROSSBOW.asItem()) arrowEntity.addScoreboardTag("ImplodingCrossbow");
    }

    public static float getVanillaOrModdedCrossbowArrowVelocity(ItemStack stack){
        float arrowVelocity;
        if (stack.getItem() instanceof McdwCrossbow){
            arrowVelocity = ((McdwCrossbow)stack.getItem()).getProjectileVelocity(stack);
        } else {
            arrowVelocity = hasProjectile(stack, Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;
        }
        return arrowVelocity;
    }

    public static float getVanillaOrModdedBowArrowVelocity(ItemStack stack, int charge){
        float arrowVelocity;
        arrowVelocity = getVanillaArrowVelocity(stack, charge);
        return arrowVelocity;
    }
}
