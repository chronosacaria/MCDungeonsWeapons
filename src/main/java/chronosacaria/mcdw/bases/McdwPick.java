package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.PicksID;
import chronosacaria.mcdw.items.ItemsInit;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class McdwPick extends PickaxeItem {
    String[] repairIngredient;
    public McdwPick(ToolMaterial material, int attackDamage, float attackSpeed, String[] repairIngredient) {
        super(material, attackDamage, attackSpeed,
                new Item.Settings().rarity(RarityHelper.fromToolMaterial(material)));
        ItemGroupEvents.modifyEntriesEvent(Mcdw.WEAPONS).register(entries -> entries.add(this));
        this.repairIngredient = repairIngredient;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return CleanlinessHelper.canRepairCheck(repairIngredient, ingredient);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        super.appendTooltip(stack, world, tooltip, tooltipContext);
        if (stack.getItem() == ItemsInit.PICK_ITEMS.get(PicksID.PICK_DIAMOND_PICKAXE_VAR)) {
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.diamond_pick_1").formatted(Formatting.ITALIC));
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.diamond_pick_2").formatted(Formatting.ITALIC));
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.diamond_pick_3").formatted(Formatting.ITALIC));
        }
    }
}