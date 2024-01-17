package anmao.mc.ned.event.server;

import anmao.mc.ned.ConstantDataTable;
import anmao.mc.ned.NED;
import anmao.mc.ned.config.Configs;
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
    public static void onDamage(LivingDamageEvent event){}
    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event){
        LivingEntity entity = event.getEntity();
        if (EntityHelper.isServerLevel(entity) && Configs.ds_applicableTarget > 0 && !(entity instanceof ServerPlayer)){
            if (Configs.ds_applicableTarget == 1){
                if (!entity.getTags().contains(ConstantDataTable.InvasionTag)){
                    return;
                }
            }
            float oldDamage = event.getAmount();
            float maxDamage = Math.max(Configs.ds_minDamage,entity.getMaxHealth() * Configs.ds_scaleWithMaxHealth);
            event.setAmount(Math.min(oldDamage, maxDamage));
        }
    }
}
