package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;

public class IgniteSkill extends Skill {
    //引燃
    public IgniteSkill() {
        super("ignite");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_DEATH){
            double x = eventData.getMainEntity().getX();
            double y = eventData.getMainEntity().getY();
            double z = eventData.getMainEntity().getZ();
            for (int a = 0;a < 4;a++){
                Explosion explosion = getExplosion(eventData.getMainEntity(), x + a, y, z + a);
                Explosion explosion1 = getExplosion(eventData.getMainEntity(),x + a, y, z - a);
                Explosion explosion2 = getExplosion(eventData.getMainEntity(),x - a, y, z + a);
                Explosion explosion3 = getExplosion(eventData.getMainEntity(),x - a, y, z - a);
                explosion.explode();
                explosion1.explode();
                explosion2.explode();
                explosion3.explode();
            }
        }
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        int tick = skillData.getInt("tick");
        if (tick > 100){
            int t2 = skillData.getInt("tick2");
            if (t2 > 20) {
                skillData.putInt("tick2",0);
                double x = livingEntity.getX();
                double y = livingEntity.getY();
                double z = livingEntity.getZ();
                int a = skillData.getInt("a");
                Explosion explosion = getExplosion(livingEntity,x + a, y, z + a);
                Explosion explosion1 = getExplosion(livingEntity,x + a, y, z - a);
                Explosion explosion2 = getExplosion(livingEntity,x - a, y, z + a);
                Explosion explosion3 = getExplosion(livingEntity,x - a, y, z - a);
                explosion.explode();
                explosion1.explode();
                explosion2.explode();
                explosion3.explode();
                if (a >= 4){
                    skillData.putInt("tick", 0);
                }else {
                    skillData.putInt("a",a + 1);
                }
            }else {
                skillData.putInt("tick2",t2 +1);
            }
        }else {
            skillData.putInt("tick",tick +1);
        }
    }
    public Explosion getExplosion(LivingEntity livingEntity,double x,double y, double z) {
        return new Explosion(livingEntity.level(), livingEntity, x , y, z , 1.0f, false, Explosion.BlockInteraction.KEEP);
    }
}
