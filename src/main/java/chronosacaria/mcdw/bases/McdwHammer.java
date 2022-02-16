package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.HammersID;
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

// TODO change to AxeItem and make sure that they cannot strip logs
public class McdwHammer extends SwordItem {
    public McdwHammer(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed,
                new Item.Settings().group(Mcdw.WEAPONS).rarity(RarityHelper.fromToolMaterial(material)));
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        super.appendTooltip(stack, world, tooltip, tooltipContext);
        for (HammersID hammersID : HammersID.values()) {
            if (stack.getItem() == ItemsInit.hammerItems.get(hammersID)) {
                int i = 1;
                String str = hammersID.toString().toLowerCase().substring(7);
                String translationKey = String.format("tooltip_info_item.mcdw.%s_", str);
                while (I18n.hasTranslation(translationKey + i)) {
                    tooltip.add(new TranslatableText(translationKey + i).formatted(Formatting.ITALIC));
                    i++;
                }
                break;
            }
        }
        if (stack.getItem() == ItemsInit.hammerItems.get(HammersID.HAMMER_GREAT)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_hammer_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_hammer_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_hammer_3").formatted(Formatting.ITALIC));
        }
    }
}