package com.ed.testmod;

import net.minecraft.entity.EntityType;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;

import com.ed.testmod.block.ModBlocks;
import com.ed.testmod.entities.ModEntityTypes;
import com.ed.testmod.features.ModFeatures;
import com.ed.testmod.objects.items.ModSpawnEggItem;
import com.ed.testmod.world.gen.StructureGen;
import com.ed.testmod.client.entity.render.GhostKnightEntityRender;

import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;

@EventBusSubscriber(modid = MainTM.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventGenSubscriber {
	public static int veinSize = 20;//кол-во блоков в 1 жиле
	public static int veinCount = 15;//кол-во жил в 1 чанке
	public static int heightMin = 0;//минимальная высота
	public static int heightBase = 0;//смещения (обычно 0)
	public static int heightMax = 120;//максимальная высота
	 
	@SubscribeEvent
	public static void onInitBiomesGen(FMLCommonSetupEvent event) {
	    for (Biome biome : ForgeRegistries.BIOMES) {
	        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.EXPERIMENTAL_ORE.get().getDefaultState(), veinSize)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(veinCount, heightMin, heightBase, heightMax)))
	        );
	        //biome.addStructure(ModFeatures.BARRACK.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	        biome.addStructure(ModFeatures.FORT.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			//biome.addFeature(Decoration.SURFACE_STRUCTURES, ModFeatures.BARRACK.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
			biome.addFeature(Decoration.SURFACE_STRUCTURES, ModFeatures.FORT.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	    }
	}
}
