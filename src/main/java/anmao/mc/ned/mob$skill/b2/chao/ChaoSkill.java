package anmao.mc.ned.mob$skill.b2.chao;

import anmao.mc.ned.effect.EffectRegister;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ChaoSkill extends Skill {
    //混乱
    public ChaoSkill() {
        super("chao");
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        int t = skillData.getInt("tick");
        if (t > 200){
            skillData.putInt("tick",0);
            Player player = livingEntity.level().getNearestPlayer(livingEntity,15);
            if (player != null) {
                player.addEffect(new MobEffectInstance(EffectRegister.CHAO.get(),100,1));
            }
        }else {
            skillData.putInt("tick",t + 1);
        }
    }
}
