package anmao.mc.ned.skill.b2;

import anmao.mc.ned.attribute.NEDAttributes;
import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.lib.AttributeHelper;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class CurseSkill extends Skill {
    //诅咒
    //降低移速，攻击力，减伤
    public CurseSkill() {
        super("curse");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_DEATH && eventData.getSecondaryEntity() instanceof ServerPlayer serverPlayer){
            AttributeHelper.setTempAttribute(serverPlayer,Attributes.MOVEMENT_SPEED,ATTRIBUTE_SKILL_MOVE_SPEED,-0.1D,AttributeModifier.Operation.ADDITION,200);
            AttributeHelper.setTempAttribute(serverPlayer,Attributes.ATTACK_DAMAGE,ATTRIBUTE_SKILL_ATTACK_DAMAGE,-0.5D,AttributeModifier.Operation.MULTIPLY_TOTAL,200);
            AttributeHelper.setTempAttribute(serverPlayer, NEDAttributes.hurtUp,ATTRIBUTE_SKILL_HURT_UP,5D,AttributeModifier.Operation.ADDITION,200);
        }
    }
}
