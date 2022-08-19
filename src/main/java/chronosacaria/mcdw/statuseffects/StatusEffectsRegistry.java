package chronosacaria.mcdw.statuseffects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class StatusEffectsRegistry {
    public static StatusEffect ACCELERATE;
    public static StatusEffect DYNAMO;
    public static StatusEffect PAIN_CYCLE;
    public static StatusEffect SHADOW_FORM;

    public static void init() {
        ACCELERATE = new AccelerateStatusEffect(StatusEffectCategory.BENEFICIAL, 0x036edc, "accelerate");
        DYNAMO = new DynamoStatusEffect(StatusEffectCategory.BENEFICIAL, 0xffbf00, "dynamo");
        PAIN_CYCLE = new PainCycleStatusEffect(StatusEffectCategory.NEUTRAL, 0x640004, "pain_cycle");
        SHADOW_FORM = new ShadowFormStatusEffect(StatusEffectCategory.BENEFICIAL, 0x40023e, "shadow_form");
    }
}