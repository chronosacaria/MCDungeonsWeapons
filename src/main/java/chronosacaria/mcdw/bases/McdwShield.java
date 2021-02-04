package chronosacaria.mcdw.bases;

import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class McdwShield extends ShieldItem {

    public final ToolMaterial material;

    public McdwShield(ToolMaterial material, Settings settings) {
        super(settings);
        this.material = material;

        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }

    @Override
    public String getTranslationKey (ItemStack itemStack){
        return itemStack.getSubTag("BlockEntityTag") != null ?
                this.getTranslationKey() + '.' + getColor(itemStack).getName() : super.getTranslationKey(itemStack);
    }

    @Override
    public UseAction getUseAction(ItemStack itemStack){
        return UseAction.BLOCK;
    }

    @Override
    public int getMaxUseTime(ItemStack itemStack){
        return 72000;
    }

    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ItemTags.PLANKS.contains(ingredient.getItem()) || super.canRepair(stack, ingredient);
    }

}
