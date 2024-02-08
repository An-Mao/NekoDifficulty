package anmao.mc.ned.skill.b2;

import anmao.mc.ned.skill.Skill;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class FrostbiteSkill extends Skill {
    //冰霜结界
    public FrostbiteSkill() {
        super("frostbite");
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
            skillData.putInt("tick",tick+1);
        }
    }
}
