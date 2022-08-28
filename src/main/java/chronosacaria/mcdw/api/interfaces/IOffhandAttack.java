package chronosacaria.mcdw.api.interfaces;

import chronosacaria.mcdw.client.OffhandAttackChecker;
import chronosacaria.mcdw.configs.CompatibilityFlags;
import chronosacaria.mcdw.enums.DaggersID;
import chronosacaria.mcdw.enums.SicklesID;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public interface IOffhandAttack {
    default TypedActionResult<ItemStack> useOffhand(World world, PlayerEntity player, Hand hand) {
        if (CompatibilityFlags.noOffhandConflicts) {
            if (hand == Hand.OFF_HAND && world.isClient && (player.getOffHandStack().getItem() instanceof IOffhandAttack && (player.getOffHandStack().isOf(player.getMainHandStack().getItem())
                    || (player.getMainHandStack().isOf(DaggersID.DAGGER_THE_BEGINNING.getItem()) && player.getOffHandStack().isOf(DaggersID.DAGGER_THE_END.getItem()))
                    || (player.getMainHandStack().isOf(DaggersID.DAGGER_THE_END.getItem()) && player.getOffHandStack().isOf(DaggersID.DAGGER_THE_BEGINNING.getItem()))
                    || (player.getMainHandStack().isOf(SicklesID.SICKLE_LAST_LAUGH_GOLD.getItem()) && player.getOffHandStack().isOf(SicklesID.SICKLE_LAST_LAUGH_SILVER.getItem()))
                    || (player.getMainHandStack().isOf(SicklesID.SICKLE_LAST_LAUGH_SILVER.getItem()) && player.getOffHandStack().isOf(SicklesID.SICKLE_LAST_LAUGH_GOLD.getItem()))))) {
                OffhandAttackChecker.checkForOffhandAttack();
                ItemStack offhand = player.getStackInHand(hand);
                return new TypedActionResult<>(ActionResult.SUCCESS, offhand);
            }
        }
        return new TypedActionResult<>(ActionResult.PASS, player.getStackInHand(hand));
    }
}