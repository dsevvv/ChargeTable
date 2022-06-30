package ca.rpgcraft.chargetable.gui.creative;

import ca.rpgcraft.chargetable.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabMod extends CreativeTabs {

    private static CreativeTabMod instance;

    private CreativeTabMod(String name) {
        super(name);
    }

    @Override
    public ItemStack createIcon() {
        return ModItems.CHARGE_ITEM.getItem().getDefaultInstance();
    }

    public static CreativeTabMod getInstance() {
        if(instance == null)
            instance = new CreativeTabMod("charge_table");

        return instance;
    }
}
