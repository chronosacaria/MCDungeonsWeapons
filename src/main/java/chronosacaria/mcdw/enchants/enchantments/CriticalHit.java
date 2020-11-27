package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enchants.types.DamageBoostEnchantment;
import chronosacaria.mcdw.weapons.Katanas;
import chronosacaria.mcdw.weapons.Swords;
import com.google.common.collect.ImmutableList;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CriticalHit extends DamageBoostEnchantment {
    public CriticalHit(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT,new Identifier(Mcdw.MOD_ID, "critical_hit"),this);
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level){

        float criticalHitChance;
        criticalHitChance = 0.5f + level * 0.05F;
        float criticalHitRand = user.getRandom().nextFloat();
        float attackDamage = (float)user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        float extraDamageMultiplier = 1.5F;
        float getExtraDamage = (attackDamage * (extraDamageMultiplier));

        if (criticalHitRand <= criticalHitChance){
           ((LivingEntity)target).damage(DamageSource.player((PlayerEntity)user),
                    getExtraDamage);
            target.world.playSound(
                    (PlayerEntity)null,
                    target.getX(),
                    target.getY(),
                    target.getZ(),
                    SoundEvents.ENTITY_PLAYER_ATTACK_CRIT,
                    SoundCategory.PLAYERS,
                    64.0F,
                    1.0F);
        }
    }
}

