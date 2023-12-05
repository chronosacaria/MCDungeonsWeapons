package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.registries.ItemGroupRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.*;

public class McdwShield extends ShieldItem {

    public final ToolMaterial material;
    String[] repairIngredient;

    public McdwShield(ToolMaterial material, String[] repairIngredient) {
        super(new Item.Settings().rarity(RarityHelper.fromToolMaterial(material)).maxCount(1)
                .maxDamage(250 + material.getDurability())
        );
        ItemGroupEvents.modifyEntriesEvent(ItemGroupRegistry.SHIELDS).register(entries -> entries.add(this.getDefaultStack()));
        this.material = material;
        this.repairIngredient = repairIngredient;

        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }

    @Override
    public String getTranslationKey (ItemStack itemStack){
        return BlockItem.getBlockEntityNbt(itemStack) != null ?
                this.getTranslationKey() + '.' + getColor(itemStack).getName() : super.getTranslationKey(itemStack);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return CleanlinessHelper.canRepairCheck(repairIngredient, ingredient);
    }
}