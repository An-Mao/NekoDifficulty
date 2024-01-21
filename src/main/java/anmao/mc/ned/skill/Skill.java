package anmao.mc.ned.skill;

import anmao.mc.ned.datatype.EventData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;

public abstract class Skill extends SkillCDT implements SkillCore{
    private final String id;
    public Skill(String id){
        this.id = id;
    }
    @Override
    public void Event(EventData eventData , CompoundTag skillData){}

    @Override
    public void Tick(LivingEntity livingEntity , CompoundTag skillData){}

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
