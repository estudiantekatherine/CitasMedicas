package cr.ac.ucenfotec.bl.entities.persona;

import cr.ac.ucenfotec.bl.entities.IValidable;
import cr.ac.ucenfotec.bl.entities.clinica.Especialidad;

import java.util.Objects;

/**
 * Representa a un médico dentro del sistema de citas médicas.
 *
 * <p><b>Concepto POO — Herencia:</b> Extiende {@link Persona} para reutilizar
 * los atributos y comportamientos comunes (nombre, cédula, contacto), y agrega
 * los atributos propios del rol médico: código, especialidad y salario.</p>
 *
 * <p><b>Concepto POO — Composición:</b> El médico <em>está compuesto</em> por
 * una {@link Especialidad}. Si el médico deja de existir, su especialidad
 * también pierde sentido en este contexto (relación todo–parte fuerte).</p>
 *
 * <p><b>Concepto POO — Interfaz {@link IValidable}:</b> Al implementar esta
 * interfaz, el médico se compromete a exponer su código único, a describirse
 * en una línea y a auto-validar sus datos obligatorios.</p>
 *
 */
public class Medico extends Persona implements IValidable {

    /** Código único que identifica al médico en el sistema (p.ej. "MED-001") */
    private String codigoMedico;

    /**
     * Especialidad médica del doctor.
     *
     * <p><b>Composición:</b> La especialidad se crea junto con el médico y
     * no tiene existencia independiente en este modelo.</p>
     */
    private Especialidad especialidad;

    /** Salario mensual bruto del médico en colones costarricenses */
    private double salarioMensual;


    // Constructores
     /**
     * Constructor por defecto.
     * Invoca el constructor por defecto de {@link Persona}.
     */
    public Medico() {
        super();
    }

    /**
     * Constructor que inicializa todos los atributos del médico.
     *
     * @param nombre            Nombre de pila.
     * @param apellido          Apellido del médico.
     * @param numeroCedula      Número de cédula.
     * @param numeroCelular     Número de celular.
     * @param correoElectronico Correo electrónico.
     * @param codigoMedico      Código único del médico.
     * @param especialidad      Especialidad médica (composición).
     * @param salarioMensual    Salario mensual bruto en colones.
     */
    public Medico(String nombre, String apellido, String numeroCedula,
                  String numeroCelular, String correoElectronico,
                  String codigoMedico, Especialidad especialidad,
                  double salarioMensual) {
        super(nombre, apellido, numeroCedula, numeroCelular, correoElectronico);
        this.codigoMedico   = codigoMedico;
        this.especialidad   = especialidad; // COMPOSICIÓN: se asigna en construcción
        this.salarioMensual = salarioMensual;
    }


    // Getters y Setters
    /**
     * Retorna el código único del médico.
     * @return Código del médico (p.ej. "MED-001").
     */
    public String getCodigoMedico() {
        return codigoMedico;
    }

    /**
     * Establece el código único del médico.
     * @param codigoMedico Nuevo código del médico.
     */
    public void setCodigoMedico(String codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    /**
     * Retorna la especialidad médica del doctor.
     * @return Objeto {@link Especialidad} del médico.
     */
    public Especialidad getEspecialidad() {
        return especialidad;
    }

    /**
     * Establece la especialidad médica del doctor.
     * @param especialidad Nueva especialidad del médico.
     */
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Retorna el salario mensual bruto del médico.
     * @return Salario mensual en colones costarricenses.
     */
    public double getSalarioMensual() {
        return salarioMensual;
    }

    /**
     * Establece el salario mensual del médico.
     * @param salarioMensual Nuevo salario mensual bruto en colones.
     */
    public void setSalarioMensual(double salarioMensual) {
        this.salarioMensual = salarioMensual;
    }


    // Sobre-escritura de métodos heredados
     /**
     * Retorna el rol que cumple este objeto en el sistema.
     *
     * <p><b>Sobre-escritura (Override):</b> Implementa el método abstracto
     * definido en {@link Persona}, devolviendo el rol específico "Médico".</p>
     *
     * @return La cadena {@code "Médico"}.
     */
    @Override
    public String obtenerRol() {
        return "Médico";
    }


    // Implementación de IValidable

    /**
     * Retorna el código único del médico como identificador del sistema.
     *
     * <p><b>Interfaz IValidable:</b> Implementación del contrato {@code getCodigo()}.
     * Permite que la capa de lógica trate a médicos y pacientes de forma
     * polimórfica al verificar duplicados.</p>
     *
     * @return Código único del médico.
     */
    @Override
    public String getCodigo() {
        return codigoMedico;
    }

    /**
     * Valida que los campos obligatorios del médico no estén vacíos ni nulos.
     *
     * <p>Campos validados: código, cédula, nombre, apellido, correo y especialidad.</p>
     *
     * @return {@code true} si todos los campos requeridos son válidos.
     */
    @Override
    public boolean validarDatos() {
        return codigoMedico      != null && !codigoMedico.isBlank()      &&
                getNumeroCedula() != null && !getNumeroCedula().isBlank() &&
                getNombre()       != null && !getNombre().isBlank()       &&
                getApellido()     != null && !getApellido().isBlank()     &&
                getCorreoElectronico() != null && !getCorreoElectronico().isBlank() &&
                especialidad      != null;
    }

    /**
     * Retorna una descripción resumida del médico en una sola línea,
     * útil para listas desplegables o resúmenes de pantalla.
     *
     * @return Código y nombre completo del médico.
     */
    @Override
    public String obtenerDescripcion() {
        return "[" + codigoMedico + "] Dr. " + getNombre() + " " + getApellido()
                + " — " + (especialidad != null ? especialidad.getNombreEspecialidad() : "Sin especialidad");
    }


    // Identidad de objetos
      /**
     * Dos médicos son iguales si comparten el mismo código de médico.
     *
     * <p>Complementa el {@code equals} de {@link Persona} (por cédula):
     * aquí la igualdad se determina por el código interno del sistema,
     * que es el identificador de negocio del médico.</p>
     *
     * @param objeto Objeto a comparar.
     * @return {@code true} si ambos tienen el mismo código de médico.
     */
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null || getClass() != objeto.getClass()) return false;
        Medico otroMedico = (Medico) objeto;
        return Objects.equals(codigoMedico, otroMedico.codigoMedico);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(codigoMedico);
    }


    // toString
    /**
     * Retorna la información completa del médico incluyendo su especialidad.
     *
     * <p><b>Sobre-escritura de toString:</b> Extiende el {@code toString} de
     * {@link Persona} con los atributos específicos del médico.</p>
     *
     * @return Cadena con todos los datos del médico.
     */
    @Override
    public String toString() {
        return super.toString()                                              + "\n" +
                "  Código médico   : " + codigoMedico                        + "\n" +
                "  Salario mensual : ₡" + String.format("%,.2f", salarioMensual) + "\n" +
                "  --- Especialidad ---"                                      + "\n" +
                especialidad.toString();
    }
}