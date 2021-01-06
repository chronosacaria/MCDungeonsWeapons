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
    private static boolean pillagerTowerLootTables(Identifier lootTable){
        for (Identifier id : PILLAGER_TOWER_LOOT_TABLES){
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

                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ItemRegistry.getItem("crossbow_corrupted_crossbow")));
                supplier.pool(poolBuilder);
            }
        }));

    }
}
