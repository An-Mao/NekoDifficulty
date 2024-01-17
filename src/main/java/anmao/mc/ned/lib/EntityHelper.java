package anmao.mc.ned.lib;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

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

    public static EntityType<?> getEntityType(String id){
        //return ForgeRegistries.ENTITY_TYPES.getValue(new net.minecraft.resources.ResourceLocation(registryId));
        return BuiltInRegistries.ENTITY_TYPE.get(new ResourceLocation(id));
    }
    public static EntityType<?> getEntityTypeNew(String id){
        return ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(id));
    }
    private void spawnZombieNearPlayer(Level level, double x, double y, double z , String id) {
        double xOffset = (level.random.nextDouble() - 0.5) * 10;
        double zOffset = (level.random.nextDouble() - 0.5) * 10;
        double spawnX = x + xOffset;
        double spawnZ = z + zOffset;
        Zombie zombie = new Zombie(level);
        zombie.setPos(spawnX, y, spawnZ);
        zombie.addTag(id);
        level.addFreshEntity(zombie);
    }
}
