package Remontando.model;

import Remontando.util.ValidadorInstrumentos;

/**
 * Representa una cuenta corriente. Solo permite moneda CRC
 * y aplica tasas según el monto invertido.
 */
public class Corriente extends Instrumento {

   /**
   * Crea una cuenta corriente.
     * @param monto monto invertido
     * @param dias días de permanencia
     * @param moneda debe ser CRC
     * @param clienteId código del cliente
   */
  public Corriente(double monto, int dias, String moneda, String clienteId) {
      super(monto, dias, moneda, clienteId);
      ValidadorInstrumentos.validarCorriente(monto, moneda);
  }

  
  @Override
  public String getTipo() {
      return "corriente";
  }

  
  @Override
  public double calcularInteres() {
      double tasa = ValidadorInstrumentos.tasaCorriente(monto);
      return monto * tasa * (dias / 30.0);
  }

  
  @Override
  public double calcularRendimiento() {
      return monto + calcularInteres();
  }
}
