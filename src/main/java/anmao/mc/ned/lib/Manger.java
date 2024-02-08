package anmao.mc.ned.lib;

import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.server.ServerLifecycleHooks;

public class Manger {
    public static ResourceManager getResourceManger() {
        if (ServerLifecycleHooks.getCurrentServer() != null) {
            return ServerLifecycleHooks.getCurrentServer().getResourceManager();
        } else {
            return Minecraft.getInstance().getResourceManager();
        }
    }
}
