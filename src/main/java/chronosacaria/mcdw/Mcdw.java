package chronosacaria.mcdw;

import chronosacaria.mcdw.configs.CompatibilityFlags;
import chronosacaria.mcdw.configs.McdwConfig;
import chronosacaria.mcdw.enums.LongbowsID;
import chronosacaria.mcdw.enums.ShieldsID;
import chronosacaria.mcdw.enums.SwordsID;
import chronosacaria.mcdw.networking.OffhandAttackPacket;
import chronosacaria.mcdw.registries.*;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mcdw implements ModInitializer {

    public static final String MOD_ID = "mcdw";
    public static final Logger LOGGER = LogManager.getLogger();

    public static Identifier ID(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static final ItemGroup WEAPONS = FabricItemGroup.builder(Mcdw.ID("weapons"))
            .displayName(Text.translatable("itemGroup.mcdw.weapons/melee"))
            .icon(() -> new ItemStack(SwordsID.SWORD_HEARTSTEALER.getItem())).build();
    public static final ItemGroup RANGED = FabricItemGroup.builder(Mcdw.ID("weapons/bows"))
            .displayName(Text.translatable("itemGroup.mcdw.weapons/bows"))
            .icon(() -> new ItemStack(LongbowsID.BOW_LONGBOW.getItem())).build();
    public static final ItemGroup SHIELDS = FabricItemGroup.builder(Mcdw.ID("weapons/shields"))
            .displayName(Text.translatable("itemGroup.mcdw.shields"))
            .icon(() -> new ItemStack(ShieldsID.SHIELD_ROYAL_GUARD.getItem())).build();
    public static final ItemGroup ENCHANTMENTS = FabricItemGroup.builder(Mcdw.ID("enchantments"))
            .displayName(Text.translatable("itemGroup.mcdw.enchantments"))
            .icon(() -> new ItemStack(Items.ENCHANTED_BOOK)).build();

    public static McdwConfig CONFIG;
    public static boolean SHOW_ENCHANTS = true;

    @Override
    public void onInitialize() {
        String exampleConfig = "// Enchant Giver example file\n// Adding enchants to tools is done in the following way:\n// \"mod_name:item_name\": {\n//   \"mod_name:enchant_name\": level\n// }\n// Example:\n{\n  \"minecraft:iron_pickaxe\": {\n    \"minecraft:fortune\": 3,\n    \"minecraft:efficiency\": 2\n  },\n  \"minecraft:stick\": {\n    \"minecraft:sharpness\": 10\n  }\n}\n// You can create multiple files for different configs.\n// It is recommended to name the files \"your_mod_name.json\" so as to not cause any incompatibilities with other mods using EnchantGiver.";
        InnateEnchantmentsConfig.createFile("config/mcdw/example.yaml", exampleConfig, true);
        InnateEnchantmentsConfig.createInnateEnchantmentConfig();
        //JsonObject json = InnateEnchantmentsConfig.getJsonObject(InnateEnchantmentsConfig.readFile(new File("config/mcdw/mcdw_innate_enchantments_config.json")));
        //SHOW_ENCHANTS = json.get("show_enchants_in_tooltip").getAsBoolean();
        McdwInnateEnchantmentsConfig.loadInnateEnchantments();

        EntityAttributesRegistry.registerAttributes();
        CompatibilityFlags.init();
        McdwConfig.init();
        CONFIG = AutoConfig.getConfigHolder(McdwConfig.class).getConfig();

        ParticlesRegistry.initParticlesOnServer();
        ItemsRegistry.init();
        OffhandAttackPacket.init();
        LootTablesRegistry.init();
        EnchantsRegistry.init();
        SoundEventsRegistry.init();
        McdwEnchantGiverConfig.appendEnchants();
        SummonedEntityRegistry.register();
        StatusEffectsRegistry.init();
        //CompatRegistry.init();
    }
}