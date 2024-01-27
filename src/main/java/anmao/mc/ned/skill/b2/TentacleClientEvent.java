package anmao.mc.ned.skill.b2;

import anmao.mc.ned.NED;
import anmao.mc.ned.cap.skill.SkillProvider;
import anmao.mc.ned.effect.EffectRegister;
import anmao.mc.ned.lib.EntityHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
@Mod.EventBusSubscriber(modid = NED.MOD_ID,value = Dist.CLIENT)
public class TentacleClientEvent {
    private static final double speed = 0.2;
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event){
        if (event.phase == TickEvent.Phase.START) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                List<? extends LivingEntity> entities = EntityHelper.getEntityLevel(player).getEntities(EntityTypeTest.forClass(LivingEntity.class), player.getBoundingBox().inflate(20), Entity::isAlive);
                final boolean[] b = {false};

                for (LivingEntity livingEntity : entities) {
                    livingEntity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                        if (skillCap.hasSkill("skill.ned.tentacle")){
                            System.out.println("---------------tent---------------");
                            double directionX = livingEntity.getX() - player.getX();
                            double directionY = livingEntity.getY() - player.getY();
                            double directionZ = livingEntity.getZ() - player.getZ();
                            double distance = Math.sqrt(directionX * directionX + directionY * directionY + directionZ * directionZ);
                            double motionX = directionX / distance * speed;
                            double motionY = directionY / distance * speed;
                            double motionZ = directionZ / distance * speed;
                            player.lerpMotion(motionX, motionY, motionZ);
                            b[0] = true;
                        }
                    });
                    if (b[0]){
                        break;
                    }
                }
            }
        }
    }
}
