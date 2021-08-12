package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IRangedWeapon;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.items.ItemRegistry;
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
        return stack.getItem() == ItemRegistry.getItem("crossbow_auto_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_azure_seeker")
                || stack.getItem() == ItemRegistry.getItem("crossbow_baby_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_burst_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_butterfly_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_corrupted_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_doom_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_dual_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_exploding_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_feral_soul_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_firebolt_thrower")
                || stack.getItem() == ItemRegistry.getItem("crossbow_harp_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_heavy_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_imploding_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_lightning_harp_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_rapid_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_scatter_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_slayer_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_the_slicer")
                || stack.getItem() == ItemRegistry.getItem("crossbow_soul_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_soul_hunter_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_spellbound_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_voidcaller_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_cog_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_pride_of_the_piglins")
                || stack.getItem() == ItemRegistry.getItem("crossbow_harpoon_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_nautical_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_shadow_crossbow")
                || stack.getItem() == ItemRegistry.getItem("crossbow_veiled_crossbow");
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (stack.getItem() == ItemRegistry.getItem("crossbow_auto_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.auto_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.auto_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.auto_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_azure_seeker")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.azure_seeker_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.azure_seeker_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.azure_seeker_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_baby_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.baby_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.baby_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.baby_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_burst_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.burst_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.burst_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.burst_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_butterfly_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.butterfly_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.butterfly_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.butterfly_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_corrupted_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.corrupted_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.corrupted_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.corrupted_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_cog_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cog_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cog_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cog_crossbow_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.cog_crossbow_4").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_doom_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.doom_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.doom_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.doom_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_dual_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dual_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dual_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dual_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_exploding_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.exploding_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.exploding_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.exploding_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_feral_soul_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.feral_soul_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.feral_soul_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.feral_soul_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_firebolt_thrower")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.firebolt_thrower_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.firebolt_thrower_2").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_harp_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.harp_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.harp_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.harp_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_harpoon_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.harpoon_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.harpoon_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.harpoon_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_heavy_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heavy_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heavy_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heavy_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_imploding_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.imploding_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.imploding_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.imploding_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_lightning_harp_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.lightning_harp_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.lightning_harp_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.lightning_harp_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_nautical_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nautical_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.nautical_crossbow_2").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_pride_of_the_piglins")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.pride_of_the_piglins_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.pride_of_the_piglins_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.pride_of_the_piglins_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_rapid_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.rapid_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.rapid_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.rapid_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_scatter_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.scatter_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.scatter_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.scatter_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_shadow_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.shadow_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.shadow_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.shadow_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_slayer_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.slayer_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.slayer_crossbow_2").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_the_slicer")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.the_slicer_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.the_slicer_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.the_slicer_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.the_slicer_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.the_slicer_5").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_soul_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_soul_hunter_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_hunter_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_hunter_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_hunter_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_spellbound_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.spellbound_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.spellbound_crossbow_2").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_veiled_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.veiled_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.veiled_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.veiled_crossbow_3").formatted(Formatting.ITALIC));
        }
        else if (stack.getItem() == ItemRegistry.getItem("crossbow_voidcaller_crossbow")) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.void_caller_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.void_caller_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.void_caller_crossbow_3").formatted(Formatting.ITALIC));
        }
    }
}
