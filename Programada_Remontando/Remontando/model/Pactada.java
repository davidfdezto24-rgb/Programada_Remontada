package Remontando.model;

import Remontando.util.ValidadorInstrumentos;

/**
 * Representa una inversión a la vista pactada.
 * Permite CRC y USD según reglas del negocio.
 */
public class Pactada extends Instrumento {

    
  /**
   * Crea una inversión pactada.
   * @param monto monto invertido
   * @param dias plazo total
   * @param moneda CRC
   * @param clienteId código del cliente
   */
  public Pactada(double monto, int dias, String moneda, String clienteId) {
      super(monto, dias, moneda, clienteId);
      ValidadorInstrumentos.validarPactada(monto, dias, moneda);
  }

  
  @Override
  public String getTipo() {
      return "pactada";
  }

  
  @Override
  public double calcularInteres() {
      double tasa = ValidadorInstrumentos.tasaPactada(dias, moneda);
      return monto * tasa * (dias / 30.0);
  }

  
  @Override
  public double calcularRendimiento() {
      return monto + calcularInteres();
  }
}
