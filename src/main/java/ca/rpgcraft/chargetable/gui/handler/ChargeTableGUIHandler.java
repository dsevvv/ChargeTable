package ca.rpgcraft.chargetable.gui.handler;

import ca.rpgcraft.chargetable.gui.table.ChargeTableContainer;
import ca.rpgcraft.chargetable.gui.table.ChargeTableGUI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ChargeTableGUIHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == 0){
            return new ChargeTableContainer(player.inventory);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == 0){
            return new ChargeTableGUI(player.inventory);
        }

        return null;
    }
}
