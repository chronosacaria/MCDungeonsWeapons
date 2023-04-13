package chronosacaria.mcdw.data;

import chronosacaria.mcdw.Mcdw;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigItemEnabledCondition {

    public static void init() {
        ResourceConditions.register(new Identifier(Mcdw.MOD_ID, "config_enabled"), jsonObject -> {
            JsonArray jsonArray = JsonHelper.getArray(jsonObject, "values");
            List<Boolean> booleanArrayList = new ArrayList<>();

            for (JsonElement jsonElement : jsonArray) {
                if (jsonElement.isJsonPrimitive()) {
                    try {
                        String jsonElementString = jsonElement.getAsString();
                        List<String> configClasses = Arrays.stream(jsonElementString.split("\\.")).toList();
                        if (configClasses.size() > 1) {
                            booleanArrayList.add(Mcdw.CONFIG.getClass().getField(configClasses.get(0)).get(Mcdw.CONFIG).getClass().getField(configClasses.get(1)).getBoolean(Mcdw.CONFIG.getClass().getField(configClasses.get(0)).get(Mcdw.CONFIG)));
                        } else
                            booleanArrayList.add(Mcdw.CONFIG.getClass().getField(jsonElementString).getBoolean(Mcdw.CONFIG));
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            return booleanArrayList.stream().allMatch(aBoolean -> aBoolean);
        });

        ResourceConditions.register(new Identifier(Mcdw.MOD_ID, "item_enabled"), jsonObject -> {
            JsonArray jsonArray = JsonHelper.getArray(jsonObject, "values");

            for (JsonElement jsonElement : jsonArray) {
                if (jsonElement.isJsonPrimitive()) {
                    return Registry.ITEM.get(new Identifier(jsonElement.getAsString())) != Items.AIR;
                }
            }
            return true;
        });
    }
}