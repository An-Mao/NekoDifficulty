package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;

public class IroncladSkill extends Skill {
    //铁壁
    public IroncladSkill() {
        super("ironclad");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_HURT){
            float damage = Math.min(eventData.getAmount(),10);
            eventData.setAmount(damage);
            eventData.setUpdateType(EVENT_UP_TYPE_AMOUNT);
        }
    }
}
