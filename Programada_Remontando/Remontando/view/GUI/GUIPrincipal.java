package Remontando.view.GUI;

import javax.swing.*;
import java.awt.*;

import Remontando.util.ServicioClientes;
import Remontando.util.ServicioInstrumentos;

/**
 * Ventana principal de la aplicación GUI para el sistema financiero.
 * Muestra un menú lateral con las opciones principales y carga dinámicamente
 * el panel correspondiente según la funcionalidad seleccionada.
 *
 * <p>Esta clase funciona como un contenedor general que administra los paneles
 * de registro, consulta, listado y eliminación. Mantiene la separación de
 * responsabilidades porque NO implementa lógica de negocio, únicamente lógica
 * de presentación.
 */
public class GUIPrincipal extends JFrame {

  /** Panel actualmente visible en la zona central de la ventana. */
  private JPanel panelActual;

  /** Servicio encargado de gestionar clientes. */
  private ServicioClientes sClientes = new ServicioClientes();

  /** Servicio encargado de gestionar instrumentos financieros. */
  private ServicioInstrumentos sInstrumentos = new ServicioInstrumentos();

  /**
   * Construye la ventana principal, configura el menú lateral
   * y define los eventos para cambiar entre paneles.
   */
  public GUIPrincipal() {

      super("Sistema Financiero - GUI");
      setSize(850, 550);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new BorderLayout());

      // MENÚ LATERAL
      JPanel menu = new JPanel();
      menu.setLayout(new GridLayout(8, 1));

      JButton b1 = new JButton("Registrar Cliente");
      JButton b2 = new JButton("Consultar Cliente");
      JButton b3 = new JButton("Registrar Instrumento");
      JButton b4 = new JButton("Instrumentos del Cliente");
      JButton b5 = new JButton("Instrumentos Globales");
      JButton b6 = new JButton("Eliminar Cliente");
      JButton b7 = new JButton("Listar Clientes");

      menu.add(b1);
      menu.add(b2);
      menu.add(b3);
      menu.add(b4);
      menu.add(b5);
      menu.add(b6);
      menu.add(b7);

      add(menu, BorderLayout.WEST);

      // PANEL CENTRAL INICIAL
      panelActual = new JPanel();
      add(panelActual, BorderLayout.CENTER);

      // EVENTOS QUE CAMBIAN LOS PANELES
      b1.addActionListener(e -> cambiarPanel(new GUIPanelRegistroCliente(sClientes)));
      b2.addActionListener(e -> cambiarPanel(new GUIPanelConsultarCliente(sClientes)));
      b3.addActionListener(e -> cambiarPanel(new GUIPanelRegistrarInstrumento(sClientes, sInstrumentos)));
      b4.addActionListener(e -> cambiarPanel(new GUIPanelListarInstrumentosCliente(sClientes, sInstrumentos)));
      b5.addActionListener(e -> cambiarPanel(new GUIPanelListarInstrumentosGlobal(sInstrumentos)));
      b6.addActionListener(e -> cambiarPanel(new GUIPanelEliminarCliente(sClientes)));
      b7.addActionListener(e -> cambiarPanel(new GUIPanelListarClientes(sClientes)));
    }

    
  /**
   * Reemplaza el panel central por uno nuevo.
   * Permite navegar entre las diferentes funcionalidades del sistema.
   * @param nuevo panel que se desea mostrar en pantalla
   */
  private void cambiarPanel(JPanel nuevo) {
      remove(panelActual);
      panelActual = nuevo;
      add(panelActual, BorderLayout.CENTER);
      revalidate();
      repaint();
  }

  
  /**
   * Método principal de ejecución del sistema en modo GUI.
   * Crea una instancia de la ventana y la hace visible.
   * @param args argumentos de ejecución (no utilizados)
   */
  public static void main(String[] args) {
      GUIPrincipal g = new GUIPrincipal();
      g.setVisible(true);
  }
}