package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.configs.McdwConfig;
import chronosacaria.mcdw.enchants.types.AOEEnchantment;
import chronosacaria.mcdw.enchants.types.DamageBoostEnchantment;
import chronosacaria.mcdw.enchants.util.AOEHelper;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageRecord;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Committed extends DamageBoostEnchantment {
    McdwConfig config = AutoConfig.getConfigHolder(McdwConfig.class).getConfig();

    public Committed(Enchantment.Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT, new Identifier(Mcdw.MOD_ID, "committed"), this);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return config.enableAOEMixing || !(other instanceof Leeching);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {

        float getTargetHealth = ((LivingEntity) target).getHealth();
        float getTargetMaxHealth = ((LivingEntity) target).getMaxHealth();
        float getTargetRemainingHealth = getTargetHealth / getTargetMaxHealth;
        float getOriginalDamage = getTargetMaxHealth - getTargetRemainingHealth;
        float extraDamageMultiplier = 0.1F + level * 0.1F;
        float getExtraDamage = (getOriginalDamage * (1 - getTargetRemainingHealth) * extraDamageMultiplier);

        float chance = user.getRandom().nextFloat();
        if (chance <= 0.2) {
            if ((Math.abs(getTargetHealth)) < (Math.abs(getTargetMaxHealth))) {
                ((LivingEntity) target).damage(DamageSource.player((PlayerEntity) user),
                        getExtraDamage);
                target.world.playSound(
                        (PlayerEntity) null,
                        target.getX(),
                        target.getY(),
                        target.getZ(),
                        SoundEvents.ENTITY_GENERIC_EXPLODE,
                        SoundCategory.PLAYERS,
                        1.0F,
                        1.0F);
            }
        }
    }
}