package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.configs.McdwConfig;
import chronosacaria.mcdw.enchants.types.AOEEnchantment;
import chronosacaria.mcdw.enchants.util.AOEHelper;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Thundering extends AOEEnchantment {
    McdwConfig config = AutoConfig.getConfigHolder(McdwConfig.class).getConfig();

    public Thundering(Enchantment.Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT,new Identifier(Mcdw.MOD_ID, "thundering"),this);
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    protected boolean canAccept (Enchantment other){
        return config.enableAOEMixing || !(other instanceof AOEEnchantment);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level){

        //Spawn Lightning @ 10%/20%/30% chance respective of level
        if (!(target instanceof LivingEntity)) return;
        float chance = user.getRandom().nextFloat();
        if (chance <= 0.1F * level) {
            AOEHelper.electrocuteNearbyEnemies(
                    user,
                    target,
                    5,
                    10,
                    Integer.MAX_VALUE);
            /*AOECloudHelper.spawnLightning(
                    user,
                    (LivingEntity) target);*/
            //user.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1, 0));
        }
    }
}


