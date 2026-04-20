package cr.ac.ucenfotec.bl.entities.cita;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Representa una receta médica emitida durante una cita.
 *
 * <p><b>Concepto POO — Agregación:</b> La receta es creada por el
 * {@link cr.ac.ucenfotec.bl.logic.Service} y luego asignada a una
 * {@link Cita} al momento de atender al paciente. La receta podría
 * existir conceptualmente de forma independiente a la cita.</p>
 *
 * <p><b>Concepto POO — Identidad de objetos:</b> Dos recetas son iguales
 * si comparten el mismo código único de receta.</p>
 *
 */
public class Receta {

    /** Código único que identifica la receta (p.ej. "REC-001") */
    private String codigoReceta;

    /** Lista de medicamentos prescritos, separados por coma */
    private String listaMedicamentos;

    /** Indicaciones de dosis para cada medicamento prescrito */
    private String indicacionesDosis;

    /** Instrucciones de uso adicionales para el paciente */
    private String instruccionesUso;

    /** Fecha en que fue emitida la receta */
    private LocalDate fechaEmision;


    // Constructores
     /**
     * Constructor por defecto.
     * Inicializa los textos con cadena vacía y la fecha con el día actual.
     */
    public Receta() {
        this.codigoReceta      = "";
        this.listaMedicamentos = "";
        this.indicacionesDosis = "";
        this.instruccionesUso  = "";
        this.fechaEmision      = LocalDate.now();
    }

    /**
     * Constructor que inicializa todos los atributos de la receta.
     *
     * @param codigoReceta      Código único de la receta.
     * @param listaMedicamentos Medicamentos prescritos (separados por coma).
     * @param indicacionesDosis Dosis indicada para cada medicamento.
     * @param instruccionesUso  Instrucciones de uso para el paciente.
     * @param fechaEmision      Fecha de emisión de la receta.
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
     * Retorna el código único de la receta.
     * @return Código de la receta.
     */
    public String getCodigoReceta() {
        return codigoReceta;
    }

    /**
     * Establece el código único de la receta.
     * @param codigoReceta Nuevo código de la receta.
     */
    public void setCodigoReceta(String codigoReceta) {
        this.codigoReceta = codigoReceta;
    }

    /**
     * Retorna la lista de medicamentos prescritos.
     * @return Medicamentos como cadena de texto separada por comas.
     */
    public String getListaMedicamentos() {
        return listaMedicamentos;
    }

    /**
     * Establece la lista de medicamentos prescritos.
     * @param listaMedicamentos Medicamentos separados por coma.
     */
    public void setListaMedicamentos(String listaMedicamentos) {
        this.listaMedicamentos = listaMedicamentos;
    }

    /**
     * Retorna las indicaciones de dosis para cada medicamento.
     * @return Indicaciones de dosis.
     */
    public String getIndicacionesDosis() {
        return indicacionesDosis;
    }

    /**
     * Establece las indicaciones de dosis.
     * @param indicacionesDosis Dosis de cada medicamento prescrito.
     */
    public void setIndicacionesDosis(String indicacionesDosis) {
        this.indicacionesDosis = indicacionesDosis;
    }

    /**
     * Retorna las instrucciones de uso adicionales para el paciente.
     * @return Instrucciones de uso.
     */
    public String getInstruccionesUso() {
        return instruccionesUso;
    }

    /**
     * Establece las instrucciones de uso adicionales.
     * @param instruccionesUso Instrucciones de uso para el paciente.
     */
    public void setInstruccionesUso(String instruccionesUso) {
        this.instruccionesUso = instruccionesUso;
    }

    /**
     * Retorna la fecha de emisión de la receta.
     * @return Fecha de emisión como {@link LocalDate}.
     */
    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Establece la fecha de emisión de la receta.
     * @param fechaEmision Nueva fecha de emisión.
     */
    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }


    // Identidad de objetos
      /**
     * Dos recetas son iguales si comparten el mismo código único de receta.
     *
     * @param objeto Objeto a comparar.
     * @return {@code true} si ambas tienen el mismo código de receta.
     */
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null || getClass() != objeto.getClass()) return false;
        Receta otraReceta = (Receta) objeto;
        return Objects.equals(codigoReceta, otraReceta.codigoReceta);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(codigoReceta);
    }


    // toString
     /**
     * Retorna una representación textual completa de la receta.
     *
     * @return Cadena con todos los atributos de la receta.
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