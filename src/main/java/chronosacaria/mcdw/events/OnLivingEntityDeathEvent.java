package chronosacaria.mcdw.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.impl.base.event.EventFactoryImpl;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;


@FunctionalInterface
public interface OnLivingEntityDeathEvent {

    Event<OnLivingEntityDeathEvent> DEATH_EVENT = EventFactoryImpl.createArrayBacked(OnLivingEntityDeathEvent.class,
            listeners -> (player, cause) ->
            {
                for (OnLivingEntityDeathEvent callback : listeners)
                {
                    callback.onDeath(player, cause);
                }
            });

          void onDeath(LivingEntity player, DamageSource cause);
    }

