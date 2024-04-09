package GestionContactos;

import javax.swing.*;

public class VentanaContactos extends JFrame {
    private GestorContactos gestorContactos;

    public VentanaContactos(GestorContactos gestorContactos) {
        this.gestorContactos = gestorContactos;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (Contacto contacto : gestorContactos.getContactos()) {
            JLabel label = new JLabel("Nombre: " + contacto.getNombre() + ", Email: " + contacto.getEmail() + ", Número de teléfono: " + contacto.getNumeroTelefono());
            panel.add(label);
        }

        this.add(panel);

        this.setTitle("Agenda de Contactos");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centrar en la pantalla
        this.setVisible(true);
    }
}