package Remontando.view.GUI;

import javax.swing.*;
import java.awt.*;

import Remontando.util.ServicioClientes;
import Remontando.model.Cliente;

/**
 * Panel gráfico encargado de la consulta de un cliente por su ID.
 * Este panel forma parte de la vista (GUI) y únicamente maneja la
 * interacción con el usuario. La lógica de negocio se mantiene en
 * la clase ServicioClientes, cumpliendo así la separación de responsabilidades.
 * <p>El panel permite escribir un ID, presionar un botón y visualizar
 * en pantalla los datos básicos del cliente consultado.</p>
 */
public class GUIPanelConsultarCliente extends JPanel {

  /**
   * Construye el panel de consulta de cliente.
   *
   * @param sClientes referencia al servicio encargado de gestionar clientes
   */
  public GUIPanelConsultarCliente(ServicioClientes sClientes) {

      // Distribución general del panel
      setLayout(new BorderLayout());

      JPanel form = new JPanel(new GridLayout(2, 2, 5, 5));

      JTextField txtId = new JTextField();

      form.add(new JLabel("ID del cliente:"));
      form.add(txtId);

      JButton boton = new JButton("Consultar");
      form.add(boton);

      add(form, BorderLayout.NORTH);

      JTextArea salida = new JTextArea();
      salida.setEditable(false);
      add(new JScrollPane(salida), BorderLayout.CENTER);

      
      boton.addActionListener(e -> {

          Cliente c = sClientes.buscar(txtId.getText());

          if (c == null) {

           salida.setText("NO existe un cliente con ese ID.\n");

          } else {

              salida.setText(
                  "Código: " + c.getCodigo() + "\n" +
                  "Nombre: " + c.getNombre() + "\n" +
                  "Correo: " + c.getCorreo() + "\n"
              );
            }
      });
    }
}