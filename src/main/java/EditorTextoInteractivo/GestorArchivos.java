package EditorTextoInteractivo;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GestorArchivos {
    private JFileChooser fileChooser;

    public GestorArchivos() {
        this.fileChooser = new JFileChooser();
    }

    public void guardarArchivo(Editor editor) {
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                FileWriter writer = new FileWriter(selectedFile);
                writer.write(editor.getTexto());
                writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void abrirArchivo(Editor editor) {
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                String content = new String(Files.readAllBytes(Paths.get(selectedFile.getPath())));
                editor.setTexto(content);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}