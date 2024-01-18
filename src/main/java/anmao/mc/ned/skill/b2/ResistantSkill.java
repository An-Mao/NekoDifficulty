package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.skill.Skill;
import anmao.mc.ned.skill.SkillCDT;
import anmao.mc.ned.skill.Skills;
import net.minecraft.world.entity.LivingEntity;

public class ResistantSkill extends Skill {
    //厚皮
    //减免受到的伤害
    public ResistantSkill() {
        super("resistant");
        reg();
    }
    private void reg() {
        Skills.getInstance()._reg(this);
    }
    @Override
    public EventData Event(EventData eventData) {
        eventData.Amount = eventData.Amount / 2;
        eventData.updateType = SkillCDT.TYPE_AMOUNT;
        return eventData;
    }

    @Override
    public void Tick(LivingEntity livingEntity) {

    }
}
