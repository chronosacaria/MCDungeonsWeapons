package chronosacaria.mcdw.api.interfaces;

public interface IDualWielding {

    float mcdw$getOffhandAttackCooldownProgressPerTick();

    float mcdw$getOffhandAttackCooldownProgress(float baseTime);

    void mcdw$resetLastAttackedOffhandTicks();

    int mcdw$getOffhandAttackedTicks();

    void mcdw$setOffhandAttackedTicks(int lastAttackedOffhandTicks);
}
