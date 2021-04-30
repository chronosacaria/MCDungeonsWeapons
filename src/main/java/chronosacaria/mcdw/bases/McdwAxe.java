package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.items.ItemRegistry;
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
        super(material, attackDamage, attackSpeed, new Item.Settings().group(Mcdw.WEAPONS).rarity(RarityHelper.fromToolMaterial(material)));
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        if (stack.getItem() == ItemRegistry.getItem("axe")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.axe_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.axe_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.axe_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.axe_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.axe_5").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("axe_firebrand")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.firebrand_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.firebrand_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.firebrand_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.firebrand_4").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("axe_highland")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.highland_axe_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.highland_axe_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.highland_axe_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.highland_axe_4").formatted(Formatting.ITALIC));
        }
    }
}