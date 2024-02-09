package anmao.mc.ned.cap.mob$skill;

import anmao.mc.ned.NED;
import anmao.mc.ned.net.MobSkillDataSendToClient;
import anmao.mc.ned.net.NEDNetCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class MobSkillCapEvent {
    @Mod.EventBusSubscriber(modid = NED.MOD_ID)
    public class MSEA {
        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
            event.register(MobSkillCap.class);
        }

        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof LivingEntity livingEntity && notPlayer(event.getObject())) {
                if (livingEntity instanceof Animal || livingEntity instanceof Npc){
                    return;
                }
                if (!livingEntity.getCapability(MobSkillProvider.MOB_SKILLS).isPresent()) {
                    event.addCapability(new ResourceLocation(NED.MOD_ID, "mob_skill"), new MobSkillProvider());
                }
            }
        }

        @SubscribeEvent
        public static void onSpawn(MobSpawnEvent event) {
            if (!event.getLevel().isClientSide()) {
                event.getEntity().getCapability(MobSkillProvider.MOB_SKILLS).ifPresent(mobSkillCap -> {
                    if (!mobSkillCap.isHasMobSkill()){
                        mobSkillCap.giveMobSkills();
                    }
                    mobSkillCap.RunMobSkill(event);
                    NEDNetCore.sendToPlayer(new MobSkillDataSendToClient(mobSkillCap.getSkillAndData(),event.getEntity().getId()),event.getEntity());
                });
            }

        }

        @SubscribeEvent
        public static void onJoin(EntityJoinLevelEvent event) {
            if (!event.getLevel().isClientSide() && event.getEntity() instanceof LivingEntity livingEntity && notPlayer(livingEntity)) {
                livingEntity.getCapability(MobSkillProvider.MOB_SKILLS).ifPresent(mobSkillCap -> mobSkillCap.RunMobSkill(event));
            }
        }

        @SubscribeEvent
        public static void onAttack(LivingAttackEvent event) {
            if (notClient(event.getEntity())) {
                LivingEntity livingEntity = event.getEntity();
                if (notPlayer(livingEntity)) {
                    livingEntity.getCapability(MobSkillProvider.MOB_SKILLS).ifPresent(mobSkillCap -> mobSkillCap.RunMobSkill(event));
                }
            }
        }

        @SubscribeEvent
        public static void onHurt(LivingHurtEvent event) {
            if (notClient(event.getEntity())) {
                LivingEntity livingEntity = event.getEntity();
                if (notPlayer(livingEntity)) {
                    livingEntity.getCapability(MobSkillProvider.MOB_SKILLS).ifPresent(mobSkillCap -> mobSkillCap.RunMobSkill(event));
                }
            }
        }

        @SubscribeEvent
        public static void onDamage(LivingDamageEvent event) {
            if (notClient(event.getEntity())) {
                LivingEntity livingEntity = event.getEntity();
                if (notPlayer(livingEntity)) {
                    livingEntity.getCapability(MobSkillProvider.MOB_SKILLS).ifPresent(mobSkillCap -> mobSkillCap.RunMobSkill(event));
                }
            }
        }

        @SubscribeEvent
        public static void onDeath(LivingDeathEvent event) {
            if (notClient(event.getEntity())) {
                LivingEntity livingEntity = event.getEntity();
                if (notPlayer(livingEntity)) {

                    livingEntity.getCapability(MobSkillProvider.MOB_SKILLS).ifPresent(mobSkillCap -> mobSkillCap.RunMobSkill(event));
                }
            }
        }

        @SubscribeEvent
        public static void onTick(LivingEvent.LivingTickEvent event) {
            if (notClient(event.getEntity()) && notPlayer(event.getEntity())) {
                event.getEntity().getCapability(MobSkillProvider.MOB_SKILLS).ifPresent(mobSkillCap -> mobSkillCap.RunMobSkill(event));
            }
        }
    }






    public static boolean notClient(Entity entity){
        return !entity.level().isClientSide;
    }
    public static boolean notPlayer(Entity entity){
        return !(entity instanceof Player);
    }
}
