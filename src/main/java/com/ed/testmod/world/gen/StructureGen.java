package com.ed.testmod.world.gen;
import com.ed.testmod.features.ModFeatures;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
public class StructureGen {

public static void generateStruct() {
	for(Biome biome : ForgeRegistries.BIOMES) {
		
		biome.addStructure(ModFeatures.BARRACK.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		biome.addStructure(ModFeatures.FORT.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		biome.addFeature(Decoration.SURFACE_STRUCTURES, ModFeatures.BARRACK.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		biome.addFeature(Decoration.SURFACE_STRUCTURES, ModFeatures.FORT.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));

	}
	
}
}
