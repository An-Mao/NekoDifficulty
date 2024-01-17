package anmao.mc.ned.cap.invasion;

import anmao.mc.ned.NED;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = NED.MOD_ID)
public class InvasionEvent extends InvasionCDT{
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event)
    {
        event.register(Invasion.class);
    }
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Level> event)
    {
        if (event.getObject() instanceof Level)
        {
            if (!event.getObject().getCapability(InvasionProvider.levelInvasion).isPresent())
            {
                event.addCapability(new ResourceLocation(NED.MOD_ID,"invasion"),new InvasionProvider());
            }
        }
    }
    @SubscribeEvent
    public static void onTick(TickEvent.LevelTickEvent event){
        if (event.phase == TickEvent.Phase.END) {
            Level level = event.level;
            if (!level.isClientSide && level.dimension().location().toString().equals("minecraft:overworld")) {
                level.getCapability(InvasionProvider.levelInvasion).ifPresent(invasion -> {
                    invasion.tick(level);
                });
            }
        }
    }
    @SubscribeEvent
    public static void onSpawn(MobSpawnEvent event){
        Mob mob = event.getEntity();
        if (!mob.level().isClientSide) {
            if (mob.getPersistentData().getBoolean(SAVE_INVASION_KEY)) {
                mob.addEffect(InvasionCDT.SLOW_FALLING);
                mob.addEffect(InvasionCDT.WATER_BREATHING);
                mob.addEffect(InvasionCDT.FIRE_RESISTANCE);
            }
        }
    }
    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event){
        System.out.println("================data=================");
        System.out.println(event.getEntity().serializeNBT());
    }
}
