package anmao.mc.ned.oracle;

import anmao.mc.ned.datatype.EventType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Event;

public interface OracleCore {
    boolean isRun(EventType eventType);
    <T extends Event> void run(T event);
    String getId();
    void generateOracleDat(Player player);
    void penalty(Player player);
}
