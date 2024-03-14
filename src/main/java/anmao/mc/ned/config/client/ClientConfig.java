package anmao.mc.ned.config.client;

import anmao.mc.amlib.json.JsonConfig;
import anmao.mc.ned.config.ConfigCDT;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class ClientConfig extends JsonConfig<Map<String, JsonObject>> {
    public static final ClientConfig INSTANCE = new ClientConfig();
    public ClientConfig() {
        super(ConfigCDT.configDir + "Client.json", """
                {
                  "MobSkill":{
                    "RenderType": 2,
                    "RenderRotationAngle": 10000
                  }
                }""", new TypeToken<>(){});
    }
    public JsonObject getData(String k){
        return getDatas().get(k);
    }
    public double getSkillRenderRotationAngle() {
        return getData("MobSkill").get("RenderRotationAngle").getAsDouble();
    }
    public int getSkillRenderType() {
        return getData("MobSkill").get("RenderType").getAsInt();
    }
}
