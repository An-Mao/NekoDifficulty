package anmao.mc.ned.mob$skill;

import anmao.mc.ned.NED;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class MobSkills extends MobSkillRegister{
    private static final List<String> allMobSkillId = new ArrayList<>();
    private static int allMobSkillNumber = 0;



    public static void init(){
        allMobSkillId.clear();
        REGISTRY.get().forEach(mobSkill -> allMobSkillId.add(mobSkill.GetID()));
        allMobSkillNumber = allMobSkillId.size();
    }

    public static List<String> getAllMobSkillId(){
        return allMobSkillId;
    }

    public static int getAllMobSkillNumber() {
        return allMobSkillNumber;
    }

    public static MobSkill getMobSkill(String id){
        return getMobSkill(new ResourceLocation(NED.MOD_ID,id));
    }
    public static MobSkill getMobSkill(ResourceLocation id){
        return REGISTRY.get().getValue(id);
    }
}
