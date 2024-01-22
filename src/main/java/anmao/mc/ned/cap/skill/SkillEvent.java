package anmao.mc.ned.cap.skill;

import anmao.mc.ned.NED;
import anmao.mc.ned.config.Configs;
import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.lib._Math;
import anmao.mc.ned.skill.SkillCDT;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;


public class SkillEvent {
    @Mod.EventBusSubscriber(modid = NED.MOD_ID)
    public class SEA {
        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
            event.register(SkillCap.class);
        }

        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof LivingEntity livingEntity && notPlayer(event.getObject())) {
                if (livingEntity instanceof Animal || livingEntity instanceof Npc){
                    return;
                }
                if (!livingEntity.getCapability(SkillProvider.MOB_SKILLS).isPresent()) {
                    event.addCapability(new ResourceLocation(NED.MOD_ID, "skill"), new SkillProvider());
                }
            }
        }

        @SubscribeEvent
        public static void onSpawn(MobSpawnEvent event) {
            if (!event.getLevel().isClientSide()) {
                //runSkillCap(event.getEntity(),eventData);
                event.getEntity().getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                    EventData eventData;
                    if (skillCap.isNotFirstSpawn()) {
                        eventData = new EventData(EventType.EVENT_SPAWN, event.getEntity());
                    } else {
                        skillCap.GiveRandomSkills(_Math.getIntRandomNumber(1, Configs.skill_mobMaxSkill));
                        skillCap.setNotFirstSpawn();
                        eventData = new EventData(EventType.EVENT_FIRST_SPAWN, event.getEntity());
                    }
                    skillCap.SkillRun(eventData);
                    if (eventData.getUpdateType() == SkillCDT.EVENT_UP_TYPE_CANCEL) {
                        event.setCanceled(eventData.isCancel());
                    }
                });
            }
        }

        @SubscribeEvent
        public static void onJoin(EntityJoinLevelEvent event) {
            if (!event.getLevel().isClientSide() && event.getEntity() instanceof LivingEntity livingEntity && notPlayer(livingEntity)) {
                livingEntity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                    EventData eventData;
                    if (skillCap.isNotFirstJoin()) {
                        eventData = new EventData(EventType.EVENT_JOIN_LEVEL, livingEntity);
                    } else {
                        skillCap.setNotFirstJoin();
                        eventData = new EventData(EventType.EVENT_FIRST_JOIN, livingEntity);
                    }
                    skillCap.SkillRun(eventData);
                    if (eventData.getUpdateType() == SkillCDT.EVENT_UP_TYPE_CANCEL) {
                        event.setCanceled(eventData.isCancel());
                    }
                });
            }
        }

        @SubscribeEvent
        public static void onAttack(LivingAttackEvent event) {
            if (notClient(event.getEntity())) {
                LivingEntity livingEntity = event.getEntity();
                if (notPlayer(livingEntity)) {

                    //runSkillCap(livingEntity,eventData);
                    livingEntity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                        EventData eventData = new EventData(EventType.EVENT_ATTACK, livingEntity, event.getSource().getEntity(), event.getSource(), event.getAmount());
                        skillCap.SkillRun(eventData);
                        if (eventData.getUpdateType() == SkillCDT.EVENT_UP_TYPE_CANCEL) {
                            event.setCanceled(eventData.isCancel());
                        }
                    });
                }
            }
        }

        @SubscribeEvent
        public static void onHurt(LivingHurtEvent event) {
            if (notClient(event.getEntity())) {
                LivingEntity livingEntity = event.getEntity();
                if (notPlayer(livingEntity)) {

                    //runSkillCap(livingEntity,eventData);
                    livingEntity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                        EventData eventData = new EventData(EventType.EVENT_HURT, livingEntity, event.getSource().getEntity(), event.getSource(), event.getAmount());
                        skillCap.SkillRun(eventData);
                        if (eventData.getUpdateType() == SkillCDT.EVENT_UP_TYPE_CANCEL) {
                            event.setCanceled(eventData.isCancel());
                        } else if (eventData.getUpdateType() == SkillCDT.EVENT_UP_TYPE_AMOUNT) {
                            event.setAmount(eventData.getAmount());
                        }
                    });
                }
            }
        }

        @SubscribeEvent
        public static void onDamage(LivingDamageEvent event) {
            if (notClient(event.getEntity())) {
                LivingEntity livingEntity = event.getEntity();
                if (notPlayer(livingEntity)) {
                    //runSkillCap(livingEntity,eventData);
                    livingEntity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                        EventData eventData = new EventData(EventType.EVENT_DAMAGE, livingEntity, event.getSource().getEntity(), event.getSource(), event.getAmount());
                        skillCap.SkillRun(eventData);
                        if (eventData.getUpdateType() == SkillCDT.EVENT_UP_TYPE_CANCEL) {
                            event.setCanceled(eventData.isCancel());
                        } else if (eventData.getUpdateType() == SkillCDT.EVENT_UP_TYPE_AMOUNT) {
                            event.setAmount(eventData.getAmount());
                        }
                    });
                }
            }
        }

        @SubscribeEvent
        public static void onDeath(LivingDeathEvent event) {
            if (notClient(event.getEntity())) {
                LivingEntity livingEntity = event.getEntity();
                if (notPlayer(livingEntity)) {

                    //runSkillCap(livingEntity,eventData);
                    livingEntity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                        EventData eventData = new EventData(EventType.EVENT_DEATH, livingEntity, event.getSource().getEntity(), event.getSource());
                        skillCap.SkillRun(eventData);
                        if (eventData.getUpdateType() == SkillCDT.EVENT_UP_TYPE_CANCEL) {
                            event.setCanceled(eventData.isCancel());
                        }
                    });
                }
            }
        }

        @SubscribeEvent
        public static void onTick(LivingEvent.LivingTickEvent event) {
            if (notClient(event.getEntity()) && notPlayer(event.getEntity())) {
                event.getEntity().getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                    skillCap.SkillTick(event.getEntity());
                });
            }
        }
    }
    @Mod.EventBusSubscriber(modid = NED.MOD_ID, value = Dist.CLIENT)
    public class SEC {
        private static final HashMap<String,Boolean> isClick = new HashMap<>();

        public static void onKeyInput(InputEvent.Key event) {
            String k = "{k:"+event.getKey() +"}{s:"+ event.getScanCode() +"}{a:"+ event.getAction() +"}{m:"+ event.getModifiers()+"}";
            if (!isClick.getOrDefault(k,false) && event.getKey() == InputConstants.getKey("key.forward").getValue()) {
                ForgeHooksClient.onKeyInput(InputConstants.getKey("key.left").getValue(),event.getScanCode(),event.getAction(),event.getModifiers());
                isClick.put(k,true);
            }else {
                isClick.remove(k);
            }
        }
    }






    public static boolean notClient(Entity entity){
        return !entity.level().isClientSide;
    }
    public static boolean notPlayer(Entity entity){
        return !(entity instanceof Player);
    }
    public static void runSkillCap(LivingEntity livingEntity , EventData eventData){
        livingEntity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
             skillCap.SkillRun(eventData);
        });
    }
    public void onRenderOverlay(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_ENTITIES) {
        }
    }
}
