package cr.ac.ucenfotec.bl.entities.clinica;

import java.util.Objects;

/**
 * Representa la especialidad médica de un doctor.
 *
 * <p><b>Concepto POO — Composición:</b> La especialidad es parte integral del
 * {@link cr.ac.ucenfotec.bl.entities.persona.Medico}; se crea al mismo tiempo
 * que el médico y no tiene existencia independiente en el modelo.</p>
 *
 * <p><b>Concepto POO — Identidad de objetos:</b> Dos especialidades son iguales
 * si comparten el mismo código, independientemente de que sean instancias
 * distintas en memoria.</p>
 *
 */
public class Especialidad {

    /** Código único que identifica la especialidad (p.ej. "ESP-001") */
    private String codigoEspecialidad;

    /** Nombre de la especialidad médica (p.ej. "Cardiología") */
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
        return codigoEspecialidad;
    }

    /**
     * Establece el código único de la especialidad.
     * @param codigoEspecialidad Nuevo código de la especialidad.
     */
    public void setCodigoEspecialidad(String codigoEspecialidad) {
        this.codigoEspecialidad = codigoEspecialidad;
    }

    /**
     * Retorna el nombre de la especialidad médica.
     * @return Nombre de la especialidad.
     */
    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    /**
     * Establece el nombre de la especialidad médica.
     * @param nombreEspecialidad Nuevo nombre de la especialidad.
     */
    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    /**
     * Retorna la descripción detallada de la especialidad.
     * @return Descripción del área médica.
     */
    public String getDescripcionEspecialidad() {
        return descripcionEspecialidad;
    }

    /**
     * Establece la descripción de la especialidad.
     * @param descripcionEspecialidad Descripción del área que cubre.
     */
    public void setDescripcionEspecialidad(String descripcionEspecialidad) {
        this.descripcionEspecialidad = descripcionEspecialidad;
    }


    // Identidad de objetos
        /**
     * Dos especialidades son iguales si comparten el mismo código único.
     *
     * @param objeto Objeto a comparar.
     * @return {@code true} si ambas tienen el mismo código de especialidad.
     */
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null || getClass() != objeto.getClass()) return false;
        Especialidad otraEspecialidad = (Especialidad) objeto;
        return Objects.equals(codigoEspecialidad, otraEspecialidad.codigoEspecialidad);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(codigoEspecialidad);
    }


    // toString
       /**
     * Retorna una representación textual de la especialidad con todos sus atributos.
     *
     * @return Cadena con los datos de la especialidad.
     */
    @Override
    public String toString() {
        return  "  Código          : " + codigoEspecialidad      + "\n" +
                "  Nombre          : " + nombreEspecialidad       + "\n" +
                "  Descripción     : " + descripcionEspecialidad;
    }
}