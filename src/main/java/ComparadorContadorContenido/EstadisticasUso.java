package ComparadorContadorContenido;

import java.util.HashMap;
import java.util.Map;

public class EstadisticasUso {
    public Map<String, Integer> obtenerEstadisticas(String texto) {
        Map<String, Integer> estadisticas = new HashMap<>();
        if (texto == null || texto.isEmpty()) {
            return estadisticas;
        }
        String[] palabras = texto.split("\\s+");
        for (String palabra : palabras) {
            estadisticas.put(palabra, estadisticas.getOrDefault(palabra, 0) + 1);
        }
        return estadisticas;
    }
}
