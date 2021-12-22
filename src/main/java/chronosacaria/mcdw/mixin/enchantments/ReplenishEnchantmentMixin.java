package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LivingEntity.class, PlayerEntity.class})
public class ReplenishEnchantmentMixin {
    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyReplenishEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();

        if (source.isProjectile()) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.REPLENISH)) {
                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.REPLENISH,
                            mainHandStack) >= 1 )) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.REPLENISH, mainHandStack);
                        if (user instanceof PlayerEntity) {
                            if (level >= 1) {
                                float replenishRand = user.getRandom().nextFloat();
                                float replenishChance = 0;
                                if (level == 1) replenishChance = 0.10f;
                                if (level == 2) replenishChance = 0.17f;
                                if (level == 3) replenishChance = 0.24f;
                                if (replenishRand <= replenishChance) {
                                    ItemEntity arrowDrop = new ItemEntity(user.world, user.getX(), user.getY(),
                                            user.getZ(),
                                            new ItemStack(Items.ARROW));
                                    user.world.spawnEntity(arrowDrop);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
