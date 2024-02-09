package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.lib.AttributeHelper;
import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;

public class SenseOfCompetitionMobSkill extends MobSkill {
    //竞争意识
    public SenseOfCompetitionMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingEvent.LivingTickEvent tickEvent){
            int t = dat.getInt("tick");
            if (t > 300){
                dat.putInt("tick",0);
                LivingEntity livingEntity= tickEvent.getEntity();
                Player player = livingEntity.level().getNearestPlayer(livingEntity,10);
                if (player != null) {
                    AttributeHelper.setTempAttribute(livingEntity, Attributes.MOVEMENT_SPEED, ATTRIBUTE_SKILL_MOVE_SPEED, player.getAttribute(Attributes.MOVEMENT_SPEED).getValue() / 2, AttributeModifier.Operation.ADDITION, 300);
                }
            }else {
                dat.putInt("tick", t + 1);
            }
        }
    }
}
