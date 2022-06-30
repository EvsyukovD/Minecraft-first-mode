package com.ed.testmod.item;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.player.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

import com.ed.testmod.entities.LightningBoltBall;

import net.minecraft.entity.effect.LightningBoltEntity;
public class FlashStaff extends Item {
     public int N = 1;
	  public FlashStaff(Properties properties) {
		  super(properties);
	  }
	  @Override
	  public boolean hasEffect(ItemStack stack) {
		  
		  return true;
	  }
	  @Override
	  public ActionResult<ItemStack> onItemRightClick(World worldIn,PlayerEntity playerIn,Hand handIn){
		  //ItemStack item = playerIn.getHeldItem(handIn);
		   Vec3d aim = playerIn.getLookVec();
		   
		   /*double p_213324_1_ = 100;
		   float p_213324_3_ = 1;
		   boolean p_213324_4_ = false;
		   
		   Vec3d vec3d = playerIn.getEyePosition(p_213324_3_);
		     Vec3d vec3d1 = playerIn.getLook(p_213324_3_);
		     Vec3d vec3d2 = vec3d.add(vec3d1.x * p_213324_1_, vec3d1.y * p_213324_1_, vec3d1.z * p_213324_1_);
		    RayTraceResult rts =  worldIn.rayTraceBlocks(new RayTraceContext(vec3d, vec3d2, RayTraceContext.BlockMode.OUTLINE, p_213324_4_ ? RayTraceContext.FluidMode.ANY : RayTraceContext.FluidMode.NONE,playerIn));
		   BlockPos pos = new BlockPos(rts.getHitVec().getX(), rts.getHitVec().getY(), rts.getHitVec().getZ());
		   LightningBoltEntity flash = new LightningBoltEntity(worldIn,pos.getX(),pos.getY(),pos.getZ(),false);*/
		  // flash.setPosition(playerIn.getPosX() + aim.x * 1.5D,playerIn.getPosY() + aim.y * 1.5D, playerIn.getPosZ() + aim.z * 1.5D);
		  // flash.setMotion(aim.x * 3.00D, aim.z * 3.00D, aim.z * 3.00D);
		  // flash.setGlowing(true);
		  // playerIn.getCooldownTracker().setCooldown(this, 1);
          // flash.setGlowing(true);
		   // worldIn.addEntity(flash);
	      // item.setDamage(100000);
		 LightningBoltBall ball = new LightningBoltBall(worldIn,playerIn);
		 ball.setMotion(aim);
		 ball.setGlowing(true);
		  worldIn.playSound((PlayerEntity) null, playerIn.getPosX(), playerIn.getPosY(),playerIn.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (Item.random.nextFloat() * 0.4F + 0.8F));
         worldIn.addEntity(ball);
		  
		  return super.onItemRightClick(worldIn, playerIn, handIn);  
	  }
	  
	  
}
