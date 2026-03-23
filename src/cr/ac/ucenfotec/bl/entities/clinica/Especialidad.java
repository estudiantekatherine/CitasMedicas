package cr.ac.ucenfotec.bl.entities.clinica;

/**
 * Representa la especialidad médica de un doctor
 *
 */
public class Especialidad {

    /** Código único que identifica la especialidad */
    private String codigoEspecialidad;

    /** Nombre de la especialidad médica */
    private String nombreEspecialidad;

    /** Descripción detallada del área que cubre la especialidad */
    private String descripcionEspecialidad;

    // Constructores
    /**
     * Constructor por defecto.
     */
    public Especialidad() {
    }

    /**
     * Constructor que inicializa todos los atributos de la especialidad.
     *
     * @param codigoEspecialidad      Código único de la especialidad.
     * @param nombreEspecialidad      Nombre de la especialidad médica.
     * @param descripcionEspecialidad Descripción del área que cubre.
     */
    public Especialidad(String codigoEspecialidad, String nombreEspecialidad,
                        String descripcionEspecialidad) {
        this.codigoEspecialidad      = codigoEspecialidad;
        this.nombreEspecialidad      = nombreEspecialidad;
        this.descripcionEspecialidad = descripcionEspecialidad;
    }

    // Getters y Setters
    /**
     * Retorna el código único de la especialidad.
     * @return Código de la especialidad.
     */
    public String getCodigoEspecialidad() {
        return codigoEspecialidad; }

    /**
     * Establece el código de la especialidad.
     * @param codigoEspecialidad Nuevo código de la especialidad.
     */
    public void setCodigoEspecialidad(String codigoEspecialidad) {
        this.codigoEspecialidad = codigoEspecialidad;
    }

    /**
     * Retorna el nombre de la especialidad.
     * @return Nombre de la especialidad médica.
     */
    public String getNombreEspecialidad() {
        return nombreEspecialidad; }

    /**
     * Establece el nombre de la especialidad.
     * @param nombreEspecialidad Nombre de la especialidad médica.
     */
    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    /**
     * Retorna la descripción de la especialidad.
     * @return Descripción del área médica.
     */
    public String getDescripcionEspecialidad() {
        return descripcionEspecialidad; }

    /**
     * Establece la descripción de la especialidad.
     * @param descripcionEspecialidad Descripción del área que cubre.
     */
    public void setDescripcionEspecialidad(String descripcionEspecialidad) {
        this.descripcionEspecialidad = descripcionEspecialidad;
    }

    // toString
    @Override
    public String toString() {
        return  "  Código           : " + codigoEspecialidad      + "\n" +
                "  Nombre           : " + nombreEspecialidad       + "\n" +
                "  Descripción      : " + descripcionEspecialidad;
    }
}
