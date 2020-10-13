package chronosacaria.mcdw.api;

import net.minecraft.item.ItemStack;

public interface ProjectileManipulator {
    void setOrigin(ItemStack stack);
    ItemStack getOrigin();
}
