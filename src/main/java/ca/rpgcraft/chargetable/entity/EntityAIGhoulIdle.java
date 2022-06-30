package ca.rpgcraft.chargetable.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAILookIdle;

public class EntityAIGhoulIdle extends EntityAILookIdle {
    private final EntityLiving idleEntity;
    private int idleTime;

    public EntityAIGhoulIdle(EntityLiving entitylivingIn)
    {
        super(entitylivingIn);
        this.idleEntity = entitylivingIn;
    }

    public boolean shouldExecute()
    {
        return this.idleEntity.getRNG().nextFloat() < 0.02F;
    }

    public boolean shouldContinueExecuting()
    {
        return this.idleTime >= 0;
    }

    public void startExecuting()
    {
        this.idleTime = 20 * 15;
    }

    public void updateTask()
    {
        --this.idleTime;

        if(this.idleTime <= 0){
            EntityGhoul ghoul = (EntityGhoul) idleEntity;
            ghoul.setAggressive(false);
        }
    }
}
