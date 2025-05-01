/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.api.interfaces;

import java.util.List;

public interface IExclusiveAOECloud {

    @SuppressWarnings("unused")
    List<Boolean> mcdw$getExclusions();
    void mcdw$setExclusions(boolean owner, boolean allies, boolean enemy);
}