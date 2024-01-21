package anmao.mc.ned.skill.b2;

import anmao.mc.ned.attribute.NEDAttributes;
import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.lib.AttributeHelper;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class ProliferateSkill extends Skill {
    //扩散
    //死亡后为附近同类生物提供攻击，移速，减伤
    public ProliferateSkill() {
        super("Proliferate");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_DEATH){
            List<? extends LivingEntity> entitys = eventData.getMainEntity().level().getEntities(EntityTypeTest.forClass(eventData.getMainEntity().getClass()), eventData.getMainEntity().getBoundingBox().inflate(10), Entity::isAlive);
            for (LivingEntity livingEntity :entitys){
                AttributeHelper.setAttribute(livingEntity, Attributes.ATTACK_DAMAGE,ATTRIBUTE_SKILL_ATTACK_DAMAGE,1.0D, AttributeModifier.Operation.ADDITION);
                AttributeHelper.setAttribute(livingEntity, Attributes.MOVEMENT_SPEED,ATTRIBUTE_SKILL_MOVE_SPEED,0.1D, AttributeModifier.Operation.ADDITION);
                AttributeHelper.setAttribute(livingEntity, NEDAttributes.hurtDown,ATTRIBUTE_SKILL_HURT_DOWN,5D, AttributeModifier.Operation.ADDITION);
            }
        }
    }
}
