package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;

public class SummonMobSkill extends MobSkill {
    //召唤
    public SummonMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingEvent.LivingTickEvent tickEvent){
            int t = dat.getInt("tick");
            if (t > 1000){
                dat.putInt("tick",0);
            }else {
                dat.putInt("tick",t + 1);
            }
        }
    }
}
