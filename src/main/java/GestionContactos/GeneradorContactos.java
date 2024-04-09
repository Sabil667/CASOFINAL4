package GestionContactos;
import java.util.Random;

public class GeneradorContactos {
    private static final String[] NOMBRES = {"Juan", "Ana", "Carlos", "Maria", "Pedro", "Laura", "Jose", "Carmen", "Manuel", "Teresa", "Antonio", "Rosa", "Javier", "Isabel", "Miguel", "Pilar", "Francisco", "Sara", "Luis", "Elena"};
    private static final String[] DOMINIOS_CORREO = {"gmail.com", "hotmail.com", "yahoo.com", "outlook.com"};
    private static final Random RANDOM = new Random();

    public static Contacto generarContactoAleatorio() {
        String nombre = NOMBRES[RANDOM.nextInt(NOMBRES.length)];
        String dominioCorreo = DOMINIOS_CORREO[RANDOM.nextInt(DOMINIOS_CORREO.length)];
        String email = nombre.toLowerCase() + "@" + dominioCorreo;
        String numeroTelefono = generarNumeroTelefonoAleatorio();
        return new Contacto(nombre, email, numeroTelefono);
    }

    private static String generarNumeroTelefonoAleatorio() {
        StringBuilder numeroTelefono = new StringBuilder();
        for (int i = 0; i < 10; i++) { // Generar un número de teléfono de 10 dígitos
            int digito = RANDOM.nextInt(10);
            numeroTelefono.append(digito);
        }
        return numeroTelefono.toString();
    }
}