/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries.items;

import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.api.util.RarityHelper;
import dev.timefall.mcdw.bases.McdwCrossbowItem;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class McdwCrossbowItemRegistry {

    public static final Identifier CROSSBOW_AUTO_CROSSBOW_ID              = Mcdw.ID("crossbow_auto_crossbow");
    public static final Identifier CROSSBOW_AZURE_SEEKER_ID               = Mcdw.ID("crossbow_azure_seeker");
    public static final Identifier CROSSBOW_BABY_CROSSBOW_ID              = Mcdw.ID("crossbow_baby_crossbow");
    public static final Identifier CROSSBOW_BURST_CROSSBOW_ID             = Mcdw.ID("crossbow_burst_crossbow");
    public static final Identifier CROSSBOW_BUTTERFLY_CROSSBOW_ID         = Mcdw.ID("crossbow_butterfly_crossbow");
    public static final Identifier CROSSBOW_COG_CROSSBOW_ID               = Mcdw.ID("crossbow_cog_crossbow");
    public static final Identifier CROSSBOW_CORRUPTED_CROSSBOW_ID         = Mcdw.ID("crossbow_corrupted_crossbow");
    public static final Identifier CROSSBOW_DOOM_CROSSBOW_ID              = Mcdw.ID("crossbow_doom_crossbow");
    public static final Identifier CROSSBOW_DUAL_CROSSBOW_ID              = Mcdw.ID("crossbow_dual_crossbow");
    public static final Identifier CROSSBOW_EXPLODING_CROSSBOW_ID         = Mcdw.ID("crossbow_exploding_crossbow");
    public static final Identifier CROSSBOW_FERAL_SOUL_CROSSBOW_ID        = Mcdw.ID("crossbow_feral_soul_crossbow");
    public static final Identifier CROSSBOW_FIREBOLT_THROWER_ID           = Mcdw.ID("crossbow_firebolt_thrower");
    public static final Identifier CROSSBOW_HARPOON_CROSSBOW_ID           = Mcdw.ID("crossbow_harpoon_crossbow");
    public static final Identifier CROSSBOW_HARP_CROSSBOW_ID              = Mcdw.ID("crossbow_harp_crossbow");
    public static final Identifier CROSSBOW_HEAVY_CROSSBOW_ID             = Mcdw.ID("crossbow_heavy_crossbow");
    public static final Identifier CROSSBOW_IMPLODING_CROSSBOW_ID         = Mcdw.ID("crossbow_imploding_crossbow");
    public static final Identifier CROSSBOW_LIGHTNING_HARP_CROSSBOW_ID    = Mcdw.ID("crossbow_lightning_harp_crossbow");
    public static final Identifier CROSSBOW_NAUTICAL_CROSSBOW_ID          = Mcdw.ID("crossbow_nautical_crossbow");
    public static final Identifier CROSSBOW_PRIDE_OF_THE_PIGLINS_ID       = Mcdw.ID("crossbow_pride_of_the_piglins");
    public static final Identifier CROSSBOW_RAPID_CROSSBOW_ID             = Mcdw.ID("crossbow_rapid_crossbow");
    public static final Identifier CROSSBOW_SCATTER_CROSSBOW_ID           = Mcdw.ID("crossbow_scatter_crossbow");
    public static final Identifier CROSSBOW_SHADOW_CROSSBOW_ID            = Mcdw.ID("crossbow_shadow_crossbow");
    public static final Identifier CROSSBOW_SLAYER_CROSSBOW_ID            = Mcdw.ID("crossbow_slayer_crossbow");
    public static final Identifier CROSSBOW_SOUL_CROSSBOW_ID              = Mcdw.ID("crossbow_soul_crossbow");
    public static final Identifier CROSSBOW_SOUL_HUNTER_CROSSBOW_ID       = Mcdw.ID("crossbow_soul_hunter_crossbow");
    public static final Identifier CROSSBOW_SPELLBOUND_CROSSBOW_ID        = Mcdw.ID("crossbow_spellbound_crossbow");
    public static final Identifier CROSSBOW_THE_SLICER_ID                 = Mcdw.ID("crossbow_the_slicer");
    public static final Identifier CROSSBOW_VEILED_CROSSBOW_ID            = Mcdw.ID("crossbow_veiled_crossbow");
    public static final Identifier CROSSBOW_VOIDCALLER_CROSSBOW_ID        = Mcdw.ID("crossbow_voidcaller_crossbow");

    public static final McdwCrossbowItem CROSSBOW_AUTO_CROSSBOW           = register(CROSSBOW_AUTO_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowAutoCrossbow());
    public static final McdwCrossbowItem CROSSBOW_AZURE_SEEKER            = register(CROSSBOW_AZURE_SEEKER_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowAzureSeeker());
    public static final McdwCrossbowItem CROSSBOW_BABY_CROSSBOW           = register(CROSSBOW_BABY_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowBabyCrossbow());
    public static final McdwCrossbowItem CROSSBOW_BURST_CROSSBOW          = register(CROSSBOW_BURST_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowBurstCrossbow());
    public static final McdwCrossbowItem CROSSBOW_BUTTERFLY_CROSSBOW      = register(CROSSBOW_BUTTERFLY_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowButterflyCrossbow());
    public static final McdwCrossbowItem CROSSBOW_COG_CROSSBOW            = register(CROSSBOW_COG_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowCogCrossbow());
    public static final McdwCrossbowItem CROSSBOW_CORRUPTED_CROSSBOW      = register(CROSSBOW_CORRUPTED_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowCorruptedCrossbow());
    public static final McdwCrossbowItem CROSSBOW_DOOM_CROSSBOW           = register(CROSSBOW_DOOM_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowDoomCrossbow());
    public static final McdwCrossbowItem CROSSBOW_DUAL_CROSSBOW           = register(CROSSBOW_DUAL_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowDualCrossbow());
    public static final McdwCrossbowItem CROSSBOW_EXPLODING_CROSSBOW      = register(CROSSBOW_EXPLODING_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowExplodingCrossbow());
    public static final McdwCrossbowItem CROSSBOW_FERAL_SOUL_CROSSBOW     = register(CROSSBOW_FERAL_SOUL_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowFeralSoulCrossbow());
    public static final McdwCrossbowItem CROSSBOW_FIREBOLT_THROWER        = register(CROSSBOW_FIREBOLT_THROWER_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowFireboltThrower());
    public static final McdwCrossbowItem CROSSBOW_HARPOON_CROSSBOW        = register(CROSSBOW_HARPOON_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowHarpoonCrossbow());
    public static final McdwCrossbowItem CROSSBOW_HARP_CROSSBOW           = register(CROSSBOW_HARP_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowHarpCrossbow());
    public static final McdwCrossbowItem CROSSBOW_HEAVY_CROSSBOW          = register(CROSSBOW_HEAVY_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowHeavyCrossbow());
    public static final McdwCrossbowItem CROSSBOW_IMPLODING_CROSSBOW      = register(CROSSBOW_IMPLODING_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowImplodingCrossbow());
    public static final McdwCrossbowItem CROSSBOW_LIGHTNING_HARP_CROSSBOW = register(CROSSBOW_LIGHTNING_HARP_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowLightningHarpCrossbow());
    public static final McdwCrossbowItem CROSSBOW_NAUTICAL_CROSSBOW       = register(CROSSBOW_NAUTICAL_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowNauticalCrossbow());
    public static final McdwCrossbowItem CROSSBOW_PRIDE_OF_THE_PIGLINS    = register(CROSSBOW_PRIDE_OF_THE_PIGLINS_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowPrideOfThePiglins());
    public static final McdwCrossbowItem CROSSBOW_RAPID_CROSSBOW          = register(CROSSBOW_RAPID_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowRapidCrossbow());
    public static final McdwCrossbowItem CROSSBOW_SCATTER_CROSSBOW        = register(CROSSBOW_SCATTER_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowScatterCrossbow());
    public static final McdwCrossbowItem CROSSBOW_SHADOW_CROSSBOW         = register(CROSSBOW_SHADOW_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowShadowCrossbow());
    public static final McdwCrossbowItem CROSSBOW_SLAYER_CROSSBOW         = register(CROSSBOW_SLAYER_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowSlayerCrossbow());
    public static final McdwCrossbowItem CROSSBOW_SOUL_CROSSBOW           = register(CROSSBOW_SOUL_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowSoulCrossbow());
    public static final McdwCrossbowItem CROSSBOW_SOUL_HUNTER_CROSSBOW    = register(CROSSBOW_SOUL_HUNTER_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowSoulHunterCrossbow());
    public static final McdwCrossbowItem CROSSBOW_SPELLBOUND_CROSSBOW     = register(CROSSBOW_SPELLBOUND_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowSpellboundCrossbow());
    public static final McdwCrossbowItem CROSSBOW_THE_SLICER              = register(CROSSBOW_THE_SLICER_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowTheSlicer());
    public static final McdwCrossbowItem CROSSBOW_VEILED_CROSSBOW         = register(CROSSBOW_VEILED_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowVeiledCrossbow());
    public static final McdwCrossbowItem CROSSBOW_VOIDCALLER_CROSSBOW     = register(CROSSBOW_VOIDCALLER_CROSSBOW_ID, McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowVoidcallerCrossbow());


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

    private static McdwCrossbowItem register(Identifier id, IMcdwWeaponStats.RangedStats stats) {
        McdwWeaponStatsConfig.CONFIG.registerItemEnableCheck(id,() -> stats.isEnabled);
        return Registry.register(Registries.ITEM, id, makeWeapon(stats));
    }


    private static McdwCrossbowItem makeWeapon(IMcdwWeaponStats.RangedStats stats) {
        return new McdwCrossbowItem(
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