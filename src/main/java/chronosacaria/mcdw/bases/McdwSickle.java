package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.api.util.CombatEventHandler;
import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;

import chronosacaria.mcdw.Mcdw;
import net.minecraft.world.World;

public class McdwSickle extends SwordItem implements IOffhandAttack {
    public McdwSickle(ToolMaterial material, int attackDamage, float attackSpeed, String id) {
        super(material, attackDamage, attackSpeed, new Item.Settings().group(Mcdw.WEAPONS));
        Registry.register(Registry.ITEM, new Identifier(Mcdw.MOD_ID, id), this);
    }

    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn){

        if (handIn == Hand.OFF_HAND && worldIn.isClient){
            MinecraftClient client = MinecraftClient.getInstance();
            CombatEventHandler.checkForOffHandAttack();
            ItemStack offhand = playerIn.getStackInHand(handIn);
            return new TypedActionResult<>(ActionResult.SUCCESS, offhand);
        } else {
            return new TypedActionResult<>(ActionResult.PASS, playerIn.getStackInHand(handIn));
        }

    }

}