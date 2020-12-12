package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.api.util.CombatEventHandler;
import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import chronosacaria.mcdw.weapons.Scythes;
import chronosacaria.mcdw.weapons.Sickles;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.util.registry.Registry;

import chronosacaria.mcdw.Mcdw;
import net.minecraft.world.World;

import java.util.List;

public class McdwSickle extends SwordItem implements IOffhandAttack {
    public McdwSickle(ToolMaterial material, int attackDamage, float attackSpeed, String id) {
        super(material, attackDamage, attackSpeed, new Item.Settings().group(Mcdw.WEAPONS));
        Registry.register(Registry.ITEM, new Identifier(Mcdw.MOD_ID, id), this);
    }

    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn){

        if (handIn == Hand.OFF_HAND && worldIn.isClient){
            MinecraftClient client = MinecraftClient.getInstance();
            CombatEventHandler.checkForOffHandAttack();
            ItemStack offhand = playerIn.getStackInHand(handIn);
            return new TypedActionResult<>(ActionResult.SUCCESS, offhand);
        } else {
            return new TypedActionResult<>(ActionResult.PASS, playerIn.getStackInHand(handIn));
        }

    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (stack.getItem() == Sickles.SICKLE_SICKLE) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sickle_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sickle_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sickle_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == Sickles.SICKLE_LAST_LAUGH_GOLD || stack.getItem() == Sickles.SICKLE_LAST_LAUGH_SILVER) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.last_laugh_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.last_laugh_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.last_laugh_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.last_laugh_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.last_laugh_1").formatted(Formatting.GREEN).formatted(Formatting.OBFUSCATED));
        }
        if (stack.getItem() == Sickles.SICKLE_NIGHTMARES_BITE) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nightmares_bite_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nightmares_bite_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nightmares_bite_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nightmares_bite_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.nightmares_bite_1").formatted(Formatting.GREEN));
        }
    }

}