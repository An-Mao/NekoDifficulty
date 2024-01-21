package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class InfectionSkill extends Skill {
    //感染
    public InfectionSkill() {
        super("Infection");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_DAMAGE && eventData.getSecondaryEntity() instanceof ServerPlayer serverPlayer){
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.POISON,200,1));
        }
    }
}
