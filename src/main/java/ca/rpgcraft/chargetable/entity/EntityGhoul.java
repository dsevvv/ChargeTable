package ca.rpgcraft.chargetable.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityGhoul extends EntityMob {



    private static final DataParameter<Boolean> AGGRESSIVE = EntityDataManager.createKey(EntityGhoul.class, DataSerializers.BOOLEAN);

    public EntityGhoul(World worldIn) {
        super(worldIn);
        //adjusting size to match the model better
        setSize(0.80F, 1.7F);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.getDataManager().register(AGGRESSIVE, Boolean.valueOf(false));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.05D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
    }

    @Override
    protected void initEntityAI() {
        clearTasks();
        tasks.addTask(3, new EntityAIGhoulLeap(this, 0.55F));
        tasks.addTask(4, new EntityAIGhoulMelee(this, 0.25D, false));
        tasks.addTask(8, new EntityAIGhoulIdle(this));

        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
    }

    protected void clearTasks(){
        tasks.taskEntries.clear();
        targetTasks.taskEntries.clear();
    }

    public boolean isAggressive(){
        return this.dataManager.get(AGGRESSIVE);
    }

    public void setAggressive(boolean aggressive){
        this.dataManager.set(AGGRESSIVE, aggressive);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        //hover effect
        this.motionY += MathHelper.cos(this.ticksExisted * 0.3F) * 0.1F;
    }
}
