package chronosacaria.mcdw.mixin.mcdw.client;

import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.bases.McdwLongbow;
import chronosacaria.mcdw.bases.McdwShortbow;
import chronosacaria.mcdw.registries.EnchantsRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
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
            if (itemStack.getItem() instanceof McdwBow ||
                    itemStack.getItem() instanceof McdwShortbow ||
                    itemStack.getItem() instanceof McdwLongbow) {
                int i = abPlayer.getItemUseTime();
                int overchargeLevel = EnchantmentHelper.getLevel(EnchantsRegistry.OVERCHARGE, itemStack);
                if (overchargeLevel > 0) {
                    if (itemStack.getItem() instanceof McdwShortbow mcdwShortBow) {
                        int overcharge = (int) Math.min((i / mcdwShortBow.getDrawSpeed()) - 1, overchargeLevel);
                        i = overcharge == overchargeLevel ? i : (int) (i % mcdwShortBow.getDrawSpeed());
                    } else if (itemStack.getItem() instanceof McdwLongbow mcdwLongBow) {
                        int overcharge = (int) Math.min((i / mcdwLongBow.getDrawSpeed()) - 1, overchargeLevel);
                        i = overcharge == overchargeLevel ? i : (int) (i % mcdwLongBow.getDrawSpeed());
                    } else if (itemStack.getItem() instanceof McdwBow mcdwBow) {
                        int overcharge = (int) Math.min((i / mcdwBow.getDrawSpeed()) - 1, overchargeLevel);
                        i = overcharge == overchargeLevel ? i : (int) (i % mcdwBow.getDrawSpeed());
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
