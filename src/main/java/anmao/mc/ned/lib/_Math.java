package anmao.mc.ned.lib;

import java.util.Random;

public class _Math {
    public static boolean isHit(float p){
        //
        return !(getRandomFloat() >= p);
    }
    public static float getRandomFloat(){
        return (float) getRandomDouble();
    }
    public static double getRandomDouble(){
        Random random = new Random();
        return random.nextDouble();
    }
    public static int getIntRandomNumber(int min,int max){
        Random random = new Random();
        return random.nextInt(min,max+1);
    }
}
