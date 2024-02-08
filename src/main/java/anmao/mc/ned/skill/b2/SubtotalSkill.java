package anmao.mc.ned.skill.b2;

import anmao.mc.ned.cap.skill.SkillProvider;
import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.lib.EntityHelper;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class SubtotalSkill extends Skill {
    //共济
    public SubtotalSkill() {
        super("subtotal");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_HURT){
            if (eventData.getSecondaryEntity() instanceof ServerPlayer){
                List<? extends LivingEntity> entities = EntityHelper.getLivingEntities(eventData.getMainEntity(), 10);
                List<LivingEntity> se = new ArrayList<>();
                for (LivingEntity entity : entities){
                    entity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                        if (skillCap.hasSkill("skill.ned.subtotal")){
                            se.add(entity);
                        }
                    });
                }
                if (!se.isEmpty()) {
                    float amount = eventData.getAmount() * (1F / se.size());
                    for (LivingEntity livingEntity : se) {
                        livingEntity.hurt(eventData.getMainEntity().damageSources().magic(), amount);
                    }
                    eventData.setAmount(amount);
                    eventData.setUpdateType(EVENT_UP_TYPE_AMOUNT);
                }
            }
        }
    }
}
