
package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({ExperienceOrbEntity.class})
public abstract class SoulDevourerMixin extends Entity {

    public SoulDevourerMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyArgs(method = "onPlayerCollision", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/ExperienceOrbEntity;repairPlayerGears(Lnet/minecraft/entity/player/PlayerEntity;I)I"))
    public void mcdwModifyExperience(Args args){
        int amount = args.get(1);
        PlayerEntity playerEntity = args.get(0);

        if (!Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.SOUL_DEVOURER))
            return;

        int mainHandLevel = EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_DEVOURER, playerEntity.getMainHandStack());
        int offHandLevel = EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_DEVOURER, playerEntity.getOffHandStack());

        int soulDevourerLevel = playerEntity.getOffHandStack().getItem() instanceof IOffhandAttack ?
                mainHandLevel + offHandLevel : mainHandLevel;

        if (soulDevourerLevel > 0)
            amount = (amount * (1 + (soulDevourerLevel / 3)) + Math.round(((soulDevourerLevel % 3)/3.0f) * amount));

        args.set(1, amount);
    }
}