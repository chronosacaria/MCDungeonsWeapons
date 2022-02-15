package chronosacaria.mcdw.enchants.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.*;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class GoalUtils {

    @Nullable
    public static LivingEntity getOwner(HorseBaseEntity horseBaseEntity){
        try{
            UUID ownerUniqueId = horseBaseEntity.getOwnerUuid();
            return ownerUniqueId == null ? null : horseBaseEntity.world.getPlayerByUuid(ownerUniqueId);
        }catch (IllegalArgumentException var2) {
            return null;
        }
    }
}