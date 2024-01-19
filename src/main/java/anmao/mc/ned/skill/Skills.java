package anmao.mc.ned.skill;

import anmao.mc.ned.skill.b2.AloneSkill;
import anmao.mc.ned.skill.b2.FastSkill;
import anmao.mc.ned.skill.b2.ResistantSkill;

import java.util.*;

public class Skills {
    private static final Skills instance = new Skills();
    public HashMap<String,Skill> SKILLS = new HashMap<>();
    public Skills(){
        //autoRegisterSkills();
        registers();
    }
    private void registers(){
        _reg(new ResistantSkill());
        _reg(new FastSkill());
        _reg(new AloneSkill());
    }
    private void autoRegisterSkills() {
        ServiceLoader<Skill> skillLoader = ServiceLoader.load(Skill.class);
        for (Skill skill : skillLoader) {
            _reg(skill);
        }
    }
    public void _reg(Skill skill){
        SKILLS.put(skill.GetID(),skill);
    }

    public Skill getSkill(String id){
        return SKILLS.get(id);
    }
    private static <K, V> Map.Entry<K, V> getRandomEntry(Map<K, V> map) {
        java.util.List<Map.Entry<K, V>> entryList = new java.util.ArrayList<>(map.entrySet());
        int randomIndex = new Random().nextInt(entryList.size());
        return entryList.get(randomIndex);
    }
    public List<String> getRandomSelection(int num) {
        List<String> selectedData = new ArrayList<>();

        Random random = new Random();
        String[] keys = SKILLS.keySet().toArray(new String[0]);
        System.out.println("::keys::"+ Arrays.toString(keys));
        if (keys.length > 0) {
            for (int i = 0; i < num; i++) {
                selectedData.add(keys[random.nextInt(keys.length)]);
            }
        }
        return selectedData;
    }

    public static Skills getInstance() {
        return instance;
    }
}
