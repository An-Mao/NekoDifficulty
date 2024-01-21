package anmao.mc.ned.cap.skill;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.skill.Skills;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class SkillCap {
    private boolean notFirstJoin = false;
    private boolean notFirstSpawn = false;
    private final HashMap<String,String> skills = new HashMap<>();
    private final HashMap<String,CompoundTag> skillData = new HashMap<>();
    public SkillCap(){}


    public void setNotFirstSpawn() {
        this.notFirstSpawn = true;
    }
    public boolean isNotFirstSpawn() {
        return notFirstSpawn;
    }
    public void setNotFirstJoin( ){
        notFirstJoin = true;
    }
    public boolean isNotFirstJoin() {
        return notFirstJoin;
    }


    public void GiveRandomSkills(int num) {
        if (skills.isEmpty()) {
            Random random = new Random();
            String[] keys = Skills.getInstance().SKILLS.keySet().toArray(new String[0]);
            if (keys.length > 0) {
                for (int i = 0; i < num; i++) {
                    giveNewSkill(keys[random.nextInt(keys.length)]);
                }
            }
        }
    }

    public void giveNewSkill(String id){
        int i = skills.size();
        skills.put("slot." + i, id );
        skillData.put("slot." + i, new CompoundTag());
    }

    public boolean hasSkill(String id) {
        for (String sk : skills.keySet()){
            if (Objects.equals(skills.get(sk), id)){
                return true;
            }
        }
        return false;
    }












    public void SkillRun(EventData eventData){
        for (String skillKey : skills.keySet()){
            Skills.getInstance().getSkill(skills.get(skillKey)).Event(eventData, skillData.get(skillKey));
        }
    }
    public void SkillTick(LivingEntity livingEntity){
        for (String skillKey : skills.keySet()) {
            Skills.getInstance().getSkill(skills.get(skillKey)).Tick(livingEntity,skillData.get(skillKey));
        }
    }














    public void saveNBTData(CompoundTag nbt)
    {
        ListTag tags = new ListTag();
        for (String skillKey:skills.keySet()){
            CompoundTag tag = new CompoundTag();
            tag.putString("slot",skillKey);
            tag.putString("id",skills.get(skillKey));
            tag.put("data",skillData.get(skillKey));
            tags.add(tag);
        }
        nbt.put("ned.skill.list",tags);
        nbt.putBoolean("ned.skill.first.join",notFirstJoin);
        nbt.putBoolean("ned.skill.first.spawn",notFirstSpawn);
    }
    public void loadNBTData(CompoundTag nbt)
    {
        skills.clear();
        ListTag tags = nbt.getList("ned.skill.list",Tag.TAG_COMPOUND);
        for (int i=0;i<tags.size();i++){
            CompoundTag tag = tags.getCompound(i);
            skills.put(tag.getString("slot"),tag.getString("id"));
            skillData.put(tag.getString("slot"),tag.getCompound("data"));
        }
        notFirstJoin = nbt.getBoolean("ned.skill.first.join");
        notFirstSpawn = nbt.getBoolean("ned.skill.first.spawn");
    }
}
