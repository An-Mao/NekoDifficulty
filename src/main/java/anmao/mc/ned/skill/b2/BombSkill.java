package anmao.mc.ned.skill.b2;

import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.phys.Vec3;

public class BombSkill extends Skill {
    //投弹
    public BombSkill() {
        super("boom");
    }
    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        int t = skillData.getInt("tick");
        if (t > 100) {
            skillData.putInt("tick",0);
            Player player = livingEntity.level().getNearestPlayer(livingEntity, 32.0);
            if (player != null) {
                double livingEntityX = livingEntity.getX();
                double livingEntityY = livingEntity.getY() + livingEntity.getBbHeight() / 2;
                double livingEntityZ = livingEntity.getZ();

                Vec3 playerPos = player.position();

                double motionX = playerPos.x - livingEntityX;
                double motionY = playerPos.y - livingEntityY;
                double motionZ = playerPos.z - livingEntityZ;

                LargeFireball largeFireball = new LargeFireball(livingEntity.level(), livingEntity, motionX, motionY, motionZ, 5);

                largeFireball.setPos(livingEntityX, livingEntityY, livingEntityZ);

                largeFireball.setDeltaMovement(motionX, motionY, motionZ);

                player.level().addFreshEntity(largeFireball);
            }
        }else {
            skillData.putInt("tick",t+1);
        }
    }
}
