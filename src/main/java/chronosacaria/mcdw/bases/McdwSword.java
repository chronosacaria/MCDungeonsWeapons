package chronosacaria.mcdw.bases;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import chronosacaria.mcdw.Mcdw;

public class McdwSword extends SwordItem {
    public McdwSword(ToolMaterial material, int attackDamage, float attackSpeed, String id) {
        super(material, attackDamage, attackSpeed, new Item.Settings().group(Mcdw.WEAPONS));
        Registry.register(Registry.ITEM, new Identifier(Mcdw.MOD_ID, id), this);
    }

}
