package anmao.mc.ned.skill.b2;

import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class TimidSkill extends Skill {
    //胆小
    public TimidSkill() {
        super("timid");
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        int t = skillData.getInt("tick");
        if (t > 200){
            skillData.putInt("tick",0);
            Player player = livingEntity.level().getNearestPlayer(livingEntity,10);
            if (player != null){
                ItemStack stack = player.getMainHandItem();
                if (stack != ItemStack.EMPTY){
                    if (stack.getTag() != null) {
                        stack.getTag().putLong("disarm",player.level().getGameTime());
                    }
                }
            }
        }else {
            skillData.putInt("tick",t + 1);
        }
    }
}
