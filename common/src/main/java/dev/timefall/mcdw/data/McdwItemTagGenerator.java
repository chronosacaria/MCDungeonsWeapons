/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.data;

import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.registries.items.*;
import dev.timefall.mcdw.registries.tag.McdwItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class McdwItemTagGenerator extends FabricTagProvider.ItemTagProvider {
    public McdwItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    // moved to a tagkey class McdwItemTags
    /*private static final TagKey<Item> COMMON_LOOT_POOL_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of("mcdw:common_loot_pool_items"));
    private static final TagKey<Item> UNCOMMON_LOOT_POOL_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of("mcdw:uncommon_loot_pool_items"));
    private static final TagKey<Item> RARE_LOOT_POOL_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of("mcdw:rare_loot_pool_items"));
    private static final TagKey<Item> EPIC_LOOT_POOL_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of("mcdw:epic_loot_pool_items"));*/


    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        // ITEM TAGS

        getOrCreateTagBuilder(McdwItemTags.COMMON_LOOT_POOL_ITEMS)
                .setReplace(false)
                .addOptional(McdwAxeItemRegistry.AXE_ANCHOR_ID)
                .addOptional(McdwAxeItemRegistry.AXE_AXE_ID)
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwDaggerItemRegistry.DAGGER_DAGGER.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwDoubleAxeItemRegistry.DOUBLE_AXE_DOUBLE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwGauntletItemRegistry.GAUNTLET_GAUNTLET.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwGlaiveItemRegistry.GLAIVE_GLAIVE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwHammerItemRegistry.HAMMER_GREAT_HAMMER.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwHammerItemRegistry.HAMMER_MACE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwLongbowItemRegistry.LONGBOW_LONGBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwPickaxeItemRegistry.PICK_HOWLING_PICK.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwPickaxeItemRegistry.PICK_MOUNTAINEER_PICK.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwShortbowItemRegistry.SHORTBOW_SHORTBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSickleItemRegistry.SICKLE_SICKLE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSpearItemRegistry.SPEAR_SPEAR.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwStaffItemRegistry.STAFF_BATTLESTAFF.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_CUTLASS.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_IRON_SWORD_VAR.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_KATANA.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_RAPIER.toString()));

        getOrCreateTagBuilder(McdwItemTags.UNCOMMON_LOOT_POOL_ITEMS)
                .setReplace(false)
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_BONEBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_BUBBLE_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_HUNTERS_PROMISE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_HUNTING_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_MASTERS_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_POWER_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_SNOW_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_SOUL_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_TWISTING_VINE_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_WEEPING_VINE_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_WIND_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_AUTO_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_AZURE_SEEKER.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_BURST_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_BUTTERFLY_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_DUAL_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_EXPLODING_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_FIREBOLT_THROWER.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_HARPOON_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_HARP_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_HEAVY_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_IMPLODING_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_RAPID_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_SCATTER_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_SOUL_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_THE_SLICER.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwDaggerItemRegistry.DAGGER_MOON.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwDoubleAxeItemRegistry.DOUBLE_AXE_CURSED.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwHammerItemRegistry.HAMMER_BONECLUB.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwHammerItemRegistry.HAMMER_BONE_CUDGEL.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwPickaxeItemRegistry.PICK_HOWLING_PICK.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwScytheItemRegistry.SCYTHE_JAILORS_SCYTHE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwScytheItemRegistry.SCYTHE_SKULL_SCYTHE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSickleItemRegistry.SICKLE_NIGHTMARES_BITE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_BROADSWORD.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_BROKEN_SAWBLADE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_CLAYMORE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_DIAMOND_SWORD_VAR.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_MECHANIZED_SAWBLADE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_NAMELESS_BLADE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_SINISTER_SWORD.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwWhipItemRegistry.WHIP_VINE_WHIP.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwWhipItemRegistry.WHIP_WHIP.toString()));

        getOrCreateTagBuilder(McdwItemTags.RARE_LOOT_POOL_ITEMS)
                .setReplace(false)
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwAxeItemRegistry.AXE_ENCRUSTED_ANCHOR.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwAxeItemRegistry.AXE_FIREBRAND.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwAxeItemRegistry.AXE_HIGHLAND.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_BUBBLE_BURSTER.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_BURST_GALE_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_CALL_OF_THE_VOID.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_ECHO_OF_THE_VALLEY.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_ELITE_POWER_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_GREEN_MENACE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_NOCTURNAL_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_PHANTOM_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_PINK_SCOUNDREL.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_SABREWING.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_SHIVERING_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_TRICKBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_TWIN_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_VOID_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_WEB_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_WINTERS_TOUCH.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_BABY_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_COG_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_FERAL_SOUL_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_LIGHTNING_HARP_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_NAUTICAL_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_SHADOW_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_SLAYER_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_SOUL_HUNTER_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_SPELLBOUND_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_VEILED_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_VOIDCALLER_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwDaggerItemRegistry.DAGGER_CHILL_GALE_KNIFE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwDaggerItemRegistry.DAGGER_FANGS_OF_FROST.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwDaggerItemRegistry.DAGGER_RESOLUTE_TEMPEST_KNIFE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwDaggerItemRegistry.DAGGER_SHEAR_DAGGER.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwDaggerItemRegistry.DAGGER_TEMPEST_KNIFE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwDoubleAxeItemRegistry.DOUBLE_AXE_WHIRLWIND.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwGauntletItemRegistry.GAUNTLET_MAULER.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwGlaiveItemRegistry.GLAIVE_GRAVE_BANE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwGlaiveItemRegistry.GLAIVE_VENOM_GLAIVE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwHammerItemRegistry.HAMMER_FLAIL.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwLongbowItemRegistry.LONGBOW_GUARDIAN_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwLongbowItemRegistry.LONGBOW_RED_SNAKE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwPickaxeItemRegistry.PICK_DIAMOND_PICKAXE_VAR.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwScytheItemRegistry.SCYTHE_FROST_SCYTHE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwShieldItemRegistry.SHIELD_ROYAL_GUARD_SHIELD.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwShieldItemRegistry.SHIELD_VANGUARD_SHIELD.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwShortbowItemRegistry.SHORTBOW_LOVE_SPELL_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwShortbowItemRegistry.SHORTBOW_MECHANICAL_SHORTBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwShortbowItemRegistry.SHORTBOW_PURPLE_STORM.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSickleItemRegistry.SICKLE_LAST_LAUGH_GOLD.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSickleItemRegistry.SICKLE_LAST_LAUGH_GOLD.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSickleItemRegistry.SICKLE_LAST_LAUGH_SILVER.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSpearItemRegistry.SPEAR_FORTUNE_SPEAR.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSpearItemRegistry.SPEAR_WHISPERING_SPEAR.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwStaffItemRegistry.STAFF_BATTLESTAFF_OF_TERROR.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwStaffItemRegistry.STAFF_GROWING_STAFF.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_BEE_STINGER.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_CORAL_BLADE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_DANCERS_SWORD.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_FREEZING_FOIL.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_FROST_SLAYER.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_GREAT_AXEBLADE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_HAWKBRAND.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_HEARTSTEALER.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_MASTERS_KATANA.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_SPONGE_STRIKER.toString()));

        getOrCreateTagBuilder(McdwItemTags.EPIC_LOOT_POOL_ITEMS)
                .setReplace(false)
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_ANCIENT_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_HAUNTED_BOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwBowItemRegistry.BOW_LOST_SOULS.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_CORRUPTED_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_DOOM_CROSSBOW.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwCrossbowItemRegistry.CROSSBOW_PRIDE_OF_THE_PIGLINS.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwDaggerItemRegistry.DAGGER_BACKSTABBER.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwDaggerItemRegistry.DAGGER_SWIFT_STRIKER.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwDaggerItemRegistry.DAGGER_THE_BEGINNING.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwDaggerItemRegistry.DAGGER_THE_END.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwDaggerItemRegistry.DAGGER_VOID_TOUCHED_BLADE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwGauntletItemRegistry.GAUNTLET_SOUL_FIST.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwHammerItemRegistry.HAMMER_GRAVITY.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwHammerItemRegistry.HAMMER_STORMLANDER.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwHammerItemRegistry.HAMMER_SUNS_GRACE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwScytheItemRegistry.SCYTHE_SOUL_SCYTHE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSoulDaggerItemRegistry.SOUL_DAGGER_ETERNAL_KNIFE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSoulDaggerItemRegistry.SOUL_DAGGER_SOUL_KNIFE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSoulDaggerItemRegistry.SOUL_DAGGER_TRUTHSEEKER.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_DARK_KATANA.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_OBSIDIAN_CLAYMORE.toString()))
                .addOptional(Identifier.of(Mcdw.MOD_ID, McdwSwordItemRegistry.SWORD_THE_STARLESS_NIGHT.toString()));

        getOrCreateTagBuilder(McdwItemTags.ANY_WEAPON_ENCHANTABLE)
                .setReplace(false)
                .addTag(ItemTags.TRIDENT_ENCHANTABLE)
                .addTag(ItemTags.BOW_ENCHANTABLE)
                .addTag(ItemTags.CROSSBOW_ENCHANTABLE)
                .addTag(ItemTags.WEAPON_ENCHANTABLE);
    }

}