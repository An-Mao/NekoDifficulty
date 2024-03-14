package anmao.mc.ned.mob$skill.b2;

import anmao.mc.amlib.attribute.AttributeHelper;
import anmao.mc.ned.attribute.NEDAttributes;
import anmao.mc.ned.lib.EntityHelper;
import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.Event;

import java.util.List;

public class SymbiosisMobSkill extends MobSkill {
    //共生
    public SymbiosisMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof MobSpawnEvent spawnEvent){
            if (!dat.getBoolean("NotFirst")){
                dat.putBoolean("NotFirst",true);
                List<? extends LivingEntity> entities = EntityHelper.getLivingEntities(spawnEvent.getEntity());
                double s = entities.size();
                double a = 1 + s * 0.3;
                AttributeHelper.setAttribute(spawnEvent.getEntity(), Attributes.ATTACK_DAMAGE,ATTRIBUTE_SKILL_ATTACK_DAMAGE,a, AttributeModifier.Operation.MULTIPLY_TOTAL);
                a = s * 10;
                AttributeHelper.setAttribute(spawnEvent.getEntity(), NEDAttributes.hurtDown,ATTRIBUTE_SKILL_HURT_DOWN,a, AttributeModifier.Operation.ADDITION);
            }
        }
    }
}
