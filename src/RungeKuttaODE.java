import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RungeKuttaODE {
    static final double L = 1.0;
    static final double g = 9.81;
    static volatile List<double[]> coords = new ArrayList<>();

    public static void main(String[] args) {
        double theta = Math.PI / 4;
        double omega = 0.0;
        double dt = 0.01;
        new Thread(()-> rk4(theta,omega,dt)).start();

        SwingUtilities.invokeLater(()->{
            JFrame frame = new JFrame("Circle Panel");
            frame.setSize(1920, 1080);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            panel panel = new panel();
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());
            frame.add(panel, BorderLayout.CENTER);
            frame.setVisible(true);
        });

    }
    public static List<double[]> rk4(double theta, double omega, double dt){
        while (true){
            try {
                Thread.sleep(1000/120);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            double[] result = rk4calc(theta, omega, dt);
            theta = result[0];
            omega = result[1];
            RungeKuttaODE.addToCoords(new double[]{L * Math.sin(theta), -L * Math.cos(theta)});
        }
    }
    static double[] rk4calc(double theta, double omega, double dt) {
        double k1_omega = f(theta);
        double k2_theta = omega + 0.5 * dt * k1_omega;
        double k2_omega = f(theta + 0.5 * dt * omega);
        double k3_theta = omega + 0.5 * dt * k2_omega;
        double k3_omega = f(theta + 0.5 * dt * k2_theta);
        double k4_theta = omega + dt * k3_omega;
        double k4_omega = f(theta + dt * k3_theta);
        return new double[]{theta + (dt / 6.0) * (omega + 2.0 * k2_theta + 2.0 * k3_theta + k4_theta), omega + (dt / 6.0) * (k1_omega + 2.0 * k2_omega + 2.0 * k3_omega + k4_omega)};
    }
    static double f(double theta) {
        return -(g / L) * Math.sin(theta);
    }
    static synchronized void addToCoords(double[] toAdd){
        System.out.println(Arrays.toString(toAdd));
        RungeKuttaODE.coords.add(toAdd);
    }
    static synchronized double[] takeFromCoords(){
        int size = RungeKuttaODE.coords.size()-1;
        double[] toReturn = RungeKuttaODE.coords.get(size);
        if (size > 1000) {
            RungeKuttaODE.coords= new ArrayList<>();
        }
        return toReturn;
    }
}