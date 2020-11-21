package chronosacaria.mcdw.api;

import com.google.common.collect.Lists;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.List;
import java.util.TreeMap;
import java.util.function.Function;

/**
 * EnchantmentSkeleton
 * Buildable enchantment
 * vy biom4st3r
 */
public class EnchantmentSkeleton extends Enchantment {

    boolean enabled = false;
    boolean isTreasure = false;
    boolean isCurse = false;
    String regname = "";
    int minlevel = 1;
    int maxlevel = 1;
    Function<ItemStack,Boolean> isAcceptible = ((itemStack)->{return this.type.isAcceptableItem(itemStack.getItem());});
    LevelProvider minpower = (level) -> {return 1 + level * 10;};
    LevelProvider maxpower = (level) -> {return minpower.supply(level) + 5;};
    List<Enchantment> exclusiveEnchantments = Lists.newArrayList();
    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    @Override
    public Text getName(int level)
    {
        MutableText text = new TranslatableText(this.getTranslationKey(), new Object[0]);
        if (this.isCursed()) {
            text.formatted(Formatting.RED);
        } else {
            text.formatted(Formatting.GRAY);
        }
        if (level != 1 || this.getMaxLevel() != 1) {
            text.append(" ").append(new TranslatableText(toRoman(level)));
        }
        return text;
    }

    public static String toRoman(int num) {
        int l = map.floorKey(num);
        if (num == l) {
            return map.get(num);
        }
        return map.get(l) + toRoman(num - l);
    }

    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    public interface LevelProvider
    {
        public int supply(int level);
    }

    public EnchantmentSkeleton(Rarity r, EnchantmentTarget et, EquipmentSlot[] es) {
        super(r, et, es);
        this.exclusiveEnchantments.add(this);
    }

    @Override
    public boolean isCursed() {
        return isCurse;
    }
    @Override
    public boolean isTreasure() {
        return isTreasure;
    }

    public String regName() {
        return regname;
    }

    public boolean enabled() {
        return enabled;
    }

    @Override
    public int getMaxLevel() {
        return maxlevel;
    }

    @Override
    public int getMinLevel() {
        return minlevel;
    }

    @Override
    public boolean isAcceptableItem(ItemStack iS) {
        return isAcceptible.apply(iS);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return !exclusiveEnchantments.contains(other);
    }

    @Override
    public int getMinPower(int level) {

        return minpower.supply(level);
    }

    @Override
    public int getMaxPower(int level) {

        return maxpower.supply(level);
    }

    public boolean hasEnchantment(ItemStack i)
    {
        return EnchantmentHelper.getLevel(this, i) > 0;
    }

    public int getLevel(ItemStack i)
    {
        return EnchantmentHelper.getLevel(this, i);
    }

    public static boolean hasEnchant(Enchantment e, ItemStack i) {
        return EnchantmentHelper.getLevel(e, i) > 0;
    }


}
