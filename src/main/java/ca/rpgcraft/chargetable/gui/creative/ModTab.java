package ca.rpgcraft.chargetable.gui.creative;

import ca.rpgcraft.chargetable.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModTab extends CreativeTabs {

    private static ModTab instance;

    private ModTab(String name) {
        super(name);
    }

    @Override
    public ItemStack createIcon() {
        return ModItems.CHARGE_ITEM.getItem().getDefaultInstance();
    }

    public static ModTab getInstance() {
        if(instance == null)
            instance = new ModTab("charge_table");

        return instance;
    }
}
