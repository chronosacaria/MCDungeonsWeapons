
package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IRangedWeapon;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.LongBowsID;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

public class McdwLongBow extends BowItem implements IRangedWeapon {

    public static float chargeTime = 60.0f;

    public final ToolMaterial material;
    public final float drawSpeed;
    public static float maxBowRange;
    private final ParticleEffect type;

    public McdwLongBow(ToolMaterial material, float drawSpeed, float maxBowRangePar) {
        this(material, drawSpeed, maxBowRangePar, null);
    }

    public McdwLongBow(ToolMaterial material, float drawSpeed, float maxBowRangePar, ParticleEffect particles) {
        super(new Item.Settings().group(Mcdw.RANGED)
                .maxCount(1)
                .maxDamage(material.getDurability())
                .rarity(RarityHelper.fromToolMaterial(material))
        );
        this.material = material;
        this.drawSpeed = drawSpeed;
        maxBowRange = maxBowRangePar;
        type = particles;
    }

    public float getDrawSpeed() {
        return Math.max(0, drawSpeed);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000 - (int)(drawSpeed);
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
        return this.material.getRepairIngredient().test(ingredient) || super.canRepair(stack, ingredient);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        super.appendTooltip(stack, world, tooltip, tooltipContext);
        for (LongBowsID longBowsID : LongBowsID.values()) {
            if (stack.getItem() == ItemsInit.longBowItems.get(longBowsID)) {
                int i = 1;
                String str = longBowsID.toString().toLowerCase(Locale.ROOT).substring(4);
                String translationKey = String.format("tooltip_info_item.mcdw.%s_", str);
                while (I18n.hasTranslation(translationKey + i)) {
                    tooltip.add(Text.translatable(translationKey + i).formatted(Formatting.ITALIC));
                    i++;
                }
                tooltip.add(Text.translatable("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
                tooltip.add(Text.translatable("tooltip_note_item.mcdw.longbow").formatted(Formatting.GREEN));
                break;
            }
        }
    }
}