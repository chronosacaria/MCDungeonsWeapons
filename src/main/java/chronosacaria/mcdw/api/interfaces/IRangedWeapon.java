package chronosacaria.mcdw.api.interfaces;

import net.minecraft.item.ItemStack;

public interface IRangedWeapon {
    // Non-Enchantment Abilities
    default boolean shootsFasterArrows(ItemStack stack){
        return false;
    }

    default boolean shootsHeavyArrows(ItemStack stack){
        return false;
    }

    default boolean shootsStrongChargedArrows(ItemStack stack) {
        return false;
    }

    default boolean shootsFreezingArrows(ItemStack stack){
        return false;
    }

}
