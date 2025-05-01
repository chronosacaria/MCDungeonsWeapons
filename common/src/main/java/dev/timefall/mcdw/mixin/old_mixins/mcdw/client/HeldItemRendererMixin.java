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
@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {

    @Shadow protected abstract void applyEquipOffset(MatrixStack matrices, Arm arm, float equipProgress);

    @Shadow protected abstract void applySwingOffset(MatrixStack matrices, Arm arm, float swingProgress);

    @SuppressWarnings("SameReturnValue")
    @Shadow
    private static boolean isChargedCrossbow(ItemStack stack) {
        return false;
    }

    @Shadow public abstract void renderItem(LivingEntity entity, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light);

    @Inject(method = "getHandRenderType(Lnet/minecraft/client/network/ClientPlayerEntity;)Lnet/minecraft/client/render/item/HeldItemRenderer$HandRenderType;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"),
            cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private static void mcdw$getHandRenderType(ClientPlayerEntity player, CallbackInfoReturnable<HeldItemRenderer.HandRenderType> cir, ItemStack itemStack, ItemStack itemStack2) {
        Item item1 = itemStack.getItem();
        Item item2 = itemStack2.getItem();
        boolean bl = item1 instanceof McdwBowItem || item2 instanceof McdwBowItem;
        boolean bl2 = item1 instanceof McdwCrossbowItem || item2 instanceof McdwCrossbowItem;
        if (!bl && !bl2) {
            // normal behavior
        } else if (player.isUsingItem()) {
            cir.setReturnValue(HeldItemRendererInvoker.callGetUsingItemHandRenderType(player));
        } else {
            cir.setReturnValue(isChargedCrossbow(itemStack) ? HeldItemRenderer.HandRenderType.RENDER_MAIN_HAND_ONLY : HeldItemRenderer.HandRenderType.RENDER_BOTH_HANDS);
        }
    }

    @Inject(method = "getUsingItemHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 1), cancellable = true)
    private static void mcdw$getUsingItemHandRenderType(ClientPlayerEntity player, CallbackInfoReturnable<HeldItemRenderer.HandRenderType> cir) {
        ItemStack itemStack = player.getActiveItem();
        Hand hand = player.getActiveHand();
        if (itemStack.getItem() instanceof McdwCrossbowItem || itemStack.getItem() instanceof McdwBowItem)
            cir.setReturnValue(HeldItemRenderer.HandRenderType.shouldOnlyRender(hand));
    }

    @Inject(method = "isChargedCrossbow", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"), cancellable = true)
    private static void mcdw$isChargedCrossbow(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof McdwCrossbowItem)
            cir.setReturnValue(true);
    }

    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 1), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void mcdw$renderFirstPersonItem(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item,
                                            float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci,
                                            boolean bl, Arm arm) {
        if (item.getItem() instanceof McdwCrossbowItem) {
            boolean bl2 = CrossbowItem.isCharged(item);
            boolean bl3 = arm == Arm.RIGHT;
            int i = bl3 ? 1 : -1;
            if (player.isUsingItem() && player.getItemUseTimeLeft() > 0 && player.getActiveHand() == hand) {
                this.applyEquipOffset(matrices, arm, equipProgress);
                matrices.translate(((float)i * -0.4785682F), -0.0943870022892952D, 0.05731530860066414D);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-11.935F));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float)i * 65.3F));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((float)i * -9.785F));
                if (MinecraftClient.getInstance().player != null) {
                    float f = (float) item.getMaxUseTime() - ((float) MinecraftClient.getInstance().player.getItemUseTimeLeft() - tickDelta + 1.0F);
                    float g = f / (float) CrossbowItem.getPullTime(item);
                    if (g > 1.0F) {
                        g = 1.0F;
                    }

                    if (g > 0.1F) {
                        float h = MathHelper.sin((f - 0.1F) * 1.3F);
                        float j = g - 0.1F;
                        float k = h * j;
                        matrices.translate((k * 0.0F), (k * 0.004F), (k * 0.0F));
                    }

                    matrices.translate((g * 0.0F), (g * 0.0F), (g * 0.04F));
                    matrices.scale(1.0F, 1.0F, 1.0F + g * 0.2F);
                    matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees((float) i * 45.0F));
                }
            } else {
                float f = -0.4F * MathHelper.sin(MathHelper.sqrt(swingProgress) * 3.1415927F);
                float g = 0.2F * MathHelper.sin(MathHelper.sqrt(swingProgress) * 6.2831855F);
                float h = -0.2F * MathHelper.sin(swingProgress * 3.1415927F);
                matrices.translate(((float)i * f), g, h);
                this.applyEquipOffset(matrices, arm, equipProgress);
                this.applySwingOffset(matrices, arm, swingProgress);
                if (bl2 && swingProgress < 0.001F && bl) {
                    matrices.translate(((float)i * -0.641864F), 0.0D, 0.0D);
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float)i * 10.0F));
                }
            }

            this.renderItem(player, item, bl3 ? ModelTransformationMode.FIRST_PERSON_RIGHT_HAND : ModelTransformationMode.FIRST_PERSON_LEFT_HAND, !bl3, matrices, vertexConsumers, light);
            matrices.pop();
            ci.cancel();
        }
    }
}

 */
