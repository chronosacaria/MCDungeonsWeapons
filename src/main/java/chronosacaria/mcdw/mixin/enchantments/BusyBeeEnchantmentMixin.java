package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.summons.entity.SummonedBeeEntity;
import chronosacaria.mcdw.enchants.summons.registry.SummonedEntityRegistry;
import chronosacaria.mcdw.items.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
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

@Mixin (LivingEntity.class)
public abstract class BusyBeeEnchantmentMixin extends Entity {

    @Shadow
    public abstract ItemStack getOffHandStack();

    @Shadow
    public abstract ItemStack getMainHandStack();

    @Shadow
    public abstract boolean damage(DamageSource source, float amount);

    public EntityType<SummonedBeeEntity> s_bee =
            SummonedEntityRegistry.SUMMONED_BEE_ENTITY;

    public BusyBeeEnchantmentMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    // -- For summoning Bee with Bee Stinger Item
    @Inject(at = @At("HEAD"), method = "swingHand(Lnet/minecraft/util/Hand;)V", cancellable = true)

    private void swingHand(Hand hand, CallbackInfo ci) {
        ItemStack mainHandStack = getMainHandStack();
        ItemStack offHandStack = getOffHandStack();
        if (McdwEnchantsConfig.getValue("mixin_bee")) {
            if (mainHandStack.getItem() == ItemRegistry.getItem("sword_beestinger") && offHandStack.getItem() == ItemRegistry.getItem("item_bee_stinger")) {
                SummonedBeeEntity summonedBeeEntity_1 = s_bee.create(world);
                if (summonedBeeEntity_1 != null) {
                    summonedBeeEntity_1.setSummoner(this);
                    summonedBeeEntity_1.refreshPositionAndAngles(this.getX(), this.getY() + 1, this.getZ(), 0, 0);
                    world.spawnEntity(summonedBeeEntity_1);
                }
            }
            if ((offHandStack.getItem() == ItemRegistry.getItem("item_bee_stinger") && (mainHandStack.getItem() == ItemRegistry.getItem("sword_beestinger")))) {
                offHandStack.decrement(1);
            }
        }
    } //END BUSY BEE ENCHANTMENT
}
