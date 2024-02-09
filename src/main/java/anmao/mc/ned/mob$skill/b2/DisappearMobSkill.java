package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;

public class DisappearMobSkill extends MobSkill {
    //消失
    //一段时间后消失
    public DisappearMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof EntityJoinLevelEvent joinLevelEvent){
            if (!dat.getBoolean("NoFirstJoin")) {
                dat.putBoolean("NoFirstJoin",true);
                dat.putLong("JoinTime", joinLevelEvent.getEntity().level().getGameTime());
            }
        } else if (event instanceof LivingEvent.LivingTickEvent tickEvent) {
            if (tickEvent.getEntity().level().getGameTime() - dat.getLong("JoinTime") > 1200){
                tickEvent.getEntity().remove(Entity.RemovalReason.KILLED);
            }
        }
    }
}
