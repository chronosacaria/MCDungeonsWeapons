package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.types.RangedEnchantment;
import chronosacaria.mcdw.api.util.ProjectileEffectHelper;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RicochetEnchantment extends RangedEnchantment {
    McdwEnchantsConfig config = AutoConfig.getConfigHolder(McdwEnchantsConfig.class).getConfig();
    public RicochetEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT,new Identifier(Mcdw.MOD_ID, "ricochet"),this);
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level){
        if (config.mixinRicochet) {
            float damageMultiplier;
            damageMultiplier = 0.1F + (level - 1 * 0.07F);
            float arrowVelocity = McdwBow.maxBowRange;
            if (arrowVelocity > 0.1F) {
                ProjectileEffectHelper.riochetArrowTowardsOtherEntity((LivingEntity) target, 10, damageMultiplier,
                        arrowVelocity);
            }
        }
    }
}