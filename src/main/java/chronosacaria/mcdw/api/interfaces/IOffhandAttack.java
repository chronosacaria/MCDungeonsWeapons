package chronosacaria.mcdw.api.interfaces;

import chronosacaria.mcdw.api.util.PlayerAttackHelper;
import chronosacaria.mcdw.client.OffhandAttackChecker;
import chronosacaria.mcdw.configs.CompatibilityFlags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public interface IOffhandAttack {
    default TypedActionResult<ItemStack> useOffhand(World world, PlayerEntity player, Hand hand) {
        if (CompatibilityFlags.noOffhandConflicts) {
            if (hand == Hand.OFF_HAND && world.isClient && (player.getOffHandStack().getItem() instanceof IOffhandAttack && PlayerAttackHelper.mixAndMatchWeapons(player))) {
                OffhandAttackChecker.checkForOffhandAttack();
                ItemStack offhand = player.getStackInHand(hand);
                return new TypedActionResult<>(ActionResult.SUCCESS, offhand);
            }
        }
        return new TypedActionResult<>(ActionResult.PASS, player.getStackInHand(hand));
    }
}
