package anmao.mc.ned.datatype;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class EventData {
    private int eventType;
    private LivingEntity mainEntity;
    private Entity secondaryEntity;
    private DamageSource damageSource;
    private float amount;
    private boolean cancel;
    /**
     * return
     * 0 cancel event
     * 1 only amount
     * 2 ...
     */
    private int updateType;
    public EventData(int eventType,LivingEntity mainEntity){
        this(eventType,mainEntity,null,null,0);
    }
    public EventData(int eventType,LivingEntity mainEntity,Entity secondaryEntity){
        this(eventType,mainEntity,secondaryEntity,null,0);
    }
    public EventData(int eventType,LivingEntity mainEntity,Entity secondaryEntity,DamageSource damageSource){
        this(eventType,mainEntity,secondaryEntity,damageSource,0);
    }
    public EventData(int eventType,LivingEntity mainEntity,Entity secondaryEntity,DamageSource damageSource,float amount ){
        this.eventType = eventType;
        this.mainEntity = mainEntity;
        this.secondaryEntity = secondaryEntity;
        this.damageSource = damageSource;
        this.amount = amount;
        this.cancel = false;
        this.updateType = -1;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public LivingEntity getMainEntity() {
        return mainEntity;
    }

    public void setMainEntity(LivingEntity mainEntity) {
        this.mainEntity = mainEntity;
    }

    public Entity getSecondaryEntity() {
        return secondaryEntity;
    }

    public void setSecondaryEntity(LivingEntity secondaryEntity) {
        this.secondaryEntity = secondaryEntity;
    }

    public DamageSource getDamageSource() {
        return damageSource;
    }

    public void setDamageSource(DamageSource damageSource) {
        this.damageSource = damageSource;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getUpdateType() {
        return updateType;
    }

    public void setUpdateType(int updateType) {
        this.updateType = updateType;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }
    public boolean isCancel() {
        return cancel;
    }
}
