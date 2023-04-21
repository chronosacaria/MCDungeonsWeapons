package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.api.interfaces.IDualWielding;
import chronosacaria.mcdw.configs.CompatibilityFlags;
import chronosacaria.mcdw.enchants.summons.entity.IBeeSummoning;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements IDualWielding, IBeeSummoning {

    private static final TrackedData<Integer> LAST_ATTACKED_OFFHAND_TICKS = DataTracker.registerData(PlayerEntityMixin.class, TrackedDataHandlerRegistry.INTEGER);

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * IDualWielding
     */

    @Override
    public float getOffhandAttackCooldownProgressPerTick() {
        return (float) (1.0D / this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_SPEED) * 20.0D);
    }

    @Override
    public float getOffhandAttackCooldownProgress(float baseTime) {
        return MathHelper.clamp(((float) getOffhandAttackedTicks() + baseTime) / this.getOffhandAttackCooldownProgressPerTick(), 0.0F, 1.0F);
    }

    @Override
    public void resetLastAttackedOffhandTicks() {
        setOffhandAttackedTicks(0);
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getMainHandStack()Lnet/minecraft/item/ItemStack;"))
    public void tick(CallbackInfo ci) {
        if (CompatibilityFlags.noOffhandConflicts)
            setOffhandAttackedTicks(getOffhandAttackedTicks() + 1);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    protected void injectInitDataTracker(CallbackInfo ci) {
        if (CompatibilityFlags.noOffhandConflicts)
            dataTracker.startTracking(LAST_ATTACKED_OFFHAND_TICKS, 0);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void injectWriteCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        if (CompatibilityFlags.noOffhandConflicts)
            nbt.putInt("LastAttackedOffhandTicks", getOffhandAttackedTicks());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    public void injectReadCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if (CompatibilityFlags.noOffhandConflicts)
            setOffhandAttackedTicks(nbt.getInt("LastAttackedOffhandTicks"));
    }

    @Override
    public int getOffhandAttackedTicks() {
        if (CompatibilityFlags.noOffhandConflicts)
            return dataTracker.get(LAST_ATTACKED_OFFHAND_TICKS);
        return 0;
    }

    @Override
    public void setOffhandAttackedTicks(int lastAttackedOffhandTicks) {
        if (CompatibilityFlags.noOffhandConflicts) {
            if (lastAttackedOffhandTicks >= 0)
                dataTracker.set(LAST_ATTACKED_OFFHAND_TICKS, lastAttackedOffhandTicks);
        }
    }

    /**
     * IBeeSummoning
     */
    private int lastTimeSummonedBee = 0;

    public void setLastSummonedBee(int time) {
        lastTimeSummonedBee = time;
    }
    public int getLastSummonedBee() {
        return lastTimeSummonedBee;
    }
}
