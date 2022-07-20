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
                (nearbyEntity) -> AbilityHelper.isAoeTarget(nearbyEntity, user, user));
        if(nearbyEntities.size() < 2) return;
        nearbyEntities.sort(Comparator.comparingDouble(livingEntity -> livingEntity.squaredDistanceTo(user)));
        LivingEntity target = nearbyEntities.get(0);
        if (target != null){
            ArrowItem arrowItem = (ArrowItem) Items.ARROW;
            PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, new ItemStack(Items.ARROW),user);
            persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() * bonusShotDamageMultiplier);

            double towardsX = target.getX() - user.getX();
            double towardsZ = target.getZ() - user.getZ();
            double euclideanDistance = MathHelper.sqrt((float) (towardsX * towardsX + towardsZ * towardsZ));
            double towardsY =
                    target.getBodyY(0.3333333333333333D) - persistentProjectileEntity.getY() + euclideanDistance * (double)0.2F;
            persistentProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f,
                    arrowVelocity * 3.0f,
                    1.0f);
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
                        attacker.getX() + distance, attacker.getY() + distance, attacker.getZ() + distance), (nearbyEntity) -> AbilityHelper.isAoeTarget(nearbyEntity, attacker, attacker));
        if(nearbyEntities.size() < 2) return;
        nearbyEntities.sort(Comparator.comparingDouble(livingEntity -> livingEntity.squaredDistanceTo(attacker)));
        LivingEntity target =  nearbyEntities.get(0);
        if(target != null){
            ArrowItem arrowItem = (ArrowItem) Items.ARROW;
            PersistentProjectileEntity arrowEntity = arrowItem.createArrow(world, new ItemStack(Items.ARROW), attacker);
            arrowEntity.setDamage(arrowEntity.getDamage() * bonusShotDamageMultiplier);
            // borrowed from AbstractSkeletonEntity
            double towardsX = target.getX() - attacker.getX();
            double towardsZ = target.getZ() - attacker.getZ();
            double euclideanDist = MathHelper.sqrt((float) (towardsX * towardsX + towardsZ * towardsZ));
            double towardsY = target.getBodyY(0.3333333333333333D) - arrowEntity.getY() + euclideanDist * (double)0.2F;
            arrowEntity.setVelocity(attacker, attacker.getPitch(), attacker.getYaw(), 0.0F, arrowVelocity * 3.0F,
                    1.0F);
            setProjectileTowards(arrowEntity, towardsX, towardsY, towardsZ, 0);
            //
            arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
            arrowEntity.addScoreboardTag("BonusProjectile");
            attacker.world.spawnEntity(arrowEntity);
        }
    }

    private static PersistentProjectileEntity createChainReactionProjectile(World world, LivingEntity attacker, ItemStack ammoStack,
                                                                            PersistentProjectileEntity originalArrow) {
        ArrowItem arrowItem = (ArrowItem) (ammoStack.getItem() instanceof ArrowItem ? ammoStack.getItem() : Items.ARROW);
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
            Quaternion quaternion = new Quaternion(new Vec3f(upVector), centerOffset, true);
            Vec3d lookVector = victim.getRotationVec(1.0F);
            Vec3f vector3f = new Vec3f(lookVector);
            vector3f.rotate(quaternion);
            projectile.setVelocity(vector3f.getX(), vector3f.getY(), vector3f.getZ(), v1, v2);
            world.spawnEntity(projectile);
        }
    }

    public static void fireBurstBowstringArrows(LivingEntity attacker, int distance, double damageMultiplier, float arrowVelocity, int numberOfArrows) {
        World world = attacker.getEntityWorld();
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class, new Box(attacker.getX() - distance, attacker.getY() - distance, attacker.getZ() - distance,
                attacker.getX() + distance, attacker.getY() + distance, attacker.getZ() + distance), (nearbyEntity) -> AbilityHelper.isAoeTarget(nearbyEntity, attacker, attacker));
        if (nearbyEntities.size() < 2) return;
        nearbyEntities.sort(Comparator.comparingDouble(livingEntity -> livingEntity.squaredDistanceTo(attacker)));
        int amount = Math.min(numberOfArrows, nearbyEntities.size());
        if (attacker instanceof PlayerEntity attackingPlayer && InventoryHelper.mcdw$hasItem(attackingPlayer, Items.ARROW, amount)) {
            for (int i = 0; i < amount; i++) {
                LivingEntity target = nearbyEntities.get(i);
                ArrowItem arrowItem = (ArrowItem) Items.ARROW;
                PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, new ItemStack(Items.ARROW), attacker);
                persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() * damageMultiplier);
                // borrowed from AbstractSkeletonEntity
                double towardsX = target.getX() - attacker.getX();
                double towardsZ = target.getZ() - attacker.getZ();
                double euclideanDist = MathHelper.sqrt((float) (towardsX * towardsX + towardsZ * towardsZ));
                double towardsY = target.getBodyY(0.3333333333333333D) - persistentProjectileEntity.getY() + euclideanDist * (double) 0.2F;
                persistentProjectileEntity.setVelocity(attacker, attacker.getPitch(), attacker.getYaw(), 0.0F, arrowVelocity * 3.0F,
                        1.0F);
                setProjectileTowards(persistentProjectileEntity, towardsX, towardsY, towardsZ, 0);
                //
                persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                attacker.world.spawnEntity(persistentProjectileEntity);
                if (!attackingPlayer.isCreative()) {
                    InventoryHelper.mcdw$deductAmountOfItem(attackingPlayer, Items.ARROW, 1);
                }
            }
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
        float f = MathHelper.sqrt((float) projectileEntity.squaredDistanceTo(vec3d));
        //noinspection SuspiciousNameCombination
        projectileEntity.setYaw((float)(MathHelper.atan2(vec3d.x, vec3d.z) * (double)(180f / (float)Math.PI)));
        projectileEntity.setPitch((float)(MathHelper.atan2(vec3d.y, f) * (double)(180f / (float)Math.PI)));
        projectileEntity.prevYaw = projectileEntity.getYaw();
        projectileEntity.prevPitch = projectileEntity.getPitch();
    }
}