package ca.rpgcraft.chargetable.registry;

import ca.rpgcraft.chargetable.block.ChargeTableBlock;
import ca.rpgcraft.chargetable.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ModObjectRegistry {

    @SubscribeEvent
    public static void addItems(RegistryEvent.Register<Item> event) {
        for (ModItems value : ModItems.values()) {
            Item item = value.getItem();
            event.getRegistry().register(item);
            ModelLoader.setCustomModelResourceLocation(
                    item,
                    0,
                    new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }

        Block chargeTableBlock = ChargeTableBlock.getInstance();
        ItemBlock itemBlock = new ItemBlock(chargeTableBlock);

        itemBlock.setRegistryName(chargeTableBlock.getRegistryName());
        event.getRegistry().register(itemBlock);
        ModelLoader.setCustomModelResourceLocation(
                itemBlock,
                0,
                new ModelResourceLocation(itemBlock.getRegistryName(), "inventory"));
    }

    @SubscribeEvent
    public static void addBlocks(RegistryEvent.Register<Block> event) {
        Block chargeTableBlock = ChargeTableBlock.getInstance();

        event.getRegistry().register(chargeTableBlock);
        ModelLoader.setCustomModelResourceLocation(
                Item.getItemFromBlock(chargeTableBlock),
                0,
                new ModelResourceLocation(chargeTableBlock.getRegistryName(), "inventory"));
    }
}
