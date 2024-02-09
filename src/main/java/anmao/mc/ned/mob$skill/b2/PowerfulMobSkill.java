package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.lib.AttributeHelper;
import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.Event;

public class PowerfulMobSkill extends MobSkill {
    //强力
    //攻击力大幅提升
    public PowerfulMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof MobSpawnEvent spawnEvent){
            if (spawnEvent.getEntity() != null){
                AttributeHelper.setAttribute(spawnEvent.getEntity(), Attributes.ATTACK_DAMAGE,ATTRIBUTE_SKILL_ATTACK_DAMAGE,3D, AttributeModifier.Operation.ADDITION);
            }
        }
    }
}
