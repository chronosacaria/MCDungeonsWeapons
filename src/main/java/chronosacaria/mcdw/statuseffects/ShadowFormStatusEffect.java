package chronosacaria.mcdw.statuseffects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class ShadowFormStatusEffect extends StatusEffect{
    public ShadowFormStatusEffect(){
        super(StatusEffectType.BENEFICIAL, 0x98D982);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

}

    //public static StatusEffect SHIELDING;
    //public static StatusEffect SOUL_PROTECTION;
    //public static StatusEffect STUNNED;
    //public static StatusEffect SHADOW_FORM;