package anmao.mc.ned.mob$skill.b2.fetters;

import anmao.mc.ned.NED;
import anmao.mc.ned.effect.EffectRegister;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NED.MOD_ID)
public class FettersMobSkillEvent {
    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event){
        if (event.player instanceof ServerPlayer serverPlayer){
            if (serverPlayer.getEffect(EffectRegister.FETTERS.get()) != null) {
                double x = serverPlayer.getPersistentData().getDouble("FettersX");
                double y = serverPlayer.getPersistentData().getDouble("FettersY");
                double z = serverPlayer.getPersistentData().getDouble("FettersZ");

                serverPlayer.teleportTo(x, y, z);
            }
        }
    }
}
