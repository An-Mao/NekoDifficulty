package anmao.mc.ned.skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.lib.AttributeHelper;
import anmao.mc.ned.lib._Math;
import anmao.mc.ned.skill.Skill;
import anmao.mc.ned.skill.SkillCDT;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ColdShieldSkill extends Skill {
    //凝寒之盾
    //大幅度减少冰以外的伤害
    //攻击力降低，攻速降低，对玩家造成伤害
    public ColdShieldSkill() {
        super("ColdShield");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_HURT){
            if (!eventData.getDamageSource().typeHolder().is(DamageTypes.FREEZE.location())){
                if (eventData.getSecondaryEntity() instanceof ServerPlayer serverPlayer){
                    int r = _Math.getIntRandomNumber(1,3);
                    if (r == 1) {
                        AttributeHelper.setTempAttribute(serverPlayer,Attributes.ATTACK_DAMAGE,ATTRIBUTE_SKILL_ATTACK_DAMAGE,-0.7D,AttributeModifier.Operation.ADDITION,200);
                    }else if (r == 2){
                        AttributeHelper.setTempAttribute(serverPlayer,Attributes.ATTACK_SPEED,ATTRIBUTE_SKILL_ATTACK_SPEED,-0.3D,AttributeModifier.Operation.ADDITION,200);
                    }else {
                        serverPlayer.hurt(eventData.getMainEntity().damageSources().freeze(),eventData.getAmount() * 0.5F);
                    }
                }
                eventData.setAmount(eventData.getAmount() * 0.2F);
                eventData.setUpdateType(EVENT_UP_TYPE_AMOUNT);
            }
        }
    }
}
