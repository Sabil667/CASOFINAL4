package WELCOME;

import BuscadorPalabras.BuscadorPalabras;
import ComparadorContadorContenido.ContadorPalabras;
import ComparadorContadorContenido.EstadisticasUso;
import ComparadorContadorContenido.HerramientasArchivos;
import EditorTextoInteractivo.GestorArchivos;
import EditorTextoInteractivo.MenuPrincipal;
import GestionContactos.GestorContactos;
import ValidacionYdiseño.HerramientaDibujo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaBienvenida extends JFrame {
    public PantallaBienvenida() {
        super("Bienvenido a EditEon");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel logoLabel = new JLabel("GS", SwingConstants.CENTER);
        logoLabel.setFont(new Font("Serif", Font.BOLD, 50));
        logoLabel.setForeground(Color.BLUE);

        JLabel nombreAplicacion = new JLabel("EditEon", SwingConstants.CENTER);
        nombreAplicacion.setFont(new Font("Serif", Font.BOLD, 36));
        nombreAplicacion.setForeground(Color.BLUE);

        JButton botonAcceso = new JButton("Acceder a la aplicación");
        botonAcceso.setFont(new Font("Serif", Font.BOLD, 18));
        botonAcceso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestorArchivos gestorArchivos = new GestorArchivos();
                HerramientasArchivos herramientasArchivos = new HerramientasArchivos();
                ContadorPalabras contadorPalabras = new ContadorPalabras();
                EstadisticasUso estadisticasUso = new EstadisticasUso();
                BuscadorPalabras buscadorPalabras = new BuscadorPalabras();
                GestorContactos gestorContactos = new GestorContactos();
                HerramientaDibujo herramientaDibujo = new HerramientaDibujo();

                MenuPrincipal menuPrincipal = new MenuPrincipal(gestorArchivos, herramientasArchivos, contadorPalabras, estadisticasUso, buscadorPalabras, gestorContactos);
                menuPrincipal.setVisible(true);
                setVisible(false); // Ocultar la pantalla de bienvenida
            }
        });

        panel.add(logoLabel, BorderLayout.NORTH);
        panel.add(nombreAplicacion, BorderLayout.CENTER);
        panel.add(botonAcceso, BorderLayout.SOUTH);

        getContentPane().add(panel, BorderLayout.CENTER);

        setVisible(true);
    }
}