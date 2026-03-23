package cr.ac.ucenfotec.bl.entities.cita;

import java.time.LocalDate;

/**
 * Representa una receta médica emitida durante una cita
 *
 *
 */
public class Receta {

    /** Código único que identifica la receta */
    private String codigoReceta;

    /** Lista de medicamentos prescritos separados por coma */
    private String listaMedicamentos;

    /** Indicaciones de dosis para cada medicamento*/
    private String indicacionesDosis;

    /** Instrucciones de uso adicionales para el paciente */
    private String instruccionesUso;

    /** Fecha en que fue emitida la receta */
    private LocalDate fechaEmision;

    // Constructores
    /**
     * Constructor por defecto
     */
    public Receta() {
        this.codigoReceta      = "";
        this.listaMedicamentos = "";
        this.indicacionesDosis = "";
        this.instruccionesUso  = "";
        this.fechaEmision      = LocalDate.now();
    }

    /**
     * Constructor que inicializa todos los atributos de la receta
     *
     * @param codigoReceta      Código único de la receta
     * @param listaMedicamentos Medicamentos prescritos
     * @param indicacionesDosis Dosis de cada medicamento
     * @param instruccionesUso  Instrucciones de uso para el paciente
     * @param fechaEmision      Fecha de emisión de la receta
     */
    public Receta(String codigoReceta, String listaMedicamentos,
                  String indicacionesDosis, String instruccionesUso,
                  LocalDate fechaEmision) {
        this.codigoReceta      = codigoReceta;
        this.listaMedicamentos = listaMedicamentos;
        this.indicacionesDosis = indicacionesDosis;
        this.instruccionesUso  = instruccionesUso;
        this.fechaEmision      = fechaEmision;
    }

    // Getters y Setters

    /**
     * Retorna el código de la receta
     * @return Código único de la receta
     */
    public String getCodigoReceta() {
        return codigoReceta; }

    /**
     * Establece el código de la receta
     * @param codigoReceta Nuevo código de la receta
     */
    public void setCodigoReceta(String codigoReceta) {
        this.codigoReceta = codigoReceta; }

    /**
     * Retorna la lista de medicamentos prescritos
     * @return Lista de medicamentos como cadena de texto
     */
    public String getListaMedicamentos() {
        return listaMedicamentos; }

    /**
     * Establece la lista de medicamentos prescritos
     * @param listaMedicamentos Medicamentos separados por coma
     */
    public void setListaMedicamentos(String listaMedicamentos) {
        this.listaMedicamentos = listaMedicamentos;
    }

    /**
     * Retorna las indicaciones de dosis
     * @return Indicaciones de dosis para cada medicamento
     */
    public String getIndicacionesDosis() {
        return indicacionesDosis; }

    /**
     * Establece las indicaciones de dosis
     * @param indicacionesDosis Dosis de cada medicamento
     */
    public void setIndicacionesDosis(String indicacionesDosis) {
        this.indicacionesDosis = indicacionesDosis;
    }

    /**
     * Retorna las instrucciones de uso adicionales
     * @return Instrucciones de uso para el paciente
     */
    public String getInstruccionesUso() {
        return instruccionesUso; }

    /**
     * Establece las instrucciones de uso adicionales.
     * @param instruccionesUso Instrucciones de uso para el paciente
     */
    public void setInstruccionesUso(String instruccionesUso) {
        this.instruccionesUso = instruccionesUso;
    }

    /**
     * Retorna la fecha de emisión de la receta
     * @return Fecha de emisión como {@link LocalDate}.
     */
    public LocalDate getFechaEmision() {
        return fechaEmision; }

    /**
     * Establece la fecha de emisión de la receta
     * @param fechaEmision Nueva fecha de emisión
     */
    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision; }

    // toString
    /**
     * @return Cadena con los atributos de la receta
     */
    @Override
    public String toString() {
        return  "  Código receta   : " + codigoReceta       + "\n" +
                "  Medicamentos    : " + listaMedicamentos   + "\n" +
                "  Dosis           : " + indicacionesDosis   + "\n" +
                "  Instrucciones   : " + instruccionesUso    + "\n" +
                "  Fecha emisión   : " + fechaEmision;
    }
}
