package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;

public class BombMobSkill extends MobSkill {
    //投弹
    public BombMobSkill() {
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingEvent.LivingTickEvent livingTickEvent) {
            int t = dat.getInt("tick");
            if (t > 100) {
                dat.putInt("tick",0);
                LivingEntity livingEntity = livingTickEvent.getEntity();
                Player player = livingEntity.level().getNearestPlayer(livingEntity, 32.0);
                if (player != null) {
                    double livingEntityX = livingEntity.getX();
                    double livingEntityY = livingEntity.getY() + livingEntity.getBbHeight() / 2;
                    double livingEntityZ = livingEntity.getZ();

                    Vec3 playerPos = player.position();

                    double motionX = playerPos.x - livingEntityX;
                    double motionY = playerPos.y - livingEntityY;
                    double motionZ = playerPos.z - livingEntityZ;

                    LargeFireball largeFireball = new LargeFireball(livingEntity.level(), livingEntity, motionX, motionY, motionZ, 3);

                    largeFireball.setPos(livingEntityX, livingEntityY , livingEntityZ);

                    largeFireball.setDeltaMovement(motionX, motionY, motionZ);

                    player.level().addFreshEntity(largeFireball);
                }
            }else {
                dat.putInt("tick",t+1);
            }
        }
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {

    }
}
