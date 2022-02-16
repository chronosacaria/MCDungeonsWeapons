package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.AxesID;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.client.item.TooltipContext;
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
        for (AxesID axesID : AxesID.values()) {
            if (stack.getItem() == ItemsInit.axeItems.get(axesID)) {
                String str = axesID.toString().toLowerCase().substring(4);
                for (int i = 1; i <= tooltipSize(axesID); i++)
                    tooltip.add(new TranslatableText("tooltip_info_item.mcdw." + str + "_" + i).formatted(Formatting.ITALIC));
                break;
            }
        }
    }

    private int tooltipSize(AxesID axesID) {
        return switch (axesID) {
            case AXE_AXE -> 5;
            case AXE_FIREBRAND, AXE_ANCHOR, AXE_HIGHLAND -> 4;
            case AXE_ENCRUSTED_ANCHOR -> 0;
            //noinspection UnnecessaryDefault
            default -> 0;
        };
    }
}