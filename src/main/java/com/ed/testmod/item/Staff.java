package com.ed.testmod.item;

import java.util.Random;

import com.ed.testmod.entities.MeteorBallEntity;
import com.ed.testmod.utils.KeyboardHelper;
import com.ed.testmod.utils.ModMathHelper;
import com.ed.testmod.utils.ModMathHelper.*;

import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraft.entity.player.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.effect.LightningBoltEntity;
public class Staff extends Item {
	public int N = 1;
  public Staff(Properties properties) {
	  super(properties);
  }
  @Override
  public boolean hasEffect(ItemStack stack) {
	  
	  return true;
  }
  @Override
  public ActionResultType onItemUse(ItemUseContext context) {
	  PlayerEntity playerIn = context.getPlayer();
	  playerIn.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE,600,0));
	  return super.onItemUse(context);
  }
@SuppressWarnings("deprecation")
@Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn,PlayerEntity playerIn,Hand handIn){
	  ItemStack item = playerIn.getHeldItem(handIn);
	   int n = 5;
	   double AccFactor = 2.1;
	   Random rand = new Random();
	   Vec3d aim = playerIn.getLookVec();
	   FireballEntity fireball = new FireballEntity(worldIn,playerIn,1,1,1);
	   fireball.setPosition(playerIn.prevPosX + aim.x, playerIn.prevPosY + aim.y,playerIn.prevPosZ + aim.z);
	   fireball.accelerationX = aim.x * 0.1;
	   fireball.accelerationY = aim.y * 0.1;
	   fireball.accelerationZ = aim.z * 0.1;
	   if(KeyboardHelper.isHoldingCtrl() & KeyboardHelper.isHoldingR()) {
		  MeteorBallEntity meteor = new MeteorBallEntity(worldIn,playerIn);
		  meteor.setMotion(aim);
		  meteor.setGlowing(true);
		  worldIn.playSound((PlayerEntity) null, playerIn.getPosX(), playerIn.getPosY(),playerIn.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (Item.random.nextFloat() * 0.4F + 0.8F));
          worldIn.addEntity(meteor);
	   } else {
       worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (Item.random.nextFloat() * 0.4F + 0.8F));
	   worldIn.addEntity(fireball);
	   fireball.explosionPower = 3;
	   }
       item.setDamage(40);
	return super.onItemRightClick(worldIn, playerIn, handIn);  
  }
}
