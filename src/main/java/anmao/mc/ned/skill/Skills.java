package anmao.mc.ned.skill;

import anmao.mc.ned.NED;
import anmao.mc.ned.skill.b2.*;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ServiceLoader;

public class Skills {
    private static final Skills instance = new Skills();
    public ImmutableMap<String, Skill> SKILLS;
    private ImmutableMap<String, Supplier<ResourceLocation>> skillTexture;
    public String[] SKILL_KEYS;
    public SkillsData skillsData = new SkillsData();
    private final ImmutableMap.Builder<String, Skill> builder = ImmutableMap.builder();
    public Skills(){
        autoRegister();
        //registers();
        //将键缓存到变量
        SKILL_KEYS = SKILLS.keySet().toArray(new String[0]);
    }

    private void autoRegister() {
        ImmutableMap.Builder<String, Skill> builder = ImmutableMap.builder();
        ImmutableMap.Builder<String, Supplier<ResourceLocation>> textureBuilder = ImmutableMap.builder();
        ServiceLoader<Skill> skillLoader = ServiceLoader.load(Skill.class);
        for (Skill skill : skillLoader) {
            builder.put(skill.GetID(),skill);
            System.out.println("_reg(\""+skill.GetID()+"\","+skill.getClass().getName()+"());");
            textureBuilder.put(skill.GetID(),() -> new ResourceLocation(NED.MOD_ID,"textures/skill/"+skill.GetID()+".png"));
        }
        SKILLS = builder.build();
    }
    private void newRegisters(){



        _reg("skill.ned.alone", new AloneSkill());
        _reg("skill.ned.boom",anmao.mc.ned.skill.b2.BombSkill());
        _reg("skill.ned.chao",anmao.mc.ned.skill.b2.ChaoSkill());
        _reg("skill.ned.chase_away",anmao.mc.ned.skill.b2.ChaseAwaySkill());
        _reg("skill.ned.Cohesion",anmao.mc.ned.skill.b2.CohesionSkill());
        _reg("skill.ned.ColdShield",anmao.mc.ned.skill.b2.ColdShieldSkill());
        _reg("skill.ned.Comprehend",anmao.mc.ned.skill.b2.ComprehendSkill());
        _reg("skill.ned.Conductivity",anmao.mc.ned.skill.b2.ConductivitySkill());
        _reg("skill.ned.Corrosion",anmao.mc.ned.skill.b2.CorrosionSkill());
        _reg("skill.ned.Counterstrike",anmao.mc.ned.skill.b2.CounterstrikeSkill());
        _reg("skill.ned.Crystallography",anmao.mc.ned.skill.b2.CrystallographySkill());
        _reg("skill.ned.Curse",anmao.mc.ned.skill.b2.CurseSkill());
        _reg("skill.ned.DarkPoisonShield",anmao.mc.ned.skill.b2.DarkPoisonShieldSkill());
        _reg("skill.ned.DarkPosition",anmao.mc.ned.skill.b2.DarkPositionSkill());
        _reg("skill.ned.Dexterity",anmao.mc.ned.skill.b2.DexteritySkill());
        _reg("skill.ned.Disappear",anmao.mc.ned.skill.b2.DisappearSkill());
        _reg("skill.ned.Dislike",anmao.mc.ned.skill.b2.DislikeSkill());
        _reg("skill.ned.EnergyShield",anmao.mc.ned.skill.b2.EnergyShieldSkill());
        _reg("skill.ned.fast",anmao.mc.ned.skill.b2.FastSkill());
        _reg("skill.ned.Favorite",anmao.mc.ned.skill.b2.FavoriteSkill());
        _reg("skill.ned.Fetters",anmao.mc.ned.skill.b2.FettersSkill());
        _reg("skill.ned.FireBarrier",anmao.mc.ned.skill.b2.FireBarrierSkill());
        _reg("skill.ned.Frostbite",anmao.mc.ned.skill.b2.FrostbiteSkill());
        _reg("skill.ned.FrostTrail",anmao.mc.ned.skill.b2.FrostTrailSkill());
        _reg("skill.ned.HighFrequencyBoundary",anmao.mc.ned.skill.b2.HighFrequencyBoundarySkill());
        _reg("skill.ned.Ignite",anmao.mc.ned.skill.b2.IgniteSkill());
        _reg("skill.ned.Imperishable",anmao.mc.ned.skill.b2.ImperishableSkill());
        _reg("skill.ned.Infection",anmao.mc.ned.skill.b2.InfectionSkill());
        _reg("skill.ned.InterruptionOfGrowth",anmao.mc.ned.skill.b2.InterruptionOfGrowthSkill());
        _reg("skill.ned.IntrinsicQuality",anmao.mc.ned.skill.b2.IntrinsicQualitySkill());
        _reg("skill.ned.Ironclad",anmao.mc.ned.skill.b2.IroncladSkill());
        _reg("skill.ned.Leaders",anmao.mc.ned.skill.b2.LeadersSkill());
        _reg("skill.ned.Learn",anmao.mc.ned.skill.b2.LearnSkill());
        _reg("skill.ned.LeftGuardian",anmao.mc.ned.skill.b2.LeftGuardianSkill());
        _reg("skill.ned.LifeAbsorbing",anmao.mc.ned.skill.b2.LifeAbsorbingSkill());
        _reg("skill.ned.LowFrequencyBoundary",anmao.mc.ned.skill.b2.LowFrequencyBoundarySkill());
        _reg("skill.ned.Mirroring",anmao.mc.ned.skill.b2.MirroringSkill());
        _reg("skill.ned.Newborn",anmao.mc.ned.skill.b2.NewbornSkill());
        _reg("skill.ned.NightmareMemory",anmao.mc.ned.skill.b2.NightmareMemorySkill());
        _reg("skill.ned.Order",anmao.mc.ned.skill.b2.OrderSkill());
        _reg("skill.ned.PoisonedEgg",anmao.mc.ned.skill.b2.PoisonedEggSkill());
        _reg("skill.ned.PoisonMist",anmao.mc.ned.skill.b2.PoisonMistSkill());
        _reg("skill.ned.Powerful",anmao.mc.ned.skill.b2.PowerfulSkill());
        _reg("skill.ned.Proliferate",anmao.mc.ned.skill.b2.ProliferateSkill());
        _reg("skill.ned.RainbowPosition",anmao.mc.ned.skill.b2.RainbowPositionSkill());
        _reg("skill.ned.Rebirth",anmao.mc.ned.skill.b2.RebirthSkill());
        _reg("skill.ned.Recover",anmao.mc.ned.skill.b2.RecoverSkill());
        _reg("skill.ned.Reincarnation",anmao.mc.ned.skill.b2.ReincarnationSkill());
        _reg("skill.ned.Repulse",anmao.mc.ned.skill.b2.RepulseSkill());
        _reg("skill.ned.resistant",anmao.mc.ned.skill.b2.ResistantSkill());
        _reg("skill.ned.ReverseEntropy",anmao.mc.ned.skill.b2.ReverseEntropySkill());
        _reg("skill.ned.RightBlessing",anmao.mc.ned.skill.b2.RightBlessingSkill());
        _reg("skill.ned.SearingShield",anmao.mc.ned.skill.b2.SearingShieldSkill());
        _reg("skill.ned.SenseOfCompetition",anmao.mc.ned.skill.b2.SenseOfCompetitionSkill());
        _reg("skill.ned.ShieldOfChaos",anmao.mc.ned.skill.b2.ShieldOfChaosSkill());
        _reg("skill.ned.ShieldOfPurity",anmao.mc.ned.skill.b2.ShieldOfPuritySkill());
        _reg("skill.ned.Steadfastness",anmao.mc.ned.skill.b2.SteadfastnessSkill());
        _reg("skill.ned.Steal",anmao.mc.ned.skill.b2.StealSkill());
        _reg("skill.ned.Subtotal",anmao.mc.ned.skill.b2.SubtotalSkill());
        _reg("skill.ned.Summon",anmao.mc.ned.skill.b2.SummonSkill());
        _reg("skill.ned.Swirl",anmao.mc.ned.skill.b2.SwirlSkill());
        _reg("skill.ned.Symbiosis",anmao.mc.ned.skill.b2.SymbiosisSkill());
        _reg("skill.ned.Teleportation",anmao.mc.ned.skill.b2.TeleportationSkill());
        _reg("skill.ned.tentacle",anmao.mc.ned.skill.b2.TentacleSkill());
        _reg("skill.ned.thunderthorn_shield",anmao.mc.ned.skill.b2.ThunderthornShieldSkill());
        _reg("skill.ned.timid",anmao.mc.ned.skill.b2.TimidSkill());
        _reg("skill.ned.trail_of_Flames",anmao.mc.ned.skill.b2.TrailOfFlamesSkill());
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
