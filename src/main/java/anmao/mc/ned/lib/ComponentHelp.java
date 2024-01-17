package anmao.mc.ned.lib;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ComponentHelp {
    public static void sendFormatMsg (ServerPlayer serverPlayer, String s, Object... data){
        String ns = MessageFormat.format(s,data);
        String[] strings = ns.split("&newLine");
        for (String str : strings){
            serverPlayer.sendSystemMessage(Component.literal(str));
        }
    }
}
