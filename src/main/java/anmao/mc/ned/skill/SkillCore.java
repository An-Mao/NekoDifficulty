package anmao.mc.ned.skill;

import anmao.mc.ned.datatype.EventData;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;

interface SkillCore {
    EventData Event(EventData eventData);
    void Tick(LivingEntity livingEntity);
    int GetMaxLvl();
    String GetID();
    Component GetDes();
    boolean CanAdd(LivingEntity livingEntity);
}
