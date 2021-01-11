package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.items.ItemRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class McdwSoulDagger extends SwordItem {
    public McdwSoulDagger(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {

        if (stack.getItem() == ItemRegistry.getItem("dagger_soul_knife")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_knife_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_knife_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_knife_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("sword_truthseeker")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.truthseeker_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.truthseeker_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.truthseeker_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("dagger_eternal_knife")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.eternal_knife_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.eternal_knife_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.eternal_knife_3").formatted(Formatting.ITALIC));

        }
    }
}