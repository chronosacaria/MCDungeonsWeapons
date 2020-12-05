package chronosacaria.mcdw.api.interfaces;

import net.minecraft.item.ItemStack;

public interface ProjectileManipulator {
    void setOrigin(ItemStack stack);
    ItemStack getOrigin();
}
