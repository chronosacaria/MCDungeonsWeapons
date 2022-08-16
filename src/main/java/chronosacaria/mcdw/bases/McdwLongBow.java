
package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.RarityHelper;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.UseAction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

public class McdwLongBow extends BowItem {

    public final ToolMaterial material;
    public final float drawSpeed;
    public static float maxBowRange;
    private final ParticleEffect type;
    String[] repairIngredient;

    public McdwLongBow(ToolMaterial material, float drawSpeed, float maxBowRangePar, String[] repairIngredient) {
        super(new Item.Settings().group(Mcdw.RANGED)
                .maxCount(1)
                .maxDamage(material.getDurability())
                .rarity(RarityHelper.fromToolMaterial(material))
        );
        this.material = material;
        this.drawSpeed = drawSpeed;
        this.repairIngredient = repairIngredient;
        maxBowRange = maxBowRangePar;
        type = null;
    }

    public float getDrawSpeed() {
        return Math.max(0, drawSpeed);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return BOW_PROJECTILES;
    }

    @Override
    public int getRange() {
        return (int) maxBowRange;
    }

    @Override
    public int getEnchantability() {
        return material.getEnchantability();
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        List<Item> potentialIngredients = new ArrayList<>(List.of());
        AtomicBoolean isWood = new AtomicBoolean(false);
        AtomicBoolean isStone = new AtomicBoolean(false);
        Arrays.stream(repairIngredient).toList().forEach(repIngredient -> {
            if (repIngredient.contentEquals("minecraft:planks"))
                isWood.set(true);
            else if (repIngredient.contentEquals("minecraft:stone_crafting_materials"))
                isStone.set(true);
            potentialIngredients.add(
                    Registry.ITEM.get(new Identifier(repIngredient)));
        });

        return potentialIngredients.contains(ingredient.getItem())
                || (isWood.get() && ingredient.isIn(ItemTags.PLANKS)
                || (isStone.get() && ingredient.isIn(ItemTags.STONE_CRAFTING_MATERIALS)));
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        super.appendTooltip(stack, world, tooltip, tooltipContext);
        int i = 1;
        String str = stack.getItem().getTranslationKey().toLowerCase(Locale.ROOT).substring(14);
        String translationKey = String.format("tooltip_info_item.mcdw.%s_", str);
        while (I18n.hasTranslation(translationKey + i)) {
            tooltip.add(Text.translatable(translationKey + i).formatted(Formatting.ITALIC));
            i++;
        }
        tooltip.add(Text.translatable("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip_note_item.mcdw.longbow").formatted(Formatting.GREEN));
    }
}