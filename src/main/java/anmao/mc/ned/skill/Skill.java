package anmao.mc.ned.skill;

import anmao.mc.ned.datatype.EventData;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;

public abstract class Skill implements SkillCore{
    private final String id;
    public Skill(String id){
        this.id = id;
    }
    @Override
    public abstract EventData Event(EventData eventData);

    @Override
    public void Tick(LivingEntity livingEntity){}

    @Override
    public int GetMaxLvl() {
        return 1;
    }

    @Override
    public String GetID() {
        return "skill.ned."+id;
    }

    @Override
    public Component GetDes() {
        return Component.translatable(GetID());
    }

    @Override
    public boolean CanAdd(LivingEntity livingEntity) {
        return true;
    }
}
