package anmao.mc.ned.config;

import anmao.mc.ned.NED;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = NED.MOD_ID,value = Dist.CLIENT)
public class ClientConfig {
    private static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec.IntValue skillRenderType;
    public static ForgeConfigSpec.DoubleValue skillRenderRotationAngle;
    public static final ForgeConfigSpec CLIENT_SPEC;


    static {
        setupConfig();
        CLIENT_SPEC = builder.build();
    }


    public static int SKILL_RENDER_TYPE = 2;
    public static double SKILL_RENDER_ROTATION_ANGLE = 360;
    private static void setupConfig() {
        builder.comment("===============================================");
        builder.comment("===========Neko Endless Difficulty=============");
        builder.comment("==================Ver 1.0.0====================");
        builder.comment("====================Client=====================");
        builder.comment(" ");
        builder.comment(" ");
        builder.comment("Skill Render");
        builder.push("SkillRender");
        skillRenderType = builder
                .comment("Render Type")
                .comment("[0.disable 1.icon 2.txt][default:2]")
                .defineInRange("RenderType", 2, 0, 2);
        skillRenderRotationAngle = builder
                .comment("Render Rotation Angle")
                .comment("[default:360]")
                .defineInRange("RenderRotationAngle", 360.0, 0, Double.MAX_VALUE);
        builder.pop();
    }
    @SubscribeEvent
    public static void onClientLoad(final ModConfigEvent event)
    {
        SKILL_RENDER_TYPE = skillRenderType.get();
        SKILL_RENDER_ROTATION_ANGLE = skillRenderRotationAngle.get();
    }

    public static double getSkillRenderRotationAngle() {
        return skillRenderRotationAngle.get();
    }

    public static int getSkillRenderType() {
        return skillRenderType.get();
    }
}
