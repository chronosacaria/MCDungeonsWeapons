package chronosacaria.mcdw;

import chronosacaria.mcdw.enchants.EnchantsRegistry;
//import chronosacaria.mcdw.enchants.summons.entity.SummonedBeeEntity;
import chronosacaria.mcdw.enchants.summons.registry.SummonedEntityRegistry;
import chronosacaria.mcdw.items.ItemRegistry;
import chronosacaria.mcdw.loottables.McdwLoottables;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;

import net.fabricmc.fabric.api.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import chronosacaria.mcdw.weapons.*;
import net.minecraft.util.registry.Registry;


public class Mcdw implements ModInitializer {

    public static final String MOD_ID = "mcdw";

    public static final ItemGroup WEAPONS = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "weapons"),
            () -> new ItemStack(Claymores.SWORD_HEARTSTEALER));
    public static final ItemGroup RANGED = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "weapons/bows"),
            () -> new ItemStack(Bows.BOW_LONGBOW));
    public static final ItemGroup ENCHANTMENTS = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "enchants"))
            .icon(() -> new ItemStack(Items.ENCHANTED_BOOK))
            .appendItems(itemStacks -> {
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.POISON_CLOUD,1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.POISON_CLOUD,2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.POISON_CLOUD,3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.THUNDERING,1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.GRAVITY,1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.GRAVITY,2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.GRAVITY,3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.EXPLODING,1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.EXPLODING,2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.EXPLODING,3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.FREEZING,1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.FREEZING,2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.FREEZING,3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.LEECHING,1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.LEECHING,2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.LEECHING,3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.STUNNING,1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.STUNNING,2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantsRegistry.STUNNING,3)));
            })
            .build();


    /*public static final EntityType<SummonedBeeEntity> SUMMONED_BEE_ENTITY =
            Registry.register(
                    Registry.ENTITY_TYPE,
                    new Identifier("summonedbee", "summoned_bee"),
                    FabricEntityTypeBuilder
                            .create(SpawnGroup.CREATURE, SummonedBeeEntity::new)
                            .size(EntityDimensions.fixed(1,2)).build()
            );*/

    //private static final Enchantment POISON_CLOUD = new PoisonCloud(Enchantment.Rarity.COMMON,
            //EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});

    @Override
    public void onInitialize() {

        // Melee Weapons
        Claymores.init();
        Curves.init();
        Daggers.init();
        Glaives.init();
        Katanas.init();
        Sickles.init();
        SoulDaggers.init();
        Scythes.init();
        Spears.init();
        Swords.init();
        Whips.init();
        Staves.init();
        Rapiers.init();
        Axes.init();
        DoubleAxes.init();
        Hammers.init();

        // Tools
        Picks.init();

        // Ranged
        Bows.init();

        // Enchants
        EnchantsRegistry.init();

        // Items
        ItemRegistry.init();

        // Loot
        McdwLoottables.init();
    }


    /*public static void registerServerboundPackets(){
        ServerSidePacketRegistry.INSTANCE.register(C2SRotateHeldItem.ID, C2SRotateHeldItem::onPacket);
    }*/
}
