package Remontando.model;

import java.util.ArrayList;

/**
 * Representa un cliente del sistema, el cual puede registrar uno o más
 * instrumentos de ahorro o inversión. Contiene la información básica del cliente
 * y la lista de instrumentos asociados.
 */
public class Cliente {

  /** Identificación única del cliente. */
  private String id;

  /** Nombre completo del cliente. */
  private String nombre;

  /** Correo electrónico del cliente. */
  private String correo;

  /** Código asignado automáticamente al cliente (CLI-X). */
  private String codigo;

    /** Lista de instrumentos asociados al cliente. */
  private ArrayList<Instrumento> instrumentos = new ArrayList<>();

  
  /**
   * Crea un nuevo cliente con los datos básicos.
   * @param id identificación del cliente
   * @param nombre nombre completo
   * @param correo correo electrónico válido
   * @param codigo código asignado automáticamente
   */
  public Cliente(String id, String nombre, String correo, String codigo) {
      this.id = id;
      this.nombre = nombre;
      this.correo = correo;
      this.codigo = codigo;
  }

  
  /**
  * Obtiene la identificación del cliente.
  * @return id del cliente
  */
  public String getId() {
      return id;
  }

  
  /**
   * Obtiene el nombre completo del cliente.
   * @return nombre del cliente
   */
  public String getNombre() {
      return nombre;
  }

  
  /**
   * Obtiene el correo electrónico del cliente.
   * @return correo del cliente
   */
  public String getCorreo() {
      return correo;
  }

  
  /**
    * Obtiene el código asignado al cliente.
    * @return código del cliente
    */
  public String getCodigo() {
      return codigo;
  }

  
  /**
   * Agrega un instrumento a la lista del cliente.
   * @param instrumento instrumento a registrar
   */
  public void agregarInstrumento(Instrumento instrumento) {
      instrumentos.add(instrumento);
  }

  
  /**
   * Obtiene la lista completa de instrumentos del cliente.
   * @return lista de instrumentos asociados
   */
  public ArrayList<Instrumento> getInstrumentos() {
      return instrumentos;
    }
}