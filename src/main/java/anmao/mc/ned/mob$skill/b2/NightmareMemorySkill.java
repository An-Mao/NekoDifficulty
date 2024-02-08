package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;

public class NightmareMemorySkill extends Skill {
    //梦魇记忆
    public NightmareMemorySkill() {
        super("nightmare_memory");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_HURT){
            int scale = skillData.getInt(eventData.getDamageSource().type().toString());
            eventData.setAmount(eventData.getAmount() * (1 - scale * 0.05F));
            if (scale < 20) {
                skillData.putInt(eventData.getDamageSource().type().toString(), scale + 1);
            }
            eventData.setUpdateType(EVENT_UP_TYPE_AMOUNT);
        }
    }
}
