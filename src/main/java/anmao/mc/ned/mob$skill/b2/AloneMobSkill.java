package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.attribute.NEDAttributes;
import anmao.mc.ned.lib.AttributeHelper;
import anmao.mc.ned.lib.EntityHelper;
import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;

import java.util.List;

public class AloneMobSkill extends MobSkill {
    //孤独
    //怪物越少，攻击力，移速，减伤提升越多
    public AloneMobSkill() {
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingEvent.LivingTickEvent livingTickEvent){
            int tick = dat.getInt("tick");
            if (tick > 200){
                dat.putInt("tick",0);
                LivingEntity entity = livingTickEvent.getEntity();
                List<? extends LivingEntity> entities = EntityHelper.getLivingEntities(entity);
                double s = 1d / entities.size();
                double a = 1 + s * 5;
                AttributeHelper.setTempAttribute(entity, Attributes.ATTACK_DAMAGE,ATTRIBUTE_SKILL_ATTACK_DAMAGE,a, AttributeModifier.Operation.MULTIPLY_TOTAL,180);
                a = 1 + s * 2;
                AttributeHelper.setTempAttribute(entity, Attributes.MOVEMENT_SPEED,ATTRIBUTE_SKILL_MOVE_SPEED,a, AttributeModifier.Operation.MULTIPLY_TOTAL,180);
                a = s * 50;
                AttributeHelper.setTempAttribute(entity, NEDAttributes.hurtDown,ATTRIBUTE_SKILL_HURT_DOWN,a, AttributeModifier.Operation.ADDITION,180);
            }else {
                dat.putInt("tick",tick + 1);
            }
        }
    }
}
