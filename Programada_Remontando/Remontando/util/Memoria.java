package Remontando.util;

import java.util.ArrayList;

import Remontando.model.Cliente;
import Remontando.model.Instrumento;

/**
 * Clase utilitaria que almacena en memoria principal las listas globales
 *Las listas se declaran como públicas para que los servicios puedan
 * manipularlas directamente, siguiendo el enfoque simple y arcaico solicitado
 * en este proyecto académico.</p>
 */
public class Memoria {

    /** Lista global de clientes registrados en el sistema. */
    public static ArrayList<Cliente> CLIENTES = new ArrayList<>();

    /** Lista global de instrumentos registrados en el sistema. */
    public static ArrayList<Instrumento> INSTRUMENTOS = new ArrayList<>();
}