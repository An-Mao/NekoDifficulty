package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;

public class DislikeMobSkill extends MobSkill {
    //讨厌
    public DislikeMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingHurtEvent hurtEvent){
            if (hurtEvent.getSource().getEntity() != null && hurtEvent.getEntity().distanceTo(hurtEvent.getSource().getEntity()) < 5) {
                hurtEvent.setAmount(hurtEvent.getAmount() * 0.2F);
            }
        }
    }
}
