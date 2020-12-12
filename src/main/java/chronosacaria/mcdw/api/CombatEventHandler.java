package chronosacaria.mcdw.api;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

public class CombatEventHandler {

    public static void checkForOffHandAttack(){
        MinecraftClient mc = MinecraftClient.getInstance();
        PlayerEntity player = mc.player;
        HitResult hitResult = mc.crosshairTarget;
        if (MinecraftClient.getInstance().world != null
                && MinecraftClient.getInstance().currentScreen == null
                && !MinecraftClient.getInstance().isPaused()
                && player != null
                && !player.isBlocking()){
            ItemStack offhand = player.getOffHandStack();
            if (offhand.getItem() instanceof IOffhandAttack){
                //float reach = (float) 3.0D;

                if (hitResult instanceof EntityHitResult){
                    if (mc.crosshairTarget != null && mc.interactionManager != null) {
                            mc.interactionManager.attackEntity(player,
                                    ((EntityHitResult)mc.crosshairTarget).getEntity());
                        }
                    }
                    /*EntityHitResult entityHitResult = (EntityHitResult)hitResult;
                    Entity entityHit = entityHitResult.getEntity();
                    UUID uuid = player.getUuid();
                    if (entityHit != player && entityHit != player.getVehicle()){
                        PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
                        passedData.writeVarInt(entityHit.getEntityId());

                        ClientSidePacketRegistry.INSTANCE.sendToServer(Mcdw.OFFHAND_ATTACK, passedData);
                    }*/
                }
            }
        }
    }
