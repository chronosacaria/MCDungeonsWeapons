/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.networking;

import dev.timefall.mcdw.Mcdw;
import net.minecraft.util.Identifier;

@SuppressWarnings("deprecation")
public class OffhandAttackPacket {

    public static final Identifier OFFHAND_ATTACK_PACKET = Identifier.of(Mcdw.MOD_ID, "offhand_attack_entity");
    public static final Identifier OFFHAND_MISS_PACKET = Identifier.of(Mcdw.MOD_ID, "offhand_miss_entity");

    //public static Packet<ServerPlayPacketListener> offhandAttackPacket(Entity entity) {
    //    PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
    //    buf.writeInt(entity.getId());
    //    return ClientPlayNetworking.createC2SPacket(OFFHAND_ATTACK_PACKET, buf);
    //}

    //public static Packet<?> offhandMissPacket() {
    //    PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
    //    return ClientPlayNetworking.createC2SPacket(OFFHAND_MISS_PACKET, buf);
    //}

    //public static void register() {
    //    ServerPlayNetworking.registerGlobalReceiver(OFFHAND_ATTACK_PACKET, (server, player, handler, buffer, sender) -> {
    //        int offhandAttackedEntityId = buffer.readInt();
    //        Entity entity = ((ServerWorld) player.getWorld()).getDragonPart(offhandAttackedEntityId);
    //        server.execute(() -> {
    //            player.updateLastActionTime();
    //            if (entity != null) {
    //                PlayerAttackHelper.mcdw$offhandAttack(player, entity);
    //            }
    //        });
    //    });

    //    ServerPlayNetworking.registerGlobalReceiver(OFFHAND_MISS_PACKET, (server, player, handler, buffer, sender) ->
    //            server.execute(() -> ((IDualWielding) player).mcdw$resetLastAttackedOffhandTicks())
    //    );
    //}
}
