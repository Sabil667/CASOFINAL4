package ComparadorContadorContenido;

public class ContadorPalabras {
    public int contarPalabras(String texto) {
        if (texto == null || texto.isEmpty()) {
            return 0;
        }
        String[] palabras = texto.split("\\s+");
        return palabras.length;
    }
}