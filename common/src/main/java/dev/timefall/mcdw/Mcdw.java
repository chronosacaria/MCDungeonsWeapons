/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw;

import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.data.ConfigEnchantmentEnabledCondition;
import dev.timefall.mcdw.data.ConfigItemEnabledCondition;
import dev.timefall.mcdw.enchantment.EnchantmentIds;
import dev.timefall.mcdw.registries.*;
import dev.timefall.mcdw.registries.items.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mcdw implements ModInitializer {

    public static final String MOD_ID = "mcdw";
    @SuppressWarnings("unused")
    public static final Logger LOGGER = LogManager.getLogger();
    public static Identifier ID(String path) {
        return Identifier.of(MOD_ID, path);
    }

    @Override
    public void onInitialize() {
        McdwRegistries.register();
        // Register Weapons
        // TODO CHECK ORDER AND MAKE SURE CALLED WHERE NEEDS TO BE CALLED
        McdwAxeItemRegistry.register();
        McdwBowItemRegistry.register();
        McdwCrossbowItemRegistry.register();
        McdwDaggerItemRegistry.register();
        McdwDoubleAxeItemRegistry.register();
        McdwGauntletItemRegistry.register();
        McdwGlaiveItemRegistry.register();
        McdwHammerItemRegistry.register();
        McdwLongbowItemRegistry.register();
        McdwPickaxeItemRegistry.register();
        McdwScytheItemRegistry.register();
        McdwShieldItemRegistry.register();
        McdwShortbowItemRegistry.register();
        McdwSickleItemRegistry.register();
        McdwSoulDaggerItemRegistry.register();
        McdwSpearItemRegistry.register();
        McdwStaffItemRegistry.register();
        McdwSwordItemRegistry.register();
        McdwWhipItemRegistry.register();

        EnchantmentIds.init();
        ConfigEnchantmentEnabledCondition.register();
        ConfigItemEnabledCondition.register();
        CompatibilityFlags.init();
        CompatRegistry.register();
        EntityAttributesRegistry.register();
        //ConfigItemEnabledCondition.register();
        //ParticlesRegistry.registerOnServer();
        ItemGroupRegistry.register();
        ItemsRegistry.register();
        //OffhandAttackPacket.register();
        LootTablesRegistry.register();
        EnchantsRegistry.register();
        SoundEventsRegistry.register();
        //SummonedEntityRegistry.register();
        StatusEffectsRegistry.register();
        EnchantmentRestrictionsRegistry.register();
        //if (FabricLoader.getInstance().isModLoaded("ranged_weapon_api")) {
        //    RangedWeaponAPICompat.init();
        //}
    }
}