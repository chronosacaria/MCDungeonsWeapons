package chronosacaria.mcdw.statuseffects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class StunnedStatusEffect extends StatusEffect{
    public StunnedStatusEffect(StatusEffectType type, int color){
        super(type, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier){
        entity.setVelocityClient(0.0,0.0,0.0);
    }
}

    //public static StatusEffect SHIELDING;
    //public static StatusEffect SOUL_PROTECTION;
    //public static StatusEffect STUNNED;
    //public static StatusEffect SHADOW_FORM;