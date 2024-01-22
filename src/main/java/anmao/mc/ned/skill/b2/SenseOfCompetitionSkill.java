package anmao.mc.ned.skill.b2;

import anmao.mc.ned.lib.AttributeHelper;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class SenseOfCompetitionSkill extends Skill {
    //竞争意识
    public SenseOfCompetitionSkill() {
        super("SenseOfCompetition");
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        int t = skillData.getInt("tick");
        if (t > 300){
            skillData.putInt("tick",0);
            Player player = livingEntity.level().getNearestPlayer(livingEntity,10);
            if (player != null) {
                AttributeHelper.setTempAttribute(livingEntity, Attributes.MOVEMENT_SPEED, ATTRIBUTE_SKILL_MOVE_SPEED, player.getAttribute(Attributes.MOVEMENT_SPEED).getValue() / 2, AttributeModifier.Operation.ADDITION, 300);
            }
        }else {
            skillData.putInt("tick", t + 1);
        }
    }
}
