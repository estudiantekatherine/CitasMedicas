package cr.ac.ucenfotec.bl.entities.cita;

import cr.ac.ucenfotec.bl.entities.persona.Medico;
import cr.ac.ucenfotec.bl.entities.persona.Paciente;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa una cita médica en el sistema
 *
 */
public class Cita {

    // Constantes que definen los estados válidos de una cita
    public static final String ESTADO_PENDIENTE  = "PENDIENTE";
    public static final String ESTADO_CONFIRMADA = "CONFIRMADA";
    public static final String ESTADO_CANCELADA  = "CANCELADA";
    public static final String ESTADO_ATENDIDA   = "ATENDIDA";

    /** Código único que identifica la cita en el sistema */
    private String codigoCita;

    /** Fecha y hora programada para la cita */
    private LocalDateTime fechaHoraCita;

    /** Motivo o descripción del motivo de la consulta */
    private String motivoConsulta;

    /**
     * Estado actual de la cita
     */
    private String estadoCita;

    /**
     * Médico asignado a esta cita
     */
    private Medico medicoAsignado;

    /**
     * Paciente que solicita esta cita
     */
    private Paciente pacienteAsignado;

    /**
     * Receta emitida al finalizar la consulta.
     */
    private Receta recetaEmitida;


    // Constructores
    /**
     * Constructor por defecto.
     */
    public Cita() {

    }

    /**
     * Constructor que inicializa los atributos principales de la cita
     *
     * @param codigoCita       Código único de la cita.
     * @param fechaHoraCita    Fecha y hora de la consulta.
     * @param motivoConsulta   Motivo de la consulta.
     * @param estadoCita       Estado inicial de la cita.
     * @param medicoAsignado   Médico que atenderá la cita (ASOCIACIÓN).
     * @param pacienteAsignado Paciente que solicita la cita (ASOCIACIÓN).
     */
    public Cita(String codigoCita, LocalDateTime fechaHoraCita,
                String motivoConsulta, String estadoCita,
                Medico medicoAsignado, Paciente pacienteAsignado) {
        this.codigoCita       = codigoCita;
        this.fechaHoraCita    = fechaHoraCita;
        this.motivoConsulta   = motivoConsulta;
        this.estadoCita       = estadoCita;
        this.medicoAsignado   = medicoAsignado;
        this.pacienteAsignado = pacienteAsignado;
        this.recetaEmitida    = null; //  sin receta al inicio
    }

    // Getters y Setters
    /**
     * Retorna el código de la cita.
     * @return Código único de la cita.
     */
    public String getCodigoCita() {
        return codigoCita; }

    /**
     * Establece el código de la cita.
     * @param codigoCita Nuevo código de la cita.
     */
    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita; }

    /**
     * Retorna la fecha y hora de la cita.
     * @return Fecha y hora como {@link LocalDateTime}.
     */
    public LocalDateTime getFechaHoraCita() {
        return fechaHoraCita; }

    /**
     * Establece la fecha y hora de la cita.
     * @param fechaHoraCita Nueva fecha y hora de la consulta.
     */
    public void setFechaHoraCita(LocalDateTime fechaHoraCita) {
        this.fechaHoraCita = fechaHoraCita;
    }

    /**
     * Retorna el motivo de la consulta.
     * @return Descripción del motivo de la consulta.
     */
    public String getMotivoConsulta() {
        return motivoConsulta; }

    /**
     * Establece el motivo de la consulta.
     * @param motivoConsulta Descripción del motivo.
     */
    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta; }

    /**
     * Retorna el estado actual de la cita
     */
    public String getEstadoCita() {
        return estadoCita; }

    /**
     * Establece el estado de la cita.
     * @param estadoCita Nuevo estado (PENDIENTE, CONFIRMADA, CANCELADA, ATENDIDA).
     */
    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita; }

    /**
     * Retorna el médico asignado a esta cita
     */
    public Medico getMedicoAsignado() {
        return medicoAsignado; }

    /**
     * Establece el médico asignado a la cita
     * @param medicoAsignado Médico que atenderá la consulta
     */
    public void setMedicoAsignado(Medico medicoAsignado) {
        this.medicoAsignado = medicoAsignado; }

    /**
     * Retorna el paciente asignado a esta cita (ASOCIACIÓN).
     * @return Objeto {@link Paciente} asignado.
     */
    public Paciente getPacienteAsignado() {
        return pacienteAsignado; }

    /**
     * Establece el paciente asignado a la cita
     * @param pacienteAsignado Paciente que solicita la consulta
     */
    public void setPacienteAsignado(Paciente pacienteAsignado) {
        this.pacienteAsignado = pacienteAsignado;
    }

    /**
     * Retorna la receta emitida en esta cita
     * @return Objeto {@link Receta} o {@code null} si no hay receta aún
     */
    public Receta getRecetaEmitida() {
        return recetaEmitida; }

    /**
     * Asigna la receta emitida a esta cita
     * @param recetaEmitida Receta médica emitida en la consulta
     */
    public void setRecetaEmitida(Receta recetaEmitida) {
        this.recetaEmitida = recetaEmitida; }

    // toString
    /**
     * @return Cadena con los atributos de la cita
     *
     */

    @Override
    public String toString() {

        String nombreMedicoAsignado = (medicoAsignado != null)
                ? medicoAsignado.getNombre() + " " + medicoAsignado.getApellido()
                : "Sin asignar";

        String nombrePacienteAsignado = (pacienteAsignado != null)
                ? pacienteAsignado.getNombre() + " " + pacienteAsignado.getApellido()
                : "Sin asignar";

        String codigoRecetaEmitida = (recetaEmitida != null)
                ? recetaEmitida.getCodigoReceta()
                : "Pendiente";

        return  "  Código cita     : " + codigoCita                        + "\n" +
                "  Fecha y hora    : " + fechaHoraCita.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))        + "\n" +
                "  Motivo          : " + motivoConsulta                     + "\n" +
                "  Estado          : " + estadoCita                         + "\n" +
                "  Médico asignado : " + nombreMedicoAsignado               + "\n" +
                "  Paciente        : " + nombrePacienteAsignado             + "\n" +
                "  Receta emitida  : " + codigoRecetaEmitida;
    }
}