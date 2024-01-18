package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventTypes;
import anmao.mc.ned.skill.Skill;
import anmao.mc.ned.skill.Skills;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class AloneSkill extends Skill {
    //孤独
    //怪物越少，攻击力，移速，减伤提升越多
    public AloneSkill() {
        super("alone");
        reg();
    }

    private void reg() {
        Skills.getInstance()._reg(this);
    }
    @Override
    public EventData Event(EventData eventData) {
        return eventData;
    }

}
