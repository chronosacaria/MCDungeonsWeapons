package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.api.interfaces.ProjectileManipulator;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin extends Entity implements ProjectileManipulator {

    private static final TrackedData<ItemStack> ORIGIN_STACK = DataTracker.registerData(PersistentProjectileEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);

    private PersistentProjectileEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    private Vec3d posContext = null;
    private int iteration = 0;

    @Inject(
            method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V", ordinal = 0),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void storeContext(CallbackInfo ci, boolean bl, Vec3d vec3d, BlockPos d, BlockState blockState, Vec3d voxelShape, Vec3d vec3d2, HitResult hitResult, double box, double entity2, double e, int i) {
        this.posContext = vec3d;
        this.iteration = i;
    }

    @Redirect(
            method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V", ordinal = 0)
    )
    private void changeParticles(World world, ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        ItemStack sourceStack = dataTracker.get(ORIGIN_STACK);
        double d = posContext.x;
        double e = posContext.y;
        double g = posContext.z;

        // Check if the source bow has a preferred particle to display
        if(!sourceStack.isEmpty() && sourceStack.getItem() instanceof McdwBow) {
            McdwBow bow = (McdwBow) sourceStack.getItem();
            ParticleEffect bowParticles = bow.getArrowParticles();

            if(bowParticles != null) {
                this.world.addParticle(bowParticles, this.getX() + d * (double) iteration / 4.0D, this.getY() + e * (double) iteration / 4.0D, this.getZ() + g * (double) iteration / 4.0D, -d, -e + 0.2D, -g);
                return;
            }
        }

        // Display default crit particles
        this.world.addParticle(ParticleTypes.CRIT, this.getX() + d * (double) iteration / 4.0D, this.getY() + e * (double) iteration / 4.0D, this.getZ() + g * (double) iteration / 4.0D, -d, -e + 0.2D, -g);
    }

    @Override
    public void setOrigin(ItemStack stack) {
        dataTracker.set(ORIGIN_STACK, stack);
    }

    @Override
    public ItemStack getOrigin() {
        return dataTracker.get(ORIGIN_STACK);
    }

    @Inject(
            method = "initDataTracker",
            at = @At("RETURN")
    )
    private void addDataTrackers(CallbackInfo ci) {
        dataTracker.startTracking(ORIGIN_STACK, ItemStack.EMPTY);
    }
}