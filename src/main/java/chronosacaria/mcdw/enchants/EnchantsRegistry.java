package chronosacaria.mcdw.enchants;

import chronosacaria.mcdw.enchants.enchantments.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class EnchantsRegistry {
    public static Enchantment ANIMA_CONDUIT;
    public static Enchantment CHAINS;
    public static Enchantment COMMITTED;
    public static Enchantment CRITICAL_HIT;
    public static Enchantment ECHO;
    public static Enchantment EXPLODING;
    public static Enchantment FREEZING;
    public static Enchantment GRAVITY;
    public static Enchantment JUNGLE_POISON;
    public static Enchantment LEECHING;
    public static Enchantment POISON_CLOUD;
    public static Enchantment RADIANCE;
    public static Enchantment RAMPAGING;
    public static Enchantment SHOCKWAVE;
    public static Enchantment SMITING;
    public static Enchantment SOUL_SIPHON;
    public static Enchantment STUNNING;
    public static Enchantment SWIRLING;
    public static Enchantment THUNDERING;
    public static Enchantment WEAKENING;

    public static Enchantment ACCELERATE;
    public static Enchantment RICOCHET;

    public static void init() {
        ANIMA_CONDUIT = new AnimaConduit(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        CRITICAL_HIT = new CriticalHit(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        CHAINS = new Chains(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        COMMITTED = new Committed(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        ECHO = new Echo(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        EXPLODING = new Exploding(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        FREEZING = new Freezing(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        GRAVITY = new Gravity(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        JUNGLE_POISON = new JunglePoison(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        LEECHING = new Leeching(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        POISON_CLOUD = new PoisonCloud(Enchantment.Rarity.COMMON, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        RADIANCE = new Radiance(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        RAMPAGING = new Rampaging(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        SHOCKWAVE = new Shockwave(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        SMITING = new Smiting(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        SOUL_SIPHON = new SoulSiphon(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        STUNNING = new Stunning(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        SWIRLING = new Swirling(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        THUNDERING = new Thundering(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        WEAKENING = new Weakening(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});

        ACCELERATE = new Accelerate(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        RICOCHET = new Ricochet(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});


    }

}
