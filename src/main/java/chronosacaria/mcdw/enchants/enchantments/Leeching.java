package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Leeching extends Enchantment{

    public Leeching (Enchantment.Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT,new Identifier(Mcdw.MOD_ID, "leeching"),this);
    }

    public int getMaxLevel() {
        return 3;
    }

@Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level){
        if (!(target instanceof LivingEntity)) return;
        float damageDealt = ((LivingEntity) target).getMaxHealth() - ((LivingEntity)target).getHealth();
        //float targetMaxHealth = ((LivingEntity) target).getMaxHealth();
        float healthRegained;
        healthRegained = (0.02F + 0.02F * level) * (damageDealt * (0.25f * level));
        user.heal(healthRegained);
    }
}
