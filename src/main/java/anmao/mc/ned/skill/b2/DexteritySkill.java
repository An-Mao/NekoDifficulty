package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.lib._Math;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

import java.util.Random;

public class DexteritySkill extends Skill {
    //灵巧
    public DexteritySkill() {
        super("Dexterity");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_ATTACK){
            if (skillData.getBoolean("withstand")) {
                skillData.putBoolean("withstand",false);
                Random random = new Random();
                double offsetX = random.nextDouble() * 20 - 10;
                double offsetY = 0;
                double offsetZ = random.nextDouble() * 20 - 10;

                double teleportX = eventData.getMainEntity().getX() + offsetX;
                double teleportY = eventData.getMainEntity().getY() + offsetY;
                double teleportZ = eventData.getMainEntity().getZ() + offsetZ;
                eventData.getMainEntity().teleportTo(teleportX, teleportY, teleportZ);
                eventData.setCancel(true);
                eventData.setUpdateType(EVENT_UP_TYPE_CANCEL);
            }
        }
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        int t = skillData.getInt("tick");
        if (t > 140){
            skillData.putInt("tick",0);
            skillData.putBoolean("withstand",true);
        }else {
            skillData.putInt("tick",t+1);
        }
    }
}
