package chronosacaria.mcdw.mixin.client;

import chronosacaria.mcdw.api.interfaces.IDualWielding;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.option.AttackIndicator;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public class InGameHudMixin extends DrawableHelper{

    @Shadow @Final @Mutable
    private MinecraftClient client;

    @Shadow
    private int scaledHeight;

    @Shadow
    private int scaledWidth;

    public InGameHudMixin(MinecraftClient client) {
        this.client = client;
    }

    @Inject(method = "renderCrosshair", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getAttackCooldownProgress(F)F", shift = At.Shift.AFTER))
    private void renderOffhandCrosshair(MatrixStack matrices, CallbackInfo ci) {

        if (this.client.options.getAttackIndicator().getValue() == AttackIndicator.CROSSHAIR) {
            float offhandAttackCooldownProgress = ((IDualWielding) this.client.player).getOffhandAttackCooldownProgress(0.0f);
            boolean bl = false;
            if (this.client.targetedEntity != null && this.client.targetedEntity instanceof LivingEntity && offhandAttackCooldownProgress >= 1.0f) {
                bl = ((IDualWielding) this.client.player).getOffhandAttackCooldownProgressPerTick() > 5.0f;
                bl &= this.client.targetedEntity.isAlive();
            }
            int height = this.scaledHeight / 2 - 7 + 16;
            int width = this.scaledWidth / 2 - 8;
            if (bl) {
                DrawableHelper.drawTexture(matrices, width, height + 8, 68, 94, 16, 16, 256, 256);
            } else if (offhandAttackCooldownProgress < 1.1f) {
                int l = (int) (offhandAttackCooldownProgress * 17.0f);
                DrawableHelper.drawTexture(matrices, width, height + 8, 36, 94, 16, 4, 256, 256);
                DrawableHelper.drawTexture(matrices, width, height + 8, 52, 94, l, 4, 256, 256);
            }
        }
    }
}
