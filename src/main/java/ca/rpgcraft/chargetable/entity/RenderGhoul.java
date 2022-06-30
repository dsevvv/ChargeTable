package ca.rpgcraft.chargetable.entity;

import ca.rpgcraft.chargetable.Main;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderGhoul extends RenderLiving<EntityGhoul> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MOD_ID + ":textures/entity/ghoul.png");

    public RenderGhoul(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
        this.bindTexture(TEXTURE);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityGhoul entity) {
        return TEXTURE;
    }
}
