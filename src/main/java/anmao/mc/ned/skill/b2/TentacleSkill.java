package anmao.mc.ned.skill.b2;

import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class TentacleSkill extends Skill {
    //触手
    public TentacleSkill() {
        super("tentacle");
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
    }
}
