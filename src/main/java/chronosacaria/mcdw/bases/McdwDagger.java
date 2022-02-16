package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.DaggersID;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class McdwDagger extends SwordItem implements IOffhandAttack {

    public McdwDagger(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed,
                new Item.Settings().group(Mcdw.WEAPONS).rarity(RarityHelper.fromToolMaterial(material)));
    }

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn){
        return useOffhand(worldIn, playerIn, handIn);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        for (DaggersID daggersID : DaggersID.values()) {
            if (stack.getItem() == ItemsInit.daggerItems.get(daggersID)) {
                String str = daggersID.toString().toLowerCase();
                for (int i = 1; i <= tooltipSize(daggersID); i++)
                    tooltip.add(new TranslatableText("tooltip_info_item.mcdw." + str.substring(7) + "_" + i).formatted(Formatting.ITALIC));
                tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
                tooltip.add(new TranslatableText("tooltip_note_item.mcdw.dualwield").formatted(Formatting.GREEN));
                break;
            }
        }
    }

    private int tooltipSize(DaggersID daggersID) {
        return switch (daggersID) {

            case DAGGER_DAGGER -> 2;
            case DAGGER_FANGS_OF_FROST -> 4;
            case DAGGER_MOON,
                    DAGGER_SHEAR_DAGGER,
                    DAGGER_TEMPEST_KNIFE,
                    DAGGER_RESOLUTE_TEMPEST_KNIFE,
                    DAGGER_CHILL_GALE_KNIFE,
                    DAGGER_BACKSTABBER,
                    DAGGER_SWIFT_STRIKER,
                    DAGGER_VOID_TOUCHED_BLADE,
                    DAGGER_THE_BEGINNING,
                    DAGGER_THE_END -> 3;
            //noinspection UnnecessaryDefault
            default -> 0;
        };
    }
}