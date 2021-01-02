package chronosacaria.mcdw.api.interfaces;

import net.minecraft.item.ItemStack;

public interface IRangedWeapon {
    // Non-Enchantment Abilities
    default boolean shootsFasterArrows(ItemStack stack){
        return false;
    }

    default boolean shootsExplosiveArrows(ItemStack stack){
        return false;
    }

    default boolean shootsHeavyArrows(ItemStack stack){
        return false;
    }

    default boolean setsPetsAttackTarget(ItemStack stack){
        return false;
    }

    default boolean shootsStrongChargedArrows(ItemStack stack) {
        return false;
    }

    default boolean hasExtraMultishot(ItemStack stack){
        return false;
    }

    default boolean shootsFreezingArrows(ItemStack stack){
        return false;
    }

    default boolean gathersSouls(ItemStack stack){
        return false;
    }

    default boolean hasGuaranteedRicochet(ItemStack stack){ return false;}

    default boolean hasMultishotWhenCharged(ItemStack stack){ return false;}

    // Enchantment Abilities
    default boolean hasTempoTheftBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasEnigmaResonatorBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasQuickChargeBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasMultishotBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasAccelerateBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasSuperChargedBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasRicochetBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasPowerBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasPunchBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasReplenishBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasPoisonCloudBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasFuseShotBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasGrowingBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasBonusShotBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasPiercingBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasGravityBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasRadianceShotBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasWildRageBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasChainReactionBuiltIn(ItemStack stack){
        return false;
    }

    default boolean hasDynamoBuiltIn(ItemStack stack){
        return false;
    }
}
