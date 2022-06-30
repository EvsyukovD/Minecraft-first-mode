package com.ed.testmod.client.entity.render;

import com.ed.testmod.MainTM;
import com.ed.testmod.client.entity.model.CommandantEntityModel;
import com.ed.testmod.entities.CommandantEntity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
public class CommandantEntityRender extends BipedRenderer<CommandantEntity, CommandantEntityModel<CommandantEntity>>{
  
	protected static final ResourceLocation TEXTURE = new ResourceLocation(MainTM.MODID,"textures/models/entity/commandant.png");
	
	@SuppressWarnings("rawtypes")
	public CommandantEntityRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new CommandantEntityModel<CommandantEntity>(0.5F,false), 0.5F);
		this.addLayer(new HeldItemLayer<>(this));
		this.addLayer(new BipedArmorLayer<>(this, new BipedModel(0.5F), new BipedModel(1.0F)));
	}
	
	@Override
	public ResourceLocation getEntityTexture(CommandantEntity entity) {
		return TEXTURE;
	}
	
}
