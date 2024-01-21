package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;

public class FavoriteSkill extends Skill {
    //喜欢
    public FavoriteSkill() {
        super("Favorite");
    }
    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_ATTACK) {
            if (eventData.getSecondaryEntity() != null && eventData.getMainEntity().distanceTo(eventData.getSecondaryEntity()) > 7) {
                eventData.setAmount(eventData.getAmount() * 0.2F);
                eventData.setUpdateType(EVENT_UP_TYPE_AMOUNT);
            }
        }
    }
}
