
/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.bases;

import dev.timefall.mcdw.api.interfaces.IMcdwDrawSpeed;
import dev.timefall.mcdw.api.util.CleanlinessHelper;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.registries.ItemGroupRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.text.Text;

import java.util.List;

// TODO Question: Should this extend BowItem or McdwBowItem?
public class McdwShortbowItem extends BowItem implements IMcdwDrawSpeed {

    private final ToolMaterial material;
    @SuppressWarnings("CanBeFinal")
    IMcdwWeaponStats.RangedStats itemStats;
    public final int drawSpeed;
    @SuppressWarnings("CanBeFinal")
    public int maxBowRange;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final ParticleEffect type;

    public McdwShortbowItem(IMcdwWeaponStats.RangedStats itemStats, ToolMaterial material, Item.Settings settings, int drawSpeed, int maxBowRangePar) {
        super(settings);
        this.itemStats = itemStats;
        this.material = material;
        this.drawSpeed = drawSpeed;
        this.maxBowRange = maxBowRangePar;
        type = null;

        ItemGroupEvents.modifyEntriesEvent(ItemGroupRegistry.RANGED).register(entries -> entries.add(this.getDefaultStack()));
    }

    @Override
    public float getDrawSpeed() {
        return Math.max(0, drawSpeed);
    }

    @Override
    public int getRange() {
        return this.maxBowRange;
    }

    public ToolMaterial getMaterial() {
        return this.material;
    }

    @Override
    public int getEnchantability() {
        return this.material.getEnchantability();
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return itemStats.repairIngredient.toIngredient().test(ingredient);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext tooltipContext, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, tooltipContext, tooltip, type);
        CleanlinessHelper.mcdw$tooltipHelper(stack, tooltip, 14);
    }
}