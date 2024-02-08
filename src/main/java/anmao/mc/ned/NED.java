package anmao.mc.ned;

import anmao.mc.ned.attribute.AttributeReg;
import anmao.mc.ned.config.ClientConfig;
import anmao.mc.ned.config.Configs;
import anmao.mc.ned.effect.EffectRegister;
import anmao.mc.ned.lib.ItemHelper;
import anmao.mc.ned.mob$skill.MobSkillRegister;
import anmao.mc.ned.net.NEDNetCore;
import anmao.mc.ned.oracle.Oracles;
import anmao.mc.ned.skill.Skills;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(NED.MOD_ID)
public class NED
{
    public static final String MOD_ID = "ned";
    private static final Skills ss = Skills.getInstance();
    private static final Oracles os = Oracles.getInstance();
    public NED()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        AttributeReg.register(modEventBus);
        EffectRegister.register(modEventBus);
        MobSkillRegister.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        regConfigFiles();
    }
    private void regConfigFiles(){
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configs.SPEC,ConstantDataTable.GoalConfigFile);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ClientConfig.CLIENT_SPEC,ConstantDataTable.ClientGoalConfigFile);
    }


    private void commonSetup(final FMLCommonSetupEvent event)
    {
        NEDNetCore.reg();
        _load();
    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        ss.loadSkillConfig(event.getServer().getResourceManager());
    }
    private void _load(){
        ItemHelper.loadAllItems();
        ss.registers();
        os.regAll();
    }
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            //ss.loadSkillConfig(Minecraft.getInstance().getResourceManager());
            //ss.registers();
            //ClientConfig.onClientLoad();
        }
    }
}
