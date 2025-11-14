
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
import dev.timefall.mcdw.registries.items.McdwBowItemRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

import static dev.timefall.mcdw.api.util.RangedAttackHelper.getVanillaBowChargeTime;

@SuppressWarnings("UnusedAssignment")
public class McdwBowItem extends BowItem implements IMcdwDrawSpeed {

    private final ToolMaterial material;
    @SuppressWarnings("CanBeFinal")
    IMcdwWeaponStats.RangedStats itemStats;
    public final int drawSpeed;
    @SuppressWarnings("CanBeFinal")
    public int maxBowRange;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final ParticleEffect type;

    public McdwBowItem(IMcdwWeaponStats.RangedStats itemStats, ToolMaterial material, Item.Settings settings, int drawSpeed, int maxBowRangePar) {
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

    public static float getBowArrowVelocity(ItemStack stack, int charge) {
        float bowChargeTime = getVanillaBowChargeTime(stack);
        if (bowChargeTime <= 0){
            bowChargeTime = 1;
        }

        float arrowVelocity = (float) charge / 30;
        arrowVelocity = (arrowVelocity * arrowVelocity + arrowVelocity * 2.0F) / 3.0F;
        if (arrowVelocity > 1.0F) {
            arrowVelocity = 1.0F;
        }

        return arrowVelocity;
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
        if (stack.getItem() == McdwBowItemRegistry.BOW_HUNTERS_PROMISE)
            tooltip.add(Text.translatable("tooltip_ench_item.mcdw.hunters_promise_1").formatted(Formatting.GRAY));
    }
}