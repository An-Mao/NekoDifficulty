package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.Event;

public class RebirthMobSkill extends MobSkill {
    //重生
    public RebirthMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingDeathEvent deathEvent){
            if (!dat.getBoolean("isRebirth")){
                dat.putBoolean("isRebirth",true);
                deathEvent.getEntity().setHealth(deathEvent.getEntity().getMaxHealth());
                deathEvent.setCanceled(true);
            }
        }
    }
}
