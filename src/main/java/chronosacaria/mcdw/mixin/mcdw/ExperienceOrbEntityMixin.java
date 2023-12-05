
package chronosacaria.mcdw.mixin.mcdw;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.damagesources.OffHandDamageSource;
import chronosacaria.mcdw.effects.EnchantmentEffects;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({ExperienceOrbEntity.class})
public class ExperienceOrbEntityMixin {

    @Unique
    private PlayerEntity playerEntity;

    @Unique
    public void mcdw$setPlayerEntity(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }

    @Unique
    public PlayerEntity mcdw$getPlayerEntity() { return this.playerEntity; }

    @ModifyArgs(method = "onPlayerCollision", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/ExperienceOrbEntity;repairPlayerGears(Lnet/minecraft/entity/player/PlayerEntity;I)I"))
    public void mcdw$ModifyExperience(Args args){
        PlayerEntity playerEntity = args.get(0);
        mcdw$setPlayerEntity(playerEntity);
        int amount = args.get(1);

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.SOUL_DEVOURER).mcdw$getIsEnabled())
            amount = EnchantmentEffects.soulDevourerExperience(playerEntity, amount);

        args.set(1, amount);
    }

   @ModifyArg(method = "onPlayerCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;addExperience(I)V"))
    public int mcdw$RepairPlayer(int experience){

       PlayerEntity playerEntity = mcdw$getPlayerEntity();
       boolean isOffHandAttack = playerEntity.getRecentDamageSource() instanceof OffHandDamageSource;

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.ANIMA_CONDUIT).mcdw$getIsEnabled())
            return EnchantmentEffects.animaConduitExperience(playerEntity, experience, isOffHandAttack);
        return experience;
    }

}