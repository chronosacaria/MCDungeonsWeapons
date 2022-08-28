package chronosacaria.mcdw.client;

import chronosacaria.mcdw.configs.CompatibilityFlags;
import chronosacaria.mcdw.networking.OffhandAttackPacket;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.EntityHitResult;

@Environment(EnvType.CLIENT)
public class OffhandAttackChecker {
    public static void checkForOffhandAttack() {
        if (CompatibilityFlags.noOffhandConflicts) {
            MinecraftClient mc = MinecraftClient.getInstance();
            PlayerEntity player = mc.player;
            if (mc.world != null
                    && mc.currentScreen == null
                    && !mc.isPaused()
                    && player != null
                    && !player.isBlocking()) {

                if (mc.interactionManager != null && mc.getNetworkHandler() != null) {
                    mc.getNetworkHandler().sendPacket(mc.crosshairTarget instanceof EntityHitResult entityHitResult
                            ? OffhandAttackPacket.offhandAttackPacket(entityHitResult.getEntity())
                            : OffhandAttackPacket.offhandMissPacket());
                }
            }
        }
    }
}