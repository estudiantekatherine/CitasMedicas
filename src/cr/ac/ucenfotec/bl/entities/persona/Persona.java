package cr.ac.ucenfotec.bl.entities.persona;

/**
 * Clase abstracta que representa a una persona en el sistema de citas médicas
 *
 */
public abstract class Persona {

    /** Nombre de pila de la persona */
    private String nombre;

    /** Apellido de la persona */
    private String apellido;

    /** Número de cédula de identidad único en el sistema */
    private String numeroCedula;

    /** Número de celular para contacto */
    private String numeroCelular;

    /** Dirección de correo electrónico */
    private String correoElectronico;


    // Constructores
    /**
     * Constructor por defecto
     */
    public Persona() {
        this.nombre            = "";
        this.apellido          = "";
        this.numeroCedula      = "";
        this.numeroCelular     = "";
        this.correoElectronico = "";
    }

    /**
     * Constructor que inicializa todos los atributos de la persona
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
     * Retorna el nombre de la persona
     * @return Nombre de pila
     */
    public String getNombre() {
        return nombre; }

    /**
     * Establece el nombre de la persona
     * @param nombre Nuevo nombre de pila
     */
    public void setNombre(String nombre) {
        this.nombre = nombre; }

    /**
     * Retorna el apellido de la persona
     * @return Apellido
     */
    public String getApellido() {
        return apellido; }

    /**
     * Establece el apellido de la persona
     * @param apellido Nuevo apellido
     */
    public void setApellido(String apellido) {
        this.apellido = apellido; }

    /**
     * Retorna el número de cédula de la persona
     * @return Número de cédula
     */
    public String getNumeroCedula() {
        return numeroCedula; }

    /**
     * Establece el número de cédula de la persona
     * @param numeroCedula Número de cédula de identidad
     */
    public void setNumeroCedula(String numeroCedula) {
        this.numeroCedula = numeroCedula; }

    /**
     * Retorna el número de celular de la persona
     * @return Número de celular
     */
    public String getNumeroCelular() {
        return numeroCelular; }

    /**
     * Establece el número de celular de la persona
     * @param numeroCelular Número de celular de contacto
     */
    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular; }

    /**
     * Retorna el correo electrónico de la persona
     * @return Correo electrónico
     */
    public String getCorreoElectronico() {
        return correoElectronico; }

    /**
     * Establece el correo electrónico de la persona
     * @param correoElectronico Dirección de correo electrónico


     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }


    public abstract String obtenerRol();


    // toString
    @Override
    public String toString() {
        return  "  Rol             : " + obtenerRol()                  + "\n" +
                "  Nombre completo : " + nombre + " " + apellido       + "\n" +
                "  Cédula          : " + numeroCedula                  + "\n" +
                "  Celular         : " + numeroCelular                 + "\n" +
                "  Correo          : " + correoElectronico;
    }
}
