package anmao.mc.ned.config.invasion;

import anmao.mc.amlib.json.JsonConfig;
import anmao.mc.ned.config.ConfigCDT;
import com.google.gson.reflect.TypeToken;

public class InvasionConfig extends JsonConfig<InvasionConfigData> {
    public static final InvasionConfig INSTANCE = new InvasionConfig();
    public InvasionConfig() {
        super(ConfigCDT.configDir + "Invasion.json", """
                {
                  "immunityNonPlayerDamage": true,
                  "MinDayInterval": 3,
                  "MaxDayInterval": 7,
                  "Probability": 0.2,
                  "Duration": 10000,
                  "DayTime": -1,
                  "Waves": 10,
                  "MobSingleLimit": 35
                }""", new TypeToken<>(){});
    }
}
