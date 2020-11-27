package chronosacaria.mcdw.configs;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name="mcdw")
public class McdwConfig implements ConfigData {
    @Comment("Allow mixing of AOE Enchantments?")
    public boolean enableAOEMixing = false;
    @Comment("Can DamageBoost Enchantments combine with Leeching?")
    public boolean extraLeeching = false;
}
