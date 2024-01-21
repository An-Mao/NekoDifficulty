package anmao.mc.ned.skill;

import anmao.mc.ned.skill.b2.*;

import java.util.*;

public class Skills {
    private static final Skills instance = new Skills();
    public HashMap<String,Skill> SKILLS = new HashMap<>();
    public Skills(){
        //autoRegisterSkills();
        registers();
    }
    private void registers(){
        //_reg(new ResistantSkill());
        //_reg(new FastSkill());
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
    }
    private void autoRegisterSkills() {
        ServiceLoader<Skill> skillLoader = ServiceLoader.load(Skill.class);
        for (Skill skill : skillLoader) {
            _reg(skill);
        }
    }
    public void _reg(Skill skill){
        SKILLS.put(skill.GetID(),skill);
    }

    public Skill getSkill(String id){
        return SKILLS.get(id);
    }
    private static <K, V> Map.Entry<K, V> getRandomEntry(Map<K, V> map) {
        java.util.List<Map.Entry<K, V>> entryList = new java.util.ArrayList<>(map.entrySet());
        int randomIndex = new Random().nextInt(entryList.size());
        return entryList.get(randomIndex);
    }
    public List<String> getRandomSelection(int num) {
        List<String> selectedData = new ArrayList<>();

        Random random = new Random();
        String[] keys = SKILLS.keySet().toArray(new String[0]);
        if (keys.length > 0) {
            for (int i = 0; i < num; i++) {
                selectedData.add(keys[random.nextInt(keys.length)]);
            }
        }
        return selectedData;
    }

    public static Skills getInstance() {
        return instance;
    }
}
