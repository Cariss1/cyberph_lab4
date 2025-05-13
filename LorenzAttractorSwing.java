import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LorenzAttractorSwing extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final double SIGMA = 10.0;
    private static final double RHO = 28.0;
    private static final double BETA = 8.0 / 3.0;
    private static final double DT = 0.01;
    private static final int STEPS = 10000;

    private final List<Point> points1 = new ArrayList<>();
    private final List<Point> points2 = new ArrayList<>();

    public LorenzAttractorSwing() {
        simulateTrajectories();
        setBackground(Color.BLACK);
    }

    private void simulateTrajectories() {
        double x1 = 1.0, y1 = 1.0, z1 = 1.0;
        double x2 = 1.0001, y2 = 1.0, z2 = 1.0;

        for (int i = 0; i < STEPS; i++) {
            double dx1 = SIGMA * (y1 - x1);
            double dy1 = x1 * (RHO - z1) - y1;
            double dz1 = x1 * y1 - BETA * z1;
            x1 += dx1 * DT;
            y1 += dy1 * DT;
            z1 += dz1 * DT;

            //з малою похибкою
            double dx2 = SIGMA * (y2 - x2);
            double dy2 = x2 * (RHO - z2) - y2;
            double dz2 = x2 * y2 - BETA * z2;
            x2 += dx2 * DT;
            y2 += dy2 * DT;
            z2 += dz2 * DT;

            points1.add(toPoint(x1, z1));
            points2.add(toPoint(x2, z2));
        }
    }

    private Point toPoint(double x, double z) {
        double scale = 10;
        int px = (int) (WIDTH / 2 + x * scale);
        int py = (int) (HEIGHT / 2 - z * scale);
        return new Point(px, py);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTrajectory(g, points1, Color.GREEN);
        drawTrajectory(g, points2, Color.RED);
    }

    private void drawTrajectory(Graphics g, List<Point> points, Color color) {
        g.setColor(color);
        for (Point p : points) {
            g.fillRect(p.x, p.y, 1, 1);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Лоренц-атрактор (ефект метелика)");
        LorenzAttractorSwing panel = new LorenzAttractorSwing();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
