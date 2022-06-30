package ca.rpgcraft.chargetable.block;

import ca.rpgcraft.chargetable.Main;
import ca.rpgcraft.chargetable.gui.creative.ModTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.net.NetworkInterface;
import java.nio.channels.NetworkChannel;
import java.util.List;

public class ChargeTableBlock extends Block {

    private static ChargeTableBlock instance;

    public static ChargeTableBlock getInstance() {
        if(instance == null)
            instance = new ChargeTableBlock("charge_table", Material.ANVIL);

        return instance;
    }

    private ChargeTableBlock(String name, Material material) {
        super(material);
        this.setTranslationKey(name)
            .setRegistryName(name)
            .setCreativeTab(ModTab.getInstance())
            .setHardness(1.5F)
            .setResistance(10.0F);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        playerIn.openGui(Main.INSTANCE, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());

        return true;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn){
        tooltip.add("Used to charge the Charge Item.");
    }
}
