package anmao.mc.ned.mob$skill;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.eventbus.api.Event;

interface MobSkillInterface {
     boolean canAdd(String[] skills);

    /**
     *
     * @param event
     * 事件
     * @param dat
     * 额外数据
     * @param <T>
     *     继承于Event
     */
     <T extends Event> void event(T event, CompoundTag dat);
}
