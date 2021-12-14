/*package chronosacaria.mcdw.loottables;

import chronosacaria.mcdw.configs.McdwLootConfig;
import chronosacaria.mcdw.items.ItemRegistry;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class McdwLoottables {

    public static final Identifier[] PILLAGER_TOWER_LOOT_TABLES =
            new Identifier[]{LootTables.PILLAGER_OUTPOST_CHEST};
    public static final Identifier[] NETHER_FORTRESS_LOOT_TABLES =
            new Identifier[]{LootTables.NETHER_BRIDGE_CHEST};
    public static final Identifier[] PIGLIN_BASTION_LOOT_TABLES =
            new Identifier[]{LootTables.BASTION_TREASURE_CHEST, LootTables.BASTION_BRIDGE_CHEST,
                    LootTables.BASTION_OTHER_CHEST, LootTables.BASTION_HOGLIN_STABLE_CHEST};
    public static final Identifier[] UNDER_WATER_LOOT_TABLES =
            new Identifier[]{LootTables.UNDERWATER_RUIN_BIG_CHEST, LootTables.UNDERWATER_RUIN_SMALL_CHEST,
                    LootTables.SHIPWRECK_TREASURE_CHEST};
    public static final Identifier[] END_LOOT_TABLES =
            new Identifier[]{LootTables.END_CITY_TREASURE_CHEST};

    private static boolean pillagerTowerLootTables(Identifier lootTable){
        for (Identifier id : PILLAGER_TOWER_LOOT_TABLES){
            if (id.equals(lootTable)){
                return true;
            }
        }
        return false;
    }

    private static boolean netherFortressLootTables(Identifier lootTable){
        for (Identifier id : NETHER_FORTRESS_LOOT_TABLES){
            if (id.equals(lootTable)){
                return true;
            }
        }
        return false;
    }

    private static boolean piglinBastionTreasureChestLootTables(Identifier lootTable){
        for (Identifier id : PIGLIN_BASTION_LOOT_TABLES){
            if (id.equals(lootTable)){
                return true;
            }
        }
        return false;
    }

    private static boolean underWaterLootTables(Identifier lootTable){
        for (Identifier id : UNDER_WATER_LOOT_TABLES){
            if (id.equals(lootTable)){
                return true;
            }
        }
        return false;
    }

    private static boolean endLootTables(Identifier lootTable){
        for (Identifier id : END_LOOT_TABLES){
            if (id.equals(lootTable)){
                return true;
            }
        }
        return false;
    }



    public static void init() {
        LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, id, supplier, setter) -> {
            if ("minecraft:entities/bee".equals(id.toString())) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootNumberProvider.create(1, 1.0F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("item_bee_stinger")));

                supplier.pool(poolBuilder);
            }

            if ("minecraft:entities/witch".equals(id.toString())){
                LootPool poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(ItemRegistry.getItem("spear_cackling_broom")).weight(1))
                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                1.0F)).build())
                        .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build())
                        .build();
                supplier.withPool(poolBuilder);
            }

            if (pillagerTowerLootTables(id) && McdwLootConfig.getValue("pillager_towers")){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootNumberProvider.create(1, 0.01F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("crossbow_doom_crossbow")))

                        .rolls(BinomialLootNumberProvider.create(1, 0.01F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("crossbow_voidcaller_crossbow")))

                        .rolls(BinomialLootNumberProvider.create(1, 0.10F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("crossbow_corrupted_crossbow")));
                supplier.pool(poolBuilder);
            }

            if (netherFortressLootTables(id) && McdwLootConfig.getValue("nether_fortresses")){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootNumberProvider.create(1, 0.01F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("bow_ancient_bow")));
                supplier.pool(poolBuilder);
            }

            if (piglinBastionTreasureChestLootTables(id) && McdwLootConfig.getValue("piglin_bastions")){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootNumberProvider.create(1, 0.30F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("sword_broken_sawblade")))

                        .rolls(BinomialLootNumberProvider.create(3, 0.30F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("crossbow_cog_crossbow")))
                        .rolls(BinomialLootNumberProvider.create(1, 0.10F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("crossbow_pride_of_the_piglins")))

                        .rolls(BinomialLootNumberProvider.create(1, 0.30F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("hammer_boneclub")))
                        .rolls(BinomialLootNumberProvider.create(1, 0.10F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("hammer_bone_cudgel")));

                supplier.pool(poolBuilder);
            }

            if (underWaterLootTables(id) && McdwLootConfig.getValue("under_water")){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootNumberProvider.create(1, 0.30F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("sword_coral_blade")))
                        .rolls(BinomialLootNumberProvider.create(1, 0.10F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("sword_sponge_striker")))

                        .rolls(BinomialLootNumberProvider.create(1, 0.30F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("axe_anchor")))
                        .rolls(BinomialLootNumberProvider.create(1, 0.10F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("axe_encrusted_anchor")))

                        .rolls(BinomialLootNumberProvider.create(3, 0.30F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("bow_bubble_bow")))
                        .rolls(BinomialLootNumberProvider.create(1, 0.10F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("bow_bubble_burster")))

                        .rolls(BinomialLootNumberProvider.create(1, 0.30F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("crossbow_harpoon_crossbow")))
                        .rolls(BinomialLootNumberProvider.create(1, 0.10F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("crossbow_nautical_crossbow")));

                supplier.pool(poolBuilder);
            }

            if (endLootTables(id) && McdwLootConfig.getValue("end_cities")){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootNumberProvider.create(1, 0.30F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("dagger_backstabber")))
                        .rolls(BinomialLootNumberProvider.create(1, 0.10F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("dagger_swift_striker")))

                        .rolls(BinomialLootNumberProvider.create(1, 0.30F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("dagger_void_touched_blade")))
                        .rolls(BinomialLootNumberProvider.create(1, 0.10F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("dagger_the_beginning")))
                        .rolls(BinomialLootNumberProvider.create(1, 0.10F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("dagger_the_end")))

                        .rolls(BinomialLootNumberProvider.create(1, 0.30F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("sword_obsidian_claymore")))
                        .rolls(BinomialLootNumberProvider.create(1, 0.10F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("sword_the_starless_night")))

                        .rolls(BinomialLootNumberProvider.create(1, 0.30F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("bow_void_bow")))
                        .rolls(BinomialLootNumberProvider.create(1, 0.10F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("bow_call_of_the_void")))

                        .rolls(BinomialLootNumberProvider.create(1, 0.30F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("crossbow_shadow_crossbow")))
                        .rolls(BinomialLootNumberProvider.create(1, 0.10F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("crossbow_veiled_crossbow")));

                supplier.pool(poolBuilder);
            }
        }));

    }
}
*/
