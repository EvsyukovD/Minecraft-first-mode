package com.ed.testmod.client.entity.render;

import java.util.Random;

import com.ed.testmod.MainTM;
import com.ed.testmod.entities.GhostKnightEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.ed.testmod.client.entity.model.GhostKnightEntityModel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class GhostKnightEntityRender extends GeoEntityRenderer<GhostKnightEntity>{
	//private static final ItemStack sword = new ItemStack(Items.IRON_SWORD);
	//private static final ItemStack shotgun = new ItemStack(DoomItems.SG.get());
	private IRenderTypeBuffer rtb;
	private ResourceLocation whTexture;
//protected static final ResourceLocation TEXTURE = new ResourceLocation(MainTM.MODID,"textures/model/entity/ghost_knight.png");
  public GhostKnightEntityRender(EntityRendererManager rendermanagerIn) {
	super(rendermanagerIn,new GhostKnightEntityModel());
	this.shadowSize = 0.7f;
	
	//this.addLayer(new GeoLayerRenderer<GhostKnightEntity>(GhostKnightEntity.class));
}
  public ItemStack getMainHand() {
	  return this.mainHand;
	  
  }
  public ItemStack getOffHand() {
	  return this.offHand;
	  
  }
  public ItemStack getChestPlate() {
	  return this.chestplate;
	  
  }
  public ItemStack getHelmet() {
	  return this.helmet;
	  
  }
  public ItemStack getLeggings() {
	  return this.leggings;
	  
  }
  public ItemStack getBoots() {
	  return this.boots;
	  
  }
  
  @Override
	public RenderType getRenderType(GhostKnightEntity animatable, float partialTicks, MatrixStack stack,
			IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
			ResourceLocation textureLocation) {
		return RenderType.getEntityTranslucent(getTextureLocation(animatable));
	}
  
  @Override
	public void renderEarly(GhostKnightEntity animatable, MatrixStack stackIn, float ticks,
			IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float partialTicks) {
		this.rtb = renderTypeBuffer;
		this.whTexture = this.getTextureLocation(animatable);
		super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn,
				red, green, blue, partialTicks);
	}

	@Override
	public void renderRecursively(GeoBone bone, MatrixStack stack, IVertexBuilder bufferIn, int packedLightIn,
			int packedOverlayIn, float red, float green, float blue, float alpha) {
		/*if (bone.getName().equals("bipedLeftArm_1")) {
			stack.push();
			stack.rotate(Vector3f.XP.rotationDegrees(-40));
			stack.rotate(Vector3f.YP.rotationDegrees(0));
			stack.rotate(Vector3f.ZP.rotationDegrees(-5));
			stack.translate(0.30D, 0.90D, 0.3D);
			stack.scale(1.0f, 1.0f, 1.0f);
			Minecraft.getInstance().getItemRenderer().renderItem(axe, TransformType.THIRD_PERSON_RIGHT_HAND,
					packedLightIn, packedOverlayIn, stack, this.rtb);
			stack.pop();
			bufferIn = rtb.getBuffer(RenderType.getEntityTranslucent(whTexture));
		}*/
		/*if (bone.getName().equals("Righthand")) {
			stack.push();
			stack.rotate(Vector3f.XP.rotationDegrees(-40));
			stack.rotate(Vector3f.YP.rotationDegrees(0));
			stack.rotate(Vector3f.ZP.rotationDegrees(-5));
			stack.translate(0.30D, 0.90D, 0.3D);
			stack.scale(1.0f, 1.0f, 1.0f);
			Minecraft.getInstance().getItemRenderer().renderItem(sword, TransformType.THIRD_PERSON_RIGHT_HAND,
					packedLightIn, packedOverlayIn, stack, this.rtb);
			stack.pop();
			bufferIn = rtb.getBuffer(RenderType.getEntityTranslucent(whTexture));
		}*/
		super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
  
}
