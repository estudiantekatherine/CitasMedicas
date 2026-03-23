package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.cita.Cita;
import cr.ac.ucenfotec.bl.entities.cita.Receta;
import cr.ac.ucenfotec.bl.entities.clinica.Clinica;
import cr.ac.ucenfotec.bl.entities.clinica.Especialidad;
import cr.ac.ucenfotec.bl.entities.persona.Medico;
import cr.ac.ucenfotec.bl.entities.persona.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Service {

    /**
     * Clínica activa del sistema
     */
    private Clinica clinicaActiva;

    // Constructores

    /**
     * Constructor por defecto.
     */
    public Service() {
    }


    public Service(Clinica clinicaActiva) {
        this.clinicaActiva = clinicaActiva;
    }


    // Getter y Setter
      /**
     * Retorna el nombre de la clínica activa
     * El {@code Main} lo usa para mostrarlo en el encabezado del menú
     *
     * @return Nombre de la clínica activa
     */
    public String getNombreClinica() {
        return clinicaActiva.getNombreClinica();
    }

    /**
     * Retorna la clínica activa.
     * @return Objeto {@link Clinica} activo.
     */
    public Clinica getClinicaActiva() {
        return clinicaActiva; }

    /**
     * Establece la clínica activa del servicio.
     * @param clinicaActiva Nueva clínica activa.
     */
    public void setClinicaActiva(Clinica clinicaActiva) {
        this.clinicaActiva = clinicaActiva; }


    // Registro de médicos
    /**
     * Registra un nuevo médico en la clínica a partir de datos primitivos.
     *
     * @param nombre                  Nombre de pila del médico.
     * @param apellido                Apellido del médico.
     * @param numeroCedula            Número de cédula.
     * @param numeroCelular           Número de celular.
     * @param correoElectronico       Correo electrónico.
     * @param codigoMedico            Código único del médico.
     * @param salarioMensual          Salario mensual bruto.
     * @param codigoEspecialidad      Código de la especialidad.
     * @param nombreEspecialidad      Nombre de la especialidad.
     * @param descripcionEspecialidad Descripción de la especialidad.
     * @return Mensaje de éxito o descripción del error encontrado.
     */
    public String registrarMedico(String nombre, String apellido, String numeroCedula,
                                  String numeroCelular, String correoElectronico,
                                  String codigoMedico, double salarioMensual,
                                  String codigoEspecialidad, String nombreEspecialidad,
                                  String descripcionEspecialidad) {
        if (buscarMedicoPorCodigo(codigoMedico) != null) {
            return "Error: ya existe un médico con el código " + codigoMedico + ".";
        }
        // Especialidad se crea aquí y se relaciona con el médico
        Especialidad especialidad = new Especialidad(
                codigoEspecialidad, nombreEspecialidad, descripcionEspecialidad);

        Medico medico = new Medico(nombre, apellido, numeroCedula, numeroCelular,
                correoElectronico, codigoMedico, especialidad, salarioMensual);
        clinicaActiva.getListaMedicos().add(medico);
        return "Médico '" + nombre + " " + apellido + "' registrado correctamente.";
    }

    // Registro de pacientes
    /**
     * Registra un nuevo paciente en la clínica a partir de datos primitivos.
     *
     * @param nombre            Nombre de pila del paciente.
     * @param apellido          Apellido del paciente.
     * @param numeroCedula      Número de cédula.
     * @param numeroCelular     Número de celular.
     * @param correoElectronico Correo electrónico.
     * @param codigoPaciente    Código único del paciente.
     * @param fechaNacimiento   Fecha de nacimiento.
     * @param tipoSangre        Tipo de sangre del paciente.
     * @return Mensaje de éxito o descripción del error encontrado.
     */
    public String registrarPaciente(String nombre, String apellido, String numeroCedula,
                                    String numeroCelular, String correoElectronico,
                                    String codigoPaciente, LocalDate fechaNacimiento,
                                    String tipoSangre) {
        if (buscarPacientePorCodigo(codigoPaciente) != null) {
            return "Error: ya existe un paciente con el código " + codigoPaciente + ".";
        }
        Paciente paciente = new Paciente(nombre, apellido, numeroCedula, numeroCelular,
                correoElectronico, codigoPaciente,
                fechaNacimiento, tipoSangre);
        clinicaActiva.getListaPacientes().add(paciente);
        return "Paciente '" + nombre + " " + apellido + "' registrado correctamente.";
    }

    // Gestión de citas
    /**
     * Programa una nueva cita en la clínica a partir de datos primitivos.
     *
     * @param codigoCita     Código único de la cita.
     * @param motivoConsulta Motivo de la consulta.
     * @param fechaHoraCita  Fecha y hora de la consulta.
     * @param codigoMedico   Código del médico asignado.
     * @param codigoPaciente Código del paciente asignado.
     * @return Mensaje de éxito o descripción del error encontrado.
     */
    public String programarCita(String codigoCita, String motivoConsulta,
                                LocalDateTime fechaHoraCita, String codigoMedico,
                                String codigoPaciente) {
        if (buscarCitaPorCodigo(codigoCita) != null) {
            return "Error: ya existe una cita con el código " + codigoCita + ".";
        }
        Medico medico = buscarMedicoPorCodigo(codigoMedico);
        if (medico == null) {
            return "Error: no existe un médico con el código " + codigoMedico + ".";
        }
        Paciente paciente = buscarPacientePorCodigo(codigoPaciente);
        if (paciente == null) {
            return "Error: no existe un paciente con el código " + codigoPaciente + ".";
        }
        // Cita.ESTADO_PENDIENTE usa la constante String definida dentro de Cita
        Cita cita = new Cita(codigoCita, fechaHoraCita, motivoConsulta,
                Cita.ESTADO_PENDIENTE, medico, paciente);
        clinicaActiva.getListaCitas().add(cita);
        return "Cita '" + codigoCita + "' programada con estado PENDIENTE.";
    }

    /**
     Localizar la cita y cambiar su estado
     */
    public String confirmarCita(String codigoCita) {
        Cita cita = buscarCitaPorCodigo(codigoCita);
        if (cita == null) {
            return "Error: no se encontró la cita con código " + codigoCita + ".";
        }
        cita.setEstadoCita(Cita.ESTADO_CONFIRMADA);
        return "Cita '" + codigoCita + "' confirmada correctamente.";
    }

    /**
       Localizar la cita y cambiar su estado
     */
    public String cancelarCita(String codigoCita) {
        Cita cita = buscarCitaPorCodigo(codigoCita);
        if (cita == null) {
            return "Error: no se encontró la cita con código " + codigoCita + ".";
        }
        cita.setEstadoCita(Cita.ESTADO_CANCELADA);
        return "Cita '" + codigoCita + "' cancelada correctamente.";
    }

    /**
     * Emite una receta para una cita y la marca como atendida
     *
     * @param codigoCita        Código de la cita que se atiende.
     * @param codigoReceta      Código único de la receta.
     * @param listaMedicamentos Medicamentos prescritos.
     * @param indicacionesDosis Dosis de cada medicamento.
     * @param instruccionesUso  Instrucciones de uso para el paciente.
     * @return Mensaje de éxito o descripción del error encontrado.
     */
    public String emitirReceta(String codigoCita, String codigoReceta,
                               String listaMedicamentos, String indicacionesDosis,
                               String instruccionesUso) {
        Cita cita = buscarCitaPorCodigo(codigoCita);
        if (cita == null) {
            return "Error: no se encontró la cita con código " + codigoCita + ".";
        }
        // AGREGACIÓN: Receta se construye aquí y se asigna a la cita
        Receta receta = new Receta(codigoReceta, listaMedicamentos,
                indicacionesDosis, instruccionesUso, LocalDate.now());
        cita.setRecetaEmitida(receta);
        cita.setEstadoCita(Cita.ESTADO_ATENDIDA);
        return "Receta emitida. Cita '" + codigoCita + "' marcada como ATENDIDA.";
    }


    /**
     * Retorna la lista de médicos
     *
     * @return Lista de cadenas con la información de cada médico registrado.
     */
    public ArrayList<String> getListaMedicosFormateada() {
        ArrayList<String> resultado = new ArrayList<>();
        for (int i = 0; i < clinicaActiva.getListaMedicos().size(); i++) {
            resultado.add((i + 1) + ".\n" + clinicaActiva.getListaMedicos().get(i).toString());
        }
        return resultado;
    }

    /**
     * Retorna la lista de pacientes
     *
     * @return Lista de cadenas con la información de cada paciente registrado
     */
    public ArrayList<String> getListaPacientesFormateada() {
        ArrayList<String> resultado = new ArrayList<>();
        for (int i = 0; i < clinicaActiva.getListaPacientes().size(); i++) {
            resultado.add((i + 1) + ".\n" + clinicaActiva.getListaPacientes().get(i).toString());
        }
        return resultado;
    }

    /**
     * Retorna la lista de citas
     *
     * @return Lista de cadenas con la información de cada cita registrada
     */
    public ArrayList<String> getListaCitasFormateada() {
        ArrayList<String> resultado = new ArrayList<>();
        for (int i = 0; i < clinicaActiva.getListaCitas().size(); i++) {
            resultado.add((i + 1) + ".\n" + clinicaActiva.getListaCitas().get(i).toString());
        }
        return resultado;
    }

    /**
     * Retorna la cantidad total de médicos registrados
     * @return Número de médicos.
     */
    public int getCantidadMedicos() {
        return clinicaActiva.getListaMedicos().size();
    }

    /**
     * Retorna la cantidad total de pacientes registrados
     * @return Número de pacientes
     */
    public int getCantidadPacientes() {
        return clinicaActiva.getListaPacientes().size();
    }

    /**
     * Retorna la cantidad total de citas registradas.
     * @return Número de citas.
     */
    public int getCantidadCitas() {
        return clinicaActiva.getListaCitas().size();
    }

    // Métodos privados de búsqueda
    /**
     * Busca un médico en la lista de la clínica por su código único
     *
     * @param codigoMedico Código del médico a buscar
     */
    private Medico buscarMedicoPorCodigo(String codigoMedico) {
        for (Medico m : clinicaActiva.getListaMedicos()) {
            if (m.getCodigoMedico().equalsIgnoreCase(codigoMedico)) return m;
        }
        return null;
    }

    /**
     * Busca un paciente en la lista de la clínica por su código único
     *
     * @param codigoPaciente Código del paciente a buscar
     */
    private Paciente buscarPacientePorCodigo(String codigoPaciente) {
        for (Paciente p : clinicaActiva.getListaPacientes()) {
            if (p.getCodigoPaciente().equalsIgnoreCase(codigoPaciente)) return p;
        }
        return null;
    }

    /**
     * Busca una cita en la lista de la clínica por su código único
     *
     * @param codigoCita Código de la cita a buscar.
     */
    private Cita buscarCitaPorCodigo(String codigoCita) {
        for (Cita c : clinicaActiva.getListaCitas()) {
            if (c.getCodigoCita().equalsIgnoreCase(codigoCita)) return c;
        }
        return null;
    }
}