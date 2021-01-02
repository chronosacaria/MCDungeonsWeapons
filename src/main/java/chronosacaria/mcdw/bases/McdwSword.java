package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.weapons.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import chronosacaria.mcdw.Mcdw;
import net.minecraft.world.World;

import java.util.List;

public class McdwSword extends SwordItem {
    public McdwSword(ToolMaterial material,
                     int attackDamage,
                     float attackSpeed,
                     String id) {
        super(material,
                attackDamage,
                attackSpeed,
                new Item.Settings().group(Mcdw.WEAPONS));
        Registry.register(Registry.ITEM, new Identifier(Mcdw.MOD_ID, id), this);
    }

   @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        if (stack.getItem() == Claymores.SWORD_CLAYMORE){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.claymore_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.claymore_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.claymore_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.claymore_4").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == Claymores.SWORD_BROADSWORD){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broadsword_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broadsword_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broadsword_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broadsword_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broadsword_5").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.broadsword_1").formatted(Formatting.GREEN));
        }
       if (stack.getItem() == Claymores.SWORD_FROST_SLAYER) {
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_slayer_1").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_slayer_2").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_slayer_3").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_slayer_4").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.freezing").formatted(Formatting.GREEN));
       }
        if (stack.getItem() == Claymores.SWORD_GREAT_AXEBLADE){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_axeblade_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_axeblade_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_axeblade_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_axeblade_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_axeblade_5").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_axeblade_6").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.great_axeblade_1").formatted(Formatting.GREEN).formatted(Formatting.OBFUSCATED));
        }
        if (stack.getItem() == Claymores.SWORD_HEARTSTEALER){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heartstealer_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heartstealer_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heartstealer_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heartstealer_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heartstealer_5").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heartstealer_6").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.heartstealer_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == Curves.SWORD_CUTLASS){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cutlass_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cutlass_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cutlass_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == Curves.SWORD_DANCERS_SWORD){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dancers_sword_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dancers_sword_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dancers_sword_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.dancers_sword_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == Curves.SWORD_NAMELESS_BLADE){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nameless_blade_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nameless_blade_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nameless_blade_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.nameless_blade_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == Katanas.SWORD_KATANA){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.katana_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.katana_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.katana_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == Katanas.SWORD_MASTERS_KATANA){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.masters_katana_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.masters_katana_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.masters_katana_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.masters_katana_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.masters_katana_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == Katanas.SWORD_DARK_KATANA){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dark_katana_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dark_katana_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dark_katana_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.dark_katana_1").formatted(Formatting.GREEN));

        }
        if (stack.getItem() == Rapiers.SWORD_RAPIER){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.rapier_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.rapier_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == Rapiers.SWORD_BEESTINGER){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.beestinger_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.beestinger_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.beestinger_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.beestinger_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.beestinger_5").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.beestinger_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == Rapiers.SWORD_FREEZING_FOIL){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.freezing_foil_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.freezing_foil_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.freezing_foil_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.freezing_foil_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.freezing_foil_1").formatted(Formatting.GREEN));
        }
        if (stack.getItem() == Swords.SWORD_DIAMOND_SWORD){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.diamond_sword_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.diamond_sword_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.diamond_sword_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == Swords.SWORD_IRON_SWORD_VAR){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.iron_sword_var_1").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == Swords.SWORD_HAWKBRAND){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.hawkbrand_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.hawkbrand_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.hawkbrand_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.hawkbrand_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.hawkbrand_1").formatted(Formatting.GREEN));
        }

    }

}
