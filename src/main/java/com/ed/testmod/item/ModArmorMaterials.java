package com.ed.testmod.item;
import com.ed.testmod.MainTM;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.LazyValue;
import java.util.function.Supplier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
public enum ModArmorMaterials implements IArmorMaterial {
INGOT(MainTM.MODID + ":ingot",10,new int[] {11,12,13,14},420,SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,6.9F,() -> {
	return Ingredient.fromItems(ModItems.EXPERIMENTAL_INGOT.get());
});
	
	private static final int[] MAX_DAMAGE_ARRAY = new int[] { 16, 16, 16, 16 };
	private final String name;
	private final int maxDamageFactor;
	private final int[] damageReductionAmountArray;
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final LazyValue<Ingredient> repairMaterial;

	private ModArmorMaterials(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountIn,
			int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn,
			Supplier<Ingredient> repairMaterialIn) {
		this.name = nameIn;
		this.maxDamageFactor = maxDamageFactorIn;
		this.damageReductionAmountArray = damageReductionAmountIn;
		this.enchantability = enchantabilityIn;
		this.soundEvent = soundEventIn;
		this.toughness = toughnessIn;
		this.repairMaterial = new LazyValue<>(repairMaterialIn);
	}
	
	@Override
	public int getDurability(EquipmentSlotType slotIn) {
		return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slotIn) {
		return this.damageReductionAmountArray[slotIn.getIndex()];
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public SoundEvent getSoundEvent() {
		return this.soundEvent;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return this.repairMaterial.getValue();
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}
}
