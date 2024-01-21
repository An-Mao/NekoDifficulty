package anmao.mc.ned.skill.b2;

import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class ChaseAwaySkill extends Skill {
    //驱赶
    //将角色推开
    public ChaseAwaySkill() {
        super("chase_away");
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        double pushStrength = 1.0;
        Player player = livingEntity.level().getNearestPlayer(livingEntity, 10.0);
        if (player != null && !livingEntity.noPhysics && !player.noPhysics) {
            //System.out.println("push player");
            double dX = player.getX() - livingEntity.getX();
            double dZ = player.getZ() - livingEntity.getZ();
            double pushX = dX * pushStrength;
            double pushZ = dZ * pushStrength;
            player.absMoveTo(player.getX() + pushX, player.getY(), player.getZ() + pushZ);
        }
    }
}
