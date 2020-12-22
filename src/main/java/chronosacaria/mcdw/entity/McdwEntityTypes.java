package chronosacaria.mcdw.entity;

import chronosacaria.mcdw.Mcdw;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class McdwEntityTypes {

    public static final EntityType<IceCloudEntity> ICE_CLOUD = FabricEntityTypeBuilder.create(
            SpawnGroup.MISC, ((EntityType.EntityFactory<IceCloudEntity>) IceCloudEntity::new))
            .fireImmune()
            .dimensions(EntityDimensions.fixed(2.0f, 1.0f))
            .trackRangeBlocks(6)
            .build();

}
