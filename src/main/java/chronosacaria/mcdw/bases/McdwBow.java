
package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class McdwBow extends BowItem {

    public static float chargeTime = 30.0f;

    private final ToolMaterial material;
    private final float maxDrawTime;
    public static float maxBowRange;
    private final ParticleEffect type;

    public McdwBow(ToolMaterial material, Settings settings, float maxDrawTime, float maxBowRange, String id) {
        super(settings);
        this.material = material;
        this.maxDrawTime = maxDrawTime;
        this.maxBowRange = maxBowRange;
        type = null;
        Registry.register(Registry.ITEM, new Identifier(Mcdw.MOD_ID, id), this);
    }

    public McdwBow(ToolMaterial material, Settings settings, float maxDrawTime, float maxBowRange,
                   ParticleEffect particles){
        super(settings);
        this.material = material;
        this.maxDrawTime = maxDrawTime;
        this.maxBowRange = maxBowRange;
        type = particles;
    }

    public float getMaxDrawTime(ItemStack bow){
        return (float) Math.max(0, maxDrawTime);
    }

    public float getMaxBowRange(ItemStack bow) {return (float) Math.max(0, maxBowRange); }

    public ParticleEffect getArrowParticles(){
        return type;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks){
        if (user instanceof PlayerEntity){
            PlayerEntity playerEntity = (PlayerEntity) user;
            boolean bl = playerEntity.abilities.creativeMode
                    || EnchantmentHelper.getLevel(Enchantments.INFINITY,stack) > 0;
            ItemStack itemStack = playerEntity.getArrowType(stack);
            if (!itemStack.isEmpty() || bl) {
                if (itemStack.isEmpty()) {
                    itemStack = new ItemStack(Items.ARROW);
                }

                int i = this.getMaxUseTime(stack) - remainingUseTicks;
                float f = getPullProgress(i);
                if ((double) f >= 0.1D) {
                    boolean bl2 = bl && itemStack.getItem() == Items.ARROW;
                    if (!world.isClient) {
                        ArrowItem arrowItem = ((ArrowItem) (itemStack.getItem() instanceof ArrowItem
                                ? itemStack.getItem()
                                : Items.ARROW));
                        PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world,
                                itemStack, playerEntity);

                        persistentProjectileEntity.setProperties(playerEntity, playerEntity.pitch, playerEntity.yaw,
                                0.0F, f * maxBowRange, 1.0F);
                        if (f == 1.0F){
                            persistentProjectileEntity.setCritical(true);
                        }

                        // POWER ENCHANTMENT
                        int j = EnchantmentHelper.getLevel(Enchantments.POWER,stack);
                        if (j > 0) {
                            persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double) j * 0.5D + 0.5D);
                        }

                        // PUNCH ENCHANTMENT
                        int k = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
                        if (k > 0) {
                            persistentProjectileEntity.setPunch(k);
                        }

                        // FLAME ENCHANTMENT
                        if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
                            persistentProjectileEntity.setOnFireFor(100);
                        }

                        // DAMAGE TOOL
                        stack.damage(1, playerEntity, (p) -> p.sendToolBreakStatus(playerEntity.getActiveHand()));

                        if (bl2 || playerEntity.abilities.creativeMode && (itemStack.getItem() == Items.SPECTRAL_ARROW
                                || itemStack.getItem() == Items.TIPPED_ARROW)) {
                            persistentProjectileEntity.pickupType =
                                    PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                        }

                        world.spawnEntity(persistentProjectileEntity);
                    }

                    world.playSound((PlayerEntity) null, playerEntity.getX(), playerEntity.getY(),
                            playerEntity.getZ(),
                            SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F,
                            1.0F / (RANDOM.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!bl2 && !playerEntity.abilities.creativeMode) {
                        itemStack.decrement(1);
                        if (itemStack.isEmpty()) {
                            playerEntity.inventory.removeOne(itemStack);
                        }
                    }

                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                }
            }
        }
    }

    public static float getPullProgress (int useTicks) {
        float f = (float) useTicks / chargeTime;
        ;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        boolean bl = !user.getArrowType(itemStack).isEmpty();
        if (!user.abilities.creativeMode && !bl) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return BOW_PROJECTILES;
    }

    @Override
    public int getRange() {
        return 15;
    }

    @Override
    public int getEnchantability() {
        return material.getEnchantability();
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return this.material.getRepairIngredient().test(ingredient) || super.canRepair(stack, ingredient);
    }
}
