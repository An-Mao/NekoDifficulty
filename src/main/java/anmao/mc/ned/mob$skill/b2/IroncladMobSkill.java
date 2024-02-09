package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;

public class IroncladMobSkill extends MobSkill {
    //铁壁
    public IroncladMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingHurtEvent hurtEvent){
            float damage = Math.min(hurtEvent.getAmount(),11);
            hurtEvent.setAmount(damage);
        }
    }
}
