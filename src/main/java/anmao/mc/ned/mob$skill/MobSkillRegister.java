package anmao.mc.ned.mob$skill;

import anmao.mc.ned.NED;
import anmao.mc.ned.config.mob_skill.MobSkillConfig;
import anmao.mc.ned.mob$skill.b2.*;
import anmao.mc.ned.mob$skill.b2.chao.ChaoMobSkill;
import anmao.mc.ned.mob$skill.b2.fetters.FettersMobSkill;
import anmao.mc.ned.mob$skill.b2.life_absorbing.LifeAbsorbingMobSkill;
import anmao.mc.ned.mob$skill.b2.tentacle.TentacleMobSkill;
import anmao.mc.ned.mob$skill.b2.timid.TimidMobSkill;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

public class MobSkillRegister {
    public static final DeferredRegister<MobSkill> MOB_SKILL = DeferredRegister.create(new ResourceLocation(NED.MOD_ID, "mob_skill"), NED.MOD_ID);
    public static final Supplier<IForgeRegistry<MobSkill>> REGISTRY = MOB_SKILL.makeRegistry(RegistryBuilder::new);
    public static final RegistryObject<MobSkill> Alone = reg("alone", AloneMobSkill::new);
    public static final RegistryObject<MobSkill> Bomb = reg("bomb", BombMobSkill::new);
    public static final RegistryObject<MobSkill> Chao = reg("chao", ChaoMobSkill::new);
    public static final RegistryObject<MobSkill> ChaseAway = reg("chase_away", ChaseAwayMobSkill::new);
    public static final RegistryObject<MobSkill> Cohesion = reg("cohesion", CohesionMobSkill::new);
    public static final RegistryObject<MobSkill> ColdShield = reg("cold_shield", ColdShieldMobSkill::new);
    public static final RegistryObject<MobSkill> Comprehend = reg("comprehend", ComprehendMobSkill::new);
    public static final RegistryObject<MobSkill> Conductivity = reg("conductivity", ConductivityMobSkill::new);
    public static final RegistryObject<MobSkill> Corrosion = reg("corrosion", CorrosionMobSkill::new);
    public static final RegistryObject<MobSkill> Counterstrike = reg("counterstrike", CounterstrikeMobSkill::new);
    public static final RegistryObject<MobSkill> Crystallography = reg("crystallography", CrystallographyMobSkill::new);
    public static final RegistryObject<MobSkill> Curse = reg("curse", CurseMobSkill::new);
    public static final RegistryObject<MobSkill> DarkPoisonShield = reg("dark_poison_shield", DarkPoisonShieldMobSkill::new);
    public static final RegistryObject<MobSkill> DarkPosition = reg("dark_position", DarkPositionMobSkill::new);
    public static final RegistryObject<MobSkill> Dexterity = reg("dexterity", DexterityMobSkill::new);
    public static final RegistryObject<MobSkill> Disappear = reg("disappear", DisappearMobSkill::new);
    public static final RegistryObject<MobSkill> Dislike = reg("dislike", DislikeMobSkill::new);
    public static final RegistryObject<MobSkill> EnergyShield = reg("energy_shield", EnergyShieldMobSkill::new);
    public static final RegistryObject<MobSkill> Fast = reg("fast", FastMobSkill::new);
    public static final RegistryObject<MobSkill> Favorite = reg("favorite", FavoriteMobSkill::new);
    public static final RegistryObject<MobSkill> Fetters = reg("fetters", FettersMobSkill::new);
    public static final RegistryObject<MobSkill> FireBarrier = reg("fire_barrier", FireBarrierMobSkill::new);
    public static final RegistryObject<MobSkill> Frostbite = reg("frostbite", FrostbiteMobSkill::new);
    public static final RegistryObject<MobSkill> FrostTrail = reg("frostTrail", FrostTrailMobSkill::new);
    public static final RegistryObject<MobSkill> HighFrequencyBoundary = reg("high_frequency_boundary", HighFrequencyBoundaryMobSkill::new);
    public static final RegistryObject<MobSkill> Ignite = reg("ignite", IgniteMobSkill::new);
    public static final RegistryObject<MobSkill> Imperishable = reg("imperishable", ImperishableMobSkill::new);
    public static final RegistryObject<MobSkill> Infection = reg("infection", InfectionMobSkill::new);
    public static final RegistryObject<MobSkill> InterruptionOfGrowth = reg("interruption_of_growth", InterruptionOfGrowthMobSkill::new);
    public static final RegistryObject<MobSkill> IntrinsicQuality = reg("intrinsic_quality", IntrinsicQualityMobSkill::new);
    public static final RegistryObject<MobSkill> Ironclad = reg("ironclad", IroncladMobSkill::new);
    public static final RegistryObject<MobSkill> Leaders = reg("leaders", LeadersMobSkill::new);
    public static final RegistryObject<MobSkill> LearnMobSkill = reg("learn", LearnMobSkill::new);
    public static final RegistryObject<MobSkill> LeftGuardian = reg("left_guardian", LeftGuardianMobSkill::new);
    public static final RegistryObject<MobSkill> LifeAbsorbing = reg("life_absorbing", LifeAbsorbingMobSkill::new);
    public static final RegistryObject<MobSkill> LowFrequencyBoundary = reg("low_frequency_boundary", LowFrequencyBoundaryMobSkill::new);
    public static final RegistryObject<MobSkill> Mirroring = reg("mirroring", MirroringMobSkill::new);
    public static final RegistryObject<MobSkill> NewbornMobSkill = reg("newborn", NewbornMobSkill::new);
    public static final RegistryObject<MobSkill> NightmareMemory = reg("nightmare_memory", NightmareMemoryMobSkill::new);
    public static final RegistryObject<MobSkill> Order = reg("order", OrderMobSkill::new);
    public static final RegistryObject<MobSkill> PoisonedEgg = reg("poisoned_egg", PoisonedEggMobSkill::new);
    public static final RegistryObject<MobSkill> PoisonMist = reg("poison_mist", PoisonMistMobSkill::new);
    public static final RegistryObject<MobSkill> Powerful = reg("powerful", PowerfulMobSkill::new);
    public static final RegistryObject<MobSkill> Proliferate = reg("proliferate", ProliferateMobSkill::new);
    public static final RegistryObject<MobSkill> RainbowPosition = reg("rainbow_position", RainbowPositionMobSkill::new);
    public static final RegistryObject<MobSkill> Rebirth = reg("rebirth", RebirthMobSkill::new);
    public static final RegistryObject<MobSkill> Recover = reg("recover", RecoverMobSkill::new);
    public static final RegistryObject<MobSkill> Reincarnation = reg("reincarnation", ReincarnationMobSkill::new);
    public static final RegistryObject<MobSkill> Repulse = reg("repulse", RepulseMobSkill::new);
    public static final RegistryObject<MobSkill> Resistant = reg("resistant", ResistantMobSkill::new);
    public static final RegistryObject<MobSkill> ReverseEntropy = reg("reverse_entropy", ReverseEntropyMobSkill::new);
    public static final RegistryObject<MobSkill> RightBlessing = reg("right_blessing", RightBlessingMobSkill::new);
    public static final RegistryObject<MobSkill> SearingShield = reg("searing_shield", SearingShieldMobSkill::new);
    public static final RegistryObject<MobSkill> SenseOfCompetition = reg("sense_of_competition", SenseOfCompetitionMobSkill::new);
    public static final RegistryObject<MobSkill> ShieldOfChaos = reg("shield_of_chaos", ShieldOfChaosMobSkill::new);
    public static final RegistryObject<MobSkill> ShieldOfPurity = reg("shield_of_purity", ShieldOfPurityMobSkill::new);
    public static final RegistryObject<MobSkill> Steadfastness = reg("steadfastness", SteadfastnessMobSkill::new);
    public static final RegistryObject<MobSkill> Steal = reg("steal", StealMobSkill::new);
    public static final RegistryObject<MobSkill> Subtotal = reg("subtotal", SubtotalMobSkill::new);
    public static final RegistryObject<MobSkill> Summon = reg("summon", SummonMobSkill::new);
    public static final RegistryObject<MobSkill> Swirl = reg("swirl", SwirlMobSkill::new);
    public static final RegistryObject<MobSkill> Symbiosis = reg("symbiosis", SymbiosisMobSkill::new);
    public static final RegistryObject<MobSkill> Teleportation = reg("teleportation", TeleportationMobSkill::new);
    public static final RegistryObject<MobSkill> Tentacle = reg("tentacle", TentacleMobSkill::new);
    public static final RegistryObject<MobSkill> ThunderthornShield = reg("thunderthorn_shield", ThunderthornShieldMobSkill::new);
    public static final RegistryObject<MobSkill> Timid = reg("timid", TimidMobSkill::new);
    public static final RegistryObject<MobSkill> TrailOfFlames = reg("trail_of_flames", TrailOfFlamesMobSkill::new);
    public static RegistryObject<MobSkill> reg(String name , Function<String , MobSkill> mobSkillFunction){
        if (MobSkillConfig.INSTANCE.isEnable(name)) {
            System.out.println("reg mob skill ::"+name);
            return MOB_SKILL.register(name, () -> mobSkillFunction.apply(name));
        }
        System.out.println("skip reg mob skill ::"+name);
        return null;
    }

    public static void register(IEventBus eventBus){
        MOB_SKILL.register(eventBus);
    }
}
