package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IRangedWeapon;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.CrossbowsID;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class McdwCrossbow extends CrossbowItem implements IRangedWeapon {

    public final ToolMaterial material;
    public final float drawSpeed;
    public final float range;

    public McdwCrossbow(ToolMaterial material, float drawSpeed, float range) {
        super(new Item.Settings().group(Mcdw.RANGED)
                .maxCount(1)
                .maxDamage(100 + material.getDurability())
                .rarity(RarityHelper.fromToolMaterial(material))
        );
        this.material = material;
        this.drawSpeed = drawSpeed;
        this.range = range;
    }

    public float getProjectileVelocity(ItemStack stack){
        boolean fastProjectiles = shootsFasterArrows(stack);
        if (hasProjectile(stack, Items.FIREWORK_ROCKET)){
            if (fastProjectiles) return 3.2F;
            else return 1.6F;
        }
        else if (fastProjectiles) return 4.8F;
        else return 3.2f;
    }

    @Override
    public int getRange() { return (int)range; }

    @Override
    public int getEnchantability() {
        return material.getEnchantability();
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return getPullTime(stack) + 3 - (28 - (int)(drawSpeed));
    }



    @Override
    public boolean isUsedOnRelease(ItemStack stack){
        return stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_AUTO_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_AZURE_SEEKER)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_BABY_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_BURST_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_DOOM_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_DUAL_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_FIREBOLT_THROWER)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_HARP_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_HEAVY_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_RAPID_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SCATTER_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_THE_SLICER)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SOUL_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_COG_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_HARPOON_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SHADOW_CROSSBOW)
                || stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_VEILED_CROSSBOW);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_AUTO_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.auto_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.auto_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.auto_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_AZURE_SEEKER)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.azure_seeker_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.azure_seeker_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.azure_seeker_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_BABY_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.baby_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.baby_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.baby_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_BURST_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.burst_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.burst_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.burst_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.butterfly_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.butterfly_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.butterfly_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.corrupted_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.corrupted_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.corrupted_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_COG_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cog_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cog_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cog_crossbow_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cog_crossbow_4").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_DOOM_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.doom_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.doom_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.doom_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_DUAL_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dual_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dual_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dual_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.exploding_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.exploding_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.exploding_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.feral_soul_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.feral_soul_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.feral_soul_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_FIREBOLT_THROWER)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.firebolt_thrower_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.firebolt_thrower_2").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_HARP_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.harp_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.harp_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.harp_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_HARPOON_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.harpoon_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.harpoon_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.harpoon_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_HEAVY_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heavy_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heavy_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heavy_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.imploding_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.imploding_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.imploding_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.lightning_harp_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.lightning_harp_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.lightning_harp_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nautical_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nautical_crossbow_2").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.pride_of_the_piglins_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.pride_of_the_piglins_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.pride_of_the_piglins_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_RAPID_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.rapid_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.rapid_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.rapid_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SCATTER_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.scatter_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.scatter_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.scatter_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SHADOW_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.shadow_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.shadow_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.shadow_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.slayer_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.slayer_crossbow_2").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_THE_SLICER)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.the_slicer_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.the_slicer_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.the_slicer_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.the_slicer_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.the_slicer_5").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SOUL_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_hunter_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_hunter_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_hunter_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.spellbound_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.spellbound_crossbow_2").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_VEILED_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.veiled_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.veiled_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.veiled_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW)) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.void_caller_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.void_caller_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.void_caller_crossbow_3").formatted(Formatting.ITALIC));
        }
    }
}
