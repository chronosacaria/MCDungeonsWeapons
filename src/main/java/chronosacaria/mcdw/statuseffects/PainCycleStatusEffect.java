package chronosacaria.mcdw.statuseffects;

import chronosacaria.mcdw.Mcdw;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PainCycleStatusEffect extends StatusEffect {
    public PainCycleStatusEffect(StatusEffectCategory statusEffectCategory, int color, String id) {
        super(statusEffectCategory, color);
        Registry.register(Registry.STATUS_EFFECT, new Identifier(Mcdw.MOD_ID, id), this);
    }

    private int lastPain = 0;

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier){
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);
        lastPain++;
        if (lastPain == 300) {
            entity.damage(DamageSource.MAGIC, 2);
            lastPain = 0;
        }
    }
}
