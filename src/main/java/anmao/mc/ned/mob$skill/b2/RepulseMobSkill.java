package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.lib.AttributeHelper;
import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.Event;

public class RepulseMobSkill extends MobSkill {
    //击退
    //增加击退距离
    public RepulseMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof MobSpawnEvent spawnEvent){
            if (!dat.getBoolean("notFirstSpawn")) {
                dat.putBoolean("notFirstSpawn",true);
                AttributeHelper.setAttribute(spawnEvent.getEntity(), Attributes.KNOCKBACK_RESISTANCE, ATTRIBUTE_SKILL_KNOCK_BACK, 2D, AttributeModifier.Operation.ADDITION);
            }
        }
    }
}
