package anmao.mc.ned.oracles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Oracles {
    private static final Oracles instance = new Oracles();
    private final HashMap<String,Oracle> ORACLES = new HashMap<>();
    public Oracles(){
        regAll();
    }
    private void regAll(){
        //
    }
    private void _reg(Oracle oracle){
        ORACLES.put(oracle.getId(),oracle);
    }
    public static Oracles getInstance(){return instance;}
    public Oracle getOracle(String s){
        return ORACLES.get(s);
    }
    public Oracle getRandomOracle(){
        List<String> keyList = new ArrayList<>(ORACLES.keySet());
        Random random = new Random();
        String randomKey = keyList.get(random.nextInt(keyList.size()));
        return ORACLES.get(randomKey);
    }
}
