package chronosacaria.mcdw;

import chronosacaria.mcdw.configs.CompatibilityFlags;
import chronosacaria.mcdw.configs.McdwConfig;
import chronosacaria.mcdw.configs.McdwEnchantGiverConfig;
import chronosacaria.mcdw.enchants.EnchantsLists;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enchants.summons.registry.SummonedEntityRegistry;
import chronosacaria.mcdw.enums.LongbowsID;
import chronosacaria.mcdw.enums.ShieldsID;
import chronosacaria.mcdw.enums.SwordsID;
import chronosacaria.mcdw.items.ItemsInit;
import chronosacaria.mcdw.loottables.McdwNewLoottables;
import chronosacaria.mcdw.networking.OffhandAttackPacket;
import chronosacaria.mcdw.particles.ParticlesInit;
import chronosacaria.mcdw.registry.CompatRegistry;
import chronosacaria.mcdw.sounds.McdwSoundEvents;
import chronosacaria.mcdw.statuseffects.StatusEffectsRegistry;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class Mcdw implements ModInitializer {

    public static final String MOD_ID = "mcdw";

    public static Identifier ID(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static final ItemGroup WEAPONS = FabricItemGroup.builder(Mcdw.ID("weapons"))
            .displayName(Text.translatable("itemGroup.mcdw.weapons"))
            .icon(() -> new ItemStack(SwordsID.SWORD_HEARTSTEALER.getItem())).build();
    public static final ItemGroup RANGED = FabricItemGroup.builder(Mcdw.ID("weapons/bows"))
            .displayName(Text.translatable("itemGroup.mcdw.weapons/bows"))
            .icon(() -> new ItemStack(LongbowsID.BOW_LONGBOW.getItem())).build();
    public static final ItemGroup SHIELDS = FabricItemGroup.builder(Mcdw.ID("weapons/shields"))
            .displayName(Text.translatable("itemGroup.mcdw.shields"))
            .icon(() -> new ItemStack(ShieldsID.SHIELD_ROYAL_GUARD.getItem())).build();
    public static final ItemGroup ENCHANTMENTS = FabricItemGroup.builder(Mcdw.ID("enchantments"))
            .displayName(Text.translatable("itemGroup.mcdw.enchantments"))
            .icon(() -> new ItemStack(Items.ENCHANTED_BOOK))
            .entries((enabledFeatures, entries, operatorEnabled) -> {
                // For loop creates first 3 levels of enchanted books
                for (Enchantment enchantment : EnchantsLists.EnchantsList)
                    for (int i = 1; i <= 3; i++)
                        entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, i)));
            })
            .build();

    public static McdwConfig CONFIG;

    @Override
    public void onInitialize() {
        CompatibilityFlags.init();
        McdwConfig.init();
        CONFIG = AutoConfig.getConfigHolder(McdwConfig.class).getConfig();
        ParticlesInit.initializeOnServer();
        ItemsInit.init();
        OffhandAttackPacket.init();
        McdwNewLoottables.init();
        EnchantsRegistry.init();
        Registry.register(Registries.SOUND_EVENT, McdwSoundEvents.ECHO_SOUND, McdwSoundEvents.ECHO_SOUND_EVENT);
        McdwEnchantGiverConfig.appendEnchants();
        SummonedEntityRegistry.register();
        StatusEffectsRegistry.init();
        CompatRegistry.init();
    }
}