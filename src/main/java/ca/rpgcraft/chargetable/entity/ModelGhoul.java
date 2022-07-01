package ca.rpgcraft.chargetable.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;


public class ModelGhoul extends ModelBase {
	private final ModelRenderer Head;
	private final ModelRenderer Body;
	private final ModelRenderer BodyBlock_r1;
	private final ModelRenderer RightArm;
	private final ModelRenderer RArm3_r1;
	private final ModelRenderer RArm2_r1;
	private final ModelRenderer LeftArm;
	private final ModelRenderer LArm3_r1;
	private final ModelRenderer LArm2_r1;
	private final ModelRenderer WingR;
	private final ModelRenderer wingr3_r1;
	private final ModelRenderer wingr2_r1;
	private final ModelRenderer WingL;
	private final ModelRenderer wingl3_r1;
	private final ModelRenderer wingl2_r1;

	public ModelGhoul() {
		textureWidth = 128;
		textureHeight = 64;

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 5.0F, 0.0F);
		Head.cubeList.add(new ModelBox(Head, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 32, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.4F, false));

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 16.0F, 0.0F);
		

		BodyBlock_r1 = new ModelRenderer(this);
		BodyBlock_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(BodyBlock_r1);
		setRotationAngle(BodyBlock_r1, 0.2618F, 0.0F, 0.0F);
		BodyBlock_r1.cubeList.add(new ModelBox(BodyBlock_r1, 33, 18, -5.0F, -11.0F, 0.0F, 10, 18, 6, 0.4F, false));
		BodyBlock_r1.cubeList.add(new ModelBox(BodyBlock_r1, 69, 0, -2.5F, -7.5F, 7.0F, 4, 12, 2, 0.4F, false));
		BodyBlock_r1.cubeList.add(new ModelBox(BodyBlock_r1, 33, 18, -5.0F, -11.0F, 0.0F, 10, 18, 6, 0.4F, false));
		BodyBlock_r1.cubeList.add(new ModelBox(BodyBlock_r1, 0, 18, -5.0F, -11.0F, 0.0F, 10, 18, 6, 0.0F, false));

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(-7.0F, 7.0F, 1.5F);
		RightArm.cubeList.add(new ModelBox(RightArm, 0, 51, -2.0F, -2.0F, -6.5F, 4, 4, 7, 0.0F, false));

		RArm3_r1 = new ModelRenderer(this);
		RArm3_r1.setRotationPoint(0.0F, 0.8873F, -11.9091F);
		RightArm.addChild(RArm3_r1);
		setRotationAngle(RArm3_r1, 0.3491F, 0.0F, 0.0F);
		RArm3_r1.cubeList.add(new ModelBox(RArm3_r1, 42, 51, -2.0F, -1.3873F, -2.5F, 4, 4, 5, -0.2F, false));

		RArm2_r1 = new ModelRenderer(this);
		RArm2_r1.setRotationPoint(0.0F, 0.0F, -9.0F);
		RightArm.addChild(RArm2_r1);
		setRotationAngle(RArm2_r1, 0.1309F, 0.0F, 0.0F);
		RArm2_r1.cubeList.add(new ModelBox(RArm2_r1, 23, 51, -2.0F, -1.5F, -1.5F, 4, 4, 5, -0.1F, false));

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(7.0F, 7.0F, 1.5F);
		LeftArm.cubeList.add(new ModelBox(LeftArm, 0, 51, -2.0F, -2.0F, -6.5F, 4, 4, 7, 0.0F, false));

		LArm3_r1 = new ModelRenderer(this);
		LArm3_r1.setRotationPoint(0.0F, 0.8873F, -11.9091F);
		LeftArm.addChild(LArm3_r1);
		setRotationAngle(LArm3_r1, 0.3491F, 0.0F, 0.0F);
		LArm3_r1.cubeList.add(new ModelBox(LArm3_r1, 42, 51, -2.0F, -1.3873F, -2.5F, 4, 4, 5, -0.2F, false));

		LArm2_r1 = new ModelRenderer(this);
		LArm2_r1.setRotationPoint(0.0F, 0.0F, -9.0F);
		LeftArm.addChild(LArm2_r1);
		setRotationAngle(LArm2_r1, 0.1309F, 0.0F, 0.0F);
		LArm2_r1.cubeList.add(new ModelBox(LArm2_r1, 23, 51, -2.0F, -1.5F, -1.5F, 4, 4, 5, -0.1F, false));

		WingR = new ModelRenderer(this);
		WingR.setRotationPoint(-1.5F, 6.5F, 7.0F);
		setRotationAngle(WingR, 0.2618F, 0.0F, 0.0F);
		WingR.cubeList.add(new ModelBox(WingR, 96, 49, -0.5F, -0.5F, -1.0F, 1, 1, 12, 0.0F, false));
		WingR.cubeList.add(new ModelBox(WingR, 68, 39, 0.0F, 0.5F, -1.0F, 1, 11, 12, 0.0F, false));

		wingr3_r1 = new ModelRenderer(this);
		wingr3_r1.setRotationPoint(-2.0F, 0.0F, 2.0F);
		WingR.addChild(wingr3_r1);
		setRotationAngle(wingr3_r1, -0.5236F, 0.0F, 0.0F);
		wingr3_r1.cubeList.add(new ModelBox(wingr3_r1, 98, 51, 1.5F, 5.5F, -1.0F, 1, 1, 10, 0.0F, false));

		wingr2_r1 = new ModelRenderer(this);
		wingr2_r1.setRotationPoint(-2.0F, 0.0F, 2.0F);
		WingR.addChild(wingr2_r1);
		setRotationAngle(wingr2_r1, -0.2618F, 0.0F, 0.0F);
		wingr2_r1.cubeList.add(new ModelBox(wingr2_r1, 96, 49, 1.5F, 2.5F, -3.0F, 1, 1, 12, 0.0F, false));

		WingL = new ModelRenderer(this);
		WingL.setRotationPoint(0.5F, 6.5F, 7.0F);
		setRotationAngle(WingL, 0.2618F, 0.0F, 0.0F);
		WingL.cubeList.add(new ModelBox(WingL, 96, 49, -0.5F, -0.5F, -1.0F, 1, 1, 12, 0.0F, true));
		WingL.cubeList.add(new ModelBox(WingL, 68, 39, -1.0F, 0.5F, -1.0F, 1, 11, 12, 0.0F, true));

		wingl3_r1 = new ModelRenderer(this);
		wingl3_r1.setRotationPoint(3.0F, 0.0F, 2.0F);
		WingL.addChild(wingl3_r1);
		setRotationAngle(wingl3_r1, -0.5236F, 0.0F, 0.0F);
		wingl3_r1.cubeList.add(new ModelBox(wingl3_r1, 98, 51, -3.5F, 5.5F, -1.0F, 1, 1, 10, 0.0F, true));

		wingl2_r1 = new ModelRenderer(this);
		wingl2_r1.setRotationPoint(3.0F, 0.0F, 2.0F);
		WingL.addChild(wingl2_r1);
		setRotationAngle(wingl2_r1, -0.2618F, 0.0F, 0.0F);
		wingl2_r1.cubeList.add(new ModelBox(wingl2_r1, 96, 49, -3.5F, 2.5F, -3.0F, 1, 1, 12, 0.0F, true));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Head.render(f5);
		Body.render(f5);
		RightArm.render(f5);
		LeftArm.render(f5);
		WingR.render(f5);
		WingL.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		// flap wings
		// right now these models collide with each other, but the animation looks good enough for this purpose
		// this is my first experience animating a model, so I am excited to discuss strategies for improving the animation
		// with other DoubleJump devs if I get the chance
		float flap = MathHelper.cos(ageInTicks * 0.6F);
		this.WingL.rotateAngleY = flap * 0.75F;
		this.WingR.rotateAngleY = - flap * 0.75F;

		//change state based on target
		EntityGhoul entityGhoul = (EntityGhoul) entityIn;
		boolean aggresive = entityGhoul.isAggressive();

		// slowly turn arms towards angle 1.0F if entityGhoul state is SLEEPING
		// otherwise, turn arms towards angle 0.0F
		// do the same for the head but with slightly different values
		if(!aggresive) {
			//tilting right arm downwards
			if(this.RightArm.rotateAngleX < 1.0F) {
				this.RightArm.rotateAngleX += 0.02F;
			}
			//tilting left arm downwards
			if(this.LeftArm.rotateAngleX < 1.0F) {
				this.LeftArm.rotateAngleX += 0.02F;
			}
			//tilting head downwards
			if(this.Head.rotateAngleX < 1.35F) {
				this.Head.rotateAngleX += 0.02F;
			}
		}
		else {
			// arm flail animation when moving
			this.RightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
			this.LeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;

			//tilting head upwards
			if(this.Head.rotateAngleX > 0.0F) {
				this.Head.rotateAngleX -= 0.03F;
			}
		}
    }
}
