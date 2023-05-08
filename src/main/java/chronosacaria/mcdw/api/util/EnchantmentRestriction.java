package chronosacaria.mcdw.api.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This code is used with the permission of <a href = "https://github.com/ZsoltMolnarrr">Daedelus</a>. <br />
 * The original code is from SpellEngine and can be found <a href = "https://github.com/ZsoltMolnarrr/SpellEngine/blob/1.19.2/common/src/main/java/net/spell_engine/api/enchantment/EnchantmentRestriction.java">here</a>.
 */

public final class EnchantmentRestriction {
    public interface Condition {
        boolean isAcceptableItem(ItemStack itemStack);
    }
    private static HashMap<Enchantment, ArrayList<Condition>> alleviations = new HashMap<>();

    // If alleviating condition is met, the enchantment will be applicable ignoring additional checks
    public static void alleviate(Enchantment enchantment, Condition condition) {
        var conditions = alleviations.get(enchantment);
        if (conditions == null) {
            conditions = new ArrayList<>();
        }
        conditions.add(condition);
        alleviations.put(enchantment, conditions);
    }

    public static boolean isAlleviated(Enchantment enchantment, ItemStack itemStack) {
        var conditions = alleviations.get(enchantment);
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