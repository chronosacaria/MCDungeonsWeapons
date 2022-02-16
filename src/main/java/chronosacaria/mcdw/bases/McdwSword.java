package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.GlaivesID;
import chronosacaria.mcdw.enums.SwordsID;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
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
       super.appendTooltip(stack, world, tooltip, tooltipContext);
       //for (GlaivesID glaivesID : GlaivesID.values()) {
       //    if (stack.getItem() == ItemsInit.glaiveItems.get(glaivesID)) {
       //        int i = 1;
       //        String str = glaivesID.toString().toLowerCase().substring(9);
       //        String translationKey = String.format("tooltip_info_item.mcdw.%s_", str);
       //        while (I18n.hasTranslation(translationKey + i)) {
       //            tooltip.add(new TranslatableText(translationKey + i).formatted(Formatting.ITALIC));
       //            i++;
       //        }
       //        break;
       //    }
       //}
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_CLAYMORE)){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.claymore_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.claymore_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.claymore_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.claymore_4").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_BROADSWORD)){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broadsword_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broadsword_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broadsword_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broadsword_4").formatted(Formatting.ITALIC));
        }
       if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_FROST_SLAYER)) {
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_slayer_1").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_slayer_2").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_slayer_3").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.frost_slayer_4").formatted(Formatting.ITALIC));
       }
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_GREAT_AXEBLADE)){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_axeblade_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_axeblade_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_axeblade_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_axeblade_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.great_axeblade_5").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_HEARTSTEALER)){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heartstealer_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heartstealer_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heartstealer_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heartstealer_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heartstealer_5").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_OBSIDIAN_CLAYMORE)){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.obsidian_claymore_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.obsidian_claymore_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.obsidian_claymore_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_THE_STARLESS_NIGHT)){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.the_starless_night_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.the_starless_night_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.the_starless_night_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_CUTLASS)){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cutlass_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cutlass_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cutlass_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_DANCERS_SWORD)){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dancers_sword_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dancers_sword_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_NAMELESS_BLADE)){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nameless_blade_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nameless_blade_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_KATANA)){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.katana_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.katana_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.katana_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_MASTERS_KATANA)){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.masters_katana_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.masters_katana_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.masters_katana_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_DARK_KATANA)){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dark_katana_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dark_katana_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_RAPIER)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.rapier_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.rapier_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_BEESTINGER)){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.beestinger_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.beestinger_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.beestinger_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.beestinger_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.beestinger_1").formatted(Formatting.GRAY));
        }
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_FREEZING_FOIL)){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.freezing_foil_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.freezing_foil_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.freezing_foil_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_DIAMOND_SWORD_VAR)){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.diamond_sword_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.diamond_sword_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.diamond_sword_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_IRON_SWORD_VAR)){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.iron_sword_var_1").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_HAWKBRAND)){
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.hawkbrand_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.hawkbrand_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.hawkbrand_3").formatted(Formatting.ITALIC));
        }
       if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_SINISTER)){
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sinister_1").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sinister_2").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sinister_3").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sinister_4").formatted(Formatting.ITALIC));
       }
       if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_BROKEN_SAWBLADE)){
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broken_sawblade_1").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broken_sawblade_2").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.broken_sawblade_3").formatted(Formatting.ITALIC));
       }
       if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_MECHANIZED_SAWBLADE)){
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.mechanized_sawblade_1").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.mechanized_sawblade_2").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.mechanized_sawblade_3").formatted(Formatting.ITALIC));
       }
       if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_CORAL_BLADE)){
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.coral_blade_1").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.coral_blade_2").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.coral_blade_3").formatted(Formatting.ITALIC));
       }
       if (stack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_SPONGE_STRIKER)){
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sponge_striker_1").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sponge_striker_2").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sponge_striker_3").formatted(Formatting.ITALIC));
           tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sponge_striker_4").formatted(Formatting.ITALIC));
       }

    }

}
