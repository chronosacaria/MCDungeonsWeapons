package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.types.DamageBoostEnchantment;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Leeching extends Enchantment{
    McdwEnchantsConfig config = AutoConfig.getConfigHolder(McdwEnchantsConfig.class).getConfig();

    public Leeching (Enchantment.Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT,new Identifier(Mcdw.MOD_ID, "leeching"),this);
    }

    public int getMaxLevel() {
        return 3;
    }

    protected boolean canAccept (Enchantment other){
        return config.extraLeeching || !(other instanceof DamageBoostEnchantment);
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
}