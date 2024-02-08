package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

public class IntrinsicQualitySkill extends Skill {
    //根性
    public IntrinsicQualitySkill() {
        super("intrinsic_quality");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_ATTACK){
            if (skillData.getBoolean("noDamage")){
                skillData.putBoolean("noDamage",false);
                eventData.setCancel(true);
                eventData.setUpdateType(EVENT_UP_TYPE_CANCEL);
            }
        }
    }
    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        int t = skillData.getInt("tick");
        if (t > 60){
            skillData.putInt("tick",0);
            skillData.putBoolean("noDamage",true);
        }else {
            skillData.putInt("tick",t + 1);
        }
    }
}
