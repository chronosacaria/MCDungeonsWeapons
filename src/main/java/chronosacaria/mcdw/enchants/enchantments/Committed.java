package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.types.DamageBoostEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageRecord;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Committed extends DamageBoostEnchantment {
    public Committed(Enchantment.Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT,new Identifier(Mcdw.MOD_ID, "committed"),this);
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level){

        float getTargetHealth = ((LivingEntity)target).getHealth();
        float getTargetMaxHealth = ((LivingEntity)target).getMaxHealth();
        float getTargetRemainingHealth = getTargetHealth / getTargetMaxHealth;
        float getOriginalDamage = getTargetMaxHealth - getTargetRemainingHealth;
        float extraDamageMultiplier = 0.25F + level * 0.25F;
        float getExtraDamage = (getOriginalDamage * (1 - getTargetRemainingHealth) * extraDamageMultiplier);

        if ((Math.abs(getTargetHealth)) < (Math.abs(getTargetMaxHealth))){
           ((LivingEntity)target).damage(DamageSource.player((PlayerEntity)user),
                    getExtraDamage);
    }
}
}

