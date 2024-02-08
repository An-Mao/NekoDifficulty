package anmao.mc.ned.skill;

import anmao.mc.ned.NED;
import anmao.mc.ned.lib.Manger;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SkillsData {
    private final HashMap<String,JsonObject> data = new HashMap<>();
    private final ResourceLocation skillConfig = new ResourceLocation(NED.MOD_ID, "skill/config.json");
    public SkillsData(){
    }
    public void loadJSONFile(ResourceManager resourceManager) {
        System.out.println("Load data file :" + skillConfig);
        resourceManager.getResource(skillConfig).ifPresent(resource -> {
            try {
                System.out.println("Load data file con");
                BufferedReader reader = resource.openAsReader();
                StringBuilder jsonContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonContent.append(line);
                }
                reader.close();

                System.out.println("data file con :"+ jsonContent);
                JsonObject jsonObject = JsonParser.parseString(jsonContent.toString()).getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                    String key = entry.getKey();
                    JsonObject value = entry.getValue().getAsJsonObject();
                    data.put(key, value);
                }


                System.out.println("data::"+data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public JsonObject getData(String skillId){
        return data.get(skillId);
    }
}
