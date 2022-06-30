package com.ed.testmod.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class ShooterKnightEntity extends MonsterEntity{
  
	public ShooterKnightEntity(EntityType<ShooterKnightEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	/*public ShooterKnightEntity(World worldIn) {
		this(ModEntityTypes.SHOOTER_KNIGHT.get(), worldIn);
	}*/
}
