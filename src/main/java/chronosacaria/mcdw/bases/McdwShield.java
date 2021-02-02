package chronosacaria.mcdw.bases;

import me.crimsondawn45.fabricshieldlib.lib.object.FabricShield;
import net.minecraft.item.Item;
import net.minecraft.item.ShieldItem;

public class McdwShield extends FabricShield{

    public McdwShield(Settings settings, int cooldownTicks, int durability, Item repairItem) {
        super(settings, cooldownTicks, durability, repairItem);
    }
}
