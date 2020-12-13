package chronosacaria.mcdw.configs;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name="mcdw")
public class McdwConfig extends PartitioningSerializer.GlobalData{

    static {
        AutoConfig.register(McdwConfig.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        AutoConfig.register(McdwConfig.McdwStatsConfig.class,JanksonConfigSerializer::new);
        AutoConfig.register(McdwConfig.McdwEnchantsConfig.class,JanksonConfigSerializer::new);
    }
    public static final McdwConfig config = AutoConfig.getConfigHolder(McdwConfig.class).getConfig();

    @ConfigEntry.Category("enchants")
    @ConfigEntry.Gui.TransitiveObject
    public McdwEnchantsConfig enchantsConfig = new McdwEnchantsConfig();

    @ConfigEntry.Category("stats")
    @ConfigEntry.Gui.TransitiveObject
    public McdwStatsConfig statsConfig = new McdwStatsConfig();


    @Config(name = "mcdw_enchantsConfig")
    public static class McdwEnchantsConfig implements ConfigData {
        @Comment("Allow mixing of AOE Enchantments?")
        public boolean enableAOEMixing = false;
        @Comment("Can DamageBoost Enchantments combine with Leeching?")
        public boolean extraLeeching = false;
        @Comment("Can Soul Siphon and Anima Conduit be combined?")
        public boolean extraXpHealing = false;
        @Comment("Enable Enchantment - Anima Conduit?")
        public boolean mixinAnima = true;
        @Comment("Enable Enchantment - Busy Bee?")
        public boolean mixinBee = true;
        @Comment("Enable Enchantment - Chains?")
        public boolean mixinChains = true;
        @Comment("Enable Enchantment - Committed?")
        public boolean mixinCommitted = true;
        @Comment("Enable Enchantment - Critical Hit?")
        public boolean mixinCritical = true;
        @Comment("Enable Enchantment - Echo?")
        public boolean mixinEcho = true;
        @Comment("Enable Enchantment - Enigma Resonator?")
        public boolean mixinEnigma = true;
        @Comment("Enable Enchantment - Exploding?")
        public boolean mixinExploding = true;
        @Comment("Enable Enchantment - Custom Fire Aspect?")
        public boolean mixinCustomFireAspect = true;
        @Comment("Enable Enchantment - Freezing?")
        public boolean mixinFreezing = true;
        @Comment("Enable Enchantment - Gravity?")
        public boolean mixinGravity = true;
        @Comment("Enable Enchantment - Jungle's Poison?")
        public boolean mixinJPoison = true;
        @Comment("Enable Enchantment - Leeching?")
        public boolean mixinLeeching = true;
        @Comment("Enable Enchantment - Custom Looting?")
        public boolean mixinCustomLooting = true;
        @Comment("Enable Enchantment - Poison Cloud?")
        public boolean mixinPoison = true;
        @Comment("Enable Enchantment - Radiance?")
        public boolean mixinRadiance = true;
        @Comment("Enable Enchantment - Rampaging?")
        public boolean mixinRampaging = true;
        @Comment("Enable Enchantment - Ricochet?")
        public boolean mixinRicochet = true;
        @Comment("Enable Enchantment - Shockwave?")
        public boolean mixinShockwave = true;
        @Comment("Enable Enchantment - Smiting?")
        public boolean mixinSmiting = true;
        @Comment("Enable Enchantment - Soul Siphon?")
        public boolean mixinSiphon = true;
        @Comment("Enable Enchantment - Stunning?")
        public boolean mixinStunning = true;
        @Comment("Enable Enchantment - Swirling?")
        public boolean mixinSwirling = true;
        @Comment("Enable Enchantment - Tempo Theft?")
        public boolean mixinTempoTheft = true;
        @Comment("Enable Enchantment - Thundering?")
        public boolean mixinThundering = true;
        @Comment("Enable Enchantment - Weakening?")
        public boolean mixinWeakening = true;
    }

    @Config(name = "mcdw_stats")
    public static class McdwStatsConfig implements ConfigData {
        @Comment("Axe Damage")
        public float Axe_Damage = 6.0F;

        public float getAxeAttack() {return Axe_Damage;}

    }
}
