package chronosacaria.mcdw.mixin.reach.client;

import chronosacaria.mcdw.api.util.PlayerAttackHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.resource.SynchronousResourceReloader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/**
 * At this time, the code that is in this class is used without permission. However, the reason for it being uploaded
 * to GitHub on our project is to make sure that we can address issues with older versions of MCDW. Permission has been
 * requested, and if it is denied, this code shall be removed from the project.
 * <br/><br/>
 * Timefall Development want to make it very clear that NO copyright infringement was intended and we shall comply with
 * any and all requests from either <a href="https://github.com/JamiesWhiteShirt/">JamieWhiteShirt</a> or <a href="https://github.com/ChloeDawn/">ChloeDawn</a> to remove this code from our repository.
 * <br/><br/>
 * The following code is from Reach Entity Attributes and can be found here:
 * <a href = "https://github.com/JamiesWhiteShirt/reach-entity-attributes/blob/1.19/src/main/java/com/jamieswhiteshirt/reachentityattributes/mixin/client/GameRendererMixin.java">GameRendererMixin</a>
 */

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin implements SynchronousResourceReloader {
    @ModifyConstant(method = "updateTargetedEntity", require = 1, allow = 1, constant = @Constant(doubleValue = 6.0))
    private double getNewReachDistance(double reachDistance) {
        if (MinecraftClient.getInstance().player != null) {
            return PlayerAttackHelper.getReachDistance(MinecraftClient.getInstance().player, reachDistance);
        }
        return reachDistance;
    }

    @ModifyConstant(method = "updateTargetedEntity", require = 1, allow = 1, constant = @Constant(doubleValue = 3.0))
    private double getNewAttackRangeI(double attackRange) {
        if (MinecraftClient.getInstance().player != null) {
            return PlayerAttackHelper.getAttackRange(MinecraftClient.getInstance().player, attackRange);
        }
        return attackRange;
    }

    @ModifyConstant(method = "updateTargetedEntity", require = 1, allow = 1, constant = @Constant(doubleValue = 9.0))
    private double getNewAttackRangeII(double attackRange) {
        if (MinecraftClient.getInstance().player != null) {
            return PlayerAttackHelper.getSquaredAttackRange(MinecraftClient.getInstance().player, attackRange);
        }
        return attackRange;
    }
}
