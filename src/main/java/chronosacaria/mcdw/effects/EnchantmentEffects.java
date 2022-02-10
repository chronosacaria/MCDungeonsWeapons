package chronosacaria.mcdw.effects;

import chronosacaria.mcdw.api.util.*;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.sounds.McdwSoundEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;

public class EnchantmentEffects {

    /* LivingEntityPlayerEntityMixin */

    //mcdw$damageModifiers
    public static float ambushDamage (LivingEntity ambushingEntity, LivingEntity ambushee) {
        int ambushLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(ambushingEntity, EnchantsRegistry.AMBUSH);
        if (ambushLevel > 0) {

            if (ambushingEntity.isInvisible() && ambushingEntity.isSneaking()) {

                CleanlinessHelper.playCenteredSound(ambushee, SoundEvents.BLOCK_POINTED_DRIPSTONE_LAND, 0.5F, 1.0F);
                return 1 + (ambushLevel * 0.15f);
            }
        }
        return 1;
    }

    public static float criticalHitDamage (LivingEntity crittingEntity, LivingEntity target) {

        int criticalHitLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(crittingEntity, EnchantsRegistry.CRITICAL_HIT);
        if (criticalHitLevel > 0) {

            if (CleanlinessHelper.percentToOccur(10 + (10 * criticalHitLevel))) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, 0.5F, 1.0F);
                return 1.5f;
            }
        }
        return 1f;
    }

    public static float enigmaResonatorDamage (LivingEntity resonatingEntity, LivingEntity target) {
        int resonatorLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(resonatingEntity, EnchantsRegistry.ENIGMA_RESONATOR);
        if (resonatorLevel > 0) {

            int numSouls = ((PlayerEntity) resonatingEntity).experienceLevel;
            if (numSouls > 0) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.PARTICLE_SOUL_ESCAPE, 0.5F, 1.0F);

                float attackDamage = (float) resonatingEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                float extraDamageMultiplier = (float) (Math.log(numSouls * resonatorLevel))/1.75F;
                float getExtraDamage = attackDamage * extraDamageMultiplier;

                return MathHelper.clamp(getExtraDamage, 1f, attackDamage * extraDamageMultiplier * (numSouls + 1));
            }
        }
        return 1f;
    }

    public static float voidStrikeDamage (LivingEntity voidEntity, LivingEntity target) {
        int voidlevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(voidEntity, EnchantsRegistry.VOID_STRIKE);
        if (voidlevel > 0) {

            if (CleanlinessHelper.percentToOccur(15 + (5 * voidlevel))) {
                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.5F, 1.0F);
                return 2.0F * voidlevel;
            }
        }
        return 1f;
    }

    public static float committedDamage (LivingEntity committedEntity, LivingEntity target) {

        int committedLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(committedEntity, EnchantsRegistry.COMMITTED);
        if (committedLevel > 0) {

            float getTargetRemainingHealth = MathHelper.clamp(target.getHealth() / target.getMaxHealth(), 0, 1);
            float attributeDamage = (float) committedEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            float committedMultiplier = 0.2F * committedLevel;

            if (CleanlinessHelper.percentToOccur(30)) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.5F, 1.0F);
                return MathHelper.clamp(attributeDamage * (1 - getTargetRemainingHealth) * committedMultiplier, 1, 3 * committedLevel);
            }
        }
        return 0;
    }

    //mcdw$onApplyDamageHead
    public static void applyCharge (LivingEntity chargingEntity) {
        int chargeLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(chargingEntity, EnchantsRegistry.CHARGE);
        if (chargeLevel > 0) {

            if (CleanlinessHelper.percentToOccur(10))
                chargingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, chargeLevel * 20, 4));
        }
    }

    public static void applyPoisoning (LivingEntity poisoningEntity, LivingEntity target) {
        int poisoningLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(poisoningEntity, EnchantsRegistry.JUNGLE_POISON);
        if (poisoningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(30)) {
                StatusEffectInstance poison = new StatusEffectInstance(StatusEffects.POISON, 60, poisoningLevel - 1);
                target.addStatusEffect(poison);
            }
        }
    }

    public static void applyPoisonCloud (LivingEntity poisoningEntity, LivingEntity target) {
        int poisoningLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(poisoningEntity, EnchantsRegistry.POISON_CLOUD);
        if (poisoningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(30)) {
                AOECloudHelper.spawnPoisonCloud(poisoningEntity, target,
                        poisoningLevel - 1);
            }
        }
    }

    public static void applyRadianceCloud (LivingEntity radiantEntity) {
        int radianceLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(radiantEntity, EnchantsRegistry.RADIANCE);
        if (radianceLevel > 0) {

            if (CleanlinessHelper.percentToOccur(20)) {
                AOECloudHelper.spawnRegenCloud(radiantEntity,
                        radianceLevel - 1);
            }
        }
    }

    public static void applyShockwave (LivingEntity shockwaveEntity, LivingEntity target, float amount) {
        int shockwaveLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(shockwaveEntity, EnchantsRegistry.SHOCKWAVE);
        if (shockwaveLevel > 0) {

            if (CleanlinessHelper.percentToOccur(10 + (15 * shockwaveLevel))) {
                AOEHelper.causeShockwaveAttack(shockwaveEntity, target,
                        3.0f, amount);

                target.world.playSound(null,
                        target.getX(),
                        target.getY(),
                        target.getZ(),
                        SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.WEATHER,
                        0.5F, 1.0F);
            }
        }
    }

    public static void applyStunning (LivingEntity stunningEntity, LivingEntity target) {
        int stunningLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(stunningEntity, EnchantsRegistry.STUNNING);
        if (stunningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(20 + (15 * stunningLevel))) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 10));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 60, 1));
            }
        }
    }

    public static void applyThundering (LivingEntity thunderingEntity, float amount) {
        int thunderingLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(thunderingEntity, EnchantsRegistry.THUNDERING);
        if (thunderingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(20)) {
                AOEHelper.electrocuteNearbyEnemies(thunderingEntity,
                        5 * thunderingLevel, amount,
                        Integer.MAX_VALUE);
            }
        }
    }

    public static void applyWeakeningCloud (LivingEntity weakeningEntity, LivingEntity target) {
        int weakeningLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(weakeningEntity, EnchantsRegistry.WEAKENING);
        if (weakeningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(30)) {
                AOECloudHelper.spawnWeakeningCloud(weakeningEntity, target,
                        weakeningLevel - 1);
            }
        }
    }

    public static void applySwirling (LivingEntity swirlingEntity, LivingEntity target, float amount) {
        int swirlingLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(swirlingEntity, EnchantsRegistry.SWIRLING);
        if (swirlingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(10 + (15 * swirlingLevel))) {
                AOEHelper.causeSwirlingAttack(swirlingEntity, swirlingEntity,
                        1.5f, amount);

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 0.5F, 1.0F);
            }
        }
    }

    public static void applyGravity (LivingEntity gravityEntity, LivingEntity target) {
        int gravityLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(gravityEntity, EnchantsRegistry.CHARGE);
        if (gravityLevel > 0) {

            if (CleanlinessHelper.percentToOccur(30)) {
                AOEHelper.pullInNearbyEntities(gravityEntity, target,
                        (gravityLevel + 1) * 3);
            }
        }
    }

    public static void applyTempoTheft (LivingEntity tempoEntity, LivingEntity target) {
        int tempoLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(tempoEntity, EnchantsRegistry.TEMPO_THEFT);
        if (tempoLevel > 0) {

            AbilityHelper.stealSpeedFromTarget(tempoEntity, target, tempoLevel);
        }
    }

    //mcdw$onApplyDamageTail
    public static void echoDamage (LivingEntity echoEntity, LivingEntity target, float amount) {
        int echoLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(echoEntity, EnchantsRegistry.CRITICAL_HIT);
        if (echoLevel > 0) {

            if (CleanlinessHelper.percentToOccur(10 + (15 * echoLevel))) {
                AOEHelper.causeEchoAttack(echoEntity, target,
                        3.0f,
                        echoLevel, amount);
                CleanlinessHelper.playCenteredSound(echoEntity, McdwSoundEvents.ECHO_SOUND_EVENT, 0.5F, 1.0F);
            }
        }
    }

    //mcdw$onDeath
    public static void explodingDamage (LivingEntity exploderEntity, LivingEntity target) {
        int explodingLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(exploderEntity, EnchantsRegistry.EXPLODING);
        if (explodingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(20)) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.5F, 1.0F);
                AOECloudHelper.spawnExplosionCloud(exploderEntity, target, 3.0F);

                float explodingDamage = target.getMaxHealth() * 0.2f * explodingLevel;
                AOEHelper.causeExplosionAttack(exploderEntity, target, explodingDamage, 3.0F);
            }
        }
    }

    public static void applyRampaging (LivingEntity rampagingEntity) {
        int rampagingLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(rampagingEntity, EnchantsRegistry.RAMPAGING);
        if (rampagingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(10)) {
                StatusEffectInstance rampage = new StatusEffectInstance(StatusEffects.HASTE, rampagingLevel * 100, 2,
                        false, false);
                rampagingEntity.addStatusEffect(rampage);
            }
        }
    }

    public static void applyGuardingStrike (LivingEntity guardingEntity) {
        int guardingLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(guardingEntity, EnchantsRegistry.GUARDING_STRIKE);
        if (guardingLevel > 0) {

            StatusEffectInstance shield = new StatusEffectInstance(StatusEffects.RESISTANCE, 20 + (20 * guardingLevel), 2);
            guardingEntity.addStatusEffect(shield);
        }
    }
}
