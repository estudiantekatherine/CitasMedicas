package cr.ac.ucenfotec.bl.entities;

/**
 * Interfaz que define el contrato de validación para las entidades del sistema.
 *
 * <p>Cualquier entidad que pueda ser registrada en la clínica debe implementar
 * esta interfaz, garantizando que expone un código único, una descripción
 * legible y la capacidad de auto-validar sus datos antes de ser persistida.</p>
 *
 * <p><b>Concepto POO aplicado:</b> <i>Interfaz</i> — define un contrato que
 * las clases {@link cr.ac.ucenfotec.bl.entities.persona.Medico},
 * {@link cr.ac.ucenfotec.bl.entities.persona.Paciente} y
 * {@link cr.ac.ucenfotec.bl.entities.cita.Cita} están obligadas a cumplir,
 * permitiendo tratarlas de forma polimórfica desde la capa de lógica.</p>
 *
 */
public interface IValidable {

    /**
     * Retorna el código único que identifica a la entidad dentro del sistema.
     *
     * @return Cadena con el código único de la entidad.
     */
    String getCodigo();

    /**
     * Valida que los campos obligatorios de la entidad no estén vacíos ni nulos.
     *
     * <p>Cada clase implementadora define su propia regla de negocio;
     * por ejemplo, un {@code Medico} verifica código, cédula y especialidad,
     * mientras que un {@code Paciente} verifica además la fecha de nacimiento.</p>
     *
     * @return {@code true} si los datos son válidos; {@code false} en caso contrario.
     */
    boolean validarDatos();

    /**
     * Retorna una descripción corta y legible de la entidad,
     * útil para mostrar en listas o resúmenes de pantalla.
     *
     * @return Descripción resumida de la entidad.
     */
    String obtenerDescripcion();
}