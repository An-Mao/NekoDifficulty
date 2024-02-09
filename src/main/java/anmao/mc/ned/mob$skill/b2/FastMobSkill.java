package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.lib.AttributeHelper;
import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.Event;

public class FastMobSkill extends MobSkill {
    //快速
    //移速大幅上升
    public FastMobSkill(String id) {
        super(id);
        //reg();
    }
    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof MobSpawnEvent mobSpawnEvent){
            if (!dat.getBoolean("NotFirst") && mobSpawnEvent.getEntity() != null){
                dat.putBoolean("NotFirst",true);
                AttributeHelper.setAttribute(mobSpawnEvent.getEntity(),Attributes.MOVEMENT_SPEED,ATTRIBUTE_SKILL_MOVE_SPEED,0.5D, AttributeModifier.Operation.ADDITION);
            }
        }
    }
}
