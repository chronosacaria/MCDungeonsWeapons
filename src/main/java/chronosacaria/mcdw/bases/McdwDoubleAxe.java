package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.DoubleAxesID;
import chronosacaria.mcdw.registries.ItemGroupRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class McdwDoubleAxe extends AxeItem implements IInnateEnchantment {
    String[] repairIngredient;
    DoubleAxesID doubleAxesEnum;
    public McdwDoubleAxe(DoubleAxesID doubleAxesEnum, ToolMaterial material, float attackDamage, float attackSpeed, String[] repairIngredient){
        super(material, attackDamage, attackSpeed,
                new Item.Settings().rarity(RarityHelper.fromToolMaterial(material)));
        this.doubleAxesEnum = doubleAxesEnum;
        ItemGroupEvents.modifyEntriesEvent(ItemGroupRegistry.MELEE).register(entries -> entries.add(this.getDefaultStack()));
        this.repairIngredient = repairIngredient;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return CleanlinessHelper.canRepairCheck(repairIngredient, ingredient);
    }

    @Override
    public ItemStack getDefaultStack() {
        return getInnateEnchantedStack(this);
    }

    @Override
    public Map<Enchantment, Integer> getInnateEnchantments() {
        return this.doubleAxesEnum.getInnateEnchantments();
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        super.appendTooltip(stack, world, tooltip, tooltipContext);
        CleanlinessHelper.mcdw$tooltipHelper(stack, tooltip, 21);
    }
}