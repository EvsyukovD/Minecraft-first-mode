package com.ed.testmod;

import com.ed.testmod.client.entity.render.CommandantEntityRender;
import com.ed.testmod.client.entity.render.GhostKnightEntityRender;
import com.ed.testmod.entities.ModEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import software.bernie.example.registry.EntityRegistry;
@EventBusSubscriber(modid = MainTM.MODID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientListener {
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GHOST_KNIGHT.get(),GhostKnightEntityRender::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.COMMANDANT.get(),CommandantEntityRender::new);
	}
}
