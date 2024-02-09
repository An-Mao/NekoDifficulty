package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.Event;

public class CohesionMobSkill extends MobSkill {
    //凝聚
    public CohesionMobSkill(String id) {
        super(id);
    }
    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingDeathEvent deathEvent){
            LivingEntity livingEntity = deathEvent.getEntity();
            if (!dat.getBoolean("Cohesion")) {
                dat.putBoolean("Cohesion",true);
                PrimedTnt tntEntity = new PrimedTnt(livingEntity.level(), livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), livingEntity);
                tntEntity.setFuse(60);
                livingEntity.level().addFreshEntity(tntEntity);
            }
        }
    }
}
