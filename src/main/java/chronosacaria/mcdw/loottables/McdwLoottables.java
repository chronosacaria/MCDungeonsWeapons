package chronosacaria.mcdw.loottables;

import chronosacaria.mcdw.items.ItemRegistry;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.BinomialLootTableRange;
import net.minecraft.loot.entry.ItemEntry;

public class McdwLoottables {
    public static void init(){
        LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, id, supplier, setter) -> {
            if ("minecraft:entities/bee".equals(id.toString())){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootTableRange.create(1,1.0F))
                        .with(ItemEntry.builder(ItemRegistry.BEE_STINGER_ITEM));

                supplier.pool(poolBuilder);
            }
        }));
    }
}
