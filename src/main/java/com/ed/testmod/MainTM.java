package com.ed.testmod;

import java.rmi.registry.RegistryHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ed.testmod.block.ModBlocks;
import com.ed.testmod.client.entity.render.GhostKnightEntityRender;
import com.ed.testmod.entities.ModEntityTypes;
import com.ed.testmod.features.ModFeatures;
import com.ed.testmod.item.ModItems;
import com.ed.testmod.objects.items.ModSpawnEggItem;
import com.ed.testmod.world.gen.StructureGen;
import com.ed.testmod.features.structures.BarrackPieces;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.network.GeckoLibNetwork;

@Mod(MainTM.MODID)
@Mod.EventBusSubscriber(modid = MainTM.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MainTM {
public static final String MODID = "testmod";
public static final Logger LOGGER = LogManager.getLogger();
public static boolean DISABLE_IN_DEV = false;
public MainTM() {
	GeckoLib.initialize();
	GeckoLibNetwork.initialize();
	//FMLJavaModLoadingContext.get().getModEventBus().addListener(ModEventGenSubscriber::onInitBiomesGen);
	//FMLJavaModLoadingContext.get().getModEventBus().addListener(ModEventGenSubscriber::registerRenderers);
	//FMLJavaModLoadingContext.get().getModEventBus().addListener(ModEventGenSubscriber::onRegisterEntities);
    IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    ModBlocks.BLOCKS.register(bus);
    ModItems.ITEMS.register(bus);
    ModFeatures.FEATURES.register(bus);
    ModEntityTypes.ENTITY_TYPES.register(bus);
   
    MinecraftForge.EVENT_BUS.register(this);
   
}

@SubscribeEvent(priority = EventPriority.LOWEST)
public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
	ModSpawnEggItem.initSpawnEggs();
}
}
