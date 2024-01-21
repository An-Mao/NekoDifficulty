package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.lib.AttributeHelper;
import anmao.mc.ned.lib._Math;
import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SearingShieldSkill extends Skill {
    //灼热之盾
    public SearingShieldSkill() {
        super("SearingShield");
    }
    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_HURT){
            if (!eventData.getDamageSource().typeHolder().is(DamageTypes.IN_FIRE.location())){
                if (eventData.getSecondaryEntity() instanceof ServerPlayer serverPlayer){
                    int r = _Math.getIntRandomNumber(1,3);
                    if (r == 1) {
                        AttributeHelper.setTempAttribute(serverPlayer, Attributes.ATTACK_DAMAGE,ATTRIBUTE_SKILL_ATTACK_DAMAGE,-0.7D, AttributeModifier.Operation.ADDITION,200);
                    }else if (r == 2){
                        AttributeHelper.setTempAttribute(serverPlayer,Attributes.ATTACK_SPEED,ATTRIBUTE_SKILL_ATTACK_SPEED,-0.3D,AttributeModifier.Operation.ADDITION,200);
                    }else {
                        serverPlayer.hurt(eventData.getMainEntity().damageSources().inFire(),eventData.getAmount() * 0.5F);
                    }
                }
                eventData.setAmount(eventData.getAmount() * 0.2F);
                eventData.setUpdateType(EVENT_UP_TYPE_AMOUNT);
            }
        }
    }
}
