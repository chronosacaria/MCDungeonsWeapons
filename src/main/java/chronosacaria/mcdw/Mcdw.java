package chronosacaria.mcdw;

import chronosacaria.mcdw.configs.CompatibilityFlags;
import chronosacaria.mcdw.configs.McdwConfig;
import chronosacaria.mcdw.configs.McdwEnchantGiverConfig;
import chronosacaria.mcdw.enchants.EnchantsLists;
import chronosacaria.mcdw.enums.LongbowsID;
import chronosacaria.mcdw.enums.ShieldsID;
import chronosacaria.mcdw.enums.SwordsID;
import chronosacaria.mcdw.networking.OffhandAttackPacket;
import chronosacaria.mcdw.registries.*;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Mcdw implements ModInitializer {

    public static final String MOD_ID = "mcdw";

    public static Identifier ID(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static final ItemGroup WEAPONS = FabricItemGroupBuilder.build(
            Mcdw.ID("weapons"),
            () -> new ItemStack(ItemsRegistry.SWORD_ITEMS.get(SwordsID.SWORD_HEARTSTEALER)));
    public static final ItemGroup RANGED = FabricItemGroupBuilder.build(
            Mcdw.ID("weapons/bows"),
            () -> new ItemStack(ItemsRegistry.LONGBOW_ITEMS.get(LongbowsID.BOW_LONGBOW)));
    public static final ItemGroup SHIELDS = FabricItemGroupBuilder.build(
            Mcdw.ID("weapons/shields"),
            () -> new ItemStack(ItemsRegistry.SHIELD_ITEMS.get(ShieldsID.SHIELD_ROYAL_GUARD)));
    public static final ItemGroup ENCHANTMENTS = FabricItemGroupBuilder.create(
                    Mcdw.ID("enchants"))
            .icon(() -> new ItemStack(Items.ENCHANTED_BOOK))
            .appendItems(itemStacks -> {
                // For loop creates first 3 levels of enchanted books
                for (Enchantment enchantment : EnchantsLists.EnchantsList)
                    for (int i = 1; i <= 3; i++)
                        itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, i)));
            })
            .build();

    public static McdwConfig CONFIG;

    @Override
    public void onInitialize() {
        CompatibilityFlags.init();
        McdwConfig.init();
        CONFIG = AutoConfig.getConfigHolder(McdwConfig.class).getConfig();
        ParticlesRegistry.initParticlesOnServer();
        ItemsRegistry.init();
        OffhandAttackPacket.init();
        LootTablesRegistry.init();
        EnchantsRegistry.init();
        Registry.register(Registry.SOUND_EVENT, SoundEventsRegistry.ECHO_SOUND, SoundEventsRegistry.ECHO_SOUND_EVENT);
        McdwEnchantGiverConfig.appendEnchants();
        SummonedEntityRegistry.register();
        StatusEffectsRegistry.init();
        CompatRegistry.init();
    }
}