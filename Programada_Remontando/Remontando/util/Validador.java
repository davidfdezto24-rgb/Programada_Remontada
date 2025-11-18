package Remontando.util;

import java.util.regex.Pattern;

/**
 * Clase utilitaria encargada de validar los datos ingresados por el usuario,
 * tanto en la vista CLI como en la vista GUI. Su función es garantizar la
 * robustez de la entrada según los requerimientos del proyecto, cumpliendo
 * con la separación de responsabilidades (SoC), ya que no manipula modelos
 * ni lógica de negocio.
 *
 * <p>Incluye validaciones de texto obligatorio, formato de correo,
 * montos, días y monedas permitidas.</p>
 */
public class Validador {

  /** Expresión regular que valida el formato estándar de un correo electrónico. */
  private static final Pattern CORREO_REGEX =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

  /**
   * Valida que un texto no sea nulo ni vacío.
   * @param valor texto a verificar
   * @param campo nombre del campo (para mostrar en el error)
   * @throws IllegalArgumentException si el texto es nulo o vacío
   */
  public static void texto(String valor, String campo) {
      if (valor == null || valor.trim().isEmpty()) {
          throw new IllegalArgumentException("Debe ingresar " + campo);
      }
  }

  /**
   * Valida que el correo electrónico tenga un formato correcto.
   * @param correo correo a validar
   * @throws IllegalArgumentException si el formato no es válido
   */
  public static void correo(String correo) {
      if (correo == null || !CORREO_REGEX.matcher(correo).matches()) {
            throw new IllegalArgumentException("Correo inválido");
      }
  }

  /**
   * Valida que el monto sea mayor a cero.
   * @param monto valor del monto
   * @throws IllegalArgumentException si el monto es menor o igual a cero
   */
  public static void monto(double monto) {
      if (monto <= 0) {
            throw new IllegalArgumentException("Monto inválido");
      }
  }

  /**
   * Valida que la cantidad de días sea mayor a cero.
   * @param dias cantidad de días
   * @throws IllegalArgumentException si los días son menores o iguales a cero
   */
  public static void dias(int dias) {
      if (dias <= 0) {
          throw new IllegalArgumentException("Días inválidos");
      }
  }

  /**
   * Valida la moneda ingresada y la normaliza a mayúsculas.
   * Solo se aceptan CRC y USD.
   * @param moneda texto ingresado por el usuario
   * @return moneda en formato normalizado (CRC o USD)
   * @throws IllegalArgumentException si la moneda no es válida
   */
  public static String moneda(String moneda) {

      if (moneda == null || moneda.trim().isEmpty()) {
          throw new IllegalArgumentException("Moneda inválida");
      }

      String m = moneda.trim().toUpperCase();

      if (!m.equals("CRC") && !m.equals("USD")) {
          throw new IllegalArgumentException("Moneda inválida");
      }

      return m;
    }
}