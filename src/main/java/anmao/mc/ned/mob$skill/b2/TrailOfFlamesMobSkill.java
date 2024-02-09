package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;

public class TrailOfFlamesMobSkill extends MobSkill {
    //烈焰轨迹
    public TrailOfFlamesMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingHurtEvent hurtEvent){
            if (!hurtEvent.getSource().typeHolder().is(DamageTypes.IN_FIRE.location())){
                hurtEvent.setCanceled(true);
            }
        } else if (event instanceof LivingEvent.LivingTickEvent t) {
            Level level = t.getEntity().level();
            if (!level.isClientSide()) {
                BlockPos pos = t.getEntity().getOnPos();
                if (level.getBlockState(pos).isAir()) {
                    level.setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
                }
            }
        }
    }
}
