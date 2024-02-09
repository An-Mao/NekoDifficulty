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

public class FrostbiteMobSkill extends MobSkill {
    //冰霜结界
    public FrostbiteMobSkill(String id) {
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
                        for (int yOffset = 0; yOffset <= 2; yOffset++) {
                            for (int zOffset = -1; zOffset <= 1; zOffset++) {
                                BlockPos snowPos = playerPos.offset(xOffset, yOffset, zOffset);
                                if (level.getBlockState(snowPos).isAir()) {
                                    level.setBlockAndUpdate(snowPos, Blocks.POWDER_SNOW.defaultBlockState());
                                }
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
