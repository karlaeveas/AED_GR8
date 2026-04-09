import javax.swing.*;
import java.awt.*;

class SierpinskiPanel extends JPanel {
    private int level;

    public SierpinskiPanel(int level) {
        this.level = level;
        setBackground(Color.WHITE);
    }

    public void drawTriangle(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int nivel) {
        if (nivel == 0) {
            int[] xPoints = {x1, x2, x3};
            int[] yPoints = {y1, y2, y3};
            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            int mx12 = (x1 + x2) / 2;
            int my12 = (y1 + y2) / 2;

            int mx23 = (x2 + x3) / 2;
            int my23 = (y2 + y3) / 2;

            int mx31 = (x3 + x1) / 2;
            int my31 = (y3 + y1) / 2;

            drawTriangle(g, x1, y1, mx12, my12, mx31, my31, nivel - 1);
            drawTriangle(g, mx12, my12, x2, y2, mx23, my23, nivel - 1);
            drawTriangle(g, mx31, my31, mx23, my23, x3, y3, nivel - 1);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        int width = getWidth();
        int height = getHeight();

        drawTriangle(g,
                width / 2, 20,
                20, height - 20,
                width - 20, height - 20,
                level
        );

        g.drawString("Nivel: " + level, 10, 15);
    }
}

public class SierpinskiMultiNivel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Triángulos de Sierpinski");
        frame.setLayout(new GridLayout(1, 3));

        frame.add(new SierpinskiPanel(4));
        frame.add(new SierpinskiPanel(6));
        frame.add(new SierpinskiPanel(8));

        frame.setSize(900, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}