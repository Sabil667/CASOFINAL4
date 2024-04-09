package IGA;

import javax.swing.*;
import java.awt.event.MouseMotionAdapter;

public class RastreadorPosicionRaton extends MouseMotionAdapter {
    private JLabel etiquetaPosicionRaton;

    public RastreadorPosicionRaton(JLabel etiquetaPosicionRaton) {
        this.etiquetaPosicionRaton = etiquetaPosicionRaton;
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
        etiquetaPosicionRaton.setText("Posición del ratón: X=" + e.getX() + " Y=" + e.getY());
    }
}
