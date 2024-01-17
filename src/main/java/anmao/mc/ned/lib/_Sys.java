package anmao.mc.ned.lib;

public class _Sys {
    public static int BooleanToInt(boolean b){
        if (b){
            return 1;
        }
        return 0;
    }
    public static boolean IntToBoolean(int i){
        return i != 0;
    }
}
