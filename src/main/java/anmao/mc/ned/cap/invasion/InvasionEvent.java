package anmao.mc.ned.cap.invasion;

import anmao.mc.ned.NED;
import anmao.mc.ned.config.Configs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
                level.getCapability(InvasionProvider.levelInvasion).ifPresent(invasion -> invasion.tick(level));
            }
        }
    }
    @SubscribeEvent
    public static void onSpawn(MobSpawnEvent event){
        Mob mob = event.getEntity();
        if (!mob.level().isClientSide) {
            if (isInvasionMob(mob)) {
                mob.addEffect(InvasionCDT.SLOW_FALLING);
                mob.addEffect(InvasionCDT.WATER_BREATHING);
                mob.addEffect(InvasionCDT.FIRE_RESISTANCE);
            }
        }
    }
    @SubscribeEvent
    public void onDamage(LivingDamageEvent event){
        LivingEntity livingEntity = event.getEntity();
        if (!livingEntity.level().isClientSide && isInvasionMob(livingEntity) && Configs.invasion_immunityNonPlayerDamage){
            if (!(event.getSource().getEntity() instanceof Player)){
                event.setCanceled(true);
            }
        }
    }

    public static boolean isInvasionMob(LivingEntity livingEntity){
        return livingEntity.getPersistentData().getBoolean(SAVE_INVASION_KEY);
    }
}
