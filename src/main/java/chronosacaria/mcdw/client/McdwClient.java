package chronosacaria.mcdw.client;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.*;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enchants.summons.registry.SummonedEntityRegistry;
import chronosacaria.mcdw.enchants.summons.render.SummonedBeeRenderer;
import chronosacaria.mcdw.enums.*;
import chronosacaria.mcdw.statuseffects.StatusEffectsRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;

@Environment(EnvType.CLIENT)
public class McdwClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(SummonedEntityRegistry.SUMMONED_BEE_ENTITY, SummonedBeeRenderer::new);

        Arrays.stream(BowsID.values()).forEach(bowsID -> registerBowPredicates(bowsID.getItem()));
        Arrays.stream(ShortBowsID.values()).forEach(shortBowsID -> registerShortBowPredicates(shortBowsID.getItem()));
        Arrays.stream(LongBowsID.values()).forEach(longBowsID -> registerLongBowPredicates(longBowsID.getItem()));
        Arrays.stream(CrossbowsID.values()).forEach(crossbowsID -> registerCrossbowPredicates(crossbowsID.getItem()));
        Arrays.stream(ShieldsID.values()).forEach(shieldsID -> registerShieldPredicates(shieldsID.getItem()));
    }

    public static void registerBowPredicates(McdwBow bow) {
        ModelPredicateProviderRegistry.register(bow, new Identifier("pull"), (itemStack, clientWorld,
                                                                              livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                int useTicks = itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft();
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.ACCELERATE)) {
                    int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ACCELERATE, itemStack);
                    if (accelerateLevel > 0) {
                        StatusEffectInstance accelerateInstance = livingEntity.getStatusEffect(StatusEffectsRegistry.ACCELERATE);
                        int consecutiveShots = accelerateInstance != null ? accelerateInstance.getAmplifier() + 1 : 0;

                        useTicks = (int) (useTicks * (1f + (MathHelper.clamp(consecutiveShots * (6.0f + 2.0f * accelerateLevel), 0f, 100f) / 100f)));
                    }
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.OVERCHARGE)) {
                    int overchargeLevel = EnchantmentHelper.getLevel(EnchantsRegistry.OVERCHARGE, itemStack);
                    if (overchargeLevel > 0) {
                        int overcharge = (int) Math.min((useTicks / bow.getDrawSpeed()) - 1, overchargeLevel);
                        useTicks = overcharge == overchargeLevel ? useTicks : (int) (useTicks % bow.getDrawSpeed());
                    }
                }
                return livingEntity.getActiveItem() != itemStack ? 0.0F :
                        (float) useTicks / bow.getDrawSpeed();
            }
        });

        ModelPredicateProviderRegistry.register(bow, new Identifier("pulling"), (itemStack, clientWorld,
                                                                                 livingEntity, seed) ->
                livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F);
    }

    public static void registerShortBowPredicates(McdwShortBow bow) {
        ModelPredicateProviderRegistry.register(bow, new Identifier("pull"), (itemStack, clientWorld,
                                                                              livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                int useTicks = itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft();
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.ACCELERATE)) {
                    int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ACCELERATE, itemStack);
                    if (accelerateLevel > 0) {
                        StatusEffectInstance accelerateInstance = livingEntity.getStatusEffect(StatusEffectsRegistry.ACCELERATE);
                        int consecutiveShots = accelerateInstance != null ? accelerateInstance.getAmplifier() + 1 : 0;

                        useTicks = (int) (useTicks * (1f + (MathHelper.clamp(consecutiveShots * (6.0f + 2.0f * accelerateLevel), 0f, 100f) / 100f)));
                    }
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.OVERCHARGE)) {
                    int overchargeLevel = EnchantmentHelper.getLevel(EnchantsRegistry.OVERCHARGE, itemStack);
                    if (overchargeLevel > 0) {
                        int overcharge = (int) Math.min((useTicks / bow.getDrawSpeed()) - 1, overchargeLevel);
                        useTicks = overcharge == overchargeLevel ? useTicks : (int) (useTicks % bow.getDrawSpeed());
                    }
                }
                return livingEntity.getActiveItem() != itemStack ? 0.0F :
                        (float) useTicks / bow.getDrawSpeed();
            }
        });

        ModelPredicateProviderRegistry.register(bow, new Identifier("pulling"), (itemStack, clientWorld,
                                                                                 livingEntity, seed) ->
                livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F);
    }

    public static void registerLongBowPredicates(McdwLongBow bow) {
        ModelPredicateProviderRegistry.register(bow, new Identifier("pull"), (itemStack, clientWorld,
                                                                              livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                int useTicks = itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft();
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.ACCELERATE)) {
                    int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ACCELERATE, itemStack);
                    if (accelerateLevel > 0) {
                        StatusEffectInstance accelerateInstance = livingEntity.getStatusEffect(StatusEffectsRegistry.ACCELERATE);
                        int consecutiveShots = accelerateInstance != null ? accelerateInstance.getAmplifier() + 1 : 0;

                        useTicks = (int) (useTicks * (1f + (MathHelper.clamp(consecutiveShots * (6.0f + 2.0f * accelerateLevel), 0f, 100f) / 100f)));
                    }
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.OVERCHARGE)) {
                    int overchargeLevel = EnchantmentHelper.getLevel(EnchantsRegistry.OVERCHARGE, itemStack);
                    if (overchargeLevel > 0) {
                        int overcharge = (int) Math.min((useTicks / bow.getDrawSpeed()) - 1, overchargeLevel);
                        useTicks = overcharge == overchargeLevel ? useTicks : (int) (useTicks % bow.getDrawSpeed());
                    }
                }
                return livingEntity.getActiveItem() != itemStack ? 0.0F :
                        (float) useTicks / bow.getDrawSpeed();
            }
        });

        ModelPredicateProviderRegistry.register(bow, new Identifier("pulling"), (itemStack, clientWorld,
                                                                                 livingEntity, seed) ->
                livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F);
    }

    public static void registerCrossbowPredicates(McdwCrossbow crossbow) {
        ModelPredicateProviderRegistry.register(crossbow, new Identifier("pull"), (itemStack, clientWorld,
                                                                                   livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                int useTicks = itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft();
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.ACCELERATE)) {
                    int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ACCELERATE, itemStack);
                    if (accelerateLevel > 0) {
                        StatusEffectInstance accelerateInstance = livingEntity.getStatusEffect(StatusEffectsRegistry.ACCELERATE);
                        int consecutiveShots = accelerateInstance != null ? accelerateInstance.getAmplifier() + 1 : 0;

                        useTicks = (int) (useTicks * (1f + (MathHelper.clamp(consecutiveShots * (6.0f + 2.0f * accelerateLevel), 0f, 100f) / 100f)));
                    }
                }
                return McdwCrossbow.isCharged(itemStack) ? 0.0F :
                        (float) useTicks / (float) McdwCrossbow.getPullTime(itemStack);
            }
        });

        ModelPredicateProviderRegistry.register(crossbow, new Identifier("pulling"), (itemStack, clientWorld,
                                                                                      livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack && !McdwCrossbow.isCharged(itemStack) ? 1.0F : 0.0F;
            }
        });

        ModelPredicateProviderRegistry.register(crossbow, new Identifier("charged"), (itemStack, clientWorld,
                                                                                      livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return McdwCrossbow.isCharged(itemStack) ? 1.0F : 0.0F;
            }
        });

        ModelPredicateProviderRegistry.register(crossbow, new Identifier("firework"), (itemStack, clientWorld,
                                                                                       livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return McdwCrossbow.isCharged(itemStack) && McdwCrossbow.hasProjectile(itemStack,
                        Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
            }
        });
    }

    public static void registerShieldPredicates(McdwShield shield) {
        ModelPredicateProviderRegistry.register(shield, new Identifier("blocking"), (itemStack, clientWorld,
                                                                                     livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem()
                == itemStack ? 1.0F : 0.0F);
    }
}
