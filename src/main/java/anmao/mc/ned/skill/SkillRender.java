package anmao.mc.ned.skill;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.TextureAtlasHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.client.ForgeHooksClient;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class SkillRender extends EntityRenderer<Entity> {
    private final ResourceLocation ico_1 = new ResourceLocation("//");
    protected SkillRender(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(Entity pEntity) {
        return null;
    }

    @Override
    public void render(Entity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);


    }
    protected void renderSkillImage(Entity pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        double d0 = this.entityRenderDispatcher.distanceToSqr(pEntity);
        if (ForgeHooksClient.isNameplateInRenderDistance(pEntity, d0)) {
            boolean flag = !pEntity.isDiscrete();
            //-------------------------------------------------
            float f = pEntity.getNameTagOffsetY();
            int i = "deadmau5".equals(pDisplayName.getString()) ? -10 : 0;
            //-------------------------------------------------
            pPoseStack.pushPose();
            pPoseStack.translate(0.0F, f, 0.0F);
            pPoseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            pPoseStack.scale(-0.025F, -0.025F, 0.025F);
            Matrix4f matrix4f = pPoseStack.last().pose();
            innerBlit(ico_1,matrix4f,0,16,0,16,0,0,0,0,0);
            pPoseStack.popPose();
        }
    }
    void innerBlit(ResourceLocation pAtlasLocation,Matrix4f matrix4f, int pX1, int pX2, int pY1, int pY2, int pBlitOffset, float pMinU, float pMaxU, float pMinV, float pMaxV) {
        RenderSystem.setShaderTexture(0, pAtlasLocation);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(matrix4f, (float)pX1, (float)pY1, (float)pBlitOffset).uv(pMinU, pMinV).endVertex();
        bufferbuilder.vertex(matrix4f, (float)pX1, (float)pY2, (float)pBlitOffset).uv(pMinU, pMaxV).endVertex();
        bufferbuilder.vertex(matrix4f, (float)pX2, (float)pY2, (float)pBlitOffset).uv(pMaxU, pMaxV).endVertex();
        bufferbuilder.vertex(matrix4f, (float)pX2, (float)pY1, (float)pBlitOffset).uv(pMaxU, pMinV).endVertex();
        BufferUploader.drawWithShader(bufferbuilder.end());
    }
}
