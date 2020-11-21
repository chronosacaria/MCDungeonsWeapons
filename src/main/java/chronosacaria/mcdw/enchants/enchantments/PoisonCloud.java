package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.util.EnchantsAbilitiesUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PoisonCloud extends Enchantment {
    public PoisonCloud(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT,new Identifier(Mcdw.MOD_ID, "poison_cloud"),this);
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level){

        double chance = Math.random();
        //Spawn Poison Cloud @ 30% chance
        if (target instanceof LivingEntity) {
            if (chance <= 0.3) {
                EnchantsAbilitiesUtil.spawnPoisonCloud(
                        user,
                        (LivingEntity) target,
                        level - 1);
            }
        }
    }
}
