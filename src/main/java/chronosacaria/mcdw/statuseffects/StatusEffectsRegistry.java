package chronosacaria.mcdw.statuseffects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class StatusEffectsRegistry {
    public static StatusEffect DYNAMO;
    public static StatusEffect PAIN_CYCLE;
    public static StatusEffect SHADOW_FORM;

    public static void init() {
        DYNAMO = new DynamoStatusEffect(StatusEffectCategory.BENEFICIAL, 0xffbf00, "dynamo");
        PAIN_CYCLE = new PainCycleStatusEffect(StatusEffectCategory.NEUTRAL, 0x640004, "pain_cycle");
        SHADOW_FORM = new ShadowFormStatusEffect(StatusEffectCategory.BENEFICIAL, 0x40023e, "shadow_form");
    }
}
