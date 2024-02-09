package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.Event;

public class ConductivityMobSkill extends MobSkill {
    //导电体质
    public ConductivityMobSkill(String id) {
        super(id);
    }
    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingDamageEvent damageEvent && damageEvent.getSource().getEntity() instanceof ServerPlayer serverPlayer){
            //受到攻击召唤闪电
            LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(serverPlayer.level());
            if (lightningBolt != null) {
                lightningBolt.moveTo(serverPlayer.getX(),serverPlayer.getY(),serverPlayer.getZ());
                serverPlayer.level().addFreshEntity(lightningBolt);
            }
        }
    }
}
