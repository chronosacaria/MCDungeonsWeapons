package chronosacaria.mcdw.api.interfaces;

import net.minecraft.item.ItemStack;

/**
 * This code is used with the permission of <a href = "https://github.com/ZsoltMolnarrr">Daedelus</a>. <br />
 * The original code is from SpellPower and can be found <"https://github.com/ZsoltMolnarrr/SpellPower/blob/1.19.2/common/src/main/java/net/spell_power/api/enchantment/CustomConditionalEnchantment.java">here</a>.
 */

public interface CustomConditionalEnchantment {
    interface Condition {
        boolean isAcceptableItem(ItemStack stack);
    }
    void setCondition(Condition condition);
}
