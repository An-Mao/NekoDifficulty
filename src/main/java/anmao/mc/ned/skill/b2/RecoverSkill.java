package anmao.mc.ned.skill.b2;

import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

public class RecoverSkill extends Skill {
    //复苏
    public RecoverSkill() {
        super("recover");
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        int t = skillData.getInt("tick");
        if (t > 100){
            skillData.putInt("tick",0);
            livingEntity.heal(2.0F);
        }else {
            skillData.putInt("tick",t + 1);
        }
    }
}
