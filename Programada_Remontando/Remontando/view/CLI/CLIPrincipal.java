package Remontando.view.CLI;

import java.util.Scanner;
import java.util.ArrayList;

import Remontando.util.ServicioClientes;
import Remontando.util.ServicioInstrumentos;
import Remontando.model.Cliente;
import Remontando.model.Instrumento;

/**
 * Vista principal para la interacción en modo consola (CLI).
 * Permite registrar clientes, consultar información, registrar
 * instrumentos y listar datos del sistema.
 */
public class CLIPrincipal {

  /** Entrada estándar del usuario. */
  private static final Scanner sc = new Scanner(System.in);

  /** Servicio encargado de la gestión de clientes. */
  private static final ServicioClientes sClientes = new ServicioClientes();

  /** Servicio encargado de la gestión de instrumentos. */
  private static final ServicioInstrumentos sInstrumentos = new ServicioInstrumentos();
    
    
  /**
   * Método principal que inicia el menú interactivo del sistema.
   * @param (no utilizados)
   */
  public static void main(String[] args) {

      while (true) {
          System.out.println("\n===== MENU PRINCIPAL =====");
          System.out.println("1. Registrar cliente");
          System.out.println("2. Consultar cliente por ID");
          System.out.println("3. Registrar instrumento");
          System.out.println("4. Listar instrumentos del cliente");
          System.out.println("5. Listar instrumentos globales");
          System.out.println("6. Eliminar cliente");
          System.out.println("7. Listar clientes");
          System.out.println("0. Salir");
          System.out.print("Seleccione: ");

          String opcion = sc.nextLine();

          switch (opcion) {
               case "1":
                  registrarCliente();
                  break;
               case "2":
                  consultarCliente();
                  break;
               case "3":
                   registrarInstrumento();
                   break;
               case "4":
                   listarInstrumentosCliente();
                   break;
               case "5":
                   listarInstrumentosGlobal();
                   break;
               case "6":
                   eliminarCliente();
                   break;
               case "7":
                   listarClientes();
                   break;
               case "0":
                   System.out.println("Saliendo...");
                   return;
               default:
                   System.out.println("Opción inválida.");
                   break;
          }
      }
    }
 
    
   /**
   * Permite registrar un nuevo cliente en el sistema.
   * Solicita los datos básicos y los envía al servicio de clientes.
   */
  private static void registrarCliente() {
      try {
          System.out.print("ID: ");
          String id = sc.nextLine();

          System.out.print("Nombre completo: ");
          String nombre = sc.nextLine();

          System.out.print("Correo: ");
          String correo = sc.nextLine();

          Cliente c = sClientes.registrar(id, nombre, correo);

          System.out.println("Cliente registrado. Código asignado: " + c.getCodigo());

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
  }

  
  /**
    * Consulta un cliente por su ID y muestra sus datos si existe.
    */
  private static void consultarCliente() {
      System.out.print("ID del cliente: ");
      String id = sc.nextLine();

      Cliente c = sClientes.buscar(id);

      if (c == null) {
          System.out.println("No existe un cliente con ese ID.");
      } else {
          System.out.println("Código: " + c.getCodigo());
          System.out.println("Nombre: " + c.getNombre());
          System.out.println("Correo: " + c.getCorreo());
      }
  }

  
  /**
   * Registra un instrumento financiero para un cliente ya existente.
   * Solicita los datos del instrumento y los envía al servicio correspondiente.
   */
  private static void registrarInstrumento() {
      try {
          System.out.print("ID del cliente: ");
          String id = sc.nextLine();

          Cliente cli = sClientes.buscar(id);

          if (cli == null) {
              System.out.println("No existe un cliente con ese ID. No se puede registrar un instrumento.");
              return;
          }

          System.out.print("Tipo (corriente/pactada/certificado): ");
          String tipo = sc.nextLine();

          System.out.print("Monto de inversión: ");
          double monto = Double.parseDouble(sc.nextLine());

          System.out.print("Periodo en días: ");
          int dias = Integer.parseInt(sc.nextLine());

          System.out.print("Moneda (CRC/colones o USD/dólares): ");
          String moneda = sc.nextLine();

          Instrumento ins = sInstrumentos.registrar(cli, tipo, monto, dias, moneda);

          imprimirDetalleInstrumento(cli, ins);

      } catch (Exception e) {
          System.out.println("ERROR: " + e.getMessage());
      }
  }
  
  
  /**
     * Lista todos los instrumentos pertenecientes a un cliente específico,
     * ordenados por monto.
     */
  private static void listarInstrumentosCliente() {
      System.out.print("ID del cliente: ");
      String id = sc.nextLine();

      Cliente cli = sClientes.buscar(id);

      if (cli == null) {
          System.out.println("No existe un cliente con ese ID.");
          return;
      }

      ArrayList<Instrumento> lista = sInstrumentos.listarPorCliente(cli);

      if (lista.isEmpty()) {
          System.out.println("Este cliente no tiene instrumentos.");
          return;
      }

      System.out.println("Instrumentos ordenados por monto:");
      for (Instrumento i : lista) {
          System.out.println(i.getTipo() + " | " + i.getMonto() + " | " + i.getFechaHora());
      }
  }

  
  /**
     * Lista todos los instrumentos registrados en el sistema,
     * ordenados por monto.
     */
  private static void listarInstrumentosGlobal() {
      ArrayList<Instrumento> lista = sInstrumentos.listarGlobal();

      if (lista.isEmpty()) {
          System.out.println("No hay instrumentos registrados.");
          return;
      }

      System.out.println("Instrumentos GLOBAL ordenados por monto:");
      for (Instrumento i : lista) {
          System.out.println(i.getTipo() + " | " + i.getMonto() + " | " + i.getFechaHora());
      }
  }

  
  /**
    * Permite eliminar un cliente del sistema luego de confirmar la acción.
     * Los instrumentos globales asociados no se eliminan.
     */
  private static void eliminarCliente() {

      System.out.print("ID del cliente a eliminar: ");
      String id = sc.nextLine();

      Cliente cli = sClientes.buscar(id);

      if (cli == null) {
          System.out.println("No existe un cliente con ese ID.");
          return;
      }

      System.out.println("Cliente encontrado:");
      System.out.println("Código: " + cli.getCodigo());
      System.out.println("Nombre: " + cli.getNombre());
      System.out.println("Correo: " + cli.getCorreo());
      System.out.println();

      System.out.print("¿Realmente desea eliminar este cliente? (s/n): ");
      String conf = sc.nextLine().trim().toLowerCase();

      if (!conf.equals("s")) {
          System.out.println("Operación cancelada.");
          return;
      }

      boolean eliminado = sClientes.eliminar(id);

      if (eliminado) {
          System.out.println("Cliente eliminado correctamente.");
          System.out.println("(Los instrumentos globales se conservan.)");
      } else {
          System.out.println("Error al eliminar el cliente.");
      }
  }

  
   /**
     * Lista todos los clientes registrados en el sistema,
     * ordenados alfabéticamente por nombre.
     */
  private static void listarClientes() {

      ArrayList<Cliente> lista = sClientes.listarOrdenados();

       if (lista.isEmpty()) {
          System.out.println("No hay clientes registrados.");
          return;
      }

      System.out.println("Clientes:");

      for (Cliente c : lista) {
          System.out.println(
                  c.getCodigo() + " | " + c.getId()
                  + " | " + c.getNombre()
                  + " | " + c.getCorreo()
          );
      }
  }
    
  /**
   * Imprime el detalle del instrumento recién registrado, junto con los datos del cliente.
   * @param cli cliente dueño del instrumento
   * @param ins instrumento registrado
   */
  
  private static void imprimirDetalleInstrumento(Cliente cli, Instrumento ins) {

      // Nombre de la moneda
      String nombreMoneda = ins.getMoneda().equalsIgnoreCase("CRC")
              ? "colones"
              : "dólares";

      // Formato de números
      String montoTexto = String.format("%,.0f", ins.getMonto());
      String interesTexto = String.format("%,.2f", ins.calcularInteres());
      String saldoTexto = String.format("%,.2f", ins.calcularRendimiento());

      double tasaAnual;

      if (ins.getTipo().equalsIgnoreCase("corriente")) {
          tasaAnual = Remontando.util.ValidadorInstrumentos.tasaCorriente(ins.getMonto()) * 100;

      } else if (ins.getTipo().equalsIgnoreCase("pactada")) {
          tasaAnual = Remontando.util.ValidadorInstrumentos.tasaPactada(
                  ins.getDias(),
                  ins.getMoneda()
          ) * 100;

      } else { // certificado
          tasaAnual = Remontando.util.ValidadorInstrumentos.tasaCertificado(
                  ins.getDias()
          ) * 100;
      }

      // Nombre del sistema

      String nombreSistema;
      if (ins.getTipo().equalsIgnoreCase("corriente")) {
          nombreSistema = "Cuenta corriente";
      } else if (ins.getTipo().equalsIgnoreCase("pactada")) {
            nombreSistema = "Cuenta pactada";
      } else {
           nombreSistema = "Certificado a plazo";
      }

    
      // Impresión final
      
      System.out.println();
      System.out.println("--- Datos del cliente y registro de su instrumento de ahorro o inversión ---");
      System.out.println("Cliente:  " + cli.getNombre());
      System.out.println("Código de Cliente " + cli.getCodigo()
               + ", ID " + cli.getId()
               + ", correo: " + cli.getCorreo());
      System.out.println();
      System.out.println("Monto de inversión:\t" + montoTexto + "  " + nombreMoneda);
      System.out.println("Plazo de la inversión:\t" + ins.getDias() + " días");
      System.out.println("Sistema de ahorro e inversión:\t" + nombreSistema);
      System.out.printf("Interés anual correspondiente:\t%.2f %%\n", tasaAnual);
      System.out.println();
      System.out.println("Rendimiento");
      System.out.println("Plazo        Monto invertido         Intereses        Saldo Final");

      System.out.printf("%-12d %-22s %-16s %-16s\n",
        ins.getDias(),
        montoTexto,
        interesTexto,
        saldoTexto);
      System.out.println("-- Última línea --");
  }
}