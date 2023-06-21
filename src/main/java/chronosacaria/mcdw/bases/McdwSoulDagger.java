package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.SoulDaggersID;
import chronosacaria.mcdw.registries.ItemsRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class McdwSoulDagger extends SwordItem implements IInnateEnchantment {
    String[] repairIngredient;
    SoulDaggersID soulDaggersEnum;
    public McdwSoulDagger(SoulDaggersID soulDaggersEnum, ToolMaterial material, int attackDamage, float attackSpeed, String[] repairIngredient) {
        super(material, attackDamage, attackSpeed,
                new Item.Settings().rarity(RarityHelper.fromToolMaterial(material)));
        this.soulDaggersEnum = soulDaggersEnum;
        ItemGroupEvents.modifyEntriesEvent(Mcdw.WEAPONS).register(entries -> entries.add(this.getDefaultStack()));
        this.repairIngredient = repairIngredient;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return CleanlinessHelper.canRepairCheck(repairIngredient, ingredient);
    }

    @Override
    public ItemStack getDefaultStack() {
        return getInnateEnchantedStack(this);
    }

    @Override
    public Map<Enchantment, Integer> getInnateEnchantments() {
        return this.soulDaggersEnum.getInnateEnchantments();
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        super.appendTooltip(stack, world, tooltip, tooltipContext);
        if (stack.getItem() == ItemsRegistry.SOUL_DAGGER_ITEMS.get(SoulDaggersID.DAGGER_SOUL_KNIFE)) {
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.soul_knife_1").formatted(Formatting.ITALIC));
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.soul_knife_2").formatted(Formatting.ITALIC));
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.soul_knife_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsRegistry.SOUL_DAGGER_ITEMS.get(SoulDaggersID.SWORD_TRUTHSEEKER)) {
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.truthseeker_1").formatted(Formatting.ITALIC));
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.truthseeker_2").formatted(Formatting.ITALIC));
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.truthseeker_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsRegistry.SOUL_DAGGER_ITEMS.get(SoulDaggersID.DAGGER_ETERNAL_KNIFE)) {
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.eternal_knife_1").formatted(Formatting.ITALIC));
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.eternal_knife_2").formatted(Formatting.ITALIC));
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.eternal_knife_3").formatted(Formatting.ITALIC));
        }
    }
}