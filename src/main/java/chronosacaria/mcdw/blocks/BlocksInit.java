package chronosacaria.mcdw.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.registry.Registry;
import static chronosacaria.mcdw.Mcdw.ID;

public class BlocksInit {
    public static final Block TEMP_COBWEB_BLOCK = new McdwTempCobwebBlock(FabricBlockSettings.of(Material.COBWEB).noCollision().strength(4.0F).nonOpaque().ticksRandomly());
    public static void init (){
        registerBlock("temp_cobweb_block", TEMP_COBWEB_BLOCK);
    }

    protected static void registerBlock(String id, Block block) {
        Registry.register(Registry.BLOCK, ID(id), block);

    }
}

