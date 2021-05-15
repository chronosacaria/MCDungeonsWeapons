package chronosacaria.mcdw.loottables;

import chronosacaria.mcdw.items.ItemRegistry;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.BinomialLootTableRange;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

public class McdwLoottables {

    public static final Identifier[] PILLAGER_TOWER_LOOT_TABLES =
            new Identifier[]{LootTables.PILLAGER_OUTPOST_CHEST};
    public static final Identifier[] NETHER_FORTRESS_LOOT_TABLES =
            new Identifier[]{LootTables.NETHER_BRIDGE_CHEST};
    public static final Identifier[] PIGLIN_BASTION_LOOT_TABLES =
            new Identifier[]{LootTables.BASTION_TREASURE_CHEST, LootTables.BASTION_BRIDGE_CHEST,
                    LootTables.BASTION_OTHER_CHEST, LootTables.BASTION_HOGLIN_STABLE_CHEST};

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



    public static void init() {
        LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, id, supplier, setter) -> {
            if ("minecraft:entities/bee".equals(id.toString())) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootTableRange.create(1, 1.0F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("item_bee_stinger")));

                supplier.pool(poolBuilder);
            }

            if (pillagerTowerLootTables(id)){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(new BinomialLootTableRange(1, 0.01F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("crossbow_doom_crossbow")))

                        .rolls(new BinomialLootTableRange(1, 0.01F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("crossbow_voidcaller_crossbow")))

                        .rolls(new BinomialLootTableRange(1, 0.10F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("crossbow_corrupted_crossbow")));
                supplier.pool(poolBuilder);
            }

            if (netherFortressLootTables(id)){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(new BinomialLootTableRange(1, 0.01F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("bow_ancient_bow")));
                supplier.pool(poolBuilder);
            }

            if (piglinBastionTreasureChestLootTables(id)){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(new BinomialLootTableRange(1, 0.30F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("sword_broken_sawblade")))

                        .rolls(new BinomialLootTableRange(3, 0.30F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("crossbow_cog_crossbow")))
                        .rolls(new BinomialLootTableRange(1, 0.10F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("crossbow_pride_of_the_piglins")))

                        .rolls(new BinomialLootTableRange(1, 0.30F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("hammer_boneclub")))
                        .rolls(new BinomialLootTableRange(1, 0.10F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("hammer_bone_cudgel")));

                supplier.pool(poolBuilder);
            }
        }));

    }
}
