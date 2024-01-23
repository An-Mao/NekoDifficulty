package anmao.mc.ned.skill.b2;

import anmao.mc.ned.NED;
import anmao.mc.ned.effect.EffectRegister;
import anmao.mc.ned.lib._Math;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber(modid = NED.MOD_ID,value = Dist.CLIENT)
public class ChaoEventClient {
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
                double dy = player.getY();
                //double dz = player.getZ() + zi;
                //System.out.println("xi{" + xi + "} zi{" + zi + "} dx{" + dx + "} dy{" + dy + "} dz{" + dz + "}");
                double distance = Math.sqrt(xi * xi + dy * dy + zi * zi);
                if (distance > 0.1) {
                    player.lerpMotion(xi, dy, zi);
                } else {
                    player.lerpMotion(0, 0, 0);
                }
            }
        }
    }
}
