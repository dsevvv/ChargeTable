package ca.rpgcraft.chargetable.item;

import ca.rpgcraft.chargetable.gui.creative.CreativeTabMod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class ItemChargeItem extends Item {

    public ItemChargeItem(String name) {
        super();
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabMod.getInstance());
        setMaxStackSize(1);

    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        //if stack doesn't have tag, create one
        if(!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());

        int charge = stack.getTagCompound().getInteger("charge");

        tooltip.add("Holds a charge.");
        tooltip.add("");
        tooltip.add("Current charge: " + charge);
    }
}
