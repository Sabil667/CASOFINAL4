import EditorTextoInteractivo.Editor;
import EditorTextoInteractivo.GestorArchivos;
import EditorTextoInteractivo.MenuPrincipal;
import ComparadorContadorContenido.HerramientasArchivos;
import ComparadorContadorContenido.ContadorPalabras;
import ComparadorContadorContenido.EstadisticasUso;
import BuscadorPalabras.BuscadorPalabras;
import GestionContactos.GestorContactos;
import ValidacionYdiseño.HerramientaDibujo;
import WELCOME.PantallaBienvenida;

public class MAIN {
    public static void main(String[] args) {
        PantallaBienvenida pantallaBienvenida = new PantallaBienvenida();

        GestorArchivos gestorArchivos = new GestorArchivos();
        HerramientasArchivos herramientasArchivos = new HerramientasArchivos();
        ContadorPalabras contadorPalabras = new ContadorPalabras();
        EstadisticasUso estadisticasUso = new EstadisticasUso();
        BuscadorPalabras buscadorPalabras = new BuscadorPalabras();
        GestorContactos gestorContactos = new GestorContactos();
        HerramientaDibujo herramientaDibujo = new HerramientaDibujo();

    }
}