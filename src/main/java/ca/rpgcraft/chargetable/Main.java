package ca.rpgcraft.chargetable;

import ca.rpgcraft.chargetable.entity.EntityGhoul;
import ca.rpgcraft.chargetable.entity.ModelGhoul;
import ca.rpgcraft.chargetable.entity.RenderGhoul;
import ca.rpgcraft.chargetable.gui.handler.GUIHandlerChargeTable;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@Mod(
        modid = Main.MOD_ID,
        name = Main.MOD_NAME,
        version = Main.VERSION
)
public class Main {

    public static final String MOD_ID = "chargetable";
    public static final String MOD_NAME = "ChargeTable";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(MOD_ID)
    public static Main INSTANCE;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GUIHandlerChargeTable());

        EntityRegistry.registerModEntity(new ResourceLocation(MOD_ID, "entity/ghoul"), EntityGhoul.class, "ghoul", 0, INSTANCE, 20, 1, true);
        EntityRegistry.registerEgg(new ResourceLocation(MOD_ID, "entity/ghoul"), 0x28C216, 0x208414);

        RenderingRegistry.registerEntityRenderingHandler(EntityGhoul.class, new IRenderFactory<EntityGhoul>() {
            @Override
            public Render<? super EntityGhoul> createRenderFor(RenderManager manager) {
                return new RenderGhoul(manager, new ModelGhoul(), 0.5F);
            }
        });
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {


    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }
}
