import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LorenzCalculator {
    public static final double SIGMA = 10.0;
    public static final double RHO = 28.0;
    public static final double BETA = 8.0 / 3.0;
    public static final double DT = 0.01;

    public static List<double[]> simulate(double x0, double y0, double z0, int steps) {
        List<double[]> trajectory = new ArrayList<>();
        double x = x0, y = y0, z = z0;

        for (int i = 0; i < steps; i++) {
            double dx = SIGMA * (y - x);
            double dy = x * (RHO - z) - y;
            double dz = x * y - BETA * z;

            x += dx * DT;
            y += dy * DT;
            z += dz * DT;

            trajectory.add(new double[]{x, y, z});
        }

        return trajectory;
    }

    public static double computeMaxDistance(List<double[]> t1, List<double[]> t2) {
        double maxDist = 0;
        for (int i = 0; i < Math.min(t1.size(), t2.size()); i++) {
            double[] p1 = t1.get(i);
            double[] p2 = t2.get(i);
            double dx = p1[0] - p2[0];
            double dy = p1[1] - p2[1];
            double dz = p1[2] - p2[2];
            double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
            if (dist > maxDist) {
                maxDist = dist;
            }
        }
        return maxDist;
    }

    public static Point toPixel(double x, double z, int width, int height, double scale) {
        int px = (int) (width / 2 + x * scale);
        int py = (int) (height / 2 - z * scale);
        return new Point(px, py);
    }
}
