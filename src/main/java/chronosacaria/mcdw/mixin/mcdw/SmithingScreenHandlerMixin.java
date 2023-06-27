package chronosacaria.mcdw.mixin.mcdw;

import chronosacaria.mcdw.enums.SwordsID;
import chronosacaria.mcdw.registries.ItemsRegistry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.SmithingScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SmithingScreenHandler.class)
public class SmithingScreenHandlerMixin {

    @Inject(method = "onTakeOutput", at = @At(value = "TAIL"))
    public void mcdw$onTakeOutput(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        if (stack.getItem() == ItemsRegistry.SWORD_ITEMS.get(SwordsID.SWORD_MECHANIZED_SAWBLADE))
            stack.addEnchantment(Enchantments.FIRE_ASPECT, 1);
    }
}
