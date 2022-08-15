package chronosacaria.mcdw.effects;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IMcdwEnchantedArrow;
import chronosacaria.mcdw.api.util.*;
import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enchants.goals.WildRageAttackGoal;
import chronosacaria.mcdw.enums.BowsID;
import chronosacaria.mcdw.enums.EnchantStatsID;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.mixin.MobEntityAccessor;
import chronosacaria.mcdw.sounds.McdwSoundEvents;
import chronosacaria.mcdw.statuseffects.StatusEffectsRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
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
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.LinkedHashMap;
import java.util.UUID;

public class EnchantmentEffects {

    static final LinkedHashMap<EnchantmentsID, Integer> CONFIG_CHANCE = Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase;

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

    public static int animaConduitExperience(PlayerEntity playerEntity, int amount) {
        int animaLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ANIMA_CONDUIT, playerEntity.getMainHandStack());
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

    public static int animaConduitExperienceFromOffHand(PlayerEntity playerEntity, int amount) {
        int animaLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ANIMA_CONDUIT, playerEntity.getOffHandStack());
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
        if (Mcdw.CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_HUNTERS_PROMISE)) {
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
    public static void applyProspector(LivingEntity prospectingEntity, LivingEntity victim) {
        int prospectorLevel = EnchantmentHelper.getLevel(EnchantsRegistry.PROSPECTOR, prospectingEntity.getMainHandStack());
        if (prospectorLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.PROSPECTOR) * prospectorLevel)) {
                if (victim instanceof Monster){
                    CleanlinessHelper.mcdw$dropItem(victim, Items.EMERALD);
                }
            }
        }
    }

    public static void applyProspectorFromOffHand(LivingEntity prospectingEntity, LivingEntity victim) {
        int prospectorLevel = EnchantmentHelper.getLevel(EnchantsRegistry.PROSPECTOR, prospectingEntity.getOffHandStack());
        if (prospectorLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.PROSPECTOR) * prospectorLevel)) {
                if (victim instanceof Monster){
                    CleanlinessHelper.mcdw$dropItem(victim, Items.EMERALD);
                }
            }
        }
    }

    public static void applyRushdown(LivingEntity rushingEntity) {
        int rushdownLevel = EnchantmentHelper.getLevel(EnchantsRegistry.RUSHDOWN, rushingEntity.getMainHandStack());
        if (rushdownLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.RUSHDOWN))) {
                StatusEffectInstance rushdown = new StatusEffectInstance(StatusEffects.SPEED, 100 * rushdownLevel, 2,
                        false, false);
                rushingEntity.addStatusEffect(rushdown);
            }
        }
    }

    public static void applyRushdownFromOffHand(LivingEntity rushingEntity) {
        int rushdownLevel = EnchantmentHelper.getLevel(EnchantsRegistry.RUSHDOWN, rushingEntity.getOffHandStack());
        if (rushdownLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.RUSHDOWN))) {
                StatusEffectInstance rushdown = new StatusEffectInstance(StatusEffects.SPEED, 100 * rushdownLevel, 2,
                        false, false);
                rushingEntity.addStatusEffect(rushdown);
            }
        }
    }

    public static void applySoulSiphon(PlayerEntity siphoningEntity) {
        int soulLevel = EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_SIPHON, siphoningEntity.getMainHandStack());
        if (soulLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.SOUL_SIPHON))) {
                siphoningEntity.addExperience(3 * soulLevel);
            }
        }
    }

    public static void applySoulSiphonFromOffHand(PlayerEntity siphoningEntity) {
        int soulLevel = EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_SIPHON, siphoningEntity.getOffHandStack());
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
    public static float ambushDamage(LivingEntity ambushingEntity, LivingEntity ambushee) {
        int ambushLevel = EnchantmentHelper.getLevel(EnchantsRegistry.AMBUSH, ambushingEntity.getMainHandStack());
        if (ambushLevel > 0) {

            if (ambushingEntity.isInvisible() && ambushingEntity.isSneaking()) {

                CleanlinessHelper.playCenteredSound(ambushee, SoundEvents.BLOCK_POINTED_DRIPSTONE_LAND, 0.5F, 1.0F);
                return 0.15f * ambushLevel;
            }
        }
        return 0f;
    }

    public static float ambushDamageFromOffHand(LivingEntity ambushingEntity, LivingEntity ambushee) {
        int ambushLevel = EnchantmentHelper.getLevel(EnchantsRegistry.AMBUSH, ambushingEntity.getOffHandStack());
        if (ambushLevel > 0) {

            if (ambushingEntity.isInvisible() && ambushingEntity.isSneaking()) {

                CleanlinessHelper.playCenteredSound(ambushee, SoundEvents.BLOCK_POINTED_DRIPSTONE_LAND, 0.5F, 1.0F);
                return 0.15f * ambushLevel;
            }
        }
        return 0f;
    }

    public static float criticalHitDamage(LivingEntity crittingEntity, LivingEntity target) {
        int criticalHitLevel = EnchantmentHelper.getLevel(EnchantsRegistry.CRITICAL_HIT, crittingEntity.getMainHandStack());
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

    public static float criticalHitDamageFromOffHand(LivingEntity crittingEntity, LivingEntity target) {
        int criticalHitLevel = EnchantmentHelper.getLevel(EnchantsRegistry.CRITICAL_HIT, crittingEntity.getOffHandStack());
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

    public static float voidStrikeDamage(LivingEntity voidEntity, LivingEntity target) {
        int voidlevel = EnchantmentHelper.getLevel(EnchantsRegistry.VOID_STRIKE, voidEntity.getMainHandStack());
        if (voidlevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.VOID_STRIKE) + (5 * voidlevel))) {
                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.5F, 1.0F);
                return (2f * voidlevel) - 1f; // -1f accounts for change to storedAmount calc
            }
        }
        return 0f;
    }

    public static float voidStrikeDamageFromOffHand(LivingEntity voidEntity, LivingEntity target) {
        int voidlevel = EnchantmentHelper.getLevel(EnchantsRegistry.VOID_STRIKE, voidEntity.getOffHandStack());
        if (voidlevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.VOID_STRIKE) + (5 * voidlevel))) {
                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.5F, 1.0F);
                return (2f * voidlevel) - 1f; // -1f accounts for change to storedAmount calc
            }
        }
        return 0f;
    }

    public static float painCycleDamage(LivingEntity painEntity) {
        int painCycleLevel = EnchantmentHelper.getLevel(EnchantsRegistry.PAIN_CYCLE, painEntity.getMainHandStack());
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

    public static float painCycleDamageFromOffHand(LivingEntity painEntity) {
        int painCycleLevel = EnchantmentHelper.getLevel(EnchantsRegistry.PAIN_CYCLE, painEntity.getOffHandStack());
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

    public static float enigmaResonatorDamage(PlayerEntity resonatingEntity, LivingEntity target) {
        int resonatorLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ENIGMA_RESONATOR, resonatingEntity.getMainHandStack());
        if (resonatorLevel > 0) {

            int numSouls = resonatingEntity.experienceLevel;
            if (numSouls > 0) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.PARTICLE_SOUL_ESCAPE, 0.5F, 1.0F);
                float extraDamageMultiplier =
                        (float) (Math.log(numSouls * resonatorLevel + 20)) /
                                Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enigmaResonatorDivisor.get(EnchantStatsID.ENIGMA_RESONATOR_DIVISOR);

                return Math.max(extraDamageMultiplier - 1, 0f);
            }
        }
        return 0f;
    }

    public static float enigmaResonatorDamageFromOffHand(PlayerEntity resonatingEntity, LivingEntity target) {
        int resonatorLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ENIGMA_RESONATOR, resonatingEntity.getOffHandStack());
        if (resonatorLevel > 0) {

            int numSouls = resonatingEntity.experienceLevel;
            if (numSouls > 0) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.PARTICLE_SOUL_ESCAPE, 0.5F, 1.0F);
                float extraDamageMultiplier =
                        (float) (Math.log(numSouls * resonatorLevel + 20)) /
                                Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enigmaResonatorDivisor.get(EnchantStatsID.ENIGMA_RESONATOR_DIVISOR);

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
                        Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enigmaResonatorDivisor.get(EnchantStatsID.ENIGMA_RESONATOR_DIVISOR);

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

    public static float committedDamage(LivingEntity committedEntity, LivingEntity target) {
        int committedLevel = EnchantmentHelper.getLevel(EnchantsRegistry.COMMITTED, committedEntity.getMainHandStack());
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

    public static float committedDamageFromOffHand(LivingEntity committedEntity, LivingEntity target) {
        int committedLevel = EnchantmentHelper.getLevel(EnchantsRegistry.COMMITTED, committedEntity.getOffHandStack());
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

    public static float dynamoDamage (LivingEntity dynamoEntity) {
        int dynamoLevel = EnchantmentHelper.getLevel(EnchantsRegistry.DYNAMO, dynamoEntity.getMainHandStack());
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

    public static float dynamoDamageFromOffHand (LivingEntity dynamoEntity) {
        int dynamoLevel = EnchantmentHelper.getLevel(EnchantsRegistry.DYNAMO, dynamoEntity.getOffHandStack());
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
    public static void applyFreezing(LivingEntity freezerEntity, LivingEntity target) {
        int freezingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.FREEZING, freezerEntity.getMainHandStack());
        if (freezingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.FREEZING) + (10 * freezingLevel))) {
                AbilityHelper.causeFreezing(target, 100);
            }
        }
    }

    public static void applyFreezingFromOffHand(LivingEntity freezerEntity, LivingEntity target) {
        int freezingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.FREEZING, freezerEntity.getOffHandStack());
        if (freezingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.FREEZING) + (10 * freezingLevel))) {
                AbilityHelper.causeFreezing(target, 100);
            }
        }
    }

    public static void applyPoisoning(LivingEntity poisoningEntity, LivingEntity target) {
        int poisoningLevel = EnchantmentHelper.getLevel(EnchantsRegistry.JUNGLE_POISON, poisoningEntity.getMainHandStack());
        if (poisoningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.POISONING))) {
                StatusEffectInstance poison = new StatusEffectInstance(StatusEffects.POISON, 60, poisoningLevel - 1);
                target.addStatusEffect(poison);
            }
        }
    }

    public static void applyPoisoningFromOffHand(LivingEntity poisoningEntity, LivingEntity target) {
        int poisoningLevel = EnchantmentHelper.getLevel(EnchantsRegistry.JUNGLE_POISON, poisoningEntity.getOffHandStack());
        if (poisoningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.POISONING))) {
                StatusEffectInstance poison = new StatusEffectInstance(StatusEffects.POISON, 60, poisoningLevel - 1);
                target.addStatusEffect(poison);
            }
        }
    }

    public static void applyPoisonCloud(LivingEntity poisoningEntity, LivingEntity target) {
        int poisoningLevel = EnchantmentHelper.getLevel(EnchantsRegistry.POISON_CLOUD, poisoningEntity.getMainHandStack());
        if (poisoningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.POISON_CLOUD))) {
                AOECloudHelper.spawnPickyStatusCloud(poisoningEntity, target, StatusEffects.POISON,
                        60, poisoningLevel - 1, true, true, false);
            }
        }
    }

    public static void applyPoisonCloudFromOffHand(LivingEntity poisoningEntity, LivingEntity target) {
        int poisoningLevel = EnchantmentHelper.getLevel(EnchantsRegistry.POISON_CLOUD, poisoningEntity.getOffHandStack());
        if (poisoningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.POISON_CLOUD))) {
                AOECloudHelper.spawnPickyStatusCloud(poisoningEntity, target, StatusEffects.POISON,
                        60, poisoningLevel - 1, true, true, false);
            }
        }
    }

    public static void applyRadianceCloud(LivingEntity radiantEntity) {
        int radianceLevel = EnchantmentHelper.getLevel(EnchantsRegistry.RADIANCE, radiantEntity.getMainHandStack());
        if (radianceLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.RADIANCE))) {
                AOECloudHelper.spawnPickyStatusCloud(radiantEntity, radiantEntity, StatusEffects.REGENERATION,
                        100, radianceLevel - 1, false, false, true);
            }
        }
    }

    public static void applyRadianceCloudFromOffHand(LivingEntity radiantEntity) {
        int radianceLevel = EnchantmentHelper.getLevel(EnchantsRegistry.RADIANCE, radiantEntity.getOffHandStack());
        if (radianceLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.RADIANCE))) {
                AOECloudHelper.spawnPickyStatusCloud(radiantEntity, radiantEntity, StatusEffects.REGENERATION,
                        100, radianceLevel - 1, false, false, true);
            }
        }
    }

    public static void applyShockwave(LivingEntity shockwaveEntity, LivingEntity target, float amount) {
        int shockwaveLevel = EnchantmentHelper.getLevel(EnchantsRegistry.SHOCKWAVE, shockwaveEntity.getMainHandStack());
        if (shockwaveLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.SHOCKWAVE) + (15 * shockwaveLevel))) {
                AOEHelper.causeShockwaveAttack(shockwaveEntity, target,
                        3.0f, amount);

                CleanlinessHelper.playCenteredSound(target,
                        SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.WEATHER,
                        0.5f, 1.0f);
            }
        }
    }

    public static void applyShockwaveFromOffHand(LivingEntity shockwaveEntity, LivingEntity target, float amount) {
        int shockwaveLevel = EnchantmentHelper.getLevel(EnchantsRegistry.SHOCKWAVE, shockwaveEntity.getOffHandStack());
        if (shockwaveLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.SHOCKWAVE) + (15 * shockwaveLevel))) {
                AOEHelper.causeShockwaveAttack(shockwaveEntity, target,
                        3.0f, amount);

                CleanlinessHelper.playCenteredSound(target,
                        SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.WEATHER,
                        0.5f, 1.0f);
            }
        }
    }

    public static void applyStunning(LivingEntity stunningEntity, LivingEntity target) {
        int stunningLevel = EnchantmentHelper.getLevel(EnchantsRegistry.STUNNING, stunningEntity.getMainHandStack());
        if (stunningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.STUNNING) + (15 * stunningLevel))) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 10));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 60, 1));
            }
        }
    }
    public static void applyStunningFromOffHand(LivingEntity stunningEntity, LivingEntity target) {
        int stunningLevel = EnchantmentHelper.getLevel(EnchantsRegistry.STUNNING, stunningEntity.getOffHandStack());
        if (stunningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.STUNNING) + (15 * stunningLevel))) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 10));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 60, 1));
            }
        }
    }

    public static void applyThundering(LivingEntity thunderingEntity, float amount) {
        int thunderingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.THUNDERING, thunderingEntity.getMainHandStack());
        if (thunderingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.THUNDERING))) {
                AOEHelper.electrocuteNearbyEnemies(thunderingEntity,
                        5 * thunderingLevel, amount,
                        Integer.MAX_VALUE);
            }
        }
    }

    public static void applyThunderingFromOffHand(LivingEntity thunderingEntity, float amount) {
        int thunderingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.THUNDERING, thunderingEntity.getOffHandStack());
        if (thunderingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.THUNDERING))) {
                AOEHelper.electrocuteNearbyEnemies(thunderingEntity,
                        5 * thunderingLevel, amount,
                        Integer.MAX_VALUE);
            }
        }
    }

    public static void applyWeakeningCloud(LivingEntity weakeningEntity, LivingEntity target) {
        int weakeningLevel = EnchantmentHelper.getLevel(EnchantsRegistry.WEAKENING, weakeningEntity.getMainHandStack());
        if (weakeningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.WEAKENING))) {
                AOECloudHelper.spawnPickyStatusCloud(weakeningEntity, target, StatusEffects.WEAKNESS,
                        60, weakeningLevel - 1, true, true, false);
            }
        }
    }
    public static void applyWeakeningCloudFromOffHand(LivingEntity weakeningEntity, LivingEntity target) {
        int weakeningLevel = EnchantmentHelper.getLevel(EnchantsRegistry.WEAKENING, weakeningEntity.getOffHandStack());
        if (weakeningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.WEAKENING))) {
                AOECloudHelper.spawnPickyStatusCloud(weakeningEntity, target, StatusEffects.WEAKNESS,
                        60, weakeningLevel - 1, true, true, false);
            }
        }
    }

    public static void applySwirling(LivingEntity swirlingEntity, LivingEntity target, float amount) {
        int swirlingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.SWIRLING, swirlingEntity.getMainHandStack());
        if (swirlingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.SWIRLING) + (15 * swirlingLevel))) {
                AOEHelper.causeSwirlingAttack(swirlingEntity, swirlingEntity,
                        1.5f, amount);

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 0.5F, 1.0F);
            }
        }
    }

    public static void applySwirlingFromOffHand(LivingEntity swirlingEntity, LivingEntity target, float amount) {
        int swirlingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.SWIRLING, swirlingEntity.getOffHandStack());
        if (swirlingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.SWIRLING) + (15 * swirlingLevel))) {
                AOEHelper.causeSwirlingAttack(swirlingEntity, swirlingEntity,
                        1.5f, amount);

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 0.5F, 1.0F);
            }
        }
    }

    public static void applyChains(LivingEntity chainingEntity, LivingEntity target) {
        int chainsLevel = EnchantmentHelper.getLevel(EnchantsRegistry.CHAINS, chainingEntity.getMainHandStack());
        if (chainsLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.CHAINS)))
                AOEHelper.chainNearbyEntities(chainingEntity, target, 1.5F * chainsLevel, chainsLevel);
        }
    }

    public static void applyChainsFromOffHand(LivingEntity chainingEntity, LivingEntity target) {
        int chainsLevel = EnchantmentHelper.getLevel(EnchantsRegistry.CHAINS, chainingEntity.getOffHandStack());
        if (chainsLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.CHAINS)))
                AOEHelper.chainNearbyEntities(chainingEntity, target, 1.5F * chainsLevel, chainsLevel);
        }
    }

    public static void applyGravity(LivingEntity gravityEntity, LivingEntity target) {
        int gravityLevel = EnchantmentHelper.getLevel(EnchantsRegistry.GRAVITY, gravityEntity.getMainHandStack());
        if (gravityLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.GRAVITY))) {
                AOEHelper.pullInNearbyEntities(gravityEntity, target,
                        (gravityLevel + 1) * 3);
            }
        }
    }

    public static void applyGravityFromOffHand(LivingEntity gravityEntity, LivingEntity target) {
        int gravityLevel = EnchantmentHelper.getLevel(EnchantsRegistry.GRAVITY, gravityEntity.getOffHandStack());
        if (gravityLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.GRAVITY))) {
                AOEHelper.pullInNearbyEntities(gravityEntity, target,
                        (gravityLevel + 1) * 3);
            }
        }
    }

    //mcdw$onApplyDamageTail
    public static void echoDamage(LivingEntity echoEntity, LivingEntity target, float amount) {
        int echoLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ECHO, echoEntity.getMainHandStack());
        if (echoLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.ECHO) + (15 * echoLevel))) {
                AOEHelper.causeEchoAttack(echoEntity, target,
                        3.0f,
                        echoLevel, amount);
                CleanlinessHelper.playCenteredSound(echoEntity, McdwSoundEvents.ECHO_SOUND_EVENT, 0.5F, 1.0F);
            }
        }
    }
    public static void echoDamageFromOffHand(LivingEntity echoEntity, LivingEntity target, float amount) {
        int echoLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ECHO, echoEntity.getOffHandStack());
        if (echoLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.ECHO) + (15 * echoLevel))) {
                AOEHelper.causeEchoAttack(echoEntity, target,
                        3.0f,
                        echoLevel, amount);
                CleanlinessHelper.playCenteredSound(echoEntity, McdwSoundEvents.ECHO_SOUND_EVENT, 0.5F, 1.0F);
            }
        }
    }

    //mcdw$onDeath
    public static void explodingDamage(LivingEntity exploderEntity, LivingEntity target) {
        int explodingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.EXPLODING, exploderEntity.getMainHandStack());
        if (explodingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.EXPLODING))) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.5F, 1.0F);
                AOECloudHelper.spawnExplosionCloud(exploderEntity, target, 3.0F);

                float explodingDamage = target.getMaxHealth() * 0.2f * explodingLevel;
                AOEHelper.causeExplosionAttack(exploderEntity, target, explodingDamage, 3.0F);
            }
        }
    }

    public static void explodingDamageFromOffHand(LivingEntity exploderEntity, LivingEntity target) {
        int explodingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.EXPLODING, exploderEntity.getOffHandStack());
        if (explodingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.EXPLODING))) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.5F, 1.0F);
                AOECloudHelper.spawnExplosionCloud(exploderEntity, target, 3.0F);

                float explodingDamage = target.getMaxHealth() * 0.2f * explodingLevel;
                AOEHelper.causeExplosionAttack(exploderEntity, target, explodingDamage, 3.0F);
            }
        }
    }

    public static void applyRampaging(LivingEntity rampagingEntity) {
        int rampagingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.RAMPAGING, rampagingEntity.getMainHandStack());
        if (rampagingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.RAMPAGING))) {
                StatusEffectInstance rampage = new StatusEffectInstance(StatusEffects.HASTE, rampagingLevel * 100, 2,
                        false, false);
                rampagingEntity.addStatusEffect(rampage);
            }
        }
    }

    public static void applyRampagingFromOffHand(LivingEntity rampagingEntity) {
        int rampagingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.RAMPAGING, rampagingEntity.getOffHandStack());
        if (rampagingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.RAMPAGING))) {
                StatusEffectInstance rampage = new StatusEffectInstance(StatusEffects.HASTE, rampagingLevel * 100, 2,
                        false, false);
                rampagingEntity.addStatusEffect(rampage);
            }
        }
    }

    public static void applyGuardingStrike(LivingEntity guardingEntity) {
        int guardingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.GUARDING_STRIKE, guardingEntity.getMainHandStack());
        if (guardingLevel > 0) {

            StatusEffectInstance shield = new StatusEffectInstance(StatusEffects.RESISTANCE, 20 + (20 * guardingLevel), 2);
            guardingEntity.addStatusEffect(shield);
        }
    }

    public static void applyGuardingStrikeFromOffHand(LivingEntity guardingEntity) {
        int guardingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.GUARDING_STRIKE, guardingEntity.getOffHandStack());
        if (guardingLevel > 0) {

            StatusEffectInstance shield = new StatusEffectInstance(StatusEffects.RESISTANCE, 20 + (20 * guardingLevel), 2);
            guardingEntity.addStatusEffect(shield);
        }
    }

    public static void applyLeeching(LivingEntity leechingEntity, LivingEntity target) {
        int leechingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.LEECHING, leechingEntity.getMainHandStack());
        if (leechingLevel > 0) {
            if (leechingEntity.getHealth() < leechingEntity.getMaxHealth()) {
                float healthRegained = (0.2F + 0.2F * leechingLevel) * target.getMaxHealth();
                leechingEntity.heal(healthRegained);
            }
        }
    }

    public static void applyLeechingFromOffHand(LivingEntity leechingEntity, LivingEntity target) {
        int leechingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.LEECHING, leechingEntity.getOffHandStack());
        if (leechingLevel > 0) {
            if (leechingEntity.getHealth() < leechingEntity.getMaxHealth()) {
                float healthRegained = (0.2F + 0.2F * leechingLevel) * target.getMaxHealth();
                leechingEntity.heal(healthRegained);
            }
        }
    }
    public static void applyRefreshment(PlayerEntity refreshingEntity){
        int refreshmentLevel = EnchantmentHelper.getLevel(EnchantsRegistry.REFRESHMENT, refreshingEntity.getMainHandStack());
        if (refreshmentLevel > 0) {
            ItemStack healthPotion = PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.HEALING);
            InventoryHelper.mcdw$systematicReplacePotions(refreshingEntity, Items.GLASS_BOTTLE, healthPotion, refreshmentLevel);
        }
    }

    public static void applyRefreshmentFromOffHand(PlayerEntity refreshingEntity){
        int refreshmentLevel = EnchantmentHelper.getLevel(EnchantsRegistry.REFRESHMENT, refreshingEntity.getOffHandStack());
        if (refreshmentLevel > 0) {
            ItemStack healthPotion = PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.HEALING);
            InventoryHelper.mcdw$systematicReplacePotions(refreshingEntity, Items.GLASS_BOTTLE, healthPotion, refreshmentLevel);
        }
    }

    /* PersistentProjectileEntityMixin */
    // mcdw$onEntityHitTail
    public static void applyChainReaction(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        int chainReactionLevel = ((IMcdwEnchantedArrow) ppe).getChainReactionLevel();
        if (chainReactionLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.CHAIN_REACTION) * chainReactionLevel)){
                ProjectileEffectHelper.fireChainReactionProjectiles(target.getEntityWorld(), target, shooter,
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
                AOEHelper.causeExplosionAttack(shooter, target, fuseShotDamage, 3.0F);
            }
        }
    }

    public static void applyGravityShot(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        int gravityLevel = ((IMcdwEnchantedArrow) ppe).getGravityLevel();
        if (gravityLevel > 0) {

            if (CleanlinessHelper.percentToOccur(CONFIG_CHANCE.get(EnchantmentsID.GRAVITY))) {
                AOEHelper.pullInNearbyEntities(shooter, target, (gravityLevel + 1) * 3);
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
                ProjectileEffectHelper.ricochetArrowTowardsOtherEntity(shooter, target, 10, damageMultiplier);
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
            ProjectileEffectHelper.fireBurstBowstringArrows(jumpingEntity, 16, 0.4F, burstBowstringLevel);
        }
    }
    public static void handleAddDynamoEffect(PlayerEntity playerEntity) {
        ItemStack mainHandStack = playerEntity.getMainHandStack();
        if (McdwEnchantmentHelper.hasEnchantment(mainHandStack, EnchantsRegistry.DYNAMO)) {
            StatusEffectInstance dynamoInstance = playerEntity.getStatusEffect(StatusEffectsRegistry.DYNAMO);
            int i = 1;
            if (dynamoInstance != null) {
                i += dynamoInstance.getAmplifier();
            } else {
                --i;
            }
            i = MathHelper.clamp(i, 0, Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.dynamoStackCap.get(EnchantStatsID.DYNAMO_STACK_CAP));
            StatusEffectInstance dynamoUpdateInstance = new StatusEffectInstance(StatusEffectsRegistry.DYNAMO, 120000, i, false, false, true);
            playerEntity.addStatusEffect(dynamoUpdateInstance);
        }
    }

    public static void handleAddDynamoEffectFromOffHand(PlayerEntity playerEntity) {
        ItemStack offHandStack = playerEntity.getOffHandStack();
        if (McdwEnchantmentHelper.hasEnchantment(offHandStack, EnchantsRegistry.DYNAMO)) {
            StatusEffectInstance dynamoInstance = playerEntity.getStatusEffect(StatusEffectsRegistry.DYNAMO);
            int i = 1;
            if (dynamoInstance != null) {
                i += dynamoInstance.getAmplifier();
            } else {
                --i;
            }
            i = MathHelper.clamp(i, 0, Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.dynamoStackCap.get(EnchantStatsID.DYNAMO_STACK_CAP));
            StatusEffectInstance dynamoUpdateInstance = new StatusEffectInstance(StatusEffectsRegistry.DYNAMO, 120000, i, false, false, true);
            playerEntity.addStatusEffect(dynamoUpdateInstance);
        }
    }

    // Goal Effects
    public static void sendIntoWildRage(MobEntity mobEntity) {
        ((MobEntityAccessor)mobEntity).targetSelector().add(0, new WildRageAttackGoal(mobEntity));
    }

}