package ValidacionYdiseño;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class HerramientaDibujo extends JPanel {
    private BufferedImage imagen;
    private Graphics2D graficos;

    public HerramientaDibujo() {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                dibujar(e.getX(), e.getY(), true);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                dibujar(e.getX(), e.getY(), true);
            }
        });
    }

    protected void paintComponent(Graphics g) {
        if (imagen == null) {
            imagen = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_ARGB);
            graficos = (Graphics2D) imagen.getGraphics();
            graficos.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            limpiar();
        }
        g.drawImage(imagen, 0, 0, null);
    }

    public void limpiar() {
        graficos.setPaint(Color.white);
        graficos.fillRect(0, 0, getSize().width, getSize().height);
        graficos.setPaint(Color.black);
        repaint();
    }

    private void dibujar(int x, int y, boolean pressed) {
        if (pressed) {
            graficos.fillOval(x, y, 4, 4);
        }
        repaint();
    }
}