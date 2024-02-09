package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;

public class NewbornMobSkill extends MobSkill {
    //新生
    public NewbornMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof EntityJoinLevelEvent){
            if (!dat.getBoolean("isNotFirstJoin")) {
                dat.putBoolean("isNotFirstJoin",true);
                dat.putInt("invincibility", 1000);
            }
        } else if (event instanceof LivingHurtEvent h) {
            if (dat.getInt("invincibility") >0){
                h.setCanceled(true);
            }
        } else if (event instanceof LivingEvent.LivingTickEvent) {
            int i = dat.getInt("invincibility");
            if (i > 0){
                dat.putInt("invincibility",i-1);
            }
        }
    }
}
