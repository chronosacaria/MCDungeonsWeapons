package chronosacaria.mcdw.api.util;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class CleanlinessHelper {
    static final Random random = new Random();

    public static boolean percentToOccur (int chance) {
        return random.nextInt(100) < chance;
    }

    public static boolean canRepairCheck(String[] repairIngredient, ItemStack ingredient) {
        List<Item> potentialIngredients = new ArrayList<>(List.of());
        AtomicBoolean isWood = new AtomicBoolean(false);
        AtomicBoolean isStone = new AtomicBoolean(false);
        if (repairIngredient != null && repairIngredient.length > 0) {
            Arrays.stream(repairIngredient).toList().forEach(repIngredient -> {
                if (repIngredient != null) {
                    if (repIngredient.contentEquals("minecraft:planks"))
                        isWood.set(true);
                    else if (repIngredient.contentEquals("minecraft:stone_crafting_materials"))
                        isStone.set(true);
                    potentialIngredients.add(
                            Registries.ITEM.get(new Identifier(repIngredient)));
                }
            });
        }

        return potentialIngredients.contains(ingredient.getItem())
                || (isWood.get() && ingredient.isIn(ItemTags.PLANKS)
                || (isStone.get() && ingredient.isIn(ItemTags.STONE_CRAFTING_MATERIALS)));
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
