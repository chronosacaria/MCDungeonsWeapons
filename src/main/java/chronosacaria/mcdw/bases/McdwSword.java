package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.SwordsID;
import chronosacaria.mcdw.items.ItemsInit;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.Locale;

public class McdwSword extends SwordItem {
    String[] repairIngredient;

    public McdwSword(ToolMaterial material, int attackDamage, float attackSpeed, String[] repairIngredient) {
        super(material, attackDamage, attackSpeed,
                new Item.Settings().rarity(RarityHelper.fromToolMaterial(material)));
        ItemGroupEvents.modifyEntriesEvent(Mcdw.WEAPONS).register(entries -> entries.add(this));
        this.repairIngredient = repairIngredient;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return CleanlinessHelper.canRepairCheck(repairIngredient, ingredient);
    }

   @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
       super.appendTooltip(stack, world, tooltip, tooltipContext);
       int i = 1;
       String str = stack.getItem().getTranslationKey().toLowerCase(Locale.ROOT).substring(16);
       String translationKey = String.format("tooltip_info_item.mcdw.%s_", str);
       while (I18n.hasTranslation(translationKey + i)) {
           tooltip.add(Text.translatable(translationKey + i).formatted(Formatting.ITALIC));
           i++;
       }
        if (stack.getItem() == ItemsInit.SWORD_ITEMS.get(SwordsID.SWORD_BEESTINGER))
            tooltip.add(Text.translatable("tooltip_ench_item.mcdw.beestinger_1").formatted(Formatting.GRAY));
        if (stack.getItem() == ItemsInit.SWORD_ITEMS.get(SwordsID.SWORD_DIAMOND_SWORD_VAR)){
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.diamond_sword_1").formatted(Formatting.ITALIC));
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.diamond_sword_2").formatted(Formatting.ITALIC));
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.diamond_sword_3").formatted(Formatting.ITALIC));
        }
    }

}
