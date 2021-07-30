package chronosacaria.mcdw.enchants;

import chronosacaria.mcdw.enchants.enchantments.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class EnchantsRegistry {
    public static Enchantment ANIMA_CONDUIT;
    public static Enchantment CHAINS;
    public static Enchantment COMMITTED;
    public static Enchantment CRITICAL_HIT;
    public static Enchantment ECHO;
    public static Enchantment ENIGMA_RESONATOR;
    public static Enchantment EXPLODING;
    public static Enchantment FREEZING;
    public static Enchantment GRAVITY;
    public static Enchantment GUARDING_STRIKE;
    public static Enchantment ILLAGERS_BANE;
    public static Enchantment JUNGLE_POISON;
    public static Enchantment LEECHING;
    public static Enchantment POISON_CLOUD;
    public static Enchantment PROSPECTOR;
    public static Enchantment RADIANCE;
    public static Enchantment RAMPAGING;
    public static Enchantment REFRESHMENT;
    public static Enchantment RUSHDOWN;
    public static Enchantment SHOCKWAVE;
    public static Enchantment SMITING;
    public static Enchantment SOUL_SIPHON;
    public static Enchantment STUNNING;
    public static Enchantment SWIRLING;
    public static Enchantment THUNDERING;
    public static Enchantment WEAKENING;

    public static Enchantment ACCELERATE;
    public static Enchantment BONUS_SHOT;
    public static Enchantment CHAIN_REACTION;
    public static Enchantment CHARGE;
    public static Enchantment FUSE_SHOT;
    public static Enchantment GROWING;
    public static Enchantment RADIANCE_SHOT;
    public static Enchantment REFRESHMENT_SHOT;
    public static Enchantment REPLENISH;
    public static Enchantment RICOCHET;
    public static Enchantment TEMPO_THEFT;

    public static void init() {
        ANIMA_CONDUIT = new AnimaConduitEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        CRITICAL_HIT = new CriticalHitEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        CHAINS = new ChainsEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        COMMITTED = new CommittedEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        ECHO = new EchoEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        ENIGMA_RESONATOR = new EnigmaResonatorEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        EXPLODING = new ExplodingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        FREEZING = new FreezingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        GRAVITY = new GravityEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        GUARDING_STRIKE = new GuardingStrikeEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        ILLAGERS_BANE = new IllagersBaneEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        JUNGLE_POISON = new JunglePoisonEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        LEECHING = new LeechingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        POISON_CLOUD = new PoisonCloudEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        PROSPECTOR = new ProspectorEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        RADIANCE = new RadianceEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        RAMPAGING = new RampagingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        REFRESHMENT = new RefreshmentEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        RUSHDOWN = new RushdownEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        SHOCKWAVE = new ShockwaveEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        SMITING = new SmitingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        SOUL_SIPHON = new SoulSiphonEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        STUNNING = new StunningEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        SWIRLING = new SwirlingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        THUNDERING = new ThunderingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        WEAKENING = new WeakeningEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});

        //ACCELERATE = new Accelerate(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                //new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        BONUS_SHOT = new BonusShotEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.CROSSBOW,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        CHAIN_REACTION = new ChainReactionEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.CROSSBOW,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        CHARGE = new ChargeEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        FUSE_SHOT = new FuseShotEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        GROWING = new GrowingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.CROSSBOW,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        RADIANCE_SHOT = new RadianceShotEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        REFRESHMENT_SHOT = new RefreshmentShotEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        REPLENISH = new ReplenishEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        RICOCHET = new RicochetEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        TEMPO_THEFT = new TempoTheftEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }
}