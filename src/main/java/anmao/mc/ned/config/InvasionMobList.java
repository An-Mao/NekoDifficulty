package anmao.mc.ned.config;

import anmao.mc.ned.NED;
import anmao.mc.ned.cap.invasion.InvasionCDT;
import anmao.mc.ned.lib.EntityHelper;
import anmao.mc.ned.lib._Math;
import com.google.gson.Gson;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class InvasionMobList extends InvasionCDT {
    public MobListJson[] mobList;
    private final String fileName;
    public InvasionMobList(){
        fileName = System.getProperty("user.dir")+MOB_LIST_FILE_PATH;
    }
    public void _start(){
        File file = new File(fileName);
        if (!file.exists()){
            reset();
        }
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(fileName)) {
            mobList = gson.fromJson(reader, MobListJson[].class);
        } catch (IOException e) {
            NED.LOG.error(e.getMessage());
        }
    }
    private void reset(){
        MobListJson zombieData = new MobListJson("minecraft:zombie", 10, 20,1d);
        MobListJson skeletonData = new MobListJson("minecraft:skeleton", 5, 10,1d);
        MobListJson slimeData = new MobListJson("minecraft:slime", 1, 3,0.5d);
        MobListJson vexData = new MobListJson("minecraft:vex", 5, 15,0.7d);
        MobListJson vindicatorData = new MobListJson("minecraft:vindicator", 3, 7,0.6d);
        MobListJson blazeData = new MobListJson("minecraft:blaze", 3, 7,0.6d);
        MobListJson illusionerData = new MobListJson("minecraft:illusioner", 3, 7,0.6d);
        MobListJson ravagerData = new MobListJson("minecraft:ravager", 1, 3,0.3d);
        MobListJson piglin_bruteData = new MobListJson("minecraft:piglin_brute", 1, 3,0.3d);
        MobListJson phantomData = new MobListJson("minecraft:phantom", 3, 7,0.4d);
        MobListJson pillagerData = new MobListJson("minecraft:pillager", 3, 7,0.7d);
        MobListJson[] monsterArray = {zombieData,skeletonData,slimeData,vexData,vindicatorData,blazeData,illusionerData,ravagerData,piglin_bruteData,phantomData,pillagerData};
        Gson gson = new Gson();
        String jsonString = gson.toJson(monsterArray);
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(jsonString);
        } catch (IOException e) {
            NED.LOG.error(e.getMessage());
        }
    }
    public void summonMob(ServerLevel level,double x,double y,double z){
        for (MobListJson monsterData : mobList) {
            if (monsterData.probability > _Math.getRandomDouble()) {
                int count = _Math.getIntRandomNumber(monsterData.min, monsterData.max);
                for (int i = 0; i < count; i++) {
                    int radius = SPAWN_MIN_RADIUS + (int) (Math.random() * 17);
                    double angle = Math.random() * 2 * Math.PI;
                    x += Math.cos(angle) * radius;
                    z += Math.sin(angle) * radius;
                    EntityType<?> entityType = EntityHelper.getEntityType(monsterData.mid);
                    Entity entity = entityType.create(level);
                    if (entity != null) {
                        entity.setPos(x,y,z);
                        entity.getPersistentData().putBoolean(SAVE_INVASION_KEY,true);
                        level.addFreshEntity(entity);
                    }
                }
            }
        }
    }
    public class MobListJson{
        private String mid;
        private int min;
        private int max;
        private double probability;
        public MobListJson(){}
        public MobListJson(String mid,int min,int max,double probability){
            setMid(mid);
            setMin(min);
            setMax(max);
            setProbability(probability);
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getMid() {
            return mid;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMin() {
            return min;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getMax() {
            return max;
        }

        public double getProbability() {
            return probability;
        }

        public void setProbability(double probability) {
            this.probability = probability;
        }

        @Override
        public String toString() {
            return "MobListJson{" +
                    "mid='" + getMid() + '\'' +
                    ", min=" + getMin() +
                    ", max=" + getMax() +
                    ", probability=" + getProbability() +
                    '}';
        }
    }
}
