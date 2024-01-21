package anmao.mc.ned.skill.b2;

import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.util.Collection;

public class RainbowPositionSkill extends Skill {
    //彩虹立场
    public RainbowPositionSkill() {
        super("RainbowPosition");
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        int t = skillData.getInt("tick");
        if (t > 10){
            skillData.putInt("tick",0);
            Iterable<MobEffectInstance> effects = livingEntity.getActiveEffects();
            effects.forEach(mobEffectInstance -> {
                if (mobEffectInstance.getEffect().isBeneficial()){
                    livingEntity.removeEffect(mobEffectInstance.getEffect());
                }
            });
        }else {
            skillData.putInt("tick",t + 1);
        }
    }
}
