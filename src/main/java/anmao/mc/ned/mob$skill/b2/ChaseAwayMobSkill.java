package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;

public class ChaseAwayMobSkill extends MobSkill {
    //驱赶
    //将角色推开
    public ChaseAwayMobSkill(String id) {
        super(id);
    }
    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingEvent.LivingTickEvent livingTickEvent){
            int t = dat.getInt("tick");
            if (t > 20) {
                dat.putInt("tick",0);
                double pushStrength = 1.0;
                LivingEntity livingEntity = livingTickEvent.getEntity();
                Player player = livingEntity.level().getNearestPlayer(livingEntity, 10.0);
                if (player != null && !livingEntity.noPhysics && !player.noPhysics) {
                    double dX = player.getX() - livingEntity.getX();
                    double dZ = player.getZ() - livingEntity.getZ();
                    double pushX = dX * pushStrength;
                    double pushZ = dZ * pushStrength;
                }
            }else {
                dat.putInt("tick",t+1);
            }
        }
    }
}
