package chronosacaria.mcdw.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(HeldItemRenderer.class)
public interface HandRenderTypeInvoker {
    @SuppressWarnings("unused")
    @Invoker("getUsingItemHandRenderType")
    static HeldItemRenderer.HandRenderType callHandRenderType(ClientPlayerEntity player) {
        throw new UnsupportedOperationException();
    }
}
