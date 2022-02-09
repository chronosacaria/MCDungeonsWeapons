package chronosacaria.mcdw.effects;

import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.api.util.McdwEnchantmentHelper;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;

public class EnchantmentEffects {

    public static float ambushDamage (LivingEntity ambushingEntity, LivingEntity ambushee) {
        int ambushLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(ambushingEntity, EnchantsRegistry.AMBUSH);
        if (ambushLevel > 0) {

            if (ambushingEntity.isInvisible() && ambushingEntity.isSneaking()) {
                ambushee.world.playSound(
                        null,
                        ambushee.getX(),
                        ambushee.getY(),
                        ambushee.getZ(),
                        SoundEvents.BLOCK_POINTED_DRIPSTONE_LAND,
                        SoundCategory.PLAYERS,
                        0.5F,
                        1.0F);
                return 1 + (ambushLevel * 0.15f);
            }
        }
        return 1;
    }

    public static float committedDamage (LivingEntity committedEntity, LivingEntity target) {

        int committedLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(committedEntity, EnchantsRegistry.COMMITTED);
        if (committedLevel > 0) {

            float getTargetRemainingHealth = MathHelper.clamp(target.getHealth() / target.getMaxHealth(), 0, 1);
            float attributeDamage = (float) committedEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            float committedMultiplier = 0.2F * committedLevel;

            if (CleanlinessHelper.percentToOccur(30)) {
                target.world.playSound(
                        null,
                        target.getX(),
                        target.getY(),
                        target.getZ(),
                        SoundEvents.ENTITY_GENERIC_EXPLODE,
                        SoundCategory.PLAYERS,
                        0.5F,
                        1.0F);
                return MathHelper.clamp(attributeDamage * (1 - getTargetRemainingHealth) * committedMultiplier, 1, 3 * committedLevel);
            }
        }
        return 0;
    }

    public static void applyCharge (PlayerEntity playerEntity) {
        int chargeLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(playerEntity, EnchantsRegistry.CHARGE);
        if (chargeLevel > 0) {

            if (CleanlinessHelper.percentToOccur(10))
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, chargeLevel * 20, 4));
        }
    }
}
