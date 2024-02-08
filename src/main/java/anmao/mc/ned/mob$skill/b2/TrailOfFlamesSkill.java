package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.datatype.EventData;
import anmao.mc.ned.datatype.EventType;
import anmao.mc.ned.skill.Skill;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class TrailOfFlamesSkill extends Skill {
    //烈焰轨迹
    public TrailOfFlamesSkill() {
        super("trail_of_flames");
    }

    @Override
    public void Event(EventData eventData, CompoundTag skillData) {
        if (eventData.getEventType() == EventType.EVENT_HURT){
            if (!eventData.getDamageSource().typeHolder().is(DamageTypes.IN_FIRE.location())){
                eventData.setCancel(true);
                eventData.setUpdateType(EVENT_UP_TYPE_CANCEL);
            }
        }
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        Level level = livingEntity.level();
        if (!level.isClientSide()) {
            BlockPos pos = livingEntity.getOnPos();
            if (level.getBlockState(pos).isAir()) {
                level.setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
            }
        }
    }
}
