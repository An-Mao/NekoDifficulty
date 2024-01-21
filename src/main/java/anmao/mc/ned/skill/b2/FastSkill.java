package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.lib.AttributeHelper;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class FastSkill extends Skill {
    //快速
    //移速大幅上升
    public FastSkill() {
        super("fast");
        //reg();
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_FIRST_SPAWN){
            if (eventData.getMainEntity() != null){
                AttributeHelper.setAttribute(eventData.getMainEntity(),Attributes.MOVEMENT_SPEED,ATTRIBUTE_SKILL_MOVE_SPEED,0.5D, AttributeModifier.Operation.ADDITION);
            }
        }
    }
}
