package anmao.mc.ned.effect;

import anmao.mc.ned.NED;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectRegister {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, NED.MOD_ID);
    public static final RegistryObject<MobEffect> FETTERS = EFFECTS.register("fetters", FettersEffect::new);
    public static void register(IEventBus eventBus){
        EFFECTS.register(eventBus);
    }
}
