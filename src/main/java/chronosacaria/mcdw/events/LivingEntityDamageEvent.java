package chronosacaria.mcdw.events;


import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.impl.base.event.EventFactoryImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


/**
 * LivingEntityDamageCallback
 * by biom4st3r
 */


@FunctionalInterface
public interface LivingEntityDamageEvent {
    Event<LivingEntityDamageEvent> EVENT = EventFactoryImpl.createArrayBacked(LivingEntityDamageEvent.class,
            (listeners) -> (damageSource_1, float_1, e, ci) ->
            {
                for (LivingEntityDamageEvent callback : listeners)
                {
                    callback.onDamage(damageSource_1, float_1, e, ci);
                }
            });
    void onDamage(DamageSource damageSource, float damage, Entity entity, CallbackInfoReturnable<Boolean> ci);
}
