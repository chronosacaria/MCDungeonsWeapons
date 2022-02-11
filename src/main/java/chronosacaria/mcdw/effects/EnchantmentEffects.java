package chronosacaria.mcdw.effects;

import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import chronosacaria.mcdw.api.util.*;
import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.sounds.McdwSoundEvents;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EnchantmentEffects {

    /* ExperienceOrbEntityMixin */
    //mcdw$ModifyExperience
    public static int soulDevourerExperience(PlayerEntity playerEntity, int amount) {
        int mainHandLevel = EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_DEVOURER, playerEntity.getMainHandStack());
        int offHandLevel = EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_DEVOURER, playerEntity.getOffHandStack());

        int soulDevourerLevel = playerEntity.getOffHandStack().getItem() instanceof IOffhandAttack ?
                mainHandLevel + offHandLevel : mainHandLevel;

        if (soulDevourerLevel > 0)
            return (amount * (1 + (soulDevourerLevel / 3)) + Math.round(((soulDevourerLevel % 3)/3.0f) * amount));
        return amount;
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

            if (CleanlinessHelper.percentToOccur(10 + (10 * criticalHitLevel))) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, 0.5F, 1.0F);
                return 1.5f;
            }
        }
        return 1f;
    }

    public static float voidStrikeDamage(LivingEntity voidEntity, LivingEntity target) {
        int voidlevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(voidEntity, EnchantsRegistry.VOID_STRIKE);
        if (voidlevel > 0) {

            if (CleanlinessHelper.percentToOccur(15 + (5 * voidlevel))) {
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
                float attackDamage = (float) resonatingEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                float extraDamageMultiplier = (float) (Math.log(numSouls * resonatorLevel)) / 1.75F;
                float getExtraDamage = attackDamage * extraDamageMultiplier;

                return Math.max(getExtraDamage, 1f);
            }
        }
        return 1f;
    }

    public static float enigmaShotDamage(LivingEntity resonatingEntity, LivingEntity target) {
        int resonatorLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(resonatingEntity, EnchantsRegistry.ENIGMA_RESONATOR);
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

    public static float growingDamage(LivingEntity growingEntity, LivingEntity target) {
        int growingLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(growingEntity, EnchantsRegistry.GROWING);
        if (growingLevel > 0) {

            CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.5F, 1.0F);
            float damageModifier = 0.03F * growingEntity.distanceTo(target) * growingLevel;
            return MathHelper.clamp(damageModifier, 1f, growingLevel + 1f);
        }
        return 1f;
    }

    public static float voidShotDamage(LivingEntity voidEntity, LivingEntity target) {
        int voidlevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(voidEntity, EnchantsRegistry.VOID_SHOT);
        if (voidlevel > 0) {

            if (CleanlinessHelper.percentToOccur(25 + (5 * voidlevel))) {
                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.5F, 1.0F);
                return voidlevel + 1f;
            }
        }
        return 1f;
    }

    public static float committedDamage(LivingEntity committedEntity, LivingEntity target) {

        int committedLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(committedEntity, EnchantsRegistry.COMMITTED);
        if (committedLevel > 0) {

            float getTargetRemainingHealth = MathHelper.clamp(target.getHealth() / target.getMaxHealth(), 0, 1);
            float attributeDamage = (float) committedEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            float committedMultiplier = 0.2F * committedLevel;

            if (CleanlinessHelper.percentToOccur(30)) {

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.5F, 1.0F);
                float getExtraDamage = attributeDamage * (1 - getTargetRemainingHealth) * committedMultiplier;
                return Math.max(getExtraDamage, 0f);
            }
        }
        return 0f;
    }

    //mcdw$onApplyDamageHead
    public static void applyCharge(LivingEntity chargingEntity) {
        int chargeLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(chargingEntity, EnchantsRegistry.CHARGE);
        if (chargeLevel > 0) {

            if (CleanlinessHelper.percentToOccur(10))
                chargingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, chargeLevel * 20, 4));
        }
    }

    public static void applyFreezing(LivingEntity freezerEntity, LivingEntity target) {
        int freezingLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(freezerEntity, EnchantsRegistry.FREEZING);
        if (freezingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(30 + (10 * freezingLevel))) {
                AbilityHelper.causeFreezing(target, 100);
            }
        }
    }

    public static void applyPoisoning(LivingEntity poisoningEntity, LivingEntity target) {
        int poisoningLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(poisoningEntity, EnchantsRegistry.JUNGLE_POISON);
        if (poisoningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(30)) {
                StatusEffectInstance poison = new StatusEffectInstance(StatusEffects.POISON, 60, poisoningLevel - 1);
                target.addStatusEffect(poison);
            }
        }
    }

    public static void applyPoisonCloud(LivingEntity poisoningEntity, LivingEntity target) {
        int poisoningLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(poisoningEntity, EnchantsRegistry.POISON_CLOUD);
        if (poisoningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(30)) {
                AOECloudHelper.spawnPoisonCloud(poisoningEntity, target,
                        poisoningLevel - 1);
            }
        }
    }

    public static void applyRadianceCloud(LivingEntity radiantEntity) {
        int radianceLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(radiantEntity, EnchantsRegistry.RADIANCE);
        if (radianceLevel > 0) {

            if (CleanlinessHelper.percentToOccur(20)) {
                AOECloudHelper.spawnRegenCloud(radiantEntity,
                        radianceLevel - 1);
            }
        }
    }

    public static void applyShockwave(LivingEntity shockwaveEntity, LivingEntity target, float amount) {
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

    public static void applyStunning(LivingEntity stunningEntity, LivingEntity target) {
        int stunningLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(stunningEntity, EnchantsRegistry.STUNNING);
        if (stunningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(20 + (15 * stunningLevel))) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 10));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 60, 1));
            }
        }
    }

    public static void applyThundering(LivingEntity thunderingEntity, float amount) {
        int thunderingLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(thunderingEntity, EnchantsRegistry.THUNDERING);
        if (thunderingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(20)) {
                AOEHelper.electrocuteNearbyEnemies(thunderingEntity,
                        5 * thunderingLevel, amount,
                        Integer.MAX_VALUE);
            }
        }
    }

    public static void applyWeakeningCloud(LivingEntity weakeningEntity, LivingEntity target) {
        int weakeningLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(weakeningEntity, EnchantsRegistry.WEAKENING);
        if (weakeningLevel > 0) {

            if (CleanlinessHelper.percentToOccur(30)) {
                AOECloudHelper.spawnWeakeningCloud(weakeningEntity, target,
                        weakeningLevel - 1);
            }
        }
    }

    public static void applySwirling(LivingEntity swirlingEntity, LivingEntity target, float amount) {
        int swirlingLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(swirlingEntity, EnchantsRegistry.SWIRLING);
        if (swirlingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(10 + (15 * swirlingLevel))) {
                AOEHelper.causeSwirlingAttack(swirlingEntity, swirlingEntity,
                        1.5f, amount);

                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 0.5F, 1.0F);
            }
        }
    }

    public static void applyChains(LivingEntity chainingEntity, LivingEntity target) {
        int chainsLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(chainingEntity, EnchantsRegistry.CHAINS);
        if (chainsLevel > 0) {

            if (CleanlinessHelper.percentToOccur(20))
                AOEHelper.chainNearbyEntities(chainingEntity, target, 1.5F * chainsLevel, chainsLevel);
        }
    }

    public static void applyGravity(LivingEntity gravityEntity, LivingEntity target) {
        int gravityLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(gravityEntity, EnchantsRegistry.CHARGE);
        if (gravityLevel > 0) {

            if (CleanlinessHelper.percentToOccur(30)) {
                AOEHelper.pullInNearbyEntities(gravityEntity, target,
                        (gravityLevel + 1) * 3);
            }
        }
    }

    public static void applyTempoTheft(LivingEntity tempoEntity, LivingEntity target) {
        int tempoLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(tempoEntity, EnchantsRegistry.TEMPO_THEFT);
        if (tempoLevel > 0) {

            AbilityHelper.stealSpeedFromTarget(tempoEntity, target, tempoLevel);
        }
    }

    //mcdw$onApplyDamageTail
    public static void echoDamage(LivingEntity echoEntity, LivingEntity target, float amount) {
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
    public static void explodingDamage(LivingEntity exploderEntity, LivingEntity target) {
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

    public static void applyRampaging(LivingEntity rampagingEntity) {
        int rampagingLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(rampagingEntity, EnchantsRegistry.RAMPAGING);
        if (rampagingLevel > 0) {

            if (CleanlinessHelper.percentToOccur(10)) {
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

    /* PersistentProjectileEntityMixin */
    // mcdw$onEntityHitTail
    public static void applyChainReaction(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        int chainReactLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(shooter, EnchantsRegistry.CHAIN_REACTION);
        if (chainReactLevel > 0) {

            if (CleanlinessHelper.percentToOccur(10 * chainReactLevel)){
                ProjectileEffectHelper.fireChainReactionProjectiles(target.getEntityWorld(), target, shooter,
                        3.15F,
                        1.0F, ppe);
            }
        }
    }

    public static void applyCobwebShotEntity(LivingEntity shooter, LivingEntity target) {
        int cobwebLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(shooter, EnchantsRegistry.COBWEB_SHOT);
        if (cobwebLevel > 0) {

            World targetWorld = target.getEntityWorld();
            BlockPos targetPos = target.getBlockPos();
            if (targetWorld.getBlockState(targetPos) == Blocks.AIR.getDefaultState())
                targetWorld.setBlockState(targetPos, Blocks.COBWEB.getDefaultState());
        }
    }

    public static void applyFuseShot(LivingEntity shooter, LivingEntity target, PersistentProjectileEntity ppe) {
        int fuseLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(shooter, EnchantsRegistry.FUSE_SHOT);
        if (fuseLevel > 0) {

            if (CleanlinessHelper.percentToOccur(20 + (15 * fuseLevel))) {
                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.5F, 1.0F);

                AOECloudHelper.spawnExplosionCloud(shooter, target, 3.0F);
                float f = (float) ppe.getVelocity().length();
                int fuseShotDamage = MathHelper.ceil(MathHelper.clamp((double) f * ppe.getDamage(), 0.0D, 2.147483647E9D));
                AOEHelper.causeExplosionAttack(shooter, target, fuseShotDamage, 3.0F);
            }
        }
    }

    public static void applyGravityShot(LivingEntity shooter, LivingEntity target) {
        int gravityLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(shooter, EnchantsRegistry.GRAVITY);
        if (gravityLevel > 0) {

            if (CleanlinessHelper.percentToOccur(20)) {
                AOEHelper.pullInNearbyEntities(shooter, target,
                        (gravityLevel + 1) * 3);
            }
        }
    }

    public static void applyLevitationShot(LivingEntity shooter, LivingEntity target) {
        int levitationLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(shooter, EnchantsRegistry.LEVITATION_SHOT);
        if (levitationLevel > 0) {

            target.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 200 * levitationLevel));
        }
    }

    public static void applyPhantomsMark(LivingEntity shooter, LivingEntity target) {
        int phantomLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(shooter, EnchantsRegistry.PHANTOMS_MARK);
        if (phantomLevel > 0) {

            target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100 * phantomLevel));
        }
    }

    public static void applyPoisonCloudShot(LivingEntity shooter, LivingEntity target) {
        int poisonLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(shooter, EnchantsRegistry.POISON_CLOUD);
        if (poisonLevel > 0) {

            if (CleanlinessHelper.percentToOccur(20)) {
                AOECloudHelper.spawnPoisonCloud(shooter, target, poisonLevel - 1);
            }
        }
    }

    public static void applyRadianceShot(LivingEntity shooter, LivingEntity target) {
        int radianceLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(shooter, EnchantsRegistry.RADIANCE_SHOT);
        if (radianceLevel > 0) {

            if (CleanlinessHelper.percentToOccur(20))
                AOECloudHelper.spawnRegenCloudAtPos(shooter, true, target.getBlockPos(), radianceLevel - 1);
        }
    }

    public static void applyRicochet(LivingEntity shooter, LivingEntity target) {
        int ricochetLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(shooter, EnchantsRegistry.RICOCHET);
        if (ricochetLevel > 0) {

            float damageMultiplier = 0.1F + ((ricochetLevel - 1) * 0.07F);
            float arrowVelocity = McdwBow.maxBowRange;
            if (arrowVelocity > 0.1F)
                ProjectileEffectHelper.riochetArrowTowardsOtherEntity(target, 10, damageMultiplier, arrowVelocity);
        }
    }

    public static void applyRefreshmentShot(PlayerEntity shooter) {
        int refreshmentLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(shooter, EnchantsRegistry.REFRESHMENT_SHOT);
        if (refreshmentLevel > 0) {

            PlayerInventory playerInventory = shooter.getInventory();
            for (int slotID = 0; slotID < playerInventory.size(); slotID++) {
                ItemStack currentStack = playerInventory.getStack(slotID);
                if (currentStack.getItem() instanceof GlassBottleItem && currentStack.getCount() < 2) {
                    ItemStack healthPotion = PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.HEALING);
                    playerInventory.setStack(slotID, healthPotion);
                    if (healthPotion.getCount() <= refreshmentLevel) {
                        break;
                    }
                }
            }
            //TODO Figure out how to make more than one, but less than four bottles convert to potions

            // Search through the player inventory for empty bottles. Put the slots that have them in a list according to amount of bottles in ascending order.
            // Any slots that have a single empty bottle should be filled first.
            // After that, fill empty slots with Healing potions until either all slots are filled, or no more healing potions should be created.
            // Taking away empty bottles should happen recursively with a base case of the following.
            /*
            public void ~~~

            ItemStack currentStack = playerInventory.getStack(index);
            // Do stuff here

            if (currentStack.getCount < 2) {
                ItemStack healthPotion = PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.HEALING);
                playerInventory.setStack(slotID, healthPotion);
                if (healthPotion.getCount() > level){
                        ~~~(healthPotion.getCount(), nextIndex, ...)
                    }
            }
            Or something along these lines.
             */
        }
    }

    public static void applyReplenish(PlayerEntity shooter) {
        int replenishLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(shooter, EnchantsRegistry.REPLENISH);
        if (replenishLevel > 0) {

            if (CleanlinessHelper.percentToOccur(3 + (7 * replenishLevel))) {
                ItemEntity arrowDrop = new ItemEntity(shooter.world, shooter.getX(), shooter.getY(), shooter.getZ(),
                        new ItemStack(Items.ARROW));
                shooter.world.spawnEntity(arrowDrop);
            }
        }
    }

    // mcdw$onBlockHitTail
    public static void applyCobwebShotBlock(LivingEntity shooter, BlockHitResult blockHitResult) {
        int cobwebLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(shooter, EnchantsRegistry.COBWEB_SHOT);
        if (cobwebLevel > 0) {

            World shooterWorld = shooter.getEntityWorld();
            Direction side = blockHitResult.getSide();
            if (shooterWorld.getBlockState(blockHitResult.getBlockPos().offset(side)) == Blocks.AIR.getDefaultState())
                shooterWorld.setBlockState(blockHitResult.getBlockPos().offset(side), Blocks.COBWEB.getDefaultState());
        }
    }
}