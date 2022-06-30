package com.ed.testmod.entities;

import java.util.List;
import java.util.Random;

import com.ed.testmod.utils.ModMathHelper.Circle;
import com.ed.testmod.utils.ModMathHelper.Point;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.projectile.FireballEntity;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
public class MeteorBallEntity extends ProjectileItemEntity{
	public Point finishPoint = Point.ZERO;
	public MeteorBallEntity(EntityType<? extends SnowballEntity> p_i50159_1_, World p_i50159_2_) {
	      super(p_i50159_1_, p_i50159_2_);
	   }

	   public MeteorBallEntity(World worldIn, LivingEntity throwerIn) {
	      super(EntityType.SNOWBALL, throwerIn, worldIn);
	   }

	   public MeteorBallEntity(World worldIn, double x, double y, double z) {
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
		  this.finishPoint = new Point(this.getPosX(),this.getPosY(),this.getPosZ());
		  int n = 6;
		  double R = 5;
		  double H = 6 * R;
		  double AccFactor = 0.1;
		   FireballEntity[] fireballs = new FireballEntity[n];
		  Circle Area = new Circle(this.finishPoint,R);
		  FireballEntity superFireBall = new FireballEntity(this.world,Area.CenterX,Area.CenterY + H + 10,Area.CenterZ,1,1,1);;
	       for(int i = 0; i < n;i++) {
	    	   Point target = Area.getPointInCircleWithDev(n,i);
		       fireballs[i] = new FireballEntity(this.world,target.x,target.y + H,target.z,1,1,1);
		       Vec3d r = fireballs[i].getPositionVec();
		       Vec3d targVec = new Vec3d(target.x - r.x,target.y - r.y,target.z - r.z);
		       targVec = targVec.normalize().scale(AccFactor);
		       fireballs[i].accelerationX = targVec.x;
		       fireballs[i].accelerationY = targVec.y;
		       fireballs[i].accelerationZ = targVec.z;
		       fireballs[i].explosionPower = 3;
		       this.world.playSound((PlayerEntity)null, target.x,target.y + H,target.z, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));
		       this.world.addEntity(fireballs[i]);
	    }
	     Vec3d SuperTargetVec = Point.getVec(new Point(Area.CenterX,Area.CenterY + H + 10,Area.CenterZ), Area.getCenter());
		  SuperTargetVec = SuperTargetVec.normalize().scale(AccFactor);
		  superFireBall.accelerationX = SuperTargetVec.x;
		  superFireBall.accelerationY = SuperTargetVec.y;
		  superFireBall.accelerationZ = SuperTargetVec.z;
		  superFireBall.explosionPower = 5;
		  superFireBall.setGlowing(true);
	       this.world.playSound((PlayerEntity)null, superFireBall.getPosX(),superFireBall.getPosY(),superFireBall.getPosZ(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));
	       this.world.addEntity(superFireBall);
	       
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
	   public boolean isInGround() {
		   return this.inGround;
	   }
	   
	   public Point getFinishPoint() {
		   return this.finishPoint;
	   }
	   
	/*
	 * private int xTile;
    private int yTile;
    private int zTile;
    private Block inTile;
    protected boolean inGround;
    public int throwableShake;
    
    protected LivingEntity thrower;
    private String throwerName;
    private int ticksInGround;
    private int ticksInAir;
    public Entity ignoreEntity;
    private int ignoreTime;
    @SuppressWarnings("unsused")
	public MeteorBallEntity(EntityType<MeteorBallEntity> type,World worldIn)
    {
        super(type,worldIn);
    }

    public MeteorBallEntity(World worldIn, LivingEntity throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public MeteorBallEntity(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    
    public void onUpdate()
    {   
        this.lastTickPosX = this.getPosX();
        this.lastTickPosY = this.getPosY();
        this.lastTickPosZ = this.getPosZ();
      
        super.updatePortal();

        if (this.throwableShake > 0)
        {
            --this.throwableShake;
        }

        if (this.inGround)
        {
            if (this.world.getBlockState(new BlockPos(this.xTile, this.yTile, this.zTile)).getBlock() == this.inTile)
            {
                ++this.ticksInGround;

                if (this.ticksInGround == 1200)
                {
                    this.remove();
                }

                return;
            }

            this.inGround = false;
            this.setMotion(this.rand.nextFloat() * 0.2F,this.rand.nextFloat() * 0.2F,this.rand.nextFloat() * 0.2F);
            this.ticksInGround = 0;
            this.ticksInAir = 0;
        }
        else
        {
            ++this.ticksInAir;
        }

        Vec3d vec3d = this.getPositionVec();
        Vec3d vec3d1 = this.getPositionVec().add(getMotion());//new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        BlockRayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d, vec3d1);
        vec3d = this.getPositionVec();//new Vec3d(this.posX, this.posY, this.posZ);
        vec3d1 = this.getPositionVec().add(getMotion());//new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (raytraceresult != null)
        {
            vec3d1 = new Vec3d(raytraceresult.getHitVec().x, raytraceresult.getHitVec().y, raytraceresult.getHitVec().z);
        }

        Entity entity = null;
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getBoundingBox().expand(this.getMotion()).grow(1.0D));
        double d0 = 0.0D;
        boolean flag = false;

        for (int i = 0; i < list.size(); ++i)
        {
            Entity entity1 = list.get(i);

            if (entity1.canBeCollidedWith())
            {
                if (entity1 == this.ignoreEntity)
                {
                    flag = true;
                }
                else if (this.thrower != null && this.ticksExisted < 2 && this.ignoreEntity == null)
                {
                    this.ignoreEntity = entity1;
                    flag = true;
                }
                else
                {
                    flag = false;
                    AxisAlignedBB axisalignedbb = entity1.getBoundingBox().grow(0.30000001192092896D);
                    BlockRayTraceResult raytraceresult1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);

                    if (raytraceresult1 != null)
                    {
                        double d1 = vec3d.squareDistanceTo(raytraceresult1.getHitVec());

                        if (d1 < d0 || d0 == 0.0D)
                        {
                            entity = entity1;
                            d0 = d1;
                        }
                    }
                }
            }
        }

        if (this.ignoreEntity != null)
        {
            if (flag)
            {
                this.ignoreTime = 2;
            }
            else if (this.ignoreTime-- <= 0)
            {
                this.ignoreEntity = null;
            }
        }

        if (entity != null)
        {
            raytraceresult = new RayTraceResult(entity);
        }

        if (raytraceresult != null)
        {
            if (raytraceresult.getType() == RayTraceResult.Type.BLOCK && this.world.getBlockState(raytraceresult.getBlockPos()).getBlock() == Blocks.PORTAL)
            {
                this.setPortal(raytraceresult.getBlockPos());
            }
            else if (!net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult))
            {
                this.onImpact(raytraceresult);
            }
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));

        for (this.rotationPitch = (float)(MathHelper.atan2(this.motionY, (double)f) * (180D / Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
        {
            ;
        }

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
        {
            this.prevRotationPitch += 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw < -180.0F)
        {
            this.prevRotationYaw -= 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
        {
            this.prevRotationYaw += 360.0F;
        }

        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
        float f1 = 0.99F;
        float f2 = this.getGravityVelocity();

        if (this.isInWater())
        {
            for (int j = 0; j < 4; ++j)
            {
                float f3 = 0.25F;
                this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ);
            }

            f1 = 0.8F;
        }

        this.motionX *= (double)f1;
        this.motionY *= (double)f1;
        this.motionZ *= (double)f1;

        if (!this.hasNoGravity())
        {
            this.motionY -= (double)f2;
        }

        this.setPosition(this.posX, this.posY, this.posZ);
        this.world.spawnEntity(new EntityLightningBolt(this.world, this.posX, this.posY, this.posZ, false));
    }
    
    @Override
    protected void onImpact(RayTraceResult result) 
    {
    	EntityTNTPrimed entitytnt = new EntityTNTPrimed(this.world, this.posX, this.posY, this.posZ, thrower);
    	entitytnt.setFuse(60);
    	this.world.spawnEntity(entitytnt);
    	super.onImpact(result);
    }
}*/
}
