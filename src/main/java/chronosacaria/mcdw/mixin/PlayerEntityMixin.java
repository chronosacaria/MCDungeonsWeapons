package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.api.interfaces.IDualWielding;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements IDualWielding {

    @Unique
    private int lastAttackedOffhandTicks = 0;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public float getOffhandAttackCooldownProgressPerTick() {
        return (float)(1.0D / this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_SPEED) * 20.0D);
    }

    @Override
    public float getOffhandAttackCooldownProgress(float baseTime) {
        return MathHelper.clamp(((float)this.lastAttackedOffhandTicks + baseTime) / this.getOffhandAttackCooldownProgressPerTick(), 0.0F, 1.0F);
    }

    @Override
    public void resetLastAttackedOffhandTicks() {
        this.lastAttackedOffhandTicks = 0;
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getMainHandStack()Lnet/minecraft/item/ItemStack;"))
    public void tick(CallbackInfo ci) {
        ++this.lastAttackedOffhandTicks;
    }

}
