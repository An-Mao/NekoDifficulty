package anmao.mc.ned.mob$skill.b2.life_absorbing;

import anmao.mc.ned.NED;
import anmao.mc.ned.cap.mob$skill.MobSkillProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NED.MOD_ID)
public class LifeAbsorbingEvent {
    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event){
        if (event.getSource().getEntity() instanceof LivingEntity livingEntity){
            livingEntity.getCapability(MobSkillProvider.MOB_SKILLS).ifPresent(mobSkillCap -> {

                if (mobSkillCap.hasMobSkillById("life_absorbing")){
                    livingEntity.heal(event.getAmount() * 0.5F);
                }
            });
        }
    }
}
