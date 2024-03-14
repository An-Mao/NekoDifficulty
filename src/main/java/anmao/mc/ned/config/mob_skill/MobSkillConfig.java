package anmao.mc.ned.config.mob_skill;

import anmao.mc.amlib.json.JsonConfig;
import anmao.mc.ned.config.ConfigCDT;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class MobSkillConfig extends JsonConfig<Map<String, MobSkillConfigData>> {
    public static final MobSkillConfig INSTANCE = new MobSkillConfig();
    public MobSkillConfig(){
        super(ConfigCDT.configDir + "/MobSkill.json",MobSkillDataJsonDefault.jsonData,new TypeToken<>(){});
    }

    public MobSkillConfigData getData(String mobSkillId){
        return getDatas().get(mobSkillId);
    }
    public boolean isEnable(String key){
        MobSkillConfigData data = getData(key);
        if (data == null){
            return false;
        }
        return data.isEnable();
    }
}
