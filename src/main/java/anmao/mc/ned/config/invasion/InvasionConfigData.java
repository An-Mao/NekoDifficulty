package anmao.mc.ned.config.invasion;

public class InvasionConfigData {
    private boolean immunityNonPlayerDamage;
    private int MinDayInterval;
    private int MaxDayInterval;
    private float Probability;
    private int Duration;
    private int DayTime;
    private int Waves;
    private int MobSingleLimit;

    public boolean isImmunityNonPlayerDamage() {
        return immunityNonPlayerDamage;
    }

    public void setImmunityNonPlayerDamage(boolean immunityNonPlayerDamage) {
        this.immunityNonPlayerDamage = immunityNonPlayerDamage;
    }

    public int getMinDayInterval() {
        return MinDayInterval;
    }

    public void setMinDayInterval(int minDayInterval) {
        MinDayInterval = minDayInterval;
    }

    public int getMaxDayInterval() {
        return MaxDayInterval;
    }

    public void setMaxDayInterval(int maxDayInterval) {
        MaxDayInterval = maxDayInterval;
    }

    public float getProbability() {
        return Probability;
    }

    public void setProbability(float probability) {
        Probability = probability;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public int getDayTime() {
        return DayTime;
    }

    public void setDayTime(int dayTime) {
        DayTime = dayTime;
    }

    public int getWaves() {
        return Waves;
    }

    public void setWaves(int waves) {
        Waves = waves;
    }

    public int getMobSingleLimit() {
        return MobSingleLimit;
    }

    public void setMobSingleLimit(int mobSingleLimit) {
        MobSingleLimit = mobSingleLimit;
    }
}
