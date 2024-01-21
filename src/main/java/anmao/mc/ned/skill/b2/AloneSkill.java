package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;

public class AloneSkill extends Skill {
    //孤独
    //怪物越少，攻击力，移速，减伤提升越多
    public AloneSkill() {
        super("alone");
        //reg();
    }
    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
    }

}
