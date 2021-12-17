package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.ScythesID;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class McdwScythe extends SwordItem {
    public McdwScythe(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed,
                new Item.Settings().group(Mcdw.WEAPONS).rarity(RarityHelper.fromToolMaterial(material)));
    }
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (stack.getItem() == ItemsInit.scytheItems.get(ScythesID.SICKLE_SOUL_SCYTHE)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_scythe_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_scythe_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_scythe_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.scytheItems.get(ScythesID.SICKLE_FROST_SCYTHE)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_scythe_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_scythe_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_scythe_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_scythe_4").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.scytheItems.get(ScythesID.SICKLE_JAILORS_SCYTHE)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.jailors_scythe_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.jailors_scythe_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.scytheItems.get(ScythesID.SICKLE_SKULL_SCYTHE)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.skull_scythe_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.skull_scythe_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.skull_scythe_3").formatted(Formatting.ITALIC));
        }
    }
}