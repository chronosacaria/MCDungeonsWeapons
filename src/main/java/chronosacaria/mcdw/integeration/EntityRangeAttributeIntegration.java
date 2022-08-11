package chronosacaria.mcdw.integeration;

import chronosacaria.mcdw.configs.FeatureFlags;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;

public class EntityRangeAttributeIntegration {
    public static void addRange(float range, ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder) {
        if (FeatureFlags.isRangeAttributeUsageEnabled) {
            builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier("Attack range",
                    range,
                    EntityAttributeModifier.Operation.ADDITION));
        }
    }
}
