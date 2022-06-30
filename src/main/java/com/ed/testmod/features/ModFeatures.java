package com.ed.testmod.features;

import java.util.Locale;

import com.ed.testmod.MainTM;
import com.ed.testmod.features.structures.BarrackStructure;
import com.ed.testmod.features.structures.BarrackPieces;
import com.ed.testmod.features.structures.FortStructure;
import com.ed.testmod.features.structures.FortPieces;


import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class ModFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, MainTM.MODID);
    public static final RegistryObject<BarrackStructure> BARRACK = FEATURES.register("barrack",() -> new BarrackStructure(NoFeatureConfig::deserialize));
   public static IStructurePieceType BARRACK_PIECE = BarrackPieces.Piece::new;
   public static final RegistryObject<FortStructure> FORT = FEATURES.register("fort",() -> new FortStructure(NoFeatureConfig::deserialize));
   public static IStructurePieceType FORT_PIECE = FortPieces.Piece::new;

    
   @SubscribeEvent
	public static void registerStructurePieces(RegistryEvent.Register<Feature<?>> event) {
		Registry.register(Registry.STRUCTURE_PIECE, "BARRACK".toLowerCase(Locale.ROOT), BARRACK_PIECE);
		Registry.register(Registry.STRUCTURE_PIECE, "FORT".toLowerCase(Locale.ROOT), FORT_PIECE);

	}

}

