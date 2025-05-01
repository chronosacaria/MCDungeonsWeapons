/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.effects;

import dev.timefall.mcdw.api.interfaces.IMcdwEnchantedArrow;
import dev.timefall.mcdw.api.util.*;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import dev.timefall.mcdw.enchantment.goals.WildRageAttackGoal;
import dev.timefall.mcdw.enums.EnchantmentsID;
import dev.timefall.mcdw.mixin.old_mixins.mcdw.CreeperEntityAccessor;
import dev.timefall.mcdw.mixin.old_mixins.mcdw.MobEntityAccessor;
import dev.timefall.mcdw.registries.EnchantsRegistry;
import dev.timefall.mcdw.registries.SoundEventsRegistry;
import dev.timefall.mcdw.registries.StatusEffectsRegistry;
import dev.timefall.mcdw.registries.items.McdwBowItemRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.UUID;

import static dev.timefall.mcdw.configs.stats.McdwEnchantmentStatsConfig.CONFIG;


public class EnchantmentEffects {

    public static int mcdw$getEnchantmentLevel(Enchantment enchantment, LivingEntity enchantedEntity, boolean isOffHandStack) {
        if (FabricLoader.getInstance().isModLoaded("bettercombat")) {
            // Better Combat can figure out if the hit was done by offhand
            return EnchantmentHelper.getEquipmentLevel(enchantment, enchantedEntity);
        } else {
            // We know if the hit was done by offhand
            return EnchantmentHelper.getLevel(enchantment, isOffHandStack ? enchantedEntity.getOffHandStack() : enchantedEntity.getMainHandStack());
        }
    }

    /* ExperienceOrbEntityMixin */
    //mcdw$ModifyExperience
    public static int soulDevourerExperience(PlayerEntity playerEntity, int amount) {
        int mainHandLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.SOUL_DEVOURER), playerEntity.getMainHandStack());
        int offHandLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.SOUL_DEVOURER), playerEntity.getOffHandStack());

        int soulDevourerLevel = mainHandLevel + offHandLevel;

        if (soulDevourerLevel > 0)
            return Math.round((float) amount * (1 + ((float) soulDevourerLevel / CONFIG.getMcdwEnchantmentStats().getSoulDevourerEnchantment().effectOffset)));
        return amount;
    }

    public static int animaConduitExperience(PlayerEntity playerEntity, int amount, boolean isOffHandStack) {
        int animaLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.ANIMA_CONDUIT), playerEntity, isOffHandStack);

        if (animaLevel > 0) {
            float missingHealth = playerEntity.getMaxHealth() - playerEntity.getHealth();
            if (missingHealth > 0) {
                float i = Math.min(AbilityHelper.getAnimaRepairAmount(amount, animaLevel), missingHealth);
                playerEntity.heal(i *
                        (CONFIG.getMcdwEnchantmentStats().getAnimaConduitEnchantment().effectOffset)/100f);
                amount -= (int) (i * 5);
                return Math.max(amount, 0);
            }
        }
        return amount;
    }

    /* LivingEntityMixin */
    //mcdw$damageModifiers
    public static float huntersPromiseDamage(PlayerEntity owner, ServerWorld serverWorld) {
        if (McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowHuntersPromise().isEnabled) {
            if (owner.getMainHandStack().isOf(McdwBowItemRegistry.BOW_HUNTERS_PROMISE)) {
                UUID petOwnerUUID = owner.getUuid();

                if (petOwnerUUID != null) {
                    if (serverWorld.getEntity(petOwnerUUID) instanceof LivingEntity) {
                        return 0.5F;
                    }
                }
            }
        }
        return 0f;
    }

    //mcdw$onDeath
    public static void applyProspector(LivingEntity prospectingEntity, LivingEntity victim, boolean isOffHandStack) {
        int prospectorLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.PROSPECTOR), prospectingEntity, isOffHandStack);

        if (prospectorLevel > 0) {

            if (CleanlinessHelper.percentToOccur( CONFIG.getMcdwEnchantmentStats().getProspectorEnchantment().procWeight * prospectorLevel)) {
                if (victim instanceof Monster){
                    CleanlinessHelper.mcdw$dropItem(victim, Items.EMERALD);
                }
            }
        }
    }

    public static void applyRushdown(LivingEntity rushingEntity, boolean isOffHandStack) {
        int rushdownLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.RUSHDOWN), rushingEntity, isOffHandStack);

        if (rushdownLevel > 0) {

            if (CleanlinessHelper.percentToOccur(  CONFIG.getMcdwEnchantmentStats().getRushdownEnchantment().procWeight)) {
                StatusEffectInstance rushdown = new StatusEffectInstance(StatusEffects.SPEED, 100 * rushdownLevel, 2,
                        false, false);
                rushingEntity.addStatusEffect(rushdown);
            }
        }
    }

    public static void applySoulSiphon(PlayerEntity siphoningEntity, boolean isOffHandStack) {
        int soulLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.SOUL_SIPHON), siphoningEntity, isOffHandStack);

        if (soulLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getSoulSiphonEnchantment().procWeight)) {
                siphoningEntity.addExperience(3 * soulLevel);
            }
        }
    }

    public static void applyShadowShotShadowForm(LivingEntity shadowShotEntity, PersistentProjectileEntity ppe, int duration){
        int shadowShotLevel = ((IMcdwEnchantedArrow)ppe).mcdw$getShadowShotLevel();
        if (shadowShotLevel > 0) {
            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getShadowShotEnchantment().procWeight)) {
                shadowShotEntity.addStatusEffect(new StatusEffectInstance(StatusEffectsRegistry.SHADOW_FORM, duration, 0, false, true, true));
            }
        }
    }

    /* LivingEntityPlayerEntityMixin */
    //mcdw$damageModifiers
    public static float ambushDamage(LivingEntity ambushingEntity, LivingEntity ambushee, boolean isOffHandStack) {
        int ambushLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.AMBUSH), ambushingEntity, isOffHandStack);
        if (ambushLevel > 0) {

            if (ambushingEntity.isInvisible() && ambushingEntity.isSneaking()) {

                CleanlinessHelper.playCenteredSound(ambushee, SoundEvents.BLOCK_POINTED_DRIPSTONE_LAND, 0.5F, 1.0F);
                return 0.15f * ambushLevel;
            }
        }
        return 0f;
    }

    public static float criticalHitDamage(LivingEntity crittingEntity, LivingEntity target, boolean isOffHandStack) {
        int criticalHitLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.CRITICAL_HIT), crittingEntity, isOffHandStack);

        if (criticalHitLevel > 0) {

            if (CleanlinessHelper.percentToOccur(10 + (CONFIG.getMcdwEnchantmentStats().getCriticalHitEnchantment().procWeight * criticalHitLevel))) {
                if (!AbilityHelper.entityCanCrit(crittingEntity)) {

                    CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, 0.5F, 1.0F);
                    return 0.5f;
                }
            }
        }
        return 0f;
    }

    public static float voidStrikeDamage(LivingEntity voidEntity, LivingEntity target, boolean isOffHandStack) {
        int voidlevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.VOID_STRIKE), voidEntity, isOffHandStack);

        if (voidlevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getVoidStrikeEnchantment().procWeight + (5 * voidlevel))) {
                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.5F, 1.0F);
                return (2f * voidlevel) - 1f; // -1f accounts for change to storedAmount calc
            }
        }
        return 0f;
    }

    public static float painCycleDamage(LivingEntity painEntity, boolean isOffHandStack) {
        int painCycleLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.PAIN_CYCLE), painEntity, isOffHandStack);

        if (painCycleLevel > 0) {
            StatusEffectInstance painCycleInstance = painEntity.getStatusEffect(StatusEffectsRegistry.PAIN_CYCLE);
            int i = painCycleInstance != null ? painCycleInstance.getAmplifier() + 1 : 0;
            if (i < 5) {
                StatusEffectInstance painCycleUpdate = new StatusEffectInstance(StatusEffectsRegistry.PAIN_CYCLE, 120000, i, false, false, true);
                painEntity.removeStatusEffect(StatusEffectsRegistry.PAIN_CYCLE);
                painEntity.addStatusEffect(painCycleUpdate);
                painEntity.damage(painEntity.getWorld().getDamageSources().magic(), 1);
            } else {
                painEntity.removeStatusEffect(StatusEffectsRegistry.PAIN_CYCLE);
                return painCycleLevel + 1;
            }
        }
        return 0;
    }

    public static float enigmaResonatorDamage(PlayerEntity resonatingEntity, LivingEntity target, boolean isOffHandStack) {
        int resonatorLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.ENIGMA_RESONATOR), resonatingEntity, isOffHandStack);
        return calcEnigmaResonatorDamage(resonatingEntity, target, resonatorLevel);
    }

    public static float enigmaShotDamage(LivingEntity resonatingEntity, LivingEntity target, PersistentProjectileEntity ppe) {
        if (!(resonatingEntity instanceof PlayerEntity player))
            return 0f;
        IMcdwEnchantedArrow enchantedArrow = (IMcdwEnchantedArrow) ppe;
        int resonatorLevel = enchantedArrow.mcdw$getEnigmaResonatorLevel();
        return calcEnigmaResonatorDamage(player, target, resonatorLevel);
    }

    private static float calcEnigmaResonatorDamage(PlayerEntity resonatingEntity, LivingEntity target, int resonatorLevel) {
        if (resonatorLevel > 0) {
            int numSouls = resonatingEntity.experienceLevel;
            if (numSouls > 0) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.PARTICLE_SOUL_ESCAPE, 0.5F, 1.0F);
                float extraDamageMultiplier =
                        (float) (Math.log(numSouls * resonatorLevel + 20)) /
                                CONFIG.getMcdwEnchantmentStats().getEnigmaResonatorEnchantment().effectOffset;

                return Math.max(extraDamageMultiplier - 1, 0f);
            }
        }
        return 0f;
    }



    public static float growingDamage(LivingEntity growingEntity, LivingEntity target, PersistentProjectileEntity ppe) {
        int growingLevel = ((IMcdwEnchantedArrow)ppe).mcdw$getGrowingLevel();
        if (growingLevel > 0) {

            CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.5F, 1.0F);
            float damageModifier = 0.03F * growingEntity.distanceTo(target) * growingLevel;
            return MathHelper.clamp(damageModifier, 0f, growingLevel);
        }
        return 0f;
    }

    public static float voidShotDamage(LivingEntity target, PersistentProjectileEntity ppe) {
        int voidlevel = ((IMcdwEnchantedArrow)ppe).mcdw$getVoidShotLevel();
        if (voidlevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getVoidShotEnchantment().procWeight + (5 * voidlevel))) {
                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.5F, 1.0F);
                return voidlevel;
            }
        }
        return 0f;
    }

    public static float committedDamage(LivingEntity committedEntity, LivingEntity target, boolean isOffHandStack) {
        int committedLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.COMMITTED), committedEntity, isOffHandStack);

        if (committedLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getCommittedEnchantment().procWeight)) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.5F, 1.0F);

                float getTargetRemainingHealth = MathHelper.clamp(target.getHealth() / target.getMaxHealth(), 0, 1);
                float attributeDamage = (float) committedEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                float committedMultiplier = 0.2F * committedLevel;

                float getExtraDamage = attributeDamage * (1 - getTargetRemainingHealth) * committedMultiplier;
                return Math.max(getExtraDamage, 0f);
            }
        }
        return 0f;
    }

    public static float dynamoDamage (LivingEntity dynamoEntity, boolean isOffHandStack) {
        int dynamoLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.DYNAMO), dynamoEntity, isOffHandStack);
        return calcDynamoDamage(dynamoEntity, dynamoLevel);
    }

    public static float dynamoShotDamage (LivingEntity dynamoEntity, PersistentProjectileEntity ppe) {
        int dynamoLevel = ((IMcdwEnchantedArrow)ppe).mcdw$getDynamoLevel();
        return calcDynamoDamage(dynamoEntity, dynamoLevel);
    }

    private static float calcDynamoDamage(LivingEntity dynamoEntity, int dynamoLevel) {
        if (dynamoLevel > 0 && dynamoEntity.hasStatusEffect(StatusEffectsRegistry.DYNAMO)) {
            StatusEffectInstance dynamoInstance = dynamoEntity.getStatusEffect(StatusEffectsRegistry.DYNAMO);
            if (dynamoInstance != null) {
                int dynamoAmplifier = dynamoInstance.getAmplifier() + 1;
                float dynamoLevelModifier = (dynamoLevel - 1) * 0.25f + 1;
                float getDynamoDamage = (float) (dynamoLevelModifier * (dynamoAmplifier * 0.1));
                dynamoEntity.removeStatusEffect(StatusEffectsRegistry.DYNAMO);

                return Math.max(getDynamoDamage, 0f);
            }
        }
        return 0f;
    }

    public static float shadowFormDamage (LivingEntity shadowShotEntity) {
        if (shadowShotEntity.hasStatusEffect(StatusEffectsRegistry.SHADOW_FORM)) {
            shadowShotEntity.removeStatusEffect(StatusEffectsRegistry.SHADOW_FORM);
            return 7f;
        }
        return 0f;
    }

    public static float shadowFormShotDamage (LivingEntity shadowShotEntity, PersistentProjectileEntity ppe) {
        boolean shadowBarbBoolean = ((IMcdwEnchantedArrow)ppe).mcdw$getShadowBarbBoolean();
        if (shadowShotEntity.hasStatusEffect(StatusEffectsRegistry.SHADOW_FORM)) {
            if (!shadowBarbBoolean) {
                shadowShotEntity.removeStatusEffect(StatusEffectsRegistry.SHADOW_FORM);
                //TODO TRY TO FIGURE OUT HOW TO REMOVE INVISIBILITY
            }
            return 7f;
        }
        return 0f;
    }

    public static float overchargeDamage(PersistentProjectileEntity ppe) {
        int overchargeAmount = ((IMcdwEnchantedArrow)ppe).mcdw$getOvercharge();
        return Math.max(overchargeAmount, 0);
    }

    //mcdw$onApplyDamageHead
    public static void applyFreezing(LivingEntity freezerEntity, LivingEntity target, boolean isOffHandStack) {
        int freezingLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.FREEZING), freezerEntity, isOffHandStack);
        if (freezingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getFreezingEnchantment().procWeight + (10 * freezingLevel))) {
                AbilityHelper.causeFreezing(target, 100);
            }
        }
    }

    public static void applyPoisoning(LivingEntity poisoningEntity, LivingEntity target, boolean isOffHandStack) {
        int poisoningLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.JUNGLE_POISON), poisoningEntity, isOffHandStack);
        if (poisoningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getJunglePoisonEnchantment().procWeight)) {
                StatusEffectInstance poison = new StatusEffectInstance(StatusEffects.POISON, 60, poisoningLevel - 1);
                target.addStatusEffect(poison);
            }
        }
    }

    public static void applyPoisonCloud(LivingEntity poisoningEntity, LivingEntity target, boolean isOffHandStack) {
        int poisoningLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.POISON_CLOUD), poisoningEntity, isOffHandStack);
        if (poisoningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getPoisonCloudEnchantment().procWeight)) {
                AOECloudHelper.spawnAreaEffectCloudEntityWithAttributes(
                        poisoningEntity,
                        target,
                        5.0f,
                        10,
                        60,
                        StatusEffects.POISON,
                        60,
                        poisoningLevel - 1,
                        true,
                        true,
                        true,
                        false
                );
            }
        }
    }

    public static void applyRadianceCloud(LivingEntity radiantEntity, boolean isOffHandStack) {
        int radianceLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.RADIANCE), radiantEntity, isOffHandStack);

        if (radianceLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getRadianceEnchantment().procWeight)) {
                AOECloudHelper.spawnAreaEffectCloudEntityWithAttributes(
                        radiantEntity,
                        radiantEntity,
                        5.0f,
                        10,
                        60,
                        StatusEffects.REGENERATION,
                        100,
                        radianceLevel - 1,
                        true,
                        false,
                        false,
                        true
                );
            }
        }
    }

    public static void applyShockwave(LivingEntity shockwaveEntity, LivingEntity target, float amount, boolean isOffHandStack) {
        int shockwaveLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.SHOCKWAVE), shockwaveEntity, isOffHandStack);

        if (shockwaveLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getShockwaveEnchantment().procWeight + (15 * shockwaveLevel))) {
                causeShockwaveAttack(shockwaveEntity, target,
                        3.0f, amount);

                CleanlinessHelper.playCenteredSound(target,
                        SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.WEATHER,
                        0.5f, 1.0f);
            }
        }
    }

    public static void causeShockwaveAttack(LivingEntity user, LivingEntity target, float distance, float amount) {
        AOEHelper.getEntitiesByConfig(user, distance).stream()
                .filter(nearbyEntity -> nearbyEntity != target)
                .forEach(nearbyEntity -> nearbyEntity.damage(
                        nearbyEntity.getWorld().getDamageSources().generic(),
                        amount * 0.25f)
                );
    }

    public static void causeSmitingAttack(LivingEntity user, LivingEntity target, float distance, float amount) {
        AOEHelper.getEntitiesByConfig(user, distance).stream()
                .filter(nearbyEntity -> nearbyEntity != target && nearbyEntity.isUndead())
                .forEach(nearbyEntity -> nearbyEntity.damage(nearbyEntity.getWorld().getDamageSources().magic(), amount * 1.25F));
    }

    public static void applyStunning(LivingEntity stunningEntity, LivingEntity target, boolean isOffHandStack) {
        int stunningLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.STUNNING), stunningEntity, isOffHandStack);
        if (stunningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getStunningEnchantment().procWeight + (15 * stunningLevel))) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 10));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 60, 1));
            }
        }
    }

    public static void applyThundering(LivingEntity thunderingEntity, float amount, boolean isOffHandStack) {
        int thunderingLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.THUNDERING), thunderingEntity, isOffHandStack);

        if (thunderingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getThunderingEnchantment().procWeight)) {
                electrocuteNearbyEnemies(thunderingEntity,
                        5 * thunderingLevel, amount,
                        Integer.MAX_VALUE);
            }
        }
    }

    public static void createVisualLightningBoltOnEntity(Entity target) {
        TrackedData<Boolean> charged = CreeperEntityAccessor.getCHARGED();
        World world = target.getEntityWorld();
        LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);

        if (lightningEntity != null) {
            lightningEntity.refreshPositionAfterTeleport(target.getX(), target.getY(), target.getZ());
            lightningEntity.setCosmetic(true);
            if (target instanceof CreeperEntity creeperEntity) {
                creeperEntity.getDataTracker().set(charged, true);
            }
            world.spawnEntity(lightningEntity);
        }
    }

    public static void electrocute(LivingEntity victim, float damageAmount) {
        createVisualLightningBoltOnEntity(victim);
        victim.damage(victim.getWorld().getDamageSources().lightningBolt(), damageAmount);
    }

    public static void electrocuteNearbyEnemies(LivingEntity user, float distance, float damageAmount, int limit) {
        boolean foundTarget = false;
        for (LivingEntity nearbyEntity : AOEHelper.getEntitiesByConfig(user, distance)) {
            electrocute(nearbyEntity, damageAmount);
            foundTarget = true;
            limit--;
            if (limit <= 0) break;
        }
        if (foundTarget) {
            CleanlinessHelper.playCenteredSound(user, SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.WEATHER, 1f, 1f);
            CleanlinessHelper.playCenteredSound(user, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.WEATHER, 1f, 1f);
        }
    }

    public static void applyWeakeningCloud(LivingEntity weakeningEntity, LivingEntity target, boolean isOffHandStack) {
        int weakeningLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.WEAKENING), weakeningEntity, isOffHandStack);

        if (weakeningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getWeakeningEnchantment().procWeight)) {
                AOECloudHelper.spawnAreaEffectCloudEntityWithAttributes(
                        weakeningEntity,
                        target,
                        5.0f,
                        10,
                        60,
                        StatusEffects.WEAKNESS,
                        100,
                        weakeningLevel - 1,
                        true,
                        true,
                        true,
                        false
                );
            }
        }
    }

    public static void applySwirling(LivingEntity swirlingEntity, LivingEntity target, float amount, boolean isOffHandStack) {
        int swirlingLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.SWIRLING), swirlingEntity, isOffHandStack);

        if (swirlingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getSwirlingEnchantment().procWeight + (15 * swirlingLevel))) {
                causeSwirlingAttack(swirlingEntity, swirlingEntity,
                        1.5f, amount);

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 0.5F, 1.0F);
            }
        }
    }

    public static void causeSwirlingAttack(LivingEntity user, LivingEntity target, float distance, float amount) {
        AOEHelper.getEntitiesByConfig(target, user, distance)
                .forEach(nearbyEntity -> nearbyEntity.damage(nearbyEntity.getWorld().getDamageSources().generic(), amount * 0.5F));
    }

    public static void applyChains(LivingEntity chainingEntity, LivingEntity target, boolean isOffHandStack) {
        int chainsLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.CHAINS), chainingEntity, isOffHandStack);

        if (chainsLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getChainsEnchantment().procWeight))
                chainNearbyEntities(chainingEntity, target, 1.5F * chainsLevel, chainsLevel);
        }
    }

    public static void chainNearbyEntities(LivingEntity user, LivingEntity target, float distance, int timeMultiplier) {
        StatusEffectInstance chained = new StatusEffectInstance(StatusEffects.SLOWNESS, 100 * timeMultiplier, 100);

        target.addStatusEffect(chained);

        AOEHelper.getEntitiesByConfig(user, distance).stream()
                .filter(nearbyEntity -> nearbyEntity != target)
                .forEach(nearbyEntity -> {
                    pullTowards(nearbyEntity, target);
                    nearbyEntity.addStatusEffect(chained);
                });
    }

    public static void applyGravity(LivingEntity gravityEntity, LivingEntity target, boolean isOffHandStack) {
        int gravityLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.GRAVITY), gravityEntity, isOffHandStack);

        if (gravityLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getGravityEnchantment().procWeight)) {
                pullInNearbyEntities(gravityEntity, target,
                        (gravityLevel + 1) * 3);
            }
        }
    }

    public static void pullTowards(Entity self, Entity target) {
        if (self instanceof PlayerEntity && ((PlayerEntity) self).getAbilities().creativeMode) return;

        double motionX = MathHelper.clamp((target.getX() - self.getX()) * 0.15f, -5, 5);
        double motionZ = MathHelper.clamp((target.getZ() - self.getZ()) * 0.15f, -5, 5);
        Vec3d vec3d = new Vec3d(motionX, 0, motionZ);

        self.setVelocity(vec3d);
    }

    public static void pullInNearbyEntities(LivingEntity user, LivingEntity target, float distance) {
        AOEHelper.getEntitiesByConfig(user, distance).stream()
                .filter(nearbyEntity -> nearbyEntity != target)
                .forEach(nearbyEntity -> pullTowards(nearbyEntity, target));
    }



    //mcdw$onApplyDamageTail
    public static void echoDamage(LivingEntity echoEntity, LivingEntity target, float amount, boolean isOffHandStack) {
        int echoLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.ECHO), echoEntity, isOffHandStack);

        if (echoLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getEchoEnchantment().procWeight + (15 * echoLevel))) {
                causeEchoAttack(echoEntity, target,
                        3.0f,
                        echoLevel, amount);
                CleanlinessHelper.playCenteredSound(echoEntity, SoundEventsRegistry.ECHO_SOUND_EVENT, 0.5F, 1.0F);
            }
        }
    }

    public static void causeEchoAttack(LivingEntity user, LivingEntity target, float distance, int echoLevel, float amount) {
        for (LivingEntity nearbyEntity : AOEHelper.getEntitiesByConfig(user, distance)) {
            if (nearbyEntity != target) {
                nearbyEntity.damage(nearbyEntity.getWorld().getDamageSources().generic(), amount);

                echoLevel--;
                if (echoLevel <= 0) break;
            }
        }
    }

    //mcdw$onDeath
    public static void explodingDamage(LivingEntity exploderEntity, LivingEntity target, boolean isOffHandStack) {
        int explodingLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.EXPLODING), exploderEntity, isOffHandStack);
        if (explodingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getExplodingEnchantment().procWeight)) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.5F, 1.0F);
                AOECloudHelper.spawnExplosionCloud(exploderEntity, target, 3.0F);

                float explodingDamage = target.getMaxHealth() * 0.2f * explodingLevel;
                causeExplosionAttack(exploderEntity, target, explodingDamage, 3.0F);
            }
        }
    }

    public static void causeExplosionAttack(LivingEntity user, LivingEntity target, float damageAmount, float distance) {
        AOEHelper.getEntitiesByConfig(target, user, distance)
                .forEach(nearbyEntity -> nearbyEntity.damage(nearbyEntity.getWorld().getDamageSources().explosion(target, user), damageAmount));
    }

    public static void applyRampaging(LivingEntity rampagingEntity, boolean isOffHandStack) {
        int rampagingLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.RAMPAGING), rampagingEntity, isOffHandStack);
        if (rampagingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getRampagingEnchantment().procWeight)) {
                StatusEffectInstance rampage = new StatusEffectInstance(StatusEffects.HASTE, rampagingLevel * 100, 2,
                        false, false);
                rampagingEntity.addStatusEffect(rampage);
            }
        }
    }

    public static void applyGuardingStrike(LivingEntity guardingEntity, boolean isOffHandStack) {
        int guardingLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.GUARDING_STRIKE), guardingEntity, isOffHandStack);
        if (guardingLevel > 0) {

            StatusEffectInstance shield = new StatusEffectInstance(StatusEffects.RESISTANCE, 20 + (20 * guardingLevel), 2);
            guardingEntity.addStatusEffect(shield);
        }
    }

    public static void applyLeeching(LivingEntity leechingEntity, LivingEntity target, boolean isOffHandStack) {
        int leechingLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.LEECHING), leechingEntity, isOffHandStack);

        if (leechingLevel > 0) {
            if (leechingEntity.getHealth() < leechingEntity.getMaxHealth()) {
                float healthRegained = (0.2F + 0.2F * leechingLevel) * target.getMaxHealth();
                leechingEntity.heal(healthRegained * CONFIG.getMcdwEnchantmentStats().getLeechingEnchantment().effectOffset / 100f);
            }
        }
    }

    public static void applyRefreshment(PlayerEntity refreshingEntity, boolean isOffHandStack){
        int refreshmentLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.REFRESHMENT), refreshingEntity, isOffHandStack);

        if (refreshmentLevel > 0) {
            InventoryHelper.mcdw$systematicReplacePotions(refreshingEntity, Items.GLASS_BOTTLE, Potions.HEALING, refreshmentLevel);
        }
    }

    /* PersistentProjectileEntityMixin */
    // mcdw$onEntityHitTail
    public static void applyChainReaction(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        int chainReactionLevel = ((IMcdwEnchantedArrow) ppe).mcdw$getChainReactionLevel();
        if (chainReactionLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getChainReactionEnchantment().procWeight * chainReactionLevel)){
                ProjectileEffectHelper.mcdw$fireChainReactionProjectileFromTarget(target.getEntityWorld(), target, shooter,
                        3.15F,1.0F);
            }
        }
    }

    public static void applyCharge(LivingEntity chargingEntity, PersistentProjectileEntity ppe) {
        int chargeLevel = ((IMcdwEnchantedArrow) ppe).mcdw$getChargeLevel();
        if (chargeLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getChargeEnchantment().procWeight))
                chargingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, chargeLevel * 20,
                        4));
        }
    }

    public static void applyCobwebShotEntity(LivingEntity target, PersistentProjectileEntity ppe) {
        if (((IMcdwEnchantedArrow)ppe).mcdw$getCobwebShotLevel() > 0) {
            World targetWorld = target.getEntityWorld();
            BlockPos targetPos = target.getBlockPos();

            if (targetWorld.getBlockState(targetPos) == Blocks.AIR.getDefaultState())
                targetWorld.setBlockState(targetPos, Blocks.COBWEB.getDefaultState());
        }
    }

    public static void applyFuseShot(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        int fuseShotLevel = ((IMcdwEnchantedArrow) ppe).mcdw$getFuseShotLevel();
        if (fuseShotLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getFuseShotEnchantment().procWeight + (15 * fuseShotLevel))) {
                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.5F, 1.0F);

                AOECloudHelper.spawnExplosionCloud(shooter, target, 3.0F);
                float f = (float) ppe.getVelocity().length();
                int fuseShotDamage = MathHelper.ceil(MathHelper.clamp((double) f * ppe.getDamage(), 0.0D, 2.147483647E9D));
                causeExplosionAttack(shooter, target, fuseShotDamage, 3.0F);
            }
        }
    }

    public static void applyFreezingShot(LivingEntity target, PersistentProjectileEntity ppe) {
        int freezingLevel = ((IMcdwEnchantedArrow) ppe).mcdw$getFreezingLevel();
        if (freezingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getFreezingEnchantment().procWeight + (10 * freezingLevel))) {
                AbilityHelper.causeFreezing(target, 100);
            }
        }
    }

    public static void applyGravityShot(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        int gravityLevel = ((IMcdwEnchantedArrow) ppe).mcdw$getGravityLevel();
        if (gravityLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getGravityEnchantment().procWeight)) {
                pullInNearbyEntities(shooter, target, (gravityLevel + 1) * 3);
            }
        }
    }

    public static void applyLevitationShot(LivingEntity target, PersistentProjectileEntity ppe) {
        int levitationShotLevel = ((IMcdwEnchantedArrow) ppe).mcdw$getLevitationShotLevel();
        if (levitationShotLevel > 0) {

            target.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 200 * levitationShotLevel));
        }
    }

    public static void applyPhantomsMark(LivingEntity target, PersistentProjectileEntity ppe) {
        int phantomsMarkLevel = ((IMcdwEnchantedArrow) ppe).mcdw$getPhantomsMarkLevel();
        if (phantomsMarkLevel > 0) {

            target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100 * phantomsMarkLevel));
        }
    }

    public static void applyPoisonCloudShot(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        int poisonCloudLevel = ((IMcdwEnchantedArrow) ppe).mcdw$getPoisonCloudLevel();
        if (poisonCloudLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getPoisonCloudEnchantment().procWeight)) {
                AOECloudHelper.spawnAreaEffectCloudEntityWithAttributes(
                        shooter,
                        target,
                        5.0f,
                        10,
                        60,
                        StatusEffects.POISON,
                        60,
                        poisonCloudLevel - 1,
                        true,
                        true,
                        false,
                        false
                );
            }
        }
    }

    public static void applyRadianceShot(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        int radianceLevel = ((IMcdwEnchantedArrow) ppe).mcdw$getRadianceLevel();
        if (radianceLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getRadianceEnchantment().procWeight))
                AOECloudHelper.spawnRegenCloudAtPos(shooter, true, target.getBlockPos(), radianceLevel - 1);
        }
    }

    public static void applyRicochet(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        int ricochetLevel = ((IMcdwEnchantedArrow) ppe).mcdw$getRicochetLevel();
        if (ricochetLevel > 0) {

            float damageMultiplier = 0.03F + (ricochetLevel * 0.07F);
            if (ppe.getVelocity().length() > 0.7F)
                ProjectileEffectHelper.mcdw$spawnExtraArrows(shooter, target, 1, 10, damageMultiplier);
        }
    }

    public static void applyReplenish(PlayerEntity shooter, PersistentProjectileEntity ppe) {
        int replenishLevel = ((IMcdwEnchantedArrow) ppe).mcdw$getReplenishLevel();
        if (replenishLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getReplenishEnchantment().procWeight + (7 * replenishLevel))) {
                CleanlinessHelper.mcdw$dropItem(shooter, Items.ARROW);
            }
        }
    }

    public static void applyTempoTheft(LivingEntity tempoEntity, LivingEntity target, PersistentProjectileEntity ppe) {
        int tempoTheftLevel = ((IMcdwEnchantedArrow) ppe).mcdw$getTempoTheftLevel();
        if (tempoTheftLevel > 0) {

            AbilityHelper.stealSpeedFromTarget(tempoEntity, target, tempoTheftLevel);
        }
    }

    public static void applyThunderingShot(LivingEntity shooter, PersistentProjectileEntity ppe){
        int thunderingLevel = ((IMcdwEnchantedArrow)ppe).mcdw$getThunderingLevel();

        if (thunderingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getThunderingEnchantment().procWeight)) {
                electrocuteNearbyEnemies(shooter,
                        5 * thunderingLevel, 5,
                        Integer.MAX_VALUE);
            }
        }
    }

    public static void applyWildRage(MobEntity ragingEntity, PersistentProjectileEntity ppe) {
        int wildRageLevel = ((IMcdwEnchantedArrow)ppe).mcdw$getWildRageLevel();
        if (wildRageLevel > 0) {
            if (CleanlinessHelper.percentToOccur(CONFIG.getMcdwEnchantmentStats().getWildRageEnchantment().procWeight + (10 * wildRageLevel))) {
                sendIntoWildRage(ragingEntity);
            }
        }
    }

    // mcdw$onBlockHitTail
    public static void applyCobwebShotBlock(BlockHitResult blockHitResult, PersistentProjectileEntity ppe) {
        if (((IMcdwEnchantedArrow)ppe).mcdw$getCobwebShotLevel() > 0) {
            World ppeWorld = ppe.getEntityWorld();
            Direction side = blockHitResult.getSide();

            if (ppeWorld.getBlockState(blockHitResult.getBlockPos().offset(side)) == Blocks.AIR.getDefaultState())
                ppeWorld.setBlockState(blockHitResult.getBlockPos().offset(side), Blocks.COBWEB.getDefaultState());
        }
    }

    public static void applyRadianceShotBlock(BlockHitResult blockHitResult, LivingEntity shooter, PersistentProjectileEntity ppe) {
        int radianceLevel = ((IMcdwEnchantedArrow)ppe).mcdw$getRadianceLevel();
        if (radianceLevel > 0) {

            AOECloudHelper.spawnRegenCloudAtPos(shooter, true, blockHitResult.getBlockPos(), radianceLevel - 1);
        }
    }

    // mcdw$onJumpEffects

    public static void activateBurstBowstringOnJump(LivingEntity jumpingEntity) {
        int burstBowstringLevel =
                Math.max(EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.BURST_BOWSTRING), jumpingEntity.getMainHandStack()),
                        EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.BURST_BOWSTRING), jumpingEntity.getOffHandStack()));

        if (burstBowstringLevel > 0) {
            if (jumpingEntity instanceof PlayerEntity attackingPlayer) {
                int availableArrows = Math.min(InventoryHelper.mcdw$countItem(attackingPlayer, Items.ARROW), burstBowstringLevel);
                if (availableArrows < 1) return; //Avoid area lookup

                ProjectileEffectHelper.mcdw$spawnExtraArrows(jumpingEntity, jumpingEntity, availableArrows, 16, 0.4F);
            }
        }
    }
    public static void handleAddDynamoEffect(PlayerEntity playerEntity) {
        ItemStack mainHandStack = playerEntity.getMainHandStack();
        ItemStack offHandStack = playerEntity.getOffHandStack();
        if (Math.max(EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.DYNAMO), mainHandStack),
                EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.DYNAMO), offHandStack)) > 0) {
            StatusEffectInstance dynamoInstance = playerEntity.getStatusEffect(StatusEffectsRegistry.DYNAMO);
            int i = 1;
            if (dynamoInstance != null) {
                i += dynamoInstance.getAmplifier();
            } else {
                --i;
            }
            i = MathHelper.clamp(i, 0, (int) CONFIG.getMcdwEnchantmentStats().getDynamoEnchantment().effectOffset);
            StatusEffectInstance dynamoUpdateInstance = new StatusEffectInstance(StatusEffectsRegistry.DYNAMO, 120000, i, false, false, true);
            playerEntity.addStatusEffect(dynamoUpdateInstance);
        }
    }

    // Goal Effects
    public static void sendIntoWildRage(MobEntity mobEntity) {
        ((MobEntityAccessor)mobEntity).targetSelector().add(0, new WildRageAttackGoal(mobEntity));
    }
}