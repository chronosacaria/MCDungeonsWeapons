package chronosacaria.mcdw.network;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.CombatEventHandler;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.PacketContext;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageRecord;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.lwjgl.system.CallbackI;

import java.util.UUID;


public class C2SOffhandAttackPacket {

    public static final Identifier OFFHAND_PACKET = new Identifier(Mcdw.MOD_ID, "offhand_attack");

    public int entityIdTarget;

    public C2SOffhandAttackPacket(int entityIdTarget){
        this.entityIdTarget = entityIdTarget;
    }

    public static void encode(C2SOffhandAttackPacket msg, PacketByteBuf buf){
        buf.writeVarInt(msg.entityIdTarget);
    }

    public static C2SOffhandAttackPacket decode(PacketByteBuf buf){
        return new C2SOffhandAttackPacket(buf.readVarInt());
    }

    public static void handle(PacketContext ctx, PacketByteBuf byteBuf){
        EntityType<?> type = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
        UUID entityUUID = byteBuf.readUuid();
        int entityID = byteBuf.readVarInt();
        double x = byteBuf.readDouble();
        double y = byteBuf.readDouble();
        double z = byteBuf.readDouble();
        ClientWorld world = MinecraftClient.getInstance().world;
        Entity entity = type.create(world);
        if (entity instanceof LivingEntity){
            ((LivingEntity)entity).tryAttack(entity);
        }
        ctx.getTaskQueue().execute( () ->{
            ctx.getPlayer();
            if (entity != null){
                entity.updatePosition(x, y, z);
                entity.updateTrackedPosition(x,y,z);
                entity.setEntityId(entityID);
                entity.setUuid(entityUUID);
                entity.damage(DamageSource.player(ctx.getPlayer()),20);
            }
        });

    }
}
