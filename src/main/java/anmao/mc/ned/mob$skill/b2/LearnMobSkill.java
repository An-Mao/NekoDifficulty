package anmao.mc.ned.mob$skill.b2;


import anmao.mc.amlib.attribute.AttributeHelper;
import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;

public class LearnMobSkill extends MobSkill {
    //学习
    public LearnMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingEvent.LivingTickEvent tickEvent){
            int t = dat.getInt("tick");
            if (t > 600){
                dat.putInt("tick",0);
                int lvl = dat.getInt("lvl");
                if (lvl < 10){
                    dat.putInt("lvl",lvl +1);
                    LivingEntity livingEntity = tickEvent.getEntity();
                    AttributeHelper.setAttribute(livingEntity, Attributes.ATTACK_DAMAGE,ATTRIBUTE_SKILL_ATTACK_DAMAGE,1.0D, AttributeModifier.Operation.ADDITION);
                    AttributeHelper.setAttribute(livingEntity, Attributes.MOVEMENT_SPEED,ATTRIBUTE_SKILL_MOVE_SPEED,0.1D, AttributeModifier.Operation.ADDITION);
                }
            }else {
                dat.putInt("tick",t + 1);
            }
        }
    }
}
