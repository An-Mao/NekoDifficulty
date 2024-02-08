package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;

public class RebirthSkill extends Skill {
    //重生
    public RebirthSkill() {
        super("rebirth");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_DEATH){
            if (!skillData.getBoolean("isRebirth")){
                skillData.putBoolean("isRebirth",true);
                eventData.getMainEntity().setHealth(eventData.getMainEntity().getMaxHealth());
                eventData.setCancel(true);
                eventData.setUpdateType(EVENT_UP_TYPE_CANCEL);
            }
        }
    }
}
