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
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.Locale;

public class McdwSword extends SwordItem {

    public McdwSword(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed,
                new Item.Settings().group(Mcdw.WEAPONS).rarity(RarityHelper.fromToolMaterial(material)));
    }

   @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
       super.appendTooltip(stack, world, tooltip, tooltipContext);
       for (SwordsID swordsID : SwordsID.values()) {
           if (stack.getItem() == ItemsInit.swordItems.get(swordsID)) {
               int i = 1;
               String str = swordsID.toString().toLowerCase(Locale.ROOT).substring(6);
               String translationKey = String.format("tooltip_info_item.mcdw.%s_", str);
               while (I18n.hasTranslation(translationKey + i)) {
                   tooltip.add(new TranslatableText(translationKey + i).formatted(Formatting.ITALIC));
                   i++;
               }
               break;
           }
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
