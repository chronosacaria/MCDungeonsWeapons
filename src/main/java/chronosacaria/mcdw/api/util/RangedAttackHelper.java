package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.bases.McdwCrossbow;
import chronosacaria.mcdw.items.ItemRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import static net.minecraft.item.CrossbowItem.hasProjectile;

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
        if (stack.getItem() == ItemRegistry.getItem("bow_ancient_bow").asItem()) arrowEntity.addScoreboardTag("AncientBow");
        if (stack.getItem() == ItemRegistry.getItem("bow_bonebow").asItem()) arrowEntity.addScoreboardTag("Bonebow");
        if (stack.getItem() == ItemRegistry.getItem("bow_elite_power_bow").asItem()) arrowEntity.addScoreboardTag("ElitePowerBow");
        if (stack.getItem() == ItemRegistry.getItem("bow_guardian_bow").asItem()) arrowEntity.addScoreboardTag("GuardianBow");
        if (stack.getItem() == ItemRegistry.getItem("bow_hunters_promise").asItem()) arrowEntity.addScoreboardTag("HuntersPromise");
        if (stack.getItem() == ItemRegistry.getItem("bow_masters_bow").asItem()) arrowEntity.addScoreboardTag("MastersBow");
        if (stack.getItem() == ItemRegistry.getItem("bow_nocturnal_bow").asItem()) arrowEntity.addScoreboardTag("NocturnalBow");
        if (stack.getItem() == ItemRegistry.getItem("bow_red_snake").asItem()) arrowEntity.addScoreboardTag("RedSnake");
        if (stack.getItem() == ItemRegistry.getItem("bow_sabrewing").asItem()) arrowEntity.addScoreboardTag("Sabrewing");
        if (stack.getItem() == ItemRegistry.getItem("bow_green_menace").asItem()) arrowEntity.addScoreboardTag("GreenMenace");
        if (stack.getItem() == ItemRegistry.getItem("bow_pink_scoundrel").asItem()) arrowEntity.addScoreboardTag("PinkScoundrel");

        // CROSSBOWS
        //if(stack.getItem() == DeferredItemInit.AUTO_CROSSBOW.get()) arrowEntity.addTag("AutoCrossbow");
        if(stack.getItem() == ItemRegistry.getItem("crossbow_azure_seeker").asItem()) arrowEntity.addScoreboardTag("AzureSeeker");
        if(stack.getItem() == ItemRegistry.getItem("crossbow_butterfly_crossbow").asItem()) arrowEntity.addScoreboardTag("ButterflyCrossbow");
        if(stack.getItem() == ItemRegistry.getItem("crossbow_doom_crossbow").asItem()) arrowEntity.addScoreboardTag("DoomCrossbow");
        if(stack.getItem() == ItemRegistry.getItem("crossbow_feral_crossbow").asItem()) arrowEntity.addScoreboardTag("FeralSoulCrossbow");
        if(stack.getItem() == ItemRegistry.getItem("crossbow_firebolt_thrower").asItem()) arrowEntity.addScoreboardTag("FireboltThrower");
        if(stack.getItem() == ItemRegistry.getItem("crossbow_harp_crossbow").asItem()) arrowEntity.addScoreboardTag("HarpCrossbow");
        if(stack.getItem() == ItemRegistry.getItem("crossbow_lightning_harp_crossbow").asItem()) arrowEntity.addScoreboardTag("LightningHarpCrossbow");
        if(stack.getItem() == ItemRegistry.getItem("crossbow_slayer_crossbow").asItem()) arrowEntity.addScoreboardTag("SlayerCrossbow");
        if(stack.getItem() == ItemRegistry.getItem("crossbow_the_slicer_crossbow").asItem()) arrowEntity.addScoreboardTag("TheSlicer");
        if(stack.getItem() == ItemRegistry.getItem("crossbow_void_caller_crossbow").asItem()) arrowEntity.addScoreboardTag("Voidcaller");
        if(stack.getItem() == ItemRegistry.getItem("crossbow_dual_crossbow").asItem()) arrowEntity.addScoreboardTag("DualCrossbow");
        if(stack.getItem() == ItemRegistry.getItem("crossbow_baby_crossbow").asItem()) arrowEntity.addScoreboardTag("BabyCrossbow");
        if(stack.getItem() == ItemRegistry.getItem("crossbow_exploding_crossbow").asItem()) arrowEntity.addScoreboardTag("ExplodingCrossbow");
        if(stack.getItem() == ItemRegistry.getItem("crossbow_heavy_crossbow").asItem()) arrowEntity.addScoreboardTag("HeavyCrossbow");
        if(stack.getItem() == ItemRegistry.getItem("crossbow_rapid_crossbow").asItem()) arrowEntity.addScoreboardTag("RapidCrossbow");
        if(stack.getItem() == ItemRegistry.getItem("crossbow_scatter_crossbow").asItem()) arrowEntity.addScoreboardTag("ScatterCrossbow");
        if(stack.getItem() == ItemRegistry.getItem("crossbow_soul_crossbow").asItem()) arrowEntity.addScoreboardTag("SoulCrossbow");
        if(stack.getItem() == ItemRegistry.getItem("crossbow_imploding_crossbow").asItem()) arrowEntity.addScoreboardTag("ImplodingCrossbow");
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

    /*public static float getVanillaOrModdedBowArrowVelocity(ItemStack stack, int charge){
        float arrowVelocity;
        arrowVelocity = getVanillaArrowVelocity(stack, charge);
        return arrowVelocity;
    }*/

    public static float getVanillaOrModdedBowArrowVelocity(ItemStack stack, int charge){
        float arrowVelocity;
        if (stack.getItem() instanceof McdwBow){
            arrowVelocity = ((McdwBow)stack.getItem()).getBowArrowVelocity(stack, charge);
        } else {
            arrowVelocity = getVanillaArrowVelocity(stack, charge);
        }
        return arrowVelocity;
    }
}
