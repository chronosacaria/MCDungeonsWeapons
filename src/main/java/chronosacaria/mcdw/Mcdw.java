package chronosacaria.mcdw;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import chronosacaria.mcdw.weapons.*;


public class Mcdw implements ModInitializer {

    public static final String MOD_ID = "mcdw";

    public static final ItemGroup WEAPONS = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "weapons"),
            () -> new ItemStack(Swords.SWORD_HEARTSTEALER));



    @Override
    public void onInitialize() {

        Axes.init();
        Daggers.init();
        Hammers.init();
        Picks.init();
        Sickles.init();
        Spears.init();
        Swords.init();
    }
}
