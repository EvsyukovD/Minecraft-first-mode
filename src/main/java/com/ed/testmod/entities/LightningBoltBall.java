package com.ed.testmod.entities;

import com.ed.testmod.utils.ModMathHelper.Point;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LightningBoltBall extends ProjectileItemEntity {
	public LightningBoltBall(EntityType<? extends SnowballEntity> p_i50159_1_, World p_i50159_2_) {
	      super(p_i50159_1_, p_i50159_2_);
	   }

	   public LightningBoltBall(World worldIn, LivingEntity throwerIn) {
	      super(EntityType.SNOWBALL, throwerIn, worldIn);
	   }

	   public LightningBoltBall(World worldIn, double x, double y, double z) {
	      super(EntityType.SNOWBALL, x, y, z, worldIn);
	   }

	   protected Item getDefaultItem() {
	      return Items.SNOWBALL;
	   }

	   @OnlyIn(Dist.CLIENT)
	   private IParticleData makeParticle() {
	      ItemStack itemstack = this.func_213882_k();
	      return (IParticleData)(itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleData(ParticleTypes.ITEM, itemstack));
	   }

	   /**
	    * Handler for {@link World#setEntityState}
	    */
	   @OnlyIn(Dist.CLIENT)
	   public void handleStatusUpdate(byte id) {
	      if (id == 3) {
	         IParticleData iparticledata = this.makeParticle();

	         for(int i = 0; i < 8; ++i) {
	            this.world.addParticle(iparticledata, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);
	         }
	      }

	   }

	   /**
	    * Called when this EntityThrowable hits a block or entity.
	    */
	   protected void onImpact(RayTraceResult result) {
		   /*double p_213324_1_ = 100;
		   float p_213324_3_ = 1;
		   boolean p_213324_4_ = false;*/
		   World worldIn = this.world;
		    /*Vec3d vec3d = this.thrower.getEyePosition(p_213324_3_);
		     Vec3d vec3d1 = this.thrower.getLook(p_213324_3_);
		     Vec3d vec3d2 = vec3d.add(vec3d1.x * p_213324_1_, vec3d1.y * p_213324_1_, vec3d1.z * p_213324_1_);
		    RayTraceResult rts =  worldIn.rayTraceBlocks(new RayTraceContext(vec3d, vec3d2, RayTraceContext.BlockMode.OUTLINE, p_213324_4_ ? RayTraceContext.FluidMode.ANY : RayTraceContext.FluidMode.NONE,this.thrower));
		    BlockPos pos = new BlockPos(rts.getHitVec().getX(), rts.getHitVec().getY(), rts.getHitVec().getZ());*/
		   
		   LightningBoltEntity flash = new LightningBoltEntity(worldIn,this.getPosX(),this.getPosY(),this.getPosZ(),false);
		   
		   //worldIn.addEntity(flash);

		   worldIn.getServer().getWorld(worldIn.dimension.getType()).addLightningBolt(flash);
		  worldIn.playSound((PlayerEntity) null,this.getPosX(), this.getPosY(),this.getPosZ(), SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));
		   /*TNTEntity entitytnt = new TNTEntity(worldIn, this.getPosX(), this.getPosY(), this.getPosZ(), thrower);
	    	entitytnt.setFuse(60);
	    	worldIn.addEntity(entitytnt);*/
		   
		   
		   if (result.getType() == RayTraceResult.Type.ENTITY) {
		         Entity entity = ((EntityRayTraceResult)result).getEntity();
		         int i = entity instanceof BlazeEntity ? 3 : 0;
		         entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)i);
		      }
	          
		      if (!this.world.isRemote) {
		         this.world.setEntityState(this, (byte)3);
		         this.remove();
		      }

		   }
	   
}
