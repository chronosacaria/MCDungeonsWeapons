package chronosacaria.mcdw;

import chronosacaria.mcdw.configs.*;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.items.ItemRegistry;
import chronosacaria.mcdw.loottables.McdwLoottables;
import chronosacaria.mcdw.sounds.McdwSoundEvents;
import com.google.gson.JsonObject;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.io.File;

public class Mcdw implements ModInitializer {

    public static final String MOD_ID = "mcdw";

    public static Identifier ID(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static final ItemGroup WEAPONS = FabricItemGroupBuilder.build(
            Mcdw.ID("weapons"),
            () -> new ItemStack(ItemRegistry.getItem("sword_heartstealer")));
    public static final ItemGroup RANGED = FabricItemGroupBuilder.build(
            Mcdw.ID("weapons/bows"),
            () -> new ItemStack(ItemRegistry.getItem("bow_longbow")));
    public static final ItemGroup SHIELDS = FabricItemGroupBuilder.build(
            Mcdw.ID("weapons/shields"),
            () -> new ItemStack(ItemRegistry.getItem("shield_royal_guard")));
    public static final ItemGroup ENCHANTMENTS = FabricItemGroupBuilder.create(
            Mcdw.ID("enchants"))
            .icon(() -> new ItemStack(Items.ENCHANTED_BOOK))
            .appendItems(itemStacks -> {
                //itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(RangedEnchantsRegistry.ACCELERATE,1)));
                //itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(RangedEnchantsRegistry.ACCELERATE,2)));
                //itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(RangedEnchantsRegistry.ACCELERATE,3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.AMBUSH, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.AMBUSH, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.AMBUSH, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.ANIMA_CONDUIT, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.ANIMA_CONDUIT, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.ANIMA_CONDUIT, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.BONUS_SHOT,1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.BONUS_SHOT,2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.BONUS_SHOT,3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.CHAIN_REACTION, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.CHAIN_REACTION, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.CHAIN_REACTION, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.CHAINS, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.CHAINS, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.CHAINS, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.CHARGE, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.CHARGE, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.CHARGE, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.COMMITTED, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.COMMITTED, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.COMMITTED, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.CRITICAL_HIT, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.CRITICAL_HIT, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.CRITICAL_HIT, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.ECHO, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.ECHO, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.ECHO, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.ENIGMA_RESONATOR, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.ENIGMA_RESONATOR, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.ENIGMA_RESONATOR, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.EXPLODING, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.EXPLODING, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.EXPLODING, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.FREEZING, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.FREEZING, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.FREEZING, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.FUSE_SHOT, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.FUSE_SHOT, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.FUSE_SHOT, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.GRAVITY, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.GRAVITY, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.GRAVITY, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.GROWING, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.GROWING, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.GROWING, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.GUARDING_STRIKE, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.GUARDING_STRIKE, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.GUARDING_STRIKE, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.JUNGLE_POISON, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.JUNGLE_POISON, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.JUNGLE_POISON, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.LEECHING, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.LEECHING, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.LEECHING, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.LEVITATION_SHOT, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.LEVITATION_SHOT, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.LEVITATION_SHOT, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.POISON_CLOUD, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.POISON_CLOUD, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.POISON_CLOUD, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.RADIANCE, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.RADIANCE, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.RADIANCE, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.RADIANCE_SHOT, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.RADIANCE_SHOT, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.RADIANCE_SHOT, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.RAMPAGING, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.RAMPAGING, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.RAMPAGING, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.REFRESHMENT, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.REFRESHMENT_SHOT, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.REPLENISH, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.REPLENISH, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.REPLENISH, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.RICOCHET, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.RICOCHET, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.RICOCHET, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.SHOCKWAVE, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.SHOCKWAVE, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.SHOCKWAVE, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.SMITING,1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.SMITING,2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.SMITING,3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.SOUL_SIPHON, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.SOUL_SIPHON, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.SOUL_SIPHON, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.STUNNING, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.STUNNING, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.STUNNING, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.SWIRLING, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.SWIRLING, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.SWIRLING, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.TEMPO_THEFT, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.TEMPO_THEFT, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.TEMPO_THEFT, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.THUNDERING, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.THUNDERING, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.THUNDERING, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.VOID_SHOT, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.VOID_SHOT, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.VOID_SHOT, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.VOID_STRIKE, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.VOID_STRIKE, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.VOID_STRIKE, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.WEAKENING, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.WEAKENING, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.WEAKENING, 3)));
            })
            .build();



    @Override
    public void onInitialize() {
        // Config
        McdwStatsConfig.initAll();
        McdwEnchantsConfig.init();
        McdwLootConfig.init();

        String defaultConfig =
            "{\n" +
            "  \"regenerate_stat_configs\": false,\n" +
            "  \"regenerate_enchants_configs\": false,\n" +
            "  \"regenerate_loot_configs\": false\n" +
            "}";
        File configFile = McdwBaseConfig.createFile("config/minecraft_dungeon_weapons/config.json", defaultConfig, false);
        JsonObject json = McdwBaseConfig.getJsonObject(McdwBaseConfig.readFile(configFile));

        McdwStatsConfig.generateConfigs(json == null || !json.has("regenerate_stat_configs") || json.get("regenerate_stat_configs").getAsBoolean());
        McdwStatsConfig.loadConfig();

        McdwEnchantsConfig.generateConfigs(json == null || !json.has("regenerate_enchants_configs") || json.get("regenerate_enchants_configs").getAsBoolean());
        McdwEnchantsConfig.loadConfig();

        McdwLootConfig.generateConfigs(json == null || !json.has("regenerate_loot_configs") || json.get(
                "regenerate_loot_configs").getAsBoolean());
        McdwLootConfig.loadConfig();

        // Enchants
        EnchantsRegistry.init();

        // Items
        ItemRegistry.addItems();
        ItemRegistry.registerItems();

        // Loot
        McdwLoottables.init();

        // Sounds
        Registry.register(Registry.SOUND_EVENT, McdwSoundEvents.ECHO_SOUND, McdwSoundEvents.ECHO_SOUND_EVENT);

        // EnchantGiver
        McdwEnchantsGiverConfig.appendEnchants();
    }
}
