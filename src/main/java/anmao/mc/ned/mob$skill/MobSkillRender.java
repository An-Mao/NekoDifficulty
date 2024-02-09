package anmao.mc.ned.mob$skill;

import anmao.mc.ned.NED;
import anmao.mc.ned.cap.mob$skill.MobSkillProvider;
import anmao.mc.ned.config.ClientConfig;
import anmao.mc.ned.lib.Draw;
import anmao.mc.ned.lib.math.UniformCircleDistribution;
import anmao.mc.ned.lib.math._Math;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Quaternionf;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = NED.MOD_ID, value = Dist.CLIENT)
public class MobSkillRender {
    private static final Minecraft MC = Minecraft.getInstance();
    private static double rotationAngle = 0;
    //private static final double ra = Math.PI / ClientConfig.getSkillRenderRotationAngle() ;
    public static double getAngle(){
        if (ClientConfig.getSkillRenderRotationAngle() == 0){
            return 0;
        }
        rotationAngle += Math.PI / ClientConfig.getSkillRenderRotationAngle() ;
        if (rotationAngle >= _Math.TWICE_PI) {
            rotationAngle = 0.0;
        }
        return rotationAngle;
    }
    @SubscribeEvent
    public static void onRender(RenderNameTagEvent event){
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            if (ClientConfig.getSkillRenderType() == 0){
                return;
            }
            livingEntity.getCapability(MobSkillProvider.MOB_SKILLS).ifPresent(mobSkillCap -> {
                PoseStack poseStack = event.getPoseStack();
                float lY = livingEntity.getBbHeight() / 2 + 0.16f;
                poseStack.pushPose();
                poseStack.translate(0.0,lY,0.0);
                Quaternionf camera = MC.getEntityRenderDispatcher().cameraOrientation();
                camera.x = 0;
                camera.z = 0;
                poseStack.mulPose(camera);
                poseStack.scale(-0.025F, -0.025F, 0.025F);
                draw(livingEntity,poseStack,mobSkillCap.getAllSkillID(),MC.font,event.getPackedLight());
                RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
                poseStack.popPose();
            });
        }
    }
    private static void draw( LivingEntity entity, PoseStack poseStack,List<String> skills,Font font,int packedLight){
        if (skills.isEmpty()){
            return;
        }
        RenderSystem.enableDepthTest();
        RenderSystem.defaultBlendFunc();
        int i = 0;
        ArrayList<Point2D.Double> pointPoss = UniformCircleDistribution.distributePoints(entity.getBbWidth()* 40, skills.size(),getAngle());
        for (String id : skills){
            Point2D.Double point = pointPoss.get(i);
            poseStack.pushPose();
            poseStack.translate(point.x-16,0,point.y);
            if (ClientConfig.getSkillRenderType() == 1) {
                ResourceLocation icon = MobSkills.getMobSkill(id).getTexture();
                RenderSystem.setShaderTexture(0, icon);
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                Draw.blit(poseStack, icon, 0, 0, 0, 0, 0, 32, 32, 32, 32);
            }else if (ClientConfig.getSkillRenderType() == 2){
                MultiBufferSource.BufferSource bufferSource = MC.renderBuffers().bufferSource();
                font.drawInBatch(MobSkills.getMobSkill(id).GetName(), 0, 0, -1, false, poseStack.last().pose(), bufferSource, Font.DisplayMode.NORMAL, 0, packedLight);
            }
            poseStack.popPose();
            i ++;
        }
        RenderSystem.disableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
    }
}
