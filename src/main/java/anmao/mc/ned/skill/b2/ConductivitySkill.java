package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;

public class ConductivitySkill extends Skill {
    //导电体质
    public ConductivitySkill() {
        super("conductivity");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_DAMAGE && eventData.getSecondaryEntity() instanceof ServerPlayer serverPlayer){
            //受到攻击召唤闪电
            LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(serverPlayer.level());
            if (lightningBolt != null) {
                lightningBolt.moveTo(serverPlayer.getX(),serverPlayer.getY(),serverPlayer.getZ());
                serverPlayer.level().addFreshEntity(lightningBolt);
            }
        }
    }
}
