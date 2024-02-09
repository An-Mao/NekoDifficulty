package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.mob$skill.MobSkill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.Event;

public class StealMobSkill extends MobSkill {
    //窃取
    public StealMobSkill(String id) {
        super(id);
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
        if (event instanceof LivingDamageEvent damageEvent){
            if (damageEvent.getSource().getEntity() instanceof ServerPlayer serverPlayer){
                ItemStack itemStack = serverPlayer.getMainHandItem();
                if (itemStack != ItemStack.EMPTY && itemStack.isDamageableItem()){
                    itemStack.setDamageValue((int) (itemStack.getDamageValue()+itemStack.getMaxDamage()*0.1));
                }
            }
        }
    }
}

