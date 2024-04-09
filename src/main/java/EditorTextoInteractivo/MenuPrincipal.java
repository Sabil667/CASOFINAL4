package EditorTextoInteractivo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import GestionContactos.GestorContactos;
import GestionContactos.GeneradorContactos;
import GestionContactos.Contacto;
import ComparadorContadorContenido.HerramientasArchivos;
import ComparadorContadorContenido.ContadorPalabras;
import ComparadorContadorContenido.EstadisticasUso;
import BuscadorPalabras.BuscadorPalabras;
import EditorTextoInteractivo.GestorArchivos;
import ValidacionYdiseño.EmailValidator;
import ValidacionYdiseño.HerramientaDibujo;

public class MenuPrincipal extends JFrame {
    private JDesktopPane desktopPane;
    private GestorContactos gestorContactos;
    private GestorArchivos gestorArchivos;
    private HerramientasArchivos herramientasArchivos;
    private ContadorPalabras contadorPalabras;
    private EstadisticasUso estadisticasUso;
    private BuscadorPalabras buscadorPalabras;
    private EmailValidator validadorEmail;
    private JTextField campoEmail;
    private JLabel indicadorValidacion;

    public MenuPrincipal(GestorArchivos gestorArchivos, HerramientasArchivos herramientasArchivos, ContadorPalabras contadorPalabras, EstadisticasUso estadisticasUso, BuscadorPalabras buscadorPalabras, GestorContactos gestorContactos) {
        this.gestorArchivos = gestorArchivos;
        this.herramientasArchivos = herramientasArchivos;
        this.contadorPalabras = contadorPalabras;
        this.estadisticasUso = estadisticasUso;
        this.buscadorPalabras = buscadorPalabras;
        this.gestorContactos = gestorContactos;
        this.desktopPane = new JDesktopPane();
        this.desktopPane.setBackground(new Color(173, 216, 230)); // Cambia el color de fondo a azul claro
        setContentPane(desktopPane);

        validadorEmail = new EmailValidator();
        campoEmail = validadorEmail.crearCampoEmail();
        indicadorValidacion = validadorEmail.getIndicadorValidacion();

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Font font = new Font("Serif", Font.BOLD, 20); // Define una nueva fuente

        JButton abrirEditorButton = new JButton("Abrir Editor");
        abrirEditorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        abrirEditorButton.setBackground(Color.BLUE); // Cambia el color de fondo a azul
        abrirEditorButton.setForeground(Color.WHITE); // Cambia el color de la fuente a blanco
        abrirEditorButton.setFont(font); // Aplica la fuente al botón

        abrirEditorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor editor = new Editor(gestorArchivos, herramientasArchivos, contadorPalabras, estadisticasUso, buscadorPalabras, "Documento " + (desktopPane.getAllFrames().length + 1));
                editor.setClosable(true);
                desktopPane.add(editor);
                editor.setSize(800, 600); // Set the size of the window
                editor.setVisible(true);
            }
        });

        JButton validadorEmailButton = new JButton("Validador de Email");
        validadorEmailButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        validadorEmailButton.setBackground(Color.BLUE); // Cambia el color de fondo a azul
        validadorEmailButton.setForeground(Color.WHITE); // Cambia el color de la fuente a blanco
        validadorEmailButton.setFont(font); // Aplica la fuente al botón

        validadorEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Validador de Email");
                frame.setLayout(new GridLayout(2, 1));
                frame.add(campoEmail);
                frame.add(indicadorValidacion);
                frame.pack();
                frame.setSize(800, 600); // Set the size of the window
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        JButton herramientaDibujoButton = new JButton("Herramienta de Dibujo");
        herramientaDibujoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        herramientaDibujoButton.setBackground(Color.BLUE); // Cambia el color de fondo a azul
        herramientaDibujoButton.setForeground(Color.WHITE); // Cambia el color de la fuente a blanco
        herramientaDibujoButton.setFont(font); // Aplica la fuente al botón

        herramientaDibujoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Herramienta de Dibujo");
                HerramientaDibujo herramientaDibujo = new HerramientaDibujo();
                frame.add(herramientaDibujo);
                frame.setSize(800, 600); // Set the size of the window
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        JButton gestionContactosButton = new JButton("Gestión de Contactos");
        gestionContactosButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        gestionContactosButton.setBackground(Color.BLUE); // Cambia el color de fondo a azul
        gestionContactosButton.setForeground(Color.WHITE); // Cambia el color de la fuente a blanco
        gestionContactosButton.setFont(font); // Aplica la fuente al botón

        gestionContactosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Set<String> nombres = new HashSet<>();
                while (nombres.size() < 20) {
                    Contacto contacto = GeneradorContactos.generarContactoAleatorio();
                    if (nombres.add(contacto.getNombre())) {
                        gestorContactos.agregarContacto(contacto);
                    }
                }

                JInternalFrame contactosFrame = new JInternalFrame("Contactos", true, true, true, true);
                JTextArea textArea = new JTextArea();
                JScrollPane scrollPane = new JScrollPane(textArea);
                contactosFrame.add(scrollPane, BorderLayout.CENTER);
                contactosFrame.setSize(800, 600);
                contactosFrame.setVisible(true);
                desktopPane.add(contactosFrame);

                for (Contacto contacto : gestorContactos.getContactos()) {
                    textArea.append("Nombre: " + contacto.getNombre() + ", Email: " + contacto.getEmail() + ", Número de teléfono: " + contacto.getNumeroTelefono() + "\n");
                }

                JButton guardarContactosButton = new JButton("Guardar Contactos");
                guardarContactosButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Specify a file to save");

                        int userSelection = fileChooser.showSaveDialog(MenuPrincipal.this);

                        if (userSelection == JFileChooser.APPROVE_OPTION) {
                            File fileToSave = fileChooser.getSelectedFile();
                            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                                for (Contacto contacto : gestorContactos.getContactos()) {
                                    writer.write("Nombre: " + contacto.getNombre() + ", Email: " + contacto.getEmail() + ", Número de teléfono: " + contacto.getNumeroTelefono() + "\n");
                                }
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    }
                });

                JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                topPanel.add(guardarContactosButton);
                contactosFrame.add(topPanel, BorderLayout.NORTH);
            }
        });

        panel.add(Box.createRigidArea(new Dimension(0, 100)));
        panel.add(abrirEditorButton);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(validadorEmailButton);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(herramientaDibujoButton);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(gestionContactosButton);
        panel.add(Box.createRigidArea(new Dimension(0, 100)));

        JInternalFrame internalFrame = new JInternalFrame();
        internalFrame.add(panel);
        internalFrame.pack();
        internalFrame.setVisible(true);

        desktopPane.add(internalFrame);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo \u25BC");
        menuArchivo.setFont(font); // Aplica la fuente al menú

        menuBar.add(menuArchivo);
        this.setJMenuBar(menuBar);

        this.setTitle("Menú Principal");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}