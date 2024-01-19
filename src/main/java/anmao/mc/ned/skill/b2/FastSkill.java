package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventTypes;
import anmao.mc.ned.skill.Skill;
import anmao.mc.ned.skill.Skills;
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


    private void reg() {
        Skills.getInstance()._reg(this);
    }

    @Override
    public void Event(EventData eventData) {
        if (eventData.getEventType() == EventTypes.EVENT_SPAWN){
            if (eventData.getMainEntity() != null){
                AttributeInstance att = eventData.getMainEntity().getAttribute(Attributes.MOVEMENT_SPEED);
                if (att != null) {
                    att.addPermanentModifier(new AttributeModifier("skill.ned.fast.speed",0.5D, AttributeModifier.Operation.ADDITION));
                }
            }
        }
    }
}
