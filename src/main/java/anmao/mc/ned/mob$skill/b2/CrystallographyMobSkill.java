package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.Event;

public class CrystallographyMobSkill extends MobSkill {
    //结晶
    public CrystallographyMobSkill(String id) {
        super(id);
    }
    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingDamageEvent damageEvent){
            if (damageEvent.getAmount() != 1.0F){
                damageEvent.setAmount(1.0F);
            }
        }
    }
}
