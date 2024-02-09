package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.lib.AttributeHelper;
import anmao.mc.ned.lib._Math;
import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;

public class ThunderthornShieldMobSkill extends MobSkill {
    //雷棘之盾
    public ThunderthornShieldMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingHurtEvent hurtEvent){
            if (!hurtEvent.getSource().typeHolder().is(DamageTypes.LIGHTNING_BOLT.location())){
                if (hurtEvent.getSource().getEntity() instanceof ServerPlayer serverPlayer){
                    int r = _Math.getIntRandomNumber(1,3);
                    if (r == 1) {
                        AttributeHelper.setTempAttribute(serverPlayer, Attributes.ATTACK_DAMAGE,ATTRIBUTE_SKILL_ATTACK_DAMAGE,-0.7D, AttributeModifier.Operation.ADDITION,200);
                    }else if (r == 2){
                        AttributeHelper.setTempAttribute(serverPlayer,Attributes.ATTACK_SPEED,ATTRIBUTE_SKILL_ATTACK_SPEED,-0.3D,AttributeModifier.Operation.ADDITION,200);
                    }else {
                        serverPlayer.hurt(hurtEvent.getEntity().damageSources().lightningBolt(),hurtEvent.getAmount() * 0.5F);
                    }
                }
                hurtEvent.setAmount(hurtEvent.getAmount() * 0.2F);
            }
        }
    }
}
