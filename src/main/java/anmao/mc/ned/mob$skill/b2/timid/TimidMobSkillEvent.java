package anmao.mc.ned.mob$skill.b2.timid;

import anmao.mc.ned.NED;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NED.MOD_ID)
public class TimidMobSkillEvent {
    @SubscribeEvent
    public static void onPlayerUseItem(LivingEntityUseItemEvent event){
        if (event.getEntity() instanceof ServerPlayer serverPlayer){
            ItemStack itemStack = event.getItem();
            if (itemStack != ItemStack.EMPTY){
                if (itemStack.getTag() != null) {
                    long a = itemStack.getTag().getLong("disarm");
                    if (a > 0 && serverPlayer.level().getGameTime() - a < 100){
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}
