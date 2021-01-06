package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.items.ItemRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class McdwDoubleAxe extends AxeItem {

    public McdwDoubleAxe(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings){
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        if (stack.getItem() == ItemRegistry.getItem("axe_double")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.double_axe_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.double_axe_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("axe_cursed")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cursed_axe_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cursed_axe_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cursed_axe_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cursed_axe_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cursed_axe_5").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.cursed_axe_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == ItemRegistry.getItem("axe_whirlwind")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.whirlwind_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.whirlwind_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.whirlwind_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.whirlwind_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.whirlwind_1").formatted(Formatting.GREEN));
        }
    }
}