package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.items.ItemRegistry;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract Item getItem();

    @Shadow public abstract int getDamage();

    @Shadow public abstract int getMaxDamage();

    // When the Mechanised Sawblade breaks, it "becomes" the Broken Sawblade
    @Inject(at = @At("HEAD"), method = "damage(ILnet/minecraft/entity/LivingEntity;Ljava/util/function/Consumer;)V")
    public <T extends LivingEntity> void damage(int amount, T entity, Consumer<T> breakCallback, CallbackInfo ci) {
        World world = entity.getEntityWorld();
        if (getItem() == ItemRegistry.getItem("sword_mechanized_sawblade") && getDamage() + amount >= getMaxDamage()) {
            ItemEntity brokenSawbladeDrop = new ItemEntity(entity.world, entity.getX(), entity.getY(),
                    entity.getZ(),
                    new ItemStack(ItemRegistry.getItem("sword_broken_sawblade")));
            world.spawnEntity(brokenSawbladeDrop);
        }
    }
}
