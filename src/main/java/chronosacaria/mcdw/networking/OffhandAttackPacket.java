package chronosacaria.mcdw.networking;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import chronosacaria.mcdw.api.util.PlayerAttackHelper;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class OffhandAttackPacket {

    public static final Identifier OFFHAND_ATTACK_PACKET = new Identifier(Mcdw.MOD_ID, "offhand_attack_entity");

    public static Packet<?> offhandAttackPacket(Entity entity) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeInt(entity.getId());
        return ClientPlayNetworking.createC2SPacket(OFFHAND_ATTACK_PACKET, buf);
    }

    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(OFFHAND_ATTACK_PACKET, (server, player, handler, buffer, sender) -> {
            int offhandAttackedEntityId = buffer.readInt();
            server.execute(() -> {
                player.updateLastActionTime();
                if (player.world.getEntityById(offhandAttackedEntityId) != null) {
                    PlayerAttackHelper.offhandAttack(player, player.world.getEntityById(offhandAttackedEntityId));
                }
            });
        });
    }
}
