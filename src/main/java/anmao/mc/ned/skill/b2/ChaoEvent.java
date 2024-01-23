package anmao.mc.ned.skill.b2;

import anmao.mc.ned.NED;
import anmao.mc.ned.effect.EffectRegister;
import anmao.mc.ned.lib._Math;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NED.MOD_ID)
public class ChaoEvent {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
        if (event.player instanceof ServerPlayer serverPlayer) {
        }
    }

}
