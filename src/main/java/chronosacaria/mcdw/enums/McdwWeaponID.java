package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.configs.stats.McdwWeaponStats;
import net.minecraft.item.Item;

import java.util.List;
import java.util.stream.Stream;

public interface McdwWeaponID {

    static McdwWeaponID[] values() {
        List<McdwWeaponID[]> arrayFather = List.of(AxesID.values(), BowsID.values(), CrossbowsID.values(), DaggersID.values(),
                DoubleAxesID.values(), GauntletsID.values(), GlaivesID.values(), HammersID.values(), LongBowsID.values(),
                PicksID.values(), ScythesID.values(), ShieldsID.values(), ShortBowsID.values(), SicklesID.values(),
                SoulDaggersID.values(), SpearsID.values(), StavesID.values(), SwordsID.values(), WhipsID.values());

        return arrayFather.stream().flatMap(Stream::of).toArray(McdwWeaponID[]::new);
    }

    Boolean isEnabled();

    Item getItem();

    Float getItemSpawnRate();

    McdwWeaponStats getWeaponItemStats();

    Item makeWeapon();
}
