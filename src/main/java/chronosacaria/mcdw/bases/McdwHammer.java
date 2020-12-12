package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.weapons.Glaives;
import chronosacaria.mcdw.weapons.Hammers;
import net.minecraft.advancement.criterion.ItemUsedOnBlockCriterion;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.UseAction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.List;

// TODO change to AxeItem and make sure that they cannot strip logs
public class McdwHammer extends SwordItem {
    public McdwHammer(ToolMaterial material, int attackDamage, float attackSpeed, String id) {
        super(material, attackDamage, attackSpeed, new Item.Settings().group(Mcdw.WEAPONS));
        Registry.register(Registry.ITEM, new Identifier(Mcdw.MOD_ID, id), this);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (stack.getItem() == Hammers.HAMMER_FLAIL) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.flail_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.flail_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.flail_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.flail_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.flail_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == Hammers.HAMMER_SUNS_GRACE) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.suns_grace_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.suns_grace_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.suns_grace_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.suns_grace_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == Hammers.HAMMER_GREAT) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_hammer_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_hammer_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_hammer_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == Hammers.HAMMER_GRAVITY) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gravity_hammer_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gravity_hammer_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gravity_hammer_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gravity_hammer_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.gravity_hammer_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == Hammers.HAMMER_STORMLANDER) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.stormlander_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.stormlander_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.stormlander_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.stormlander_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.stormlander_1").formatted(Formatting.GREEN));
        }

    }
}
