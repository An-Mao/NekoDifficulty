package anmao.mc.ned.lib;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.text.MessageFormat;

public class ComponentHelp {
    public static void sendFormatMsg (ServerPlayer serverPlayer, String s, Object... data){
        String ns = MessageFormat.format(s,data);
        String[] strings = ns.split("&newLine");
        for (String str : strings){
            serverPlayer.sendSystemMessage(Component.literal(str));
        }
    }
    public static void sendFormatMsgWithKey (ServerPlayer serverPlayer, String s, Object... data){
        sendFormatMsg(serverPlayer,Component.translatable(s).getString(),data);
    }
}
