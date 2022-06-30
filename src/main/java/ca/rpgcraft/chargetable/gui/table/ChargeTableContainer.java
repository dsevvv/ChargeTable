package ca.rpgcraft.chargetable.gui.table;

import ca.rpgcraft.chargetable.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;

public class ChargeTableContainer extends Container {

    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int CHARGE_ITEM_INPUT_SLOT_INDEX = 0;
    private static final int METAL_INPUT_SLOT_INDEX = 1;
    private static final int OUTPUT_SLOT_INDEX = 2;

    private final InventoryCrafting chargeInputInventory = new InventoryCrafting(this, 1, 1);
    private final InventoryCrafting metalInputInventory = new InventoryCrafting(this, 1, 1);
    private final InventoryCrafting outputInventory = new InventoryCrafting(this, 1, 1);

    private final InventoryPlayer playerInventory;


    public ChargeTableContainer(InventoryPlayer playerInventory) {
        super();
        this.playerInventory = playerInventory;

        addSlotToContainer(new Slot(chargeInputInventory, 0, 24, 35));
        addSlotToContainer(new Slot(metalInputInventory, 0, 58, 35));
        addSlotToContainer(new Slot(outputInventory, 0, 130, 35));

        for(int playerSlotIndexY = 0; playerSlotIndexY < PLAYER_INVENTORY_ROW_COUNT; playerSlotIndexY++) {
            for(int playerSlotIndexX = 0; playerSlotIndexX < PLAYER_INVENTORY_COLUMN_COUNT; playerSlotIndexX++) {
                addSlotToContainer(new Slot(this.playerInventory,
                        playerSlotIndexX + playerSlotIndexY * 9 + 9,
                        8 + playerSlotIndexX * 18,
                        84 + playerSlotIndexY * 18));
            }
        }
        for(int hotbarSlotIndex = 0; hotbarSlotIndex < PLAYER_INVENTORY_COLUMN_COUNT; hotbarSlotIndex++) {
            addSlotToContainer(new Slot(this.playerInventory,
                    hotbarSlotIndex,
                    8 + hotbarSlotIndex * 18,
                    142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player){
        //stop the player from putting anything other than a charge item in the charge slot
        if(slotId == CHARGE_ITEM_INPUT_SLOT_INDEX) {
            ItemStack stack = player.inventory.getItemStack();
            if(stack.isEmpty()){
                return super.slotClick(slotId, dragType, clickTypeIn, player);
            }

            if(stack.getItem() != ModItems.CHARGE_ITEM.getItem()) {
                return ItemStack.EMPTY;
            }
        }
        //stop the player from putting anything other than a colored metal in the metal slot
        if(slotId == METAL_INPUT_SLOT_INDEX) {
            ItemStack stack = player.inventory.getItemStack();
            if(stack.isEmpty()){
                return super.slotClick(slotId, dragType, clickTypeIn, player);
            }

            if(stack.getItem() != ModItems.BLUE_METAL.getItem()
            && stack.getItem() != ModItems.GREEN_METAL.getItem()
            && stack.getItem() != ModItems.PURPLE_METAL.getItem()
            && stack.getItem() != ModItems.RED_METAL.getItem()) {

                return ItemStack.EMPTY;
            }
        }

        //output logic
        if (slotId == OUTPUT_SLOT_INDEX) {
            //if the output slot is empty, block the player from putting anything in it
            if (outputInventory.getStackInSlot(0).isEmpty()) {

                return ItemStack.EMPTY;
            }
            //if the output slot is filled, take the item out of it and clear both input slots and the output slot
            //I clear the output slot into the player's inventory (if full will drop on ground)
            //and return EMPTY so that the player can't put anything in it while the item is being taken out
            else if (!chargeInputInventory.getStackInSlot(0).isEmpty()
                 && !metalInputInventory.getStackInSlot(0).isEmpty()
                 && isCorrectResult(chargeInputInventory.getStackInSlot(0), metalInputInventory.getStackInSlot(0), outputInventory.getStackInSlot(0))) {

                chargeInputInventory.setInventorySlotContents(0, ItemStack.EMPTY);
                metalInputInventory.setInventorySlotContents(0, ItemStack.EMPTY);
                playerInventory.player.playSound(SoundEvents.ENTITY_WITHER_SPAWN, 1.0f, 3.0f);
                clearContainer(player, player.world, outputInventory);
                return ItemStack.EMPTY;
            }
            //catches incorrect results and prevents the player from removing the item from the output slot
            //clears output slot so the inventory can update the output slot with the correct item
            else {

                player.sendMessage(new TextComponentString("Oops! Incorrect result! Try clicking again."));
                player.playSound(SoundEvents.BLOCK_NOTE_PLING, 1.0f, 0.1f);
                outputInventory.setInventorySlotContents(0, ItemStack.EMPTY);
                return ItemStack.EMPTY;
            }
        }
        else
            return super.slotClick(slotId, dragType, clickTypeIn, player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index){

        Slot clickedSlot = inventorySlots.get(index);

        Slot chargeInputSlot = inventorySlots.get(CHARGE_ITEM_INPUT_SLOT_INDEX);
        Slot metalInputSlot = inventorySlots.get(METAL_INPUT_SLOT_INDEX);
        Slot outputSlot = inventorySlots.get(OUTPUT_SLOT_INDEX);

        if(clickedSlot != null && clickedSlot.getHasStack()){

            //custom slots
            if(clickedSlot.equals(chargeInputSlot)
            || clickedSlot.equals(metalInputSlot)
            || clickedSlot.equals(outputSlot)){

                //try to move item to player inventory
                //fails if full
                if(!playerInventory.addItemStackToInventory(clickedSlot.getStack())){
                    return ItemStack.EMPTY;
                }

                clickedSlot.putStack(ItemStack.EMPTY);
                clickedSlot.onSlotChanged();
            }

            //player slots
            else if(clickedSlot.inventory.equals(playerInventory)){
                //if item is a charge item, move it to charge input slot
                if(clickedSlot.getStack().getItem().equals(ModItems.CHARGE_ITEM.getItem())){
                    //making sure charge item doesnt stack
                    if(!chargeInputSlot.getHasStack()){

                        chargeInputSlot.putStack(clickedSlot.getStack());
                        clickedSlot.putStack(ItemStack.EMPTY);
                        clickedSlot.onSlotChanged();
                    }
                }

                //if item is one of the four colored metals, move it to the metal input slot
                else if(clickedSlot.getStack().getItem().equals(ModItems.BLUE_METAL.getItem())
                || clickedSlot.getStack().getItem().equals(ModItems.GREEN_METAL.getItem())
                || clickedSlot.getStack().getItem().equals(ModItems.PURPLE_METAL.getItem())
                || clickedSlot.getStack().getItem().equals(ModItems.RED_METAL.getItem())){
                    //if the metal input slot has an item, and the item is a metal item matching the color of the metal
                    //the player is trying to put in, move the item to the metal input slot
                    //or if the metal input slot is empty, move the item to the metal input slot
                    if( (inventorySlots.get(METAL_INPUT_SLOT_INDEX).getHasStack()
                        && metalInputSlot.getStack().getItem().equals(clickedSlot.getStack().getItem()))
                    ||  !inventorySlots.get(METAL_INPUT_SLOT_INDEX).getHasStack()){

                        int clickedCount = clickedSlot.getStack().getCount();
                        int metalInputCount = metalInputSlot.getStack().getCount();
                        int totalCount = clickedCount + metalInputCount;

                        //if the two counts added together is greater than the max stack size, only move the max stack size
                        if(totalCount > metalInputSlot.getSlotStackLimit()){
                            metalInputSlot.putStack(new ItemStack(clickedSlot.getStack().getItem(), metalInputSlot.getSlotStackLimit()));
                            metalInputSlot.onSlotChanged();
                            clickedSlot.putStack(new ItemStack(clickedSlot.getStack().getItem(), totalCount - metalInputSlot.getSlotStackLimit()));
                            clickedSlot.onSlotChanged();
                            return ItemStack.EMPTY;
                        }
                        //otherwise, add the two counts together
                        else{
                            metalInputSlot.putStack(new ItemStack(clickedSlot.getStack().getItem(), totalCount));
                            metalInputSlot.onSlotChanged();
                            clickedSlot.putStack(ItemStack.EMPTY);
                            clickedSlot.onSlotChanged();
                            return ItemStack.EMPTY;
                        }
                    }
                }
            }
        }

        return ItemStack.EMPTY;
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {

        Slot chargeInputSlot = inventorySlots.get(CHARGE_ITEM_INPUT_SLOT_INDEX);
        Slot metalInputSlot = inventorySlots.get(METAL_INPUT_SLOT_INDEX);
        Slot outputSlot = inventorySlots.get(OUTPUT_SLOT_INDEX);

        ItemStack chargeInputStack = chargeInputSlot.getStack();
        ItemStack metalInputStack = metalInputSlot.getStack();
        ItemStack outputStack = outputSlot.getStack();

        Item chargeInputItem = chargeInputStack.getItem();
        Item metalInputItem = metalInputStack.getItem();

        //checking if the charge item slot has a charge item,
        //and the metal input slot has a metal,
        //and that the current output result is wrong
        //if all those checks pass, set the output slot to the correct result
        if(chargeInputSlot.getHasStack()
        && metalInputSlot.getHasStack()
        && !isCorrectResult(chargeInputStack, metalInputStack, outputStack)
        && chargeInputItem.equals(ModItems.CHARGE_ITEM.getItem())
        && (metalInputItem.equals(ModItems.BLUE_METAL.getItem())
        || metalInputItem.equals(ModItems.GREEN_METAL.getItem())
        || metalInputItem.equals(ModItems.PURPLE_METAL.getItem())
        || metalInputItem.equals(ModItems.RED_METAL.getItem()))){

            ItemStack newOutputStack = chargeInputStack.copy();

            //if ItemStack does not have a TagCompound, add one
            if(!newOutputStack.hasTagCompound()){
                newOutputStack.setTagCompound(new NBTTagCompound());
            }

            int charge = newOutputStack.getTagCompound().getInteger("charge");
            int metalCharge = metalInputItem.equals(ModItems.BLUE_METAL.getItem()) ? ModItems.BLUE_METAL.getBaseCharge()
                            : metalInputItem.equals(ModItems.GREEN_METAL.getItem()) ? ModItems.GREEN_METAL.getBaseCharge()
                            : metalInputItem.equals(ModItems.RED_METAL.getItem()) ? ModItems.RED_METAL.getBaseCharge()
                            : ModItems.PURPLE_METAL.getBaseCharge();

            metalCharge = metalCharge * metalInputStack.getCount();

            int result = charge + metalCharge;

            newOutputStack.getTagCompound().setInteger("charge", result);

            outputSlot.putStack(newOutputStack);
            outputSlot.onSlotChanged();
        }
    }

    @Override
    public boolean canMergeSlot(ItemStack itemStack, Slot slot){
        return !slot.inventory.equals(outputInventory);
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn){
        clearContainer(playerIn, playerIn.world, chargeInputInventory);
        clearContainer(playerIn, playerIn.world, metalInputInventory);
    }

    protected boolean isCorrectResult(ItemStack inputChargeStack, ItemStack inputMetalStack, ItemStack outputStack){
        if(outputStack.isEmpty()){
            return false;
        }

        int inputChargeAmt = inputChargeStack.getTagCompound().getInteger("charge");
        int outputChargeAmt = outputStack.getTagCompound().getInteger("charge");

        int chargeToAdd = inputMetalStack.getItem().equals(ModItems.BLUE_METAL.getItem()) ? ModItems.BLUE_METAL.getBaseCharge()
                        : inputMetalStack.getItem().equals(ModItems.GREEN_METAL.getItem()) ? ModItems.GREEN_METAL.getBaseCharge()
                        : inputMetalStack.getItem().equals(ModItems.RED_METAL.getItem()) ? ModItems.RED_METAL.getBaseCharge()
                        : ModItems.PURPLE_METAL.getBaseCharge();

        chargeToAdd = chargeToAdd * inputMetalStack.getCount();

        return inputChargeAmt + chargeToAdd == outputChargeAmt;
    }
}
