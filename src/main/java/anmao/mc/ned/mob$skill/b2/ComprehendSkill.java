package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ComprehendSkill extends Skill {
    //领悟
    //生命低于某个值时，攻击力和移速大幅提升
    public ComprehendSkill() {
        super("comprehend");
    }
    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_DAMAGE){
            if (eventData.getMainEntity().getHealth() - eventData.getAmount() < eventData.getMainEntity().getMaxHealth() * 0.5){
                AttributeInstance att = eventData.getMainEntity().getAttribute(Attributes.ATTACK_DAMAGE);
                if (att != null) {
                    att.addPermanentModifier(new AttributeModifier("skill.ned.attack.damage", 2D, AttributeModifier.Operation.MULTIPLY_TOTAL));
                }
                att = eventData.getMainEntity().getAttribute(Attributes.MOVEMENT_SPEED);
                if (att != null) {
                    att.addPermanentModifier(new AttributeModifier("skill.ned.move.speed", 2D, AttributeModifier.Operation.MULTIPLY_TOTAL));
                }
            }
        }
    }
}
