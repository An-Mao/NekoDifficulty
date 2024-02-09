package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;

public class IgniteMobSkill extends MobSkill {
    //引燃
    public IgniteMobSkill(String id) {
        super(id);
    }
    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingDeathEvent deathEvent){
            LivingEntity livingEntity = deathEvent.getEntity();
            double x = livingEntity.getX();
            double y = livingEntity.getY();
            double z = livingEntity.getZ();
            for (int a = 0;a < 4;a++){
                summonExplosion(livingEntity,x,y,z,a);
            }
        } else if (event instanceof LivingEvent.LivingTickEvent t) {
            int tick = dat.getInt("tick");
            if (tick > 100){
                int t2 = dat.getInt("tick2");
                if (t2 > 20) {
                    dat.putInt("tick2",0);
                    LivingEntity livingEntity = t.getEntity();
                    int a = dat.getInt("a");
                    summonExplosion(livingEntity,a);
                    if (a >= 4){
                        dat.putInt("tick", 0);
                    }else {
                        dat.putInt("a",a + 1);
                    }
                }else {
                    dat.putInt("tick2",t2 +1);
                }
            }else {
                dat.putInt("tick",tick +1);
            }
        }
    }
    private void summonExplosion(LivingEntity livingEntity , int a){
        double x = livingEntity.getX();
        double y = livingEntity.getY();
        double z = livingEntity.getZ();
        summonExplosion(livingEntity,x,y,z,a);
    }
    private void summonExplosion(LivingEntity livingEntity, double x,double y,double z, int a){
        Explosion explosion = getExplosion(livingEntity,x + a, y, z + a);
        Explosion explosion1 = getExplosion(livingEntity,x + a, y, z - a);
        Explosion explosion2 = getExplosion(livingEntity,x - a, y, z + a);
        Explosion explosion3 = getExplosion(livingEntity,x - a, y, z - a);
        explosion.explode();
        explosion1.explode();
        explosion2.explode();
        explosion3.explode();
    }
    public Explosion getExplosion(LivingEntity livingEntity,double x,double y, double z) {
        return new Explosion(livingEntity.level(), livingEntity, x , y, z , 1.0f, false, Explosion.BlockInteraction.KEEP);
    }
}
