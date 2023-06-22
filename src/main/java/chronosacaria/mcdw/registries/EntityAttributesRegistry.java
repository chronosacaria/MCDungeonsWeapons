package chronosacaria.mcdw.registries;

import chronosacaria.mcdw.Mcdw;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Copyright 2019 Erlend Åmdal
 * <br/><br/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * <br/><br/>
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 * <br/><br/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 * <br/><br/>
 * The following code is from Reach Entity Attributes and can be found here:
 * <a href = "https://github.com/JamiesWhiteShirt/reach-entity-attributes/blob/1.19/src/main/java/com/jamieswhiteshirt/reachentityattributes/ReachEntityAttributes.java#L24">ReachEntityAttributes Lines 24-25</a>
 */

public class EntityAttributesRegistry {
    public static final EntityAttribute REACH = createAttribute("reach", 0.0, 0.0, 1024.0);
    public static final EntityAttribute ATTACK_RANGE = createAttribute("attack_range", 0.0, 0.0, 1024.0);

    @SuppressWarnings("SameParameterValue")
    private static EntityAttribute createAttribute(String name, double fallback, double min, double max) {
        return new ClampedEntityAttribute("attribute.name.generic." + Mcdw.MOD_ID + '.' + name, fallback, min, max).setTracked(true);
    }
    public static void register() {
        Registry.register(Registries.ATTRIBUTE, new Identifier(Mcdw.MOD_ID, "reach"), REACH);
        Registry.register(Registries.ATTRIBUTE, new Identifier(Mcdw.MOD_ID, "attack_range"), ATTACK_RANGE);
    }
}
