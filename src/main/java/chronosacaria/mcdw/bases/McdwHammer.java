package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.items.ItemRegistry;
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

// TODO change to AxeItem and make sure that they cannot strip logs
public class McdwHammer extends SwordItem {
    public McdwHammer(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed, new Item.Settings().group(Mcdw.WEAPONS).rarity(RarityHelper.fromToolMaterial(material)));
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (stack.getItem() == ItemRegistry.getItem("hammer_flail")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.flail_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.flail_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.flail_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("hammer_suns_grace")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.suns_grace_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.suns_grace_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("hammer_great")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_hammer_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_hammer_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_hammer_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("hammer_gravity")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gravity_hammer_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gravity_hammer_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gravity_hammer_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("hammer_stormlander")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.stormlander_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.stormlander_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.stormlander_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("hammer_boneclub")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.boneclub_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.boneclub_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("hammer_bone_cudgel")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.bone_cudgel_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.bone_cudgel_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.bone_cudgel_3").formatted(Formatting.ITALIC));
        }

    }
}
