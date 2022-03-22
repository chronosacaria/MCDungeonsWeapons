package chronosacaria.mcdw.effects;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IMcdwEnchantedArrow;
import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import chronosacaria.mcdw.api.util.*;
import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.BowsID;
import chronosacaria.mcdw.enums.EnchantStatsID;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.items.ItemsInit;
import chronosacaria.mcdw.sounds.McdwSoundEvents;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.GlassBottleItem;
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

import java.util.UUID;

public class EnchantmentEffects {

    /* ExperienceOrbEntityMixin */
    //mcdw$ModifyExperience
    public static int soulDevourerExperience(PlayerEntity playerEntity, int amount) {
        int mainHandLevel = EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_DEVOURER, playerEntity.getMainHandStack());
        int offHandLevel = EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_DEVOURER, playerEntity.getOffHandStack());

        int soulDevourerLevel = playerEntity.getOffHandStack().getItem() instanceof IOffhandAttack ?
                mainHandLevel + offHandLevel : mainHandLevel;

        if (soulDevourerLevel > 0)
            return Math.round((float) amount * (1 + ((float) soulDevourerLevel / 3f)));
        return amount;
    }

    public static int animaConduitExperience(PlayerEntity playerEntity, int amount) {
        int animaLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(playerEntity, EnchantsRegistry.ANIMA_CONDUIT);
        if (animaLevel > 0) {
            float missingHealth = playerEntity.getMaxHealth() - playerEntity.getHealth();
            if (missingHealth > 0) {
                float i = Math.min(AbilityHelper.getAnimaRepairAmount(amount, animaLevel), missingHealth);
                playerEntity.heal(i);
                amount -= (int) (i * 10);
                return Math.max(amount, 0);
            }
        }
        return amount;
    }

    /* LivingEntityMixin */
    //mcdw$damageModifiers
    public static float huntersPromiseDamage(PlayerEntity owner, ServerWorld serverWorld) {
        if (owner.getMainHandStack().getItem() == ItemsInit.bowItems.get(BowsID.BOW_HUNTERS_PROMISE).asItem()) {
            UUID petOwnerUUID = owner.getUuid();

            if (petOwnerUUID != null) {
                if (serverWorld.getEntity(petOwnerUUID) instanceof LivingEntity) {
                    return 1.5F;
                }
            }
        }
        return 1f;
    }

    //mcdw$onDeath
    public static void applyProspector(LivingEntity prospectingEntity, LivingEntity victim) {
        int prospectorLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(prospectingEntity, EnchantsRegistry.PROSPECTOR);
        if (prospectorLevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.PROSPECTOR) * prospectorLevel)) {
                if (victim instanceof Monster){
                    ItemEntity emeraldDrop = new ItemEntity(victim.world, victim.getX(), victim.getY(), victim.getZ(),
                            new ItemStack(Items.EMERALD, 1));
                    victim.world.spawnEntity(emeraldDrop);
                }
            }
        }
    }

    public static void applyRushdown(LivingEntity rushingEntity) {
        int rushdownLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(rushingEntity, EnchantsRegistry.RUSHDOWN);
        if (rushdownLevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.RUSHDOWN))) {
                StatusEffectInstance rushdown = new StatusEffectInstance(StatusEffects.SPEED, 100 * rushdownLevel, 2,
                        false, false);
                rushingEntity.addStatusEffect(rushdown);
            }
        }
    }

    public static void applySoulSiphon(PlayerEntity siphoningEntity) {
        int soulLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(siphoningEntity, EnchantsRegistry.SOUL_SIPHON);
        if (soulLevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.SOUL_SIPHON))) {
                siphoningEntity.addExperience(3 * soulLevel);
            }
        }
    }

    /* LivingEntityPlayerEntityMixin */
    //mcdw$damageModifiers
    public static float ambushDamage(LivingEntity ambushingEntity, LivingEntity ambushee) {
        int ambushLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(ambushingEntity, EnchantsRegistry.AMBUSH);
        if (ambushLevel > 0) {

            if (ambushingEntity.isInvisible() && ambushingEntity.isSneaking()) {

                CleanlinessHelper.playCenteredSound(ambushee, SoundEvents.BLOCK_POINTED_DRIPSTONE_LAND, 0.5F, 1.0F);
                return 1f + (0.15f * ambushLevel);
            }
        }
        return 1f;
    }

    public static float criticalHitDamage(LivingEntity crittingEntity, LivingEntity target) {

        int criticalHitLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(crittingEntity, EnchantsRegistry.CRITICAL_HIT);
        if (criticalHitLevel > 0) {

            if (CleanlinessHelper.percentToOccur(10 + (Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.CRITICAL_HIT) * criticalHitLevel))) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, 0.5F, 1.0F);
                return 1.5f;
            }
        }
        return 1f;
    }

    public static float voidStrikeDamage(LivingEntity voidEntity, LivingEntity target) {
        int voidlevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(voidEntity, EnchantsRegistry.VOID_STRIKE);
        if (voidlevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.VOID_STRIKE) + (5 * voidlevel))) {
                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.5F, 1.0F);
                return 2f * voidlevel;
            }
        }
        return 1f;
    }

    public static float enigmaResonatorDamage(LivingEntity resonatingEntity, LivingEntity target) {
        int resonatorLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(resonatingEntity, EnchantsRegistry.ENIGMA_RESONATOR);
        if (resonatorLevel > 0) {

            int numSouls = ((PlayerEntity) resonatingEntity).experienceLevel;
            if (numSouls > 0) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.PARTICLE_SOUL_ESCAPE, 0.5F, 1.0F);
                float extraDamageMultiplier =
                        (float) (Math.log(numSouls * resonatorLevel)) /
                                Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentStatsSettings.get(EnchantStatsID.ENIGMA_RESONATOR_DIVISOR);

                return Math.max(extraDamageMultiplier, 1f);
            }
        }
        return 1f;
    }

    public static float enigmaShotDamage(LivingEntity resonatingEntity, LivingEntity target, PersistentProjectileEntity ppe) {
        IMcdwEnchantedArrow enchantedArrow = (IMcdwEnchantedArrow) ppe;
        int resonatorLevel = enchantedArrow.getEnigmaResonatorLevel();
        if (resonatorLevel > 0) {

            int numSouls = ((PlayerEntity) resonatingEntity).experienceLevel;
            if (numSouls > 0) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.PARTICLE_SOUL_ESCAPE, 0.5F, 1.0F);
                float getExtraDamage = (float) (Math.log(numSouls * resonatorLevel) * 1.75F);

                return Math.max(getExtraDamage, 1f);
            }
        }
        return 1f;
    }

    public static float growingDamage(LivingEntity growingEntity, LivingEntity target, PersistentProjectileEntity ppe) {
        IMcdwEnchantedArrow enchantedArrow = (IMcdwEnchantedArrow) ppe;
        int growingLevel = enchantedArrow.getGrowingLevel();
        if (growingLevel > 0) {

            CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.5F, 1.0F);
            float damageModifier = 0.03F * growingEntity.distanceTo(target) * growingLevel;
            return MathHelper.clamp(damageModifier, 1f, growingLevel + 1f);
        }
        return 1f;
    }

    public static float voidShotDamage(LivingEntity target, PersistentProjectileEntity ppe) {
        IMcdwEnchantedArrow enchantedArrow = (IMcdwEnchantedArrow) ppe;
        int voidlevel = enchantedArrow.getVoidShotLevel();
        if (voidlevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.VOID_SHOT) + (5 * voidlevel))) {
                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.5F, 1.0F);
                return voidlevel + 1f;
            }
        }
        return 1f;
    }

    public static float committedDamage(LivingEntity committedEntity, LivingEntity target) {

        int committedLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(committedEntity, EnchantsRegistry.COMMITTED);
        if (committedLevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.COMMITTED))) {

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

    //mcdw$onApplyDamageHead
    public static void applyFreezing(LivingEntity freezerEntity, LivingEntity target) {
        int freezingLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(freezerEntity, EnchantsRegistry.FREEZING);
        if (freezingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.FREEZING) + (10 * freezingLevel))) {
                AbilityHelper.causeFreezing(target, 100);
            }
        }
    }

    public static void applyPoisoning(LivingEntity poisoningEntity, LivingEntity target) {
        int poisoningLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(poisoningEntity, EnchantsRegistry.JUNGLE_POISON);
        if (poisoningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.POISONING))) {
                StatusEffectInstance poison = new StatusEffectInstance(StatusEffects.POISON, 60, poisoningLevel - 1);
                target.addStatusEffect(poison);
            }
        }
    }

    public static void applyPoisonCloud(LivingEntity poisoningEntity, LivingEntity target) {
        int poisoningLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(poisoningEntity, EnchantsRegistry.POISON_CLOUD);
        if (poisoningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.POISON_CLOUD))) {
                AOECloudHelper.spawnStatusCloud(poisoningEntity, target, StatusEffects.POISON,
                        poisoningLevel - 1);
            }
        }
    }

    public static void applyRadianceCloud(LivingEntity radiantEntity) {
        int radianceLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(radiantEntity, EnchantsRegistry.RADIANCE);
        if (radianceLevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.RADIANCE))) {
                AOECloudHelper.spawnStatusCloud(radiantEntity, radiantEntity, StatusEffects.REGENERATION,
                        100, radianceLevel - 1);
            }
        }
    }

    public static void applyShockwave(LivingEntity shockwaveEntity, LivingEntity target, float amount) {
        int shockwaveLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(shockwaveEntity, EnchantsRegistry.SHOCKWAVE);
        if (shockwaveLevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.SHOCKWAVE) + (15 * shockwaveLevel))) {
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

    public static void applyStunning(LivingEntity stunningEntity, LivingEntity target) {
        int stunningLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(stunningEntity, EnchantsRegistry.STUNNING);
        if (stunningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.STUNNING) + (15 * stunningLevel))) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 10));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 60, 1));
            }
        }
    }

    public static void applyThundering(LivingEntity thunderingEntity, float amount) {
        int thunderingLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(thunderingEntity, EnchantsRegistry.THUNDERING);
        if (thunderingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.THUNDERING))) {
                AOEHelper.electrocuteNearbyEnemies(thunderingEntity,
                        5 * thunderingLevel, amount,
                        Integer.MAX_VALUE);
            }
        }
    }

    public static void applyWeakeningCloud(LivingEntity weakeningEntity, LivingEntity target) {
        int weakeningLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(weakeningEntity, EnchantsRegistry.WEAKENING);
        if (weakeningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.WEAKENING))) {
                AOECloudHelper.spawnStatusCloud(weakeningEntity, target, StatusEffects.WEAKNESS,
                        weakeningLevel - 1);
            }
        }
    }

    public static void applySwirling(LivingEntity swirlingEntity, LivingEntity target, float amount) {
        int swirlingLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(swirlingEntity, EnchantsRegistry.SWIRLING);
        if (swirlingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.SWIRLING) + (15 * swirlingLevel))) {
                AOEHelper.causeSwirlingAttack(swirlingEntity, swirlingEntity,
                        1.5f, amount);

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 0.5F, 1.0F);
            }
        }
    }

    public static void applyChains(LivingEntity chainingEntity, LivingEntity target) {
        int chainsLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(chainingEntity, EnchantsRegistry.CHAINS);
        if (chainsLevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.CHAINS)))
                AOEHelper.chainNearbyEntities(chainingEntity, target, 1.5F * chainsLevel, chainsLevel);
        }
    }

    public static void applyGravity(LivingEntity gravityEntity, LivingEntity target) {
        int gravityLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(gravityEntity, EnchantsRegistry.CHARGE);
        if (gravityLevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.GRAVITY))) {
                AOEHelper.pullInNearbyEntities(gravityEntity, target,
                        (gravityLevel + 1) * 3);
            }
        }
    }

    //mcdw$onApplyDamageTail
    public static void echoDamage(LivingEntity echoEntity, LivingEntity target, float amount) {
        int echoLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(echoEntity, EnchantsRegistry.ECHO);
        if (echoLevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.ECHO) + (15 * echoLevel))) {
                AOEHelper.causeEchoAttack(echoEntity, target,
                        3.0f,
                        echoLevel, amount);
                CleanlinessHelper.playCenteredSound(echoEntity, McdwSoundEvents.ECHO_SOUND_EVENT, 0.5F, 1.0F);
            }
        }
    }

    //mcdw$onDeath
    public static void explodingDamage(LivingEntity exploderEntity, LivingEntity target) {
        int explodingLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(exploderEntity, EnchantsRegistry.EXPLODING);
        if (explodingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.EXPLODING))) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.5F, 1.0F);
                AOECloudHelper.spawnExplosionCloud(exploderEntity, target, 3.0F);

                float explodingDamage = target.getMaxHealth() * 0.2f * explodingLevel;
                AOEHelper.causeExplosionAttack(exploderEntity, target, explodingDamage, 3.0F);
            }
        }
    }

    public static void applyRampaging(LivingEntity rampagingEntity) {
        int rampagingLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(rampagingEntity, EnchantsRegistry.RAMPAGING);
        if (rampagingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.RAMPAGING))) {
                StatusEffectInstance rampage = new StatusEffectInstance(StatusEffects.HASTE, rampagingLevel * 100, 2,
                        false, false);
                rampagingEntity.addStatusEffect(rampage);
            }
        }
    }

    public static void applyGuardingStrike(LivingEntity guardingEntity) {
        int guardingLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(guardingEntity, EnchantsRegistry.GUARDING_STRIKE);
        if (guardingLevel > 0) {

            StatusEffectInstance shield = new StatusEffectInstance(StatusEffects.RESISTANCE, 20 + (20 * guardingLevel), 2);
            guardingEntity.addStatusEffect(shield);
        }
    }

    public static void applyLeeching(LivingEntity leechingEntity, LivingEntity target) {
        int leechingLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(leechingEntity, EnchantsRegistry.LEECHING);
        if (leechingLevel > 0) {

            if (leechingEntity.getHealth() < leechingEntity.getMaxHealth()) {
                float healthRegained = (0.2F + 0.2F * leechingLevel) * target.getMaxHealth();
                leechingEntity.heal(healthRegained);
            }
        }
    }

    public static void applyRefreshment(PlayerEntity refreshingEntity){
        int refreshmentLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(refreshingEntity, EnchantsRegistry.REFRESHMENT);
        if (refreshmentLevel > 0) {

            PlayerInventory playerInventory = refreshingEntity.getInventory();
            for (int slotID = 0; slotID < playerInventory.size(); slotID++) {
                ItemStack currentStack = playerInventory.getStack(slotID);
                if (currentStack.getItem() instanceof GlassBottleItem && currentStack.getCount() == 1) {
                    ItemStack healthPotion = PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.HEALING);
                    playerInventory.setStack(slotID, healthPotion);
                    refreshmentLevel--;
                    if (refreshmentLevel == 0)
                        break;
                }
            }
        }
    }

    /* PersistentProjectileEntityMixin */
    // mcdw$onEntityHitTail
    public static void applyChainReaction(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        IMcdwEnchantedArrow enchantedArrow = (IMcdwEnchantedArrow) ppe;
        if (enchantedArrow.getChainReactionLevel() > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.CHAIN_REACTION) * enchantedArrow.getChainReactionLevel())){
                ProjectileEffectHelper.fireChainReactionProjectiles(target.getEntityWorld(), target, shooter,
                        3.15F,
                        1.0F, ppe);
            }
        }
    }

    public static void applyCharge(LivingEntity chargingEntity, PersistentProjectileEntity ppe) {
        IMcdwEnchantedArrow enchantedArrow = (IMcdwEnchantedArrow) ppe;
        if (enchantedArrow.getChargeLevel() > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.CHARGE)))
                chargingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, enchantedArrow.getChargeLevel() * 20,
                        4));
        }
    }

    public static void applyCobwebShotEntity(LivingEntity target, PersistentProjectileEntity ppe) {

        IMcdwEnchantedArrow enchantedArrow = (IMcdwEnchantedArrow) ppe;
        if (enchantedArrow.getCobwebShotLevel() > 0) {
            World targetWorld = target.getEntityWorld();
            BlockPos targetPos = target.getBlockPos();
            if (targetWorld.getBlockState(targetPos) == Blocks.AIR.getDefaultState())
                targetWorld.setBlockState(targetPos, Blocks.COBWEB.getDefaultState());
        }
    }

    public static void applyFuseShot(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        IMcdwEnchantedArrow enchantedArrow = (IMcdwEnchantedArrow) ppe;
        if (enchantedArrow.getFuseShotLevel() > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.FUSE_SHOT) + (15 * enchantedArrow.getFuseShotLevel()))) {
                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.5F, 1.0F);

                AOECloudHelper.spawnExplosionCloud(shooter, target, 3.0F);
                float f = (float) ppe.getVelocity().length();
                int fuseShotDamage = MathHelper.ceil(MathHelper.clamp((double) f * ppe.getDamage(), 0.0D, 2.147483647E9D));
                AOEHelper.causeExplosionAttack(shooter, target, fuseShotDamage, 3.0F);
            }
        }
    }

    public static void applyGravityShot(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        IMcdwEnchantedArrow enchantedArrow = (IMcdwEnchantedArrow) ppe;
        if (enchantedArrow.getGravityLevel() > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.GRAVITY))) {
                AOEHelper.pullInNearbyEntities(shooter, target,
                        (enchantedArrow.getGravityLevel() + 1) * 3);
            }
        }
    }

    public static void applyLevitationShot(LivingEntity target, PersistentProjectileEntity ppe) {
        IMcdwEnchantedArrow enchantedArrow = (IMcdwEnchantedArrow) ppe;
        if (enchantedArrow.getLevitationShotLevel() > 0) {

            target.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 200 * enchantedArrow.getLevitationShotLevel()));
        }
    }

    public static void applyPhantomsMark(LivingEntity target, PersistentProjectileEntity ppe) {
        IMcdwEnchantedArrow enchantedArrow = (IMcdwEnchantedArrow) ppe;
        if (enchantedArrow.getPhantomsMarkLevel() > 0) {

            target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100 * enchantedArrow.getPhantomsMarkLevel()));
        }
    }

    public static void applyPoisonCloudShot(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        IMcdwEnchantedArrow enchantedArrow = (IMcdwEnchantedArrow) ppe;
        if (enchantedArrow.getPoisonCloudLevel() > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.POISON_CLOUD))) {
                AOECloudHelper.spawnStatusCloud(shooter, target, StatusEffects.POISON,
                        enchantedArrow.getPoisonCloudLevel() - 1);
            }
        }
    }

    public static void applyRadianceShot(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        IMcdwEnchantedArrow enchantedArrow = (IMcdwEnchantedArrow) ppe;
        if (enchantedArrow.getRadianceLevel() > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.RADIANCE)))
                AOECloudHelper.spawnRegenCloudAtPos(shooter, true, target.getBlockPos(), enchantedArrow.getRadianceLevel() - 1);
        }
    }

    public static void applyRicochet(LivingEntity target, PersistentProjectileEntity ppe) {
        IMcdwEnchantedArrow enchantedArrow = (IMcdwEnchantedArrow) ppe;
        if (enchantedArrow.getRicochetLevel() > 0) {

            float damageMultiplier = 0.1F + ((enchantedArrow.getRicochetLevel() - 1) * 0.07F);
            float arrowVelocity = McdwBow.maxBowRange;
            if (arrowVelocity > 0.1F)
                ProjectileEffectHelper.riochetArrowTowardsOtherEntity(target, 10, damageMultiplier, arrowVelocity);
        }
    }

    public static void applyReplenish(PlayerEntity shooter, PersistentProjectileEntity ppe) {
        IMcdwEnchantedArrow enchantedArrow = (IMcdwEnchantedArrow) ppe;
        if (enchantedArrow.getReplenishLevel() > 0) {

            if (CleanlinessHelper.percentToOccur(Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentTriggerChanceBase.get(EnchantmentsID.REPLENISH) + (7 * enchantedArrow.getReplenishLevel()))) {
                ItemEntity arrowDrop = new ItemEntity(shooter.world, shooter.getX(), shooter.getY(), shooter.getZ(),
                        new ItemStack(Items.ARROW));
                shooter.world.spawnEntity(arrowDrop);
            }
        }
    }

    public static void applyTempoTheft(LivingEntity tempoEntity, LivingEntity target, PersistentProjectileEntity ppe) {
        IMcdwEnchantedArrow enchantedArrow = (IMcdwEnchantedArrow) ppe;
        if (enchantedArrow.getTempoTheftLevel() > 0) {

            AbilityHelper.stealSpeedFromTarget(tempoEntity, target, enchantedArrow.getTempoTheftLevel());
        }
    }

    // mcdw$onBlockHitTail
    public static void applyCobwebShotBlock(BlockHitResult blockHitResult, PersistentProjectileEntity ppe) {

        IMcdwEnchantedArrow enchantedArrow = (IMcdwEnchantedArrow) ppe;
        if (enchantedArrow.getCobwebShotLevel() > 0) {
            World ppeWorld = ppe.getEntityWorld();
            Direction side = blockHitResult.getSide();
            if (ppeWorld.getBlockState(blockHitResult.getBlockPos().offset(side)) == Blocks.AIR.getDefaultState())
                ppeWorld.setBlockState(blockHitResult.getBlockPos().offset(side), Blocks.COBWEB.getDefaultState());
        }
    }
}