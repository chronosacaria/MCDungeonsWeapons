package chronosacaria.mcdw.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.impl.base.event.EventFactoryImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@FunctionalInterface
public interface OnLivingEntityDeathEvent {

    Event<OnLivingEntityDeathEvent> EVENT = EventFactoryImpl.createArrayBacked(OnLivingEntityDeathEvent.class,
            (listeners)-> (damageSource_1, float_1, e, ci)->
            {
                for (OnLivingEntityDeathEvent callback : listeners)
                {
                    callback.onDeath(damageSource_1, float_1, e, ci);
                }
            });
    void onDeath(DamageSource damageSource, float damage, Entity entity, CallbackInfoReturnable<Boolean> ci);
}
