import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RungeKuttaODE {
    public static ArrayList<double[]> plotData = new ArrayList<>();
    static double t = -1;
    static double x = -1;
    static double y = 1;
    static double dt = 0.01;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            JFrame frame = new JFrame("Circle Panel");
            frame.setSize(1920, 1080);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            panel panel = new panel(plotData);


            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());
            frame.add(panel, BorderLayout.CENTER);
            frame.setVisible(true);
        });

        see(t,x,y,dt);
    }

    static void see(double t, double x, double y, double dt){
        plotData.add(new double[] {x, y});
        while (x < 1) {
            double[] doubles = doCalc(t,x,y,dt);
            x += doubles[0];
            y += doubles[1];
            t += dt;
            plotData.add(new double[] {x, y});

        }
//        saw(t,x,y,dt);
    }
    static void saw(double t, double x, double y, double dt){
        while (x > -1) {
            double[] doubles = doCalc(t, x, y, dt);
            x -= doubles[0];
            y += doubles[1];
            t -= dt;
            System.out.println("x: " + x + " y: " + y);
            plotData.add(new double[]{x, y});
        }
    }

    static double[] doCalc(double t, double x, double y, double dt){
        double k1x = fun(t, x);
        double k2x = fun(t + dt/2, x + dt*k1x/2);
        double k3x = fun(t + dt/2, x + dt*k2x/2);
        double k4x = fun(t + dt, x + dt*k3x);

        double k1y = fun2(t, y);
        double k2y = fun2(t + dt/2, y + dt*k1y/2);
        double k3y = fun2(t + dt/2, y + dt*k2y/2);
        double k4y = fun2(t + dt, y + dt*k3y);

        return new double[]{dt/6*(k1x + 2*k2x + 2*k3x + k4x),dt/6*(k1y + 2*k2y + 2*k3y + k4y),dt};
    }

    public static double fun(double t, double x) {
        return 3 * t * t;
    }

    public static double fun2(double t, double y) {
        return 2 * Math.sin(t); // Example function, you can modify this as needed
    }
}