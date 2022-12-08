package chronosacaria.mcdw.statuseffects;

import chronosacaria.mcdw.Mcdw;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ShadowFormStatusEffect extends StatusEffect {
    public ShadowFormStatusEffect(StatusEffectCategory statusEffectCategory, int color, String id) {
        super(statusEffectCategory, color);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(Mcdw.MOD_ID, id), this);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier){
        super.onApplied(entity, attributes, amplifier);
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 80, amplifier,false,false,false));
    }
}
