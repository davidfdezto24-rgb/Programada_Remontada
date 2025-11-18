package Remontando.util;

import java.util.ArrayList;
import Remontando.model.Cliente;
import Remontando.model.Instrumento;

/**
 * Clase utilitaria encargada de realizar ordenamientos simples sobre listas de clientes e instrumentos.
 * El proyecto solicita un enfoque arcaico y sencillo, por lo que aquí se
 * utiliza el algoritmo Bubble Sort, el cual es fácil de entender y suficiente
 * para los tamaños de listas esperados en este sistema académico.
 * no crea objetos ni realiza validaciones.
 */
public class Ordenamiento {

  /**
   * Ordena alfabéticamente una lista de clientes según su nombre.
   * El ordenamiento se realiza usando el algoritmo Bubble Sort.
   *
   * @param lista lista de clientes a ordenar
   */
  public static void ordenarClientes(ArrayList<Cliente> lista) {

      for (int i = 0; i < lista.size() - 1; i++) {
          for (int j = 0; j < lista.size() - i - 1; j++) {

                // comparacion de nombres ignorando mayúsculas
              if (lista.get(j).getNombre().compareToIgnoreCase(
                      lista.get(j + 1).getNombre()) > 0) {

                  Cliente tmp = lista.get(j);
                  lista.set(j, lista.get(j + 1));
                  lista.set(j + 1, tmp);
              }
          }
      }
  }

  /**
   * Ordena una lista de instrumentos según su monto,
   * de forma ascendente. Implementado usando Bubble Sort.
   * @param lista lista de instrumentos a ordenar
   */
  public static void ordenarInstrumentos(ArrayList<Instrumento> lista) {

      for (int i = 0; i < lista.size() - 1; i++) {
          for (int j = 0; j < lista.size() - i - 1; j++) {

              if (lista.get(j).getMonto() > lista.get(j + 1).getMonto()) {

                  Instrumento tmp = lista.get(j);
                  lista.set(j, lista.get(j + 1));
                  lista.set(j + 1, tmp);
              }
          }
      }
  }
}
