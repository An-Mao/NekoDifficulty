package anmao.mc.ned.oracle;

import anmao.mc.ned.datatype.EventType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Event;

public abstract class Oracle extends OracleCDT implements OracleCore {
    private final String id ;
    public Oracle(String id){
        this.id = id;
    }
    @Override
    public boolean isRun(EventType eventType) {
        return false;
    }

    @Override
    public <T extends Event> void run(T event) {
    }
    public String getDescriptionId(){
        return getId()+".tip";
    }
    @Override
    public String getId() {
        return "oracle.ned."+id;
    }

    public Component getTip(){
        return Component.translatable(getDescriptionId());
    }

    @Override
    public abstract void generateOracleDat(Player player);
    public String getSaveKey(){
        return "oracle.ned.data";
    }
    public CompoundTag getSaveDat(Player player){
        return player.getPersistentData().getCompound(getSaveKey());
    }
    public void penalty(Player player){
        player.die(player.damageSources().fellOutOfWorld());
    }
}
