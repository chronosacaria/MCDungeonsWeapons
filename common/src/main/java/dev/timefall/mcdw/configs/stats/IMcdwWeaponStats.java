/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.configs.stats;

import me.fzzyhmstrs.fzzy_config.util.Walkable;
import me.fzzyhmstrs.fzzy_config.validation.minecraft.ValidatedIngredient;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedDouble;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedFloat;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.Set;
@SuppressWarnings("DuplicatedCode")
public interface IMcdwWeaponStats {

    class MeleeStats implements Walkable {
        public boolean isEnabled = true;
        public boolean isLootable = true;
        public ToolMaterial material = ToolMaterials.IRON;
        public int damage = 0;
        @ValidatedFloat.Restrict(min = -4f, max = 0f)
            public float attackSpeed = -3f;
        @ValidatedDouble.Restrict(min = -3.0, max = 61.0)
            public double additionalAttackRange = 0d;
        @ValidatedInt.Restrict(min = 0)
            public int weaponSpawnWeight = 0;
        public ValidatedIngredient repairIngredient = new ValidatedIngredient(Identifier.of("iron_ingot"));


    }

    static MeleeStats meleeStats(
            boolean isEnabled,
            boolean isLootable,
            ToolMaterial material,
            int damage,
            float attackSpeed,
            double additionalAttackRange,
            int weaponSpawnWeight,
            Identifier repairIngredient)
    {
        MeleeStats stats = new MeleeStats();
        stats.isEnabled = isEnabled;
        stats.isLootable = isLootable;
        stats.material = material;
        stats.damage = damage;
        stats.attackSpeed = attackSpeed;
        stats.additionalAttackRange = additionalAttackRange;
        stats.weaponSpawnWeight = weaponSpawnWeight;
        stats.repairIngredient = new ValidatedIngredient(repairIngredient);
        return stats;
    }

    static MeleeStats meleeStats(
            boolean isEnabled,
            boolean isLootable,
            ToolMaterial material,
            int damage,
            float attackSpeed,
            double additionalAttackRange,
            int weaponSpawnWeight,
            Set<Object> repairIngredients)
    {
        MeleeStats stats = new MeleeStats();
        stats.isEnabled = isEnabled;
        stats.isLootable = isLootable;
        stats.material = material;
        stats.damage = damage;
        stats.attackSpeed = attackSpeed;
        stats.additionalAttackRange = additionalAttackRange;
        stats.weaponSpawnWeight = weaponSpawnWeight;
        stats.repairIngredient = new ValidatedIngredient(repairIngredients);
        return stats;
    }

    static MeleeStats meleeStats(
            boolean isEnabled,
            boolean isLootable,
            ToolMaterial material,
            int damage,
            float attackSpeed,
            double additionalAttackRange,
            int weaponSpawnWeight,
            TagKey<Item> repairIngredientTag)
    {
        MeleeStats stats = new MeleeStats();
        stats.isEnabled = isEnabled;
        stats.isLootable = isLootable;
        stats.material = material;
        stats.damage = damage;
        stats.attackSpeed = attackSpeed;
        stats.additionalAttackRange = additionalAttackRange;
        stats.weaponSpawnWeight = weaponSpawnWeight;
        stats.repairIngredient = new ValidatedIngredient(repairIngredientTag);
        return stats;
    }


    class RangedStats implements Walkable {
        public boolean isEnabled = true;
        public boolean isLootable = true;
        public ToolMaterial material = ToolMaterials.IRON;
        @ValidatedInt.Restrict(min = 0)
            public int projectileDamage = 6;
        @ValidatedInt.Restrict(min = 6, max = 40)
            public int drawSpeed = 20;
        @ValidatedInt.Restrict(min = 5, max = 25)
            public int range = 15;
        @ValidatedInt.Restrict(min = 0)
            public int weaponSpawnWeight = 0;
        public ValidatedIngredient repairIngredient = new ValidatedIngredient(Identifier.of("iron_ingot"));
    }

    static RangedStats rangedStats(
            boolean isEnabled,
            boolean isLootable,
            ToolMaterial material,
            int projectileDamage,
            int drawSpeed,
            int range,
            int weaponSpawnWeight,
            Identifier repairIngredient)
    {
        RangedStats stats = new RangedStats();
        stats.isEnabled = isEnabled;
        stats.isLootable = isLootable;
        stats.material = material;
        stats.projectileDamage = projectileDamage;
        stats.drawSpeed = drawSpeed;
        stats.range = range;
        stats.weaponSpawnWeight = weaponSpawnWeight;
        stats.repairIngredient = new ValidatedIngredient(repairIngredient);
        return stats;
    }

    static RangedStats rangedStats(
            boolean isEnabled,
            boolean isLootable,
            ToolMaterial material,
            int projectileDamage,
            int drawSpeed,
            int range,
            int weaponSpawnWeight,
            Set<Object> repairIngredients)
    {
        RangedStats stats = new RangedStats();
        stats.isEnabled = isEnabled;
        stats.isLootable = isLootable;
        stats.material = material;
        stats.projectileDamage = projectileDamage;
        stats.drawSpeed = drawSpeed;
        stats.range = range;
        stats.weaponSpawnWeight = weaponSpawnWeight;
        stats.repairIngredient = new ValidatedIngredient(repairIngredients);
        return stats;
    }

    static RangedStats rangedStats(
            boolean isEnabled,
            boolean isLootable,
            ToolMaterial material,
            int projectileDamage,
            int drawSpeed,
            int range,
            int weaponSpawnWeight,
            TagKey<Item> repairIngredientTag)
    {
        RangedStats stats = new RangedStats();
        stats.isEnabled = isEnabled;
        stats.isLootable = isLootable;
        stats.material = material;
        stats.projectileDamage = projectileDamage;
        stats.drawSpeed = drawSpeed;
        stats.range = range;
        stats.weaponSpawnWeight = weaponSpawnWeight;
        stats.repairIngredient = new ValidatedIngredient(repairIngredientTag);
        return stats;
    }

    class ShieldStats implements Walkable {
        public boolean isEnabled = true;
        public boolean isLootable = true;
        public ToolMaterial material = ToolMaterials.IRON;
        public int weaponSpawnWeight = 0;
        public ValidatedIngredient repairIngredient = new ValidatedIngredient(Identifier.of("iron_ingot"));
    }

    static ShieldStats shieldStats(
            boolean isEnabled,
            boolean isLootable,
            ToolMaterial material,
            int weaponSpawnWeight,
            Identifier repairIngredient)
    {
        ShieldStats stats = new ShieldStats();
        stats.isEnabled = isEnabled;
        stats.isLootable = isLootable;
        stats.material = material;
        stats.weaponSpawnWeight = weaponSpawnWeight;
        stats.repairIngredient = new ValidatedIngredient(repairIngredient);
        return stats;
    }

    static ShieldStats shieldStats(
            boolean isEnabled,
            boolean isLootable,
            ToolMaterial material,
            int weaponSpawnWeight,
            Set<Object> repairIngredients)
    {
        ShieldStats stats = new ShieldStats();
        stats.isEnabled = isEnabled;
        stats.isLootable = isLootable;
        stats.material = material;
        stats.weaponSpawnWeight = weaponSpawnWeight;
        stats.repairIngredient = new ValidatedIngredient(repairIngredients);
        return stats;
    }

    static ShieldStats shieldStats(
            boolean isEnabled,
            boolean isLootable,
            ToolMaterial material,
            int weaponSpawnWeight,
            TagKey<Item> repairIngredientTag)
    {
        ShieldStats stats = new ShieldStats();
        stats.isEnabled = isEnabled;
        stats.isLootable = isLootable;
        stats.material = material;
        stats.weaponSpawnWeight = weaponSpawnWeight;
        stats.repairIngredient = new ValidatedIngredient(repairIngredientTag);
        return stats;
    }
}