import javax.swing.*;
import java.awt.*;

public class panel extends JPanel implements Runnable {
    public panel() {
        new Thread((this)).start();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;
        double[] coords = RungeKuttaODE.takeFromCoords();
        int x = ((int)(((coords[0]+1.5)*500)));
        int y = ((int)(((coords[1])*-1)*500));
        graphics2D.fillOval(x, y, 30, 30);
    }
    @Override
    public void run() {
        while (true){
            repaint();
        try{
            Thread.sleep(1000/120);
        }catch (InterruptedException ex){
            throw new RuntimeException(ex);
            }
        }
    }
}
