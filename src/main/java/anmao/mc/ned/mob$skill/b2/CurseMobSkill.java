package anmao.mc.ned.mob$skill.b2;

import anmao.mc.amlib.attribute.AttributeHelper;
import anmao.mc.ned.attribute.NEDAttributes;
import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.Event;

public class CurseMobSkill extends MobSkill {
    //诅咒
    //降低移速，攻击力，减伤
    public CurseMobSkill(String id) {
        super(id);
    }
    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingDeathEvent deathEvent && deathEvent.getSource().getEntity() instanceof ServerPlayer serverPlayer){
            AttributeHelper.setTempAttribute(serverPlayer,Attributes.MOVEMENT_SPEED,ATTRIBUTE_SKILL_MOVE_SPEED,-0.1D,AttributeModifier.Operation.ADDITION,200);
            AttributeHelper.setTempAttribute(serverPlayer,Attributes.ATTACK_DAMAGE,ATTRIBUTE_SKILL_ATTACK_DAMAGE,-0.5D,AttributeModifier.Operation.MULTIPLY_TOTAL,200);
            AttributeHelper.setTempAttribute(serverPlayer, NEDAttributes.hurtUp,ATTRIBUTE_SKILL_HURT_UP,5D,AttributeModifier.Operation.ADDITION,200);
        }
    }
}
