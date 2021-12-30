
package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ExperienceOrbEntity.class})
public abstract class SoulDevourerMixin extends Entity {

    @Shadow private int amount;

    public SoulDevourerMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onPlayerCollision", at = @At("HEAD"))
    public void onSoulDevourerDeath(PlayerEntity player, CallbackInfo ci) {
        if (!Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.SOUL_DEVOURER))
            return;

        if (!world.isClient) {

            ItemStack mainHandStack = player.getMainHandStack();
            ItemStack offHandStack = player.getOffHandStack();

            int soulDevourerLevel = EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_DEVOURER, mainHandStack);

            if (offHandStack.getItem() instanceof IOffhandAttack) {
                soulDevourerLevel += EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_DEVOURER, offHandStack);
            }

            if (soulDevourerLevel > 0) {
                this.amount = (this.amount * (1 + (soulDevourerLevel / 3)) + Math.round(((soulDevourerLevel % 3)/3.0f) * this.amount));
                this.remove(RemovalReason.KILLED);
                player.world.playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.PARTICLE_SOUL_ESCAPE,
                        SoundCategory.PLAYERS,
                        0.5F,
                        1.0F);

            }
        }
    }
}
