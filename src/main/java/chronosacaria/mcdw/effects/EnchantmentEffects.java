package chronosacaria.mcdw.effects;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IMcdwEnchantedArrow;
import chronosacaria.mcdw.api.util.*;
import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.damagesource.ElectricShockDamageSource;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enchants.goals.WildRageAttackGoal;
import chronosacaria.mcdw.enums.BowsID;
import chronosacaria.mcdw.enums.EnchantStatsID;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.mixin.MobEntityAccessor;
import chronosacaria.mcdw.sounds.McdwSoundEvents;
import chronosacaria.mcdw.statuseffects.StatusEffectsRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
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

import java.util.LinkedHashMap;
import java.util.UUID;

public class EnchantmentEffects {

    static final LinkedHashMap<EnchantmentsID, Integer> CONFIG_CHANCE = Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.ENCHANTMENT_TRIGGER_BASE_CHANCE;

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
        int mainHandLevel = EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_DEVOURER, playerEntity.getMainHandStack());
        int offHandLevel = EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_DEVOURER, playerEntity.getOffHandStack());

        int soulDevourerLevel = mainHandLevel + offHandLevel;

        if (soulDevourerLevel > 0)
            return Math.round((float) amount * (1 + ((float) soulDevourerLevel / 3f)));
        return amount;
    }

    public static int animaConduitExperience(PlayerEntity playerEntity, int amount, boolean isOffHandStack) {
        int animaLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.ANIMA_CONDUIT, playerEntity, isOffHandStack);

        if (animaLevel > 0) {
            float missingHealth = playerEntity.getMaxHealth() - playerEntity.getHealth();
            if (missingHealth > 0) {
                float i = Math.min(AbilityHelper.getAnimaRepairAmount(amount, animaLevel), missingHealth);
                playerEntity.heal(i);
                amount -= (int) (i * 5);
                return Math.max(amount, 0);
            }
        }
        return amount;
    }

    /* LivingEntityMixin */
    //mcdw$damageModifiers
    public static float huntersPromiseDamage(PlayerEntity owner, ServerWorld serverWorld) {
        if (Mcdw.CONFIG.mcdwEnableItemsConfig.BOWS_ENABLED.get(BowsID.BOW_HUNTERS_PROMISE)) {
            if (owner.getMainHandStack().isOf(BowsID.BOW_HUNTERS_PROMISE.getItem())) {
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
        int prospectorLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.PROSPECTOR, prospectingEntity, isOffHandStack);

        if (prospectorLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.PROSPECTOR) * prospectorLevel)) {
                if (victim instanceof Monster){
                    CleanlinessHelper.mcdw$dropItem(victim, Items.EMERALD);
                }
            }
        }
    }

    public static void applyRushdown(LivingEntity rushingEntity, boolean isOffHandStack) {
        int rushdownLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.RUSHDOWN, rushingEntity, isOffHandStack);

        if (rushdownLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.RUSHDOWN))) {
                StatusEffectInstance rushdown = new StatusEffectInstance(StatusEffects.SPEED, 100 * rushdownLevel, 2,
                        false, false);
                rushingEntity.addStatusEffect(rushdown);
            }
        }
    }

    public static void applySoulSiphon(PlayerEntity siphoningEntity, boolean isOffHandStack) {
        int soulLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.SOUL_SIPHON, siphoningEntity, isOffHandStack);

        if (soulLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.SOUL_SIPHON))) {
                siphoningEntity.addExperience(3 * soulLevel);
            }
        }
    }

    public static void applyShadowShotShadowForm(LivingEntity shadowShotEntity, PersistentProjectileEntity ppe, int duration){
        int shadowShotLevel = ((IMcdwEnchantedArrow)ppe).getShadowShotLevel();
        if (shadowShotLevel > 0) {
            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.SHADOW_SHOT))) {
                shadowShotEntity.addStatusEffect(new StatusEffectInstance(StatusEffectsRegistry.SHADOW_FORM, duration, 0, false, true, true));
            }
        }
    }

    /* LivingEntityPlayerEntityMixin */
    //mcdw$damageModifiers
    public static float ambushDamage(LivingEntity ambushingEntity, LivingEntity ambushee, boolean isOffHandStack) {
        int ambushLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.AMBUSH, ambushingEntity, isOffHandStack);
        if (ambushLevel > 0) {

            if (ambushingEntity.isInvisible() && ambushingEntity.isSneaking()) {

                CleanlinessHelper.playCenteredSound(ambushee, SoundEvents.BLOCK_POINTED_DRIPSTONE_LAND, 0.5F, 1.0F);
                return 0.15f * ambushLevel;
            }
        }
        return 0f;
    }

    public static float criticalHitDamage(LivingEntity crittingEntity, LivingEntity target, boolean isOffHandStack) {
        int criticalHitLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.CRITICAL_HIT, crittingEntity, isOffHandStack);

        if (criticalHitLevel > 0) {

            if (CleanlinessHelper.percentToOccur(10 + (CONFIG_CHANCE.get(EnchantmentsID.CRITICAL_HIT) * criticalHitLevel))) {
                if (!AbilityHelper.entityCanCrit(crittingEntity)) {

                    CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, 0.5F, 1.0F);
                    return 0.5f;
                }
            }
        }
        return 0f;
    }

    public static float voidStrikeDamage(LivingEntity voidEntity, LivingEntity target, boolean isOffHandStack) {
        int voidlevel = mcdw$getEnchantmentLevel(EnchantsRegistry.VOID_STRIKE, voidEntity, isOffHandStack);

        if (voidlevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.VOID_STRIKE) + (5 * voidlevel))) {
                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.5F, 1.0F);
                return (2f * voidlevel) - 1f; // -1f accounts for change to storedAmount calc
            }
        }
        return 0f;
    }

    public static float painCycleDamage(LivingEntity painEntity, boolean isOffHandStack) {
        int painCycleLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.PAIN_CYCLE, painEntity, isOffHandStack);

        if (painCycleLevel > 0) {
            StatusEffectInstance painCycleInstance = painEntity.getStatusEffect(StatusEffectsRegistry.PAIN_CYCLE);
            int i = painCycleInstance != null ? painCycleInstance.getAmplifier() + 1 : 0;
            if (i < 5) {
                StatusEffectInstance painCycleUpdate = new StatusEffectInstance(StatusEffectsRegistry.PAIN_CYCLE, 120000, i, false, false, true);
                painEntity.removeStatusEffect(StatusEffectsRegistry.PAIN_CYCLE);
                painEntity.addStatusEffect(painCycleUpdate);
                painEntity.damage(DamageSource.MAGIC, 1);
            } else {
                painEntity.removeStatusEffect(StatusEffectsRegistry.PAIN_CYCLE);
                return painCycleLevel + 1;
            }
        }
        return 0;
    }

    public static float enigmaResonatorDamage(PlayerEntity resonatingEntity, LivingEntity target, boolean isOffHandStack) {
        int resonatorLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.ENIGMA_RESONATOR, resonatingEntity, isOffHandStack);

        if (resonatorLevel > 0) {

            int numSouls = resonatingEntity.experienceLevel;
            if (numSouls > 0) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.PARTICLE_SOUL_ESCAPE, 0.5F, 1.0F);
                float extraDamageMultiplier =
                        (float) (Math.log(numSouls * resonatorLevel + 20)) /
                                Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.ENIGMA_RESONATOR_DIVISOR.get(EnchantStatsID.ENIGMA_RESONATOR_DIVISOR);

                return Math.max(extraDamageMultiplier - 1, 0f);
            }
        }
        return 0f;
    }

    public static float enigmaShotDamage(LivingEntity resonatingEntity, LivingEntity target, PersistentProjectileEntity ppe) {
        if (!(resonatingEntity instanceof PlayerEntity player))
            return 0f;
        IMcdwEnchantedArrow enchantedArrow = (IMcdwEnchantedArrow) ppe;
        int resonatorLevel = enchantedArrow.getEnigmaResonatorLevel();
        if (resonatorLevel > 0) {

            int numSouls = player.experienceLevel;
            if (numSouls > 0) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.PARTICLE_SOUL_ESCAPE, 0.5F, 1.0F);
                float getExtraDamage = (float) (Math.log(numSouls * resonatorLevel + 20))  /
                        Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.ENIGMA_RESONATOR_DIVISOR.get(EnchantStatsID.ENIGMA_RESONATOR_DIVISOR);

                return Math.max(getExtraDamage - 1, 0f);
            }
        }
        return 0f;
    }

    public static float growingDamage(LivingEntity growingEntity, LivingEntity target, PersistentProjectileEntity ppe) {
        int growingLevel = ((IMcdwEnchantedArrow)ppe).getGrowingLevel();
        if (growingLevel > 0) {

            CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.5F, 1.0F);
            float damageModifier = 0.03F * growingEntity.distanceTo(target) * growingLevel;
            return MathHelper.clamp(damageModifier, 0f, growingLevel);
        }
        return 0f;
    }

    public static float voidShotDamage(LivingEntity target, PersistentProjectileEntity ppe) {
        int voidlevel = ((IMcdwEnchantedArrow)ppe).getVoidShotLevel();
        if (voidlevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.VOID_SHOT) + (5 * voidlevel))) {
                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.5F, 1.0F);
                return voidlevel;
            }
        }
        return 0f;
    }

    public static float committedDamage(LivingEntity committedEntity, LivingEntity target, boolean isOffHandStack) {
        int committedLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.COMMITTED, committedEntity, isOffHandStack);

        if (committedLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.COMMITTED))) {

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
        int dynamoLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.DYNAMO, dynamoEntity, isOffHandStack);
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

    public static float dynamoShotDamage (LivingEntity dynamoEntity, PersistentProjectileEntity ppe) {
        int dynamoLevel = ((IMcdwEnchantedArrow)ppe).getDynamoLevel();
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
        boolean shadowBarbBoolean = ((IMcdwEnchantedArrow)ppe).getShadowBarbBoolean();
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
        int overchargeAmount = ((IMcdwEnchantedArrow)ppe).getOvercharge();
        return Math.max(overchargeAmount, 0);
    }

    //mcdw$onApplyDamageHead
    public static void applyFreezing(LivingEntity freezerEntity, LivingEntity target, boolean isOffHandStack) {
        int freezingLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.FREEZING, freezerEntity, isOffHandStack);
        if (freezingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.FREEZING) + (10 * freezingLevel))) {
                AbilityHelper.causeFreezing(target, 100);
            }
        }
    }

    public static void applyPoisoning(LivingEntity poisoningEntity, LivingEntity target, boolean isOffHandStack) {
        int poisoningLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.JUNGLE_POISON, poisoningEntity, isOffHandStack);
        if (poisoningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.POISONING))) {
                StatusEffectInstance poison = new StatusEffectInstance(StatusEffects.POISON, 60, poisoningLevel - 1);
                target.addStatusEffect(poison);
            }
        }
    }

    public static void applyPoisonCloud(LivingEntity poisoningEntity, LivingEntity target, boolean isOffHandStack) {
        int poisoningLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.POISON_CLOUD, poisoningEntity, isOffHandStack);
        if (poisoningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.POISON_CLOUD))) {
                AOECloudHelper.spawnPickyStatusCloud(poisoningEntity, target, StatusEffects.POISON,
                        60, poisoningLevel - 1, true, true, false);
            }
        }
    }

    public static void applyRadianceCloud(LivingEntity radiantEntity, boolean isOffHandStack) {
        int radianceLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.RADIANCE, radiantEntity, isOffHandStack);

        if (radianceLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.RADIANCE))) {
                AOECloudHelper.spawnPickyStatusCloud(radiantEntity, radiantEntity, StatusEffects.REGENERATION,
                        100, radianceLevel - 1, false, false, true);
            }
        }
    }

    public static void applyShockwave(LivingEntity shockwaveEntity, LivingEntity target, float amount, boolean isOffHandStack) {
        int shockwaveLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.SHOCKWAVE, shockwaveEntity, isOffHandStack);

        if (shockwaveLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.SHOCKWAVE) + (15 * shockwaveLevel))) {
                causeShockwaveAttack(shockwaveEntity, target,
                        3.0f, amount);

                CleanlinessHelper.playCenteredSound(target,
                        SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.WEATHER,
                        0.5f, 1.0f);
            }
        }
    }

    public static void causeShockwaveAttack(LivingEntity user, LivingEntity target, float distance, float amount) {
        AOEHelper.getAoeTargets(target, user, distance).stream()
                .filter(nearbyEntity -> nearbyEntity != target)
                .forEach(nearbyEntity -> nearbyEntity.damage(DamageSource.GENERIC, amount * 0.25f));
    }

    public static void causeSmitingAttack(LivingEntity user, LivingEntity target, float distance, float amount) {
        AOEHelper.getAoeTargets(target, user, distance).stream()
                .filter(nearbyEntity -> nearbyEntity != target && nearbyEntity.isUndead())
                .forEach(nearbyEntity -> nearbyEntity.damage(DamageSource.MAGIC, amount * 1.25F));
    }

    public static void applyStunning(LivingEntity stunningEntity, LivingEntity target, boolean isOffHandStack) {
        int stunningLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.STUNNING, stunningEntity, isOffHandStack);
        if (stunningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.STUNNING) + (15 * stunningLevel))) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 10));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 60, 1));
            }
        }
    }

    public static void applyThundering(LivingEntity thunderingEntity, float amount, boolean isOffHandStack) {
        int thunderingLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.THUNDERING, thunderingEntity, isOffHandStack);

        if (thunderingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.THUNDERING))) {
                electrocuteNearbyEnemies(thunderingEntity,
                        5 * thunderingLevel, amount,
                        Integer.MAX_VALUE);
            }
        }
    }

    public static void createVisualLightningBoltOnEntity(Entity target) {
        World world = target.getEntityWorld();
        LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);

        if (lightningEntity != null) {
            lightningEntity.refreshPositionAfterTeleport(target.getX(), target.getY(), target.getZ());
            lightningEntity.setCosmetic(true);
            world.spawnEntity(lightningEntity);
        }
    }

    public static void electrocute(LivingEntity attacker, LivingEntity victim, float damageAmount) {
        createVisualLightningBoltOnEntity(victim);
        DamageSource electricShockDamageSource = new ElectricShockDamageSource(attacker).setUsesMagic();
        victim.damage(electricShockDamageSource, damageAmount);
    }

    public static void electrocuteNearbyEnemies(LivingEntity user, float distance, float damageAmount, int limit) {
        CleanlinessHelper.playCenteredSound(user, SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.WEATHER, 1f, 1f);
        CleanlinessHelper.playCenteredSound(user, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.WEATHER, 1f, 1f);

        for (LivingEntity nearbyEntity : AOEHelper.getAoeTargets(user, user, distance)) {
            electrocute(user, nearbyEntity, damageAmount);

            limit--;
            if (limit <= 0) break;
        }
    }

    public static void applyWeakeningCloud(LivingEntity weakeningEntity, LivingEntity target, boolean isOffHandStack) {
        int weakeningLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.WEAKENING, weakeningEntity, isOffHandStack);

        if (weakeningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.WEAKENING))) {
                AOECloudHelper.spawnPickyStatusCloud(weakeningEntity, target, StatusEffects.WEAKNESS,
                        60, weakeningLevel - 1, true, true, false);
            }
        }
    }

    public static void applySwirling(LivingEntity swirlingEntity, LivingEntity target, float amount, boolean isOffHandStack) {
        int swirlingLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.SWIRLING, swirlingEntity, isOffHandStack);

        if (swirlingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.SWIRLING) + (15 * swirlingLevel))) {
                causeSwirlingAttack(swirlingEntity, swirlingEntity,
                        1.5f, amount);

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 0.5F, 1.0F);
            }
        }
    }

    public static void causeSwirlingAttack(LivingEntity user, LivingEntity target, float distance, float amount) {
        AOEHelper.getAoeTargets(target, user, distance)
                .forEach(nearbyEntity -> nearbyEntity.damage(DamageSource.GENERIC, amount * 0.5F));
    }

    public static void applyChains(LivingEntity chainingEntity, LivingEntity target, boolean isOffHandStack) {
        int chainsLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.CHAINS, chainingEntity, isOffHandStack);

        if (chainsLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.CHAINS)))
                chainNearbyEntities(chainingEntity, target, 1.5F * chainsLevel, chainsLevel);
        }
    }

    public static void chainNearbyEntities(LivingEntity user, LivingEntity target, float distance, int timeMultiplier) {
        StatusEffectInstance chained = new StatusEffectInstance(StatusEffects.SLOWNESS, 100 * timeMultiplier, 100);

        target.addStatusEffect(chained);

        AOEHelper.getAoeTargets(target, user, distance).stream()
                .filter(nearbyEntity -> nearbyEntity != target)
                .forEach(nearbyEntity -> {
                    pullTowards(nearbyEntity, target);
                    nearbyEntity.addStatusEffect(chained);
                });
    }

    public static void applyGravity(LivingEntity gravityEntity, LivingEntity target, boolean isOffHandStack) {
        int gravityLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.GRAVITY, gravityEntity, isOffHandStack);

        if (gravityLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.GRAVITY))) {
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
        AOEHelper.getAoeTargets(target, user, distance).stream()
                .filter(nearbyEntity -> nearbyEntity != target)
                .forEach(nearbyEntity -> pullTowards(nearbyEntity, target));
    }



    //mcdw$onApplyDamageTail
    public static void echoDamage(LivingEntity echoEntity, LivingEntity target, float amount, boolean isOffHandStack) {
        int echoLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.ECHO, echoEntity, isOffHandStack);

        if (echoLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.ECHO) + (15 * echoLevel))) {
                causeEchoAttack(echoEntity, target,
                        3.0f,
                        echoLevel, amount);
                CleanlinessHelper.playCenteredSound(echoEntity, McdwSoundEvents.ECHO_SOUND_EVENT, 0.5F, 1.0F);
            }
        }
    }

    public static void causeEchoAttack(LivingEntity user, LivingEntity target, float distance, int echoLevel, float amount) {
        for (LivingEntity nearbyEntity : AOEHelper.getAoeTargets(target, user, distance)) {
            if (nearbyEntity != target) {
                nearbyEntity.damage(DamageSource.GENERIC, amount);

                echoLevel--;
                if (echoLevel <= 0) break;
            }
        }
    }

    //mcdw$onDeath
    public static void explodingDamage(LivingEntity exploderEntity, LivingEntity target, boolean isOffHandStack) {
        int explodingLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.EXPLODING, exploderEntity, isOffHandStack);
        if (explodingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.EXPLODING))) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.5F, 1.0F);
                AOECloudHelper.spawnExplosionCloud(exploderEntity, target, 3.0F);

                float explodingDamage = target.getMaxHealth() * 0.2f * explodingLevel;
                causeExplosionAttack(exploderEntity, target, explodingDamage, 3.0F);
            }
        }
    }

    public static void causeExplosionAttack(LivingEntity user, LivingEntity target, float damageAmount, float distance) {
        AOEHelper.getAoeTargets(target, user, distance)
                .forEach(nearbyEntity -> nearbyEntity.damage(DamageSource.explosion(user), damageAmount));
    }

    public static void applyRampaging(LivingEntity rampagingEntity, boolean isOffHandStack) {
        int rampagingLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.RAMPAGING, rampagingEntity, isOffHandStack);
        if (rampagingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.RAMPAGING))) {
                StatusEffectInstance rampage = new StatusEffectInstance(StatusEffects.HASTE, rampagingLevel * 100, 2,
                        false, false);
                rampagingEntity.addStatusEffect(rampage);
            }
        }
    }

    public static void applyGuardingStrike(LivingEntity guardingEntity, boolean isOffHandStack) {
        int guardingLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.GUARDING_STRIKE, guardingEntity, isOffHandStack);
        if (guardingLevel > 0) {

            StatusEffectInstance shield = new StatusEffectInstance(StatusEffects.RESISTANCE, 20 + (20 * guardingLevel), 2);
            guardingEntity.addStatusEffect(shield);
        }
    }

    public static void applyLeeching(LivingEntity leechingEntity, LivingEntity target, boolean isOffHandStack) {
        int leechingLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.LEECHING, leechingEntity, isOffHandStack);

        if (leechingLevel > 0) {
            if (leechingEntity.getHealth() < leechingEntity.getMaxHealth()) {
                float healthRegained = (0.2F + 0.2F * leechingLevel) * target.getMaxHealth();
                leechingEntity.heal(healthRegained);
            }
        }
    }

    public static void applyRefreshment(PlayerEntity refreshingEntity, boolean isOffHandStack){
        int refreshmentLevel = mcdw$getEnchantmentLevel(EnchantsRegistry.REFRESHMENT, refreshingEntity, isOffHandStack);

        if (refreshmentLevel > 0) {
            InventoryHelper.mcdw$systematicReplacePotions(refreshingEntity, Items.GLASS_BOTTLE, Potions.HEALING, refreshmentLevel);
        }
    }

    /* PersistentProjectileEntityMixin */
    // mcdw$onEntityHitTail
    public static void applyChainReaction(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        int chainReactionLevel = ((IMcdwEnchantedArrow) ppe).getChainReactionLevel();
        if (chainReactionLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.CHAIN_REACTION) * chainReactionLevel)){
                ProjectileEffectHelper.fireChainReactionProjectileFromTarget(target.getEntityWorld(), target, shooter,
                        3.15F,1.0F);
            }
        }
    }

    public static void applyCharge(LivingEntity chargingEntity, PersistentProjectileEntity ppe) {
        int chargeLevel = ((IMcdwEnchantedArrow) ppe).getChargeLevel();
        if (chargeLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.CHARGE)))
                chargingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, chargeLevel * 20,
                        4));
        }
    }

    public static void applyCobwebShotEntity(LivingEntity target, PersistentProjectileEntity ppe) {
        if (((IMcdwEnchantedArrow)ppe).getCobwebShotLevel() > 0) {
            World targetWorld = target.getEntityWorld();
            BlockPos targetPos = target.getBlockPos();

            if (targetWorld.getBlockState(targetPos) == Blocks.AIR.getDefaultState())
                targetWorld.setBlockState(targetPos, Blocks.COBWEB.getDefaultState());
        }
    }

    public static void applyFuseShot(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        int fuseShotLevel = ((IMcdwEnchantedArrow) ppe).getFuseShotLevel();
        if (fuseShotLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.FUSE_SHOT) + (15 * fuseShotLevel))) {
                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.5F, 1.0F);

                AOECloudHelper.spawnExplosionCloud(shooter, target, 3.0F);
                float f = (float) ppe.getVelocity().length();
                int fuseShotDamage = MathHelper.ceil(MathHelper.clamp((double) f * ppe.getDamage(), 0.0D, 2.147483647E9D));
                causeExplosionAttack(shooter, target, fuseShotDamage, 3.0F);
            }
        }
    }

    public static void applyGravityShot(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        int gravityLevel = ((IMcdwEnchantedArrow) ppe).getGravityLevel();
        if (gravityLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.GRAVITY))) {
                pullInNearbyEntities(shooter, target, (gravityLevel + 1) * 3);
            }
        }
    }

    public static void applyLevitationShot(LivingEntity target, PersistentProjectileEntity ppe) {
        int levitationShotLevel = ((IMcdwEnchantedArrow) ppe).getLevitationShotLevel();
        if (levitationShotLevel > 0) {

            target.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 200 * levitationShotLevel));
        }
    }

    public static void applyPhantomsMark(LivingEntity target, PersistentProjectileEntity ppe) {
        int phantomsMarkLevel = ((IMcdwEnchantedArrow) ppe).getPhantomsMarkLevel();
        if (phantomsMarkLevel > 0) {

            target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100 * phantomsMarkLevel));
        }
    }

    public static void applyPoisonCloudShot(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        int poisonCloudLevel = ((IMcdwEnchantedArrow) ppe).getPoisonCloudLevel();
        if (poisonCloudLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.POISON_CLOUD))) {
                AOECloudHelper.spawnStatusCloud(shooter, target, StatusEffects.POISON, poisonCloudLevel - 1);
            }
        }
    }

    public static void applyRadianceShot(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        int radianceLevel = ((IMcdwEnchantedArrow) ppe).getRadianceLevel();
        if (radianceLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.RADIANCE)))
                AOECloudHelper.spawnRegenCloudAtPos(shooter, true, target.getBlockPos(), radianceLevel - 1);
        }
    }

    public static void applyRicochet(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        int ricochetLevel = ((IMcdwEnchantedArrow) ppe).getRicochetLevel();
        if (ricochetLevel > 0) {

            float damageMultiplier = 0.03F + (ricochetLevel * 0.07F);
            float arrowVelocity = McdwBow.maxBowRange;
            if (arrowVelocity > 0.1F)
                ProjectileEffectHelper.spawnExtraArrows(shooter, target, 1, 10, damageMultiplier);
        }
    }

    public static void applyReplenish(PlayerEntity shooter, PersistentProjectileEntity ppe) {
        int replenishLevel = ((IMcdwEnchantedArrow) ppe).getReplenishLevel();
        if (replenishLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.REPLENISH) + (7 * replenishLevel))) {
                CleanlinessHelper.mcdw$dropItem(shooter, Items.ARROW);
            }
        }
    }

    public static void applyTempoTheft(LivingEntity tempoEntity, LivingEntity target, PersistentProjectileEntity ppe) {
        int tempoTheftLevel = ((IMcdwEnchantedArrow) ppe).getTempoTheftLevel();
        if (tempoTheftLevel > 0) {

            AbilityHelper.stealSpeedFromTarget(tempoEntity, target, tempoTheftLevel);
        }
    }

    public static void applyWildRage(MobEntity ragingEntity, PersistentProjectileEntity ppe) {
        int wildRageLevel = ((IMcdwEnchantedArrow)ppe).getWildRageLevel();
        if (wildRageLevel > 0) {
            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.WILD_RAGE) + (10 * wildRageLevel))) {
                sendIntoWildRage(ragingEntity);
            }
        }
    }

    // mcdw$onBlockHitTail
    public static void applyCobwebShotBlock(BlockHitResult blockHitResult, PersistentProjectileEntity ppe) {
        if (((IMcdwEnchantedArrow)ppe).getCobwebShotLevel() > 0) {
            World ppeWorld = ppe.getEntityWorld();
            Direction side = blockHitResult.getSide();

            if (ppeWorld.getBlockState(blockHitResult.getBlockPos().offset(side)) == Blocks.AIR.getDefaultState())
                ppeWorld.setBlockState(blockHitResult.getBlockPos().offset(side), Blocks.COBWEB.getDefaultState());
        }
    }

    public static void applyRadianceShotBlock(BlockHitResult blockHitResult, LivingEntity shooter, PersistentProjectileEntity ppe) {
        int radianceLevel = ((IMcdwEnchantedArrow)ppe).getRadianceLevel();
        if (radianceLevel > 0) {

            AOECloudHelper.spawnRegenCloudAtPos(shooter, true, blockHitResult.getBlockPos(), radianceLevel - 1);
        }
    }

    // mcdw$onJumpEffects

    public static void activateBurstBowstringOnJump(LivingEntity jumpingEntity) {
        int burstBowstringLevel =
                Math.max(EnchantmentHelper.getLevel(EnchantsRegistry.BURST_BOWSTRING, jumpingEntity.getMainHandStack()),
                        EnchantmentHelper.getLevel(EnchantsRegistry.BURST_BOWSTRING, jumpingEntity.getOffHandStack()));

        if (burstBowstringLevel > 0) {
            if (jumpingEntity instanceof PlayerEntity attackingPlayer) {
                int availableArrows = Math.min(InventoryHelper.mcdw$countItem(attackingPlayer, Items.ARROW), burstBowstringLevel);
                if (availableArrows < 1) return; //Avoid area lookup

                ProjectileEffectHelper.spawnExtraArrows(jumpingEntity, jumpingEntity, availableArrows, 16, 0.4F);
            }
        }
    }
    public static void handleAddDynamoEffect(PlayerEntity playerEntity) {
        ItemStack mainHandStack = playerEntity.getMainHandStack();
        ItemStack offHandStack = playerEntity.getOffHandStack();
        if (Math.max(EnchantmentHelper.getLevel(EnchantsRegistry.DYNAMO, mainHandStack), EnchantmentHelper.getLevel(EnchantsRegistry.DYNAMO, offHandStack)) > 0) {
            StatusEffectInstance dynamoInstance = playerEntity.getStatusEffect(StatusEffectsRegistry.DYNAMO);
            int i = 1;
            if (dynamoInstance != null) {
                i += dynamoInstance.getAmplifier();
            } else {
                --i;
            }
            i = MathHelper.clamp(i, 0, Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.DYNAMO_STACK_CAP.get(EnchantStatsID.DYNAMO_STACK_CAP));
            StatusEffectInstance dynamoUpdateInstance = new StatusEffectInstance(StatusEffectsRegistry.DYNAMO, 120000, i, false, false, true);
            playerEntity.addStatusEffect(dynamoUpdateInstance);
        }
    }

    // Goal Effects
    public static void sendIntoWildRage(MobEntity mobEntity) {
        ((MobEntityAccessor)mobEntity).targetSelector().add(0, new WildRageAttackGoal(mobEntity));
    }
}
