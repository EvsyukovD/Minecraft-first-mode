package com.ed.testmod.client.entity.model;

import java.util.List;
import java.util.Random;

import com.ed.testmod.entities.CommandantEntity;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.HandSide;

public class CommandantEntityModel<T extends CommandantEntity> extends BipedModel<T> {

	/*private final ModelRenderer Body;
	private final ModelRenderer leftleg;
	private final ModelRenderer rightleg;
	private final ModelRenderer mainbody;
	private final ModelRenderer leftarm;
	private final ModelRenderer rightarm;
	private final ModelRenderer head;*/
	 private List<ModelRenderer> modelRenderers = Lists.newArrayList();
	   public final ModelRenderer bipedLeftArmwear;
	   public final ModelRenderer bipedRightArmwear;
	   public final ModelRenderer bipedLeftLegwear;
	   public final ModelRenderer bipedRightLegwear;
	   public final ModelRenderer bipedBodyWear;
	   private final ModelRenderer bipedCape;
	   private final ModelRenderer bipedDeadmau5Head;
	   private final boolean smallArms;

	
	public CommandantEntityModel(float modelSize,boolean smallArmsIn) {
		super(RenderType::getEntityTranslucent, modelSize, 0.0F, 64, 64);
         
	      this.smallArms = smallArmsIn;
		this.bipedDeadmau5Head = new ModelRenderer(this, 24, 0);
	      this.bipedDeadmau5Head.addBox(-3.0F, -6.0F, -1.0F, 6.0F, 6.0F, 1.0F, modelSize);
	      this.bipedCape = new ModelRenderer(this, 0, 0);
	      this.bipedCape.setTextureSize(64, 32);
	      this.bipedCape.addBox(-5.0F, 0.0F, -1.0F, 10.0F, 16.0F, 1.0F, modelSize);
	      if (smallArmsIn) {
	          this.bipedLeftArm = new ModelRenderer(this, 32, 48);
	          this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, modelSize);
	          this.bipedLeftArm.setRotationPoint(5.0F, 2.5F, 0.0F);
	          this.bipedRightArm = new ModelRenderer(this, 40, 16);
	          this.bipedRightArm.addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, modelSize);
	          this.bipedRightArm.setRotationPoint(-5.0F, 2.5F, 0.0F);
	          this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
	          this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, modelSize + 0.25F);
	          this.bipedLeftArmwear.setRotationPoint(5.0F, 2.5F, 0.0F);
	          this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
	          this.bipedRightArmwear.addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, modelSize + 0.25F);
	          this.bipedRightArmwear.setRotationPoint(-5.0F, 2.5F, 10.0F);
	       } else {
	          this.bipedLeftArm = new ModelRenderer(this, 32, 48);
	          this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, modelSize);
	          this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
	          this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
	          this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, modelSize + 0.25F);
	          this.bipedLeftArmwear.setRotationPoint(5.0F, 2.0F, 0.0F);
	          this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
	          this.bipedRightArmwear.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, modelSize + 0.25F);
	          this.bipedRightArmwear.setRotationPoint(-5.0F, 2.0F, 10.0F);
	       }

	       this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
	       this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, modelSize);
	       this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
	       this.bipedLeftLegwear = new ModelRenderer(this, 0, 48);
	       this.bipedLeftLegwear.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, modelSize + 0.25F);
	       this.bipedLeftLegwear.setRotationPoint(1.9F, 12.0F, 0.0F);
	       this.bipedRightLegwear = new ModelRenderer(this, 0, 32);
	       this.bipedRightLegwear.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, modelSize + 0.25F);
	       this.bipedRightLegwear.setRotationPoint(-1.9F, 12.0F, 0.0F);
	       this.bipedBodyWear = new ModelRenderer(this, 16, 32);
	       this.bipedBodyWear.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, modelSize + 0.25F);
	       this.bipedBodyWear.setRotationPoint(0.0F, 0.0F, 0.0F);
		/*Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(leftleg);
		leftleg.setTextureOffset(20, 16).addBox(-5.0F, -9.0F, -2.0F, 5.0F, 9.0F, 5.0F, modelSize, false);

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(rightleg);
		rightleg.setTextureOffset(0, 16).addBox(0.0F, -9.0F, -2.0F, 5.0F, 9.0F, 5.0F, modelSize, false);

		mainbody = new ModelRenderer(this);
		mainbody.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(mainbody);
		mainbody.setTextureOffset(0, 0).addBox(-5.0F, -20.0F, -2.0F, 10.0F, 11.0F, 5.0F, modelSize, false);

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(leftarm);
		leftarm.setTextureOffset(22, 30).addBox(-8.0F, -20.0F, -2.0F, 3.0F, 10.0F, 5.0F, modelSize, false);

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(rightarm);
		rightarm.setTextureOffset(30, 0).addBox(5.0F, -20.0F, -2.0F, 3.0F, 10.0F, 5.0F, modelSize, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(head);
		head.setTextureOffset(0, 30).addBox(-3.0F, -26.0F, -2.0F, 6.0F, 6.0F, 5.0F, modelSize, false);*/
		
		/*this.bipedBody = mainbody;
		this.bipedHead = head;
		this.bipedLeftArm =leftarm;
		this.bipedLeftLeg = leftleg;
		this.bipedRightArm = rightarm;
		this.bipedRightLeg = rightleg;*/
	}
    
	public CommandantEntityModel() {
		this(0.0F,false);
	}
	
	protected Iterable<ModelRenderer> getBodyParts() {
	      return Iterables.concat(super.getBodyParts(), ImmutableList.of(this.bipedLeftLegwear, this.bipedRightLegwear, this.bipedLeftArmwear, this.bipedRightArmwear, this.bipedBodyWear));
	   }

	   public void renderEars(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn) {
	      this.bipedDeadmau5Head.copyModelAngles(this.bipedHead);
	      this.bipedDeadmau5Head.rotationPointX = 0.0F;
	      this.bipedDeadmau5Head.rotationPointY = 0.0F;
	      this.bipedDeadmau5Head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
	   }
	   public void renderCape(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn) {
		      this.bipedCape.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
		   }
	
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		this.rightArmPose = BipedModel.ArmPose.EMPTY;
		this.leftArmPose = BipedModel.ArmPose.EMPTY;
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
	}
	
	public void translateHand(HandSide sideIn, MatrixStack matrixStackIn) {
		ModelRenderer modelrenderer = this.getArmForSide(sideIn);
	      if (this.smallArms) {
	         float f = 0.5F * (float)(sideIn == HandSide.RIGHT ? 1 : -1);
	         modelrenderer.rotationPointX += f;
	         modelrenderer.translateRotate(matrixStackIn);
	         modelrenderer.rotationPointX -= f;
	      } else {
	         modelrenderer.translateRotate(matrixStackIn);
	      }
	}
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.bipedLeftLegwear.copyModelAngles(this.bipedLeftLeg);
	      this.bipedRightLegwear.copyModelAngles(this.bipedRightLeg);
	      this.bipedLeftArmwear.copyModelAngles(this.bipedLeftArm);
	      this.bipedRightArmwear.copyModelAngles(this.bipedRightArm);
	      this.bipedBodyWear.copyModelAngles(this.bipedBody);
	      if (entity.isCrouching()) {
	         this.bipedCape.rotationPointY = 2.0F;
	      } else {
	         this.bipedCape.rotationPointY = 0.0F;
	      }
		/*this.leftleg.copyModelAngles(this.bipedLeftLeg);
		this.rightleg.copyModelAngles(this.bipedRightLeg);
		this.leftarm.copyModelAngles(this.bipedLeftArm);
		this.rightarm.copyModelAngles(this.bipedRightArm);
		this.mainbody.copyModelAngles(this.bipedBody);*/
	}

	/*@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}*/

	public void setVisible(boolean visible) {
	      super.setVisible(visible);
	      this.bipedLeftArmwear.showModel = visible;
	      this.bipedRightArmwear.showModel = visible;
	      this.bipedLeftLegwear.showModel = visible;
	      this.bipedRightLegwear.showModel = visible;
	      this.bipedBodyWear.showModel = visible;
	      this.bipedCape.showModel = visible;
	      this.bipedDeadmau5Head.showModel = visible;
	   }
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	public ModelRenderer getRandomModelRenderer(Random randomIn) {
	      return this.modelRenderers.get(randomIn.nextInt(this.modelRenderers.size()));
	   }

	   public void accept(ModelRenderer p_accept_1_) {
	      if (this.modelRenderers == null) {
	         this.modelRenderers = Lists.newArrayList();
	      }

	      this.modelRenderers.add(p_accept_1_);
	   }
}

