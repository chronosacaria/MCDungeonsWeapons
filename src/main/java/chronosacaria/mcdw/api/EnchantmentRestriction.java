package chronosacaria.mcdw.api;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public final class EnchantmentRestriction {
    public interface Condition {
        boolean isAcceptableItem(ItemStack itemStack);
    }

    private static HashMap<Enchantment, ArrayList<Condition>> permissions = new HashMap<>();
    private static HashMap<Enchantment, ArrayList<Condition>> prohibitions = new HashMap<>();

    // If alleviating condition is met, the enchantment will be applicable ignoring additional checks
    public static void permit(Enchantment enchantment, Condition condition) {
        var conditions = permissions.get(enchantment);
        if (conditions == null) {
            conditions = new ArrayList<>();
        }
        conditions.add(condition);
        permissions.put(enchantment, conditions);
    }

    public static boolean isPermitted(Enchantment enchantment, ItemStack itemStack) {
        var conditions = permissions.get(enchantment);
        if (conditions != null) {
            for(var condition: conditions) {
               if (condition.isAcceptableItem(itemStack)) {
                   return true;
               }
            }
        }
        return false;
    }

    public static void prohibit(Enchantment enchantment, Condition condition) {
        var conditions = prohibitions.get(enchantment);
        if (conditions == null) {
            conditions = new ArrayList<>();
        }
        conditions.add(condition);
        prohibitions.put(enchantment, conditions);
    }

    public static boolean isProhibited(Enchantment enchantment, ItemStack itemStack) {
        var conditions = prohibitions.get(enchantment);
        if (conditions != null) {
            for(var condition: conditions) {
                if (condition.isAcceptableItem(itemStack)) {
                    return true;
                }
            }
        }
        return false;
    }
}