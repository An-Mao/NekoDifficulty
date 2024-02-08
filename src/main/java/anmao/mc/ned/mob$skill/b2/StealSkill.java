package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class StealSkill extends Skill {
    //窃取
    public StealSkill() {
        super("steal");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_DAMAGE){
            if (eventData.getSecondaryEntity() instanceof ServerPlayer serverPlayer){
                ItemStack itemStack = serverPlayer.getMainHandItem();
                if (itemStack != ItemStack.EMPTY && itemStack.isDamageableItem()){
                    itemStack.setDamageValue((int) (itemStack.getDamageValue()+itemStack.getMaxDamage()*0.1));
                }
            }
        }
    }
}

