package anmao.mc.ned.net;

import anmao.mc.amlib.amlib.network.easy_net.EasyNet;
import anmao.mc.ned.cap.mob$skill.MobSkillProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MobSkillNet extends EasyNet {
    @Override
    public void client(Supplier<NetworkEvent.Context> contextSupplier, CompoundTag dat) {
        Entity entity = null;
        if (Minecraft.getInstance().level != null) {
            entity = Minecraft.getInstance().level.getEntity(dat.getInt("entity"));
        }
        if (entity != null) {
            entity.getCapability(MobSkillProvider.MOB_SKILLS).ifPresent(mobSkillCap -> mobSkillCap.handlePacket(dat));
        }
        super.client(contextSupplier, dat);
    }
}
