package chronosacaria.mcdw.api.interfaces;

import chronosacaria.mcdw.api.util.CombatEventHandler;
import chronosacaria.mcdw.configs.FeatureFlags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public interface IOffhandAttack {

    default TypedActionResult<ItemStack> useOffhand(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (handIn == Hand.OFF_HAND && worldIn.isClient && FeatureFlags.isDualWieldingEnabled) {
            CombatEventHandler.checkForOffHandAttack();
            ItemStack offhand = playerIn.getStackInHand(handIn);
            return new TypedActionResult<>(ActionResult.SUCCESS, offhand);
        } else {
            return new TypedActionResult<>(ActionResult.PASS, playerIn.getStackInHand(handIn));
        }
    }

}
