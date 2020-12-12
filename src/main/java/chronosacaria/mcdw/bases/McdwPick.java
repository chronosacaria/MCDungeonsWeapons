package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.weapons.Hammers;
import chronosacaria.mcdw.weapons.Picks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import chronosacaria.mcdw.Mcdw;
import net.minecraft.world.World;

import java.util.List;

public class McdwPick extends PickaxeItem {
    public McdwPick(ToolMaterial material, int attackDamage, float attackSpeed, String id){
        super(material, attackDamage, attackSpeed, new Item.Settings().group(Mcdw.WEAPONS));
        Registry.register(Registry.ITEM, new Identifier(Mcdw.MOD_ID, id), this);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (stack.getItem() == Picks.PICK_DIAMOND_PICKAXE) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.diamond_pick_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.diamond_pick_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.diamond_pick_3").formatted(Formatting.ITALIC));
        }
    }
}
