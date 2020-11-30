package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.enchants.types.RangedEnchantment;
import chronosacaria.mcdw.enchants.util.ProjectileEffectHelper;
import chronosacaria.mcdw.sounds.McdwSoundEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.registry.Registry;

public class Ricochet extends RangedEnchantment {
    public Ricochet(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT,new Identifier(Mcdw.MOD_ID, "ricochet"),this);
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level){

        float damageMultiplier;
        damageMultiplier = 0.1F + (level - 1 * 0.07F);
        float arrowVelocity = McdwBow.maxBowRange;
        if (arrowVelocity > 0.1F){
            ProjectileEffectHelper.riochetArrowTowardsOtherEntity((LivingEntity)target, 10, damageMultiplier,
                    arrowVelocity);
        }
    }
}
