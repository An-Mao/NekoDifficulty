package anmao.mc.ned.mob$skill.b2;

import anmao.mc.amlib.attribute.AttributeHelper;
import anmao.mc.amlib.math._Random;
import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;

public class ColdShieldMobSkill extends MobSkill {
    //凝寒之盾
    //大幅度减少冰以外的伤害
    //攻击力降低，攻速降低，对玩家造成伤害
    public ColdShieldMobSkill(String id) {
        super(id);
    }
    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingHurtEvent hurtEvent){
            if (!hurtEvent.getSource().typeHolder().is(DamageTypes.FREEZE.location())){
                if (hurtEvent.getSource().getEntity() instanceof ServerPlayer serverPlayer){
                    int r = _Random.getIntRandomNumber(1,3);
                    if (r == 1) {
                        AttributeHelper.setTempAttribute(serverPlayer,Attributes.ATTACK_DAMAGE,ATTRIBUTE_SKILL_ATTACK_DAMAGE,-0.7D,AttributeModifier.Operation.ADDITION,200);
                    }else if (r == 2){
                        AttributeHelper.setTempAttribute(serverPlayer,Attributes.ATTACK_SPEED,ATTRIBUTE_SKILL_ATTACK_SPEED,-0.3D,AttributeModifier.Operation.ADDITION,200);
                    }else {
                        serverPlayer.hurt(hurtEvent.getEntity().damageSources().freeze(),hurtEvent.getAmount() * 0.5F);
                    }
                }
                hurtEvent.setAmount(hurtEvent.getAmount() * 0.2F);
            }
        }
    }
}
