package chronosacaria.mcdw.configs;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnchantmentIDConfigHelper {
    List<String> comment;
    float offset = 0.0f;
    boolean isEnabled = true;
    boolean isAvailableForEnchantedBookOffer = true;
    boolean isAvailableForRandomSelection = true;
    int maxLevel = 3;
    @Nullable Integer procChance = 0;

    public float mcdw$getOffset(){
        return offset;
    }
    public boolean mcdw$getIsEnabled() {
        return isEnabled;
    }
    public boolean mcdw$getIsAvailableForEnchantedBookOffer() {
        return isAvailableForEnchantedBookOffer;
    }
    public boolean mcdw$getIsAvailableForRandomSelection() {
        return isAvailableForRandomSelection;
    }
    public int mcdw$getMaxLevel() {
        return maxLevel;
    }
    public @Nullable Integer mcdw$getProcChance() {
        return procChance;
    }

    @SuppressWarnings("unused")
    public EnchantmentIDConfigHelper(){
    }

    public EnchantmentIDConfigHelper(boolean isEnabled, boolean isAvailableForEnchantedBookOffer, boolean isAvailableForRandomSelection, int maxLevel, @Nullable Integer procChance) {
        this.isEnabled = isEnabled;
        this.isAvailableForEnchantedBookOffer = isAvailableForEnchantedBookOffer;
        this.isAvailableForRandomSelection = isAvailableForRandomSelection;
        this.maxLevel = maxLevel;
        this.procChance = procChance;
    }

    public EnchantmentIDConfigHelper(List<String> comment, boolean isEnabled, boolean isAvailableForEnchantedBookOffer, boolean isAvailableForRandomSelection, int maxLevel, @Nullable Integer procChance, float effectOffset) {
        this.comment = comment;
        this.isEnabled = isEnabled;
        this.isAvailableForEnchantedBookOffer = isAvailableForEnchantedBookOffer;
        this.isAvailableForRandomSelection = isAvailableForRandomSelection;
        this.maxLevel = maxLevel;
        this.procChance = procChance;
        this.offset = effectOffset;
    }

}
