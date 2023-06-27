package chronosacaria.mcdw.mixin.mcdw.client;

import chronosacaria.mcdw.api.interfaces.IDualWielding;
import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import chronosacaria.mcdw.api.util.PlayerAttackHelper;
import chronosacaria.mcdw.configs.CompatibilityFlags;
import chronosacaria.mcdw.enums.DaggersID;
import chronosacaria.mcdw.enums.SicklesID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.option.AttackIndicator;
import net.minecraft.client.option.GameOptions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Shadow @Final @Mutable
    private MinecraftClient client;

    @Shadow
    private int scaledHeight;

    @Shadow
    private int scaledWidth;

    @Shadow @Final private static Identifier ICONS;

    public InGameHudMixin(MinecraftClient client) {
        this.client = client;
    }

    @Inject(method = "renderCrosshair", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getAttackCooldownProgress(F)F", shift = At.Shift.AFTER))
    private void renderOffhandCrosshair(DrawContext context, CallbackInfo ci) {
        if (CompatibilityFlags.noOffhandConflicts) {
            PlayerEntity player = client.player;
            if (player == null)
                return;
            if (player.getOffHandStack().getItem() instanceof IOffhandAttack && (player.getOffHandStack().isOf(player.getMainHandStack().getItem())
                    || (player.getMainHandStack().isOf(DaggersID.DAGGER_THE_BEGINNING.getItem()) && player.getOffHandStack().isOf(DaggersID.DAGGER_THE_END.getItem()))
                    || (player.getMainHandStack().isOf(DaggersID.DAGGER_THE_END.getItem()) && player.getOffHandStack().isOf(DaggersID.DAGGER_THE_BEGINNING.getItem()))
                    || (player.getMainHandStack().isOf(SicklesID.SICKLE_LAST_LAUGH_GOLD.getItem()) && player.getOffHandStack().isOf(SicklesID.SICKLE_LAST_LAUGH_SILVER.getItem()))
                    || (player.getMainHandStack().isOf(SicklesID.SICKLE_LAST_LAUGH_SILVER.getItem()) && player.getOffHandStack().isOf(SicklesID.SICKLE_LAST_LAUGH_GOLD.getItem())))) {

                GameOptions gameOptions = this.client.options;
                if (gameOptions.getPerspective().isFirstPerson()) {
                    if (this.client.interactionManager != null) {
                        if (this.client.interactionManager.getCurrentGameMode() != GameMode.SPECTATOR || mcdw$shouldRenderSpectatorCrosshair(this.client.crosshairTarget)) {
                            if (this.client.options.getAttackIndicator().getValue() == AttackIndicator.CROSSHAIR) {
                                PlayerAttackHelper.mcdw$switchModifiers(player, player.getMainHandStack(), player.getOffHandStack());
                                float offhandAttackCooldownProgress = ((IDualWielding) player).getOffhandAttackCooldownProgress(0.0f);
                                boolean bl = false;
                                if (this.client.targetedEntity != null && this.client.targetedEntity instanceof LivingEntity && offhandAttackCooldownProgress >= 1.0f) {
                                    bl = ((IDualWielding) player).getOffhandAttackCooldownProgressPerTick() > 5.0f;
                                    bl &= this.client.targetedEntity.isAlive();
                                }
                                PlayerAttackHelper.mcdw$switchModifiers(player, player.getOffHandStack(), player.getMainHandStack());
                                int height = this.scaledHeight / 2 - 7 + 16;
                                int width = this.scaledWidth / 2 - 8;
                                if (bl) {
                                    context.drawTexture(ICONS, width, height + 8, 68, 94, 16, 16, 256, 256);
                                } else if (offhandAttackCooldownProgress < 1.0f) {
                                    int l = (int) (offhandAttackCooldownProgress * 17.0f);
                                    context.drawTexture(ICONS, width, height + 8, 36, 94, 16, 4, 256, 256);
                                    context.drawTexture(ICONS, width, height + 8, 52, 94, l, 4, 256, 256);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean mcdw$shouldRenderSpectatorCrosshair(HitResult hitResult) {
        if (hitResult == null) {
            return false;
        } else if (hitResult.getType() == HitResult.Type.ENTITY) {
            return ((EntityHitResult)hitResult).getEntity() instanceof NamedScreenHandlerFactory;
        } else if (hitResult.getType() == HitResult.Type.BLOCK && this.client.world != null) {
            BlockPos blockPos = ((BlockHitResult)hitResult).getBlockPos();
            World world = this.client.world;
            return world.getBlockState(blockPos).createScreenHandlerFactory(world, blockPos) != null;
        } else {
            return false;
        }
    }
}
