package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import anmao.mc.ned.skill.SkillCDT;
import net.minecraft.nbt.CompoundTag;

public class CrystallographySkill extends Skill {
    //结晶
    public CrystallographySkill() {
        super("Crystallography");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_DAMAGE){
            if (eventData.getAmount() != 1.0F){
                eventData.setAmount(1.0F);
                eventData.setUpdateType(SkillCDT.EVENT_UP_TYPE_AMOUNT);
            }
        }
    }
}
