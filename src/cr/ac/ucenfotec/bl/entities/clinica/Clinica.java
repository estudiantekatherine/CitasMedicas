package cr.ac.ucenfotec.bl.entities.clinica;

import cr.ac.ucenfotec.bl.entities.cita.Cita;
import cr.ac.ucenfotec.bl.entities.persona.Medico;
import cr.ac.ucenfotec.bl.entities.persona.Paciente;

import java.util.ArrayList;

/**
 * Representa la clínica médica
 *
 */
public class Clinica {

    /** Nombre oficial de la clínica */
    private String nombreClinica;

    /** Dirección física de la clínica */
    private String direccionClinica;

    /** Número de teléfono principal de la clínica */
    private String telefonoClinica;

    /**
     * Lista de médicos registrados en la clínica
     */
    private ArrayList<Medico> listaMedicos;

    /**
     * Lista de pacientes registrados en la clínica
     */
    private ArrayList<Paciente> listaPacientes;

    /**
     * Lista de citas registradas en el sistema de la clínica
     */
    private ArrayList<Cita> listaCitas;

    // Constructores
    /**
     * Constructor por defecto
     */
    public Clinica() {
        this.listaMedicos     = new ArrayList<>();
        this.listaPacientes   = new ArrayList<>();
        this.listaCitas       = new ArrayList<>();
    }

    /**
     * Constructor que inicializa los datos de la clínica
     *
     * @param nombreClinica    Nombre oficial de la clínica
     * @param direccionClinica Dirección física de la clínica
     * @param telefonoClinica  Teléfono principal de la clínica
     */
    public Clinica(String nombreClinica, String direccionClinica, String telefonoClinica) {
        this.nombreClinica    = nombreClinica;
        this.direccionClinica = direccionClinica;
        this.telefonoClinica  = telefonoClinica;
        this.listaMedicos     = new ArrayList<>();
        this.listaPacientes   = new ArrayList<>();
        this.listaCitas       = new ArrayList<>();
    }

    // Getters y Setters
    /**
     * Retorna el nombre de la clínica
     * @return Nombre oficial de la clínica
     */
    public String getNombreClinica() {
        return nombreClinica; }

    /**
     * Establece el nombre de la clínica
     * @param nombreClinica Nuevo nombre oficial
     */
    public void setNombreClinica(String nombreClinica) {
        this.nombreClinica = nombreClinica; }

    /**
     * Retorna la dirección de la clínica
     * @return Dirección física de la clínica
     */
    public String getDireccionClinica() {
        return direccionClinica; }

    /**
     * Establece la dirección de la clínica
     * @param direccionClinica Nueva dirección física
     */
    public void setDireccionClinica(String direccionClinica) {
        this.direccionClinica = direccionClinica;
    }

    /**
     * Retorna el teléfono de la clínica
     * @return Número de teléfono principal
     */
    public String getTelefonoClinica() {
        return telefonoClinica; }

    /**
     * Establece el teléfono de la clínica
     * @param telefonoClinica Nuevo número de teléfono
     */
    public void setTelefonoClinica(String telefonoClinica) {
        this.telefonoClinica = telefonoClinica;
    }

    /**
     * @return {@link ArrayList} de objetos {@link Medico}
     */
    public ArrayList<Medico> getListaMedicos() {
        return listaMedicos; }

    /**
     * Establece la lista de médicos.
     * @param listaMedicos Nueva lista de médicos.
     */
    public void setListaMedicos(ArrayList<Medico> listaMedicos) {
        this.listaMedicos = listaMedicos;
    }

    /**
     * Retorna la lista de pacientes registrados (AGREGACIÓN).
     * @return {@link ArrayList} de objetos {@link Paciente}.
     */
    public ArrayList<Paciente> getListaPacientes() {
        return listaPacientes; }

    /**
     * Establece la lista de pacientes.
     * @param listaPacientes Nueva lista de pacientes.
     */
    public void setListaPacientes(ArrayList<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    /**
     * Retorna la lista de citas registradas (AGREGACIÓN).
     * @return {@link ArrayList} de objetos {@link Cita}.
     */
    public ArrayList<Cita> getListaCitas() {
        return listaCitas; }

    /**
     * Establece la lista de citas.
     * @param listaCitas Nueva lista de citas.
     */
    public void setListaCitas(ArrayList<Cita> listaCitas) {
        this.listaCitas = listaCitas; }

    // toString
    @Override
    public String toString() {
        return  "  Nombre clínica  : " + nombreClinica              + "\n" +
                "  Dirección       : " + direccionClinica            + "\n" +
                "  Teléfono        : " + telefonoClinica             + "\n" +
                "  Médicos regist. : " + listaMedicos.size()         + "\n" +
                "  Pacientes reg.  : " + listaPacientes.size()       + "\n" +
                "  Citas regist.   : " + listaCitas.size();
    }
}
