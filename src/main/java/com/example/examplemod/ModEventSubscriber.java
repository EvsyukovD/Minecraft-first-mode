package com.example.examplemod;

import com.example.examplemod.init.ModBlocks;
import com.example.examplemod.init.ModItemGroups;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

/*@EventBusSubscriber(modid = Main.MODID,bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				setup(new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)), "experimental_ingot"),
                 setup(new BlockItem(ModBlocks.EXPERIMENTAL_ORE,new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)),"experimental_ore")
				);
	}
	@SubscribeEvent
	public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
				setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F,3.0F)),"experimental_ore")
				);
		
	}
	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(Main.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}
}*/
