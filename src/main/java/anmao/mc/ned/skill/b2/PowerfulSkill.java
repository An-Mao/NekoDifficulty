package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.lib.AttributeHelper;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class PowerfulSkill extends Skill {
    //强力
    //攻击力大幅提升
    public PowerfulSkill() {
        super("powerful");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_FIRST_SPAWN){
            if (eventData.getMainEntity() != null){
                AttributeHelper.setAttribute(eventData.getMainEntity(), Attributes.ATTACK_DAMAGE,ATTRIBUTE_SKILL_ATTACK_DAMAGE,3D, AttributeModifier.Operation.ADDITION);
            }
        }
    }
}
