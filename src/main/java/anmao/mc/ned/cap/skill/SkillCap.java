package anmao.mc.ned.cap.skill;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.skill.Skills;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkillCap {
    private List<String> skills = new ArrayList<>();
    public void GiveSkills(){
        skills = Skills.getInstance().getRandomSelection(1);
    }
    public void SkillRun(EventData eventData){
        for (String skill : skills){
            Skills.getInstance().getSkill(skill).Event(eventData);
        }
    }
    public void SkillTick(LivingEntity livingEntity){
        for (String skill : skills){
            Skills.getInstance().getSkill(skill).Tick(livingEntity);
        }
    }
}
