package anmao.mc.ned.lib;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.Set;

public class EntityHelper {
    public static Level getEntityLevel(Entity entity){
        return entity.level();
    }
    public static boolean isServerLevel(Entity entity){
        return !getEntityLevel(entity).isClientSide;
    }
    public static Set<String> getLivingEntityTag(LivingEntity entity){
        return entity.getTags();
    }
}
