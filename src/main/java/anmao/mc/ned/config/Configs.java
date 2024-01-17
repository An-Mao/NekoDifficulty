package anmao.mc.ned.config;

import anmao.mc.ned.NED;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = NED.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Configs {
    public static final ForgeConfigSpec GENERAL_SPEC;

    static {
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
        setupConfig(configBuilder);
        GENERAL_SPEC = configBuilder.build();
    }
    private static ForgeConfigSpec.IntValue DamageScaleApplicableTarget;
    private static ForgeConfigSpec.DoubleValue DamageScaleWithMaxHealth;
    private static ForgeConfigSpec.DoubleValue DamageScaleMinDamage;


    private static ForgeConfigSpec.BooleanValue InvasionImmunityNonPlayerDamage;
    private static ForgeConfigSpec.IntValue InvasionMinDayInterval;
    private static ForgeConfigSpec.IntValue InvasionMaxDayInterval;
    private static ForgeConfigSpec.DoubleValue InvasionProbability;
    private static ForgeConfigSpec.IntValue InvasionDuration;
    private static ForgeConfigSpec.IntValue InvasionWaves;
    private static ForgeConfigSpec.IntValue InvasionMobSingleLimit;
    private static ForgeConfigSpec.IntValue InvasionDayTime;
    public static InvasionMobList InvasionMobList = new InvasionMobList();

    public static int ds_applicableTarget;
    public static float ds_scaleWithMaxHealth;
    public static float ds_minDamage;
    public static boolean invasion_immunityNonPlayerDamage;
    public static int invasion_minDayInterval;
    public static int invasion_maxDayInterval;
    public static float invasion_probability;
    public static int invasion_duration;
    public static int invasion_dayTime;//入侵时间
    public static int invasion_waves;//入侵波数
    public static int invasion_mobSingleLimit;//单次波数的生物数量（临时）


    private static void setupConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("===============================================");
        builder.comment("===========Neko Endless Difficulty=============");
        builder.comment("==================Ver 1.0.0====================");
        builder.comment("===============================================");
        builder.comment(" ");
        builder.comment(" ");
        builder.comment("Scale Damage");
        builder.push("DamageScale");
        DamageScaleApplicableTarget = builder
                .comment("Applicable target")
                .comment("[0.disable 1.invasion 2.all][default:1]")
                .defineInRange("ApplicableTarget", 1, 0, 2);
        DamageScaleWithMaxHealth = builder
                .comment("Reduced damage will not be less than (MinDamage)")
                .comment("[default:0.2]")
                .defineInRange("ScaleWithHealth", 0.2, 0, 1);

        DamageScaleMinDamage = builder
                .comment("Damage below this value is not reduced")
                .comment("[default:100]")
                .defineInRange("MinDamage", 5.0, 0.0, Double.MAX_VALUE);
        builder.pop();

        builder.comment(" ");
        builder.comment(" ");
        builder.comment("Invasion");
        builder.push("invasion");
        InvasionImmunityNonPlayerDamage = builder
                .comment("Intrusion mob immunity from non-player damage")
                .comment("[default:true]")
                .define("immunityNonPlayerDamage", true);

        InvasionMinDayInterval = builder
                .comment("Minimum day interval")
                .comment("[default:3]")
                .defineInRange("MinDayInterval", 3, 0, Integer.MAX_VALUE);
        InvasionMaxDayInterval = builder
                .comment("Maximum day interval")
                .comment("[default:7]")
                .defineInRange("MaxDayInterval", 7, 0, Integer.MAX_VALUE);

        InvasionProbability = builder
                .comment("Invasion probability")
                .comment("[default:0.2]")
                .defineInRange("Probability", 0.2, 0.0, 1.0);
        InvasionDuration = builder
                .comment("Duration of intrusion")
                .comment("[default:10000][20tick = 1s]")
                .defineInRange("Duration", 10000, 0, 24000);
        InvasionDayTime = builder
                .comment("Invasion time)")
                .comment("[default:-1][-1 = random]")
                .defineInRange("DayTime", -1, -1, 24000);

        InvasionWaves = builder
                .comment("Invasion waves")
                .comment("[default:10]")
                .defineInRange("Waves", 10, 1, 1000);
        InvasionMobSingleLimit = builder
                .comment("Intrusion wave count single time limit")
                .comment("[default:35]")
                .defineInRange("MobSingleLimit", 35, 1, 300);
        builder.pop();



    }
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        ds_applicableTarget = DamageScaleApplicableTarget.get();
        ds_scaleWithMaxHealth = DamageScaleWithMaxHealth.get().floatValue();
        ds_minDamage = DamageScaleMinDamage.get().floatValue();

        invasion_minDayInterval = InvasionMinDayInterval.get();
        invasion_maxDayInterval = InvasionMaxDayInterval.get();
        invasion_probability = InvasionProbability.get().floatValue();
        invasion_duration = InvasionDuration.get();
        invasion_waves = InvasionWaves.get();
        invasion_mobSingleLimit = InvasionMobSingleLimit.get();
        invasion_dayTime = InvasionDayTime.get();
        invasion_immunityNonPlayerDamage = InvasionImmunityNonPlayerDamage.get();
        InvasionMobList._start();
    }
}