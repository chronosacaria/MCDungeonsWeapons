package chronosacaria.mcdw.api.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProjectileEffectHelper {

    public static void ricochetArrowTowardsOtherEntity(LivingEntity shooter, LivingEntity damagedEntity, int distance, double bonusShotDamageMultiplier) {
        List<LivingEntity> nearbyEntities = getSecondaryTargets(damagedEntity, distance);
        if (!nearbyEntities.isEmpty()) {
            PersistentProjectileEntity arrowEntity = createProjectileEntityTowards(damagedEntity, nearbyEntities.get(0));
            arrowEntity.setDamage(arrowEntity.getDamage() * bonusShotDamageMultiplier);
            arrowEntity.setOwner(shooter);
            damagedEntity.world.spawnEntity(arrowEntity);
        }
    }

    public static void fireBonusShotTowardsOtherEntity(LivingEntity attacker, int distance, double bonusShotDamageMultiplier) {
        List<LivingEntity> nearbyEntities = getSecondaryTargets(attacker, distance);
        if (!nearbyEntities.isEmpty()) {
            PersistentProjectileEntity arrowEntity = createProjectileEntityTowards(attacker, nearbyEntities.get(0));
            arrowEntity.setDamage(arrowEntity.getDamage() * bonusShotDamageMultiplier);
            attacker.world.spawnEntity(arrowEntity);
        }
    }

    private static PersistentProjectileEntity createChainReactionProjectile(World world, LivingEntity attacker, ItemStack ammoStack) {
        ArrowItem arrowItem = (ArrowItem) (ammoStack.getItem() instanceof ArrowItem ? ammoStack.getItem() : Items.ARROW);
        PersistentProjectileEntity abstractArrowEntity = arrowItem.createArrow(world, ammoStack, attacker);
        if (attacker instanceof PlayerEntity) {
            abstractArrowEntity.setCritical(true);
        }

        abstractArrowEntity.setSound(SoundEvents.ITEM_CROSSBOW_HIT);
        abstractArrowEntity.setShotFromCrossbow(true);
        return abstractArrowEntity;
    }

    public static void fireChainReactionProjectiles(World world, LivingEntity attacker, LivingEntity chainReactionProjectileSource, float v,
                                                    float v1) {
        for (int i = 0 ; i < 4 ; i++)
            fireChainReactionProjectileFromTarget(world, attacker, chainReactionProjectileSource, new ItemStack(Items.ARROW), v, v1, -135.0F + (90.0f * i));
    }

    private static void fireChainReactionProjectileFromTarget(World world, LivingEntity attacker, LivingEntity target
            , ItemStack projectileStack, float v1, float v2, float centerOffset) {
        if (!world.isClient) {
            PersistentProjectileEntity projectile;
            projectile = createChainReactionProjectile(world, attacker, projectileStack);
            projectile.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
            Vec3d upVector = target.getOppositeRotationVector(1.0F);
            Quaternion quaternion = new Quaternion(new Vec3f(upVector), centerOffset, true);
            Vec3d lookVector = target.getRotationVec(1.0F);
            Vec3f vector3f = new Vec3f(lookVector);
            vector3f.rotate(quaternion);
            projectile.setVelocity(vector3f.getX(), vector3f.getY(), vector3f.getZ(), v1, v2);
            projectile.setOwner(target);
            world.spawnEntity(projectile);
        }
    }

    public static void fireBurstBowstringArrows(LivingEntity attacker, int distance, double damageMultiplier, int numberOfArrows) {
        if (attacker instanceof PlayerEntity attackingPlayer) {
            int availableArrows = Math.min(InventoryHelper.mcdw$countItem(attackingPlayer, Items.ARROW), numberOfArrows);
            if (availableArrows < 1) return; //Avoid area lookup

            List<LivingEntity> nearbyEntities = getSecondaryTargets(attacker, distance);
            for (int i = 0; i < Math.min(availableArrows, nearbyEntities.size()); i++) {
                LivingEntity target = nearbyEntities.get(i);
                PersistentProjectileEntity arrowEntity = createProjectileEntityTowards(attacker, target);
                arrowEntity.setDamage(arrowEntity.getDamage() * damageMultiplier);
                attacker.world.spawnEntity(arrowEntity);

                if (!attackingPlayer.isCreative()) {
                    InventoryHelper.mcdw$deductAmountOfItem(attackingPlayer, Items.ARROW, 1);
                }
            }
        }
    }

    private static List<LivingEntity> getSecondaryTargets(LivingEntity source, double distance) {
        World world = source.getEntityWorld();
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(source.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.isAoeTarget(nearbyEntity, source, source));
        if (nearbyEntities.size() < 2) return Collections.emptyList();

        nearbyEntities.sort(Comparator.comparingDouble(livingEntity -> livingEntity.squaredDistanceTo(source)));
        return nearbyEntities;
    }

    public static PersistentProjectileEntity createProjectileEntityTowards(LivingEntity source, LivingEntity target) {
        World world = source.getEntityWorld();
        ArrowItem arrowItem = (ArrowItem) Items.ARROW;
        PersistentProjectileEntity arrowEntity = arrowItem.createArrow(world, new ItemStack(Items.ARROW), source);
        // borrowed from AbstractSkeletonEntity
        double towardsX = target.getX() - source.getX();
        double towardsZ = target.getZ() - source.getZ();
        double euclideanDist = MathHelper.hypot(towardsX, towardsZ);
        double towardsY = target.getBodyY(0.3333333333333333D) - arrowEntity.getY() + euclideanDist * 0.2d;
        setProjectileTowards(arrowEntity, towardsX, towardsY, towardsZ);
        arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
        return arrowEntity;
    }

    public static void setProjectileTowards(ProjectileEntity projectileEntity, double x, double y
            , double z) {
        Vec3d vec3d = new Vec3d(x, y, z).normalize();
        projectileEntity.setVelocity(vec3d);
        float f = MathHelper.sqrt((float) projectileEntity.squaredDistanceTo(vec3d));
        //noinspection SuspiciousNameCombination
        projectileEntity.setYaw((float) (MathHelper.atan2(vec3d.x, vec3d.z) * (180d / Math.PI)));
        projectileEntity.setPitch((float) (MathHelper.atan2(vec3d.y, f) * (180d / Math.PI)));
        projectileEntity.prevYaw = projectileEntity.getYaw();
        projectileEntity.prevPitch = projectileEntity.getPitch();
    }
}