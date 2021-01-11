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

public class McdwSickle extends SwordItem implements IOffhandAttack {
    public McdwSickle(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn){
        return useOffhand(worldIn, playerIn, handIn);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (stack.getItem() == ItemRegistry.getItem("sickle_sickle")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sickle_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sickle_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sickle_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_note_item.mcdw.dualwield").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == ItemRegistry.getItem("sickle_last_laugh_gold") || stack.getItem() == ItemRegistry.getItem("sickle_last_laugh_silver")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.last_laugh_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.last_laugh_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.last_laugh_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_note_item.mcdw.dualwield").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == ItemRegistry.getItem("sickle_nightmares_bite")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nightmares_bite_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nightmares_bite_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nightmares_bite_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_note_item.mcdw.dualwield").formatted(Formatting.GREEN));
        }
    }

}