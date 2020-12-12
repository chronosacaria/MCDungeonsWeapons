package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.weapons.Axes;
import chronosacaria.mcdw.weapons.DoubleAxes;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.List;

public class McdwDoubleAxe extends AxeItem {
    public McdwDoubleAxe(ToolMaterial material, float attackDamage, float attackSpeed, String id){
        super(material, attackDamage, attackSpeed, new Settings().group(Mcdw.WEAPONS));
        Registry.register(Registry.ITEM, new Identifier(Mcdw.MOD_ID, id), this);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        if (stack.getItem() == DoubleAxes.AXE_DOUBLE){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.double_axe_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.double_axe_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == DoubleAxes.AXE_CURSED){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cursed_axe_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cursed_axe_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cursed_axe_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cursed_axe_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cursed_axe_5").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.cursed_axe_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == DoubleAxes.AXE_WHIRLWIND){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.whirlwind_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.whirlwind_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.whirlwind_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.whirlwind_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.whirlwind_1").formatted(Formatting.GREEN));
        }
    }
}