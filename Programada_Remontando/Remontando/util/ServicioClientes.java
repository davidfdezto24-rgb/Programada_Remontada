package Remontando.util;

import Remontando.model.Cliente;
import java.util.ArrayList;

/**
 * Servicio encargado de gestionar todas las operaciones relacionadas
 * con los clientes del sistema. 
 * Las operaciones contempladas incluyen: registrar clientes, buscar
 * clientes, eliminar clientes y devolver una lista ordenada por nombre.</p>
 */
public class ServicioClientes {

  /**
   * Registra un nuevo cliente después de validar sus datos.
   * @param id identificación única del cliente
   * @param nombre nombre completo del cliente
   * @param correo correo electrónico del cliente
   * @return el cliente registrado
   * @throws IllegalArgumentException si los datos no son válidos o si el ID ya existe
   */
  public Cliente registrar(String id, String nombre, String correo) {

      // Validaciones de entrada
      Validador.texto(id, "ID");
      Validador.texto(nombre, "nombre");
      Validador.correo(correo);

      // Verificar duplicados
      if (buscar(id) != null) {
          throw new IllegalArgumentException("Ese ID ya existe");
      }

      // Generación del código del cliente
      String codigo = GeneradorCodigo.siguiente();

      // Crear y almacenar el cliente
      Cliente c = new Cliente(id, nombre, correo, codigo);
      Memoria.CLIENTES.add(c);

      return c;
  }

  /**
   * Busca un cliente en memoria por su ID.
    * @param id identificación del cliente
    * @return el cliente encontrado o null si no existe
   */
  public Cliente buscar(String id) {
      for (Cliente c : Memoria.CLIENTES) {
          if (c.getId().equals(id)) {
                return c;
          }
      }
      return null;
  }

  /**
   * Elimina un cliente del sistema, pero mantiene los instrumentos 
   * asociados en la lista global (según requerimientos del proyecto).
   * @param id identificación del cliente a eliminar
   * @return true si el cliente fue eliminado, false si no existía
   */
  public boolean eliminar(String id) {

    Cliente c = buscar(id);

    if (c == null) {
            return false;
     }

    // Eliminarlo de la memoria principal
    return Memoria.CLIENTES.remove(c);
  }

   /**
   * Devuelve una lista ordenada alfabéticamente por nombre del cliente.
   * La lista retornada es una copia, protegiendo así la lista global en Memoria.
   * @return lista de clientes ordenados por nombre
   */
  public ArrayList<Cliente> listarOrdenados() {

      ArrayList<Cliente> copia = new ArrayList<>(Memoria.CLIENTES);
      Ordenamiento.ordenarClientes(copia);

      return copia;
  }
}
