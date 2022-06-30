package com.ed.testmod.entities;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.BossInfo;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.ProtectionEnchantment;

public class CommandantEntity extends MonsterEntity {

	private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(),
			BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(false).setCreateFog(false);
			
	public CommandantEntity(EntityType<CommandantEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	public CommandantEntity(World worldIn) {
		this(ModEntityTypes.COMMANDANT.get(), worldIn);
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	@Override
	public void onStruckByLightning(LightningBoltEntity lightningBolt) {
		super.onStruckByLightning(lightningBolt);
		
		this.setGlowing(true);
	}
	@Override
	protected void registerGoals() {
		//this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		//this.goalSelector.addGoal(2, new LookAtGoal(this, AbstractVillagerEntity.class, 6.0F));
		//this.goalSelector.addGoal(2, new LookAtGoal(this, IronGolemEntity.class, 6.0F));
		
		/*this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(10, new HurtByTargetGoal(this).setCallsForHelp(GhostKnightEntity.class));
		this.goalSelector.addGoal(10, new MeleeAttackGoal(this, 0.35D, false));
		this.targetSelector.addGoal(20, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 0, true, false, null));
		this.targetSelector.addGoal(24, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, 0, true, false, null));
		this.targetSelector.addGoal(25, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, 0, true, false, null));*/
        
		//this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        //this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 0.20D, 50));
		//this.applyEntityAI();
		
		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 0, true, false, null));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, VillagerEntity.class, 0, true, false, null));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.35D, false));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setCallsForHelp(GhostKnightEntity.class));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.20D, 50));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookAtGoal(this, VillagerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookAtGoal(this, IronGolemEntity.class, 6.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	}

	/*protected void applyEntityAI() {
		this.goalSelector.addGoal(10, new MeleeAttackGoal(this, 0.35D, false));
		this.targetSelector.addGoal(20, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 0, true, false, null));
		this.targetSelector.addGoal(24, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, 0, true, false, null));
		this.targetSelector.addGoal(25, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, 0, true, false, null));
	}*/

	@Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0F);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0F);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0f);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.9F);
    }
	
	@Override 
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_WITHER_HURT;
	}
	
	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			@Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		this.setCustomName(new TranslationTextComponent("Komendant"));
		ItemStack head = new ItemStack(Items.DIAMOND_HELMET);
		ItemStack chestplate = new ItemStack(Items.DIAMOND_CHESTPLATE);
		ItemStack legs = new ItemStack(Items.DIAMOND_LEGGINGS);
		ItemStack feet = new ItemStack(Items.DIAMOND_BOOTS);
		ItemStack mainhand = new ItemStack(Items.DIAMOND_SWORD);
		head.addEnchantment(Enchantments.PROTECTION, 4);
		chestplate.addEnchantment(Enchantments.PROTECTION, 4);
		legs.addEnchantment(Enchantments.PROTECTION, 4);
		feet.addEnchantment(Enchantments.PROTECTION, 4);
		mainhand.addEnchantment(Enchantments.POWER, Enchantments.POWER.getMinLevel());
		
		this.setItemStackToSlot(EquipmentSlotType.HEAD, head);
		this.setItemStackToSlot(EquipmentSlotType.CHEST,chestplate);
		this.setItemStackToSlot(EquipmentSlotType.LEGS,legs);
		this.setItemStackToSlot(EquipmentSlotType.FEET,feet);
		this.setItemStackToSlot(EquipmentSlotType.MAINHAND,mainhand);
		return spawnDataIn;
	}
	
	@Override
	public void addTrackingPlayer(ServerPlayerEntity player) {
		super.addTrackingPlayer(player);//startSeenByPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);;//.stopSeenByPlayer(player);
		this.bossInfo.removePlayer(player);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);//.readAdditionalSaveData(compound);
		if (this.hasCustomName()) {
			this.bossInfo.setName(this.getDisplayName());
		}
	}
	
	public ServerBossInfo getBossInfo() {
		return this.bossInfo;
	}

	@Override
	public void setCustomName(@Nullable ITextComponent name) {
		super.setCustomName(name);
		this.bossInfo.setName(this.getDisplayName());
	}
	public boolean isNonBoss() {
		return false;
	}
	@Override
	protected void updateAITasks() {
		super.updateAITasks();
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
	}
}
