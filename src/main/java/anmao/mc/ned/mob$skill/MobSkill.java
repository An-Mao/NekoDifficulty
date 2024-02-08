package anmao.mc.ned.mob$skill;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.eventbus.api.Event;

public abstract class MobSkill extends MobSkillCDT implements MobSkillInterface {

    protected MobSkill(){
        //
    }
    @Override
    public boolean canAdd(String[] skills) {
        return true;
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
    }
}
