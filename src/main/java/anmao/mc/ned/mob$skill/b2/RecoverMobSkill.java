package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;

public class RecoverMobSkill extends MobSkill {
    //复苏
    public RecoverMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingEvent.LivingTickEvent tickEvent){
            int t = dat.getInt("tick");
            if (t > 100){
                dat.putInt("tick",0);
                tickEvent.getEntity().heal(2.0F);
            }else {
                dat.putInt("tick",t + 1);
            }
        }
    }
}
