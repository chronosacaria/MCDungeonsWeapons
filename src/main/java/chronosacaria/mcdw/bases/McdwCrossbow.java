package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IRangedWeapon;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.CrossbowsID;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
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
        super.appendTooltip(stack, world, tooltip, tooltipContext);
        for (CrossbowsID crossbowsID : CrossbowsID.values()) {
            if (stack.getItem() == ItemsInit.crossbowItems.get(crossbowsID)) {
                int i = 1;
                String str = crossbowsID.toString().toLowerCase().substring(9);
                String translationKey = String.format("tooltip_info_item.mcdw.%s_", str);
                while (I18n.hasTranslation(translationKey + i)) {
                    tooltip.add(new TranslatableText(translationKey + i).formatted(Formatting.ITALIC));
                    i++;
                }
                break;
            }
        }
    }
}
