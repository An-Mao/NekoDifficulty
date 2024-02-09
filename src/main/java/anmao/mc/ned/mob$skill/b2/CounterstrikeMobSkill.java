package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.Event;

public class CounterstrikeMobSkill extends MobSkill {
    //反刺
    public CounterstrikeMobSkill(String id) {
        super(id);
    }
    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingDamageEvent damageEvent && damageEvent.getSource().getEntity() instanceof ServerPlayer serverPlayer){
            serverPlayer.hurt(damageEvent.getEntity().damageSources().fellOutOfWorld(),damageEvent.getAmount() * 0.3F);
        }
    }
}
