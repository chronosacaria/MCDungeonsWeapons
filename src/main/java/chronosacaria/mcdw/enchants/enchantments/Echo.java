package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enchants.types.AOEEnchantment;
import chronosacaria.mcdw.enchants.util.AOEHelper;
import chronosacaria.mcdw.enchants.util.McdwEnchantmentHelper;
import chronosacaria.mcdw.sounds.McdwSoundEvents;
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

public class Echo extends AOEEnchantment {
    public Echo(Enchantment.Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT, new Identifier(Mcdw.MOD_ID, "echo"), this);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {

        //Spawn Lightning @ 10%/20%/30% chance respective of level
        if (!(target instanceof LivingEntity)) return;
        float attackDamage = (float)user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        float cooledAttackStrength = 0.5F;
        attackDamage *= 0.2F + cooledAttackStrength * cooledAttackStrength * 0.8F;
        float chance = user.getRandom().nextFloat();
        if (chance <= 0.1F * level) {
            AOEHelper.causeEchoAttack(user, target, attackDamage, 3.0f, level);
            user.world.playSound(
                    (PlayerEntity)null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    McdwSoundEvents.ECHO_SOUND_EVENT,
                    SoundCategory.PLAYERS,
                    0.5F,
                    1.0F);
        }
    }
}
