package anmao.mc.ned.skill;

import anmao.mc.ned.NED;
import anmao.mc.ned.cap.skill.SkillProvider;
import anmao.mc.ned.config.ClientConfig;
import anmao.mc.ned.lib.math.UniformCircleDistribution;
import anmao.mc.ned.lib.math._Math;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
@Mod.EventBusSubscriber(modid = NED.MOD_ID, value = Dist.CLIENT)
public class SkillRender {
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
            livingEntity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                PoseStack poseStack = event.getPoseStack();
                float lY = livingEntity.getBbHeight() / 2 + 0.16f;
                poseStack.pushPose();
                poseStack.translate(0.0,lY,0.0);
                Quaternionf camera = MC.getEntityRenderDispatcher().cameraOrientation();
                camera.x = 0;
                camera.z = 0;
                poseStack.mulPose(camera);
                poseStack.scale(-0.025F, -0.025F, 0.025F);
                draw(livingEntity,poseStack,skillCap.getAllSkillID(),MC.font,event.getPackedLight());
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
                ResourceLocation icon = Skills.getInstance().getSkillTexture(id);
                RenderSystem.setShaderTexture(0, icon);
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                blit(poseStack, icon, 0, 0, 0, 0, 0, 32, 32, 32, 32);
            }else if (ClientConfig.getSkillRenderType() == 2){
                MultiBufferSource.BufferSource bufferSource = MC.renderBuffers().bufferSource();
                font.drawInBatch(Skills.getInstance().getComponent(id), 0, 0, -1, false, poseStack.last().pose(), bufferSource, Font.DisplayMode.NORMAL, 0, packedLight);
            }
            poseStack.popPose();
            i ++;
        }
        RenderSystem.disableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
    }
    private static void blit(PoseStack poseStack, ResourceLocation pAtlasLocation, int pX, int pY, int pUOffset, int pVOffset, int pUWidth, int pVHeight) {
        blit(poseStack, pAtlasLocation, pX, pY, 0, (float)pUOffset, (float)pVOffset, pUWidth, pVHeight, 256, 256);
    }
    private static void blit(PoseStack poseStack,ResourceLocation pAtlasLocation, int pX, int pY, int pBlitOffset, float pUOffset, float pVOffset, int pUWidth, int pVHeight, int pTextureWidth, int pTextureHeight) {
        blit(poseStack, pAtlasLocation, pX, pX + pUWidth, pY, pY + pVHeight, pBlitOffset, pUWidth, pVHeight, pUOffset, pVOffset, pTextureWidth, pTextureHeight);
    }
    private static void blit(PoseStack poseStack,ResourceLocation pAtlasLocation, int pX1, int pX2, int pY1, int pY2, int pBlitOffset, int pUWidth, int pVHeight, float pUOffset, float pVOffset, int pTextureWidth, int pTextureHeight) {
        innerBlit(poseStack, pAtlasLocation, pX1, pX2, pY1, pY2, pBlitOffset, (pUOffset + 0.0F) / (float)pTextureWidth, (pUOffset + (float)pUWidth) / (float)pTextureWidth, (pVOffset + 0.0F) / (float)pTextureHeight, (pVOffset + (float)pVHeight) / (float)pTextureHeight);
    }
    private static void innerBlit(PoseStack poseStack, ResourceLocation pAtlasLocation, int pX1, int pX2, int pY1, int pY2, int pBlitOffset, float pMinU, float pMaxU, float pMinV, float pMaxV) {
        RenderSystem.setShaderTexture(0, pAtlasLocation);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        Matrix4f matrix4f = poseStack.last().pose();
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(matrix4f, (float)pX1, (float)pY1, (float)pBlitOffset).uv(pMinU, pMinV).endVertex();
        bufferbuilder.vertex(matrix4f, (float)pX1, (float)pY2, (float)pBlitOffset).uv(pMinU, pMaxV).endVertex();
        bufferbuilder.vertex(matrix4f, (float)pX2, (float)pY2, (float)pBlitOffset).uv(pMaxU, pMaxV).endVertex();
        bufferbuilder.vertex(matrix4f, (float)pX2, (float)pY1, (float)pBlitOffset).uv(pMaxU, pMinV).endVertex();
        BufferUploader.drawWithShader(bufferbuilder.end());
    }
}
