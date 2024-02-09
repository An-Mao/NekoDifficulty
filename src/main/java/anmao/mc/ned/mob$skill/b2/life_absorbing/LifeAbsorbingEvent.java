package anmao.mc.ned.mob$skill.b2.life_absorbing;

import anmao.mc.ned.NED;
import anmao.mc.ned.cap.skill.SkillProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NED.MOD_ID)
public class LifeAbsorbingEvent {
    public static void onAttack(LivingAttackEvent event){
        if (event.getSource().getEntity() instanceof LivingEntity livingEntity){
            livingEntity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                if (skillCap.hasSkill("skill.ned.life_absorbing")){
                    livingEntity.heal(event.getAmount() * 0.5F);
                }
            });
        }
    }
}
