package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.util.AoeHelper;
import chronosacaria.mcdw.enchants.util.EnchantsAbilitiesUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Thundering extends Enchantment {
    public Thundering(Enchantment.Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT,new Identifier(Mcdw.MOD_ID, "thundering"),this);
    }

    @Override
    public int getMaxLevel(){
        return 1;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level){

        //Spawn Lightning @ 30% chance
        if (!(target instanceof LivingEntity)) return;
        float chance = user.getRandom().nextFloat();
        if (chance <= 1.0) {
            AoeHelper.electrocuteNearbyEnemies(
                    user,
                    target,
                    5,
                    10,
                    Integer.MAX_VALUE);
            /*EnchantsAbilitiesUtil.spawnLightning(
                    user,
                    (LivingEntity) target);*/
            //user.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1, 0));
        }
    }
}


