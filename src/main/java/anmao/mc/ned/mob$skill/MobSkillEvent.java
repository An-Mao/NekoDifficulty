package anmao.mc.ned.mob$skill;

import anmao.mc.ned.NED;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;

import java.util.function.Supplier;
@Mod.EventBusSubscriber(modid = NED.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobSkillEvent {
    public static Supplier<IForgeRegistry<MobSkill>> registrySupplier = null;
    public void onNewRegistry(NewRegistryEvent event){

        RegistryBuilder<MobSkill> registryBuilder = new RegistryBuilder<>();
        registryBuilder.setName(new ResourceLocation(NED.MOD_ID, "mob_skill")).setDefaultKey(new ResourceLocation(NED.MOD_ID, "mob_skill"));
        registrySupplier = event.create(registryBuilder);
    }
    public static void registerMobSkills(RegisterEvent event) {
        if (event.getRegistryKey().equals(MobSkillRegister.MOB_SKILL.getRegistryKey())){
            //registers();
        }
    }
    public void onMissing(final MissingMappingsEvent event) {
        event.getMappings(ForgeRegistries.Keys.ITEMS, NED.MOD_ID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("mob_skill"))
                .forEach(MissingMappingsEvent.Mapping::ignore);
    }
}