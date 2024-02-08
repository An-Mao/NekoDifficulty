package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

public class RainbowPositionSkill extends Skill {
    //彩虹立场
    public RainbowPositionSkill() {
        super("rainbow_position");
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        int t = skillData.getInt("tick");
        if (t > 10){
            skillData.putInt("tick",0);
            livingEntity.getActiveEffects().removeIf(mobEffectInstance -> mobEffectInstance.getEffect().isBeneficial());
        }else {
            skillData.putInt("tick",t + 1);
        }
    }
}
