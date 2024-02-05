package anmao.mc.ned.skill;

import anmao.mc.ned.skill.b2.*;
import com.google.common.collect.ImmutableMap;
import net.minecraft.network.chat.Component;

import java.util.ServiceLoader;

public class Skills {
    private static final Skills instance = new Skills();
    public ImmutableMap<String, Skill> SKILLS;
    public String[] SKILL_KEYS;
    private final ImmutableMap.Builder<String, Skill> builder = ImmutableMap.builder();
    public Skills(){
        autoRegister();
        //registers();
    }

    private void autoRegister() {
        ImmutableMap.Builder<String, Skill> builder = ImmutableMap.builder();
        ServiceLoader<Skill> skillLoader = ServiceLoader.load(Skill.class);
        for (Skill skill : skillLoader) {
            builder.put(skill.GetID(),skill);
        }
        SKILLS = builder.build();
        SKILL_KEYS = SKILLS.keySet().toArray(new String[0]);
    }
    private void registers(){
        _reg(new AloneSkill());
        _reg(new BombSkill());
        _reg(new ChaoSkill());
        _reg(new ChaseAwaySkill());
        _reg(new CohesionSkill());
        _reg(new ColdShieldSkill());
        _reg(new ComprehendSkill());
        _reg(new ConductivitySkill());
        _reg(new CorrosionSkill());
        _reg(new CounterstrikeSkill());
        _reg(new CrystallographySkill());
        _reg(new CurseSkill());
        _reg(new DarkPoisonShieldSkill());
        _reg(new DarkPositionSkill());
        _reg(new DexteritySkill());
        _reg(new DisappearSkill());
        _reg(new DislikeSkill());
        _reg(new EnergyShieldSkill());
        _reg(new FastSkill());
        _reg(new FavoriteSkill());
        _reg(new FettersSkill());
        _reg(new FireBarrierSkill());
        _reg(new FrostbiteSkill());
        _reg(new FrostTrailSkill());
        _reg(new HighFrequencyBoundarySkill());
        _reg(new IgniteSkill());
        _reg(new ImperishableSkill());
        _reg(new InfectionSkill());
        _reg(new InterruptionOfGrowthSkill());
        _reg(new IntrinsicQualitySkill());
        _reg(new IroncladSkill());
        _reg(new LeadersSkill());
        _reg(new LearnSkill());
        _reg(new LeftGuardianSkill());
        _reg(new LifeAbsorbingSkill());
        _reg(new LowFrequencyBoundarySkill());
        _reg(new MirroringSkill());
        _reg(new NewbornSkill());
        _reg(new NightmareMemorySkill());
        _reg(new OrderSkill());
        _reg(new PoisonedEggSkill());
        _reg(new PoisonMistSkill());
        _reg(new PowerfulSkill());
        _reg(new ProliferateSkill());
        _reg(new RainbowPositionSkill());
        _reg(new RebirthSkill());
        _reg(new RecoverSkill());
        _reg(new ReincarnationSkill());
        _reg(new RepulseSkill());
        _reg(new ResistantSkill());
        _reg(new ReverseEntropySkill());
        _reg(new RightBlessingSkill());
        _reg(new SearingShieldSkill());
        _reg(new SenseOfCompetitionSkill());
        _reg(new ShieldOfChaosSkill());
        _reg(new ShieldOfPuritySkill());
        _reg(new SteadfastnessSkill());
        _reg(new StealSkill());
        _reg(new SubtotalSkill());
        _reg(new SummonSkill());
        _reg(new SwirlSkill());
        _reg(new SymbiosisSkill());
        _reg(new TeleportationSkill());
        _reg(new TentacleSkill());
        _reg(new ThunderthornShieldSkill());
        _reg(new TimidSkill());
        _reg(new TrailOfFlamesSkill());
        SKILLS = builder.build();
    }
    private void _reg(Skill skill){
        builder.put(skill.GetID(),skill);
    }
    public Skill getSkill(String id){
        return SKILLS.get(id);
    }
    public Component getComponent(String s){
        //System.out.println("SKILLS"+SKILLS);
        Skill skill = getSkill(s);
        if (skill != null) {
            return skill.GetName();
        }
        return Component.literal("error");
    }
    public static Skills getInstance() {
        return instance;
    }
}
