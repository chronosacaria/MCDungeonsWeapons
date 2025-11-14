/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.bases;

import com.google.common.collect.BiMap;
import dev.timefall.mcdw.api.util.CleanlinessHelper;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.mixin.old_mixins.mcdw.InsulatedAxeItemAccessor;
import dev.timefall.mcdw.registries.ItemGroupRegistry;
import dev.timefall.mcdw.registries.items.McdwSwordItemRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;
import java.util.Optional;

public class McdwSwordItem extends SwordItem {
    private final ToolMaterial material;
    @SuppressWarnings("CanBeFinal")
    IMcdwWeaponStats.MeleeStats itemStats;

    public McdwSwordItem(IMcdwWeaponStats.MeleeStats itemStats, ToolMaterial material, Item.Settings settings) {
        super(material, settings);
        this.itemStats = itemStats;
        this.material = material;

        ItemGroupEvents.modifyEntriesEvent(ItemGroupRegistry.MELEE).register(entries -> entries.add(this.getDefaultStack()));
    }

    @Override
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

    @SuppressWarnings("rawtypes")
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        BlockState blockState = world.getBlockState(blockPos);
        Optional<BlockState> strippedState = this.getStrippedState(blockState);
        Optional<BlockState> decreasedOxidationState = Oxidizable.getDecreasedOxidationState(blockState);
        Optional<BlockState> blockStateOptional = Optional.ofNullable((Block)((BiMap)HoneycombItem.WAXED_TO_UNWAXED_BLOCKS.get()).get(blockState.getBlock())).map((block) -> block.getStateWithProperties(blockState));
        ItemStack itemStack = context.getStack();
        Optional<BlockState> empty = Optional.empty();
        if (context.getStack().isOf(McdwSwordItemRegistry.SWORD_MECHANIZED_SAWBLADE)) {
            if (strippedState.isPresent()) {
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                empty = strippedState;
            } else if (decreasedOxidationState.isPresent()) {
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.syncWorldEvent(playerEntity, 3005, blockPos, 0);
                empty = decreasedOxidationState;
            } else if (blockStateOptional.isPresent()) {
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.syncWorldEvent(playerEntity, 3004, blockPos, 0);
                empty = blockStateOptional;
            }

            if (empty.isPresent()) {
                if (playerEntity instanceof ServerPlayerEntity) {
                    Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos, itemStack);
                }

                world.setBlockState(blockPos, empty.get(), 11);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(playerEntity, empty.get()));
                if (playerEntity != null) {
                    itemStack.damage(1, playerEntity, EquipmentSlot.MAINHAND);
                }

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        }
        return ActionResult.PASS;
    }

    //@Override
    //public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
    //    if (stack.isOf(McdwSwordItemRegistry.SWORD_MECHANIZED_SAWBLADE.getItem())) {
    //        if (state.isIn(BlockTags.AXE_MINEABLE))
    //            return 8.0F;
    //    }
    //    return super.getMiningSpeedMultiplier(stack, state);
    //}


    private Optional<BlockState> getStrippedState(BlockState state) {
        return Optional.ofNullable(InsulatedAxeItemAccessor.getSTRIPPED_BLOCKS().get(state.getBlock())).map((block) -> block.getDefaultState().with(PillarBlock.AXIS, state.get(PillarBlock.AXIS)));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext tooltipContext, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, tooltipContext, tooltip, type);
        CleanlinessHelper.mcdw$tooltipHelper(stack, tooltip, 16);
    }
}
