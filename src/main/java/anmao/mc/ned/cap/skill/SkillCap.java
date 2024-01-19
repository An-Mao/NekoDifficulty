package anmao.mc.ned.cap.skill;

import anmao.mc.ned.config.Configs;
import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.lib._Math;
import anmao.mc.ned.skill.Skills;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkillCap {
    private List<String> skills;
    public SkillCap(){
        skills = new ArrayList<>();
    }
    public void GiveSkills(){
        if (skills.isEmpty()) {
            skills = Skills.getInstance().getRandomSelection(_Math.getIntRandomNumber(1, Configs.skill_mobMaxSkill));
            System.out.println("::GiveSkill::"+skills);
        }
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
    public void saveNBTData(CompoundTag nbt)
    {
        ListTag tags = new ListTag();
        for (String skill:skills){
            CompoundTag tag = new CompoundTag();
            tag.putString("id",skill);
            tags.add(tag);
        }
        nbt.put("ned.skill",tags);
    }
    public void loadNBTData(CompoundTag nbt)
    {
        skills.clear();
        ListTag tags = nbt.getList("ned.skill",Tag.TAG_COMPOUND);
        for (int i=0;i<tags.size();i++){
            CompoundTag tag = tags.getCompound(i);
            skills.add(tag.getString("id"));
        }
    }
}
