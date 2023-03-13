package chronosacaria.mcdw.mixin.mcdw.client;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(HeldItemRenderer.class)
public interface HeldItemRendererInvoker {

    @Invoker("getUsingItemHandRenderType")
    static HeldItemRenderer.HandRenderType callGetUsingItemHandRenderType(ClientPlayerEntity player) {
        throw new UnsupportedOperationException();
    }
}
