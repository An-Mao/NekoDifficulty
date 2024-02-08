package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import anmao.mc.ned.skill.SkillCDT;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

public class ResistantSkill extends Skill {
    //厚皮
    //减免受到的伤害
    public ResistantSkill() {
        super("resistant");
    }
    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_DAMAGE) {
            eventData.setAmount(eventData.getAmount() /2);
            eventData.setUpdateType(SkillCDT.EVENT_UP_TYPE_AMOUNT);
        }
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {

    }
}
