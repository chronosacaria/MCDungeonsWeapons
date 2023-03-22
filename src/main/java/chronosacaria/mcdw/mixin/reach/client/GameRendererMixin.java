package chronosacaria.mcdw.mixin.reach.client;

import chronosacaria.mcdw.api.util.PlayerAttackHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.resource.SynchronousResourceReloader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/**
 * Copyright 2019 Erlend Åmdal
 * <br/><br/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * <br/><br/>
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 * <br/><br/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
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
