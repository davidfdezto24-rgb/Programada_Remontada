package Remontando.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase abstracta que representa un instrumento de ahorro o inversión.
 * Contiene el monto, plazo en días, moneda utilizada, código del cliente
 * y la fecha/hora en que el instrumento fue registrado.
 */
public abstract class Instrumento {

  /** Monto invertido. */
  protected double monto;

  /** Plazo total de la inversión en días. */
  protected int dias;

  /** Moneda utilizada: CRC o USD según el tipo de instrumento. */
  protected String moneda;

  /** Código del cliente dueño del instrumento. */
  protected String clienteId;

  /** Fecha y hora del registro del instrumento. */
  protected String fechaHora;

  /**
  * Crea un instrumento de inversión.
  *
  * @param monto monto invertido
  * @param dias plazo total en días
  * @param moneda moneda utilizada
  * @param clienteId código del cliente
  */
  public Instrumento(double monto, int dias, String moneda, String clienteId) {
      this.monto = monto;
      this.dias = dias;
      this.moneda = moneda;
      this.clienteId = clienteId;
      this.fechaHora = LocalDateTime.now().format(
              DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

  /** @return el monto total invertido */
  public double getMonto() {
      return monto;
  }

  /** @return número total de días de la inversión */
  public int getDias() {
      return dias;
  }

  /** @return moneda utilizada en la inversión */
  public String getMoneda() {
      return moneda;
  }

  /** @return identificación del cliente dueño del instrumento */
  public String getClienteId() {
      return clienteId;
  }

  /** @return fecha y hora del registro del instrumento */
  public String getFechaHora() {
      return fechaHora;
  }

  /**
   * Calcula el interés del instrumento.
   * @return interés generado
   */
  public abstract double calcularInteres();

  /**
   * Calcula el rendimiento total (monto + interés).
   * @return rendimiento final
   */
   public abstract double calcularRendimiento();

  /**
   * Obtiene el tipo del instrumento (corriente, pactada o certificado).
   * @return nombre del tipo de instrumento
   */
  public abstract String getTipo();
}
