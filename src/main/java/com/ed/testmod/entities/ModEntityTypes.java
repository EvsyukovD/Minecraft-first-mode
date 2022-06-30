package com.ed.testmod.entities;

import com.ed.testmod.MainTM;
import com.ed.testmod.entities.GhostKnightEntity;
import com.ed.testmod.entities.CommandantEntity;
import com.ed.testmod.entities.ShooterKnightEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES,MainTM.MODID);
    /*public static final RegistryObject<EntityType<GhostKnightEntity>> GHOST_KNIGHT = ENTITY_TYPES.register("ghost_knight",
    		            () -> EntityType.Builder.<GhostKnightEntity>create(GhostKnightEntity::new,EntityClassification.MONSTER)
    		                      .size(EntityType.ZOMBIE.getWidth(),EntityType.ZOMBIE.getHeight())
    		                                             .build(new ResourceLocation(MainTM.MODID,"ghost_knight").toString()));*/
	
	
	/*public static final RegistryObject<EntityType<ShooterKnightEntity>> SHOOTER_KNIGHT = buildMonsterEntity(
			ShooterKnightEntity::new,ShooterKnightEntity.class,
			EntityType.ZOMBIE.getWidth(),EntityType.ZOMBIE.getHeight(),"shooter_knight");*/
	
	
	public static final RegistryObject<EntityType<GhostKnightEntity>> GHOST_KNIGHT = buildMonsterEntity(
			GhostKnightEntity::new,GhostKnightEntity.class,
			EntityType.ZOMBIE.getWidth(),EntityType.ZOMBIE.getHeight(),"ghost_knight");
	
	
    public static final RegistryObject<EntityType<CommandantEntity>> COMMANDANT = buildMonsterEntity(
    		CommandantEntity::new,CommandantEntity.class,
    		EntityType.ZOMBIE.getWidth(),EntityType.ZOMBIE.getHeight(),"commandant");
	
    
	public static <T extends Entity> RegistryObject<EntityType<T>> buildMonsterEntity(
			EntityType.IFactory<T> entity,
			Class<T> entityClass, float width, float height,String name) {
		//String name = entityClass.getSimpleName().toLowerCase();
		return ENTITY_TYPES.register(name,
				() -> EntityType.Builder.create(entity, EntityClassification.MONSTER).size(width, height).build(new ResourceLocation(MainTM.MODID,name).toString()));
	}
}
