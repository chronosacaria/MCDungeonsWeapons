package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface IMcdwWeaponID {

    static IMcdwWeaponID[] values() {
        List<IMcdwWeaponID[]> arrayFather = List.of(meleeValues(), rangedValues(), ShieldsID.values());

        return arrayFather.stream().flatMap(Stream::of).toArray(IMcdwWeaponID[]::new);
    }

    static IMeleeWeaponID[] meleeValues() {
        List<IMeleeWeaponID[]> arrayFather = List.of(AxesID.values(), DaggersID.values(), DoubleAxesID.values(),
                GauntletsID.values(), GlaivesID.values(), HammersID.values(), PicksID.values(), ScythesID.values(),
                SicklesID.values(), SoulDaggersID.values(), SpearsID.values(), StavesID.values(), SwordsID.values(),
                WhipsID.values());

        return arrayFather.stream().flatMap(Stream::of).toArray(IMeleeWeaponID[]::new);
    }

    static IRangedWeaponID[] rangedValues() {
        List<IRangedWeaponID[]> arrayFather = List.of(BowsID.values(), ShortbowsID.values(),
                LongbowsID.values(), CrossbowsID.values());

        return arrayFather.stream().flatMap(Stream::of).toArray(IRangedWeaponID[]::new);
    }

    Boolean isEnabled();

    Item getItem();

    Integer getItemSpawnRate();

    /**
     * Copyright 2023 DaFuqs
     * <br/><br/>
     * Used with Permission
     * <br/><br/>
     * The following code is from Spectrum and can be found here:<br/>
     * <a href = "https://github.com/DaFuqs/Spectrum/blob/1.19-deeper-down/src/main/java/de/dafuqs/spectrum/items/Preenchanted.java#L11">Preenchanted#getDefaultEnchantments</a>
     */

    Map<Enchantment, Integer> getInnateEnchantments();

    /**
     * Copyright 2023 DaFuqs
     * <br/><br/>
     * Used with Permission, modifications made to allow for checking whether innate enchantments are enabled or not.
     * <br/><br/>
     * The following code is from Spectrum and can be found here:<br/>
     * <a href = "https://github.com/DaFuqs/Spectrum/blob/1.19-deeper-down/src/main/java/de/dafuqs/spectrum/items/Preenchanted.java#L13">Preenchanted#getDefaultEnchantedStack</a>
     */
    default @NotNull ItemStack getInnateEnchantedStack(Item item) {
        ItemStack itemStack = new ItemStack(item);
        if (Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.ENABLE_ENCHANTMENT_SETTINGS.get(SettingsID.ENABLE_INNATE_ENCHANTMENTS)) {
            for (Map.Entry<Enchantment, Integer> innateEnchantments : getInnateEnchantments().entrySet()) {
                itemStack.addEnchantment(innateEnchantments.getKey(), innateEnchantments.getValue());
            }
        }
        return itemStack;
    }

    Item makeWeapon();
}
