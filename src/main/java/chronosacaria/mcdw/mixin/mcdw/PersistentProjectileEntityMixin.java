package chronosacaria.mcdw.mixin.mcdw;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IMcdwEnchantedArrow;
import chronosacaria.mcdw.effects.EnchantmentEffects;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin implements IMcdwEnchantedArrow {

    @Unique
    private int overcharge;
    @Unique
    private int chainReactionLevel = 0;
    @Unique
    private int chargeLevel = 0;
    @Unique
    private int cobwebShotLevel = 0;
    @Unique
    private int dynamoLevel = 0;
    @Unique
    private int enigmaResonatorLevel = 0;
    @Unique
    private int fuseShotLevel = 0;
    @Unique
    private int freezingLevel = 0;
    @Unique
    private int gravityLevel = 0;
    @Unique
    private int growingLevel = 0;
    @Unique
    private int levitationShotLevel = 0;
    @Unique
    private boolean nautilusBoolean = false;
    @Unique
    private int phantomsMarkLevel = 0;
    @Unique
    private int poisonCloudLevel = 0;
    @Unique
    private int radianceLevel = 0;
    @Unique
    private int replenishLevel = 0;
    @Unique
    private int ricochetLevel = 0;
    @Unique
    private boolean shadowBarbBoolean = false;
    @Unique
    private int shadowShotLevel = 0;
    @Unique
    private int tempoTheftLevel = 0;
    @Unique
    private int thunderingLevel = 0;
    @Unique
    private int voidShotLevel = 0;
    @Unique
    private int wildRageLevel = 0;

    @Override
    public int mcdw$getOvercharge() {
        return overcharge;
    }

    @Override
    public void mcdw$setOvercharge(int overcharge) {
        this.overcharge = overcharge;
    }

    @Override
    public int mcdw$getChainReactionLevel() {
        return chainReactionLevel;
    }

    @Override
    public void mcdw$setChainReactionLevel(int chainReactionLevel) { this.chainReactionLevel = chainReactionLevel; }

    @Override
    public int mcdw$getChargeLevel() {
        return chargeLevel;
    }

    @Override
    public void mcdw$setChargeLevel(int chargeLevel) {
        this.chargeLevel = chargeLevel;
    }

    @Override
    public int mcdw$getCobwebShotLevel() {
        return cobwebShotLevel;
    }

    @Override
    public void mcdw$setCobwebShotLevel(int cobwebShotLevel){
        this.cobwebShotLevel = cobwebShotLevel;
    }

    @Override
    public int mcdw$getDynamoLevel() {
        return dynamoLevel;
    }

    @Override
    public void mcdw$setDynamoLevel(int dynamoLevel) { this.dynamoLevel = dynamoLevel; }

    @Override
    public int mcdw$getEnigmaResonatorLevel() {
        return enigmaResonatorLevel;
    }

    @Override
    public void mcdw$setEnigmaResonatorLevel(int enigmaResonatorLevel) {
        this.enigmaResonatorLevel = enigmaResonatorLevel;
    }

    @Override
    public int mcdw$getFuseShotLevel() {
        return fuseShotLevel;
    }

    @Override
    public void mcdw$setFuseShotLevel(int fuseShotLevel) {
        this.fuseShotLevel = fuseShotLevel;
    }

    @Override
    public int mcdw$getFreezingLevel() {
        return freezingLevel;
    }

    @Override
    public void mcdw$setFreezingLevel(int freezingLevel) {
        this.freezingLevel = freezingLevel;
    }

    @Override
    public int mcdw$getGravityLevel() {
        return gravityLevel;
    }

    @Override
    public void mcdw$setGravityLevel(int gravityLevel) {
        this.gravityLevel = gravityLevel;
    }

    @Override
    public int mcdw$getGrowingLevel() {
        return growingLevel;
    }

    @Override
    public void mcdw$setGrowingLevel(int growingLevel) {
        this.growingLevel = growingLevel;
    }

    @Override
    public int mcdw$getLevitationShotLevel() {
        return levitationShotLevel;
    }

    @Override
    public void mcdw$setLevitationShotLevel(int levitationShotLevel) {
        this.levitationShotLevel = levitationShotLevel;
    }

    @Override
    public boolean mcdw$getNautilusBoolean() {
        return nautilusBoolean;
    }

    @Override
    public void mcdw$setNautilusBoolean(boolean nautilusBoolean) {
        this.nautilusBoolean = nautilusBoolean;
    }

    @Override
    public int mcdw$getPhantomsMarkLevel() {
        return phantomsMarkLevel;
    }

    @Override
    public void mcdw$setPhantomsMarkLevel(int phantomsMarkLevel) {
        this.phantomsMarkLevel = phantomsMarkLevel;
    }

    @Override
    public int mcdw$getPoisonCloudLevel() {
        return poisonCloudLevel;
    }

    @Override
    public void mcdw$setPoisonCloudLevel(int poisonCloudLevel) {
        this.poisonCloudLevel = poisonCloudLevel;
    }

    @Override
    public int mcdw$getRadianceLevel() {
        return radianceLevel;
    }

    @Override
    public void mcdw$setRadianceLevel(int radianceLevel) {
        this.radianceLevel = radianceLevel;
    }

    @Override
    public int mcdw$getReplenishLevel() {
        return replenishLevel;
    }

    @Override
    public void mcdw$setReplenishLevel(int replenishLevel) {
        this.replenishLevel = replenishLevel;
    }

    @Override
    public int mcdw$getRicochetLevel() {
        return ricochetLevel;
    }

    @Override
    public void mcdw$setRicochetLevel(int ricochetLevel) {
        this.ricochetLevel = ricochetLevel;
    }

    @Override
    public boolean mcdw$getShadowBarbBoolean() { return shadowBarbBoolean; }

    @Override
    public void mcdw$setShadowBarbBoolean(boolean shadowBarbBoolean) {this.shadowBarbBoolean = shadowBarbBoolean; }

    @Override
    public int mcdw$getShadowShotLevel() { return shadowShotLevel; }

    @Override
    public void mcdw$setShadowShotLevel(int shadowShotLevel) { this.shadowShotLevel = shadowShotLevel; }

    @Override
    public int mcdw$getTempoTheftLevel() {
        return tempoTheftLevel;
    }

    @Override
    public void mcdw$setTempoTheftLevel(int tempoTheftLevel) {
        this.tempoTheftLevel = tempoTheftLevel;
    }

    @Override
    public int mcdw$getThunderingLevel() {
        return thunderingLevel;
    }

    @Override
    public void mcdw$setThunderingLevel(int thunderingLevel) {
        this.thunderingLevel = thunderingLevel;
    }

    @Override
    public int mcdw$getVoidShotLevel() {
        return voidShotLevel;
    }

    @Override
    public void mcdw$setVoidShotLevel(int voidShotLevel) {
        this.voidShotLevel = voidShotLevel;
    }

    @Override
    public int mcdw$getWildRageLevel() { return wildRageLevel; }

    @Override
    public void mcdw$setWildRageLevel(int wildRageLevel) { this.wildRageLevel = wildRageLevel; }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void mcdw$nbtToTag(NbtCompound tag, CallbackInfo ci){
        tag.putInt("chainReactionLevel", chainReactionLevel);
        tag.putInt("chargeLevel", chargeLevel);
        tag.putInt("cobwebShotLevel", cobwebShotLevel);
        tag.putInt("enigmaResonatorLevel", enigmaResonatorLevel);
        tag.putInt("fuseShotLevel", fuseShotLevel);
        tag.putInt("freezingLevel", freezingLevel);
        tag.putInt("gravityLevel", gravityLevel);
        tag.putInt("growingLevel", growingLevel);
        tag.putInt("levitationShotLevel", levitationShotLevel);
        tag.putBoolean("nautilusBoolean", nautilusBoolean);
        tag.putInt("overchargeLevel", overcharge);
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
        this.chainReactionLevel = tag.getInt("chainReactionLevel");
        this.chargeLevel = tag.getInt("chargeLevel");
        this.cobwebShotLevel = tag.getInt("cobwebShotLevel");
        this.enigmaResonatorLevel = tag.getInt("enigmaResonatorLevel");
        this.fuseShotLevel = tag.getInt("fuseShotLevel");
        this.freezingLevel = tag.getInt("freezingLevel");
        this.gravityLevel = tag.getInt("gravityLevel");
        this.growingLevel = tag.getInt("growingLevel");
        this.levitationShotLevel = tag.getInt("levitationLevel");
        this.nautilusBoolean = tag.getBoolean("nautilusBoolean");
        this.overcharge = tag.getInt("overchargeLevel");
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

            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.CHAIN_REACTION).mcdw$getIsEnabled())
                EnchantmentEffects.applyChainReaction(shooter, target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.CHARGE).mcdw$getIsEnabled())
                EnchantmentEffects.applyCharge(shooter, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.COBWEB_SHOT).mcdw$getIsEnabled())
                EnchantmentEffects.applyCobwebShotEntity(target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.FUSE_SHOT).mcdw$getIsEnabled())
                EnchantmentEffects.applyFuseShot(shooter, target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.FREEZING).mcdw$getIsEnabled())
                EnchantmentEffects.applyFreezingShot(target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.GRAVITY).mcdw$getIsEnabled())
                EnchantmentEffects.applyGravityShot(shooter, target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.LEVITATION_SHOT).mcdw$getIsEnabled())
                EnchantmentEffects.applyLevitationShot(target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.PHANTOMS_MARK).mcdw$getIsEnabled())
                EnchantmentEffects.applyPhantomsMark(target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.POISON_CLOUD).mcdw$getIsEnabled())
                EnchantmentEffects.applyPoisonCloudShot(shooter, target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.RADIANCE).mcdw$getIsEnabled())
                EnchantmentEffects.applyRadianceShot(shooter, target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.RICOCHET).mcdw$getIsEnabled())
                EnchantmentEffects.applyRicochet(shooter, target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.TEMPO_THEFT).mcdw$getIsEnabled())
                EnchantmentEffects.applyTempoTheft(shooter, target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.THUNDERING).mcdw$getIsEnabled())
                EnchantmentEffects.applyThunderingShot(shooter, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.WILD_RAGE).mcdw$getIsEnabled()) {
                if (!(target instanceof EnderDragonEntity || target instanceof WardenEntity) && target instanceof MobEntity) {
                    EnchantmentEffects.applyWildRage((MobEntity) target, persProjEntity);
                }
            }
        }

        if (persProjEntity.getOwner() instanceof PlayerEntity shooter) {

            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.REPLENISH).mcdw$getIsEnabled())
                EnchantmentEffects.applyReplenish(shooter, persProjEntity);
        }
    }

    @Inject(method = "onBlockHit", at = @At("TAIL"))
    private void mcdw$onBlockHitTail(BlockHitResult blockHitResult, CallbackInfo ci){
        PersistentProjectileEntity persProjEntity = (PersistentProjectileEntity) (Object) this;
        if (!(persProjEntity.getOwner() instanceof LivingEntity shooter))
            return;

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.COBWEB_SHOT).mcdw$getIsEnabled())
            EnchantmentEffects.applyCobwebShotBlock(blockHitResult, persProjEntity);
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.RADIANCE).mcdw$getIsEnabled())
            EnchantmentEffects.applyRadianceShotBlock(blockHitResult, shooter, persProjEntity);
    }

    @Inject(method = "getDragInWater", at = @At("RETURN"), cancellable = true)
    public void mcdw$onHarpoonArrowFire(CallbackInfoReturnable<Float> cir) {
        PersistentProjectileEntity ppe = (PersistentProjectileEntity) (Object) this;
        if (ppe.isTouchingWater()) {
            if (((IMcdwEnchantedArrow)ppe).mcdw$getNautilusBoolean()) {
                float normDrag = cir.getReturnValueF();
                float v = (cir.getReturnValue() == null ? 0.6F : normDrag) * 1.542f;
                cir.setReturnValue(v);
            }
        }
    }

}