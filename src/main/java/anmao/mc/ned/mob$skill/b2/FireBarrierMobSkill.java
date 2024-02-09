package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;

public class FireBarrierMobSkill extends MobSkill {
    //火焰结界
    public FireBarrierMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingEvent.LivingTickEvent tickEvent){
            int tick = dat.getInt("tick");
            if (tick > 200) {
                dat.putInt("tick",0);
                LivingEntity livingEntity = tickEvent.getEntity();
                Player player = livingEntity.level().getNearestPlayer(livingEntity, 15);
                if (player != null) {
                    Level level = player.level();
                    BlockPos playerPos = player.blockPosition();
                    for (int xOffset = -1; xOffset <= 1; xOffset++) {
                        for (int zOffset = -1; zOffset <= 1; zOffset++) {
                            BlockPos firePos = playerPos.offset(xOffset, 0, zOffset);
                            if (level.getBlockState(firePos).isAir()) {
                                level.setBlockAndUpdate(firePos, Blocks.FIRE.defaultBlockState());
                            }
                        }
                    }
                }
            }else {
                dat.putInt("tick",tick+1);
            }
        }
    }
}
