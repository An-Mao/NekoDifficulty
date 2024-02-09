package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.Event;

public class InfectionMobSkill extends MobSkill {
    //感染
    public InfectionMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingDamageEvent damageEvent && damageEvent.getSource().getEntity() instanceof ServerPlayer serverPlayer){
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.POISON,200,1));
        }
    }
}
