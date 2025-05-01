/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchantment.summons;

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