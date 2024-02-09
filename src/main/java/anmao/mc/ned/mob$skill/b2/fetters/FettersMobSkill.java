package anmao.mc.ned.mob$skill.b2.fetters;

import anmao.mc.ned.effect.EffectRegister;
import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;

public class FettersMobSkill extends MobSkill {
    //禁锢
    public FettersMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingEvent.LivingTickEvent tickEvent){
            int tick = dat.getInt("tick");
            if (tick > 200){
                dat.putInt("tick",0);
                LivingEntity livingEntity = tickEvent.getEntity();
                Player player = livingEntity.level().getNearestPlayer(livingEntity,15);
                if (player != null) {
                    player.getPersistentData().putDouble("FettersX",player.getX());
                    player.getPersistentData().putDouble("FettersY",player.getY());
                    player.getPersistentData().putDouble("FettersZ",player.getZ());
                    player.addEffect(new MobEffectInstance(EffectRegister.FETTERS.get(),100,1));
                }
            }else {
                dat.putInt("tick",tick+1);
            }
        }
    }
}
