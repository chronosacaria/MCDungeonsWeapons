/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.mixin.old_mixins.mcdw.client;

/*
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
            if (player.getOffHandStack().getItem() instanceof IOffhandAttack && PlayerAttackHelper.mixAndMatchWeapons(player)) {

                GameOptions gameOptions = this.client.options;
                if (gameOptions.getPerspective().isFirstPerson()) {
                    if (this.client.interactionManager != null) {
                        if (this.client.interactionManager.getCurrentGameMode() != GameMode.SPECTATOR || mcdw$shouldRenderSpectatorCrosshair(this.client.crosshairTarget)) {
                            if (this.client.options.getAttackIndicator().getValue() == AttackIndicator.CROSSHAIR) {
                                //PlayerAttackHelper.mcdw$switchModifiers(player, player.getMainHandStack(), player.getOffHandStack());
                                float offhandAttackCooldownProgress = ((IDualWielding) player).mcdw$getOffhandAttackCooldownProgress(0.0f);
                                boolean bl = false;
                                if (this.client.targetedEntity != null && this.client.targetedEntity instanceof LivingEntity && offhandAttackCooldownProgress >= 1.0f) {
                                    bl = ((IDualWielding) player).mcdw$getOffhandAttackCooldownProgressPerTick() > 5.0f;
                                    bl &= this.client.targetedEntity.isAlive();
                                }
                                //PlayerAttackHelper.mcdw$switchModifiers(player, player.getOffHandStack(), player.getMainHandStack());
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

    @Unique
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

 */
