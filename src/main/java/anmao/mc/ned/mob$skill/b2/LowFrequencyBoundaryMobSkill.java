package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;

public class LowFrequencyBoundaryMobSkill extends MobSkill {
    //低频结界
    public LowFrequencyBoundaryMobSkill(String id) {
        super(id);
    }
    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingHurtEvent hurtEvent){
            int d = dat.getInt("downDamage");
            hurtEvent.setAmount(hurtEvent.getAmount() * (d * 0.1F));
            if (d < 10) {
                dat.putInt("downDamage",d+1);
            }
        } else if (event instanceof LivingEvent.LivingTickEvent) {
            int tick = dat.getInt("tick");
            if (tick >40){
                dat.putInt("tick",0);

                int d = dat.getInt("downDamage");
                if (d > 0){
                    dat.putInt("downDamage",d-1);
                }
            }else {
                dat.putInt("tick",tick+1);
            }
        }
    }
}
