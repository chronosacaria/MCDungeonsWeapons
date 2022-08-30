package chronosacaria.mcdw.mixin.client;

import chronosacaria.mcdw.bases.McdwCrossbow;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @Inject(
            method = "getArmPose(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;",
            at = @At(value = "TAIL"),
            cancellable = true
    )
    private static void mcdw$getArmPose(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
        var isMcdwCrossbowCharged = McdwCrossbow.isCharged(player.getStackInHand(hand));

        if (!player.handSwinging
                && (player.getMainHandStack().getItem() instanceof McdwCrossbow || player.getOffHandStack().getItem() instanceof McdwCrossbow)
                && isMcdwCrossbowCharged) {

            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
            cir.cancel();
        }
        if (!player.handSwinging
                && (player.getMainHandStack().getItem() instanceof McdwCrossbow && player.getOffHandStack().getItem() instanceof McdwCrossbow)
                || !isMcdwCrossbowCharged) {
            cir.setReturnValue(BipedEntityModel.ArmPose.ITEM);
            cir.cancel();
        }
        // Close, but not quite what I'm looking for
        //if (!player.handSwinging
        //        && ((player.getMainHandStack().getItem() instanceof McdwCrossbow && player.getOffHandStack().getItem() instanceof McdwCrossbow))) {
        //    cir.setReturnValue(BipedEntityModel.ArmPose.SPYGLASS);
        //    cir.cancel();
        //}
    }
}
