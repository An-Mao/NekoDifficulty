package anmao.mc.ned.skill.b2;

import anmao.mc.ned.attribute.NEDAttributes;
import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.lib.AttributeHelper;
import anmao.mc.ned.lib.EntityHelper;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.List;

public class SymbiosisSkill extends Skill {
    //共生
    public SymbiosisSkill() {
        super("symbiosis");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_FIRST_SPAWN){
            List<? extends LivingEntity> entities = EntityHelper.getLivingEntities(eventData.getMainEntity());
            double s = entities.size();
            double a = 1 + s * 0.3;
            AttributeHelper.setAttribute(eventData.getMainEntity(), Attributes.ATTACK_DAMAGE,ATTRIBUTE_SKILL_ATTACK_DAMAGE,a, AttributeModifier.Operation.MULTIPLY_TOTAL);
            a = s * 10;
            AttributeHelper.setAttribute(eventData.getMainEntity(), NEDAttributes.hurtDown,ATTRIBUTE_SKILL_HURT_DOWN,a, AttributeModifier.Operation.ADDITION);
        }
    }
}
