package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;

import java.util.Random;

public class DexterityMobSkill extends MobSkill {
    //灵巧
    public DexterityMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingAttackEvent attackEvent){
            if (dat.getBoolean("withstand")) {
                dat.putBoolean("withstand",false);
                Random random = new Random();
                double offsetX = random.nextDouble() * 20 - 10;
                double offsetY = 0;
                double offsetZ = random.nextDouble() * 20 - 10;

                double teleportX = attackEvent.getEntity().getX() + offsetX;
                double teleportY = attackEvent.getEntity().getY() + offsetY;
                double teleportZ = attackEvent.getEntity().getZ() + offsetZ;
                attackEvent.getEntity().teleportTo(teleportX, teleportY, teleportZ);
                attackEvent.setCanceled(true);
            }
        }else if (event instanceof LivingEvent.LivingTickEvent){
            int t = dat.getInt("tick");
            if (t > 140){
                dat.putInt("tick",0);
                dat.putBoolean("withstand",true);
            }else {
                dat.putInt("tick",t+1);
            }
        }
    }
}
