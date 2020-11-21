package chronosacaria.mcdw.api;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

import java.util.function.Function;

/**
 * Enchantment Builder
 * by biom4st3r
 */

public class EnchantmentBuilder {
    EnchantmentSkeleton mcdwenchant;
    public static final EquipmentSlot[]
            HAND_SLOTS = new EquipmentSlot[] {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND};

    public EnchantmentBuilder(Enchantment.Rarity rarity, EnchantmentTarget target, EquipmentSlot... slots){
        this.mcdwenchant = new EnchantmentSkeleton(rarity, target, slots);
    }

    public EnchantmentBuilder enabled(boolean bool){
        this.mcdwenchant.enabled = bool;
        return this;
    }

    public  EnchantmentBuilder treasure(boolean bool){
        this.mcdwenchant.isTreasure = bool;
        return this;
    }

    public EnchantmentBuilder curse(boolean bool){
        this.mcdwenchant.isCurse = bool;
        return this;
    }

    public EnchantmentBuilder minlevel(int i)
    {
        this.mcdwenchant.minlevel = i;
        return this;
    }

    public EnchantmentBuilder maxlevel(int i)
    {
        this.mcdwenchant.maxlevel = i;
        return this;
    }

    public EnchantmentBuilder minpower(EnchantmentSkeleton.LevelProvider provider)
    {
        this.mcdwenchant.minpower = provider;
        return this;
    }

    public EnchantmentBuilder maxpower(EnchantmentSkeleton.LevelProvider provider)
    {
        this.mcdwenchant.maxpower = provider;
        return this;
    }

    public EnchantmentBuilder addExclusive(Enchantment e)
    {
        this.mcdwenchant.exclusiveEnchantments.add(e);
        return this;
    }

    public EnchantmentBuilder isAcceptible(Function<ItemStack,Boolean> isAcceptible)
    {
        this.mcdwenchant.isAcceptible = isAcceptible;
        return this;
    }

    public EnchantmentSkeleton build(String regname)
    {
        this.mcdwenchant.regname = regname.toLowerCase();
        return this.mcdwenchant;
    }
}
