package chronosacaria.mcdw.bases;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.ToolMaterial;

public class McdwShield extends ShieldItem {

    public final ToolMaterial material;

    public McdwShield(ToolMaterial material, Settings settings) {
        super(settings);
        this.material = material;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return this.material.getRepairIngredient().test(ingredient) || super.canRepair(stack, ingredient);
    }

}
