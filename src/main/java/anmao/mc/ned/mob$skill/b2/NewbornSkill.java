package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

public class NewbornSkill extends Skill {
    //新生
    public NewbornSkill() {
        super("newborn");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_FIRST_JOIN){
            skillData.putInt("invincibility",1000);
        }else if (eventData.getEventType() == EventType.EVENT_HURT){
            if (skillData.getInt("invincibility") >0){
                eventData.setCancel(true);
                eventData.setUpdateType(EVENT_UP_TYPE_CANCEL);
            }
        }
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        int i = skillData.getInt("invincibility");
        if (i > 0){
            skillData.putInt("invincibility",i-1);
        }
    }
}
