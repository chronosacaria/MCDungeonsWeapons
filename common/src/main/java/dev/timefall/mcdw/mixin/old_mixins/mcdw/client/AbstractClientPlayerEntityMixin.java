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


import dev.timefall.mcdw.bases.McdwBowItem;
import dev.timefall.mcdw.bases.McdwLongbowItem;
import dev.timefall.mcdw.bases.McdwShortbowItem;
import dev.timefall.mcdw.enums.EnchantmentsID;
import dev.timefall.mcdw.registries.EnchantsRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public class AbstractClientPlayerEntityMixin {

    @Inject(method = "getFovMultiplier", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    public void mcdw$customBowsZoom(CallbackInfoReturnable<Float> cir, float f) {

        AbstractClientPlayerEntity abPlayer = MinecraftClient.getInstance().player;

        if (abPlayer == null)
            return;
        if (abPlayer.getActiveItem() == null)
            return;
        ItemStack itemStack = abPlayer.getActiveItem();
        if (abPlayer.isUsingItem()) {
            if (itemStack.getItem() instanceof McdwBowItem ||
                    itemStack.getItem() instanceof McdwShortbowItem ||
                    itemStack.getItem() instanceof McdwLongbowItem) {
                int i = abPlayer.getItemUseTime();
                int overchargeLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.OVERCHARGE), itemStack);
                if (overchargeLevel > 0) {
                    if (itemStack.getItem() instanceof McdwShortbowItem mcdwShortBowItem) {
                        int overcharge = (int) Math.min((i / mcdwShortBowItem.getDrawSpeed()) - 1, overchargeLevel);
                        i = overcharge == overchargeLevel ? i : (int) (i % mcdwShortBowItem.getDrawSpeed());
                    } else if (itemStack.getItem() instanceof McdwLongbowItem mcdwLongBowItem) {
                        int overcharge = (int) Math.min((i / mcdwLongBowItem.getDrawSpeed()) - 1, overchargeLevel);
                        i = overcharge == overchargeLevel ? i : (int) (i % mcdwLongBowItem.getDrawSpeed());
                    } else if (itemStack.getItem() instanceof McdwBowItem mcdwBowItem) {
                        int overcharge = (int) Math.min((i / mcdwBowItem.getDrawSpeed()) - 1, overchargeLevel);
                        i = overcharge == overchargeLevel ? i : (int) (i % mcdwBowItem.getDrawSpeed());
                    }
                }
                float g = (float)i / 20.0F;
                if (g > 1.0F) {
                    g = 1.0F;
                } else {
                    g *= g;
                }

                f *= 1.0F - g * 0.15F;

                cir.setReturnValue(MathHelper.lerp(MinecraftClient.getInstance().options.getFovEffectScale().getValue().floatValue(), 1.0F, f));
            }
        }
    }
}
