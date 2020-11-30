package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.configs.McdwConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enchants.types.AOEEnchantment;
import chronosacaria.mcdw.enchants.util.AOEHelper;
import chronosacaria.mcdw.sounds.McdwSoundEvents;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Swirling extends AOEEnchantment {
    McdwConfig config = AutoConfig.getConfigHolder(McdwConfig.class).getConfig();

    public static final float SWIRLING_DAMAGE_MULTIPLIER = 0.5F;

    public Swirling(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT, new Identifier(Mcdw.MOD_ID, "swirling"), this);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    protected boolean canAccept (Enchantment other){
        return config.enableAOEMixing || !(other instanceof AOEEnchantment);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {

        //Spawn Lightning @ 10%/20%/30% chance respective of level
        if (!(target instanceof LivingEntity)) return;

        float attackDamage = (float)user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        float cooledAttackStrength = 0.5F;
        attackDamage *= 0.2F + cooledAttackStrength * cooledAttackStrength * 0.8F;

        float swirlingDamage = attackDamage * SWIRLING_DAMAGE_MULTIPLIER;
        swirlingDamage *= (level + 1) / 2.0F;
        target.world.playSound(
                (PlayerEntity)null,
                target.getX(),
                target.getY(),
                target.getZ(),
                SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,
                SoundCategory.PLAYERS,
                64.0F,
                1.0F);
        AOEHelper.causeSwirlingAttack((PlayerEntity)user, (LivingEntity)target, swirlingDamage, 1.5f);
    }
}

