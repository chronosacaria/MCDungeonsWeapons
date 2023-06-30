package chronosacaria.mcdw.mixin.reach;

import chronosacaria.mcdw.api.util.PlayerAttackHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyConstant(method = "attack", constant = @Constant(doubleValue = 9.0))
    private double modifiedAttackRange(double attackRange) {
        return PlayerAttackHelper.mcdw$getSquaredAttackRange(this, attackRange);
    }
}
