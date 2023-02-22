/*
package chronosacaria.mcdw.datagen;

import chronosacaria.mcdw.enums.AxesID;
import chronosacaria.mcdw.registries.ItemsRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class McdwAxeRecipeGenerator extends FabricRecipeProvider {
    public McdwAxeRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        // Anchor Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ItemsRegistry.AXE_ITEMS.get(AxesID.AXE_ANCHOR))
                .pattern("#N#")
                .pattern("NI#")
                .pattern("###")
                .input('N', Items.IRON_NUGGET)
                .input('I', Items.IRON_INGOT)
                .input('#', Items.IRON_BLOCK)
                .criterion(FabricRecipeProvider.hasItem(Items.IRON_NUGGET),
                        FabricRecipeProvider.conditionsFromItem(Items.IRON_NUGGET))
                .criterion(FabricRecipeProvider.hasItem(Items.IRON_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                .criterion(FabricRecipeProvider.hasItem(Items.IRON_BLOCK),
                        FabricRecipeProvider.conditionsFromItem(Items.IRON_BLOCK))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(AxesID.AXE_ANCHOR.getItem())));
    }
}

*/
