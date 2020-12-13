package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import chronosacaria.mcdw.api.util.CombatEventHandler;
import chronosacaria.mcdw.weapons.Daggers;
import chronosacaria.mcdw.weapons.SoulDaggers;
import chronosacaria.mcdw.weapons.TempestKnives;
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

public class McdwSoulDagger extends SwordItem {
    public McdwSoulDagger(ToolMaterial material,
                          int attackDamage,
                          float attackSpeed,
                          String id) {
        super(material,
                attackDamage,
                attackSpeed,
                new Settings().group(Mcdw.WEAPONS));

        Registry.register(Registry.ITEM, new Identifier(Mcdw.MOD_ID, id), this);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {

        if (stack.getItem() == SoulDaggers.DAGGER_SOUL_KNIFE) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_knife_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_knife_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_knife_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == SoulDaggers.SWORD_TRUTHSEEKER) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.truthseeker_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.truthseeker_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.truthseeker_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.truthseeker_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.truthseeker_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == SoulDaggers.DAGGER_ETERNAL_KNIFE) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.eternal_knife_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.eternal_knife_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.eternal_knife_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.eternal_knife_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.eternal_knife_1").formatted(Formatting.GREEN));

        }
    }
}