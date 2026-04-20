package cr.ac.ucenfotec.bl.entities.persona;

import cr.ac.ucenfotec.bl.entities.IValidable;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * Representa a un paciente dentro del sistema de citas médicas.
 *
 * <p><b>Concepto POO — Herencia:</b> Extiende {@link Persona} reutilizando
 * los atributos comunes y agregando los específicos del paciente:
 * código, fecha de nacimiento y tipo de sangre.</p>
 *
 * <p><b>Concepto POO — Interfaz {@link IValidable}:</b> Al implementar la
 * interfaz, el paciente garantiza exponer su código, describirse brevemente
 * y auto-validar sus datos antes de ser registrado en la clínica.</p>
 *
 */
public class Paciente extends Persona implements IValidable {

    /** Código único que identifica al paciente en el sistema (p.ej. "PAC-001") */
    private String codigoPaciente;

    /** Fecha de nacimiento del paciente, utilizada para calcular la edad */
    private LocalDate fechaNacimiento;

    /** Tipo de sangre del paciente (p.ej. O+, A-, B+) */
    private String tipoSangre;


    // Constructores
     /**
     * Constructor por defecto.
     * Invoca el constructor por defecto de {@link Persona}.
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
     * @param fechaNacimiento   Fecha de nacimiento ({@link LocalDate}).
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
     * @return Código del paciente (p.ej. "PAC-001").
     */
    public String getCodigoPaciente() {
        return codigoPaciente;
    }

    /**
     * Establece el código único del paciente.
     * @param codigoPaciente Nuevo código del paciente.
     */
    public void setCodigoPaciente(String codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    /**
     * Retorna la fecha de nacimiento del paciente.
     * @return Fecha de nacimiento como {@link LocalDate}.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del paciente.
     * @param fechaNacimiento Nueva fecha de nacimiento.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Retorna el tipo de sangre del paciente.
     * @return Tipo de sangre (p.ej. O+, A-, B+).
     */
    public String getTipoSangre() {
        return tipoSangre;
    }

    /**
     * Establece el tipo de sangre del paciente.
     * @param tipoSangre Nuevo tipo de sangre del paciente.
     */
    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }


    // Lógica de negocio
    /**
     * Calcula y retorna la edad actual del paciente en años completos.
     *
     * <p>Utiliza {@link Period#between} para calcular la diferencia exacta
     * entre la fecha de nacimiento y la fecha actual del sistema.</p>
     *
     * @return Edad del paciente en años completos.
     */
    public int calcularEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }


    // Sobre-escritura de métodos heredados
      /**
     * Retorna el rol que cumple este objeto en el sistema.
     *
     * <p><b>Sobre-escritura (Override):</b> Implementa el método abstracto
     * definido en {@link Persona}, devolviendo el rol específico "Paciente".</p>
     *
     * @return La cadena {@code "Paciente"}.
     */
    @Override
    public String obtenerRol() {
        return "Paciente";
    }


    // Implementación de IValidable

    /**
     * Retorna el código único del paciente como identificador del sistema.
     *
     * <p><b>Interfaz IValidable:</b> Implementación del contrato {@code getCodigo()},
     * que permite tratar médicos y pacientes de forma polimórfica desde el
     * {@link cr.ac.ucenfotec.bl.logic.Service}.</p>
     *
     * @return Código único del paciente.
     */
    @Override
    public String getCodigo() {
        return codigoPaciente;
    }

    /**
     * Valida que los campos obligatorios del paciente no estén vacíos ni nulos.
     *
     * <p>Campos validados: código, cédula, nombre, apellido, correo,
     * tipo de sangre y fecha de nacimiento.</p>
     *
     * @return {@code true} si todos los campos requeridos son válidos.
     */
    @Override
    public boolean validarDatos() {
        return codigoPaciente != null && !codigoPaciente.isBlank()       &&
                getNumeroCedula() != null && !getNumeroCedula().isBlank() &&
                getNombre()       != null && !getNombre().isBlank()       &&
                getApellido()     != null && !getApellido().isBlank()     &&
                getCorreoElectronico() != null && !getCorreoElectronico().isBlank() &&
                tipoSangre        != null && !tipoSangre.isBlank()        &&
                fechaNacimiento   != null;
    }

    /**
     * Retorna una descripción resumida del paciente en una sola línea,
     * útil para listas o resúmenes de pantalla.
     *
     * @return Código, nombre completo y edad del paciente.
     */
    @Override
    public String obtenerDescripcion() {
        return "[" + codigoPaciente + "] " + getNombre() + " " + getApellido()
                + " — " + calcularEdad() + " años | " + tipoSangre;
    }


    // Identidad de objetos
      /**
     * Dos pacientes son iguales si comparten el mismo código de paciente.
     *
     * @param objeto Objeto a comparar.
     * @return {@code true} si ambos tienen el mismo código de paciente.
     */
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null || getClass() != objeto.getClass()) return false;
        Paciente otroPaciente = (Paciente) objeto;
        return Objects.equals(codigoPaciente, otroPaciente.codigoPaciente);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(codigoPaciente);
    }


    // toString

    /**
     * Retorna la información completa del paciente.
     *
     * <p><b>Sobre-escritura de toString:</b> Extiende el {@code toString} de
     * {@link Persona} con los atributos específicos del paciente.</p>
     *
     * @return Cadena con todos los datos del paciente.
     */
    @Override
    public String toString() {
        return super.toString()                                          + "\n" +
                "  Código paciente : " + codigoPaciente                  + "\n" +
                "  Nacimiento      : " + fechaNacimiento                 + "\n" +
                "  Edad            : " + calcularEdad() + " años"        + "\n" +
                "  Tipo de sangre  : " + tipoSangre;
    }
}