package chronosacaria.mcdw.api.util;

import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Rarity;

public class RarityHelper {
    public static Rarity fromToolMaterial(ToolMaterial material){
        return
                material == ToolMaterials.NETHERITE ? Rarity.EPIC :
                material == ToolMaterials.DIAMOND ? Rarity.RARE :
                material == ToolMaterials.GOLD ? Rarity.UNCOMMON :
                material == ToolMaterials.IRON ? Rarity.UNCOMMON : Rarity.COMMON;
    }
}
