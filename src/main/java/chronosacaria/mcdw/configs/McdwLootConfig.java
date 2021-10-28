package chronosacaria.mcdw.configs;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class McdwLootConfig {

    private static final HashMap<String, Boolean> LOOT = new HashMap<>();

    public static boolean getValue(String key) {
        if (!LOOT.containsKey(key)) {
            System.out.println(key);
        }
        return LOOT.getOrDefault(key, true);
    }

    public static void init() {
        LOOT.put("pillager_towers", true);
        LOOT.put("nether_fortresses", true);
        LOOT.put("piglin_bastions", true);
        LOOT.put("under_water", true);
        LOOT.put("end_cities", true);
    }

    public static void loadConfig() {
        JsonObject json = McdwBaseConfig.getJsonObject(McdwBaseConfig.readFile(new File("config/minecraft_dungeon_weapons/loot_config.json5")));
        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            LOOT.put(entry.getKey(), entry.getValue().getAsBoolean());
        }
    }

    public static void generateConfigs(boolean overwrite) {
        StringBuilder config = new StringBuilder("{\n");
        int i = 0;
        for (String key : LOOT.keySet()) {
            config.append("  \"").append(key).append("\": ").append(LOOT.get(key));
            ++i;
            if (i < LOOT.size()) {
                config.append(",");
            }
            config.append("\n");
        }
        config.append("}");
        McdwBaseConfig.createFile("config/minecraft_dungeon_weapons/loot_config.json5", config.toString(), overwrite);
    }
}