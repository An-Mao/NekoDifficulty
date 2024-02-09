package anmao.mc.ned.mob$skill.b2.timid;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;

public class TimidMobSkill extends MobSkill {
    //胆小
    public TimidMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingEvent.LivingTickEvent tickEvent){
            int t = dat.getInt("tick");
            if (t > 200){
                dat.putInt("tick",0);
                LivingEntity livingEntity = tickEvent.getEntity();
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
                dat.putInt("tick",t + 1);
            }
        }
    }
}
