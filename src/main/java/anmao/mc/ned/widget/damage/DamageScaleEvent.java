package anmao.mc.ned.widget.damage;

import anmao.mc.ned.NED;
import anmao.mc.ned.cap.invasion.InvasionEvent;
import anmao.mc.ned.config.damage_scale.DamageScaleConfig;
import anmao.mc.ned.lib.EntityHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NED.MOD_ID)
public class DamageScaleEvent {
    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event){
    }
    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event){
        LivingEntity entity = event.getEntity();
        if (EntityHelper.isServerLevel(entity) && !(entity instanceof ServerPlayer) && DamageScaleConfig.INSTANCE.getDatas().getApplicableTarget() > 0 ){
            if (DamageScaleConfig.INSTANCE.getDatas().getApplicableTarget() == 1){
                if (!InvasionEvent.isInvasionMob(entity)){
                    return;
                }
            }
            float oldDamage = event.getAmount();
            float maxDamage = Math.max(DamageScaleConfig.INSTANCE.getDatas().getMinDamage(),entity.getMaxHealth() * DamageScaleConfig.INSTANCE.getDatas().getScaleWithHealth());
            event.setAmount(Math.min(oldDamage, maxDamage));
        }
    }
    @SubscribeEvent
    public static void onDamage(LivingDamageEvent event){
        LivingEntity entity = event.getEntity();
        if (EntityHelper.isServerLevel(entity) && !(entity instanceof ServerPlayer) && DamageScaleConfig.INSTANCE.getDatas().getApplicableTarget() > 0 ){
            if (DamageScaleConfig.INSTANCE.getDatas().getApplicableTarget() == 1){
                if (!InvasionEvent.isInvasionMob(entity)){
                    return;
                }
            }
            float oldDamage = event.getAmount();
            float maxDamage = Math.max(DamageScaleConfig.INSTANCE.getDatas().getMinDamage(),entity.getMaxHealth() * DamageScaleConfig.INSTANCE.getDatas().getScaleWithHealth());
            event.setAmount(Math.min(oldDamage, maxDamage));
        }
    }
}
