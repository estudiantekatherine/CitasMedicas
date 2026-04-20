package cr.ac.ucenfotec.bl.entities.cita;

import cr.ac.ucenfotec.bl.entities.IValidable;
import cr.ac.ucenfotec.bl.entities.persona.Medico;
import cr.ac.ucenfotec.bl.entities.persona.Paciente;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Representa una cita médica dentro del sistema.
 *
 * <p><b>Concepto POO — Asociación:</b> La cita se <em>asocia</em> a un
 * {@link Medico} y a un {@link Paciente} que existen de forma independiente
 * en la clínica. Si la cita es eliminada, el médico y el paciente continúan
 * existiendo (relación débil).</p>
 *
 * <p><b>Concepto POO — Agregación:</b> La {@link Receta} es parte de la cita,
 * pero podría existir conceptualmente sin ella. Se agrega a la cita al momento
 * de ser atendida.</p>
 *
 * <p><b>Concepto POO — Interfaz {@link IValidable}:</b> La cita implementa
 * la interfaz para exponer su código único, su descripción y auto-validarse
 * antes de ser registrada en el sistema.</p>
 *
 */
public class Cita implements IValidable {

    // Estados válidos de una cita — definidos como constantes para evitar
    // cadenas "mágicas" dispersas por el código
    /** Estado inicial al programar una cita */
    public static final String ESTADO_PENDIENTE  = "PENDIENTE";
    /** Estado cuando la cita ha sido confirmada por el médico */
    public static final String ESTADO_CONFIRMADA = "CONFIRMADA";
    /** Estado cuando la cita fue cancelada por cualquier parte */
    public static final String ESTADO_CANCELADA  = "CANCELADA";
    /** Estado final cuando la consulta fue realizada */
    public static final String ESTADO_ATENDIDA   = "ATENDIDA";

    /** Código único que identifica la cita en el sistema (p.ej. "CIT-001") */
    private String codigoCita;

    /** Fecha y hora programada para la cita */
    private LocalDateTime fechaHoraCita;

    /** Motivo o descripción de la consulta médica */
    private String motivoConsulta;

    /**
     * Estado actual de la cita.
     * Debe ser uno de: {@link #ESTADO_PENDIENTE}, {@link #ESTADO_CONFIRMADA},
     * {@link #ESTADO_CANCELADA} o {@link #ESTADO_ATENDIDA}.
     */
    private String estadoCita;

    /**
     * Médico asignado a esta cita.
     *
     * <p><b>Asociación:</b> El médico existe independientemente de la cita.</p>
     */
    private Medico medicoAsignado;

    /**
     * Paciente que solicita esta cita.
     *
     * <p><b>Asociación:</b> El paciente existe independientemente de la cita.</p>
     */
    private Paciente pacienteAsignado;

    /**
     * Receta emitida al finalizar la consulta.
     *
     * <p><b>Agregación:</b> La receta se incorpora a la cita al momento de
     * atender al paciente. Antes de eso, este campo permanece {@code null}.</p>
     */
    private Receta recetaEmitida;


    // Constructores
    /**
     * Constructor por defecto.
     */
    public Cita() {
    }

    /**
     * Constructor que inicializa los atributos principales de la cita.
     * La receta se deja en {@code null} porque aún no ha sido atendida.
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
        this.recetaEmitida    = null; // sin receta hasta ser atendida
    }


    // Getters y Setters
       /**
     * Retorna el código único de la cita.
     * @return Código de la cita.
     */
    public String getCodigoCita() {
        return codigoCita;
    }

    /**
     * Establece el código único de la cita.
     * @param codigoCita Nuevo código de la cita.
     */
    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita;
    }

    /**
     * Retorna la fecha y hora programada de la cita.
     * @return Fecha y hora como {@link LocalDateTime}.
     */
    public LocalDateTime getFechaHoraCita() {
        return fechaHoraCita;
    }

    /**
     * Establece la fecha y hora de la cita.
     * @param fechaHoraCita Nueva fecha y hora de la consulta.
     */
    public void setFechaHoraCita(LocalDateTime fechaHoraCita) {
        this.fechaHoraCita = fechaHoraCita;
    }

    /**
     * Retorna el motivo de la consulta médica.
     * @return Descripción del motivo.
     */
    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    /**
     * Establece el motivo de la consulta.
     * @param motivoConsulta Descripción del motivo de la cita.
     */
    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    /**
     * Retorna el estado actual de la cita.
     * @return Estado de la cita (PENDIENTE, CONFIRMADA, CANCELADA o ATENDIDA).
     */
    public String getEstadoCita() {
        return estadoCita;
    }

    /**
     * Establece el estado de la cita.
     * @param estadoCita Nuevo estado (usar las constantes de esta clase).
     */
    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }

    /**
     * Retorna el médico asignado a esta cita.
     * @return Objeto {@link Medico} asignado.
     */
    public Medico getMedicoAsignado() {
        return medicoAsignado;
    }

    /**
     * Establece el médico asignado a la cita.
     * @param medicoAsignado Médico que atenderá la consulta.
     */
    public void setMedicoAsignado(Medico medicoAsignado) {
        this.medicoAsignado = medicoAsignado;
    }

    /**
     * Retorna el paciente asignado a esta cita.
     * @return Objeto {@link Paciente} asignado.
     */
    public Paciente getPacienteAsignado() {
        return pacienteAsignado;
    }

    /**
     * Establece el paciente asignado a la cita.
     * @param pacienteAsignado Paciente que solicita la consulta.
     */
    public void setPacienteAsignado(Paciente pacienteAsignado) {
        this.pacienteAsignado = pacienteAsignado;
    }

    /**
     * Retorna la receta emitida en esta cita.
     * @return Objeto {@link Receta} o {@code null} si la cita aún no fue atendida.
     */
    public Receta getRecetaEmitida() {
        return recetaEmitida;
    }

    /**
     * Asigna la receta emitida a esta cita.
     * @param recetaEmitida Receta médica emitida durante la consulta.
     */
    public void setRecetaEmitida(Receta recetaEmitida) {
        this.recetaEmitida = recetaEmitida;
    }


    // Implementación de IValidable
    /**
     * Retorna el código único de la cita como identificador del sistema.
     *
     * @return Código único de la cita.
     */
    @Override
    public String getCodigo() {
        return codigoCita;
    }

    /**
     * Valida que los campos obligatorios de la cita no estén vacíos ni nulos.
     *
     * <p>Campos validados: código, motivo, fecha, estado, médico y paciente.</p>
     *
     * @return {@code true} si todos los campos requeridos son válidos.
     */
    @Override
    public boolean validarDatos() {
        return codigoCita      != null && !codigoCita.isBlank()      &&
                motivoConsulta  != null && !motivoConsulta.isBlank()  &&
                fechaHoraCita   != null                               &&
                estadoCita      != null && !estadoCita.isBlank()      &&
                medicoAsignado  != null                               &&
                pacienteAsignado != null;
    }

    /**
     * Retorna una descripción resumida de la cita en una sola línea.
     *
     * @return Código, fecha, médico y paciente de la cita.
     */
    @Override
    public String obtenerDescripcion() {
        String nombreMedico   = (medicoAsignado   != null) ? medicoAsignado.getNombre()   + " " + medicoAsignado.getApellido()   : "Sin asignar";
        String nombrePaciente = (pacienteAsignado != null) ? pacienteAsignado.getNombre() + " " + pacienteAsignado.getApellido() : "Sin asignar";
        return "[" + codigoCita + "] " +
                fechaHoraCita.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) +
                " | Dr. " + nombreMedico + " → " + nombrePaciente +
                " (" + estadoCita + ")";
    }


    // Identidad de objetos
    /**
     * Dos citas son iguales si comparten el mismo código de cita.
     *
     * @param objeto Objeto a comparar.
     * @return {@code true} si ambas tienen el mismo código de cita.
     */
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null || getClass() != objeto.getClass()) return false;
        Cita otraCita = (Cita) objeto;
        return Objects.equals(codigoCita, otraCita.codigoCita);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(codigoCita);
    }


    // toString
      /**
     * Retorna una representación textual completa de la cita.
     *
     * @return Cadena con todos los atributos de la cita.
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

        return  "  Código cita     : " + codigoCita                                            + "\n" +
                "  Fecha y hora    : " + fechaHoraCita.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))                            + "\n" +
                "  Motivo          : " + motivoConsulta                                         + "\n" +
                "  Estado          : " + estadoCita                                             + "\n" +
                "  Médico asignado : " + nombreMedicoAsignado                                   + "\n" +
                "  Paciente        : " + nombrePacienteAsignado                                 + "\n" +
                "  Receta emitida  : " + codigoRecetaEmitida;
    }
}