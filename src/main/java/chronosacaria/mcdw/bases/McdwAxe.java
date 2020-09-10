package chronosacaria.mcdw.bases;

import net.minecraft.item.Item;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import chronosacaria.mcdw.Mcdw;

public class McdwAxe extends AxeItem {
    public McdwAxe(ToolMaterial material, float attackDamage, float attackSpeed, String id){
        super(material, attackDamage, attackSpeed, new Item.Settings().group(Mcdw.WEAPONS));
        Registry.register(Registry.ITEM, new Identifier(Mcdw.MOD_ID, id), this);
    }
}