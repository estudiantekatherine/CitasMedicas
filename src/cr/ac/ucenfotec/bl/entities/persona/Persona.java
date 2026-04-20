package cr.ac.ucenfotec.bl.entities.persona;

import java.util.Objects;

/**
 * Clase abstracta que representa a una persona dentro del sistema de citas médicas.
 *
 * <p><b>Concepto POO — Abstracción:</b> Esta clase no puede instanciarse
 * directamente porque una "persona genérica" no existe en el dominio del negocio;
 * solo existen {@link Medico} y {@link Paciente}. La abstracción captura los
 * atributos comunes (nombre, cédula, contacto) evitando duplicación de código.</p>
 *
 * <p><b>Concepto POO — Encapsulamiento:</b> Todos los atributos son {@code private};
 * el acceso externo se realiza únicamente a través de los getters y setters
 * documentados, protegiendo la integridad del estado interno.</p>
 *
 * <p><b>Concepto POO — Identidad de objetos (equals):</b> Dos personas son
 * consideradas iguales si y solo si comparten el mismo número de cédula,
 * que es el identificador único en el mundo real.</p>
 *
 */
public abstract class Persona {

    /** Nombre de pila de la persona */
    private String nombre;

    /** Apellido de la persona */
    private String apellido;

    /** Número de cédula de identidad — actúa como identificador único real */
    private String numeroCedula;

    /** Número de celular para contacto */
    private String numeroCelular;

    /** Dirección de correo electrónico */
    private String correoElectronico;


      // Constructores
    /**
     * Constructor por defecto.
     * Inicializa todos los campos de texto con cadena vacía para evitar
     * valores {@code null} en operaciones posteriores.
     */
    public Persona() {
        this.nombre            = "";
        this.apellido          = "";
        this.numeroCedula      = "";
        this.numeroCelular     = "";
        this.correoElectronico = "";
    }

    /**
     * Constructor que inicializa todos los atributos de la persona.
     *
     * @param nombre             Nombre de pila de la persona.
     * @param apellido           Apellido de la persona.
     * @param numeroCedula       Número de cédula de identidad.
     * @param numeroCelular      Número de celular de contacto.
     * @param correoElectronico  Dirección de correo electrónico.
     */
    public Persona(String nombre, String apellido, String numeroCedula,
                   String numeroCelular, String correoElectronico) {
        this.nombre            = nombre;
        this.apellido          = apellido;
        this.numeroCedula      = numeroCedula;
        this.numeroCelular     = numeroCelular;
        this.correoElectronico = correoElectronico;
    }

    // Getters y Setters
    /**
     * Retorna el nombre de pila de la persona.
     * @return Nombre de pila.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de pila de la persona.
     * @param nombre Nuevo nombre de pila.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el apellido de la persona.
     * @return Apellido.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido de la persona.
     * @param apellido Nuevo apellido.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Retorna el número de cédula de la persona.
     * @return Número de cédula de identidad.
     */
    public String getNumeroCedula() {
        return numeroCedula;
    }

    /**
     * Establece el número de cédula de la persona.
     * @param numeroCedula Nuevo número de cédula.
     */
    public void setNumeroCedula(String numeroCedula) {
        this.numeroCedula = numeroCedula;
    }

    /**
     * Retorna el número de celular de la persona.
     * @return Número de celular.
     */
    public String getNumeroCelular() {
        return numeroCelular;
    }

    /**
     * Establece el número de celular de la persona.
     * @param numeroCelular Nuevo número de celular de contacto.
     */
    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    /**
     * Retorna el correo electrónico de la persona.
     * @return Dirección de correo electrónico.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Establece el correo electrónico de la persona.
     * @param correoElectronico Nueva dirección de correo electrónico.
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }


    // Métodos abstractos — obligan a cada subclase a definir su rol
      /**
     * Retorna el rol que cumple esta persona dentro del sistema.
     *
     * <p><b>Concepto POO — Polimorfismo:</b> Cada subclase ({@link Medico},
     * {@link Paciente}) sobre-escribe este método para devolver su propio rol,
     * permitiendo tratar cualquier {@code Persona} de forma genérica y obtener
     * el rol correcto en tiempo de ejecución.</p>
     *
     * @return Cadena descriptiva del rol (p.ej. "Médico", "Paciente").
     */
    public abstract String obtenerRol();


    // Identidad de objetos: equals y hashCode
     /**
     * Compara esta persona con otro objeto para determinar si son la misma
     * persona del mundo real, usando el número de cédula como criterio único.
     *
     * <p><b>Concepto POO — Identidad de objetos:</b> Java por defecto compara
     * referencias de memoria. Al sobre-escribir {@code equals} definimos que
     * dos objetos {@code Persona} son "iguales" cuando representan a la misma
     * persona real (misma cédula), independientemente de que sean instancias
     * distintas en memoria.</p>
     *
     * @param objeto Objeto a comparar.
     * @return {@code true} si ambos tienen la misma cédula; {@code false} si no.
     */
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null || getClass() != objeto.getClass()) return false;
        Persona otraPersona = (Persona) objeto;
        return Objects.equals(numeroCedula, otraPersona.numeroCedula);
    }

    /**
     * Retorna el código hash basado en el número de cédula.
     *
     * <p>Siempre que se sobre-escriba {@code equals}, Java exige también
     * sobre-escribir {@code hashCode} para que los objetos iguales produzcan
     * el mismo hash, garantizando correcto funcionamiento en colecciones
     * como {@code HashMap} o {@code HashSet}.</p>
     *
     * @return Código hash de la cédula.
     */
    @Override
    public int hashCode() {
        return Objects.hash(numeroCedula);
    }


    // toString
    /**
     * Retorna una representación textual de la persona con todos sus atributos.
     *
     * <p><b>Sobre-escritura de métodos:</b> Se sobre-escribe el método
     * {@code toString()} de {@link Object} para proporcionar una salida
     * legible y útil en el contexto del sistema.</p>
     *
     * @return Cadena con los datos de la persona.
     */
    @Override
    public String toString() {
        return  "  Rol             : " + obtenerRol()                  + "\n" +
                "  Nombre completo : " + nombre + " " + apellido       + "\n" +
                "  Cédula          : " + numeroCedula                  + "\n" +
                "  Celular         : " + numeroCelular                 + "\n" +
                "  Correo          : " + correoElectronico;
    }
}