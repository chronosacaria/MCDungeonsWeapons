/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries.items;

import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.api.util.CleanlinessHelper;
import dev.timefall.mcdw.api.util.RarityHelper;
import dev.timefall.mcdw.bases.McdwSwordItem;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class McdwSwordItemRegistry {

    public static final Identifier SWORD_BEE_STINGER_ID           = Mcdw.ID("sword_bee_stinger");
    public static final Identifier SWORD_BROADSWORD_ID            = Mcdw.ID("sword_broadsword");
    public static final Identifier SWORD_BROKEN_SAWBLADE_ID       = Mcdw.ID("sword_broken_sawblade");
    public static final Identifier SWORD_CLAYMORE_ID              = Mcdw.ID("sword_claymore");
    public static final Identifier SWORD_CORAL_BLADE_ID           = Mcdw.ID("sword_coral_blade");
    public static final Identifier SWORD_CUTLASS_ID               = Mcdw.ID("sword_cutlass");
    public static final Identifier SWORD_DANCERS_SWORD_ID         = Mcdw.ID("sword_dancers_sword");
    public static final Identifier SWORD_DARK_KATANA_ID           = Mcdw.ID("sword_dark_katana");
    public static final Identifier SWORD_DIAMOND_SWORD_VAR_ID     = Mcdw.ID("sword_diamond_sword_var");
    public static final Identifier SWORD_FREEZING_FOIL_ID         = Mcdw.ID("sword_freezing_foil");
    public static final Identifier SWORD_FROST_SLAYER_ID          = Mcdw.ID("sword_frost_slayer");
    public static final Identifier SWORD_GREAT_AXEBLADE_ID        = Mcdw.ID("sword_great_axeblade");
    public static final Identifier SWORD_HAWKBRAND_ID             = Mcdw.ID("sword_hawkbrand");
    public static final Identifier SWORD_HEARTSTEALER_ID          = Mcdw.ID("sword_heartstealer");
    public static final Identifier SWORD_IRON_SWORD_VAR_ID        = Mcdw.ID("sword_iron_sword_var");
    public static final Identifier SWORD_KATANA_ID                = Mcdw.ID("sword_katana");
    public static final Identifier SWORD_MASTERS_KATANA_ID        = Mcdw.ID("sword_masters_katana");
    public static final Identifier SWORD_MECHANIZED_SAWBLADE_ID   = Mcdw.ID("sword_mechanized_sawblade");
    public static final Identifier SWORD_NAMELESS_BLADE_ID        = Mcdw.ID("sword_nameless_blade");
    public static final Identifier SWORD_OBSIDIAN_CLAYMORE_ID     = Mcdw.ID("sword_obsidian_claymore");
    public static final Identifier SWORD_RAPIER_ID                = Mcdw.ID("sword_rapier");
    public static final Identifier SWORD_SINISTER_SWORD_ID        = Mcdw.ID("sword_sinister_sword");
    public static final Identifier SWORD_SPONGE_STRIKER_ID        = Mcdw.ID("sword_sponge_striker");
    public static final Identifier SWORD_THE_STARLESS_NIGHT_ID    = Mcdw.ID("sword_the_starless_night");

    public static final McdwSwordItem SWORD_BEE_STINGER           = register(SWORD_BEE_STINGER_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordBeeStinger());
    public static final McdwSwordItem SWORD_BROADSWORD            = register(SWORD_BROADSWORD_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordBroadsword());
    public static final McdwSwordItem SWORD_BROKEN_SAWBLADE       = register(SWORD_BROKEN_SAWBLADE_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordBrokenSawblade());
    public static final McdwSwordItem SWORD_CLAYMORE              = register(SWORD_CLAYMORE_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordClaymore());
    public static final McdwSwordItem SWORD_CORAL_BLADE           = register(SWORD_CORAL_BLADE_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordCoralBlade());
    public static final McdwSwordItem SWORD_CUTLASS               = register(SWORD_CUTLASS_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordCutlass());
    public static final McdwSwordItem SWORD_DANCERS_SWORD         = register(SWORD_DANCERS_SWORD_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordDancersSword());
    public static final McdwSwordItem SWORD_DARK_KATANA           = register(SWORD_DARK_KATANA_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordDarkKatana());
    public static final McdwSwordItem SWORD_DIAMOND_SWORD_VAR     = register(SWORD_DIAMOND_SWORD_VAR_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordDiamondSwordVar());
    public static final McdwSwordItem SWORD_FREEZING_FOIL         = register(SWORD_FREEZING_FOIL_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordFreezingFoil());
    public static final McdwSwordItem SWORD_FROST_SLAYER          = register(SWORD_FROST_SLAYER_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordFrostSlayer());
    public static final McdwSwordItem SWORD_GREAT_AXEBLADE        = register(SWORD_GREAT_AXEBLADE_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordGreatAxeblade());
    public static final McdwSwordItem SWORD_HAWKBRAND             = register(SWORD_HAWKBRAND_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordHawkbrand());
    public static final McdwSwordItem SWORD_HEARTSTEALER          = register(SWORD_HEARTSTEALER_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordHeartstealer());
    public static final McdwSwordItem SWORD_IRON_SWORD_VAR        = register(SWORD_IRON_SWORD_VAR_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordIronSwordVar());
    public static final McdwSwordItem SWORD_KATANA                = register(SWORD_KATANA_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordKatana());
    public static final McdwSwordItem SWORD_MASTERS_KATANA        = register(SWORD_MASTERS_KATANA_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordMastersKatana());
    public static final McdwSwordItem SWORD_MECHANIZED_SAWBLADE   = register(SWORD_MECHANIZED_SAWBLADE_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordMechanizedSawblade());
    public static final McdwSwordItem SWORD_NAMELESS_BLADE        = register(SWORD_NAMELESS_BLADE_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordNamelessBlade());
    public static final McdwSwordItem SWORD_OBSIDIAN_CLAYMORE     = register(SWORD_OBSIDIAN_CLAYMORE_ID , McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordObsidianClaymore());
    public static final McdwSwordItem SWORD_RAPIER                = register(SWORD_RAPIER_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordRapier());
    public static final McdwSwordItem SWORD_SINISTER_SWORD        = register(SWORD_SINISTER_SWORD_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordSinisterSword());
    public static final McdwSwordItem SWORD_SPONGE_STRIKER        = register(SWORD_SPONGE_STRIKER_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordSpongeStriker());
    public static final McdwSwordItem SWORD_THE_STARLESS_NIGHT    = register(SWORD_THE_STARLESS_NIGHT_ID, McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordTheStarlessNight());

    public static void register() {

    }

    private static McdwSwordItem register(Identifier id, IMcdwWeaponStats.MeleeStats stats) {
        McdwWeaponStatsConfig.CONFIG.registerItemEnableCheck(id,() -> stats.isEnabled);
        return Registry.register(Registries.ITEM, id, makeWeapon(stats));
    }

    private static McdwSwordItem makeWeapon(IMcdwWeaponStats.MeleeStats stats) {
        if (CompatibilityFlags.isReachExtensionEnabled) {
            return new McdwSwordItem(
                    stats,
                    stats.material,
                    new Item.Settings()
                            .maxDamage(stats.material.getDurability())
                            .rarity(RarityHelper.fromToolMaterial(stats.material))
                            .maxCount(1)
                            .attributeModifiers(
                                    CleanlinessHelper.createBaseWithRangeAttributeModifiers(
                                            stats.material,
                                            stats.damage,
                                            stats.attackSpeed,
                                            stats.additionalAttackRange
                                    )
                            )
            );
        }
        return new McdwSwordItem(
                stats,
                stats.material,
                new Item.Settings()
                        .maxDamage(stats.material.getDurability())
                        .rarity(RarityHelper.fromToolMaterial(stats.material))
                        .maxCount(1)
                        .attributeModifiers(
                                SwordItem.createAttributeModifiers(
                                        stats.material,
                                        stats.damage,
                                        stats.attackSpeed
                                )
                        )
        );
    }
}