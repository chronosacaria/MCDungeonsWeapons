package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.types.RangedEnchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;

public class RadianceShotEnchantment extends RangedEnchantment {
    public RadianceShotEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        if (McdwEnchantsConfig.getValue("radiance_shot")) {
            Registry.register(Registry.ENCHANTMENT, Mcdw.ID("radiance_shot"), this);
        }
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    /*@Override
    public void onTargetDamaged (LivingEntity user, Entity target, int level){
        if (target instanceof LivingEntity){
            World world = user.getEntityWorld();
            world.createExplosion(target, target.getX(), target.getY(), target.getZ(), level,
                    Explosion.DestructionType.NONE);
        }
    }*/

    @Override
    public boolean isAvailableForRandomSelection() {
        return McdwEnchantsConfig.getValue("radiance_shot");
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof CrossbowItem || stack.getItem() instanceof BowItem;
    }
}