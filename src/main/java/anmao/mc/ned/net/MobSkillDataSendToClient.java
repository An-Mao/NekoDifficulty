package anmao.mc.ned.net;

import anmao.mc.ned.cap.mob$skill.MobSkillProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MobSkillDataSendToClient {
    private final CompoundTag index;
    private final int entityId;
    public MobSkillDataSendToClient(CompoundTag dat, int id){
        index = dat;
        entityId = id;
    }

    public MobSkillDataSendToClient(FriendlyByteBuf buf){
        index = buf.readNbt();
        entityId = buf.readInt();
    }
    public void toBytes(FriendlyByteBuf buf){
        buf.writeNbt(index);
        buf.writeInt(entityId);
    }
    public void handle(Supplier<NetworkEvent.Context> ctx){
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(()-> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            Entity entity = null;
            if (Minecraft.getInstance().level != null) {
                entity = Minecraft.getInstance().level.getEntity(entityId);
            }
            if (entity != null) {
                entity.getCapability(MobSkillProvider.MOB_SKILLS).ifPresent(mobSkillCap -> mobSkillCap.handlePacket(index));
            }
        }));
        ctx.get().setPacketHandled(true);
        //return true;
    }
}
