package BuscadorPalabras;

public class BuscadorPalabras {
    public int buscarPalabra(String texto, String palabra) {
        if (texto == null || texto.isEmpty() || palabra == null || palabra.isEmpty()) {
            return 0;
        }
        String[] palabras = texto.split("\\s+");
        int contador = 0;
        for (String p : palabras) {
            if (p.equalsIgnoreCase(palabra)) {
                contador++;
            }
        }
        return contador;
    }
}