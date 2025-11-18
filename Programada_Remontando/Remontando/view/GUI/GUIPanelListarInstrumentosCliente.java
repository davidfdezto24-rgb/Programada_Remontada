package Remontando.view.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Remontando.util.ServicioClientes;
import Remontando.util.ServicioInstrumentos;
import Remontando.model.Cliente;
import Remontando.model.Instrumento;

/**
 * Panel gráfico encargado de mostrar todos los instrumentos asociados
 * a un cliente específico. Permite ingresar un ID y visualizar la lista
 * ordenada de instrumentos, cumpliendo la separación de responsabilidades,
 * ya que toda la lógica de negocio se delega a los servicios.
 *
 * <p>Este panel es parte de la vista (GUI) y únicamente controla la interacción
 * con el usuario y el formato de salida, sin modificar datos ni aplicar reglas
 * de negocio.</p>
 */
public class GUIPanelListarInstrumentosCliente extends JPanel {

  /**
   * Construye el panel que lista los instrumentos de un cliente.
   *
   * @param sClientes servicio encargado de gestionar los clientes
   * @param sInstrumentos servicio encargado de gestionar los instrumentos
   */
  public GUIPanelListarInstrumentosCliente(ServicioClientes sClientes,
                                          ServicioInstrumentos sInstrumentos) {

      setLayout(new BorderLayout());

        
      JPanel form = new JPanel(new GridLayout(1, 2, 5, 5));

      JTextField txtId = new JTextField();

      form.add(new JLabel("ID del cliente:"));
      form.add(txtId);

      add(form, BorderLayout.NORTH);

      JTextArea salida = new JTextArea();
      salida.setEditable(false);
      add(new JScrollPane(salida), BorderLayout.CENTER);

      JButton btn = new JButton("Mostrar");
      add(btn, BorderLayout.SOUTH);

      btn.addActionListener(e -> {

          Cliente cli = sClientes.buscar(txtId.getText());

          if (cli == null) {
              salida.setText("Cliente no existe.\n");
              return;
          }

          ArrayList<Instrumento> lista = sInstrumentos.listarPorCliente(cli);

          if (lista.isEmpty()) {
              salida.setText("Este cliente no tiene instrumentos.");
              return;
          }

          // Cabecera de la impresión
          salida.setText("Instrumentos ordenados por monto:\n\n");

          // Listado simple (formato arcaico solicitado)
          for (Instrumento i : lista) {
               salida.append(
                       i.getTipo() + " | " +
                      i.getMonto() + " | " +
                      i.getFechaHora() + "\n"
              );
          }
      });
    }
}
