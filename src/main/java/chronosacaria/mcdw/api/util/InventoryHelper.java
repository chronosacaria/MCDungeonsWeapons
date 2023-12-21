package chronosacaria.mcdw.api.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InventoryHelper {

    public static boolean mcdw$hasItem(PlayerEntity playerEntity, Item item) {
        return mcdw$hasItem(playerEntity, item, 1);
    }

    public static boolean mcdw$hasItem(PlayerEntity playerEntity, Item item, int count) {
        return mcdw$countItem(playerEntity, item) >= count;
    }

    public static int mcdw$countItem(PlayerEntity playerEntity, Item item) {
        PlayerInventory playerInventory = playerEntity.getInventory();
        int count = 0;
        for (int slotID = 0; slotID < playerInventory.size(); slotID++) {
            ItemStack currentStack = playerInventory.getStack(slotID);
            if (currentStack.getItem() == item)
                count += currentStack.getCount();
        }
        return count;
    }

    public static void mcdw$systematicReplace(PlayerEntity player, Item toReplace, Item replaceTo, int count) {
        PlayerInventory playerInv = player.getInventory();
        playerInv.getSlotWithStack(new ItemStack(toReplace));

         // Try playerInv.remove(...) at some point. Needs predicate
         if (playerInv.getEmptySlot() >= 0) { //Player has at least one empty slot
             int hasToReplace = mcdw$countItem(player, toReplace);
             // Can't make as many 1 for 1's as specified bc toReplace count is too low
             if (hasToReplace < count) {
                 mcdw$systematicReplace(player, toReplace, replaceTo, hasToReplace);
                 return;
             }
             List<Integer> emptySlots = mcdw$getAllEmptySlots(player);
             for (Integer slotIndex: emptySlots) {
                 if (count > 0)
                     count = mcdw$switchOutItems(player, toReplace, replaceTo, count, slotIndex);
                 else
                     break;
             }
             if (count > 0)
                 mcdw$systematicReplace(player, toReplace, replaceTo, count);
         } else {
             mcdw$replaceWithoutEmptySlots(player, toReplace, replaceTo, count);
         }
    }

    public static void mcdw$systematicReplacePotions(PlayerEntity player, Item toReplace, Potion potionReplaceTo, int count) {
        // Minecraft code is dumb sometimes. Just make potions their own item gah
        PlayerInventory playerInv = player.getInventory();
        List<Integer> stackSlots = mcdw$getSlotsWithStack(player, toReplace);

        record SlotInfo(int index, int size) {}
        List<SlotInfo> toReplaceSlots = new ArrayList<>();
        for (int slotIndex : stackSlots)
            toReplaceSlots.add(new SlotInfo(slotIndex, playerInv.getStack(slotIndex).getCount()));
        // don't forget about offhand
        ItemStack offhand = playerInv.getStack(PlayerInventory.OFF_HAND_SLOT);
        if (offhand.isOf(toReplace))
            toReplaceSlots.add(new SlotInfo(PlayerInventory.OFF_HAND_SLOT, offhand.getCount()));
        // sort by size (ascending order)
        toReplaceSlots.sort(Comparator.comparingInt(a -> a.size));

        while (count > 0 && !toReplaceSlots.isEmpty()) {
            SlotInfo slot = toReplaceSlots.get(0);
            ItemStack stackReplaceTo = PotionUtil.setPotion(new ItemStack(Items.POTION), potionReplaceTo);
            if (slot.size == 1) {
                playerInv.setStack(slot.index, stackReplaceTo);
                toReplaceSlots.remove(0);
            } else {
                int emptySlot = playerInv.getEmptySlot();
                if (emptySlot == PlayerInventory.NOT_FOUND)
                    // no empty space, stop here
                    break;
                playerInv.getStack(slot.index).decrement(1);
                playerInv.setStack(emptySlot, stackReplaceTo);
                toReplaceSlots.set(0, new SlotInfo(slot.index, slot.size - 1));
            }
            count--;
        }
    }

    private static void mcdw$replaceWithoutEmptySlots(PlayerEntity player, Item toReplace, Item replaceTo, int count) {
        PlayerInventory playerInv = player.getInventory();
        int ogCount = count;
        List<Integer> stackSlots = mcdw$getSlotsWithStack(player, toReplace);
        for (int slotIndex: stackSlots) {

            int availableToReplace = playerInv.getStack(slotIndex).getCount();

            if (availableToReplace <= count) {
                playerInv.insertStack(slotIndex, new ItemStack(replaceTo, availableToReplace));
                count -= availableToReplace;
                mcdw$switchOutItems(player, toReplace, replaceTo, count, slotIndex);
            }
        }
        if (count == ogCount) {
            mcdw$optimizeSortItemStack(player, toReplace);
            mcdw$replaceWithoutEmptySlots(player, toReplace, replaceTo, count);
        }
    }

    private static void mcdw$optimizeSortItemStack(PlayerEntity player, Item toReplace) {
        PlayerInventory playerInv = player.getInventory();
        List<Integer> stackSlots = mcdw$getSlotsWithStack(player, toReplace);

        int slotTakingFromIndex = 0;

        for (int i = 1; i < stackSlots.size(); i++) {
            int slotTakingFrom = stackSlots.get(slotTakingFromIndex);
            int availableToTake = playerInv.getStack(slotTakingFrom).getCount();
            if (availableToTake == 0)
                slotTakingFromIndex++;

            int slotToReplaceTo = stackSlots.get(i);
            int alreadyInSlotToReplaceTo = playerInv.getStack(slotToReplaceTo).getCount();
            int missingFromMax = toReplace.getMaxCount() - alreadyInSlotToReplaceTo;
            // Give the proper amount of replaceTo
            int j = Math.min(missingFromMax, availableToTake);
            // Remove the same amount of toReplace
            playerInv.removeStack(slotTakingFrom, j);
            playerInv.insertStack(slotToReplaceTo, new ItemStack(toReplace, j));
        }
    }

    public static int mcdw$switchOutItems(PlayerEntity player, Item toReplace, Item replaceTo, int count, int slotIndex) {
        PlayerInventory playerInv = player.getInventory();
        int replaceAmount = replaceTo.getMaxCount();
        if (count > 0) {
            // Get amount of toReplace in the first found stack
            int k = playerInv.getSlotWithStack(new ItemStack(toReplace));
            int availableToReplace = playerInv.getStack(k).getCount();
            // Give the proper amount of replaceTo
            int j = Math.min(replaceAmount, availableToReplace);
            playerInv.insertStack(slotIndex, new ItemStack(replaceTo, j));
            // Remove the same amount of toReplace
            playerInv.removeStack(k, j);
            count -= j;

            // Check to see if the stack to be placed to can still have any to be placed to.
            int h = replaceAmount - playerInv.getStack(slotIndex).getCount();
            if (h > 0) {
                count = mcdw$switchOutItems(player, toReplace, replaceTo, count, slotIndex);
                return count;
            }
        }
        return count;
    }

    public static List<Integer> mcdw$getAllEmptySlots(PlayerEntity player) {
        List<Integer> emptySlots = new ArrayList<>();
        PlayerInventory playerInv = player.getInventory();
        for (int i = 0; i < playerInv.main.size(); i++) {
            if ((playerInv.main.get(i)).isEmpty()) {
                emptySlots.add(i);
            }
        }
        return emptySlots;
    }

    public static List<Integer> mcdw$getSlotsWithStack(PlayerEntity player, Item toReplace) {
        PlayerInventory playerInv = player.getInventory();
        List<Integer> stackSlots = new ArrayList<>();
        for(int i = 0; i < playerInv.main.size(); ++i) {
            if (!playerInv.main.get(i).isEmpty() && ItemStack.canCombine(new ItemStack(toReplace), playerInv.getStack(i))) {
                stackSlots.add(i);
            }
        }

        return stackSlots;
    }

    public static void mcdw$deductAmountOfItem(PlayerEntity player, Item toTake, int amount) {
        List<Integer> stackSlots = mcdw$getSlotsWithStack(player, toTake);
        amount = Math.min(amount, mcdw$countItem(player, toTake));
        for (Integer stackSlot : stackSlots) {
            ItemStack slot = player.getInventory().getStack(stackSlot);
            int k = Math.min(slot.getCount(), amount);
            slot.decrement(k);
            amount -= k;
            if (amount == 0) {
                break;
            }
        }
    }

}
