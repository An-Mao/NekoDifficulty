package anmao.mc.ned.datatype;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public class EventData {
    public int eventType = 0;
    public LivingEntity mainEntity = null;
    public LivingEntity secondaryEntity = null;
    public DamageSource damageSource = null;
    public float Amount = 0;
    public boolean cancel = false;
    /**
     * return
     * 0 cancel event
     * 1 only amount
     * 2 ...
     */
    public int updateType = -1;
}
