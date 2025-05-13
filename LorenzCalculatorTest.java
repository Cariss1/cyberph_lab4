import org.junit.jupiter.api.Test;
import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LorenzCalculatorTest {

    @Test
    public void testTrajectoryLength() {
        List<double[]> traj = LorenzCalculator.simulate(1.0, 1.0, 1.0, 1000);
        assertEquals(1000, traj.size(), "Траєкторія має містити 1000 точок");
    }

    @Test
    public void testEffectOfButterfly() {
        List<double[]> t1 = LorenzCalculator.simulate(1.0, 1.0, 1.0, 5000);
        List<double[]> t2 = LorenzCalculator.simulate(1.00001, 1.0, 1.0, 5000);

        double dist = LorenzCalculator.computeMaxDistance(t1, t2);

        assertTrue(dist > 10, "Очікуємо сильне розходження траєкторій через малу похибку");
    }

    @Test
    public void testPixelProjection() {
        Point p = LorenzCalculator.toPixel(1.5, 2.0, 800, 600, 10);
        assertEquals(new Point(800 / 2 + 15, 600 / 2 - 20), p, "Координати мають бути правильно проєктовані");
    }
}
