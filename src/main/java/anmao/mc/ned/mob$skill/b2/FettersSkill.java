package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.effect.EffectRegister;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class FettersSkill extends Skill {
    //禁锢
    public FettersSkill() {
        super("fetters");
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        int tick = skillData.getInt("tick");
        if (tick > 200){
            skillData.putInt("tick",0);
            Player player = livingEntity.level().getNearestPlayer(livingEntity,15);
            if (player != null) {
                player.getPersistentData().putDouble("FettersX",player.getX());
                player.getPersistentData().putDouble("FettersY",player.getY());
                player.getPersistentData().putDouble("FettersZ",player.getZ());
                player.addEffect(new MobEffectInstance(EffectRegister.FETTERS.get(),100,1));
            }
        }else {
            skillData.putInt("tick",tick+1);
        }
    }
}
