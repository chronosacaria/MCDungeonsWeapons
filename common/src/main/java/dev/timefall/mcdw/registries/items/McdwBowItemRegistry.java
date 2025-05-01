/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries.items;

import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.api.util.RarityHelper;
import dev.timefall.mcdw.bases.McdwBowItem;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class McdwBowItemRegistry {

    public static final Identifier BOW_ANCIENT_BOW_ID        = Mcdw.ID("bow_ancient_bow");
    public static final Identifier BOW_BONEBOW_ID            = Mcdw.ID("bow_bonebow");
    public static final Identifier BOW_BUBBLE_BOW_ID         = Mcdw.ID("bow_bubble_bow");
    public static final Identifier BOW_BUBBLE_BURSTER_ID     = Mcdw.ID("bow_bubble_burster");
    public static final Identifier BOW_BURST_GALE_BOW_ID     = Mcdw.ID("bow_burst_gale_bow");
    public static final Identifier BOW_CALL_OF_THE_VOID_ID   = Mcdw.ID("bow_call_of_the_void");
    public static final Identifier BOW_ECHO_OF_THE_VALLEY_ID = Mcdw.ID("bow_echo_of_the_valley");
    public static final Identifier BOW_ELITE_POWER_BOW_ID    = Mcdw.ID("bow_elite_power_bow");
    public static final Identifier BOW_GREEN_MENACE_ID       = Mcdw.ID("bow_green_menace");
    public static final Identifier BOW_HAUNTED_BOW_ID        = Mcdw.ID("bow_haunted_bow");
    public static final Identifier BOW_HUNTERS_PROMISE_ID    = Mcdw.ID("bow_hunters_promise");
    public static final Identifier BOW_HUNTING_BOW_ID        = Mcdw.ID("bow_hunting_bow");
    public static final Identifier BOW_LOST_SOULS_ID         = Mcdw.ID("bow_lost_souls");
    public static final Identifier BOW_MASTERS_BOW_ID        = Mcdw.ID("bow_masters_bow");
    public static final Identifier BOW_NOCTURNAL_BOW_ID      = Mcdw.ID("bow_nocturnal_bow");
    public static final Identifier BOW_PHANTOM_BOW_ID        = Mcdw.ID("bow_phantom_bow");
    public static final Identifier BOW_PINK_SCOUNDREL_ID     = Mcdw.ID("bow_pink_scoundrel");
    public static final Identifier BOW_POWER_BOW_ID          = Mcdw.ID("bow_power_bow");
    public static final Identifier BOW_SABREWING_ID          = Mcdw.ID("bow_sabrewing");
    public static final Identifier BOW_SHIVERING_BOW_ID      = Mcdw.ID("bow_shivering_bow");
    public static final Identifier BOW_SNOW_BOW_ID           = Mcdw.ID("bow_snow_bow");
    public static final Identifier BOW_SOUL_BOW_ID           = Mcdw.ID("bow_soul_bow");
    public static final Identifier BOW_TRICKBOW_ID           = Mcdw.ID("bow_trickbow");
    public static final Identifier BOW_TWIN_BOW_ID           = Mcdw.ID("bow_twin_bow");
    public static final Identifier BOW_TWISTING_VINE_BOW_ID  = Mcdw.ID("bow_twisting_vine_bow");
    public static final Identifier BOW_VOID_BOW_ID           = Mcdw.ID("bow_void_bow");
    public static final Identifier BOW_WEB_BOW_ID            = Mcdw.ID("bow_web_bow");
    public static final Identifier BOW_WEEPING_VINE_BOW_ID   = Mcdw.ID("bow_weeping_vine_bow");
    public static final Identifier BOW_WIND_BOW_ID           = Mcdw.ID("bow_wind_bow");
    public static final Identifier BOW_WINTERS_TOUCH_ID      = Mcdw.ID("bow_winters_touch");

    public static final McdwBowItem BOW_ANCIENT_BOW          = register(BOW_ANCIENT_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowAncientBow());
    public static final McdwBowItem BOW_BONEBOW              = register(BOW_BONEBOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowBonebow());
    public static final McdwBowItem BOW_BUBBLE_BOW           = register(BOW_BUBBLE_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowBubbleBow());
    public static final McdwBowItem BOW_BUBBLE_BURSTER       = register(BOW_BUBBLE_BURSTER_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowBubbleBurster());
    public static final McdwBowItem BOW_BURST_GALE_BOW       = register(BOW_BURST_GALE_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowBurstGaleBow());
    public static final McdwBowItem BOW_CALL_OF_THE_VOID     = register(BOW_CALL_OF_THE_VOID_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowCallOfTheVoid());
    public static final McdwBowItem BOW_ECHO_OF_THE_VALLEY   = register(BOW_ECHO_OF_THE_VALLEY_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowEchoOfTheValley());
    public static final McdwBowItem BOW_ELITE_POWER_BOW      = register(BOW_ELITE_POWER_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowElitePowerBow());
    public static final McdwBowItem BOW_GREEN_MENACE         = register(BOW_GREEN_MENACE_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowGreenMenace());
    public static final McdwBowItem BOW_HAUNTED_BOW          = register(BOW_HAUNTED_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowHauntedBow());
    public static final McdwBowItem BOW_HUNTERS_PROMISE      = register(BOW_HUNTERS_PROMISE_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowHuntersPromise());
    public static final McdwBowItem BOW_HUNTING_BOW          = register(BOW_HUNTING_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowHuntingBow());
    public static final McdwBowItem BOW_LOST_SOULS           = register(BOW_LOST_SOULS_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowLostSouls());
    public static final McdwBowItem BOW_MASTERS_BOW          = register(BOW_MASTERS_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowMastersBow());
    public static final McdwBowItem BOW_NOCTURNAL_BOW        = register(BOW_NOCTURNAL_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowNocturnalBow());
    public static final McdwBowItem BOW_PHANTOM_BOW          = register(BOW_PHANTOM_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowPhantomBow());
    public static final McdwBowItem BOW_PINK_SCOUNDREL       = register(BOW_PINK_SCOUNDREL_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowPinkScoundrel());
    public static final McdwBowItem BOW_POWER_BOW            = register(BOW_POWER_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowPowerBow());
    public static final McdwBowItem BOW_SABREWING            = register(BOW_SABREWING_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowSabrewing());
    public static final McdwBowItem BOW_SHIVERING_BOW        = register(BOW_SHIVERING_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowShiveringBow());
    public static final McdwBowItem BOW_SNOW_BOW             = register(BOW_SNOW_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowSnowBow());
    public static final McdwBowItem BOW_SOUL_BOW             = register(BOW_SOUL_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowSoulBow());
    public static final McdwBowItem BOW_TRICKBOW             = register(BOW_TRICKBOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowTrickbow());
    public static final McdwBowItem BOW_TWIN_BOW             = register(BOW_TWIN_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowTwinBow());
    public static final McdwBowItem BOW_TWISTING_VINE_BOW    = register(BOW_TWISTING_VINE_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowTwistingVineBow());
    public static final McdwBowItem BOW_VOID_BOW             = register(BOW_VOID_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowVoidBow());
    public static final McdwBowItem BOW_WEB_BOW              = register(BOW_WEB_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowWebBow());
    public static final McdwBowItem BOW_WEEPING_VINE_BOW     = register(BOW_WEEPING_VINE_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowWeepingVineBow());
    public static final McdwBowItem BOW_WIND_BOW             = register(BOW_WIND_BOW_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowWindBow());
    public static final McdwBowItem BOW_WINTERS_TOUCH        = register(BOW_WINTERS_TOUCH_ID, McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowWintersTouch());

    //@Override
    //public double getProjectileDamage() {
    //    if (FabricLoader.getInstance().isModLoaded("ranged_weapon_api")) {
    //        return projectileDamage;
    //    } else {
    //        return 0;
    //    }
    //}

    public static void register() {

    }

    private static McdwBowItem register(Identifier id, IMcdwWeaponStats.RangedStats stats) {
        McdwWeaponStatsConfig.CONFIG.registerItemEnableCheck(id,() -> stats.isEnabled);
        return Registry.register(Registries.ITEM, id, makeWeapon(stats));
    }


    private static McdwBowItem makeWeapon(IMcdwWeaponStats.RangedStats stats) {
        return new McdwBowItem(
                stats,
                stats.material,
                new Item.Settings()
                        .maxDamage(100 + stats.material.getDurability())
                        .rarity(RarityHelper.fromToolMaterial(stats.material))
                        .maxCount(1),
                stats.drawSpeed,
                stats.range
        );
    }
}