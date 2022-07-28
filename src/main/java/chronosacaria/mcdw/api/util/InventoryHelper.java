package chronosacaria.mcdw.api.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

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
             int hasToReplace = mcdw$countItem(player, toReplace);                         // ^^^^ We will not have to run this code if an itemRemove helper is made
             // Can't make as many 1 for 1's as specified bc toReplace count is too low
             if (hasToReplace <= count) {                                                   // ---------- Are we looking to replace an item one for one or are we just getting a stack of items and exchanging for another stack of predefined value
                 mcdw$systematicReplace(player, toReplace, replaceTo, hasToReplace);       // ---------- Why are we not using <= over simply <
                 return;                                                                   // ---------- Why is a function even calling itself in the first place
             }
             List<Integer> emptySlots = mcdw$getAllEmptySlots(player);                     // -----  This code would not have to run if line 46 was set to return all available slots as this line does , can be used in same way just check if value is !NULL
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

        // This code must remove a certain amount of glass bottles from the players inventory and then replace them with full ones
        // For now, there is a bug which duplicates bottles, I suspect this is due to incorrect item location and subtraction
        // To increase code readability and effectiveness I will be implementing more temporary variables that will store the exchange progress made
        mcdw$optimizeSortItemStack(player,toReplace);
        // Minecraft code is dumb sometimes. Just make potions their own item gah
        PlayerInventory playerInv = player.getInventory();
        playerInv.getSlotWithStack(new ItemStack(toReplace));

        // Try playerInv.remove(...) at some point. Needs predicate
        if (playerInv.getEmptySlot() >= 0) { //Player has at least one empty slot

            //countItem method is only returning a single stacks value (hasToReplace was a hard variable to understand but indicates how many bottles the player has to spend on the enchantment)
            int hasToReplace = mcdw$countItem(player, toReplace);


            // Can't make as many 1 for 1's as specified bc toReplace count is too low - so calls itself with the new value using hasToReplace
            if (hasToReplace < count) {
                mcdw$systematicReplacePotions(player, toReplace, stackReplaceTo, hasToReplace);
                return;
            }


            // I WILL NEED TO CREATE METHOD THAT FINDS HOW MANY EMPTY BOTTLES PLAYER HAS IN INVENTORY TO BEGIN TACKLING THIS ISSUE
            // Getting all slots that are empty and creating list with those as indexes
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
            //This code was causing the bug of duping the bottles :D
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

    private static void mcdw$optimizeSortItemStack(PlayerEntity player, Item toReplace) {                               // This function needs to be removing old items before adding new ones
        PlayerInventory playerInv = player.getInventory();
        List<Integer> stackSlots = mcdw$getSlotsWithStack(player, toReplace);

            // Counting the amount of an item the player has
            int totalOfItem = mcdw$countItem(player,toReplace);
            int stacksToMake = totalOfItem / toReplace.getMaxCount();
            int remainingItems = totalOfItem % toReplace.getMaxCount();
            // Removing all items of the specified type
            mcdw$removeAllItemsOfType(player, toReplace);
            //Adding back the stacks
            List<Integer> freeSlots = mcdw$getAllEmptySlots(player);
            for(int i = 1; i <= stacksToMake + 1; i++){
                playerInv.insertStack(freeSlots.get(1), new ItemStack(toReplace, toReplace.getMaxCount()));
                if(i == stacksToMake + 1){
                    playerInv.insertStack(freeSlots.get(i), new ItemStack(toReplace, remainingItems));
                }
            }




//            // iterating through list made previously until a merge is made (stack 1 + stack 2)
//            for (int i = 0; i < stackSlots.size() - 1; i++) {
//                // The slot that we are taking from at index 0 AKA first item that will be used to merge with second
//
//
//                //int slotTakingFrom = stackSlots.get(0);
//                int slotTakingFrom = stackSlots.get(i);
//
//
//                // The size / amount of bottles or items within the 0th stack
//                int availableToTake = playerInv.getStack(slotTakingFrom).getCount();
//
//
//                // If the stack ends up empty , end loop
//                if (availableToTake == 0)
//                    break;
//
//                // Identifying stack that can be merged into AKA stack 2
//                int slotToReplaceTo = stackSlots.get(i);
//
//                int alreadyInSlotToReplaceTo = playerInv.getStack(slotToReplaceTo).getCount();
//                // Figuring out the amount that needs to be added
//                int addAmount = toReplace.getMaxCount() - alreadyInSlotToReplaceTo;
//                int j = Math.min(addAmount, availableToTake);
//
//                // Give the proper amount of replaceTo
//                playerInv.insertStack(slotToReplaceTo, new ItemStack(toReplace, j));
//                // Remove the same amount of toReplace
//                playerInv.removeStack(slotTakingFrom, j);

//            }
    }

    public static int mcdw$switchOutItems(PlayerEntity player, Item toReplace, Item replaceTo, int count, int slotIndex) {
                                                                                                                                // This function needs to be removing old items before adding new ones
        PlayerInventory playerInv = player.getInventory();
        int replaceAmount = replaceTo.getMaxCount();
        if (count > 0) {
            // Get amount of toReplace in the first found stack
            int k = playerInv.getSlotWithStack(new ItemStack(toReplace));
            int availableToReplace = playerInv.getStack(k).getCount();
            int j = Math.min(replaceAmount, availableToReplace);
            // Replace items after
            playerInv.insertStack(slotIndex, new ItemStack(replaceTo, j));
            // Remove items first
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



    public static void mcdw$removeAllItemsOfType(PlayerEntity player, Item itemToCheck){
        PlayerInventory playerInv = player.getInventory();
        if(!playerInv.contains(itemToCheck.getDefaultStack())){
            return;
        }
        for(int slotID = 0; slotID < playerInv.MAIN_SIZE; slotID++){
            ItemStack currentStack = playerInv.getStack(slotID);
            if (currentStack.getItem() == itemToCheck){
                 int amountToRemove = currentStack.getCount();
                 currentStack.decrement(amountToRemove);
            }
        }
    }





}