package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.AOECloudHelper;
import chronosacaria.mcdw.api.util.AOEHelper;
import chronosacaria.mcdw.api.util.ProjectileEffectHelper;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public class PersistentProjectileEntityMixin {
    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void mcdw$onChainReactionEnchantmentEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){

        if (!(entityHitResult.getEntity() instanceof LivingEntity target)) {
            return;
        }
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;

        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();
        ItemStack mainHandStack = null;
        if (shooter != null) {
            mainHandStack = shooter.getMainHandStack();
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.CHAIN_REACTION)) {
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
                                1.0F, persistentProjectileEntity);
                    }
                }
            }
        }
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void mcdw$onCobwebShotEnchantmentEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity)) {
            return;
        }
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        Entity target = entityHitResult.getEntity();
        World targetWorld = target.getEntityWorld();
        BlockPos targetPos = target.getBlockPos();
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();
        ItemStack mainHandStack = null;
        if (shooter != null) {
            mainHandStack = shooter.getMainHandStack();
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.COBWEB_SHOT)) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.COBWEB_SHOT, mainHandStack) >= 1)) {
                if (targetWorld.getBlockState(targetPos) == Blocks.AIR.getDefaultState()) {
                    targetWorld.setBlockState(targetPos, Blocks.COBWEB.getDefaultState());
                }
            }
        }
    }

    @Inject(method = "onBlockHit", at = @At("TAIL"))
    private void mcdw$onCobWebShotEnchantmentBlockHit(BlockHitResult blockHitResult, CallbackInfo ci){
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();
        ItemStack mainHandStack;
        if (shooter != null) {
            World shooterWorld = shooter.getEntityWorld();
            mainHandStack = shooter.getMainHandStack();
            Direction side = blockHitResult.getSide();
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.COBWEB_SHOT)) {
                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.COBWEB_SHOT, mainHandStack) >= 1)) {
                    if (shooterWorld.getBlockState(blockHitResult.getBlockPos().offset(side)) == Blocks.AIR.getDefaultState()){
                        shooterWorld.setBlockState(blockHitResult.getBlockPos().offset(side),
                                Blocks.COBWEB.getDefaultState());
                    }
                }
            }
        }
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void mcdw$onEnigmaShotEnchantmentEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity target)) {
            return;
        }
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();

        if (!(shooter instanceof PlayerEntity)) return;

        ItemStack mainHandStack = shooter.getMainHandStack();

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.ENIGMA_RESONATOR)) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.ENIGMA_RESONATOR, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.ENIGMA_RESONATOR, mainHandStack);

                int numSouls = ((PlayerEntity)shooter).experienceLevel;

                double extraDamageMultiplier =
                        (persistentProjectileEntity.getDamage()*(Math.log(numSouls * level)))*1.75D;

                if (numSouls >= 1){
                    target.damage(DamageSource.arrow(persistentProjectileEntity, shooter),
                            (float) extraDamageMultiplier);
                    shooter.world.playSound(
                            null,
                            shooter.getX(),
                            shooter.getY(),
                            shooter.getZ(),
                            SoundEvents.PARTICLE_SOUL_ESCAPE,
                            SoundCategory.PLAYERS,
                            0.5F,
                            1.0F);
                }
            }
        }
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void mcdw$onFuseShotEnchantmentEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity target)) {
            return;
        }
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();
        ItemStack mainHandStack = null;
        if (shooter != null) {
            mainHandStack = shooter.getMainHandStack();
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.FUSE_SHOT)) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.FUSE_SHOT, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.FUSE_SHOT, mainHandStack);
                float f = (float) persistentProjectileEntity.getVelocity().length();
                int fuseShotDamage = MathHelper.ceil(MathHelper.clamp((double) f * persistentProjectileEntity.getDamage(), 0.0D, 2.147483647E9D));
                float chance = shooter.getRandom().nextFloat();
                if (chance <= (0.2 + level * 0.15)) {
                    target.world.playSound(
                            null,
                            target.getX(),
                            target.getY(),
                            target.getZ(),
                            SoundEvents.ENTITY_GENERIC_EXPLODE,
                            SoundCategory.PLAYERS,
                            0.5F,
                            1.0F);
                    AOECloudHelper.spawnExplosionCloud(shooter, target, 3.0F);
                    AOEHelper.causeExplosionAttack(shooter, target, fuseShotDamage, 3.0F);
                }
            }
        }
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void mcdw$onGravityShotEnchantmentEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity target)) {
            return;
        }
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();
        ItemStack mainHandStack = null;
        if (shooter != null) {
            mainHandStack = shooter.getMainHandStack();
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.GRAVITY)) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.GRAVITY, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.GRAVITY, mainHandStack);
                float gravityShotRand = shooter.getRandom().nextFloat();
                if (gravityShotRand <= 0.2F) {
                    AOEHelper.pullInNearbyEntities(
                            shooter,
                            target,
                            (level + 1) * 3);
                }
            }
        }
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void mcdw$onLevitationShotEnchantmentEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity)) {
            return;
        }
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        Entity target = entityHitResult.getEntity();
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();
        ItemStack mainHandStack = null;
        if (shooter != null) {
            mainHandStack = shooter.getMainHandStack();
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.LEVITATION_SHOT)) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.LEVITATION_SHOT, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.LEVITATION_SHOT, mainHandStack);
                if (target instanceof LivingEntity){
                    ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION,
                            level * 200));
                }
            }
        }
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void mcdw$onPhantomsMarkEnchantmentEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity)) {
            return;
        }
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        Entity target = entityHitResult.getEntity();
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();
        ItemStack mainHandStack = null;
        if (shooter != null) {
            mainHandStack = shooter.getMainHandStack();
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.PHANTOMS_MARK)) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.PHANTOMS_MARK, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.PHANTOMS_MARK, mainHandStack);
                StatusEffectInstance glowing = new StatusEffectInstance(StatusEffects.GLOWING, 100 * level, 0);
                if (target instanceof LivingEntity){
                    ((LivingEntity) target).addStatusEffect(glowing);
                }
            }
        }
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void mcdw$onPoisonCloudShotEnchantmentEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity)) {
            return;
        }
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        Entity target = entityHitResult.getEntity();
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();
        ItemStack mainHandStack = null;
        if (shooter != null) {
            mainHandStack = shooter.getMainHandStack();
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.POISON_CLOUD)) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.POISON_CLOUD, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.POISON_CLOUD, mainHandStack);
                float poisonCloudShotRand = shooter.getRandom().nextFloat();
                if (poisonCloudShotRand <= 0.2F) {
                    if (target instanceof LivingEntity) {
                        AOECloudHelper.spawnPoisonCloud(shooter, (LivingEntity) target, level - 1);
                    }
                }
            }
        }
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void mcdw$onRadianceShotEnchantmentEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity)) {
            return;
        }
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        Entity target = entityHitResult.getEntity();
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();
        ItemStack mainHandStack = null;
        if (shooter != null) {
            mainHandStack = shooter.getMainHandStack();
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.RADIANCE_SHOT)) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.RADIANCE_SHOT, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.RADIANCE_SHOT, mainHandStack);
                float radianceShotRand = shooter.getRandom().nextFloat();
                if (radianceShotRand <= 0.2F) {
                    if (target instanceof LivingEntity) {
                        AOECloudHelper.spawnRegenCloudAtPos(shooter, true, target.getBlockPos(), level - 1);
                    }
                }
            }
        }
    }

    //TODO Figure out how to make more than one, but less than four bottles convert to potions
    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void mcdw$onRefreshmentShotEnchantmentEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity)) {
            return;
        }
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();
        if (!(shooter instanceof PlayerEntity)){
            return;
        }
        ItemStack mainHandStack = null;
        if (shooter != null) {
            mainHandStack = shooter.getMainHandStack();
        }
        if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.REFRESHMENT_SHOT, mainHandStack) > 0 )) {
            int level = EnchantmentHelper.getLevel(EnchantsRegistry.REFRESHMENT_SHOT, mainHandStack);
            PlayerInventory playerInventory = ((PlayerEntity)shooter).getInventory();
            for (int slotID = 0; slotID < playerInventory.size(); slotID++){
                ItemStack currentStack = playerInventory.getStack(slotID);
                if (currentStack.getItem() instanceof GlassBottleItem && currentStack.getCount() < 2){
                    ItemStack healthPotion = PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.HEALING);
                    playerInventory.setStack(slotID, healthPotion);
                    if (healthPotion.getCount() <= level){
                        break;
                    }
                }
            }
        }
    }
}
