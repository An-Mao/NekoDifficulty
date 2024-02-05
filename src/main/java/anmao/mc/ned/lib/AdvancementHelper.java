package anmao.mc.ned.lib;

import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;

public class AdvancementHelper {
    public static boolean isVanillaAdvancement(Advancement advancement, String advancementId) {
        ResourceLocation vanillaAdvancementId = new ResourceLocation(advancementId);
        ResourceLocation advancementLocation = advancement.getId();
        return advancementLocation.equals(vanillaAdvancementId);
    }
}
