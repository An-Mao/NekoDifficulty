package anmao.mc.ned.mob$skill;

import anmao.mc.ned.NED;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public class MobSkillRegister {
    public static final DeferredRegister<MobSkill> MOB_SKILL = DeferredRegister.create(new ResourceLocation(NED.MOD_ID, "mob_skill"), NED.MOD_ID);
    public static final Supplier<IForgeRegistry<MobSkill>> REGISTRY = MOB_SKILL.makeRegistry(RegistryBuilder::new);


    public static void register(IEventBus eventBus){
        MOB_SKILL.register(eventBus);
    }
}
