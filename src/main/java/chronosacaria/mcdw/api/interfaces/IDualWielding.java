package chronosacaria.mcdw.api.interfaces;

import net.minecraft.util.math.MathHelper;

public interface IDualWielding {

    float getOffhandAttackCooldownProgressPerTick();

    float getOffhandAttackCooldownProgress(float baseTime);

    void resetLastAttackedOffhandTicks();
}
