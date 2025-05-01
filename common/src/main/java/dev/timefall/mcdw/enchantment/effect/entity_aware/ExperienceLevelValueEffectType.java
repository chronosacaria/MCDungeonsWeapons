/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchantment.effect.entity_aware;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.timefall.mcdw.enchantment.effect.EntityAwareValueEffect;
import me.fzzyhmstrs.fzzy_config.util.Expression;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Map;

public record ExperienceLevelValueEffectType(Expression expression) implements dev.timefall.mcdw.enchantment.effect.EntityAwareValueEffect {

    public static final MapCodec<ExperienceLevelValueEffectType> CODEC = RecordCodecBuilder.mapCodec(instance ->
        instance.group(
            Expression.Impl.getCODEC().fieldOf("expression").forGetter(ExperienceLevelValueEffectType::expression)
        ).apply(instance, ExperienceLevelValueEffectType::new)
    );

    @Override
    public float apply(int level, float input, LivingEntity livingEntity) {
        // bail if it's not a player. need xp
        if (!(livingEntity instanceof PlayerEntity player)) return input;

        //get xp levels
        int xpLevels = player.experienceLevel;

        //prepare the Expression map
        Map<Character, Double> expressionMap = Map.of(
            'x', (double) xpLevels,
            'l', (double) level
        );

        //evaluate the expression, with a safe fallback to 0.0 added multiplier
        double multiplier = expression.evalSafe(expressionMap,0.0);

        //return the multiplied input
        return input * (1f + (float) multiplier);
    }

    @Override
    public MapCodec<? extends EntityAwareValueEffect> getCodec() {
        return CODEC;
    }
}