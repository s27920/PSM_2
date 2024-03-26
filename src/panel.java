import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class panel extends JPanel {
    private final List<double[]> doubles;

    public panel(List<double[]> coords) {
        this.doubles = (coords);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (double[] coords : doubles) {
            int x = ((int)(this.getWidth()/2+((coords[0])*300)));
            int y = ((int)(this.getHeight()/2 + coords[1]*300));
            System.out.print(coords[0]+1 + ", ");
            g.fillOval(x, y, 10, 10);
        }
    }
    public static List<double[]> mirrorSemicircle(List<double[]> points) {
        List<double[]> mirroredPoints = new ArrayList<>();
        for (double[] point : points) {
            double x = point[0];
            double y = point[1];
            double[] mirroredPoint = {x, (x < 0) ? -y : y}; // Mirror the y-coordinate if x < 0
            mirroredPoints.add(mirroredPoint);
        }
        return mirroredPoints;
    }
}
