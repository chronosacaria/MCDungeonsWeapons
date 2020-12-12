package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import chronosacaria.mcdw.api.util.CombatEventHandler;
import chronosacaria.mcdw.weapons.Daggers;
import chronosacaria.mcdw.weapons.SoulDaggers;
import chronosacaria.mcdw.weapons.TempestKnives;
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

public class McdwDagger extends SwordItem implements IOffhandAttack {
    public McdwDagger(ToolMaterial material,
                      int attackDamage,
                      float attackSpeed,
                      String id) {
        super(material,
                attackDamage,
                attackSpeed,
                new Item.Settings().group(Mcdw.WEAPONS));

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
        if (stack.getItem() == Daggers.DAGGER_DAGGER) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dagger_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dagger_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == Daggers.DAGGER_FANGS_OF_FROST) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.fangs_of_frost_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.fangs_of_frost_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.fangs_of_frost_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.fangs_of_frost_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.fangs_of_frost_5").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.fangs_of_frost_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == Daggers.DAGGER_MOON) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.moon_daggers_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.moon_daggers_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.moon_daggers_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.moon_daggers_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.moon_daggers_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == Daggers.DAGGER_SHEAR_DAGGER) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.shear_daggers_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.shear_daggers_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.shear_daggers_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.shear_daggers_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.shear_daggers_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == SoulDaggers.DAGGER_SOUL_KNIFE) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_knife_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_knife_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_knife_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == SoulDaggers.DAGGER_ETERNAL_KNIFE) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.eternal_knife_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.eternal_knife_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.eternal_knife_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.eternal_knife_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.eternal_knife_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == SoulDaggers.SWORD_TRUTHSEEKER) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.truthseeker_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.truthseeker_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.truthseeker_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.truthseeker_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.truthseeker_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == TempestKnives.DAGGER_TEMPEST_KNIFE) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.tempest_knife_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.tempest_knife_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.tempest_knife_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == TempestKnives.DAGGER_RESOLUTE_TEMPEST_KNIFE) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.resolute_tempest_knife_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.resolute_tempest_knife_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.resolute_tempest_knife_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.resolute_tempest_knife_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.resolute_tempest_knife_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == TempestKnives.DAGGER_CHILL_GALE_KNIFE) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.chill_gale_knife_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.chill_gale_knife_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.chill_gale_knife_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.chill_gale_knife_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.chill_gale_knife_1").formatted(Formatting.GREEN));
        }
    }
}