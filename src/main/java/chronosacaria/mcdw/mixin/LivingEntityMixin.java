package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.summons.entity.SummonedBeeEntity;
import chronosacaria.mcdw.enchants.summons.registry.SummonedEntityRegistry;
import chronosacaria.mcdw.weapons.Rapiers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow public abstract ItemStack getStackInHand(Hand hand);

    @Shadow @Final private DefaultedList<ItemStack> equippedHand;

    public EntityType<SummonedBeeEntity> s_bee = SummonedEntityRegistry.SUMMONED_BEE_ENTITY;

    public LivingEntityMixin(EntityType<?> type, World world){
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "swingHand(Lnet/minecraft/util/Hand;)V", cancellable = true)
    private void swingHand(Hand hand, CallbackInfo ci){
        ItemStack mainHandStack = equippedHand.get(0);
        if (mainHandStack.getItem() == Rapiers.SWORD_BEESTINGER){
            SummonedBeeEntity summonedBeeEntity_1 = s_bee.create(world);
            summonedBeeEntity_1.setSummoner(this);
            summonedBeeEntity_1.refreshPositionAndAngles(this.getX(), this.getY() + 1, this.getZ(), 0, 0);
            world.spawnEntity(summonedBeeEntity_1);

        }
    }


}
