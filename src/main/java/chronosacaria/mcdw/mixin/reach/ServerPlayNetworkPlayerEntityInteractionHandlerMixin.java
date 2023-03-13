package chronosacaria.mcdw.mixin.reach;

import chronosacaria.mcdw.api.util.PlayerAttackHelper;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.server.network.ServerPlayNetworkHandler$1")
public abstract class ServerPlayNetworkPlayerEntityInteractionHandlerMixin implements PlayerInteractEntityC2SPacket.Handler {

    /**
     * At this time, the code that is in this class is used without permission. However, the reason for it being uploaded
     * to GitHub on our project is to make sure that we can address issues with older versions of MCDW. Permission has been
     * requested, and if it is denied, this code shall be removed from the project.
     * <br/><br/>
     * Timefall Development want to make it very clear that NO copyright infringement was intended and we shall comply with
     * any and all requests from either <a href="https://github.com/JamiesWhiteShirt/">JamieWhiteShirt</a> or <a href="https://github.com/ChloeDawn/">ChloeDawn</a> to remove this code from our repository.
     * <br/><br/>
     * The following code is from Reach Entity Attributes and can be found here:
     * <a href = "https://github.com/JamiesWhiteShirt/reach-entity-attributes/blob/1.19/src/main/java/com/jamieswhiteshirt/reachentityattributes/mixin/PlayerEntityInteractionHandlerMixin.java#L15">PlayerEntityInteractionHandlerMixin</a>
     */

    @Shadow @Final
    ServerPlayNetworkHandler field_28963;

    @Shadow @Final
    Entity field_28962;

    @Inject(method = "attack", at = @At("HEAD"), require = 1, allow = 1, cancellable = true)
    private void confirmTargetIsWithinAttackRange(CallbackInfo ci) {
        if (!PlayerAttackHelper.isEntityWithinAttackRange(this.field_28963.player, this.field_28962)) {
            ci.cancel();
        }
    }
}
