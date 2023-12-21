package chronosacaria.mcdw.client;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.*;
import chronosacaria.mcdw.enchants.summons.render.SummonedBeeRenderer;
import chronosacaria.mcdw.enums.*;
import chronosacaria.mcdw.registries.EnchantsRegistry;
import chronosacaria.mcdw.registries.ParticlesRegistry;
import chronosacaria.mcdw.registries.StatusEffectsRegistry;
import chronosacaria.mcdw.registries.SummonedEntityRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;

@Environment(EnvType.CLIENT)
public class McdwClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(SummonedEntityRegistry.SUMMONED_BEE_ENTITY, SummonedBeeRenderer::new);
        ParticlesRegistry.registerOnClient();

        Arrays.stream(BowsID.values()).forEach(bowsID -> registerRangedWeaponPredicates(bowsID.getItem()));
        Arrays.stream(ShortbowsID.values()).forEach(shortBowsID -> registerRangedWeaponPredicates(shortBowsID.getItem()));
        Arrays.stream(LongbowsID.values()).forEach(longBowsID -> registerRangedWeaponPredicates(longBowsID.getItem()));
        Arrays.stream(CrossbowsID.values()).forEach(crossbowsID -> registerRangedWeaponPredicates(crossbowsID.getItem()));
        Arrays.stream(ShieldsID.values()).forEach(shieldsID -> registerShieldPredicates(shieldsID.getItem()));
    }

    public static void registerRangedWeaponPredicates(Item item) {
        ModelPredicateProviderRegistry.register(item, new Identifier("pull"), (itemStack, clientWorld, livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else if (item instanceof McdwBow bow) {
                return drawSpeedModification(itemStack, livingEntity, bow.getDrawSpeed());
            } else if (item instanceof McdwShortbow shortbow) {
                return drawSpeedModification(itemStack, livingEntity, shortbow.getDrawSpeed());
            } else if (item instanceof McdwLongbow longbow) {
                return drawSpeedModification(itemStack, livingEntity, longbow.getDrawSpeed());
            } else if (item instanceof McdwCrossbow crossbow) {
                return drawSpeedModification(itemStack, livingEntity, crossbow.getDrawSpeed());
            }
            return 0.0F;
        });

        if (item instanceof BowItem) {
            ModelPredicateProviderRegistry.register(item, new Identifier("pulling"), (itemStack, clientWorld, livingEntity, seed) ->
                    livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F);
        }
        if (item instanceof CrossbowItem) {
            ModelPredicateProviderRegistry.register(item, new Identifier("pulling"), (itemStack, clientWorld, livingEntity, seed) -> {
                if (livingEntity == null) {
                    return 0.0F;
                } else {
                    return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack && !McdwCrossbow.isCharged(itemStack) ? 1.0F : 0.0F;
                }
            });

            ModelPredicateProviderRegistry.register(item, new Identifier("charged"), (itemStack, clientWorld, livingEntity, seed) -> {
                if (livingEntity == null) {
                    return 0.0F;
                } else {
                    return McdwCrossbow.isCharged(itemStack) ? 1.0F : 0.0F;
                }
            });

            ModelPredicateProviderRegistry.register(item, new Identifier("firework"), (itemStack, clientWorld, livingEntity, seed) -> {
                if (livingEntity == null) {
                    return 0.0F;
                } else {
                    return McdwCrossbow.isCharged(itemStack) && McdwCrossbow.hasProjectile(itemStack,
                            Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
                }
            });
        }
    }
    public static void registerShieldPredicates(McdwShield shield) {
        ModelPredicateProviderRegistry.register(shield, new Identifier("blocking"),
                (itemStack, clientWorld, livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem() &&
                        livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F
        );
    }
    private static float drawSpeedModification(ItemStack itemStack, LivingEntity livingEntity, float drawSpeed) {
        int useTicks = itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft();
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.ACCELERATE).mcdw$getIsEnabled()) {
            int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ACCELERATE, itemStack);
            if (accelerateLevel > 0) {
                StatusEffectInstance accelerateInstance = livingEntity.getStatusEffect(StatusEffectsRegistry.ACCELERATE);
                int consecutiveShots = accelerateInstance != null ? accelerateInstance.getAmplifier() + 1 : 0;

                useTicks = (int) (useTicks * (1f + (MathHelper.clamp(consecutiveShots * (6.0f + 2.0f * accelerateLevel), 0f, 100f) / 100f)));
            }
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.OVERCHARGE).mcdw$getIsEnabled()) {
            int overchargeLevel = EnchantmentHelper.getLevel(EnchantsRegistry.OVERCHARGE, itemStack);
            if (overchargeLevel > 0) {
                int overcharge = (int) Math.min((useTicks / drawSpeed) - 1, overchargeLevel);
                useTicks = overcharge == overchargeLevel ? useTicks : (int) (useTicks % drawSpeed);
            }
        }
        if (itemStack.getItem() instanceof BowItem)
            return livingEntity.getActiveItem() != itemStack ? 0.0F : (float) useTicks / drawSpeed;
        if (itemStack.getItem() instanceof McdwCrossbow)
            return McdwCrossbow.isCharged(itemStack) ? 0.0F : (float) useTicks / (float) McdwCrossbow.getPullTime(itemStack);
        return drawSpeed;
    }
}
