package anmao.mc.ned.mob$skill.b2;

import anmao.mc.ned.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;

public class PoisonMistSkill extends Skill {
    //毒雾
    public PoisonMistSkill() {
        super("poison_mist");
    }

    @Override
    public void Tick(LivingEntity livingEntity, CompoundTag skillData) {
        //
    }
    public static AreaEffectCloud generatePotionCloud(LivingEntity entity, MobEffectInstance effect, int duration, int radius) {
        ServerLevel level = (ServerLevel) entity.level();

        // 创建一个带有指定效果的药水瓶
        net.minecraft.world.item.ItemStack potionStack = PotionUtils.setPotion(new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.POTION), Potions.WATER);
        potionStack.getOrCreateTag().putInt("CustomPotionColor", 0x4E9331); // 设置药水颜色
        PotionUtils.setCustomEffects(potionStack, java.util.Arrays.asList(effect));

        // 创建药水云实体
        AreaEffectCloud cloud = new AreaEffectCloud(level, entity.getX(), entity.getY(), entity.getZ());
        cloud.setOwner(entity);
        //cloud.setParticle(PotionUtils.getParticle(effect.getEffect()));
        cloud.setRadius(radius);
        cloud.setRadiusOnUse(-0.5F);
        cloud.setWaitTime(10);
        cloud.setRadiusPerTick(-cloud.getRadius() / (float) cloud.getDuration());

        // 将效果应用到药水云上
        cloud.addEffect(effect);

        // 将药水云实体添加到世界
        level.addFreshEntity(cloud);

        return cloud;
    }
}
