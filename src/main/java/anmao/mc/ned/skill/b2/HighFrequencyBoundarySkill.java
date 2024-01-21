package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

public class HighFrequencyBoundarySkill extends Skill {
    //高频结界
    public HighFrequencyBoundarySkill() {
        super("HighFrequencyBoundary");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_HURT){
            int d = skillData.getInt("downDamage");
            eventData.setAmount(eventData.getAmount() * (1 - d * 0.1F));
            if (d < 10) {
                skillData.putInt("downDamage",d+1);
            }
            eventData.setUpdateType(EVENT_UP_TYPE_AMOUNT);
        }
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        int tick = skillData.getInt("tick");
        if (tick >40){
            skillData.putInt("tick",0);

            int d = skillData.getInt("downDamage");
            if (d > 0){
                skillData.putInt("downDamage",d-1);
            }
        }else {
            skillData.putInt("tick",tick+1);
        }
    }
}
