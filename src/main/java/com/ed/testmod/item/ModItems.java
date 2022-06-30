package com.ed.testmod.item;

import com.ed.testmod.item.ModArmorMaterials;
import com.ed.testmod.MainTM;
import com.ed.testmod.item.Staff;
import com.ed.testmod.objects.items.ModSpawnEggItem;
import com.ed.testmod.item.FlashStaff;
import com.ed.testmod.block.ModBlocks;
import com.ed.testmod.entities.ModEntityTypes;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ArmorItem;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MainTM.MODID);
	//Some items
	public static final RegistryObject<Item> EXPERIMENTAL_INGOT  = ITEMS.register("experimental_ingot", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
	public static final RegistryObject<Item> EXPERIMENTAL_ORE = ITEMS.register("experimental_ore", () -> new BlockItem(ModBlocks.EXPERIMENTAL_ORE.get(), new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
	public static final RegistryObject<Item> METEOR_STAFF = ITEMS.register("meteor_staff",() -> new Staff(new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
	public static final RegistryObject<Item> FLASH_STAFF = ITEMS.register("flash_staff",() -> new FlashStaff(new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));

	//Tools
	public static final RegistryObject<PickaxeItem> METEOR_PICKAXE = ITEMS.register("meteor_pickaxe",() -> new PickaxeItem(ModItemTier.INGOT,7,1000,new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
	public static final RegistryObject<AxeItem> METEOR_AXE = ITEMS.register("meteor_axe",() -> new AxeItem(ModItemTier.INGOT,7,1000,new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
	public static final RegistryObject<SwordItem> METEOR_SWORD = ITEMS.register("meteor_sword",() -> new SwordItem(ModItemTier.INGOT,13,1000,new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
	public static final RegistryObject<HoeItem> METEOR_HOE = ITEMS.register("meteor_hoe",() -> new HoeItem(ModItemTier.INGOT,7,new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));

	//Armor
	public static final RegistryObject<ArmorItem> METEOR_HELMET = ITEMS.register("meteor_helmet",() -> new ArmorItem(ModArmorMaterials.INGOT,EquipmentSlotType.HEAD,new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
	public static final RegistryObject<ArmorItem> METEOR_CHESTPLATE = ITEMS.register("meteor_chestplate",() -> new ArmorItem(ModArmorMaterials.INGOT,EquipmentSlotType.CHEST,new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
	public static final RegistryObject<ArmorItem> METEOR_LEGGINS = ITEMS.register("meteor_leggings",() -> new ArmorItem(ModArmorMaterials.INGOT,EquipmentSlotType.LEGS,new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
	public static final RegistryObject<ArmorItem> METEOR_BOOTS = ITEMS.register("meteor_boots",() -> new ArmorItem(ModArmorMaterials.INGOT,EquipmentSlotType.FEET,new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
    //Eggs
	public static final RegistryObject<ModSpawnEggItem> GHOST_KNIGHT_SPAWN_EGG = ITEMS.register("ghost_knight_spawn_egg",()-> new ModSpawnEggItem(ModEntityTypes.GHOST_KNIGHT, 0xFFFF00, 0x16777215,
			new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));//.maxStackSize(16)));
	
	public static final RegistryObject<SpawnEggItem> IRON_GOLEM_SPAWN_EGG = ITEMS.register("iron_golem_spawn_egg",()-> new SpawnEggItem(EntityType.IRON_GOLEM, 0xFFFFFF, 0x16777104,
			new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
	
	public static final RegistryObject<ModSpawnEggItem> COMMANDANT_SPAWN_EGG = ITEMS.register("commandant_spawn_egg",()-> new ModSpawnEggItem(ModEntityTypes.COMMANDANT, 0xFFABC0, 0x16755568,
			new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
}
