package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.api.util.ProjectileEffectHelper;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class ChainReactionEnchantmentMixin extends Entity {
    ArrowEntity arrowEntity = (ArrowEntity) (Object) this;

    public ChainReactionEnchantmentMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity)) {
            return;
        }
        LivingEntity target = (LivingEntity) entityHitResult.getEntity();
        LivingEntity shooter = (LivingEntity) arrowEntity.getOwner();
        ItemStack mainHandStack = null;
        if (shooter != null) {
            mainHandStack = shooter.getMainHandStack();
        }
        if (McdwEnchantsConfig.getValue("mixin_chain_reaction")) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.CHAIN_REACTION, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.CHAIN_REACTION, mainHandStack);
                if (target == null) return;
                if (level > 0){
                    float chainReactionChance = 0;
                    if (level == 1) chainReactionChance = 0.1F;
                    if (level == 2) chainReactionChance = 0.2F;
                    if (level == 3) chainReactionChance = 0.3F;
                    float chainReactionRand = shooter.getRandom().nextFloat();
                    if (chainReactionRand <= chainReactionChance){
                        ProjectileEffectHelper.fireChainReactionProjectiles(target.getEntityWorld(), target, shooter,
                                3.15F,
                                1.0F, arrowEntity);
                    }
                }
            }
        }
    }
}
