package ValidacionYdiseño;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private JLabel indicadorValidacion;

    public EmailValidator() {
        this.indicadorValidacion = new JLabel();
    }

    public boolean esEmailValido(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public JTextField crearCampoEmail() {
        JTextField campoEmail = new JTextField();

        campoEmail.getDocument().addDocumentListener(new DocumentListener() {
            void update() {
                String texto = campoEmail.getText();
                if (esEmailValido(texto)) {
                    indicadorValidacion.setText("Email válido");
                    indicadorValidacion.setForeground(Color.GREEN);
                } else {
                    indicadorValidacion.setText("Email no válido");
                    indicadorValidacion.setForeground(Color.RED);
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }
        });

        return campoEmail;
    }

    public JLabel getIndicadorValidacion() {
        return indicadorValidacion;
    }
}