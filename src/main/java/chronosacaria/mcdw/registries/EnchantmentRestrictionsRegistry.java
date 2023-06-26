package chronosacaria.mcdw.registries;

import chronosacaria.mcdw.api.util.EnchantmentRestriction;
import chronosacaria.mcdw.bases.McdwAxe;
import chronosacaria.mcdw.bases.McdwDoubleAxe;
import chronosacaria.mcdw.bases.McdwSpear;
import chronosacaria.mcdw.enums.SwordsID;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantments;

public class EnchantmentRestrictionsRegistry {
    public static void register() {
        // Individual Enchantment Permissions
        EnchantmentRestriction.permit(Enchantments.FIRE_ASPECT, itemStack -> itemStack.getItem() instanceof McdwAxe || itemStack.getItem() instanceof McdwDoubleAxe);
        EnchantmentRestriction.permit(Enchantments.EFFICIENCY, itemStack -> itemStack.isOf(SwordsID.SWORD_MECHANIZED_SAWBLADE.getItem()));

        // Enchantment Type Permissions
        EnchantmentRestriction.permitTarget((enchantment, itemStack) -> enchantment instanceof DamageEnchantment && itemStack.getItem() instanceof McdwSpear);

        // Individual Enchantment Prohibitions
        EnchantmentRestriction.prohibit(Enchantments.UNBREAKING, itemStack -> itemStack.isOf(SwordsID.SWORD_MECHANIZED_SAWBLADE.getItem()));
        EnchantmentRestriction.prohibit(Enchantments.UNBREAKING, itemStack -> itemStack.isOf(SwordsID.SWORD_BROKEN_SAWBLADE.getItem()));
        EnchantmentRestriction.prohibit(Enchantments.EFFICIENCY, itemStack -> itemStack.isOf(SwordsID.SWORD_BROKEN_SAWBLADE.getItem()));
    }
}