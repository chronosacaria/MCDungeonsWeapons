package chronosacaria.mcdw.mixin;

import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(AxeItem.class)
public interface InsulatedAxeItemAccessor {
    @Accessor
    static Map<Block, Block> getSTRIPPED_BLOCKS() {
        throw new UnsupportedOperationException();
    }
}
