package anmao.mc.ned.oracle;

import com.google.common.collect.ImmutableMap;

import java.util.*;

public class Oracles {
    private static final Oracles instance = new Oracles();
    private ImmutableMap<String,Oracle> ORACLES;
    private List<String> oracleKeys ;
    public Oracles(){
        regAll();
    }
    private void regAll(){
        ImmutableMap.Builder<String, Oracle> builder = ImmutableMap.builder();
        ServiceLoader<Oracle> oracles = ServiceLoader.load(Oracle.class);
        for (Oracle oracle : oracles) {
            builder.put(oracle.getId(),oracle);
        }
        ORACLES = builder.build();
        oracleKeys = new ArrayList<>(ORACLES.keySet());
    }
    public static Oracles getInstance(){return instance;}
    public Oracle getOracle(String s){
        return ORACLES.get(s);
    }
    public Oracle getRandomOracle(){
        Random random = new Random();
        String randomKey = oracleKeys.get(random.nextInt(oracleKeys.size()));
        return ORACLES.get(randomKey);
    }
}
