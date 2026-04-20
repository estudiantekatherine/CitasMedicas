package cr.ac.ucenfotec.bl.entities.clinica;

import cr.ac.ucenfotec.bl.entities.cita.Cita;
import cr.ac.ucenfotec.bl.entities.persona.Medico;
import cr.ac.ucenfotec.bl.entities.persona.Paciente;

import java.util.ArrayList;

/**
 * Representa la clínica médica central del sistema.
 *
 * <p><b>Concepto POO — Agregación:</b> La clínica <em>agrega</em> médicos,
 * pacientes y citas que existen de forma independiente. Si la clínica se
 * elimina, los médicos y pacientes podrían existir en otro contexto.
 * La relación es todo–parte débil.</p>
 *
 * <p><b>Concepto POO — Modularidad:</b> La clínica actúa como contenedor
 * central de las listas de entidades, separando la responsabilidad de
 * almacenamiento de la lógica de negocio que vive en
 * {@link cr.ac.ucenfotec.bl.logic.Service}.</p>
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
     * Lista de médicos registrados en la clínica.
     *
     * <p><b>Agregación:</b> Los médicos pertenecen a la clínica pero
     * tienen existencia propia.</p>
     */
    private ArrayList<Medico> listaMedicos;

    /**
     * Lista de pacientes registrados en la clínica.
     *
     * <p><b>Agregación:</b> Los pacientes pertenecen a la clínica pero
     * tienen existencia propia.</p>
     */
    private ArrayList<Paciente> listaPacientes;

    /**
     * Lista de citas registradas en el sistema de la clínica.
     *
     * <p><b>Agregación:</b> Las citas pertenecen a la clínica y
     * referencian médicos y pacientes existentes.</p>
     */
    private ArrayList<Cita> listaCitas;


    // Constructores

    /**
     * Constructor por defecto.
     * Inicializa las tres listas vacías para evitar {@code NullPointerException}.
     */
    public Clinica() {
        this.listaMedicos   = new ArrayList<>();
        this.listaPacientes = new ArrayList<>();
        this.listaCitas     = new ArrayList<>();
    }

    /**
     * Constructor que inicializa los datos identificadores de la clínica.
     *
     * @param nombreClinica    Nombre oficial de la clínica.
     * @param direccionClinica Dirección física de la clínica.
     * @param telefonoClinica  Número de teléfono principal.
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
     * Retorna el nombre oficial de la clínica.
     * @return Nombre de la clínica.
     */
    public String getNombreClinica() {
        return nombreClinica;
    }

    /**
     * Establece el nombre oficial de la clínica.
     * @param nombreClinica Nuevo nombre de la clínica.
     */
    public void setNombreClinica(String nombreClinica) {
        this.nombreClinica = nombreClinica;
    }

    /**
     * Retorna la dirección física de la clínica.
     * @return Dirección de la clínica.
     */
    public String getDireccionClinica() {
        return direccionClinica;
    }

    /**
     * Establece la dirección física de la clínica.
     * @param direccionClinica Nueva dirección de la clínica.
     */
    public void setDireccionClinica(String direccionClinica) {
        this.direccionClinica = direccionClinica;
    }

    /**
     * Retorna el número de teléfono principal de la clínica.
     * @return Teléfono de la clínica.
     */
    public String getTelefonoClinica() {
        return telefonoClinica;
    }

    /**
     * Establece el número de teléfono principal de la clínica.
     * @param telefonoClinica Nuevo número de teléfono.
     */
    public void setTelefonoClinica(String telefonoClinica) {
        this.telefonoClinica = telefonoClinica;
    }

    /**
     * Retorna la lista de médicos registrados en la clínica.
     * @return {@link ArrayList} de objetos {@link Medico}.
     */
    public ArrayList<Medico> getListaMedicos() {
        return listaMedicos;
    }

    /**
     * Establece la lista completa de médicos de la clínica.
     * @param listaMedicos Nueva lista de médicos.
     */
    public void setListaMedicos(ArrayList<Medico> listaMedicos) {
        this.listaMedicos = listaMedicos;
    }

    /**
     * Retorna la lista de pacientes registrados en la clínica.
     * @return {@link ArrayList} de objetos {@link Paciente}.
     */
    public ArrayList<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    /**
     * Establece la lista completa de pacientes de la clínica.
     * @param listaPacientes Nueva lista de pacientes.
     */
    public void setListaPacientes(ArrayList<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    /**
     * Retorna la lista de citas registradas en la clínica.
     * @return {@link ArrayList} de objetos {@link Cita}.
     */
    public ArrayList<Cita> getListaCitas() {
        return listaCitas;
    }

    /**
     * Establece la lista completa de citas de la clínica.
     * @param listaCitas Nueva lista de citas.
     */
    public void setListaCitas(ArrayList<Cita> listaCitas) {
        this.listaCitas = listaCitas;
    }


    // toString

    /**
     * Retorna un resumen de los datos y conteos de la clínica.
     *
     * @return Cadena con nombre, dirección, teléfono y cantidad de registros.
     */
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