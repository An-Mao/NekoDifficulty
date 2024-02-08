package anmao.mc.ned.skill;

import anmao.mc.ned.NED;
import anmao.mc.ned.debug._Log;
import anmao.mc.ned.skill.b2.*;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.ArrayList;
import java.util.List;

public class Skills {
    private static final Skills instance = new Skills();
    private ImmutableMap<String, Supplier<Skill>> skills;
    private ImmutableMap<String, Supplier<ResourceLocation>> skillTexture;
    public List<String> skillKeys = new ArrayList<>();
    private final SkillsData skillsData = new SkillsData();
    private final ImmutableMap.Builder<String, Supplier<Skill>> builder = ImmutableMap.builder();
    private final ImmutableMap.Builder<String, Supplier<ResourceLocation>> textureBuilder = ImmutableMap.builder();
    private final ResourceLocation icon = new ResourceLocation(NED.MOD_ID,"textures/skill/null_skill.png");
    public Skills(){}
    public void loadSkillConfig(ResourceManager resourceManager){
        skillsData.loadJSONFile(resourceManager);
    }
    private void register(String name,Supplier<Skill> skillSupplier){
        //skillsData.getData(name).get("enable").getAsBoolean()
        if (true) {
            String id = getSkillId(name);
            builder.put(id, skillSupplier);
            textureBuilder.put(id, () -> new ResourceLocation(NED.MOD_ID, getSkillTextureId(name)));
        }else {
            _Log.LOGGER.debug("Skill >>> skip skill ::"+name);
        }
    }
    public void registers(){
        register("alone", AloneSkill::new);
        register("boom", BombSkill::new);
        register("chao", ChaoSkill::new);
        register("chase_away", ChaseAwaySkill::new);
        register("cohesion", CohesionSkill::new);
        register("cold_shield", ColdShieldSkill::new);
        register("comprehend", ComprehendSkill::new);
        register("conductivity", ConductivitySkill::new);
        register("corrosion", CorrosionSkill::new);
        register("counterstrike", CounterstrikeSkill::new);
        register("crystallography", CrystallographySkill::new);
        register("curse", CurseSkill::new);
        register("dark_poison_shield", DarkPoisonShieldSkill::new);
        register("dark_position", DarkPositionSkill::new);
        register("dexterity", DexteritySkill::new);
        register("disappear", DisappearSkill::new);
        register("dislike", DislikeSkill::new);
        register("energy_shield", EnergyShieldSkill::new);
        register("fast", FastSkill::new);
        register("favorite", FavoriteSkill::new);
        register("fetters", FettersSkill::new);
        register("fire_barrier", FireBarrierSkill::new);
        register("frostbite", FrostbiteSkill::new);
        register("frost_trail", FrostTrailSkill::new);
        register("high_frequency_boundary", HighFrequencyBoundarySkill::new);
        register("ignite", IgniteSkill::new);
        register("imperishable", ImperishableSkill::new);
        register("infection", InfectionSkill::new);
        register("interruption_of_growth", InterruptionOfGrowthSkill::new);
        register("intrinsic_quality", IntrinsicQualitySkill::new);
        register("ironclad", IroncladSkill::new);
        register("leaders", LeadersSkill::new);
        register("learn", LearnSkill::new);
        register("left_guardian", LeftGuardianSkill::new);
        register("life_absorbing", LifeAbsorbingSkill::new);
        register("low_frequency_boundary", LowFrequencyBoundarySkill::new);
        register("mirroring", MirroringSkill::new);
        register("newborn", NewbornSkill::new);
        register("nightmare_memory", NightmareMemorySkill::new);
        register("order", OrderSkill::new);
        register("poisoned_egg", PoisonedEggSkill::new);
        register("poison_mist", PoisonMistSkill::new);
        register("powerful", PowerfulSkill::new);
        register("proliferate", ProliferateSkill::new);
        register("rainbow_position", RainbowPositionSkill::new);
        register("rebirth", RebirthSkill::new);
        register("recover", RecoverSkill::new);
        register("reincarnation", ReincarnationSkill::new);
        register("repulse", RepulseSkill::new);
        register("resistant", ResistantSkill::new);
        register("reverse_entropy", ReverseEntropySkill::new);
        register("right_blessing", RightBlessingSkill::new);
        register("searing_shield", SearingShieldSkill::new);
        register("sense_of_competition", SenseOfCompetitionSkill::new);
        register("shield_of_chaos", ShieldOfChaosSkill::new);
        register("shield_of_purity", ShieldOfPuritySkill::new);
        register("steadfastness", SteadfastnessSkill::new);
        register("steal", StealSkill::new);
        register("subtotal", SubtotalSkill::new);
        register("summon", SummonSkill::new);
        register("swirl", SwirlSkill::new);
        register("symbiosis", SymbiosisSkill::new);
        register("teleportation", TeleportationSkill::new);
        register("tentacle", TentacleSkill::new);
        register("thunderthorn_shield", ThunderthornShieldSkill::new);
        register("timid", TimidSkill::new);
        register("trail_of_flames", TrailOfFlamesSkill::new);

        skills = builder.build();
        skillTexture = textureBuilder.build();
        skillKeys = skills.keySet().asList();
    }
    public Skill getSkill(String name){
        Supplier<Skill> skill = skills.get(name);
        if (skill != null){
            return skill.get();
        }
        _Log.LOGGER.error("Skills::getSkill >>> The value has null with key =>" + name);
        return null;
    }
    public Component getComponent(String s){
        Skill skill = getSkill(s);
        if (skill != null) {
            return skill.GetName();
        }
        return Component.literal("error");
    }
    private String getSkillId(String name){
        return "skill.ned."+ name;
    }
    private String getSkillTextureId(String name){
        return "textures/skill/"+name+".png";
    }
    public ResourceLocation getSkillTexture(String name){
        Supplier<ResourceLocation> texture = skillTexture.get(name);
        if (texture != null){
            return texture.get();
        }
        _Log.LOGGER.error("Skills::getSkillTexture >>> The value has null with key =>" + name);
        return icon;
    }
    public static Skills getInstance() {
        return instance;
    }
}
