package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.SwordsID;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class McdwSword extends SwordItem {
    String[] repairIngredient;

    public McdwSword(ToolMaterial material, int attackDamage, float attackSpeed, String[] repairIngredient) {
        super(material, attackDamage, attackSpeed,
                new Item.Settings().group(Mcdw.WEAPONS).rarity(RarityHelper.fromToolMaterial(material)));
        this.repairIngredient = repairIngredient;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        List<Item> potentialIngredients = new ArrayList<>(List.of());
        AtomicBoolean isWood = new AtomicBoolean(false);
        AtomicBoolean isStone = new AtomicBoolean(false);
        Arrays.stream(repairIngredient).toList().forEach(repIngredient -> {
            if (repIngredient.contentEquals("minecraft:planks"))
                isWood.set(true);
            else if (repIngredient.contentEquals("minecraft:stone_crafting_materials"))
                isStone.set(true);
            potentialIngredients.add(
                    Registry.ITEM.get(new Identifier(repIngredient)));
        });

        return potentialIngredients.contains(ingredient.getItem())
                || (isWood.get() && ingredient.isIn(ItemTags.PLANKS)
                || (isStone.get() && ingredient.isIn(ItemTags.STONE_CRAFTING_MATERIALS)));
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        super.appendTooltip(stack, world, tooltip, tooltipContext);
        int i = 1;
        String str = stack.getItem().getTranslationKey().toLowerCase(Locale.ROOT).substring(16);
        String translationKey = String.format("tooltip_info_item.mcdw.%s_", str);
        while (I18n.hasTranslation(translationKey + i)) {
            tooltip.add(new TranslatableText(translationKey + i).formatted(Formatting.ITALIC));
            i++;
        }
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_BEESTINGER))
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.beestinger_1").formatted(Formatting.GRAY));
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_DIAMOND_SWORD_VAR)){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.diamond_sword_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.diamond_sword_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.diamond_sword_3").formatted(Formatting.ITALIC));
        }
    }

}