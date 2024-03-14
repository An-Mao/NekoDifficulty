package anmao.mc.ned.cap.oracles;

import anmao.mc.amlib.math._Random;
import anmao.mc.ned.oracle.Oracle;
import anmao.mc.ned.oracle.Oracles;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

public class OracleCap {
    private final int maxPace = 8;
    private Oracle oracle = null;
    private int pace = 0;
    private double time = 0;
    private double sec = 0;
    public Oracle getOracle() {
        return oracle;
    }

    public void setOracle(Oracle oracle){
        this.oracle = oracle;
    }
    public void addPace(){
        pace = Math.min(pace+1,maxPace);
    }
    public int getPace(){
        return pace;
    }
    public boolean isMaxPlace(){
        return pace >= maxPace;
    }
    public void giveOracle(){
        if (oracle == null && pace >= maxPace){
            if (time > _Random.getIntRandomNumber(3,7)){
                time = 0;
                oracle = Oracles.getInstance().getRandomOracle();
            }
        }
    }
    public Component getPaceTip(){
        return Component.translatable("oracle.ned.tip.pace_"+pace);
    }

    public void addTime(){
        sec++;
        if (sec >= 24000){
            sec = 0;
            time++;
            giveOracle();
        }
    }













    public void copyFrom(OracleCap source){
        this.oracle = source.oracle;
        this.pace = source.pace;
    }


    public void saveNBTData(CompoundTag nbt)
    {
        if (oracle == null) {
            nbt.putString("ned.oracle.id", "");
        }else {
            nbt.putString("ned.oracle.id", oracle.getId());
        }
        nbt.putInt("ned.oracle.pace",pace);
    }
    public void loadNBTData(CompoundTag nbt)
    {
        String s;
        s = nbt.getString("ned.oracle.id");
        if (!s.isEmpty()) {
            oracle = Oracles.getInstance().getOracle(s);
        }
        this.pace = nbt.getInt("ned.oracle.pace");
    }
}
