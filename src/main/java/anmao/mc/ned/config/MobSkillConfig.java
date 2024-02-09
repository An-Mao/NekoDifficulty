package anmao.mc.ned.config;

import anmao.mc.ned.debug._Log;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MobSkillConfig extends MobSkillDataJsonDefault{
    private final String fileName =Config.configDir + "/skill.json";
    private final HashMap<String,JsonObject> data = new HashMap<>();
    public MobSkillConfig(){}
    public void _start(){
        File file = new File(fileName);
        if (!file.exists()){
            reset();
        }
        try (FileReader reader = new FileReader(fileName)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                String key = entry.getKey();
                JsonObject value = entry.getValue().getAsJsonObject();
                data.put(key, value);
            }
        } catch (IOException e) {
            _Log.LOGGER.error(e.getMessage());
        }
    }
    private void reset() {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(jsonData);
        } catch (IOException e) {
            _Log.LOGGER.error(e.getMessage());
        }
    }

    public JsonObject getData(String mobSkillId){
        return data.get(mobSkillId);
    }
}
