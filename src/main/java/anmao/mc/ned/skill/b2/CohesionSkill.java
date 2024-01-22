package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.vehicle.MinecartTNT;

public class CohesionSkill extends Skill {
    //凝聚
    public CohesionSkill() {
        super("cohesion");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_DEATH){
            LivingEntity livingEntity = eventData.getMainEntity();
            if (!skillData.getBoolean("Cohesion")) {
                skillData.putBoolean("Cohesion",true);
                PrimedTnt tntEntity = new PrimedTnt(livingEntity.level(), livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), livingEntity);
                tntEntity.setFuse(60);
                livingEntity.level().addFreshEntity(tntEntity);
            }
        }
    }
}
