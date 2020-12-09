/*package chronosacaria.mcdw.api;

import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class CombatEventHandler {

    public static void checkForOffHandAttack(){
        MinecraftClient mc = MinecraftClient.getInstance();
        PlayerEntity player = mc.player;
        if (MinecraftClient.getInstance().world != null
                && MinecraftClient.getInstance().currentScreen == null
                && !MinecraftClient.getInstance().isPaused()
                && player != null
                && !player.isBlocking()){
            ItemStack offhand = player.getOffHandStack();
            if (offhand.getItem() instanceof IOffhandAttack){
                float reach = (float) 3.0D;
                HitResult hitResult = getEntityMouseOver(reach);
                if (hitResult instanceof EntityHitResult){
                    EntityHitResult entityHitResult = (EntityHitResult)hitResult;
                    Entity entityHit = entityHitResult.getEntity();
                    if (entityHit != player && entityHit != player.getVehicle()){
                        //TODO PACKET HANDLER GOES HERE?
                    }
                }
            }
        }
    }
    private static HitResult getEntityMouseOver(float reach){
        HitResult result = null;
        MinecraftClient mc = MinecraftClient.getInstance();
        Entity targetedEntity = mc.targetedEntity;
        if (targetedEntity != null && mc.world != null){
            double reachDistance = (double)reach;
            HitResult hitResult = targetedEntity.raycast(Entity.getRenderDistanceMultiplier(), 0.0f, false);
            Vec3d eyePos = targetedEntity.getCameraPosVec(0.0F);
            boolean hasExtendedReach = false;
            double attackReach = reachDistance;
            if (mc.interactionManager != null){
                if (mc.interactionManager.hasExtendedReach() && reachDistance < 3.0D){
                    attackReach = 3.0D;
                    reachDistance = attackReach;
                } else if (reachDistance > (double) reach) {
                    hasExtendedReach = true;
                }
            }
            attackReach = hitResult.getPos().squaredDistanceTo(eyePos);

            Vec3d lookVec = targetedEntity.getRotationVec(1.0F);
            Vec3d attackVec = eyePos.add(lookVec.x * reachDistance, lookVec.y  * reachDistance,
                    lookVec.z * reachDistance);
            Box box = targetedEntity.getBoundingBox();
            EntityHitResult entityHitResult = ProjectileUtil.raycast(targetedEntity, eyePos, attackVec, box,
                    (entity) -> !entity.isSpectator() && entity.isCollidable(), attackReach);
            if (entityHitResult != null){
                Vec3d hitVec = entityHitResult.getPos();
                double squareDistanceTo = eyePos.squaredDistanceTo(hitVec);
                if (hasExtendedReach && squareDistanceTo > (double) (reach * reach)){
                    result = BlockHitResult.createMissed(hitVec, Direction.getFacing(lookVec.x, lookVec.y, lookVec.z)
                            , new BlockPos(hitVec));
                } else if (squareDistanceTo < attackReach) {
                    result = entityHitResult;
                }
            } else {
                result = BlockHitResult.createMissed(attackVec, Direction.getFacing(lookVec.x, lookVec.y, lookVec.z)
                        , new BlockPos(attackVec));
            }
        }
        return (HitResult) result;
    }
}*/
