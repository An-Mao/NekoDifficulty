package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;

public class NightmareMemoryMobSkill extends MobSkill {
    //梦魇记忆
    public NightmareMemoryMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingHurtEvent hurtEvent){
            int scale = dat.getInt(hurtEvent.getSource().type().toString());
            hurtEvent.setAmount(hurtEvent.getAmount() * (1 - scale * 0.05F));
            if (scale < 20) {
                dat.putInt(hurtEvent.getSource().type().toString(), scale + 1);
            }
        }
    }
}
