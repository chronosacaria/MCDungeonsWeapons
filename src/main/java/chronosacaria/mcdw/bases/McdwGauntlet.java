package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.CombatEventHandler;
import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import chronosacaria.mcdw.weapons.Gauntlets;
import chronosacaria.mcdw.weapons.SoulDaggers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.List;

public class McdwGauntlet extends SwordItem implements IOffhandAttack {
    public McdwGauntlet(ToolMaterial material,
                        int attackDamage,
                        float attackSpeed,
                        String id) {
        super(material,
                attackDamage,
                attackSpeed,
                new Settings().group(Mcdw.WEAPONS));
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
        if (stack.getItem() == Gauntlets.GAUNTLET_GAUNTLET) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gauntlets_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gauntlets_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_note_item.mcdw.dualwield").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == Gauntlets.GAUNTLET_MAULERS) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.maulers_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.maulers_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.maulers_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.maulers_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.maulers_1").formatted(Formatting.GREEN));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_note_item.mcdw.dualwield").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == Gauntlets.GAUNTLET_SOUL_FISTS) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_fists_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_fists_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_fists_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_fists_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.soul_fists_1").formatted(Formatting.GREEN));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_note_item.mcdw.dualwield").formatted(Formatting.GREEN));
        }
    }


}
