package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

public class DarkPositionSkill extends Skill {
    //黑暗立场
    //减少受到的伤害，处于异常时可造成正常伤害
    public DarkPositionSkill() {
        super("DarkPosition");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_HURT){
            if (!hasDebuff(eventData.getMainEntity())){
                eventData.setAmount(eventData.getAmount() * 0.2F);
                eventData.setUpdateType(EVENT_UP_TYPE_AMOUNT);
            }
        }
    }
    public static boolean hasDebuff(LivingEntity entity) {
        return entity.getActiveEffects().stream().anyMatch(effect -> !effect.getEffect().isBeneficial());
    }
}
