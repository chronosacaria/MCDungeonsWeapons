package chronosacaria.mcdw.enchants.summons;

public interface IBeeSummoning {
    void mcdw$setLastSummonedBee(int time);
    int mcdw$getLastSummonedBee();

    default boolean isReadyForBeeSummon(int summonerAge) {
        return summonerAge > mcdw$getLastSummonedBee();
    }

    default void onBeeSummoned(int summonerAge) {
        mcdw$setLastSummonedBee(summonerAge);
    }
}