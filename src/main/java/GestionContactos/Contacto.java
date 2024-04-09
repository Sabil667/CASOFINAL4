package GestionContactos;

public class Contacto {
    private String nombre;
    private String email;
    private String numeroTelefono;

    public Contacto(String nombre, String email, String numeroTelefono) {
        this.nombre = nombre;
        this.email = email;
        this.numeroTelefono = numeroTelefono;
    }

    // Getters y setters para los atributos

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
}