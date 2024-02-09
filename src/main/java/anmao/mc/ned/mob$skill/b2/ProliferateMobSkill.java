package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.attribute.NEDAttributes;
import anmao.mc.ned.lib.AttributeHelper;
import anmao.mc.ned.lib.EntityHelper;
import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.Event;

import java.util.List;

public class ProliferateMobSkill extends MobSkill {
    //扩散
    //死亡后为附近同类生物提供攻击，移速，减伤
    public ProliferateMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingDeathEvent deathEvent){
            List<? extends LivingEntity> entities = EntityHelper.getLivingEntities(deathEvent.getEntity());
            for (LivingEntity livingEntity :entities){
                AttributeHelper.setAttribute(livingEntity, Attributes.ATTACK_DAMAGE,ATTRIBUTE_SKILL_ATTACK_DAMAGE,1.0D, AttributeModifier.Operation.ADDITION);
                AttributeHelper.setAttribute(livingEntity, Attributes.MOVEMENT_SPEED,ATTRIBUTE_SKILL_MOVE_SPEED,0.1D, AttributeModifier.Operation.ADDITION);
                AttributeHelper.setAttribute(livingEntity, NEDAttributes.hurtDown,ATTRIBUTE_SKILL_HURT_DOWN,5D, AttributeModifier.Operation.ADDITION);
            }
        }
    }
}
