package anmao.mc.ned;

import anmao.mc.ned.attribute.AttributeReg;
import anmao.mc.ned.config.Configs;
import anmao.mc.ned.effect.EffectRegister;
import anmao.mc.ned.lib.ItemHelper;
import anmao.mc.ned.net.NEDNetCore;
import anmao.mc.ned.oracle.Oracles;
import anmao.mc.ned.skill.Skills;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(NED.MOD_ID)
public class NED
{
    public static final String MOD_ID = "ned";
    public static final Logger LOG = LogUtils.getLogger();
    public NED()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        AttributeReg.register(modEventBus);
        EffectRegister.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configs.GENERAL_SPEC,ConstantDataTable.GoalConfigFile);
        Skills ss = Skills.getInstance();
        Oracles.getInstance();
    }
    private void commonSetup(final FMLCommonSetupEvent event)
    {
        NEDNetCore.reg();
        _load();
    }
    private void _load(){
        ItemHelper.loadAllItems();
    }
}
