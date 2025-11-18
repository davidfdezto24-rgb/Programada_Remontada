package Remontando.view.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Remontando.util.ServicioClientes;
import Remontando.model.Cliente;

/**
 * Panel gráfico encargado de mostrar todos los clientes registrados
 * en el sistema, ordenados alfabéticamente por nombre. Este panel
 * forma parte de la vista (GUI) y únicamente controla la interacción
 * con el usuario, sin involucrarse en la lógica de negocio.
 *
 * <p>La funcionalidad de obtención y ordenamiento de datos se delega
 * completamente al servicio ServicioClientes, cumpliendo así con la
 * separación de responsabilidades (SoC).</p>
 */
public class GUIPanelListarClientes extends JPanel {

    /**
     * Construye el panel dedicado al listado general de clientes.
     * @param sClientes servicio encargado de obtener y ordenar los clientes
     */
  public GUIPanelListarClientes(ServicioClientes sClientes) {

      setLayout(new BorderLayout());

      JButton btn = new JButton("Mostrar lista de clientes");
      add(btn, BorderLayout.NORTH);

      JTextArea salida = new JTextArea();
      salida.setEditable(false);
      add(new JScrollPane(salida), BorderLayout.CENTER);

      btn.addActionListener(e -> {

          ArrayList<Cliente> lista = sClientes.listarOrdenados();

          if (lista.isEmpty()) {
              salida.setText("No hay clientes registrados.");
              return;
          }

          salida.setText("Clientes ordenados por nombre:\n\n");

            // Listado simple (estilo arcaico, requerido por el proyecto)
          for (Cliente c : lista) {
              salida.append(
                      c.getCodigo() + " | " +
                      c.getId() + " | " +
                      c.getNombre() + " | " +
                      c.getCorreo() + "\n"
              );
          }
      });
    }
}
