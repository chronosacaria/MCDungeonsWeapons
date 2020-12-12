package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.weapons.Picks;
import chronosacaria.mcdw.weapons.Scythes;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.List;

public class McdwScythe extends SwordItem {
    public McdwScythe(ToolMaterial material, int attackDamage, float attackSpeed, String id) {
        super(material, attackDamage, attackSpeed, new Settings().group(Mcdw.WEAPONS));
        Registry.register(Registry.ITEM, new Identifier(Mcdw.MOD_ID, id), this);
    }
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (stack.getItem() == Scythes.SICKLE_SOUL_SCYTHE) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_scythe_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_scythe_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_scythe_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == Scythes.SICKLE_FROST_SCYTHE) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_scythe_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_scythe_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_scythe_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_scythe_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_scythe_5").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.frost_scythe_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == Scythes.SICKLE_JAILORS_SCYTHE) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.jailors_scythe_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.jailors_scythe_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.jailors_scythe_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.jailors_scythe_1").formatted(Formatting.GREEN));
        }
    }
}