package anmao.mc.ned.cap.skill;

import anmao.mc.ned.NED;
import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventTypes;
import anmao.mc.ned.skill.SkillCDT;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NED.MOD_ID)
public class SkillEvent {
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event)
    {
        event.register(SkillCap.class);
    }
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof LivingEntity livingEntity && notPlayer(event.getObject()))
        {
            if (!livingEntity.getCapability(SkillProvider.MOB_SKILLS).isPresent())
            {
                event.addCapability(new ResourceLocation(NED.MOD_ID,"skill"),new SkillProvider());
            }
        }
    }
    @SubscribeEvent
    public static void onSpawn(MobSpawnEvent event){
        if (!event.getLevel().isClientSide()){
            //runSkillCap(event.getEntity(),eventData);
            event.getEntity().getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                skillCap.GiveSkills();
                EventData eventData = new EventData(EventTypes.EVENT_SPAWN, event.getEntity());
                skillCap.SkillRun(eventData);
                if (eventData.getUpdateType() == SkillCDT.TYPE_CANCEL){
                    event.setCanceled(eventData.isCancel());
                }
            });
        }
    }
    @SubscribeEvent
    public static void onJoin(EntityJoinLevelEvent event){
        if (!event.getLevel().isClientSide() && event.getEntity() instanceof LivingEntity livingEntity && notPlayer(livingEntity)){
            //runSkillCap(livingEntity,eventData);
            livingEntity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                EventData eventData = new EventData(EventTypes.EVENT_JOIN_LEVEL, livingEntity);
                skillCap.SkillRun(eventData);
                if (eventData.getUpdateType() == SkillCDT.TYPE_CANCEL){
                    event.setCanceled(eventData.isCancel());
                }
            });
        }
    }
    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event){
        if (notClient(event.getEntity())){
            LivingEntity livingEntity = event.getEntity();
            if (notPlayer(livingEntity)) {

                //runSkillCap(livingEntity,eventData);
                livingEntity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                    EventData eventData = new EventData(EventTypes.EVENT_ATTACK, livingEntity,event.getSource().getEntity(),event.getSource(),event.getAmount());
                    skillCap.SkillRun(eventData);
                    if (eventData.getUpdateType() == SkillCDT.TYPE_CANCEL){
                        event.setCanceled(eventData.isCancel());
                    }
                });
            }
        }
    }
    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event){
        if (notClient(event.getEntity())){
            LivingEntity livingEntity = event.getEntity();
            if (notPlayer(livingEntity)) {

                //runSkillCap(livingEntity,eventData);
                livingEntity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                    EventData eventData = new EventData(EventTypes.EVENT_HURT, livingEntity,event.getSource().getEntity(),event.getSource(),event.getAmount());
                    skillCap.SkillRun(eventData);
                    if (eventData.getUpdateType() == SkillCDT.TYPE_CANCEL){
                        event.setCanceled(eventData.isCancel());
                    }else if (eventData.getUpdateType() == SkillCDT.TYPE_AMOUNT){
                        event.setAmount(eventData.getAmount());
                    }
                });
            }
        }
    }
    @SubscribeEvent
    public static void onDamage(LivingDamageEvent event){
        if (notClient(event.getEntity())){
            LivingEntity livingEntity = event.getEntity();
            if (notPlayer(livingEntity)) {
                //runSkillCap(livingEntity,eventData);
                livingEntity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                    EventData eventData = new EventData(EventTypes.EVENT_DAMAGE, livingEntity,event.getSource().getEntity(),event.getSource(),event.getAmount());
                    skillCap.SkillRun(eventData);
                    System.out.println("::"+eventData.getUpdateType());
                    if (eventData.getUpdateType() == SkillCDT.TYPE_CANCEL){
                        event.setCanceled(eventData.isCancel());
                    }else if (eventData.getUpdateType() == SkillCDT.TYPE_AMOUNT){
                        event.setAmount(eventData.getAmount());
                    }
                });
            }
        }
    }
    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event){
        if (notClient(event.getEntity())){
            LivingEntity livingEntity = event.getEntity();
            if (notPlayer(livingEntity)) {

                //runSkillCap(livingEntity,eventData);
                livingEntity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                    EventData eventData = new EventData(EventTypes.EVENT_DEATH, livingEntity,event.getSource().getEntity(),event.getSource());
                    skillCap.SkillRun(eventData);
                    if (eventData.getUpdateType() == SkillCDT.TYPE_CANCEL){
                        event.setCanceled(eventData.isCancel());
                    }
                });
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
}
