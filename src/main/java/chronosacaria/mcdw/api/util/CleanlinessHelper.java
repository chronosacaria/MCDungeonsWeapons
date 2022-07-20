package chronosacaria.mcdw.api.util;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

import java.util.Random;

public class CleanlinessHelper {
    static final Random random = new Random();

    public static boolean percentToOccur (int chance) {
        return random.nextInt(100) <= chance;
    }

    public static void playCenteredSound (LivingEntity center, SoundEvent soundEvent, float volume, float pitch) {
        playCenteredSound(center, soundEvent, SoundCategory.PLAYERS, volume, pitch);
    }

    public static void playCenteredSound (LivingEntity center, SoundEvent soundEvent, SoundCategory soundCategory, float volume, float pitch) {
        center.world.playSound(null,
                center.getX(), center.getY(), center.getZ(),
                soundEvent, soundCategory,
                volume, pitch);
    }

    public static void mcdw$dropItem(LivingEntity le, Item item) {
        mcdw$dropItem(le, item, 1);
    }

    public static void mcdw$dropItem(LivingEntity le, ItemStack itemStack) {
        ItemEntity it = new ItemEntity(
                le.world, le.getX(), le.getY(), le.getZ(),
                itemStack);
        le.world.spawnEntity(it);
    }

    public static void mcdw$dropItem(LivingEntity le, Item item, int amount) {
        mcdw$dropItem(le, new ItemStack(item, amount));
    }
}