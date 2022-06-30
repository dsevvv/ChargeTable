package ca.rpgcraft.chargetable.gui.container;

import ca.rpgcraft.chargetable.Main;
import ca.rpgcraft.chargetable.item.ModItems;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

public class GUIChargeTable extends GuiContainer {

    private static final int CHARGE_ITEM_INPUT_SLOT_INDEX = 0;
    private static final int METAL_INPUT_SLOT_INDEX = 1;
    private static final int OUTPUT_SLOT_INDEX = 2;

    private final ContainerChargeTable container;

    public GUIChargeTable(InventoryPlayer player) {

        super(new ContainerChargeTable(player));
        this.container = (ContainerChargeTable) super.inventorySlots;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        Slot chargeInputSlot = container.getSlot(CHARGE_ITEM_INPUT_SLOT_INDEX);
        Slot metalInputSlot = container.getSlot(METAL_INPUT_SLOT_INDEX);
        Slot outputSlot = container.getSlot(OUTPUT_SLOT_INDEX);

        this.fontRenderer.drawString("Charge Table", 53, 6, 4210752);

        //if the charge item slot has a charge item, draw a checkmark, otherwise draw a cross
        if(chargeInputSlot.getStack().getItem().equals(ModItems.CHARGE_ITEM.getItem())) {
            this.fontRenderer.drawString("\u2713", 8, 22, 4210752);
        }
        else {
            this.fontRenderer.drawString("\u2718", 8, 22, 4210752);
        }

        //if the metal input slot has a metal item, draw a checkmark, otherwise draw a cross
        if(metalInputSlot.getStack().getItem().equals(ModItems.BLUE_METAL.getItem())
        || metalInputSlot.getStack().getItem().equals(ModItems.GREEN_METAL.getItem())
        || metalInputSlot.getStack().getItem().equals(ModItems.PURPLE_METAL.getItem())
        || metalInputSlot.getStack().getItem().equals(ModItems.RED_METAL.getItem())) {
            this.fontRenderer.drawString("\u2713", 8, 38, 4210752);
        }
        else{
            this.fontRenderer.drawString("\u2718", 8, 38, 4210752);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Main.MOD_ID + ":textures/gui/charge_gui.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
}
