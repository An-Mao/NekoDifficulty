package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

public class SummonSkill extends Skill {
    //召唤
    public SummonSkill() {
        super("summon");
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        int t = skillData.getInt("tick");
        if (t > 1000){
            skillData.putInt("tick",0);
        }else {
            skillData.putInt("tick",t + 1);
        }
    }
}
