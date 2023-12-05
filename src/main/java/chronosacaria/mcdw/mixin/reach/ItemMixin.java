package chronosacaria.mcdw.mixin.reach;

import chronosacaria.mcdw.api.util.PlayerAttackHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

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
 * <a href = "https://github.com/JamiesWhiteShirt/reach-entity-attributes/blob/1.19/src/main/java/com/jamieswhiteshirt/reachentityattributes/mixin/ItemMixin.java">ItemMixin</a>
 */

@Mixin(Item.class)
public abstract class ItemMixin implements ItemConvertible {
    @Inject(method = "raycast", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;add(DDD)Lnet/minecraft/util/math/Vec3d;"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void mcdw$fixraycast(World world, PlayerEntity player, RaycastContext.FluidHandling fluidHandling, CallbackInfoReturnable<BlockHitResult> cir,
                                        float f, float g, Vec3d vec3d, float h, float i, float j, float k, float l, float m, float n, double d) {
        double modifiedReachDelta = PlayerAttackHelper.mcdw$getReachDistance(player, d) - 5;
        vec3d.add(l * modifiedReachDelta, k * modifiedReachDelta,n * modifiedReachDelta);
    }
}
