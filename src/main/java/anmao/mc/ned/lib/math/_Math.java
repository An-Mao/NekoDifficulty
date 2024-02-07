package anmao.mc.ned.lib.math;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class _Math {
    public static final double TWICE_PI = 2 * Math.PI;
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
    public static ArrayList<Point2D.Double> getPosWithCircle(double circleRadius , int pointNumber){
        ArrayList<Point2D.Double> points = new ArrayList<>();
        for (int i = 0; i < pointNumber; i++) {
            double angle = TWICE_PI * i / pointNumber;
            double x = circleRadius * Math.cos(angle);
            double y = circleRadius * Math.sin(angle);
            points.add(new Point2D.Double(x, y));
        }
        return points;
    }
}
