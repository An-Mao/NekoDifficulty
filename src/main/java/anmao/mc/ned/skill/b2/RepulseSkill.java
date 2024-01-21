package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.lib.AttributeHelper;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class RepulseSkill extends Skill {
    //击退
    //增加击退距离
    public RepulseSkill() {
        super("Repulse");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_FIRST_SPAWN){
            AttributeHelper.setAttribute(eventData.getMainEntity(), Attributes.KNOCKBACK_RESISTANCE,ATTRIBUTE_SKILL_KNOCK_BACK,2D, AttributeModifier.Operation.ADDITION);
        }
    }
}
