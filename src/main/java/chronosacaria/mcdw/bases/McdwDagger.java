package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import chronosacaria.mcdw.items.ItemRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class McdwDagger extends SwordItem implements IOffhandAttack {

    public McdwDagger(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn){
        return useOffhand(worldIn, playerIn, handIn);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (stack.getItem() == ItemRegistry.getItem("dagger_dagger")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dagger_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dagger_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_note_item.mcdw.dualwield").formatted(Formatting.GREEN));

        }
        if (stack.getItem() == ItemRegistry.getItem("dagger_fangs_of_frost")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.fangs_of_frost_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.fangs_of_frost_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.fangs_of_frost_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.fangs_of_frost_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_note_item.mcdw.dualwield").formatted(Formatting.GREEN));

        }
        if (stack.getItem() == ItemRegistry.getItem("dagger_moon")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.moon_daggers_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.moon_daggers_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.moon_daggers_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_note_item.mcdw.dualwield").formatted(Formatting.GREEN));

        }
        if (stack.getItem() == ItemRegistry.getItem("dagger_shear_dagger")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.shear_daggers_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.shear_daggers_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.shear_daggers_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_note_item.mcdw.dualwield").formatted(Formatting.GREEN));

        }

        if (stack.getItem() == ItemRegistry.getItem("dagger_tempest_knife")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.tempest_knife_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.tempest_knife_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.tempest_knife_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("dagger_resolute_tempest_knife")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.resolute_tempest_knife_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.resolute_tempest_knife_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.resolute_tempest_knife_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_note_item.mcdw.dualwield").formatted(Formatting.GREEN));

        }
        if (stack.getItem() == ItemRegistry.getItem("dagger_chill_gale_knife")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.chill_gale_knife_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.chill_gale_knife_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.chill_gale_knife_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.chill_gale_knife_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_note_item.mcdw.dualwield").formatted(Formatting.GREEN));

        }
    }
}