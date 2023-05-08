package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.SwordsID;
import chronosacaria.mcdw.registries.ItemsRegistry;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableMap;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("rawtypes")
public class McdwSword extends SwordItem {
    protected static final Map<Block, Block> STRIPPED_BLOCKS;
    String[] repairIngredient;

    public McdwSword(ToolMaterial material, int attackDamage, float attackSpeed, String[] repairIngredient) {
        super(material, attackDamage, attackSpeed,
                new Item.Settings().group(Mcdw.WEAPONS).rarity(RarityHelper.fromToolMaterial(material)));
        this.repairIngredient = repairIngredient;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return CleanlinessHelper.canRepairCheck(repairIngredient, ingredient);
    }

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
        if (context.getStack().isOf(SwordsID.SWORD_MECHANIZED_SAWBLADE.getItem())) {
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
                    itemStack.damage(1, playerEntity, (p) -> p.sendToolBreakStatus(context.getHand()));
                }

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        }
        return ActionResult.PASS;
    }

    private Optional<BlockState> getStrippedState(BlockState state) {
        return Optional.ofNullable(STRIPPED_BLOCKS.get(state.getBlock())).map((block) -> block.getDefaultState().with(PillarBlock.AXIS, state.get(PillarBlock.AXIS)));
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
       super.appendTooltip(stack, world, tooltip, tooltipContext);
       int i = 1;
       String str = stack.getItem().getTranslationKey().toLowerCase(Locale.ROOT).substring(16);
       String translationKey = String.format("tooltip_info_item.mcdw.%s_", str);
       while (I18n.hasTranslation(translationKey + i)) {
           tooltip.add(Text.translatable(translationKey + i).formatted(Formatting.ITALIC));
           i++;
       }
        if (stack.getItem() == ItemsRegistry.SWORD_ITEMS.get(SwordsID.SWORD_BEESTINGER))
            tooltip.add(Text.translatable("tooltip_ench_item.mcdw.beestinger_1").formatted(Formatting.GRAY));
        if (stack.getItem() == ItemsRegistry.SWORD_ITEMS.get(SwordsID.SWORD_DIAMOND_SWORD_VAR)){
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.diamond_sword_1").formatted(Formatting.ITALIC));
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.diamond_sword_2").formatted(Formatting.ITALIC));
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.diamond_sword_3").formatted(Formatting.ITALIC));
        }
    }

    static {
        //noinspection unchecked
        STRIPPED_BLOCKS = (new ImmutableMap.Builder())
                .put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD)
                .put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG)
                .put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD)
                .put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG)
                .put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD)
                .put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG)
                .put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD)
                .put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG)
                .put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD)
                .put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG)
                .put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD)
                .put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG)
                .put(Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM)
                .put(Blocks.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE)
                .put(Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM)
                .put(Blocks.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE)
                .put(Blocks.MANGROVE_WOOD, Blocks.STRIPPED_MANGROVE_WOOD)
                .put(Blocks.MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG)
                .build();
    }

}
