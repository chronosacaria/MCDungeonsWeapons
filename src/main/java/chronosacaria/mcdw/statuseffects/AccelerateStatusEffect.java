package chronosacaria.mcdw.statuseffects;

import chronosacaria.mcdw.Mcdw;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AccelerateStatusEffect extends StatusEffect {
    public AccelerateStatusEffect(StatusEffectCategory statusEffectCategory, int color, String id) {
        super(statusEffectCategory, color);
        Registry.register(Registry.STATUS_EFFECT, new Identifier(Mcdw.MOD_ID, id), this);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}