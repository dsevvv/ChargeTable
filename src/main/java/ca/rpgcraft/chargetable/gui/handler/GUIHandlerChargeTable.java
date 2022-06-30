package ca.rpgcraft.chargetable.gui.handler;

import ca.rpgcraft.chargetable.gui.container.ContainerChargeTable;
import ca.rpgcraft.chargetable.gui.container.GUIChargeTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIHandlerChargeTable implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == 0){
            return new ContainerChargeTable(player.inventory);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == 0){
            return new GUIChargeTable(player.inventory);
        }

        return null;
    }
}
