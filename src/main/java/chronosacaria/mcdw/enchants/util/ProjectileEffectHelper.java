package chronosacaria.mcdw.enchants.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.util.math.Vector3d;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

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
