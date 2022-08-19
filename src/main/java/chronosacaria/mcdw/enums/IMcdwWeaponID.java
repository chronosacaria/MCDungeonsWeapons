package chronosacaria.mcdw.enums;

import net.minecraft.item.Item;

import java.util.List;
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

    Item makeWeapon();
}