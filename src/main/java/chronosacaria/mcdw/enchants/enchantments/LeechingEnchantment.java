package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.enums.SettingsID;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class LeechingEnchantment extends Enchantment{

    public LeechingEnchantment(Enchantment.Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.LEECHING)) {
            Registry.register(Registry.ENCHANTMENT, Mcdw.ID("leeching"), this);
        }
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    protected boolean canAccept (Enchantment other){
        return Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enableEnchantmentSettings.get(SettingsID.LEECHING_CAN_BE_MIXED_WITH_HEALING);
    }

    /*@Override // LEECHING AS PER HIT
    public void onTargetDamaged(LivingEntity user, Entity target, int level){
        float chance = user.getRandom().nextFloat();
        if (!(target instanceof LivingEntity)){
            return;}
        else {
            if (chance <= 0.3) {
                float damageDealt = ((LivingEntity) target).getMaxHealth() - ((LivingEntity) target).getHealth();
                //float targetMaxHealth = ((LivingEntity) target).getMaxHealth();
                float healthRegained;
                healthRegained = (0.02F + 0.02F * level) * (damageDealt * (0.25f * level));
                user.heal(healthRegained);
            }
        }
    }*/

    @Override
    public boolean isAvailableForRandomSelection() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.LEECHING);
    }
}