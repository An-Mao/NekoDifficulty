package anmao.mc.ned.skill;

import anmao.mc.ned.datatype.EventData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;

interface SkillCore {
    void Event(EventData eventData, CompoundTag skillData);
    void Tick(LivingEntity livingEntity, CompoundTag skillData);
    int GetMaxLvl();
    String GetID();
    Component GetDes();
    boolean CanAdd(LivingEntity livingEntity);
}
