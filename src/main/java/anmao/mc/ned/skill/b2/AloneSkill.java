package anmao.mc.ned.skill.b2;

import anmao.mc.ned.attribute.NEDAttributes;
import anmao.mc.ned.lib.AttributeHelper;
import anmao.mc.ned.lib.EntityHelper;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.List;

public class AloneSkill extends Skill {
    //孤独
    //怪物越少，攻击力，移速，减伤提升越多
    public AloneSkill() {
        super("alone");
        //reg();
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        int tick = skillData.getInt("tick");
        if (tick > 200){
            skillData.putInt("tick",0);
            List<? extends LivingEntity> entities = EntityHelper.getLivingEntities(livingEntity);
            double s = 1d / entities.size();
            double a = 1 + s * 5;
            AttributeHelper.setTempAttribute(livingEntity, Attributes.ATTACK_DAMAGE,ATTRIBUTE_SKILL_ATTACK_DAMAGE,a, AttributeModifier.Operation.MULTIPLY_TOTAL,180);
            a = 1 + s * 2;
            AttributeHelper.setTempAttribute(livingEntity, Attributes.MOVEMENT_SPEED,ATTRIBUTE_SKILL_MOVE_SPEED,a, AttributeModifier.Operation.MULTIPLY_TOTAL,180);
            a = s * 50;
            AttributeHelper.setTempAttribute(livingEntity, NEDAttributes.hurtDown,ATTRIBUTE_SKILL_HURT_DOWN,a, AttributeModifier.Operation.ADDITION,180);
        }else {
            skillData.putInt("tick",tick + 1);
        }
    }
}
