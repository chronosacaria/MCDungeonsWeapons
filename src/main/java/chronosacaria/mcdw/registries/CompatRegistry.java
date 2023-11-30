package chronosacaria.mcdw.registries;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.damagesources.OffHandDamageSource;
import chronosacaria.mcdw.effects.EnchantmentEffects;
import chronosacaria.mcdw.enums.EnchantmentsID;
import com.blamejared.clumps.api.events.ClumpsEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;

public class CompatRegistry {
    public static void register() {
        if (FabricLoader.getInstance().isModLoaded("clumps")) {
            ClumpsEvents.VALUE_EVENT.register(event -> {
                int amount = event.getValue();
                PlayerEntity playerEntity = event.getPlayer();
                boolean isOffHandAttack = playerEntity.getRecentDamageSource() instanceof OffHandDamageSource;

                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.SOUL_DEVOURER).mcdw$getIsEnabled())
                    amount = EnchantmentEffects.soulDevourerExperience(playerEntity, amount);

                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.ANIMA_CONDUIT).mcdw$getIsEnabled())
                    amount = EnchantmentEffects.animaConduitExperience(playerEntity, amount, isOffHandAttack);

                event.setValue(amount);
                return null;
            });
        }
    }
}
