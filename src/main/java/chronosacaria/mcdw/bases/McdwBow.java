
package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IRangedWeapon;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.BowsID;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

import static chronosacaria.mcdw.api.util.RangedAttackHelper.getVanillaBowChargeTime;

public class McdwBow extends BowItem implements IRangedWeapon {

    public static float chargeTime = 30.0f;

    public final ToolMaterial material;
    public final float drawSpeed;
    public static float maxBowRange;
    private final ParticleEffect type;

    public McdwBow(ToolMaterial material, float drawSpeed, float maxBowRangePar) {
        this(material, drawSpeed, maxBowRangePar, null);
    }

    public McdwBow(ToolMaterial material, float drawSpeed, float maxBowRangePar, ParticleEffect particles) {
        super(new Item.Settings().group(Mcdw.RANGED)
                .maxCount(1)
                .maxDamage(100 + material.getDurability())
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

    public ParticleEffect getArrowParticles() {
        return type;
    }

    public static float getBowArrowVelocity(ItemStack stack, int charge) {
        float bowChargeTime = getVanillaBowChargeTime(stack);
        if (bowChargeTime <= 0){
            bowChargeTime = 1;
        }

        float arrowVelocity = (float) charge / chargeTime;
        arrowVelocity = (arrowVelocity * arrowVelocity + arrowVelocity * 2.0F) / 3.0F;
        if (arrowVelocity > 1.0F) {
            arrowVelocity = 1.0F;
        }

        return arrowVelocity;
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
        return (int) maxBowRange * 2 + 10;
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
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_ANCIENT_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.ancient_bow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.ancient_bow_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_BONEBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.bonebow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.bonebow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.bonebow_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_LOST_SOULS)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.lost_souls_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.lost_souls_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.lost_souls_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_ELITE_POWER_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.elite_power_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.elite_power_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.elite_power_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_HAUNTED_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.haunted_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.haunted_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.haunted_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_HUNTERS_PROMISE)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.hunters_promise_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.hunters_promise_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.hunters_promise_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.hunters_promise_1").formatted(Formatting.GRAY));

        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_HUNTING_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.hunting_bow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.hunting_bow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.hunting_bow_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_MASTERS_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.masters_bow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.masters_bow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.masters_bow_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_NOCTURNAL_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nocturnal_bow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nocturnal_bow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nocturnal_bow_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_POWER_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.power_bow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.power_bow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.power_bow_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_SABREWING)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sabrewing_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sabrewing_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.sabrewing_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_SNOW_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.snow_bow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.snow_bow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.snow_bow_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_SOUL_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_bow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_bow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_bow_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_GREEN_MENACE)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.green_menace_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.green_menace_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.green_menace_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_PINK_SCOUNDREL)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.pink_scoundrel_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.pink_scoundrel_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.pink_scoundrel_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_TRICKBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.trickbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.trickbow_2").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_TWIN_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.twin_bow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.twin_bow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.twin_bow_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_WINTERS_TOUCH)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.winters_touch_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.winters_touch_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.winters_touch_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_SHIVERING_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.shivering_bow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.shivering_bow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.shivering_bow_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_WIND_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.wind_bow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.wind_bow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.wind_bow_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_ECHO_OF_THE_VALLEY)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.echo_of_the_valley_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.echo_of_the_valley_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.echo_of_the_valley_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_BURST_GALE_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.burst_gale_bow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.burst_gale_bow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.burst_gale_bow_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_TWISTING_VINE_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.twisting_vine_bow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.twisting_vine_bow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.twisting_vine_bow_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.twisting_vine_bow_4").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_WEEPING_VINE_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.weeping_vine_bow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.weeping_vine_bow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.weeping_vine_bow_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_BUBBLE_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.bubble_bow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.bubble_bow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.bubble_bow_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.bubble_bow_4").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_BUBBLE_BURSTER)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.bubble_burster_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.bubble_burster_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.bubble_burster_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_VOID_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.void_bow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.void_bow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.void_bow_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_CALL_OF_THE_VOID)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.call_of_the_void_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.call_of_the_void_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.call_of_the_void_3").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_PHANTOM_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.phantom_bow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.phantom_bow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.phantom_bow_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.phantom_bow_4").formatted(Formatting.ITALIC));
        }
        if (stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_WEB_BOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.web_bow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.web_bow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.web_bow_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.web_bow_4").formatted(Formatting.ITALIC));
        }
    }
}