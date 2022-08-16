package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.RarityHelper;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class McdwShield extends ShieldItem {

    public final ToolMaterial material;
    String[] repairIngredient;

    public McdwShield(ToolMaterial material, String[] repairIngredient) {
        super(new Item.Settings().group(Mcdw.SHIELDS)
                .maxCount(1)
                .maxDamage(250 + material.getDurability())
                .rarity(RarityHelper.fromToolMaterial(material))
        );
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
}