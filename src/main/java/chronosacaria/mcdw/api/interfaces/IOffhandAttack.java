package chronosacaria.mcdw.api.interfaces;

import chronosacaria.mcdw.api.util.PlayerAttackHelper;
import chronosacaria.mcdw.enums.DaggersID;
import chronosacaria.mcdw.enums.SicklesID;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public interface IOffhandAttack {
    default TypedActionResult<ItemStack> useOffhand(World worldIn, PlayerEntity player, Hand handIn) {


        if (handIn == Hand.OFF_HAND && worldIn.isClient && (player.getOffHandStack().getItem() instanceof IOffhandAttack && (player.getOffHandStack().getItem() == player.getMainHandStack().getItem()
                || (player.getMainHandStack().getItem() == ItemsInit.daggerItems.get(DaggersID.DAGGER_THE_BEGINNING) && player.getOffHandStack().getItem() == ItemsInit.daggerItems.get(DaggersID.DAGGER_THE_END))
                || (player.getMainHandStack().getItem() == ItemsInit.daggerItems.get(DaggersID.DAGGER_THE_END) && player.getOffHandStack().getItem() == ItemsInit.daggerItems.get(DaggersID.DAGGER_THE_BEGINNING))
                || (player.getMainHandStack().getItem() == ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_GOLD) && player.getOffHandStack().getItem() == ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_SILVER))
                || (player.getMainHandStack().getItem() == ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_SILVER) && player.getOffHandStack().getItem() == ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_GOLD))))) {
            PlayerAttackHelper.checkForOffhandAttack();
            ItemStack offhand = player.getStackInHand(handIn);
            return new TypedActionResult<>(ActionResult.SUCCESS, offhand);
        } else {
            return new TypedActionResult<>(ActionResult.PASS, player.getStackInHand(handIn));
        }
    }
}
