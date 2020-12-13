package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.types.DamageBoostEnchantment;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Committed extends DamageBoostEnchantment {
    McdwEnchantsConfig config = AutoConfig.getConfigHolder(McdwEnchantsConfig.class).getConfig();

    public Committed(Enchantment.Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT, new Identifier(Mcdw.MOD_ID, "committed"), this);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return config.enableAOEMixing || !(other instanceof Leeching);
    }
    
}