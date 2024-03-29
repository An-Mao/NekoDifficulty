package anmao.mc.ned.mob$skill.b2.chao;

import anmao.mc.ned.NED;
import anmao.mc.ned.effect.EffectRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = NED.MOD_ID,value = Dist.CLIENT)
public class ChaoClientEvent {
    private static final Random random = new Random();
    //private static final int[] options = {-1, 0, 1};
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event){
        if (event.phase == TickEvent.Phase.START) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null && player.getEffect(EffectRegister.CHAO.get()) != null) {
                double speed = 2.5;
                double theta = random.nextDouble() * 2 * Math.PI;
                double phi = random.nextDouble() * Math.PI;
                double xi = speed * Math.sin(phi) * Math.cos(theta);
                double zi = speed * Math.sin(phi) * Math.sin(theta);
                //double dx = player.getX() + xi;
                //double dy = player.getY();
                //double dz = player.getZ() + zi;
                //System.out.println("xi{" + xi + "} zi{" + zi + "} dy{" + dy + "}");
                double distance = Math.sqrt(xi * xi + zi * zi);
                if (distance > 0.1) {
                    player.lerpMotion(xi, 0, zi);
                } else {
                    player.lerpMotion(0, 0, 0);
                }
            }
        }
    }
}
