package chronosacaria.mcdw.mixin.reach;

import chronosacaria.mcdw.api.util.PlayerAttackHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/**
 * At this time, the code that is in this class is used without permission. However, the reason for it being uploaded
 * to GitHub on our project is to make sure that we can address issues with older versions of MCDW. Permission has been
 * requested, and if it is denied, this code shall be removed from the project.
 * <br/><br/>
 * Timefall Development want to make it very clear that NO copyright infringement was intended and we shall comply with
 * any and all requests from either <a href="https://github.com/JamiesWhiteShirt/">JamieWhiteShirt</a> or <a href="https://github.com/ChloeDawn/">ChloeDawn</a> to remove this code from our repository.
 * <br/><br/>
 * The following code is from Reach Entity Attributes and can be found here:
 * <a href = "https://github.com/JamiesWhiteShirt/reach-entity-attributes/blob/1.19/src/main/java/com/jamieswhiteshirt/reachentityattributes/mixin/ItemMixin.java">ItemMixin</a>
 */

@Mixin(Item.class)
public abstract class ItemMixin implements ItemConvertible {
    @ModifyConstant(method = "raycast", require = 4, allow = 4, constant = @Constant(doubleValue = 5.0))
    private static double getNewReachDistance(double reachDistance, World world, PlayerEntity playerEntity) {
        return PlayerAttackHelper.getReachDistance(playerEntity, reachDistance);
    }
}
