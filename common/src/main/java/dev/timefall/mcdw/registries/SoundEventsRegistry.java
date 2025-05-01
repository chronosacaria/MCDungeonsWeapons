/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundEventsRegistry {
    public static final Identifier ECHO_SOUND = Identifier.of("mcdw:echo_sound");
    public static final RegistryEntry<SoundEvent> ECHO_SOUND_EVENT = registerSound(ECHO_SOUND,SoundEvent.of(ECHO_SOUND));

    public static void register(){}

    @SuppressWarnings("SameParameterValue")
    protected static RegistryEntry<SoundEvent> registerSound(Identifier soundIdentifier, SoundEvent soundEvent) {
        return Registry.registerReference(Registries.SOUND_EVENT, soundIdentifier, soundEvent);
    }
}