package Remontando.util;

import Remontando.model.*;
import java.util.ArrayList;

public class ServicioInstrumentos {

  private Instrumento crear(String tipo, double monto, int dias, String moneda, String clienteId) {

      switch (tipo.toLowerCase()) {
            case "corriente":     return new Corriente(monto, dias, moneda, clienteId);
            case "pactada":       return new Pactada(monto, dias, moneda, clienteId);
            case "certificado":   return new Certificado(monto, dias, moneda, clienteId);
            default:
                throw new IllegalArgumentException("Tipo inválido");
      }
  }

  public Instrumento registrar(Cliente cli, String tipo, double monto, int dias, String moneda) {

      Validador.texto(tipo, "tipo");
      Validador.monto(monto);
      Validador.dias(dias);
      moneda = Validador.moneda(moneda);

      Instrumento ins = crear(tipo, monto, dias, moneda, cli.getId());

      Memoria.INSTRUMENTOS.add(ins);
      cli.agregarInstrumento(ins);

      return ins;
  }

  public ArrayList<Instrumento> listarPorCliente(Cliente cli) {
      ArrayList<Instrumento> lista = new ArrayList<>(cli.getInstrumentos());
      Ordenamiento.ordenarInstrumentos(lista);
      return lista;
  }

  public ArrayList<Instrumento> listarGlobal() {
       ArrayList<Instrumento> lista = new ArrayList<>(Memoria.INSTRUMENTOS);
       Ordenamiento.ordenarInstrumentos(lista);
      return lista;
  }
}
