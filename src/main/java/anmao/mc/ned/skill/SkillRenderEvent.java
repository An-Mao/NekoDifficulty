package anmao.mc.ned.skill;

import anmao.mc.ned.NED;
import anmao.mc.ned.cap.skill.SkillProvider;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
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

@Mod.EventBusSubscriber(modid = NED.MOD_ID,value = Dist.CLIENT)
public class SkillRenderEvent {
    @SubscribeEvent
    public static void onRender(RenderNameTagEvent event){
        if (event.getEntity() instanceof LivingEntity livingEntity){
            livingEntity.getCapability(SkillProvider.MOB_SKILLS).ifPresent(skillCap -> {
                List<Component> components = skillCap.getSkillComponent();
                int lines = (int) Math.ceil((double) components.size() / 4);
                        //components.size() / 4;
                int li = -(int) Math.ceil((double) lines / 4);
                        //lines / 2;

                //System.out.println("render skill::"+components.size()+":line:"+lines);
                for (int line = 0 ;line < lines; line++){
                    //0,1,2,3
                    MutableComponent c = Component.literal("");
                    for (int i = 0;i<4;i++){
                        //0,1,2,3
                        int index = line * 4 + i;
                        //00,01,02,03,40,41,42,43,80,81,82,83
                        if (index < components.size()) {
                            //System.out.println("index" + index);
                            c.append("["+components.get(index).getString()+"]");
                        }
                    }
                    //System.out.println("c:"+c);
                    renderNameTag(event.getEntityRenderer().getFont(),event.getEntity(), c,event.getPoseStack(),event.getMultiBufferSource(), event.getPackedLight(),li);
                    li++;
                }
            });
        }
    }
    public static void renderNameTag(Font font,Entity pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight ,int line) {
        boolean flag = !pEntity.isDiscrete();
        float f = pEntity.getNameTagOffsetY();
        int i = 0;
        pPoseStack.pushPose();
        //pPoseStack.translate(0.0F, 0, 0.0F);
        pPoseStack.translate(line, 0, 0.0F);
        /*

        float yaw = pEntity.yRotO;

        // 创建旋转矩阵并应用到渲染矩阵上
        Quaternionf quaternion = new Quaternionf().rotateXYZ(0, -yaw, 0);
        // 获取实体的位置
        Vec3 entityPos = pEntity.position();

        pPoseStack.translate(entityPos.x, entityPos.y + f, entityPos.z);
        pPoseStack.mulPose(quaternion);
        // 将旋转应用到渲染矩阵上
        pPoseStack.mulPose(new Quaternionf(quaternion.x, quaternion.y, quaternion.z, quaternion.w));

        ///////////////////////////////
        // 获取实体的旋转角度
        float yaw = pEntity.yRotO;

        // 创建旋转四元数
        Quaternionf quaternion = new Quaternionf().rotationYXZ(0, -yaw, 0);

        // 获取实体的位置
        Vector3f entityPos = new Vector3f((float) pEntity.getX(), (float) pEntity.getY() + f, (float) pEntity.getZ());

        // 将旋转应用到渲染矩阵上
        pPoseStack.translate(entityPos.x(), entityPos.y(), entityPos.z());
        pPoseStack.mulPose(new Quaternionf(quaternion.x, quaternion.y, quaternion.z, quaternion.w));


         */
        pPoseStack.mulPose(pEntity.getDirection().getRotation());
        pPoseStack.scale(-0.025F, -0.025F, 0.025F);
        Matrix4f matrix4f = pPoseStack.last().pose();
        float f1 = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);
        int j = (int) (f1 * 255.0F) << 24;
        float f2 = (float) (-font.width(pDisplayName) / 2);
        font.drawInBatch(pDisplayName, f2, (float) i, 553648127, false, matrix4f, pBuffer, flag ? Font.DisplayMode.SEE_THROUGH : Font.DisplayMode.NORMAL, j, pPackedLight);
        if (flag) {
            font.drawInBatch(pDisplayName, f2, (float) i, -1, false, matrix4f, pBuffer, Font.DisplayMode.NORMAL, 0, pPackedLight);
        }

        pPoseStack.popPose();

    }
    private static void drawRotatingLabel(Font fontRenderer, Component label, double x, double y, Matrix4f matrix, MultiBufferSource bufferSource,int light) {
        // 绘制标签
        //fontRenderer.drawInBatch(label, (float) x, (float) y, (float) z, 0xFFFFFFFF, false, 0, true, matrix, bufferSource, false, 0, 0xF000F0);
        fontRenderer.drawInBatch(label,(float) x,(float) y,0x336699,false,matrix,bufferSource, Font.DisplayMode.NORMAL,0,light);
    }
}
