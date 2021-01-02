package chronosacaria.mcdw.loottables;

import chronosacaria.mcdw.items.ItemRegistry;
import chronosacaria.mcdw.weapons.Crossbows;
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
                        .with(ItemEntry.builder(ItemRegistry.BEE_STINGER_ITEM));

                supplier.pool(poolBuilder);
            }

            if (pillagerTowerLootTables(id)){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(new BinomialLootTableRange(1, 0.01F))
                        .with(ItemEntry.builder(Crossbows.CROSSBOW_DOOM_CROSSBOW))

                        .rolls(new BinomialLootTableRange(1, 0.01F))
                        .with(ItemEntry.builder(Crossbows.CROSSBOW_VOID_CALLER_CROSSBOW))

                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(Crossbows.CROSSBOW_CORRUPTED_CROSSBOW));
                supplier.pool(poolBuilder);
            }
        }));

    }
}
