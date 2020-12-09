package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.AbilityHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class TempoTheft extends Enchantment {
    public TempoTheft(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT, new Identifier(Mcdw.MOD_ID, "tempo_theft"), this);
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level){
        if (target instanceof LivingEntity){
            AbilityHelper.stealSpeedFromTarget(user, (LivingEntity) target, level);

        }
    }
}
