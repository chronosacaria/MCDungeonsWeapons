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

public class McdwScythe extends SwordItem {
    public McdwScythe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (stack.getItem() == ItemRegistry.getItem("sickle_soul_scythe")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_scythe_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_scythe_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_scythe_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("sickle_frost_scythe")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_scythe_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_scythe_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_scythe_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_scythe_4").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("sickle_jailors_scythe")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.jailors_scythe_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.jailors_scythe_2").formatted(Formatting.ITALIC));
        }
    }
}