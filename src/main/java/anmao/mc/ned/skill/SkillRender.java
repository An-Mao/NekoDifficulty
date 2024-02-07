package anmao.mc.ned.skill;

import anmao.mc.ned.NED;
import anmao.mc.ned.cap.skill.SkillProvider;
import anmao.mc.ned.lib.EntityHelper;
import anmao.mc.ned.lib._Math;
import anmao.mc.ned.lib.math.UniformCircleDistribution;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
@Mod.EventBusSubscriber(modid = NED.MOD_ID,value = Dist.CLIENT)
public class SkillRender {
    private static final Minecraft MC = Minecraft.getInstance();
    @SubscribeEvent
    public static void onRender(RenderNameTagEvent event){
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            livingEntity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                PoseStack poseStack = event.getPoseStack();
                float lY = livingEntity.getBbHeight() / 2;
                poseStack.pushPose();

                poseStack.translate(0.0,lY,0.0);
                Quaternionf camera = MC.getEntityRenderDispatcher().cameraOrientation();
                camera.x = 0;
                camera.z = 0;
                poseStack.mulPose(camera);
                poseStack.scale(-0.025F, -0.025F, 0.025F);
                draw(livingEntity,poseStack,skillCap.getAllSkill());
                RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
                poseStack.popPose();
                //RenderSystem.disableDepthTest();
            });
        }
    }
    private static void draw(LivingEntity entity, PoseStack poseStack,List<String> skills){
        if (skills.isEmpty()){
            return;
        }
        RenderSystem.enableDepthTest();
        RenderSystem.defaultBlendFunc();
        //poseStack.pushPose();
        //poseStack.translate(0.0F,y,0.0F);
        //Quaternionf camera = MC.getEntityRenderDispatcher().cameraOrientation();
        //poseStack.mulPose(new Quaternionf(camera.x, 0, camera.z, camera.w));
        //poseStack.scale(0.025F, 0.025F, 0.025F);
        int i = 0;
        ArrayList<Point2D.Double> pointPoss = UniformCircleDistribution.distributePoints(entity.getBbWidth()* 40, skills.size());
        //ArrayList<Point2D.Double> pointPoss = _Math.getPosWithCircle(entity.getBbWidth()* 40, skills.size());
        for (String id : skills){
            Point2D.Double point = pointPoss.get(i);
            poseStack.pushPose();
            poseStack.translate(point.x-16,0,point.y);
            //poseStack.mulPose(camera);
            //poseStack.scale(-0.025F, 0.025F, 0.025F);
            ResourceLocation icon = new ResourceLocation(NED.MOD_ID,"textures/skill/skill_test.png");
            RenderSystem.setShaderTexture(0,icon);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
            blit(poseStack,icon,0,0,0,0,0,32,32,32,32);
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
