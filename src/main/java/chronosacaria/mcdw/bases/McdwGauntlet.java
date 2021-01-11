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

public class McdwGauntlet extends SwordItem implements IOffhandAttack {

    public McdwGauntlet(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn){
        return useOffhand(worldIn, playerIn, handIn);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (stack.getItem() == ItemRegistry.getItem("gauntlet_gauntlet")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gauntlets_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gauntlets_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_note_item.mcdw.dualwield").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == ItemRegistry.getItem("gauntlet_maulers")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.maulers_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.maulers_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.maulers_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_note_item.mcdw.dualwield").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == ItemRegistry.getItem("gauntlet_soul_fists")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_fists_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_fists_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_fists_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_note_item.mcdw.dualwield").formatted(Formatting.GREEN));
        }
    }


}
