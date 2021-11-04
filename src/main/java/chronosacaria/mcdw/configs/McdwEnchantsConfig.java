package chronosacaria.mcdw.configs;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class McdwEnchantsConfig {

    private static final HashMap<String, Boolean> SETTINGS = new HashMap<>();

    public static boolean getValue(String key) {
        if (!SETTINGS.containsKey(key)) {
            System.out.println(key);
        }
        return SETTINGS.getOrDefault(key, null);
    }

    public static void init() {
        SETTINGS.put("enable_op_mixing", false);
        SETTINGS.put("extra_leeching", false);
        SETTINGS.put("extra_xp_healing", false);
        SETTINGS.put("ambush", true);
        SETTINGS.put("anima", true);
        SETTINGS.put("bonus_shot", true);
        SETTINGS.put("mixin_bee", true);
        SETTINGS.put("chain_reaction", true);
        SETTINGS.put("chains", true);
        SETTINGS.put("charge", true);
        SETTINGS.put("cobweb_shot", true);
        SETTINGS.put("committed", true);
        SETTINGS.put("critical_hit", true);
        SETTINGS.put("dipping_poison", true);
        SETTINGS.put("echo", true);
        SETTINGS.put("enigma_resonator", true);
        SETTINGS.put("exploding", true);
        SETTINGS.put("custom_fire_aspect", true);
        SETTINGS.put("freezing", true);
        SETTINGS.put("fuse_shot", true);
        SETTINGS.put("gravity", true);
        SETTINGS.put("growing", true);
        SETTINGS.put("guarding_strike", true);
        SETTINGS.put("illagers_bane", true);
        SETTINGS.put("jungle_poison", true);
        SETTINGS.put("leeching", true);
        SETTINGS.put("levitation_shot", true);
        SETTINGS.put("custom_looting", true);
        SETTINGS.put("multi_shot", true);
        SETTINGS.put("phantoms_mark", true);
        SETTINGS.put("poison_cloud", true);
        SETTINGS.put("prospector", true);
        SETTINGS.put("radiance", true);
        SETTINGS.put("radiance_shot", true);
        SETTINGS.put("rampaging", true);
        SETTINGS.put("replenish", true);
        SETTINGS.put("refreshment", true);
        SETTINGS.put("refreshment_shot", true);
        SETTINGS.put("ricochet", true);
        SETTINGS.put("rushdown", true);
        SETTINGS.put("shockwave", true);
        SETTINGS.put("smiting", true);
        SETTINGS.put("soul_siphon", true);
        SETTINGS.put("stunning", true);
        SETTINGS.put("swirling", true);
        SETTINGS.put("tempo_theft", true);
        SETTINGS.put("thundering", true);
        SETTINGS.put("void_shot", true);
        SETTINGS.put("void_strike", true);
        SETTINGS.put("weakening", true);
        SETTINGS.put("aoe_dont_affect_players", true);
        SETTINGS.put("enable_innate_enchants", true);
    }

    public static void loadConfig() {
        JsonObject json = McdwBaseConfig.getJsonObject(McdwBaseConfig.readFile(new File("config/minecraft_dungeon_weapons/enchants_config.json5")));
        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            SETTINGS.put(entry.getKey(), entry.getValue().getAsBoolean());
        }
    }

    public static void generateConfigs(boolean overwrite) {
        StringBuilder config = new StringBuilder("{\n");
        int i = 0;
        for (String key : SETTINGS.keySet()) {
            config.append("  \"").append(key).append("\": ").append(SETTINGS.get(key));
            ++i;
            if (i < SETTINGS.size()) {
                config.append(",");
            }
            config.append("\n");
        }
        config.append("}");
        McdwBaseConfig.createFile("config/minecraft_dungeon_weapons/enchants_config.json5", config.toString(), overwrite);
    }
}