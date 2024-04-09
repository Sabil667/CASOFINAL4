package IGA;

import javax.swing.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class BarraDesplazamientoInteractiva implements AdjustmentListener {
    private JLabel etiquetaProgreso;
    private JScrollBar barraDesplazamiento;

    public BarraDesplazamientoInteractiva(JLabel etiquetaProgreso, JScrollBar barraDesplazamiento) {
        this.etiquetaProgreso = etiquetaProgreso;
        this.barraDesplazamiento = barraDesplazamiento;
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        int max = barraDesplazamiento.getMaximum();
        int extent = barraDesplazamiento.getModel().getExtent();
        float percentage = (float) barraDesplazamiento.getValue() / (max - extent);
        etiquetaProgreso.setText(String.format("Progreso del documento: %.2f%%", percentage * 100));
    }
}