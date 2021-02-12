package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.enchants.lists.MeleeRangedEnchantmentList;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ProjectileEffectHelper {

    public static void riochetArrowTowardsOtherEntity (LivingEntity user, int distance,
                                                       double bonusShotDamageMultiplier, float arrowVelocity){
        World world = user.getEntityWorld();
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(user.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canApplyToEnemy(user, nearbyEntity));
        if(nearbyEntities.size() < 2) return;
        nearbyEntities.sort(Comparator.comparingDouble(livingEntity -> livingEntity.squaredDistanceTo(user)));
        LivingEntity target = nearbyEntities.get(0);
        if (target != null){
            ArrowItem arrowItem = (ArrowItem)((ArrowItem)(Items.ARROW));
            PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, new ItemStack(Items.ARROW),user);
            persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() * bonusShotDamageMultiplier);

            double towardsX = target.getX() - user.getX();
            double towardsZ = target.getZ() - user.getZ();
            double euclideanDistance = (double) MathHelper.sqrt(towardsX * towardsX + towardsZ * towardsZ);
            double towardsY =
                    target.getBodyY(0.3333333333333333D) - persistentProjectileEntity.getY() + euclideanDistance * (double)0.2F;
            persistentProjectileEntity.setProperties(user, user.pitch, user.yaw, 0.0f, arrowVelocity * 3.0f, 1.0f);
            setProjectileTowards(persistentProjectileEntity, towardsX, towardsY, towardsZ, 0);

            persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
            //persistentProjectileEntity.addScoreboardTag("BonusProjectile");
            user.world.spawnEntity(persistentProjectileEntity);
        }
    }

    public static void fireBonusShotTowardsOtherEntity(LivingEntity attacker, int distance, double bonusShotDamageMultiplier, float arrowVelocity){
        World world = attacker.getEntityWorld();
        //boolean nullListFlag = arrowEntity.hitEntities == null;
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(attacker.getX() - distance, attacker.getY() - distance, attacker.getZ() - distance,
                attacker.getX() + distance, attacker.getY() + distance, attacker.getZ() + distance), (nearbyEntity) -> AbilityHelper.canApplyToEnemy(attacker, nearbyEntity));
        if(nearbyEntities.size() < 2) return;
        nearbyEntities.sort(Comparator.comparingDouble(livingEntity -> livingEntity.squaredDistanceTo(attacker)));
        LivingEntity target =  nearbyEntities.get(0);
        if(target != null){
            ArrowItem arrowItem = (ArrowItem)((ArrowItem)(Items.ARROW));
            PersistentProjectileEntity arrowEntity = arrowItem.createArrow(world, new ItemStack(Items.ARROW), attacker);
            arrowEntity.setDamage(arrowEntity.getDamage() * bonusShotDamageMultiplier);
            // borrowed from AbstractSkeletonEntity
            double towardsX = target.getX() - attacker.getX();
            double towardsZ = target.getZ() - attacker.getZ();
            double euclideanDist = (double)MathHelper.sqrt(towardsX * towardsX + towardsZ * towardsZ);
            double towardsY = target.getBodyY(0.3333333333333333D) - arrowEntity.getY() + euclideanDist * (double)0.2F;
            arrowEntity.setProperties(attacker, attacker.pitch, attacker.yaw, 0.0F, arrowVelocity * 3.0F, 1.0F);
            setProjectileTowards(arrowEntity, towardsX, towardsY, towardsZ, 0);
            //
            arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
            arrowEntity.addScoreboardTag("BonusProjectile");
            attacker.world.spawnEntity(arrowEntity);
        }
    }

    public static void fireSnowballAtNearbyEnemy(LivingEntity attacker, int distance){
        World world = attacker.getEntityWorld();
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(attacker.getX() - distance, attacker.getY() - distance, attacker.getZ() - distance,
                attacker.getX() + distance, attacker.getY() + distance, attacker.getZ() + distance), (nearbyEntity) -> AbilityHelper.canApplyToEnemy(attacker, nearbyEntity));
        if(nearbyEntities.size() < 2) return;
        nearbyEntities.sort(Comparator.comparingDouble(livingEntity -> livingEntity.squaredDistanceTo(attacker)));
        LivingEntity target =  nearbyEntities.get(0);
        if(target != null){
            SnowballEntity snowballEntity = new SnowballEntity(world, attacker);
            // borrowed from AbstractSkeletonEntity
            double towardsX = target.getX() - attacker.getX();
            double towardsZ = target.getZ() - attacker.getZ();
            double euclideanDist = (double)MathHelper.sqrt(towardsX * towardsX + towardsZ * towardsZ);
            double towardsY =
                    target.getBodyY(0.3333333333333333D) - snowballEntity.getY() + euclideanDist * (double)0.2F;
            snowballEntity.setProperties(attacker, attacker.pitch, attacker.yaw, 0.0F, 1.5F, 1.0F);
            setProjectileTowards(snowballEntity, towardsX, towardsY, towardsZ, 0);
            //
            attacker.world.spawnEntity(snowballEntity);
        }
    }

    public static void ricochetArrowLikeShield(PersistentProjectileEntity arrowEntity, LivingEntity entity){
        arrowEntity.setVelocity(arrowEntity.getVelocity().multiply(-0.1D));
        arrowEntity.yaw += 180.0F;
        arrowEntity.prevYaw += 180.0F;
        if (!arrowEntity.world.isClient && arrowEntity.getVelocity().lengthSquared() < 1.0E-7D) {
            if (arrowEntity.pickupType == PersistentProjectileEntity.PickupPermission.ALLOWED) {
                arrowEntity.dropStack(new ItemStack(Items.ARROW), 0.1F);
            }
            arrowEntity.remove();
        }
    }

    private static PersistentProjectileEntity createChainReactionProjectile(World world, LivingEntity attacker, ItemStack ammoStack,
                                                                            PersistentProjectileEntity originalArrow) {
        ArrowItem arrowItem = (ArrowItem)((ArrowItem)(ammoStack.getItem() instanceof ArrowItem ? ammoStack.getItem() : Items.ARROW));
        PersistentProjectileEntity abstractArrowEntity = arrowItem.createArrow(world, ammoStack, attacker);
        if (attacker instanceof PlayerEntity) {
            abstractArrowEntity.setCritical(true);
        }

        abstractArrowEntity.setSound(SoundEvents.ITEM_CROSSBOW_HIT);
        abstractArrowEntity.setShotFromCrossbow(true);
        abstractArrowEntity.addScoreboardTag("ChainReactionProjectile");
        Set<String> originalArrowTags = originalArrow.getScoreboardTags();
        for(String tag : originalArrowTags){
            abstractArrowEntity.addScoreboardTag(tag);
        }
        return abstractArrowEntity;
    }

    public static void fireChainReactionProjectiles(World world, LivingEntity attacker, LivingEntity victim, float v,
                                                    float v1, PersistentProjectileEntity originalArrow) {
        for(int i = 0; i < 4; ++i) {
            ItemStack currentProjectile = new ItemStack(Items.ARROW);
            if (!currentProjectile.isEmpty()) {
                if (i == 0) {
                    fireChainReactionProjectileFromVictim(world, attacker, victim,  currentProjectile, v, v1, 45.0F,originalArrow);
                } else if (i == 1) {
                    fireChainReactionProjectileFromVictim(world, attacker,  victim, currentProjectile, v, v1, -45.0F, originalArrow);
                } else if (i == 2) {
                    fireChainReactionProjectileFromVictim(world, attacker,  victim, currentProjectile, v, v1, 135.0F, originalArrow);
                } else {
                    fireChainReactionProjectileFromVictim(world, attacker,  victim, currentProjectile, v, v1, -135.0F, originalArrow);
                }
            }
        }
    }

    private static void fireChainReactionProjectileFromVictim(World world, LivingEntity attacker, LivingEntity victim
            , ItemStack projectileStack, float v1, float v2, float centerOffset, PersistentProjectileEntity originalArrow) {
        if (!world.isClient) {
            PersistentProjectileEntity projectile;
            projectile = createChainReactionProjectile(world, attacker, projectileStack, originalArrow);
            projectile.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
            Vec3d upVector = victim.getOppositeRotationVector(1.0F);
            Quaternion quaternion = new Quaternion(new Vector3f(upVector), centerOffset, true);
            Vec3d lookVector = victim.getRotationVec(1.0F);
            Vector3f vector3f = new Vector3f(lookVector);
            vector3f.rotate(quaternion);
            projectile.setVelocity((double)vector3f.getX(), (double)vector3f.getY(), (double)vector3f.getZ(), v1, v2);
            world.spawnEntity(projectile);
        }
    }

    public static boolean soulsCriticalBoost(PlayerEntity attacker, ItemStack mainhand){
        int numSouls = Math.min(attacker.experienceLevel, 50);
        //boolean uniqueWeaponFlag = mainhand.getItem() == DeferredItemInit.FERAL_SOUL_CROSSBOW.get()
                //|| mainhand.getItem() == DeferredItemInit.SOUL_HUNTER_CROSSBOW.get();
        if(McdwEnchantmentHelper.hasEnchantment(mainhand, MeleeRangedEnchantmentList.ENIGMA_RESONATOR)){
            int enigmaResonatorLevel =
                    EnchantmentHelper.getLevel(MeleeRangedEnchantmentList.ENIGMA_RESONATOR, mainhand);
            float soulsCriticalBoostChanceCap;
            soulsCriticalBoostChanceCap = 0.1F + 0.05F * enigmaResonatorLevel;
            float soulsCriticalBoostRand = attacker.getRandom().nextFloat();
            return soulsCriticalBoostRand <= Math.min(numSouls / 50.0, soulsCriticalBoostChanceCap);
        }
        //if(uniqueWeaponFlag){
        //    float soulsCriticalBoostRand = attacker.getRandom().nextFloat();
        //    return soulsCriticalBoostRand <= Math.min(numSouls / 50.0, 0.15F);
        //}
        return false;
    }


    public static void setProjectileTowards(ProjectileEntity projectileEntity, double x, double y
            , double z, float inaccuracy){
        Random random = new Random();
        Vec3d vec3d = (new Vec3d(x, y, z))
                .normalize()
                .add(
                        random.nextGaussian() * (double)0.0075f * (double)inaccuracy,
                        random.nextGaussian() * (double)0.0075f * (double)inaccuracy,
                        random.nextGaussian() * (double)0.0075f * (double)inaccuracy);
        projectileEntity.setVelocity(vec3d);
        float f = MathHelper.sqrt(projectileEntity.squaredDistanceTo(vec3d));
        projectileEntity.yaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * (double)(180f / (float)Math.PI));
        projectileEntity.pitch = (float)(MathHelper.atan2(vec3d.y, f) * (double)(180f / (float)Math.PI));
        projectileEntity.prevYaw = projectileEntity.yaw;
        projectileEntity.prevPitch = projectileEntity.pitch;
    }
}