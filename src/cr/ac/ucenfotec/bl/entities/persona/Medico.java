package cr.ac.ucenfotec.bl.entities.persona;

import cr.ac.ucenfotec.bl.entities.clinica.Especialidad;

/**
 * Representa a un médico dentro del sistema de citas
 */
public class Medico extends Persona {

    /** Código único que identifica al médico en el sistema */
    private String codigoMedico;

    /**
     * Especialidad médica del doctor
     */
    private Especialidad especialidad;

    /** Salario mensual bruto del médico en colones costarricenses */
    private double salarioMensual;

    // Constructores
    /**
     * Constructor por defecto.
     */
    public Medico() {
        super();
    }

    /**
     * Constructor que inicializa todos los atributos del médico
     *
     * @param nombre          Nombre de pila.
     * @param apellido        Apellido del médico.
     * @param numeroCedula    Número de cédula.
     * @param numeroCelular   Número de celular.
     * @param correoElectronico Correo electrónico.
     * @param codigoMedico    Código único del médico.
     * @param especialidad    Especialidad médica (composición).
     * @param salarioMensual  Salario mensual bruto.
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
     * Retorna el código único del médico
     * @return Código del médico
     */
    public String getCodigoMedico() {
        return codigoMedico; }

    /**
     * Establece el código del médico
     * @param codigoMedico Nuevo código del médico
     */
    public void setCodigoMedico(String codigoMedico) {
        this.codigoMedico = codigoMedico; }

    /**
     * Retorna la especialidad médica del doctor
     * @return Objeto {@link Especialidad} del médico
     */
    public Especialidad getEspecialidad() {
        return especialidad; }

    /**
     * Establece la especialidad médica del doctor
     */
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad; }

    /**
     * Retorna el salario mensual del médico
     * @return Salario mensual bruto en colones
     */
    public double getSalarioMensual() {
        return salarioMensual; }

    /**
     * Establece el salario mensual del médico
     * @param salarioMensual Nuevo salario mensual bruto
     */
    public void setSalarioMensual(double salarioMensual) {
        this.salarioMensual = salarioMensual; }


    /**
     * Retorna el rol de esta clase en el sistema
     *
     * @return La cadena {@code "Médico"}
     */
    @Override
    public String obtenerRol() {
        return "Médico";
    }

    // toString
    @Override
    public String toString() {
        return super.toString()                                          + "\n" +
               "  Código médico   : " + codigoMedico                   + "\n" +
               "  Salario mensual : ₡" + String.format("%,.2f", salarioMensual) + "\n" +
               "  --- Especialidad ---"                                 + "\n" +
               especialidad.toString();
    }
}
