package Remontando.view.GUI;

import javax.swing.*;
import java.awt.*;

import Remontando.util.ServicioClientes;
import Remontando.util.ServicioInstrumentos;
import Remontando.util.ValidadorInstrumentos;

import Remontando.model.Cliente;
import Remontando.model.Instrumento;

/**
 * Panel encargado de registrar un instrumento financiero asociado a un cliente.
 * Pertenece exclusivamente a la capa de presentación (GUI), mientras que toda la
 * lógica de negocio —validación, cálculos, asignación y creación del instrumento—
 * se mantiene en las clases de servicio, cumpliendo así la separación de
 * responsabilidades (SoC).
 *
 * <p>El panel contiene un formulario simple donde se ingresan los datos del
 * con el usuario. Tras registrar el instrumento, se muestra una salida formateada
 * idéntica a la utilizada en la vista CLI.</p>
 */
public class GUIPanelRegistrarInstrumento extends JPanel {

  /**
  * Construye el panel de registro de instrumentos financieros.
  * @param sClientes servicio que administra los clientes
  * @param sInstrumentos servicio que administra los instrumentos
  */
  public GUIPanelRegistrarInstrumento(ServicioClientes sClientes,ServicioInstrumentos sInstrumentos) {

      setLayout(new BorderLayout());

      JPanel form = new JPanel(new GridLayout(6, 2, 5, 5));

      JTextField txtId = new JTextField();
      JTextField txtTipo = new JTextField();
      JTextField txtMonto = new JTextField();
      JTextField txtDias = new JTextField();
      JTextField txtMoneda = new JTextField();

      form.add(new JLabel("ID del cliente:"));
      form.add(txtId);

      form.add(new JLabel("Tipo (corriente/pactada/certificado):"));
      form.add(txtTipo);

      form.add(new JLabel("Monto:"));
      form.add(txtMonto);

      form.add(new JLabel("Días:"));
      form.add(txtDias);

      form.add(new JLabel("Moneda (CRC/colones o USD/dólares):"));
      form.add(txtMoneda);

      JButton btn = new JButton("Registrar instrumento");
      form.add(btn);

      add(form, BorderLayout.NORTH);

      JTextArea salida = new JTextArea();
      salida.setEditable(false);
      salida.setFont(new Font("Monospaced", Font.PLAIN, 13));
      JScrollPane scroll = new JScrollPane(salida);
      add(scroll, BorderLayout.CENTER);

      btn.addActionListener(e -> {
          try {
              Cliente cli = sClientes.buscar(txtId.getText());

              if (cli == null) {
                  JOptionPane.showMessageDialog(this,
                          "No existe un cliente con ese ID. No se puede registrar un instrumento.");
                  return;
              }

              double monto = Double.parseDouble(txtMonto.getText());
              int dias = Integer.parseInt(txtDias.getText());

              Instrumento ins = sInstrumentos.registrar(
                      cli,
                      txtTipo.getText(),
                      monto,
                      dias,
                      txtMoneda.getText()
              );

              String nombreMoneda =
                      ins.getMoneda().equalsIgnoreCase("CRC") ? "colones" : "dólares";

              String montoTxt   = String.format("%,.0f", ins.getMonto());
              String interesTxt = String.format("%,.2f", ins.calcularInteres());
              String saldoTxt   = String.format("%,.2f", ins.calcularRendimiento());

              String nombreSistema;
              double tasaAnual;

              switch (ins.getTipo().toLowerCase()) {
                   case "corriente":
                       nombreSistema = "Cuenta corriente";
                       tasaAnual = ValidadorInstrumentos.tasaCorriente(ins.getMonto()) * 100;
                       break;

                   case "pactada":
                       nombreSistema = "Cuenta pactada";
                       tasaAnual = ValidadorInstrumentos.tasaPactada(ins.getDias(), ins.getMoneda()) * 100;
                       break;

                   default:
                      nombreSistema = "Certificado a plazo";
                      tasaAnual = ValidadorInstrumentos.tasaCertificado(ins.getDias()) * 100;
                      break;
             }

                StringBuilder sb = new StringBuilder();

                sb.append("--- Datos del cliente y registro de su instrumento ---\n");
                sb.append("Cliente:  ").append(cli.getNombre()).append("\n");
                sb.append("Código de Cliente ").append(cli.getCodigo())
                        .append(", ID ").append(cli.getId())
                        .append(", correo: ").append(cli.getCorreo()).append("\n\n");

                sb.append("Monto de ahorro e inversión:\t").append(montoTxt)
                        .append("  ").append(nombreMoneda).append("\n");
                sb.append("Plazo de la inversión días:\t").append(ins.getDias()).append(" días\n");
                sb.append("Sistema de ahorro e inversión:\t").append(nombreSistema).append("\n");
                sb.append(String.format("Interés anual correspondiente:\t%.2f %%\n\n", tasaAnual));

                sb.append("Rendimiento\n");
                sb.append("Plazo        Monto invertido         Intereses        Saldo Final\n");
                sb.append(String.format(
                        "%-12d %-22s %-16s %-16s\n",
                        ins.getDias(), montoTxt, interesTxt, saldoTxt
                ));

                sb.append("-- Última línea --\n");

                salida.setText(sb.toString());

          } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
          }
   });
  }
}
