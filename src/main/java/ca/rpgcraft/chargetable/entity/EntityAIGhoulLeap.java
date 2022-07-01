package ca.rpgcraft.chargetable.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.MathHelper;

public class EntityAIGhoulLeap extends EntityAILeapAtTarget {

    EntityLiving leaper;
    EntityLivingBase leapTarget;
    float leapMotionY;

    public EntityAIGhoulLeap(EntityLiving leaper, float leapMotionY) {
        super(leaper, leapMotionY);
        this.leaper = leaper;
        this.leapMotionY = leapMotionY;
    }

    @Override
    public boolean shouldExecute()
    {
        this.leapTarget = this.leaper.getAttackTarget();

        if (this.leapTarget == null)
        {
            return false;
        }
        else
        {
            double d0 = this.leaper.getDistanceSq(this.leapTarget);

            if (d0 >= 6.0D && d0 <= 128.0D)
            {
                if (!this.leaper.onGround)
                {
                    return false;
                }
                else
                {
                    return this.leaper.getRNG().nextInt(5) == 0;
                }
            }
            else
            {
                return false;
            }
        }
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return !this.leaper.onGround;
    }

    @Override
    public void startExecuting()
    {
        EntityGhoul ghoul = (EntityGhoul) this.leaper;
        ghoul.setAggressive(true);
        this.leaper.getNavigator().tryMoveToEntityLiving(leapTarget, leapMotionY);

        double d0 = this.leapTarget.posX - this.leaper.posX;
        double d1 = this.leapTarget.posZ - this.leaper.posZ;
        float f = MathHelper.sqrt(d0 * d0 + d1 * d1);

        if ((double)f >= 1.0E-4D)
        {
            this.leaper.motionX += d0 / (double)f * 0.5D * 0.800000011920929D + this.leaper.motionX * 0.80000000298023224D + .1;
            this.leaper.motionZ += d1 / (double)f * 0.5D * 0.800000011920929D + this.leaper.motionZ * 0.80000000298023224D + .1;
        }

        this.leaper.motionY = (double)this.leapMotionY;
        this.leaper.playSound(SoundEvents.ENTITY_ENDERDRAGON_GROWL, 0.5F, 4.0F);
    }
}
