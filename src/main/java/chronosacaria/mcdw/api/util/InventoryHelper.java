package chronosacaria.mcdw.api.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InventoryHelper {

    public static boolean mcdw$hasItem(PlayerEntity playerEntity, Item item) {
        return mcdw$hasItem(playerEntity, item, 1);
    }

    public static boolean mcdw$hasItem(PlayerEntity playerEntity, Item item, int count) {
        PlayerInventory playerInventory = playerEntity.getInventory();
        for (int slotID = 0; slotID < playerInventory.size(); slotID++) {
            ItemStack currentStack = playerInventory.getStack(slotID);
            if (currentStack.getItem() == item) {
                count -= currentStack.getCount();
                if (count <= 0)
                    return true;
            }
        }
        return false;
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

    public static void mcdw$systematicReplacePotions(PlayerEntity player, Item toReplace, ItemStack stackReplaceTo, int count) {
        // Minecraft code is dumb sometimes. Just make potions their own item gah
        PlayerInventory playerInv = player.getInventory();
        playerInv.getSlotWithStack(new ItemStack(toReplace));

        // Try playerInv.remove(...) at some point. Needs predicate
        if (playerInv.getEmptySlot() >= 0) { //Player has at least one empty slot
            int hasToReplace = mcdw$countItem(player, toReplace);
            // Can't make as many 1 for 1's as specified bc toReplace count is too low
            if (hasToReplace < count) {
                mcdw$systematicReplacePotions(player, toReplace, stackReplaceTo, hasToReplace);
                return;
            }
            List<Integer> emptySlots = mcdw$getAllEmptySlots(player);
            for (Integer slotIndex: emptySlots) {
                if (count > 0) {
                    playerInv.setStack(slotIndex, stackReplaceTo);
                    int k = playerInv.getSlotWithStack(new ItemStack(toReplace));
                    playerInv.getStack(k).decrement(1);
                    count--;
                } else
                    break;
            }
            if (count > 0)
                mcdw$systematicReplacePotions(player, toReplace, stackReplaceTo, count);
        } else {
            mcdw$optimizeSortItemStack(player, toReplace);
            if (playerInv.getEmptySlot() >= 0) {
                mcdw$systematicReplacePotions(player, toReplace, stackReplaceTo, count);
            } else {
                List<Integer> stackSlots = mcdw$getSlotsWithStack(player, toReplace);
                for (int slotIndex : stackSlots) {

                    int availableToReplace = playerInv.getStack(slotIndex).getCount();

                    if (availableToReplace == 1) {
                        playerInv.insertStack(slotIndex, stackReplaceTo);
                        count -= availableToReplace;
                        mcdw$optimizeSortItemStack(player, toReplace);
                        mcdw$systematicReplacePotions(player, toReplace, stackReplaceTo, count);
                    }
                }
            }
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

        for (int i = 1; i < stackSlots.size(); i++) {
            int slotTakingFrom = stackSlots.get(0);
            int availableToTake = playerInv.getStack(slotTakingFrom).getCount();
            if (availableToTake == 0)
                break;

            int slotToReplaceTo = stackSlots.get(i);
            int alreadyInSlotToReplaceTo = playerInv.getStack(slotToReplaceTo).getCount();
            int addAmount = toReplace.getMaxCount() - alreadyInSlotToReplaceTo;
            // Give the proper amount of replaceTo
            int j = Math.min(addAmount, availableToTake);
            playerInv.insertStack(slotToReplaceTo, new ItemStack(toReplace, alreadyInSlotToReplaceTo + j));
            // Remove the same amount of toReplace
            playerInv.removeStack(slotTakingFrom, j);
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
            ItemStack currStack = playerInv.getStack(i);
            if (currStack.isEmpty())
                emptySlots.add(i);
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
}