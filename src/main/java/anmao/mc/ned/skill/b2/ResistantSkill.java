package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventTypes;
import anmao.mc.ned.skill.Skill;
import anmao.mc.ned.skill.SkillCDT;
import anmao.mc.ned.skill.Skills;
import net.minecraft.world.entity.LivingEntity;

import java.util.ServiceLoader;

public class ResistantSkill extends Skill {
    //厚皮
    //减免受到的伤害
    public ResistantSkill() {
        super("resistant");
        //reg();
    }
    private void reg() {
        Skills.getInstance()._reg(this);
    }
    @Override
    public void Event(EventData eventData) {
        if (eventData.getEventType() == EventTypes.EVENT_DAMAGE) {
            eventData.setAmount(eventData.getAmount() /2);
            eventData.setUpdateType(SkillCDT.TYPE_AMOUNT);
        }
    }

    @Override
    public void Tick(LivingEntity livingEntity) {

    }
}
