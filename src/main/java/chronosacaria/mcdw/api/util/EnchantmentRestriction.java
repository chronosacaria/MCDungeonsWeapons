package chronosacaria.mcdw.api.util;

import com.google.common.collect.ArrayListMultimap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

/**
 * This code is used with the permission of <a href = "https://github.com/ZsoltMolnarrr">Daedelus</a>. <br />
 * The original code is from SpellEngine and can be found <a href = "https://github.com/ZsoltMolnarrr/SpellEngine/blob/1.19.2/common/src/main/java/net/spell_engine/api/enchantment/EnchantmentRestriction.java">here</a>.
 */

public final class EnchantmentRestriction {
    public interface Condition {
        boolean isAcceptableItem(ItemStack itemStack);
    }
    public interface TypeCondition {
        boolean isAcceptableItem(Enchantment enchantment, ItemStack itemStack);
    }
    private static ArrayListMultimap<Enchantment, Condition> permissions = ArrayListMultimap.create();
    private static ArrayList<TypeCondition> permissibleTargets = new ArrayList<>();
    private static ArrayListMultimap<Enchantment, Condition> prohibitions = ArrayListMultimap.create();
    private static ArrayList<TypeCondition> prohibitedTargets = new ArrayList<>();


    public static void permit(Enchantment enchantment, Condition condition) {
        permissions.put(enchantment, condition);
    }

    public static void permitTarget(TypeCondition typeCondition) {
        permissibleTargets.add(typeCondition);
    }

    public static void prohibit(Enchantment enchantment, Condition condition) {
        prohibitions.put(enchantment, condition);
    }

    public static void prohibitTarget(TypeCondition typeCondition) {
        prohibitedTargets.add(typeCondition);
    }

    public static boolean isPermitted(Enchantment enchantment, ItemStack itemStack) {
        var conditions = permissions.get(enchantment);
        for (var condition: conditions) {
            if (condition.isAcceptableItem(itemStack)) {
                return true;
            }
        }
        for(var condition: permissibleTargets) {
            if (condition.isAcceptableItem(enchantment, itemStack)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isProhibited(Enchantment enchantment, ItemStack itemStack) {
        var conditions = prohibitions.get(enchantment);
        for (var condition : conditions) {
            if (condition.isAcceptableItem(itemStack)) {
                return true;
            }
        }
        for (var condition : prohibitedTargets) {
            if (condition.isAcceptableItem(enchantment, itemStack)) {
                return true;
            }
        }
        return false;
    }
}