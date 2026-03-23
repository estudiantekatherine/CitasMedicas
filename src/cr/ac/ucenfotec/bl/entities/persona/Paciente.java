package cr.ac.ucenfotec.bl.entities.persona;

import java.time.LocalDate;
import java.time.Period;

/**
 * Representa a un paciente dentro del sistema de citas médicas.
 *
 */
public class Paciente extends Persona {

    /** Código único que identifica al paciente en el sistema */
    private String codigoPaciente;

    /** Fecha de nacimiento del paciente */
    private LocalDate fechaNacimiento;

    /** Tipo de sangre del paciente */
    private String tipoSangre;


    // Constructores
    /**
     * Constructor por defecto.
     */
    public Paciente() {
        super();
    }

    /**
     * Constructor que inicializa todos los atributos del paciente.
     *
     * @param nombre            Nombre de pila.
     * @param apellido          Apellido del paciente.
     * @param numeroCedula      Número de cédula.
     * @param numeroCelular     Número de celular.
     * @param correoElectronico Correo electrónico.
     * @param codigoPaciente    Código único del paciente.
     * @param fechaNacimiento   Fecha de nacimiento (LocalDate).
     * @param tipoSangre        Tipo de sangre del paciente.
     */
    public Paciente(String nombre, String apellido, String numeroCedula,
                    String numeroCelular, String correoElectronico,
                    String codigoPaciente, LocalDate fechaNacimiento,
                    String tipoSangre) {
        super(nombre, apellido, numeroCedula, numeroCelular, correoElectronico);
        this.codigoPaciente  = codigoPaciente;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSangre      = tipoSangre;
    }


    // Getters y Setters
    /**
     * Retorna el código único del paciente.
     * @return Código del paciente.
     */
    public String getCodigoPaciente() {
        return codigoPaciente; }

    /**
     * Establece el código único del paciente.
     * @param codigoPaciente Nuevo código del paciente.
     */
    public void setCodigoPaciente(String codigoPaciente) {
        this.codigoPaciente = codigoPaciente; }

    /**
     * Retorna la fecha de nacimiento del paciente.
     * @return Fecha de nacimiento como {@link LocalDate}.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento; }

    /**
     * Establece la fecha de nacimiento del paciente.
     * @param fechaNacimiento Nueva fecha de nacimiento.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Retorna el tipo de sangre del paciente.
     * @return Tipo de sangre (ej. O+, A-, B+).
     */
    public String getTipoSangre() {
        return tipoSangre; }

    /**
     * Establece el tipo de sangre del paciente.
     * @param tipoSangre Tipo de sangre del paciente.
     */
    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre; }


    // Calculo de la edad
    /**
     * Calcula y retorna la edad actual del paciente en años
     *
     *
     * @return Edad del paciente en años completos.
     */
    public int calcularEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }



    /**
     * Retorna el rol de esta clase en el sistema.
     */
    @Override
    public String obtenerRol() {
        return "Paciente";
    }


    // toString
    @Override
    public String toString() {
        return super.toString()                                          + "\n" +
               "  Código paciente : " + codigoPaciente                 + "\n" +
               "  Nacimiento      : " + fechaNacimiento                + "\n" +
               "  Edad            : " + calcularEdad() + " años"       + "\n" +
               "  Tipo de sangre  : " + tipoSangre;
    }
}
