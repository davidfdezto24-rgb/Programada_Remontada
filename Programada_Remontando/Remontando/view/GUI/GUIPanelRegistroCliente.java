package Remontando.view.GUI;

import javax.swing.*;
import java.awt.*;

import Remontando.util.ServicioClientes;
import Remontando.model.Cliente;

/**
 * Panel gráfico encargado de registrar un nuevo cliente en el sistema.
 * Este panel pertenece a la capa de presentación (GUI) y se limita a
 * interactuar con el usuario, delegando toda la lógica de negocio al
 * servicio ServicioClientes, en cumplimiento con el principio de
 * separación de responsabilidades (SoC).
 *
 * <p>El panel contiene un pequeño formulario con campos para ID, nombre
 * y correo electrónico, además de un botón que ejecuta el registro.
 */
public class GUIPanelRegistroCliente extends JPanel {

  /**
    * Construye el panel que permite registrar clientes.
    * @param sClientes servicio encargado de gestionar los clientes
    */
  public GUIPanelRegistroCliente(ServicioClientes sClientes) {

      setLayout(new BorderLayout());

      JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));

      JTextField txtId = new JTextField();
      JTextField txtNombre = new JTextField();
      JTextField txtCorreo = new JTextField();

      form.add(new JLabel("ID:"));
      form.add(txtId);

      form.add(new JLabel("Nombre completo:"));
      form.add(txtNombre);

      form.add(new JLabel("Correo electrónico:"));
      form.add(txtCorreo);

      JButton btn = new JButton("Registrar Cliente");
      form.add(btn);

      add(form, BorderLayout.NORTH);

      JTextArea salida = new JTextArea();
      salida.setEditable(false);
      salida.setFont(new Font("Monospaced", Font.PLAIN, 13));

      JScrollPane scroll = new JScrollPane(salida);
      add(scroll, BorderLayout.CENTER);

      btn.addActionListener(e -> {
          try {

              // Registrar el cliente (lógica delegada al servicio)
              Cliente c = sClientes.registrar(
                       txtId.getText(),
                       txtNombre.getText(),
                       txtCorreo.getText()
              );

              // Construcción de salida formateada
              StringBuilder sb = new StringBuilder();
              sb.append("--- Cliente registrado exitosamente ---\n");
              sb.append("Nombre:  ").append(c.getNombre()).append("\n");
              sb.append("ID:      ").append(c.getId()).append("\n");
              sb.append("Correo:  ").append(c.getCorreo()).append("\n");
              sb.append("Código asignado: ").append(c.getCodigo()).append("\n");
              sb.append("-----------------------------------------\n");

              salida.setText(sb.toString());

           } catch (Exception ex) {
               JOptionPane.showMessageDialog(this, ex.getMessage());
           }
      });
    }
}