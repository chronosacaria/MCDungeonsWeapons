package chronosacaria.mcdw.registries;

import chronosacaria.mcdw.Mcdw;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * At this time, the code that is in this class is used without permission. However, the reason for it being uploaded
 * to GitHub on our project is to make sure that we can address issues with older versions of MCDW. Permission has been
 * requested, and if it is denied, this code shall be removed from the project.
 * <br/><br/>
 * Timefall Development want to make it very clear that NO copyright infringement was intended and we shall comply with
 * any and all requests from either <a href="https://github.com/JamiesWhiteShirt/">JamieWhiteShirt</a> or <a href="https://github.com/ChloeDawn/">ChloeDawn</a> to remove this code from our repository.
 * <br/><br/>
 * The following code is from Reach Entity Attributes and can be found here:
 * <a href = "https://github.com/JamiesWhiteShirt/reach-entity-attributes/blob/1.19/src/main/java/com/jamieswhiteshirt/reachentityattributes/ReachEntityAttributes.java#L24">ReachEntityAttributes Lines 24-25</a>
 */

public class EntityAttributesRegistry {
    public static final EntityAttribute REACH = createAttribute("reach", 0.0, 0.0, 1024.0);
    public static final EntityAttribute ATTACK_RANGE = createAttribute("attack_range", 0.0, 0.0, 1024.0);

    private static EntityAttribute createAttribute(String name, double fallback, double min, double max) {
        return new ClampedEntityAttribute("attribute.name.generic." + Mcdw.MOD_ID + '.' + name, fallback, min, max).setTracked(true);
    }
    public static void registerAttributes() {
        Registry.register(Registries.ATTRIBUTE, new Identifier(Mcdw.MOD_ID, "reach"), REACH);
        Registry.register(Registries.ATTRIBUTE, new Identifier(Mcdw.MOD_ID, "attack_range"), ATTACK_RANGE);
    }
}
