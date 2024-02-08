package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.skill.Skill;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class FireBarrierSkill extends Skill {
    //火焰结界
    public FireBarrierSkill() {
        super("fire_barrier");
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        int tick = skillData.getInt("tick");
        if (tick > 200) {
            skillData.putInt("tick",0);
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
                /*
                for (int xOffset = -1; xOffset <= 1; xOffset++) {
                    for (int yOffset = -1; yOffset <= 1; yOffset++) {
                        for (int zOffset = -1; zOffset <= 1; zOffset++) {
                            BlockPos flamePos = playerPos.offset(xOffset, yOffset, zOffset);
                            if (level.isEmptyBlock(flamePos.above())) {
                                level.setBlockAndUpdate(flamePos, Blocks.FIRE.defaultBlockState());
                            }
                        }
                    }
                }
                 */
            }
        }else {
            skillData.putInt("tick",tick+1);
        }
    }
}
