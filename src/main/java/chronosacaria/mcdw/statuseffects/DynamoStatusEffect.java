package chronosacaria.mcdw.statuseffects;

import chronosacaria.mcdw.Mcdw;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DynamoStatusEffect extends StatusEffect {
    public DynamoStatusEffect(StatusEffectCategory statusEffectCategory, int color, String id) {
        super(statusEffectCategory, color);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(Mcdw.MOD_ID, id), this);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier){
        return true;
    }
}
