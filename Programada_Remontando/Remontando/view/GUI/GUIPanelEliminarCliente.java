package Remontando.view.GUI;

import javax.swing.*;
import java.awt.*;

import Remontando.util.ServicioClientes;

/**
 * Panel gráfico encargado de eliminar un cliente del sistema.
 * Permite ingresar un ID y solicitar su eliminación mediante un botón.
 * <p>Este panel forma parte de la capa de presentación (GUI) y únicamente
 * se encarga de la interacción con el usuario. La lógica de negocio,
 * verificación de existencia y eliminación del cliente se encuentra en
 * la clase ServicioClientes, cumpliendo con la separación de
 * responsabilidades (SoC).
 */
public class GUIPanelEliminarCliente extends JPanel {

  /**
   * Construye el panel para eliminar un cliente, mostrando el campo
   * para ingresar el ID y el botón para ejecutar la acción.
   * @param sClientes servicio encargado de gestionar a los clientes
   */
  public GUIPanelEliminarCliente(ServicioClientes sClientes) {

      setLayout(new BorderLayout());

      JPanel form = new JPanel(new GridLayout(1, 2, 5, 5));

      JTextField txtId = new JTextField();

      form.add(new JLabel("ID del cliente:"));
      form.add(txtId);

      add(form, BorderLayout.NORTH);

      JTextArea salida = new JTextArea();
      salida.setEditable(false);
      add(new JScrollPane(salida), BorderLayout.CENTER);

      JButton btn = new JButton("Eliminar");
      add(btn, BorderLayout.SOUTH);

      btn.addActionListener(e -> {

          boolean eliminado = sClientes.eliminar(txtId.getText());

          if (eliminado) {
              salida.setText("Cliente eliminado.\n");
          } else {
              salida.setText("No existe ese cliente.\n");
          }
      });
  }
}

