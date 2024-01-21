package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class CounterstrikeSkill extends Skill {
    //反刺
    public CounterstrikeSkill() {
        super("Counterstrike");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_DAMAGE && eventData.getSecondaryEntity() instanceof ServerPlayer serverPlayer){
            serverPlayer.hurt(eventData.getMainEntity().damageSources().fellOutOfWorld(),eventData.getAmount() * 0.3F);
        }
    }
}
