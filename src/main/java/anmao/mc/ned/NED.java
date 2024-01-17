package anmao.mc.ned;

import anmao.mc.ned.config.Configs;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
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
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configs.GENERAL_SPEC,ConstantDataTable.GoalConfigFile);
    }
}
