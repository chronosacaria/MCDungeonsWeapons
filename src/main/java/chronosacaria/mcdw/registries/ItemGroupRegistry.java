package chronosacaria.mcdw.registries;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enums.LongbowsID;
import chronosacaria.mcdw.enums.ShieldsID;
import chronosacaria.mcdw.enums.SwordsID;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public class ItemGroupRegistry {
    public static final RegistryKey<ItemGroup> MELEE = RegistryKey.of(RegistryKeys.ITEM_GROUP, Mcdw.ID("weapons/melee"));
    public static final RegistryKey<ItemGroup> RANGED = RegistryKey.of(RegistryKeys.ITEM_GROUP, Mcdw.ID("weapons/ranged"));
    public static final RegistryKey<ItemGroup> SHIELDS = RegistryKey.of(RegistryKeys.ITEM_GROUP, Mcdw.ID("weapons/shields"));
    public static final RegistryKey<ItemGroup> ENCHANTMENTS = RegistryKey.of(RegistryKeys.ITEM_GROUP, Mcdw.ID("enchantments"));

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, MELEE, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.mcdw.weapons/melee"))
                .icon(() -> new ItemStack(SwordsID.SWORD_HEARTSTEALER.getItem()))
                .build());
        Registry.register(Registries.ITEM_GROUP, RANGED, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.mcdw.weapons/ranged"))
                .icon(() -> new ItemStack(LongbowsID.BOW_LONGBOW.getItem()))
                .build());
        Registry.register(Registries.ITEM_GROUP, SHIELDS, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.mcdw.weapons/shields"))
                .icon(() -> new ItemStack(ShieldsID.SHIELD_ROYAL_GUARD.getItem()))
                .build());
        Registry.register(Registries.ITEM_GROUP, ENCHANTMENTS, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.mcdw.enchantments"))
                .icon(() -> new ItemStack(Items.ENCHANTED_BOOK))
                .build());
    }
}
