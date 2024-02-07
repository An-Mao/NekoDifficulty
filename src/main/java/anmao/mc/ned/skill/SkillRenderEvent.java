package anmao.mc.ned.skill;

import anmao.mc.ned.NED;
import anmao.mc.ned.cap.skill.SkillProvider;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkHooks;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.util.List;

public class SkillRenderEvent {
    private static final float hPi = (float) (180F / Math.PI);
    public static void onRender(RenderNameTagEvent event){
        if (event.getEntity() instanceof LivingEntity livingEntity){
            livingEntity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                List<Component> components = skillCap.getSkillComponent();
                int lines = (int) Math.ceil((double) components.size() / 4);
                int li = -(int) Math.ceil((double) lines / 4);
                for (int line = 0 ;line < lines; line++){
                    MutableComponent c = Component.literal("");
                    for (int i = 0;i<4;i++){
                        int index = line * 4 + i;
                        if (index < components.size()) {
                            c.append("["+components.get(index).getString()+"]");
                        }
                    }
                    renderNameTag(event.getEntityRenderer().getFont(),event.getEntity(), c,event.getPoseStack(),event.getMultiBufferSource(), event.getPackedLight(),li);
                    li++;
                }
            });
        }
    }
    public static void renderNameTag(Font font,Entity entity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight ,int line) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.0F, 0.2, 0.0F);
        Quaternionf quaternion = new Quaternionf().rotateXYZ(0, entity.yRotO + line, 0);
        pPoseStack.mulPose(quaternion);
        pPoseStack.scale(-0.025F, -0.025F, 0.025F);
        Matrix4f matrix4f = pPoseStack.last().pose();
        float f2 = (float) (-font.width(pDisplayName) / 2);
        font.drawInBatch(pDisplayName, f2, 0.0F, -1, false, matrix4f, pBuffer, Font.DisplayMode.NORMAL, 0, pPackedLight);
        pPoseStack.popPose();


    }
    public static void renderNameTag(Font font, Entity entity, Component displayName, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        // 获取玩家的位置
        Entity cameraEntity = Minecraft.getInstance().cameraEntity;
        Vec3 playerPos = cameraEntity.position();

        // 获取标签的位置
        double labelX = entity.getX();
        double labelY = entity.getY() + entity.getBbHeight() + 0.5;
        double labelZ = entity.getZ();

        // 计算标签位置与玩家位置的向量
        Vec3 labelPos = new Vec3(labelX, labelY, labelZ);
        Vec3 facingVector = playerPos.subtract(labelPos).normalize();

        // 计算标签的朝向角度
        double angle = Math.atan2(facingVector.z, facingVector.x) * (180 / Math.PI) + 90;

        // 绘制标签
        poseStack.pushPose();
        poseStack.translate(labelX, labelY, labelZ);

        Quaternionf quaternion = new Quaternionf().rotateXYZ(0, (float) angle, 0);
        //poseStack.mulPose(Vector3f.YP.rotationDegrees((float) angle)); // 根据朝向角度旋转标签
        poseStack.mulPose(quaternion);
        poseStack.scale(-0.025F, -0.025F, 0.025F);
        Matrix4f matrix4f = poseStack.last().pose();
        float f2 = (float) (-font.width(displayName) / 2);
        font.drawInBatch(displayName, f2, 0, -1, false, matrix4f, bufferSource, Font.DisplayMode.NORMAL, 0, packedLight);
        poseStack.popPose();
    }
}
