package Remontando.view.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Remontando.util.ServicioInstrumentos;
import Remontando.model.Instrumento;

/**
 * Panel encargado de mostrar todos los instrumentos registrados
 * en el sistema, ordenados por monto. Este panel pertenece a
 * la vista (GUI) y únicamente controla la interacción con el usuario,
 * manteniendo la separación de responsabilidades con respecto a la
 * lógica de negocio, la cual reside en ServicioInstrumentos.
 */
public class GUIPanelListarInstrumentosGlobal extends JPanel {

  /**
   * Construye un panel que permite listar todos los instrumentos
   * globales almacenados en el sistema.
   * @param sInstrumentos servicio que administra la lista global de instrumentos financieros
   */
  public GUIPanelListarInstrumentosGlobal(ServicioInstrumentos sInstrumentos) {

      setLayout(new BorderLayout());

      JButton btn = new JButton("Mostrar instrumentos globales");
      add(btn, BorderLayout.NORTH);

      JTextArea salida = new JTextArea();
      salida.setEditable(false);
      add(new JScrollPane(salida), BorderLayout.CENTER);

      btn.addActionListener(e -> {

          ArrayList<Instrumento> lista = sInstrumentos.listarGlobal();

          if (lista.isEmpty()) {
              salida.setText("No hay instrumentos registrados.");
              return;
          }

          salida.setText("Instrumentos GLOBAL ordenados por monto:\n\n");

          // Impresión simple y arcaica, como requiere el proyecto
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
