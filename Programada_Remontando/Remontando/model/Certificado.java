package Remontando.model;

import Remontando.util.ValidadorInstrumentos;

/**
 * Representa un certificado de depósito a plazo.
 * Solo opera en colones y aplica retención del 8%.
 */
public class Certificado extends Instrumento {

  /**
   * Crea un certificado de depósito.
     * @param monto monto invertido
     * @param dias plazo total
     * @param moneda debe ser CRC
     * @param clienteId código del cliente
   */
  public Certificado(double monto, int dias, String moneda, String clienteId) {
      super(monto, dias, moneda, clienteId);
      ValidadorInstrumentos.validarCertificado(monto, dias, moneda);
  }

  
  @Override
  public String getTipo() {
      return "certificado";
  }

  
  @Override
  public double calcularInteres() {
      double tasa = ValidadorInstrumentos.tasaCertificado(dias);
      double interes = monto * tasa * (dias / 30.0);
      return ValidadorInstrumentos.retener8(interes);
  }

  
  @Override
  public double calcularRendimiento() {
      return monto + calcularInteres();
  }
}