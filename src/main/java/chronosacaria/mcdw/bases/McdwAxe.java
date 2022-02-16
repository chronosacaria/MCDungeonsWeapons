package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.AxesID;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class McdwAxe extends AxeItem {
    public McdwAxe(ToolMaterial material, float attackDamage, float attackSpeed){
        super(material, attackDamage, attackSpeed,
                new Item.Settings().group(Mcdw.WEAPONS).rarity(RarityHelper.fromToolMaterial(material)));
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        super.appendTooltip(stack, world, tooltip, tooltipContext);
        for (AxesID axesID : AxesID.values()) {
            if (stack.getItem() == ItemsInit.axeItems.get(axesID)) {
                int i = 1;
                String str = axesID.toString().toLowerCase().substring(4);
                String translationKey = String.format("tooltip_info_item.mcdw.%s_", str);
                while (I18n.hasTranslation(translationKey + i)) {
                    tooltip.add(new TranslatableText(translationKey + i).formatted(Formatting.ITALIC));
                    i++;
                }
                break;
            }
        }
    }
}