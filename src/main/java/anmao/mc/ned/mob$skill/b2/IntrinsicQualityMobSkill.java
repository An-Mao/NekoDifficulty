package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;

public class IntrinsicQualityMobSkill extends MobSkill {
    //根性
    public IntrinsicQualityMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingAttackEvent attackEvent){
            if (dat.getBoolean("noDamage")){
                dat.putBoolean("noDamage",false);
                attackEvent.setCanceled(true);
            }
        }else if (event instanceof LivingEvent.LivingTickEvent){
            int t = dat.getInt("tick");
            if (t > 60){
                dat.putInt("tick",0);
                dat.putBoolean("noDamage",true);
            }else {
                dat.putInt("tick",t + 1);
            }
        }
    }
}
