package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.IValidable;
import cr.ac.ucenfotec.bl.entities.cita.Cita;
import cr.ac.ucenfotec.bl.entities.cita.Receta;
import cr.ac.ucenfotec.bl.entities.clinica.Clinica;
import cr.ac.ucenfotec.bl.entities.clinica.Especialidad;
import cr.ac.ucenfotec.bl.entities.persona.Medico;
import cr.ac.ucenfotec.bl.entities.persona.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Capa de lógica de negocio del sistema de citas médicas.
 *
 * <p><b>Concepto POO — Modularidad / Arquitectura:</b> Esta clase implementa
 * la capa <em>Business Logic (BL)</em> de la arquitectura en capas del sistema.
 * Recibe solicitudes de la capa de UI
 * aplica las reglas de negocio y opera sobre las entidades del dominio.
 * Ninguna lógica de negocio debe residir en la UI ni en las entidades.</p>
 *
 * <p><b>Concepto POO — Sobrecarga de métodos:</b> Varios métodos de registro
 * están sobrecargados: una versión acepta datos primitivos (String, double…)
 * y otra acepta directamente el objeto entidad ya construido. Esto permite
 * que el sistema sea extensible sin modificar la firma original.</p>
 *
 * <p><b>Concepto POO — Polimorfismo con IValidable:</b> Los métodos privados
 * de validación reciben {@link IValidable}, lo que permite tratar médicos,
 * pacientes y citas de forma genérica al verificar duplicados.</p>
 *
 */
public class Service {

    /**
     * Clínica activa del sistema.
     *
     * <p><b>Dependencia:</b> El servicio depende de la clínica para operar;
     * sin ella no puede registrar ni consultar entidades.</p>
     */
    private Clinica clinicaActiva;


    // Constructores


    /**
     * Constructor por defecto.
     */
    public Service() {
    }

    /**
     * Constructor que inicializa el servicio con una clínica activa.
     *
     * @param clinicaActiva Clínica sobre la que operará este servicio.
     */
    public Service(Clinica clinicaActiva) {
        this.clinicaActiva = clinicaActiva;
    }



    // Getter y Setter


    /**
     * Retorna el nombre de la clínica activa.
     * Utilizado por {@code Main} para mostrar el encabezado del menú.
     *
     * @return Nombre de la clínica activa.
     */
    public String getNombreClinica() {
        return clinicaActiva.getNombreClinica();
    }

    /**
     * Retorna la clínica activa del servicio.
     * @return Objeto {@link Clinica} activo.
     */
    public Clinica getClinicaActiva() {
        return clinicaActiva;
    }

    /**
     * Establece la clínica activa del servicio.
     * @param clinicaActiva Nueva clínica activa.
     */
    public void setClinicaActiva(Clinica clinicaActiva) {
        this.clinicaActiva = clinicaActiva;
    }


    // Registro de médicos — SOBRECARGA DE MÉTODOS


    /**
     * Registra un nuevo médico a partir de <strong>datos primitivos</strong>.
     *
     * <p><b>Sobrecarga (versión 1/2):</b> Este método construye internamente
     * la {@link Especialidad} y el {@link Medico}, delegando la creación de
     * objetos a la capa de lógica y simplificando la interfaz de usuario.</p>
     *
     * @param nombre                  Nombre de pila del médico.
     * @param apellido                Apellido del médico.
     * @param numeroCedula            Número de cédula.
     * @param numeroCelular           Número de celular.
     * @param correoElectronico       Correo electrónico.
     * @param codigoMedico            Código único del médico.
     * @param salarioMensual          Salario mensual bruto en colones.
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
        // La especialidad se crea junto con el médico (COMPOSICIÓN)
        Especialidad especialidadMedico = new Especialidad(
                codigoEspecialidad, nombreEspecialidad, descripcionEspecialidad);

        Medico nuevoMedico = new Medico(nombre, apellido, numeroCedula, numeroCelular,
                correoElectronico, codigoMedico, especialidadMedico, salarioMensual);

        return registrarMedico(nuevoMedico); // delega a la sobrecarga por objeto
    }

    /**
     * Registra un nuevo médico a partir de un <strong>objeto {@link Medico}</strong>
     * ya construido.
     *
     * <p><b>Sobrecarga (versión 2/2):</b> Útil cuando el objeto Medico fue creado
     * en otra parte (pruebas unitarias, importación de datos, etc.) y solo
     * necesita ser validado y almacenado en la clínica.</p>
     *
     * @param medicoNuevo Objeto {@link Medico} ya construido y listo para registrar.
     * @return Mensaje de éxito o descripción del error encontrado.
     */
    public String registrarMedico(Medico medicoNuevo) {
        if (!medicoNuevo.validarDatos()) {
            return "Error: los datos del médico son inválidos o incompletos.";
        }
        if (buscarMedicoPorCodigo(medicoNuevo.getCodigoMedico()) != null) {
            return "Error: ya existe un médico con el código " + medicoNuevo.getCodigoMedico() + ".";
        }
        clinicaActiva.getListaMedicos().add(medicoNuevo);
        return "Médico '" + medicoNuevo.getNombre() + " " + medicoNuevo.getApellido()
                + "' registrado correctamente.";
    }


    // Registro de pacientes — SOBRECARGA DE MÉTODOS

    /**
     * Registra un nuevo paciente a partir de <strong>datos primitivos</strong>.
     *
     * <p><b>Sobrecarga (versión 1/2):</b> Construye el {@link Paciente}
     * internamente y delega en la sobrecarga por objeto.</p>
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

        Paciente nuevoPaciente = new Paciente(nombre, apellido, numeroCedula, numeroCelular,
                correoElectronico, codigoPaciente, fechaNacimiento, tipoSangre);

        return registrarPaciente(nuevoPaciente); // delega a la sobrecarga por objeto
    }

    /**
     * Registra un nuevo paciente a partir de un <strong>objeto {@link Paciente}</strong>
     * ya construido.
     *
     * <p><b>Sobrecarga (versión 2/2):</b> Valida el objeto y lo agrega a la
     * lista de pacientes de la clínica.</p>
     *
     * @param pacienteNuevo Objeto {@link Paciente} ya construido.
     * @return Mensaje de éxito o descripción del error encontrado.
     */
    public String registrarPaciente(Paciente pacienteNuevo) {
        if (!pacienteNuevo.validarDatos()) {
            return "Error: los datos del paciente son inválidos o incompletos.";
        }
        if (buscarPacientePorCodigo(pacienteNuevo.getCodigoPaciente()) != null) {
            return "Error: ya existe un paciente con el código " + pacienteNuevo.getCodigoPaciente() + ".";
        }
        clinicaActiva.getListaPacientes().add(pacienteNuevo);
        return "Paciente '" + pacienteNuevo.getNombre() + " " + pacienteNuevo.getApellido()
                + "' registrado correctamente.";
    }


    // Gestión de citas — SOBRECARGA DE MÉTODOS


    /**
     * Programa una nueva cita a partir de <strong>datos primitivos</strong>.
     *
     * <p><b>Sobrecarga (versión 1/2):</b> Busca las entidades médico y paciente
     * por código, construye la cita y delega en la sobrecarga por objeto.</p>
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

        Medico medicoEncontrado = buscarMedicoPorCodigo(codigoMedico);
        if (medicoEncontrado == null) {
            return "Error: no existe un médico con el código " + codigoMedico + ".";
        }
        Paciente pacienteEncontrado = buscarPacientePorCodigo(codigoPaciente);
        if (pacienteEncontrado == null) {
            return "Error: no existe un paciente con el código " + codigoPaciente + ".";
        }
        // Usa la constante String definida dentro de Cita
        Cita nuevaCita = new Cita(codigoCita, fechaHoraCita, motivoConsulta,
                Cita.ESTADO_PENDIENTE, medicoEncontrado, pacienteEncontrado);

        return programarCita(nuevaCita); // delega a la sobrecarga por objeto
    }

    /**
     * Programa una nueva cita a partir de un <strong>objeto {@link Cita}</strong>
     * ya construido.
     *
     * <p><b>Sobrecarga (versión 2/2):</b> Valida el objeto y lo agrega a la
     * lista de citas de la clínica.</p>
     *
     * @param citaNueva Objeto {@link Cita} ya construido.
     * @return Mensaje de éxito o descripción del error encontrado.
     */
    public String programarCita(Cita citaNueva) {
        if (!citaNueva.validarDatos()) {
            return "Error: los datos de la cita son inválidos o incompletos.";
        }
        if (buscarCitaPorCodigo(citaNueva.getCodigoCita()) != null) {
            return "Error: ya existe una cita con el código " + citaNueva.getCodigoCita() + ".";
        }
        clinicaActiva.getListaCitas().add(citaNueva);
        return "Cita '" + citaNueva.getCodigoCita() + "' programada con estado PENDIENTE.";
    }



    // Cambios de estado de citas


    /**
     * Localiza la cita por código y cambia su estado a CONFIRMADA.
     *
     * @param codigoCita Código de la cita a confirmar.
     * @return Mensaje de éxito o error si no se encontró la cita.
     */
    public String confirmarCita(String codigoCita) {
        Cita citaEncontrada = buscarCitaPorCodigo(codigoCita);
        if (citaEncontrada == null) {
            return "Error: no se encontró la cita con código " + codigoCita + ".";
        }
        citaEncontrada.setEstadoCita(Cita.ESTADO_CONFIRMADA);
        return "Cita '" + codigoCita + "' confirmada correctamente.";
    }

    /**
     * Localiza la cita por código y cambia su estado a CANCELADA.
     *
     * @param codigoCita Código de la cita a cancelar.
     * @return Mensaje de éxito o error si no se encontró la cita.
     */
    public String cancelarCita(String codigoCita) {
        Cita citaEncontrada = buscarCitaPorCodigo(codigoCita);
        if (citaEncontrada == null) {
            return "Error: no se encontró la cita con código " + codigoCita + ".";
        }
        citaEncontrada.setEstadoCita(Cita.ESTADO_CANCELADA);
        return "Cita '" + codigoCita + "' cancelada correctamente.";
    }

    /**
     * Emite una receta para una cita y la marca como ATENDIDA.
     *
     * <p><b>Agregación:</b> La receta se construye aquí en la capa de lógica
     * y se asigna a la cita. Ambas son entidades independientes unidas en
     * tiempo de ejecución.</p>
     *
     * @param codigoCita        Código de la cita que se atiende.
     * @param codigoReceta      Código único de la receta.
     * @param listaMedicamentos Medicamentos prescritos.
     * @param indicacionesDosis Dosis de cada medicamento.
     * @param instruccionesUso  Instrucciones de uso para el paciente.
     * @return Mensaje de éxito o error si no se encontró la cita.
     */
    public String emitirReceta(String codigoCita, String codigoReceta,
                               String listaMedicamentos, String indicacionesDosis,
                               String instruccionesUso) {
        Cita citaEncontrada = buscarCitaPorCodigo(codigoCita);
        if (citaEncontrada == null) {
            return "Error: no se encontró la cita con código " + codigoCita + ".";
        }
        Receta recetaEmitida = new Receta(codigoReceta, listaMedicamentos,
                indicacionesDosis, instruccionesUso, LocalDate.now());
        citaEncontrada.setRecetaEmitida(recetaEmitida);
        citaEncontrada.setEstadoCita(Cita.ESTADO_ATENDIDA);
        return "Receta emitida. Cita '" + codigoCita + "' marcada como ATENDIDA.";
    }



    // Consultas y listados formateados


    /**
     * Retorna la lista de médicos formateada para mostrar en pantalla.
     *
     * @return Lista de cadenas con la información de cada médico registrado.
     */
    public ArrayList<String> getListaMedicosFormateada() {
        ArrayList<String> resultado = new ArrayList<>();
        for (int indice = 0; indice < clinicaActiva.getListaMedicos().size(); indice++) {
            resultado.add((indice + 1) + ".\n"
                    + clinicaActiva.getListaMedicos().get(indice).toString());
        }
        return resultado;
    }

    /**
     * Retorna la lista de pacientes formateada para mostrar en pantalla.
     *
     * @return Lista de cadenas con la información de cada paciente registrado.
     */
    public ArrayList<String> getListaPacientesFormateada() {
        ArrayList<String> resultado = new ArrayList<>();
        for (int indice = 0; indice < clinicaActiva.getListaPacientes().size(); indice++) {
            resultado.add((indice + 1) + ".\n"
                    + clinicaActiva.getListaPacientes().get(indice).toString());
        }
        return resultado;
    }

    /**
     * Retorna la lista de citas formateada para mostrar en pantalla.
     *
     * @return Lista de cadenas con la información de cada cita registrada.
     */
    public ArrayList<String> getListaCitasFormateada() {
        ArrayList<String> resultado = new ArrayList<>();
        for (int indice = 0; indice < clinicaActiva.getListaCitas().size(); indice++) {
            resultado.add((indice + 1) + ".\n"
                    + clinicaActiva.getListaCitas().get(indice).toString());
        }
        return resultado;
    }

    /**
     * Retorna la lista resumida de médicos (una línea por médico) usando
     * el método {@link IValidable#obtenerDescripcion()}.
     *
     * <p><b>Polimorfismo con interfaz:</b> Al iterar sobre objetos que
     * implementan {@link IValidable}, se invoca la descripción correcta
     * sin conocer el tipo concreto de la entidad.</p>
     *
     * @return Lista de descripciones cortas de todos los médicos.
     */
    public ArrayList<String> getListaMedicosResumida() {
        ArrayList<String> resultado = new ArrayList<>();
        for (Medico medicoActual : clinicaActiva.getListaMedicos()) {
            resultado.add(medicoActual.obtenerDescripcion());
        }
        return resultado;
    }

    /**
     * Retorna la lista resumida de pacientes (una línea por paciente).
     *
     * @return Lista de descripciones cortas de todos los pacientes.
     */
    public ArrayList<String> getListaPacientesResumida() {
        ArrayList<String> resultado = new ArrayList<>();
        for (Paciente pacienteActual : clinicaActiva.getListaPacientes()) {
            resultado.add(pacienteActual.obtenerDescripcion());
        }
        return resultado;
    }

    /**
     * Retorna la lista resumida de citas (una línea por cita).
     *
     * @return Lista de descripciones cortas de todas las citas.
     */
    public ArrayList<String> getListaCitasResumida() {
        ArrayList<String> resultado = new ArrayList<>();
        for (Cita citaActual : clinicaActiva.getListaCitas()) {
            resultado.add(citaActual.obtenerDescripcion());
        }
        return resultado;
    }

    /**
     * Retorna la cantidad total de médicos registrados en la clínica.
     * @return Número de médicos.
     */
    public int getCantidadMedicos() {
        return clinicaActiva.getListaMedicos().size();
    }

    /**
     * Retorna la cantidad total de pacientes registrados en la clínica.
     * @return Número de pacientes.
     */
    public int getCantidadPacientes() {
        return clinicaActiva.getListaPacientes().size();
    }

    /**
     * Retorna la cantidad total de citas registradas en la clínica.
     * @return Número de citas.
     */
    public int getCantidadCitas() {
        return clinicaActiva.getListaCitas().size();
    }



    // Métodos privados de búsqueda

    /**
     * Busca un médico en la lista de la clínica por su código único.
     *
     * @param codigoMedico Código del médico a buscar.
     * @return El objeto {@link Medico} encontrado, o {@code null} si no existe.
     */
    private Medico buscarMedicoPorCodigo(String codigoMedico) {
        for (Medico medicoActual : clinicaActiva.getListaMedicos()) {
            if (medicoActual.getCodigoMedico().equalsIgnoreCase(codigoMedico)) {
                return medicoActual;
            }
        }
        return null;
    }

    /**
     * Busca un paciente en la lista de la clínica por su código único.
     *
     * @param codigoPaciente Código del paciente a buscar.
     * @return El objeto {@link Paciente} encontrado, o {@code null} si no existe.
     */
    private Paciente buscarPacientePorCodigo(String codigoPaciente) {
        for (Paciente pacienteActual : clinicaActiva.getListaPacientes()) {
            if (pacienteActual.getCodigoPaciente().equalsIgnoreCase(codigoPaciente)) {
                return pacienteActual;
            }
        }
        return null;
    }

    /**
     * Busca una cita en la lista de la clínica por su código único.
     *
     * @param codigoCita Código de la cita a buscar.
     * @return El objeto {@link Cita} encontrado, o {@code null} si no existe.
     */
    private Cita buscarCitaPorCodigo(String codigoCita) {
        for (Cita citaActual : clinicaActiva.getListaCitas()) {
            if (citaActual.getCodigoCita().equalsIgnoreCase(codigoCita)) {
                return citaActual;
            }
        }
        return null;
    }
}