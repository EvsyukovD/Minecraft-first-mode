package com.ed.testmod.entities;

import javax.annotation.Nullable;

import com.ed.testmod.client.entity.render.GhostKnightEntityRender;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
public class GhostKnightEntity extends MonsterEntity implements IAnimatable{

	public int ghost_knight_timer;
	private AnimationFactory factory = new AnimationFactory(this);
    
    
    private <E extends IAnimatable> PlayState movepredicate(AnimationEvent<E> event) {
		if (event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.ghost_knight.walk", true));
		}  
		
	    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.ghost_knight.idle", true));
		return PlayState.CONTINUE;
	}
    private <E extends IAnimatable> PlayState attackpredicate(AnimationEvent<E> event) {
		if (this.isAggressive()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.ghost_knight.attack", true));
			return PlayState.CONTINUE;
		}
		return PlayState.STOP;
	}
	public GhostKnightEntity(EntityType<? extends GhostKnightEntity> type, World worldIn) {
		super(type, worldIn);
		this.ignoreFrustumCheck = true;
		
	}
	@Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 0, true, false, null));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, VillagerEntity.class, 0, true, false, null));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.35D, false));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.20D, 50));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookAtGoal(this, VillagerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookAtGoal(this, IronGolemEntity.class, 6.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	}
	
	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		//this.setHeldItem(Hand.MAIN_HAND, new ItemStack(Items.IRON_SWORD));
		/*int random = this.rand.nextInt(90);
    	if(random >= 0 && random <= 30) {
        	this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
        	this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(Items.CHAINMAIL_CHESTPLATE));
        	this.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(Items.CHAINMAIL_LEGGINGS));
        	this.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(Items.CHAINMAIL_BOOTS));
        	this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
    	}
    	if(random > 30 && random <= 60) {
    		this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(Items.IRON_HELMET));
    		this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
    		this.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(Items.IRON_LEGGINGS));
    		this.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(Items.IRON_BOOTS));
    		this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
    	}
    	if(random > 60 ) {
    		this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(Items.GOLDEN_HELMET));
    		this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(Items.GOLDEN_CHESTPLATE));
    		this.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(Items.IRON_LEGGINGS));
    		this.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(Items.IRON_BOOTS));
    		this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
    		this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.SHIELD));
    	}
    	ItemStack stack = this.getHeldItemMainhand();
    	if(stack.getItem() instanceof SwordItem) {
    		stack.setDamage(stack.getDamage() * (1 + this.rand.nextInt(3)));
    	}*/
		
		return spawnDataIn;
	}
	@Override
	public void livingTick() {
		if(this.world.isRemote) {
			this.ghost_knight_timer = Math.max(0, this.ghost_knight_timer - 1);
			
		}
		super.livingTick();
	}
	@Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0F);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0F);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0f);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.9F);
        //this.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1.0f);
    }
	@Override
	public void onStruckByLightning(LightningBoltEntity lightningBolt) {
		super.onStruckByLightning(lightningBolt);
		
		this.setGlowing(true);
	}
	/*@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_WITHER_AMBIENT;
	}*/
	@Override 
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_WITHER_HURT;
	}

	/*@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 10) {
			this.ghost_knight_timer = 40;
		} else {
			super.handleStatusUpdate(id);
		}

	}*/
	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController(this,"movecontroller",0,this::movepredicate));
		data.addAnimationController(new AnimationController(this,"attackcontroller",0,this::attackpredicate));
	}
	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}
	
}
