package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.Mcdw;
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

public class McdwCrossbow extends CrossbowItem {

    public McdwCrossbow(Settings settings, String id) {
        super(settings);
        Registry.register(Registry.ITEM, new Identifier(Mcdw.MOD_ID, id), this);
    }

    @Override
    public boolean isUsedOnRelease(ItemStack stack){
        return stack.getItem() == Crossbows.CROSSBOW_AUTO_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_AZURE_SEEKER
                || stack.getItem() == Crossbows.CROSSBOW_BABY_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_BURST_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_BUTTERFLY_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_CORRUPTED_CROSSBOW
                || stack.getItem() == Crossbows.CROSSBOW_RAPID_CROSSBOW;

    }

}
