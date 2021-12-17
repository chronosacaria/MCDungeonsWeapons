package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.HammersID;
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

// TODO change to AxeItem and make sure that they cannot strip logs
public class McdwHammer extends SwordItem {
    public McdwHammer(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed,
                new Item.Settings().group(Mcdw.WEAPONS).rarity(RarityHelper.fromToolMaterial(material)));
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (stack.getItem() == ItemsInit.hammerItems.get(HammersID.HAMMER_MACE)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.mace_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.mace_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.mace_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.hammerItems.get(HammersID.HAMMER_FLAIL)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.flail_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.flail_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.flail_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.hammerItems.get(HammersID.HAMMER_SUNS_GRACE)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.suns_grace_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.suns_grace_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.hammerItems.get(HammersID.HAMMER_GREAT)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_hammer_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_hammer_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_hammer_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.hammerItems.get(HammersID.HAMMER_GRAVITY)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gravity_hammer_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gravity_hammer_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gravity_hammer_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.hammerItems.get(HammersID.HAMMER_STORMLANDER)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.stormlander_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.stormlander_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.stormlander_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.hammerItems.get(HammersID.HAMMER_BONECLUB)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.boneclub_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.boneclub_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.hammerItems.get(HammersID.HAMMER_BONE_CUDGEL)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.bone_cudgel_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.bone_cudgel_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.bone_cudgel_3").formatted(Formatting.ITALIC));
        }

    }
}
