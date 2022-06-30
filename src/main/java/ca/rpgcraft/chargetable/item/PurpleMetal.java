package ca.rpgcraft.chargetable.item;

import ca.rpgcraft.chargetable.gui.creative.ModTab;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class PurpleMetal extends Item {

    public PurpleMetal(String name) {
        super();
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(ModTab.getInstance());
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        int baseAmt = ModItems.PURPLE_METAL.getBaseCharge();
        int count = stack.getCount();
        int finalAmt = baseAmt * count;

        tooltip.add("Gives " + finalAmt + " charge.");
    }

}
