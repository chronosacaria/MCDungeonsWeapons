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
        SETTINGS.put("mixin_anima", true);
        SETTINGS.put("mixin_bee", true);
        SETTINGS.put("mixin_chain_reaction", true);
        SETTINGS.put("mixin_chains", true);
        SETTINGS.put("mixin_charge", true);
        SETTINGS.put("mixin_committed", true);
        SETTINGS.put("mixin_critical", true);
        SETTINGS.put("mixin_echo", true);
        SETTINGS.put("mixin_enigma", true);
        SETTINGS.put("mixin_exploding", true);
        SETTINGS.put("mixin_custom_fire_aspect", true);
        SETTINGS.put("mixin_freezing", true);
        SETTINGS.put("mixin_fuse_shot", true);
        SETTINGS.put("mixin_gravity", true);
        SETTINGS.put("mixin_growing", true);
        SETTINGS.put("mixin_jungle_poison", true);
        SETTINGS.put("mixin_leeching", true);
        SETTINGS.put("mixin_custom_looting", true);
        SETTINGS.put("mixin_poison", true);
        SETTINGS.put("mixin_prospector", true);
        SETTINGS.put("mixin_radiance", true);
        SETTINGS.put("mixin_radiance_shot", true);
        SETTINGS.put("mixin_rampaging", true);
        SETTINGS.put("mixin_replenish", true);
        SETTINGS.put("mixin_ricochet", true);
        SETTINGS.put("mixin_shockwave", true);
        SETTINGS.put("mixin_smiting", true);
        SETTINGS.put("mixin_siphon", true);
        SETTINGS.put("mixin_stunning", true);
        SETTINGS.put("mixin_swirling", true);
        SETTINGS.put("mixin_tempo_theft", true);
        SETTINGS.put("mixin_thundering", true);
        SETTINGS.put("mixin_weakening", true);
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