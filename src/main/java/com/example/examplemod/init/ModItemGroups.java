package com.example.examplemod.init;

import net.minecraft.item.ItemGroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.function.Supplier;

import com.example.examplemod.Main;

public class ModItemGroups {

	public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroup(Main.MODID, () -> new ItemStack(ModItems.EXPERIMENTAL_INGOT));
	public static class ModItemGroup extends ItemGroup{
		private final Supplier<ItemStack> iconSupplier;
		public ModItemGroup(final String name, final Supplier<ItemStack> iconSupplier) {
			super(name);
			this.iconSupplier = iconSupplier;
		}

		@Override
		public ItemStack createIcon() {
			return iconSupplier.get();
		}
		
	} 
}
