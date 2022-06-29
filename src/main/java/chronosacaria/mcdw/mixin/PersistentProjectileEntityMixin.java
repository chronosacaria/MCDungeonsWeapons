package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IMcdwEnchantedArrow;
import chronosacaria.mcdw.effects.EnchantmentEffects;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin implements IMcdwEnchantedArrow {

    private int accelerateLevel = 0;
    private int chainReactionLevel = 0;
    private int chargeLevel = 0;
    private int cobwebShotLevel = 0;
    private int dynamoLevel = 0;
    private int enigmaResonatorLevel = 0;
    private int fuseShotLevel = 0;
    private int gravityLevel = 0;
    private int growingLevel = 0;
    private int levitationShotLevel = 0;
    private boolean nautilusBoolean = false;
    private int phantomsMarkLevel = 0;
    private int poisonCloudLevel = 0;
    private int radianceLevel = 0;
    private int replenishLevel = 0;
    private int ricochetLevel = 0;
    private boolean shadowBarbBoolean = false;
    private int shadowShotLevel = 0;
    private int tempoTheftLevel = 0;
    private int voidShotLevel = 0;
    private int wildRageLevel = 0;

    @Override
    public int getAccelerateLevel() {
        return accelerateLevel;
    }

    @Override
    public void setAccelerateLevel(int accelerateLevel) {
        this.accelerateLevel = accelerateLevel;
    }

    @Override
    public int getChainReactionLevel() {
        return chainReactionLevel;
    }

    @Override
    public void setChainReactionLevel(int chainReactionLevel) { this.chainReactionLevel = chainReactionLevel; }

    @Override
    public int getChargeLevel() {
        return chargeLevel;
    }

    @Override
    public void setChargeLevel(int chargeLevel) {
        this.chargeLevel = chargeLevel;
    }

    @Override
    public int getCobwebShotLevel() {
        return cobwebShotLevel;
    }

    @Override
    public void setCobwebShotLevel(int cobwebShotLevel){
        this.cobwebShotLevel = cobwebShotLevel;
    }

    @Override
    public int getDynamoLevel() {
        return dynamoLevel;
    }

    @Override
    public void setDynamoLevel(int dynamoLevel) { this.dynamoLevel = dynamoLevel; }

    @Override
    public int getEnigmaResonatorLevel() {
        return enigmaResonatorLevel;
    }

    @Override
    public void setEnigmaResonatorLevel(int enigmaResonatorLevel) {
        this.enigmaResonatorLevel = enigmaResonatorLevel;
    }

    @Override
    public int getFuseShotLevel() {
        return fuseShotLevel;
    }

    @Override
    public void setFuseShotLevel(int fuseShotLevel) {
        this.fuseShotLevel = fuseShotLevel;
    }

    @Override
    public int getGravityLevel() {
        return gravityLevel;
    }

    @Override
    public void setGravityLevel(int gravityLevel) {
        this.gravityLevel = gravityLevel;
    }

    @Override
    public int getGrowingLevel() {
        return growingLevel;
    }

    @Override
    public void setGrowingLevel(int growingLevel) {
        this.growingLevel = growingLevel;
    }

    @Override
    public int getLevitationShotLevel() {
        return levitationShotLevel;
    }

    @Override
    public void setLevitationShotLevel(int levitationShotLevel) {
        this.levitationShotLevel = levitationShotLevel;
    }

    @Override
    public boolean getNautilusBoolean() {
        return nautilusBoolean;
    }

    @Override
    public void setNautilusBoolean(boolean nautilusBoolean) {
        this.nautilusBoolean = nautilusBoolean;
    }

    @Override
    public int getPhantomsMarkLevel() {
        return phantomsMarkLevel;
    }

    @Override
    public void setPhantomsMarkLevel(int phantomsMarkLevel) {
        this.phantomsMarkLevel = phantomsMarkLevel;
    }

    @Override
    public int getPoisonCloudLevel() {
        return poisonCloudLevel;
    }

    @Override
    public void setPoisonCloudLevel(int poisonCloudLevel) {
        this.poisonCloudLevel = poisonCloudLevel;
    }

    @Override
    public int getRadianceLevel() {
        return radianceLevel;
    }

    @Override
    public void setRadianceLevel(int radianceLevel) {
        this.radianceLevel = radianceLevel;
    }

    @Override
    public int getReplenishLevel() {
        return replenishLevel;
    }

    @Override
    public void setReplenishLevel(int replenishLevel) {
        this.replenishLevel = replenishLevel;
    }

    @Override
    public int getRicochetLevel() {
        return ricochetLevel;
    }

    @Override
    public void setRicochetLevel(int ricochetLevel) {
        this.ricochetLevel = ricochetLevel;
    }

    @Override
    public boolean getShadowBarbBoolean() { return shadowBarbBoolean; }

    @Override
    public void setShadowBarbBoolean(boolean shadowBarbBoolean) {this.shadowBarbBoolean = shadowBarbBoolean; }

    @Override
    public int getShadowShotLevel() { return shadowShotLevel; }

    @Override
    public void setShadowShotLevel(int shadowShotLevel) { this.shadowShotLevel = shadowShotLevel; }

    @Override
    public int getTempoTheftLevel() {
        return tempoTheftLevel;
    }

    @Override
    public void setTempoTheftLevel(int tempoTheftLevel) {
        this.tempoTheftLevel = tempoTheftLevel;
    }

    @Override
    public int getVoidShotLevel() {
        return voidShotLevel;
    }

    @Override
    public void setVoidShotLevel(int voidShotLevel) {
        this.voidShotLevel = voidShotLevel;
    }

    @Override
    public int getWildRageLevel() { return wildRageLevel; }

    @Override
    public void setWildRageLevel(int wildRageLevel) { this.wildRageLevel = wildRageLevel; }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void mcdw$nbtToTag(NbtCompound tag, CallbackInfo ci){
        tag.putInt("accelerateLevel", accelerateLevel);
        tag.putInt("chainReactionLevel", chainReactionLevel);
        tag.putInt("chargeLevel", chargeLevel);
        tag.putInt("cobwebShotLevel", cobwebShotLevel);
        tag.putInt("enigmaResonatorLevel", enigmaResonatorLevel);
        tag.putInt("fuseShotLevel", fuseShotLevel);
        tag.putInt("gravityLevel", gravityLevel);
        tag.putInt("growingLevel", growingLevel);
        tag.putInt("levitationShotLevel", levitationShotLevel);
        tag.putBoolean("nautilusBoolean", nautilusBoolean);
        tag.putInt("phantomsMarkLevel", phantomsMarkLevel);
        tag.putInt("poisonCloudLevel", poisonCloudLevel);
        tag.putInt("radianceLevel", radianceLevel);
        tag.putInt("replenishLevel", replenishLevel);
        tag.putInt("ricochetLevel", ricochetLevel);
        tag.putBoolean("shadowBarbBoolean", shadowBarbBoolean);
        tag.putInt("shadowShotLevel", shadowShotLevel);
        tag.putInt("tempoTheftLevel", tempoTheftLevel);
        tag.putInt("voidShotLevel", voidShotLevel);
        tag.putInt("wildRageLevel", wildRageLevel);

    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void mcdw$nbtFromTag(NbtCompound tag, CallbackInfo ci){
        this.accelerateLevel = tag.getInt("accelerateLevel");
        this.chainReactionLevel = tag.getInt("chainReactionLevel");
        this.chargeLevel = tag.getInt("chargeLevel");
        this.cobwebShotLevel = tag.getInt("cobwebShotLevel");
        this.enigmaResonatorLevel = tag.getInt("enigmaResonatorLevel");
        this.fuseShotLevel = tag.getInt("fuseShotLevel");
        this.gravityLevel = tag.getInt("gravityLevel");
        this.growingLevel = tag.getInt("growingLevel");
        this.levitationShotLevel = tag.getInt("levitationLevel");
        this.nautilusBoolean = tag.getBoolean("nautilusBoolean");
        this.phantomsMarkLevel = tag.getInt("phantomsMarkLevel");
        this.poisonCloudLevel = tag.getInt("poisonCloudLevel");
        this.radianceLevel = tag.getInt("radianceLevel");
        this.replenishLevel = tag.getInt("replenishLevel");
        this.ricochetLevel = tag.getInt("ricochetLevel");
        this.shadowBarbBoolean = tag.getBoolean("shadowBarbBoolean");
        this.shadowShotLevel = tag.getInt("shadowShotLevel");
        this.tempoTheftLevel = tag.getInt("tempoTheftLevel");
        this.voidShotLevel = tag.getInt("voidShotLevel");
        this.wildRageLevel = tag.getInt("wildRageLevel");
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void mcdw$onEntityHitTail(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity target))
            return;

        PersistentProjectileEntity persProjEntity = (PersistentProjectileEntity) (Object) this;
        if (persProjEntity.getOwner() instanceof LivingEntity shooter) {

            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.CHAIN_REACTION))
                EnchantmentEffects.applyChainReaction(shooter, target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.CHARGE))
                EnchantmentEffects.applyCharge(shooter, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.COBWEB_SHOT))
                EnchantmentEffects.applyCobwebShotEntity(target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.FUSE_SHOT))
                EnchantmentEffects.applyFuseShot(shooter, target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.GRAVITY))
                EnchantmentEffects.applyGravityShot(shooter, target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.LEVITATION_SHOT))
                EnchantmentEffects.applyLevitationShot(target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.PHANTOMS_MARK))
                EnchantmentEffects.applyPhantomsMark(target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.POISON_CLOUD))
                EnchantmentEffects.applyPoisonCloudShot(shooter, target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.RADIANCE))
                EnchantmentEffects.applyRadianceShot(shooter, target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.RICOCHET))
                EnchantmentEffects.applyRicochet(target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.TEMPO_THEFT))
                EnchantmentEffects.applyTempoTheft(shooter, target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.WILD_RAGE))
                EnchantmentEffects.applyWildRage((MobEntity) target, persProjEntity);
        }

        if (persProjEntity.getOwner() instanceof PlayerEntity shooter) {

            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.REPLENISH))
                EnchantmentEffects.applyReplenish(shooter, persProjEntity);
        }
    }

    @Inject(method = "onBlockHit", at = @At("TAIL"))
    private void mcdw$onBlockHitTail(BlockHitResult blockHitResult, CallbackInfo ci){
        PersistentProjectileEntity persProjEntity = (PersistentProjectileEntity) (Object) this;
        if (!(persProjEntity.getOwner() instanceof LivingEntity shooter))
            return;

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.COBWEB_SHOT))
            EnchantmentEffects.applyCobwebShotBlock(blockHitResult, persProjEntity);
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.RADIANCE_SHOT))
            EnchantmentEffects.applyRadianceShotBlock(blockHitResult, shooter, persProjEntity);
    }

    @Inject(method = "getDragInWater", at = @At("HEAD"), cancellable = true)
    public void onHarpoonArrowFire(CallbackInfoReturnable<Float> cir) {
        PersistentProjectileEntity ppe = (PersistentProjectileEntity) (Object) this;
        if (ppe.isTouchingWater()) {
            if (((IMcdwEnchantedArrow)ppe).getNautilusBoolean()) {
                float v = cir.getReturnValue() * 1.542f;
                cir.setReturnValue(v);
            }
        }
    }

}