package chronosacaria.mcdw.damagesource;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.entity.player.PlayerEntity;

public class OffHandDamageSource extends EntityDamageSource {
    public OffHandDamageSource(String name, Entity source) {
        super(name, source);
    }

    public static DamageSource player(PlayerEntity attacker) {
        return new OffHandDamageSource("player", attacker);
    }
}