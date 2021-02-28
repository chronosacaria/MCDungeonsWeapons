package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.api.util.AOECloudHelper;
import chronosacaria.mcdw.api.util.AOEHelper;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class ProspectorEnchantmentMixin {

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)

    private void onProspectorEnchantmentKill(DamageSource source, CallbackInfo ci) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        boolean uniqueWeaponFlag = false;
        if (McdwEnchantsConfig.getValue("prospector")) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.PROSPECTOR, mainHandStack) >= 1 )) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.PROSPECTOR, mainHandStack);
                float prospectorChance = 0.25F * level;
                float prospectorRand = user.getRandom().nextFloat();
                if (prospectorRand <= prospectorChance) {
                    if (target instanceof Monster){
                        ItemEntity emeraldDrop = new ItemEntity(target.world, target.getX(), target.getY(),
                                target.getZ(),
                                new ItemStack(Items.EMERALD, (1 * level)));
                        user.world.spawnEntity(emeraldDrop);
                    }
                }
            }
        }
    }
}
