package chronosacaria.mcdw.materials;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class BattlestaffMaterial implements ToolMaterial {

    @Override
    public int getDurability(){
        return 120;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 5.0F;
    }

    @Override
    public float getAttackDamage() {
        return 3.0F;
    }

    @Override
    public int getMiningLevel() {
        return 2;
    }

    @Override
    public int getEnchantability() {
        return 15;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.POTATO);
    }
}
