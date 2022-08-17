package chronosacaria.mcdw.api.interfaces;

import net.minecraft.util.math.MathHelper;

public interface IDualWielding {

    float getOffhandAttackCooldownProgressPerTick();

    float getAttackCooldownProgress(float baseTime);

    void resetLastAttackedOffhandTicks();
}
