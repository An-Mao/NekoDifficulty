package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.cap.skill.SkillProvider;
import anmao.mc.ned.lib.EntityHelper;
import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;

import java.util.ArrayList;
import java.util.List;

public class SubtotalMobSkill extends MobSkill {
    //共济
    public SubtotalMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingHurtEvent hurtEvent){
            if (hurtEvent.getSource().getEntity() instanceof ServerPlayer){
                List<? extends LivingEntity> entities = EntityHelper.getLivingEntities(hurtEvent.getEntity(), 10);
                List<LivingEntity> se = new ArrayList<>();
                for (LivingEntity entity : entities){
                    entity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                        if (skillCap.hasSkill("skill.ned.subtotal")){
                            se.add(entity);
                        }
                    });
                }
                if (!se.isEmpty()) {
                    float amount = hurtEvent.getAmount() * (1F / se.size());
                    for (LivingEntity livingEntity : se) {
                        livingEntity.hurt(hurtEvent.getEntity().damageSources().magic(), amount);
                    }
                    hurtEvent.setAmount(amount);
                }
            }
        }
    }
}
