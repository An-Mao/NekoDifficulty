package anmao.mc.ned.oracles;

import anmao.mc.ned.datatype.EventType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Event;

public interface OracleCore {
    boolean isRun(EventType eventType);
    <T extends Event> void run(T event);
    String getId();
    void generateOracleDat(Player player);
    void penalty(Player player);
}
