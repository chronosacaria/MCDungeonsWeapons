package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IRangedWeapon;
import chronosacaria.mcdw.weapons.Bows;
import chronosacaria.mcdw.weapons.Crossbows;
import com.google.common.collect.Lists;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import sun.font.DelegatingShape;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class McdwCrossbow extends CrossbowItem implements IRangedWeapon {

    public McdwCrossbow(Settings settings, String id) {
        super(settings);
        Registry.register(Registry.ITEM, new Identifier(Mcdw.MOD_ID, id), this);
    }

    public float getProjectileVelocity(ItemStack stack){
        boolean fastProjectiles = shootsFasterArrows(stack);
        if (hasProjectile(stack, Items.FIREWORK_ROCKET)){
            if (fastProjectiles){
                return 3.2F;
            }
            else {
                return 1.6F;
            }
        }
        else if (fastProjectiles){
            return 4.8F;
        }
        else {
            return 3.2f;
        }
    }

    @Override
    public boolean isUsedOnRelease(ItemStack stack){
        return stack.getItem() == Crossbows.CROSSBOW_AUTO_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_AZURE_SEEKER
                || stack.getItem() == Crossbows.CROSSBOW_BABY_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_BURST_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_BUTTERFLY_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_CORRUPTED_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_DOOM_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_DUAL_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_EXPLODING_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_FERAL_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_FIREBOLT_THROWER
                || stack.getItem() == Crossbows.CROSSBOW_HARP_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_HEAVY_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_IMPLODING_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_LIGHTNING_HARP_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_RAPID_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_SCATTER_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_SLAYER_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_THE_SLICER_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_SOUL_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_SOUL_HUNTER_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_SPELLBOUND_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_VOID_CALLER_CROSSBOW;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (stack.getItem() == Crossbows.CROSSBOW_AUTO_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.auto_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.auto_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.auto_crossbow_3").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_AZURE_SEEKER) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.azure_seeker_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.azure_seeker_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.azure_seeker_3").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_BABY_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.baby_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.baby_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.baby_crossbow_3").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_BURST_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.burst_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.burst_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.burst_crossbow_3").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_BUTTERFLY_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.butterfly_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.butterfly_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.butterfly_crossbow_3").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_CORRUPTED_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.corrupted_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.corrupted_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.corrupted_crossbow_3").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_DOOM_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.doom_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.doom_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.doom_crossbow_3").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_DUAL_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dual_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dual_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.dual_crossbow_3").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_EXPLODING_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.exploding_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.exploding_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.exploding_crossbow_3").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_FERAL_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.feral_soul_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.feral_soul_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.feral_soul_crossbow_3").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_FIREBOLT_THROWER) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.firebolt_thrower_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.firebolt_thrower_2").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_HARP_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.harp_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.harp_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.harp_crossbow_3").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_HEAVY_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heavy_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heavy_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.heavy_crossbow_3").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_IMPLODING_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.imploding_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.imploding_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.imploding_crossbow_3").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_LIGHTNING_HARP_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.lightning_harp_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.lightning_harp_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.lightning_harp_crossbow_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap"));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.ricochet").formatted(Formatting.GREEN));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_RAPID_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.rapid_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.rapid_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.rapid_crossbow_3").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_SCATTER_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.scatter_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.scatter_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.scatter_crossbow_3").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_SLAYER_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.slayer_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.slayer_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.gap"));
            tooltip.add(new TranslatableText("tooltip_ench_item.mcdw.ricochet").formatted(Formatting.GREEN));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_THE_SLICER_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.the_slicer_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.the_slicer_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.the_slicer_3").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.the_slicer_4").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.the_slicer_5").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_SOUL_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_crossbow_3").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_SOUL_HUNTER_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_hunter_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_hunter_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.soul_hunter_crossbow_3").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_SPELLBOUND_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.spellbound_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.spellbound_crossbow_2").formatted(Formatting.ITALIC));

        }
        if (stack.getItem() == Crossbows.CROSSBOW_VOID_CALLER_CROSSBOW) {
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.void_caller_crossbow_1").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.void_caller_crossbow_2").formatted(Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip_info_item.mcdw.void_caller_crossbow_3").formatted(Formatting.ITALIC));

        }
    }
}
