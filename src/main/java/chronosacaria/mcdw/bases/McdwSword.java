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

public class McdwSword extends SwordItem {

    public McdwSword(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed,
                new Item.Settings().group(Mcdw.WEAPONS).rarity(RarityHelper.fromToolMaterial(material)));
    }

   @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        if (stack.getItem() == ItemRegistry.getItem("sword_claymore")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.claymore_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.claymore_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.claymore_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.claymore_4").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("sword_broadsword")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broadsword_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broadsword_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broadsword_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broadsword_4").formatted(Formatting.ITALIC));
        }
       if (stack.getItem() == ItemRegistry.getItem("sword_frost_slayer")) {
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_slayer_1").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_slayer_2").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_slayer_3").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_slayer_4").formatted(Formatting.ITALIC));
       }
        if (stack.getItem() == ItemRegistry.getItem("sword_great_axeblade")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_axeblade_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_axeblade_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_axeblade_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_axeblade_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_axeblade_5").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("sword_heartstealer")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heartstealer_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heartstealer_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heartstealer_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heartstealer_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heartstealer_5").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("sword_cutlass")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cutlass_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cutlass_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cutlass_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("sword_dancers_sword")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dancers_sword_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dancers_sword_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("sword_nameless_blade")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nameless_blade_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nameless_blade_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("sword_katana")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.katana_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.katana_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.katana_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("sword_masters_katana")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.masters_katana_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.masters_katana_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.masters_katana_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("sword_dark_katana")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dark_katana_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dark_katana_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("sword_rapier")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.rapier_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.rapier_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("sword_beestinger")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.beestinger_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.beestinger_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.beestinger_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.beestinger_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.beestinger_1").formatted(Formatting.GRAY));
        }
        if (stack.getItem() == ItemRegistry.getItem("sword_freezing_foil")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.freezing_foil_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.freezing_foil_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.freezing_foil_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("sword_diamond_sword_var")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.diamond_sword_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.diamond_sword_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.diamond_sword_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("sword_iron_sword_var")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.iron_sword_var_1").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemRegistry.getItem("sword_hawkbrand")){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.hawkbrand_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.hawkbrand_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.hawkbrand_3").formatted(Formatting.ITALIC));
        }
       if (stack.getItem() == ItemRegistry.getItem("sword_broken_sawblade")){
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broken_sawblade_1").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broken_sawblade_2").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broken_sawblade_3").formatted(Formatting.ITALIC));
       }
       if (stack.getItem() == ItemRegistry.getItem("sword_mechanized_sawblade")){
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.mechanized_sawblade_1").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.mechanized_sawblade_2").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.mechanized_sawblade_3").formatted(Formatting.ITALIC));
       }
       if (stack.getItem() == ItemRegistry.getItem("sword_coral_blade")){
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.coral_blade_1").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.coral_blade_2").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.coral_blade_3").formatted(Formatting.ITALIC));
       }
       if (stack.getItem() == ItemRegistry.getItem("sword_sponge_striker")){
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sponge_striker_1").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sponge_striker_2").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sponge_striker_3").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sponge_striker_4").formatted(Formatting.ITALIC));
       }

    }

}
