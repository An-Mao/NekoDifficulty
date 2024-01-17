package anmao.mc.ned.cap.invasion;

import anmao.mc.ned.ConstantDataTable;
import anmao.mc.ned.config.Configs;
import anmao.mc.ned.lib.ComponentHelp;
import anmao.mc.ned.lib.TimeHelper;
import anmao.mc.ned.lib._Math;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

import java.util.List;

public class Invasion extends InvasionCDT{
    private int type = TYPE_STOP;//Invasion Type
    private int lastInvasionDay = 0;//Invasion Last Day
    private int startTime = -1;//Invasion Last Start Time
    private int spawnTime = 0;//summon mob time
    private int lastCheckDay = 0;//last check day
    private int duration = 0;//invasion duration time
    private int wave = 0;//invasion wave
    private final int singleWavesTime = Configs.invasion_duration / (Configs.invasion_waves + 1);


    private boolean isInvasion(){
        return type != TYPE_STOP;
    }

    //Invasion Tick
    public void tick(Level level){
        if (!level.isClientSide) {
            int time = (int) level.getDayTime();
            int day = TimeHelper.TickToDay(time);
            if (day < lastInvasionDay) {
                lastInvasionDay = -1;
            }
            if (day < lastCheckDay) {
                lastCheckDay = -1;
            }
            if (type == TYPE_PRE) {
                if (day == lastInvasionDay) {
                    if (startTime <= TimeHelper.GetDayTime(time)) {
                        type = TYPE_START;
                        sendPlayerMsg(level, MSG_START);
                    }
                } else {
                    sendPlayerMsg(level, MSG_STOP_ERROR);
                    type = TYPE_STOP;
                }
            } else if (type == TYPE_START) {
                duration--;
                if (duration <= 0) {
                    sendPlayerMsg(level, MSG_STOP);
                    type = TYPE_STOP;
                    return;
                }
                spawnTime--;
                if (spawnTime <= 0) {
                    if (level.getServer() != null) {
                        wave++;
                        spawnTime = singleWavesTime;
                        if (wave > 0) {
                            sendPlayerMsg(level, MSG_WAVES, wave);
                            List<ServerPlayer> players = level.getServer().getPlayerList().getPlayers();
                            for (ServerPlayer player : players) {
                                Configs.InvasionMobList.summonMob(player.serverLevel(), player.getX(), player.getY(), player.getZ());
                            }
                        }
                    }
                }
            } else if (type == TYPE_STOP) {
                if (canInvasion(day)) {
                    type = TYPE_PRE;
                    if (Configs.invasion_dayTime == -1) {
                        startTime = _Math.getIntRandomNumber(ConstantDataTable.MinecraftDayMinTick, ConstantDataTable.MinecraftDayMaxTick);
                    } else {
                        startTime = Configs.invasion_dayTime;
                    }
                    duration = Configs.invasion_duration;
                    lastInvasionDay = day;
                    spawnTime = 0;
                    wave = -1;
                    sendPlayerMsg(level, MSG_PRE, TimeHelper.FormatDate(TimeHelper.GetDayTime(time)), TimeHelper.FormatDate(startTime), TimeHelper.tickToTime(duration), Configs.invasion_waves);
                }
            }
        }
    }
    private void sendPlayerMsg(Level level,String key,Object... data){
        if (level.getServer() != null) {
            List<ServerPlayer> players = level.getServer().getPlayerList().getPlayers();
            for (ServerPlayer player:players) {
                String s = Component.translatable(key).getString();
                ComponentHelp.sendFormatMsg(player,s,data);
            }
        }
    }

    public boolean canInvasion(int day){
        if (isInvasion() || day <= lastCheckDay){
            return false;
        }
        lastCheckDay = day;
        if (day - lastInvasionDay >= Configs.invasion_maxDayInterval){
            return true;
        }
        return day > lastInvasionDay && day - lastInvasionDay > Configs.invasion_minDayInterval && _Math.isHit(Configs.invasion_probability);
    }
    public void saveNBTData(CompoundTag nbt)
    {
        CompoundTag tag = new CompoundTag();
        tag.putInt("type", type);
        tag.putInt("lastInvasionDay", lastInvasionDay);
        tag.putInt("startTime", startTime);
        tag.putInt("spawnTime", spawnTime);
        tag.putInt("lastCheckDay", lastCheckDay);
        tag.putInt("duration", duration);
        tag.putInt("wave", wave);
        nbt.put(SAVE_KEY,tag);
    }
    public void loadNBTData(CompoundTag nbt)
    {
        CompoundTag tag = nbt.getCompound(SAVE_KEY);
        type = tag.getInt("type");
        lastInvasionDay = tag.getInt("lastInvasionDay");
        startTime = tag.getInt("startTime");
        spawnTime = tag.getInt("spawnTime");
        lastCheckDay = tag.getInt("lastCheckDay");
        duration = tag.getInt("duration");
        wave = tag.getInt("wave");
    }
}
