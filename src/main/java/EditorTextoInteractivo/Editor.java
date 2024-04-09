package EditorTextoInteractivo;

import ComparadorContandorContenido.HerramientasArchivos;
import ComparadorContandorContenido.ContadorPalabras;
import ComparadorContandorContenido.EstadisticasUso;
import BuscadorPalabras.BuscadorPalabras;
import IGA.RastreadorPosicionRaton;
import IGA.BarraDesplazamientoInteractiva;
import ValidacionYdiseño.EmailValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Editor extends JInternalFrame {
    private JTextArea areaTexto;
    private GestorArchivos gestorArchivos;
    private HerramientasArchivos herramientasArchivos;
    private ContadorPalabras contadorPalabras;
    private EstadisticasUso estadisticasUso;
    private BuscadorPalabras buscadorPalabras;
    private JLabel etiquetaPosicionRaton;
    private JLabel etiquetaProgresoDocumento;
    private EmailValidator validadorEmail;
    private JTextField campoEmail;
    private JLabel indicadorValidacion;

    public Editor(GestorArchivos gestorArchivos, HerramientasArchivos herramientasArchivos, ContadorPalabras contadorPalabras, EstadisticasUso estadisticasUso, BuscadorPalabras buscadorPalabras, String s) {
        super("Editor de Texto", true, true, true, true);
        this.gestorArchivos = gestorArchivos;
        this.herramientasArchivos = herramientasArchivos;
        this.contadorPalabras = contadorPalabras;
        this.estadisticasUso = estadisticasUso;
        this.buscadorPalabras = buscadorPalabras;
        this.areaTexto = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);

        this.etiquetaPosicionRaton = new JLabel();
        this.etiquetaProgresoDocumento = new JLabel();
        JPanel panelSur = new JPanel(new GridLayout(3, 1));
        panelSur.add(etiquetaPosicionRaton);
        panelSur.add(etiquetaProgresoDocumento);

        validadorEmail = new EmailValidator();
        campoEmail = validadorEmail.crearCampoEmail();
        indicadorValidacion = validadorEmail.getIndicadorValidacion();
        panelSur.add(campoEmail);
        panelSur.add(indicadorValidacion);

        this.add(panelSur, BorderLayout.SOUTH);
        RastreadorPosicionRaton rastreadorPosicionRaton = new RastreadorPosicionRaton(etiquetaPosicionRaton);
        this.areaTexto.addMouseMotionListener(rastreadorPosicionRaton);

        JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
        BarraDesplazamientoInteractiva barraDesplazamientoInteractiva = new BarraDesplazamientoInteractiva(etiquetaProgresoDocumento, scrollBar);
        scrollBar.addAdjustmentListener(barraDesplazamientoInteractiva);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Archivo \u25BC"); // \u25BC es el código Unicode para la flecha hacia abajo
        JMenuItem guardar = new JMenuItem("Guardar");
        JMenuItem abrir = new JMenuItem("Abrir");
        JMenuItem comparar = new JMenuItem("Comparar");
        JMenuItem contarPalabrasItem = new JMenuItem("Contar palabras");
        JMenuItem buscarPalabraItem = new JMenuItem("Buscar palabra");

        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestorArchivos.guardarArchivo(Editor.this);
            }
        });

        abrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestorArchivos.abrirArchivo(Editor.this);
            }
        });

        comparar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File archivo1 = herramientasArchivos.seleccionarArchivo();
                File archivo2 = herramientasArchivos.seleccionarArchivo();
                boolean sonIguales = herramientasArchivos.compararArchivos(archivo1, archivo2);
                JOptionPane.showMessageDialog(Editor.this, sonIguales ? "Los archivos son iguales" : "Los archivos son diferentes");
            }
        });

        contarPalabrasItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroPalabras = contadorPalabras.contarPalabras(areaTexto.getText());
                JOptionPane.showMessageDialog(Editor.this, "El número de palabras es: " + numeroPalabras);
            }
        });

        buscarPalabraItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String palabra = JOptionPane.showInputDialog(Editor.this, "Ingrese la palabra que desea buscar:");
                int ocurrencias = buscadorPalabras.buscarPalabra(areaTexto.getText(), palabra);
                JOptionPane.showMessageDialog(Editor.this, "La palabra '" + palabra + "' aparece " + ocurrencias + " veces.");
            }
        });

        menu.add(guardar);
        menu.add(abrir);
        menu.add(comparar);
        menu.add(contarPalabrasItem);
        menu.add(buscarPalabraItem);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        this.setSize(500, 500);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent e) {
                e.getInternalFrame().dispose();
            }
        });
        this.setVisible(true);
    }

    public Editor(String s) {
        super(s, true, true, true, true);
        this.areaTexto = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }

    public void setTexto(String texto) {
        this.areaTexto.setText(texto);
    }

    public String getTexto() {
        return this.areaTexto.getText();
    }
}