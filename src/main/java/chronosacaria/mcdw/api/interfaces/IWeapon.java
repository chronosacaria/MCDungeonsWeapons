package chronosacaria.mcdw.api.interfaces;

public interface IWeapon {

    void setFuseShotcounter(int comboTimer);
    int getfuseShotCounter();

    float getBowChargeTime();
    void setBowChargeTime(float bowChargeTime);

    int getCrossbowChargeTime();
    void setCrossbowChargeTime(int crossbowChargeTime);

    long getLastFiredTime();
    void setLastFiredTime(long lastFiredTime);
}
