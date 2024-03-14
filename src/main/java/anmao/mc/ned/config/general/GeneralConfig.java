package anmao.mc.ned.config.general;

import anmao.mc.amlib.json.JsonConfig;
import anmao.mc.ned.config.ConfigCDT;
import anmao.mc.ned.config.invasion.InvasionMobList;
import com.google.gson.reflect.TypeToken;


public class GeneralConfig extends JsonConfig<GeneralConfigData> {
    public static final GeneralConfig INSTANCE = new GeneralConfig();
    public GeneralConfig() {
        super(ConfigCDT.configDir + "general.json", """
                {
                  "MobMaxSkill": 10
                }""", new TypeToken<>(){});
    }
}
