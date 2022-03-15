
package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IRangedWeapon;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.LongBowsID;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

import static chronosacaria.mcdw.api.util.RangedAttackHelper.getVanillaBowChargeTime;

public class McdwLongBow extends BowItem implements IRangedWeapon {

    public static float chargeTime = 60.0f;

    public final ToolMaterial material;
    public final float drawSpeed;
    public static float maxBowRange;
    private final ParticleEffect type;

    public McdwLongBow(ToolMaterial material, float drawSpeed, float maxBowRangePar) {
        this(material, drawSpeed, maxBowRangePar, null);
    }

    public McdwLongBow(ToolMaterial material, float drawSpeed, float maxBowRangePar, ParticleEffect particles) {
        super(new Item.Settings().group(Mcdw.RANGED)
                .maxCount(1)
                .maxDamage(material.getDurability())
                .rarity(RarityHelper.fromToolMaterial(material))
        );
        this.material = material;
        this.drawSpeed = drawSpeed;
        maxBowRange = maxBowRangePar;
        type = particles;
    }

    public float getDrawSpeed() {
        return Math.max(0, drawSpeed);
    }

    public ParticleEffect getArrowParticles() {
        return type;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
        if (user instanceof PlayerEntity playerEntity) {
            boolean bl = playerEntity.getAbilities().creativeMode
                    || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
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

                        persistentProjectileEntity.setVelocity(playerEntity, playerEntity.getPitch(),
                                playerEntity.getYaw(),
                                0.0F, f * maxBowRange, 1.0F);
                        if (f == 1.0F) {
                            persistentProjectileEntity.setCritical(true);
                        }

                        // POWER ENCHANTMENT
                        int j = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
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
                    }
                }
            }
        }
    }


    public static float getBowArrowVelocity(ItemStack stack, int charge) {
        float bowChargeTime = getVanillaBowChargeTime(stack);
        if (bowChargeTime <= 0){
            bowChargeTime = 1;
        }

        float arrowVelocity = (float) charge / chargeTime;
        arrowVelocity = (arrowVelocity * arrowVelocity + arrowVelocity * 2.0F) / 1.5F;
        if (arrowVelocity > 1.0F) {
            arrowVelocity = 1.0F;
        }

        return arrowVelocity;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000 - (int)(drawSpeed);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return BOW_PROJECTILES;
    }

    @Override
    public int getRange() {
        return (int) maxBowRange * 2 + 10;
    }

    @Override
    public int getEnchantability() {
        return material.getEnchantability();
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return this.material.getRepairIngredient().test(ingredient) || super.canRepair(stack, ingredient);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        super.appendTooltip(stack, world, tooltip, tooltipContext);
        for (LongBowsID longBowsID : LongBowsID.values()) {
            if (stack.getItem() == ItemsInit.longBowItems.get(longBowsID)) {
                int i = 1;
                String str = longBowsID.toString().toLowerCase(Locale.ROOT).substring(4);
                String translationKey = String.format("tooltip_info_item.mcdw.%s_", str);
                while (I18n.hasTranslation(translationKey + i)) {
                    tooltip.add(new TranslatableText(translationKey + i).formatted(Formatting.ITALIC));
                    i++;
                }
                tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
                tooltip.add(new TranslatableText("tooltip_note_item.mcdw.longbow").formatted(Formatting.GREEN));
                break;
            }
        }
    }
}