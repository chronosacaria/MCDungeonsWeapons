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
    private static final ArrayListMultimap<Enchantment, Condition> PERMISSIONS = ArrayListMultimap.create();
    private static final ArrayList<TypeCondition> PERMISSIBLE_TARGETS = new ArrayList<>();
    private static final ArrayListMultimap<Enchantment, Condition> PROHIBITIONS = ArrayListMultimap.create();
    private static final ArrayList<TypeCondition> PROHIBITED_TARGETS = new ArrayList<>();


    public static void permit(Enchantment enchantment, Condition condition) {
        PERMISSIONS.put(enchantment, condition);
    }

    public static void permitTarget(TypeCondition typeCondition) {
        PERMISSIBLE_TARGETS.add(typeCondition);
    }

    public static void prohibit(Enchantment enchantment, Condition condition) {
        PROHIBITIONS.put(enchantment, condition);
    }

    public static void prohibitTarget(TypeCondition typeCondition) {
        PROHIBITED_TARGETS.add(typeCondition);
    }

    public static boolean isPermitted(Enchantment enchantment, ItemStack itemStack) {
        var conditions = PERMISSIONS.get(enchantment);
        for (var condition: conditions) {
            if (condition.isAcceptableItem(itemStack)) {
                return true;
            }
        }
        for(var condition: PERMISSIBLE_TARGETS) {
            if (condition.isAcceptableItem(enchantment, itemStack)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isProhibited(Enchantment enchantment, ItemStack itemStack) {
        var conditions = PROHIBITIONS.get(enchantment);
        for (var condition : conditions) {
            if (condition.isAcceptableItem(itemStack)) {
                return true;
            }
        }
        for (var condition : PROHIBITED_TARGETS) {
            if (condition.isAcceptableItem(enchantment, itemStack)) {
                return true;
            }
        }
        return false;
    }
}