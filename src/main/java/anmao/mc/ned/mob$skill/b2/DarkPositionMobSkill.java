package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;

public class DarkPositionMobSkill extends MobSkill {
    //黑暗立场
    //减少受到的伤害，处于异常时可造成正常伤害
    public DarkPositionMobSkill(String id) {
        super(id);
    }
    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingHurtEvent hurtEvent){
            if (!hasDeBuff(hurtEvent.getEntity())){
                hurtEvent.setAmount(hurtEvent.getAmount() * 0.2F);
            }
        }
    }
    public static boolean hasDeBuff(LivingEntity entity) {
        return entity.getActiveEffects().stream().anyMatch(effect -> !effect.getEffect().isBeneficial());
    }
}
