package anmao.mc.ned.attribute;

import anmao.mc.ned.NED;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NED.MOD_ID)
public class HurtDownEvent {
    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event){
        if (!event.getEntity().level().isClientSide){
            AttributeInstance a = event.getEntity().getAttribute(NEDAttributes.hurtDown);
            if (a != null){
                float v = (float) a.getValue();
                event.setAmount(event.getAmount() * (1F- v / 100F));
            }
        }
    }
}
