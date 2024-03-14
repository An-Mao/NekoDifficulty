package anmao.mc.ned.config.damage_scale;

import anmao.mc.amlib.json.JsonConfig;
import anmao.mc.ned.config.ConfigCDT;
import com.google.gson.reflect.TypeToken;

public class DamageScaleConfig extends JsonConfig<DamageScaleData> {
    public static final DamageScaleConfig INSTANCE = new DamageScaleConfig();
    public DamageScaleConfig() {
        super(ConfigCDT.configDir +"DamageScale.json", """
                {
                  "ApplicableTarget": 1,
                  "ScaleWithHealth": 0.2,
                  "MinDamage": 5.0
                }""", new TypeToken<>(){});
    }
}
