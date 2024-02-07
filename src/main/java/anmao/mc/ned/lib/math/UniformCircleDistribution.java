package anmao.mc.ned.lib.math;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class UniformCircleDistribution extends _Math{
    private static double rotationAngle = 0;
    public static ArrayList<Point2D.Double> distributePoints(double circleRadius , int pointNumber) {
        ArrayList<Point2D.Double> points = new ArrayList<>();
        for (int i = 0; i < pointNumber; i++) {
            double angle = rotationAngle + TWICE_PI * i / pointNumber;
            double x = circleRadius * Math.cos(angle);
            double y = circleRadius * Math.sin(angle);
            points.add(new Point2D.Double(x, y));
        }
        rotationAngle += Math.PI / 360;
        if (rotationAngle >= TWICE_PI) {
            rotationAngle = 0.0;
        }
        return points;
    }
}
