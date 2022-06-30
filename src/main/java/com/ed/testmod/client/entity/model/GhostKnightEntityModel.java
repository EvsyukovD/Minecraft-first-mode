package com.ed.testmod.client.entity.model;

import com.ed.testmod.MainTM;
import com.ed.testmod.entities.GhostKnightEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class GhostKnightEntityModel extends AnimatedGeoModel<GhostKnightEntity>{
	public GhostKnightEntityModel() {
	}
	@Override
	public ResourceLocation getAnimationFileLocation(GhostKnightEntity animatable) {
		return new ResourceLocation(MainTM.MODID, "animations/ghost_knight.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(GhostKnightEntity entity) {
		return new ResourceLocation(MainTM.MODID, "geo/ghost_knight.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(GhostKnightEntity entity) {
		return new ResourceLocation(MainTM.MODID, "textures/models/entity/ghost_knight.png");

	}

}
