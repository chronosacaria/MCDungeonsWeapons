package chronosacaria.mcdw.api.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProjectileEffectHelper {

    public static void spawnExtraArrows(LivingEntity owner, LivingEntity makeArrowFromMe, int numArrowsLimit, int distance, double bonusShotDamageMultiplier) {
        List<LivingEntity> nearbyEntities = getSecondaryTargets(makeArrowFromMe, distance);
        for (int i = 0; i < Math.min(numArrowsLimit, nearbyEntities.size()); i++) {
            PersistentProjectileEntity arrowEntity = createProjectileEntityTowards(makeArrowFromMe, nearbyEntities.get(i));
            arrowEntity.setDamage(arrowEntity.getDamage() * bonusShotDamageMultiplier);
            arrowEntity.setOwner(owner);
            makeArrowFromMe.world.spawnEntity(arrowEntity);
        }
    }

    public static PersistentProjectileEntity createAbstractArrow(LivingEntity attacker) {
        return ((ArrowItem) Items.ARROW).createArrow(attacker.getEntityWorld(), new ItemStack(Items.ARROW), attacker);
    }

    public static void fireChainReactionProjectileFromTarget(World world, LivingEntity attacker, LivingEntity target,
                                                             float v1, float v2) {
        if (!world.isClient) {
            for (int i = 0 ; i < 4 ; i++) {
                PersistentProjectileEntity projectile = createAbstractArrow(attacker);
                if (attacker instanceof PlayerEntity) {
                    projectile.setCritical(true);
                }

                projectile.setSound(SoundEvents.ITEM_CROSSBOW_HIT);
                projectile.setShotFromCrossbow(true);
                projectile.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                Vec3d upVector = target.getOppositeRotationVector(1.0F);
                Quaternion quaternion = new Quaternion(new Vec3f(upVector), -135.0F + (90.0f * i), true);
                Vec3d lookVector = target.getRotationVec(1.0F);
                Vector3f vector3f = new Vector3f(lookVector);
                vector3f.rotate(quaternion);
                projectile.setVelocity(vector3f.x(), vector3f.y(), vector3f.z(), v1, v2);
                projectile.setOwner(target);
                world.spawnEntity(projectile);
            }
        }
    }

    public static List<LivingEntity> getSecondaryTargets(LivingEntity source, double distance) {
        List<LivingEntity> nearbyEntities = AOEHelper.getAoeTargets(source, source, (float) distance);
        if (nearbyEntities.size() < 2) return Collections.emptyList();

        nearbyEntities.sort(Comparator.comparingDouble(livingEntity -> livingEntity.squaredDistanceTo(source)));
        return nearbyEntities;
    }

    public static PersistentProjectileEntity createProjectileEntityTowards(LivingEntity source, LivingEntity target) {
        PersistentProjectileEntity projectile = createAbstractArrow(source);
        // borrowed from AbstractSkeletonEntity
        double towardsX = target.getX() - source.getX();
        double towardsZ = target.getZ() - source.getZ();
        double euclideanDist = MathHelper.hypot(towardsX, towardsZ);
        double towardsY = target.getBodyY(0.3333333333333333D) - projectile.getY() + euclideanDist * 0.2d;
        setProjectileTowards(projectile, towardsX, towardsY, towardsZ);
        projectile.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
        return projectile;
    }

    public static void setProjectileTowards(ProjectileEntity projectileEntity, double x, double y, double z) {
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