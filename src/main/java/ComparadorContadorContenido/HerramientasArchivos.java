package ComparadorContadorContenido;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class HerramientasArchivos {
    private JFileChooser fileChooser;

    public HerramientasArchivos() {
        this.fileChooser = new JFileChooser();
    }

    public File seleccionarArchivo() {
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    public boolean compararArchivos(File archivo1, File archivo2) {
        try {
            byte[] contenidoArchivo1 = Files.readAllBytes(archivo1.toPath());
            byte[] contenidoArchivo2 = Files.readAllBytes(archivo2.toPath());

            return Arrays.equals(contenidoArchivo1, contenidoArchivo2);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}