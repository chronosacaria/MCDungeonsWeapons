package chronosacaria.mcdw.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class IceCloudEntity extends Entity {
    private int floatTicks = 60;
    public int fallTime = 0;
    private float fallHurtAmount = 3.0F;
    private LivingEntity caster;
    private UUID casterUuid;
    private LivingEntity target;
    private UUID targetUuid;
    private double heightAboveTarget = 2.0D;
    private double heightAdjustment = (1.0F - this.getHeight()) / 2.0F;

    public IceCloudEntity(World worldIn) {
        super(McdwEntityTypes.ICE_CLOUD, worldIn);
    }

    public IceCloudEntity(EntityType<? extends IceCloudEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public IceCloudEntity(World worldIn, LivingEntity casterIn, LivingEntity targetIn) {
        this(McdwEntityTypes.ICE_CLOUD, worldIn);
        this.setCaster(casterIn);
        this.setTarget(targetIn);
        this.setPos(casterIn.getX(), casterIn.getBodyY(1.0D) + this.heightAboveTarget + heightAdjustment, casterIn.getZ());

        this.inanimate = true;
        this.setVelocity(Vec3d.ZERO);
        this.prevX = targetIn.getX();
        this.prevY = targetIn.getBodyY(1.0D) + this.heightAboveTarget + heightAdjustment;
        this.prevZ = targetIn.getZ();
    }

    protected void registerData() {

    }

    private void tryToFloatAboveTarget(LivingEntity targetIn) {
        List<IceCloudEntity> nearbyIceClouds = this.world.getEntitiesByType(
                McdwEntityTypes.ICE_CLOUD,
                this.getBoundingBox().expand(0.2, 0, 0.2),
                (nearbyEntity) -> nearbyEntity != this);

        if (nearbyIceClouds.isEmpty()) {
            this.setPos(targetIn.getX(), targetIn.getBodyY(1.0D) + this.heightAboveTarget + heightAdjustment,
                    targetIn.getZ());
        }
    }

    public void setCaster(@Nullable LivingEntity caster) {
        this.caster = caster;
        this.casterUuid = caster == null ? null : caster.getUuid();
    }

    @Nullable
    public LivingEntity getCaster() {
        if (this.caster == null && this.casterUuid != null && this.world instanceof ServerWorld) {
            Entity entity = ((ServerWorld) this.world).getEntity(this.casterUuid);
            if (entity instanceof LivingEntity) {
                this.caster = (LivingEntity) entity;
            }
        }
        return this.caster;
    }

    public void setTarget(@Nullable LivingEntity target) {
        this.target = target;
        this.targetUuid = target == null ? null : target.getUuid();
    }

    @Nullable
    public LivingEntity getTarget() {
        if (this.target == null && this.targetUuid != null && this.world instanceof ServerWorld) {
            Entity entity = ((ServerWorld) this.world).getEntity(this.targetUuid);
            if (entity instanceof LivingEntity) {
                this.target = (LivingEntity) entity;
            }
        }
        return this.caster;

    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromTag(CompoundTag tag) {

    }

    @Override
    protected void writeCustomDataToTag(CompoundTag tag) {

    }

    @Override
    public Packet<?> createSpawnPacket() {
        return null;
    }
}
