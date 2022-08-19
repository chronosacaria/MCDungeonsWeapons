package chronosacaria.mcdw.registry;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.damagesource.OffHandDamageSource;
import chronosacaria.mcdw.effects.EnchantmentEffects;
import chronosacaria.mcdw.enums.EnchantmentsID;
import com.blamejared.clumps.api.events.ClumpsEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;

public class CompatRegistry {
    public static void init() {
        if (FabricLoader.getInstance().isModLoaded("clumps")) {
            ClumpsEvents.VALUE_EVENT.register(event -> {
                int amount = event.getValue();
                PlayerEntity playerEntity = event.getPlayer();
                boolean isOffHandAttack = playerEntity.getRecentDamageSource() instanceof OffHandDamageSource;


                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.SOUL_DEVOURER))
                    amount = EnchantmentEffects.soulDevourerExperience(playerEntity, amount);

                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.ANIMA_CONDUIT))
                    amount = EnchantmentEffects.animaConduitExperience(playerEntity, amount, isOffHandAttack);

                event.setValue(amount);
                return null;
            });
        }
    }
}
