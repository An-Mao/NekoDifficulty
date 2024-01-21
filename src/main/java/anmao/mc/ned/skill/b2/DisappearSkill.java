package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class DisappearSkill extends Skill {
    //消失
    //一段时间后消失
    public DisappearSkill() {
        super("Disappear");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_FIRST_JOIN){
            skillData.putLong("JoinTime",eventData.getMainEntity().level().getGameTime());
        }
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        if (livingEntity.level().getGameTime() - skillData.getLong("JoinTime") > 1200){
            livingEntity.remove(Entity.RemovalReason.KILLED);
        }
    }
}
