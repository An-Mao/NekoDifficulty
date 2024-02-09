package anmao.mc.ned.cap.mob$skill;

import anmao.mc.ned.config.Configs;
import anmao.mc.ned.mob$skill.MobSkills;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.eventbus.api.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MobSkillCap {
    private boolean hasMobSkill = false;
    private final HashMap<String,String> mobSkills = new HashMap<>();
    private final HashMap<String,CompoundTag> mobSkillData = new HashMap<>();
    public MobSkillCap(){}

    public boolean isHasMobSkill(){
        return hasMobSkill;
    }
    public void giveMobSkills() {
        hasMobSkill = true;
        int n = MobSkills.getAllMobSkillNumber();
        if (n > 0) {
            mobSkills.clear();
            Random random = new Random();
            for (int i = 0; i < Configs.skill_mobMaxSkill; i++) {
                giveNewSkill(MobSkills.getAllMobSkillId().get(random.nextInt(n)));
            }
        }
    }
    public void giveNewSkill(String id){
        int i = mobSkills.size();
        mobSkills.put("slot." + i, id );
        mobSkillData.put("slot." + i, new CompoundTag());
    }

    public boolean hasMobSkillById(String id) {
        for (String sk : mobSkills.keySet()){
            if (Objects.equals(mobSkills.get(sk), id)){
                return true;
            }
        }
        return false;
    }
    public List<String> getAllSkillID(){
        return mobSkills.values().stream().toList();
    }


    public void RunMobSkill(Event event){
        for (String skillKey : mobSkills.keySet()){
            MobSkills.getMobSkill(mobSkills.get(skillKey)).event(event,mobSkillData.get(skillKey));
        }
    }










    public void saveNBTData(CompoundTag nbt)
    {
        ListTag tags = new ListTag();
        for (String skillKey: mobSkills.keySet()){
            CompoundTag tag = new CompoundTag();
            tag.putString("slot",skillKey);
            tag.putString("id", mobSkills.get(skillKey));
            tag.put("data", mobSkillData.get(skillKey));
            tags.add(tag);
        }
        nbt.put("mob_skill.ned.list",tags);
        nbt.putBoolean("mob_skill.ned.has",hasMobSkill);
    }
    public void loadNBTData(CompoundTag nbt)
    {
        mobSkills.clear();
        ListTag tags = nbt.getList("mob_skill.ned.list",Tag.TAG_COMPOUND);
        for (int i=0;i<tags.size();i++){
            CompoundTag tag = tags.getCompound(i);
            mobSkills.put(tag.getString("slot"),tag.getString("id"));
            mobSkillData.put(tag.getString("slot"),tag.getCompound("data"));
        }
        hasMobSkill = nbt.getBoolean("mob_skill.ned.has");
    }
    public CompoundTag getSkillAndData(){
        CompoundTag nbt = new CompoundTag();
        ListTag tags = new ListTag();
        for (String skillKey: mobSkills.keySet()){
            CompoundTag tag = new CompoundTag();
            tag.putString("slot",skillKey);
            tag.putString("id", mobSkills.get(skillKey));
            tag.put("data", mobSkillData.get(skillKey));
            tags.add(tag);
        }
        nbt.put("mob_skill.ned.list",tags);
        nbt.putBoolean("mob_skill.ned.join",hasMobSkill);
        return nbt;
    }
    public void handlePacket(CompoundTag compoundTag){
        loadNBTData(compoundTag);
    }
}
