package chronosacaria.mcdw.entity;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwLong;
import chronosacaria.mcdw.weapons.Longs;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class McdwEntities {

    private static final Map<Identifier, EntityType<?>> ENTITY_TYPES = new LinkedHashMap<>();

    //SPEARS AND GLAIVES
    public static final EntityType<SpearEntity> SPEAR_SPEAR = add("spear_spear", createSpear(Longs.SPEAR_SPEAR));
    public static final EntityType<SpearEntity> SPEAR_FORTUNE = add("spear_fortune", createSpear(Longs.SPEAR_FORTUNE));
    public static final EntityType<SpearEntity> SPEAR_WHISPERING_SPEAR = add("spear_whispering_spear",
            createSpear(Longs.SPEAR_WHISPERING_SPEAR));
    public static final EntityType<SpearEntity> SPEAR_GLAIVE = add("spear_glaive",
            createSpear(Longs.SPEAR_GLAIVE));
    public static final EntityType<SpearEntity> SPEAR_GRAVE_BANE = add("spear_grave_bane",
            createSpear(Longs.SPEAR_GRAVE_BANE));
    public static final EntityType<SpearEntity> SPEAR_VENOM_GLAIVE = add("spear_glaive",
            createSpear(Longs.SPEAR_VENOM_GLAIVE));


    //BATTLESTAVES
    public static final EntityType<SpearEntity> STAFF_BATTLESTAFF = add("staff_battlestaff", createSpear(Longs.STAFF_BATTLESTAFF));
    public static final EntityType<SpearEntity> STAFF_BATTLESTAFF_OF_TERROR = add("staff_battlestaff_of_terror", createSpear(Longs.STAFF_BATTLESTAFF_OF_TERROR));
    public static final EntityType<SpearEntity> STAFF_GROWING_STAFF = add("staff_growing_staff", createSpear(Longs.STAFF_GROWING_STAFF));

    public static void register() {
        for (Identifier id : ENTITY_TYPES.keySet()) {
            Registry.register(Registry.ENTITY_TYPE, id, ENTITY_TYPES.get(id));
        }
    }

    private static <T extends EntityType<?>> T add(String idn, T type) {
        Identifier id = new Identifier(Mcdw.MOD_ID, idn);
        ENTITY_TYPES.put(id, type);
        return type;
    }

    private static EntityType<SpearEntity> createSpear(McdwLong item) {
        return FabricEntityTypeBuilder.<SpearEntity>create(SpawnGroup.MISC, (entity, world) -> new SpearEntity(entity, world, item)).size(EntityDimensions.fixed(0.5F, 0.5F)).build();
    }
}
