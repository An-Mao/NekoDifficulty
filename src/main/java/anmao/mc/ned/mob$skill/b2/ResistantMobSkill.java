package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.Event;

public class ResistantMobSkill extends MobSkill {
    //厚皮
    //减免受到的伤害
    public ResistantMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingDamageEvent damageEvent){
            damageEvent.setAmount(damageEvent.getAmount() /2);
        }
    }
}
