package anmao.mc.ned.skill.b2;

import anmao.mc.ned.lib.AttributeHelper;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class LearnSkill extends Skill {
    //学习
    public LearnSkill() {
        super("Learn");
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        int t = skillData.getInt("tick");
        if (t > 600){
            skillData.putInt("tick",0);
            int lvl = skillData.getInt("lvl");
            if (lvl < 10){
                skillData.putInt("lvl",lvl +1);
                AttributeHelper.setAttribute(livingEntity, Attributes.ATTACK_DAMAGE,ATTRIBUTE_SKILL_ATTACK_DAMAGE,1.0D, AttributeModifier.Operation.ADDITION);
                AttributeHelper.setAttribute(livingEntity, Attributes.MOVEMENT_SPEED,ATTRIBUTE_SKILL_MOVE_SPEED,0.1D, AttributeModifier.Operation.ADDITION);
            }
        }else {
            skillData.putInt("tick",t + 1);
        }
    }
}
