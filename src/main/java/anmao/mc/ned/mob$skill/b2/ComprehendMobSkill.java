package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.Event;

public class ComprehendMobSkill extends MobSkill {
    //领悟
    //生命低于某个值时，攻击力和移速大幅提升
    public ComprehendMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingDamageEvent damageEvent){
            if (damageEvent.getEntity().getHealth() - damageEvent.getAmount() < damageEvent.getEntity().getMaxHealth() * 0.5){
                AttributeInstance att = damageEvent.getEntity().getAttribute(Attributes.ATTACK_DAMAGE);
                if (att != null) {
                    att.addPermanentModifier(new AttributeModifier("mob_skill.ned.attack.damage", 2D, AttributeModifier.Operation.MULTIPLY_TOTAL));
                }
                att = damageEvent.getEntity().getAttribute(Attributes.MOVEMENT_SPEED);
                if (att != null) {
                    att.addPermanentModifier(new AttributeModifier("mob_skill.ned.move.speed", 2D, AttributeModifier.Operation.MULTIPLY_TOTAL));
                }
            }
        }
    }
}
