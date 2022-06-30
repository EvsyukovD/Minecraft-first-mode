package com.ed.testmod.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups {
	 public static final ItemGroup MOD_ITEMS_ITEM_GROUP = new ModItemGroup("testmod_items", () -> new ItemStack( ModItems.EXPERIMENTAL_INGOT.get()));
}
