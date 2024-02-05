package anmao.mc.ned.attribute;

import anmao.mc.ned.NED;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AttributeReg {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, NED.MOD_ID);
    public static final RegistryObject<Attribute> HURT_UP = ATTRIBUTES.register("hurt_up",()->new RangedAttribute("attribute.ned.hurt.up",0,0,100).setSyncable(true));
    public static final RegistryObject<Attribute> HURT_DOWN = ATTRIBUTES.register("hurt_down",()->new RangedAttribute("attribute.ned.hurt.down",0,0,100).setSyncable(true));
    public static void register(IEventBus eventBus){
        ATTRIBUTES.register(eventBus);
    }
}
