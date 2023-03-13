package chronosacaria.mcdw.networking;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IDualWielding;
import chronosacaria.mcdw.api.util.PlayerAttackHelper;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

@SuppressWarnings("deprecation")
public class OffhandAttackPacket {

    public static final Identifier OFFHAND_ATTACK_PACKET = new Identifier(Mcdw.MOD_ID, "offhand_attack_entity");
    public static final Identifier OFFHAND_MISS_PACKET = new Identifier(Mcdw.MOD_ID, "offhand_miss_entity");

    public static Packet<ServerPlayPacketListener> offhandAttackPacket(Entity entity) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeInt(entity.getId());
        return ClientPlayNetworking.createC2SPacket(OFFHAND_ATTACK_PACKET, buf);
    }

    public static Packet<?> offhandMissPacket() {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        return ClientPlayNetworking.createC2SPacket(OFFHAND_MISS_PACKET, buf);
    }

    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(OFFHAND_ATTACK_PACKET, (server, player, handler, buffer, sender) -> {
            int offhandAttackedEntityId = buffer.readInt();
            Entity entity = ((ServerWorld) player.world).getDragonPart(offhandAttackedEntityId);
            server.execute(() -> {
                player.updateLastActionTime();
                if (entity != null) {
                    PlayerAttackHelper.offhandAttack(player, entity);
                }
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(OFFHAND_MISS_PACKET, (server, player, handler, buffer, sender) ->
                server.execute(() -> ((IDualWielding) player).resetLastAttackedOffhandTicks())
        );
    }
}
