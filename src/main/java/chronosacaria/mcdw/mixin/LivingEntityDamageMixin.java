package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.events.OnLivingEntityDamageEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LivingEntity.class)
public abstract class LivingEntityDamageMixin {
    @Inject(
            at = @At("HEAD"),
            method = "damage",
            cancellable = true,
            locals = LocalCapture.NO_CAPTURE)
    public void onTargetDamaged(DamageSource source, float damage, CallbackInfoReturnable<Boolean> ci){
        OnLivingEntityDamageEvent.EVENT.invoker().onDamage(source, damage, (Entity)(Object)this, ci);

    }
}
