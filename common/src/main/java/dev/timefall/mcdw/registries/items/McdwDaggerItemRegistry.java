/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.registries.items;

import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.api.util.CleanlinessHelper;
import dev.timefall.mcdw.api.util.RarityHelper;
import dev.timefall.mcdw.bases.McdwDaggerItem;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class McdwDaggerItemRegistry {

    public static final Identifier DAGGER_BACKSTABBER_ID               = Mcdw.ID("dagger_backstabber_");
    public static final Identifier DAGGER_DAGGER_ID                    = Mcdw.ID("dagger_dagger");
    public static final Identifier DAGGER_CHILL_GALE_KNIFE_ID          = Mcdw.ID("dagger_chill_gale_knife");
    public static final Identifier DAGGER_FANGS_OF_FROST_ID            = Mcdw.ID("dagger_fangs_of_frost");
    public static final Identifier DAGGER_MOON_ID                      = Mcdw.ID("dagger_moon");
    public static final Identifier DAGGER_RESOLUTE_TEMPEST_KNIFE_ID    = Mcdw.ID("dagger_resolute_tempest_knife");
    public static final Identifier DAGGER_SHEAR_DAGGER_ID              = Mcdw.ID("dagger_shear_dagger");
    public static final Identifier DAGGER_SWIFT_STRIKER_ID             = Mcdw.ID("dagger_swift_striker");
    public static final Identifier DAGGER_TEMPEST_KNIFE_ID             = Mcdw.ID("dagger_tempest_knife");
    public static final Identifier DAGGER_THE_BEGINNING_ID             = Mcdw.ID("dagger_the_beginning");
    public static final Identifier DAGGER_THE_END_ID                   = Mcdw.ID("dagger_the_end");
    public static final Identifier DAGGER_VOID_TOUCHED_BLADE_ID        = Mcdw.ID("dagger_void_touched_blade");

    public static final McdwDaggerItem DAGGER_BACKSTABBER              = register(DAGGER_BACKSTABBER_ID, McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerBackstabber());
    public static final McdwDaggerItem DAGGER_DAGGER                   = register(DAGGER_DAGGER_ID, McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerDagger());
    public static final McdwDaggerItem DAGGER_CHILL_GALE_KNIFE         = register(DAGGER_CHILL_GALE_KNIFE_ID, McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerChillGaleKnife());
    public static final McdwDaggerItem DAGGER_FANGS_OF_FROST           = register(DAGGER_FANGS_OF_FROST_ID, McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerFangsOfFrost());
    public static final McdwDaggerItem DAGGER_MOON                     = register(DAGGER_MOON_ID, McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerMoon());
    public static final McdwDaggerItem DAGGER_RESOLUTE_TEMPEST_KNIFE   = register(DAGGER_RESOLUTE_TEMPEST_KNIFE_ID, McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerResoluteTempestKnife());
    public static final McdwDaggerItem DAGGER_SHEAR_DAGGER             = register(DAGGER_SHEAR_DAGGER_ID, McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerShearDagger());
    public static final McdwDaggerItem DAGGER_SWIFT_STRIKER            = register(DAGGER_SWIFT_STRIKER_ID, McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerSwiftStriker());
    public static final McdwDaggerItem DAGGER_TEMPEST_KNIFE            = register(DAGGER_TEMPEST_KNIFE_ID, McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerTempestKnife());
    public static final McdwDaggerItem DAGGER_THE_BEGINNING            = register(DAGGER_THE_BEGINNING_ID, McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerTheBeginning());
    public static final McdwDaggerItem DAGGER_THE_END                  = register(DAGGER_THE_END_ID, McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerTheEnd());
    public static final McdwDaggerItem DAGGER_VOID_TOUCHED_BLADE       = register(DAGGER_VOID_TOUCHED_BLADE_ID, McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerVoidTouchedBlade());

    public static void register() {

    }

    private static McdwDaggerItem register(Identifier id, IMcdwWeaponStats.MeleeStats stats) {
        McdwWeaponStatsConfig.CONFIG.registerItemEnableCheck(id,() -> stats.isEnabled);
        return Registry.register(Registries.ITEM, id, makeWeapon(stats));
    }

    private static McdwDaggerItem makeWeapon(IMcdwWeaponStats.MeleeStats stats) {
        if (CompatibilityFlags.isReachExtensionEnabled) {
            return new McdwDaggerItem(
                    stats,
                    stats.material,
                    new Item.Settings()
                            .maxDamage(stats.material.getDurability())
                            .rarity(RarityHelper.fromToolMaterial(stats.material))
                            .maxCount(1)
                            .attributeModifiers(
                                    CleanlinessHelper.createDualWieldWithRangeAttributeModifiers(
                                            stats.material,
                                            stats.damage,
                                            stats.attackSpeed,
                                            stats.additionalAttackRange
                                    )
                            )
            );
        }
        return new McdwDaggerItem(
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