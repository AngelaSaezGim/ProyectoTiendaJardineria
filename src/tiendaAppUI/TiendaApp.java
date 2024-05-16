/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendaAppUI;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import tiendaDataAccess.DataAccessManager;
import tiendaObjetos.Clientes;
import tiendaObjetos.Empleados;
import tiendaObjetos.Productos;

/**
 *
 * @author angsaegim
 */
public class TiendaApp {

    private static Scanner tcl = new Scanner(System.in);

    //Opciones del menú principal
    private enum MenuOption {
        QUERY_CLIENTES, QUERY_EMPLEADOS,QUERY_PRODUCTOS,EXIT
    };

    public static void main(String[] args) {

        MenuOption opcionElegida = null;

        //instrucción try-con-recurso (el recurso es el objeto DataAccessManager declarado en el paréntesis). 
        // Automáticamente, tras el try-con-recurso, la JDK invoca al método AutoCloseable.close()
        //implementado en DataAcessManager.
        try ( DataAccessManager dam = DataAccessManager.getInstance()) {

            do {
                printOptions();
                opcionElegida = readChoice();

                switch (opcionElegida) {
                    case QUERY_CLIENTES:
                        verClientes(dam);
                        break;
                    case QUERY_EMPLEADOS:
                        verEmpleados(dam);
                        break;
                    case QUERY_PRODUCTOS:
                        verProductos(dam);
                        break;
                    case EXIT:
                }

            } while (opcionElegida != MenuOption.EXIT);

        } catch (SQLException sqe) {
            System.out.println("Error de acceso a datos: " + sqe.getMessage());
        }
        System.out.println("\n\n  ADIOS ! \n\n");
        tcl.close();
    }

    //***************************** FUNCIONES LANZADAS DESDE LA ELECCIÓN DEL MENÚ DE LA APLICACIÓN *****************************
   //VER TODOS LOS CLIENTES
    private static void verClientes(DataAccessManager dam) throws SQLException {
        List<Clientes> allClientes = dam.loadAllClientes();
        printClientes(allClientes);
    }
    
    //VER TODOS LOS EMPLEADOS
    private static void verEmpleados(DataAccessManager dam) throws SQLException {
        List<Empleados> allEmpleados = dam.loadAllEmpleados();
        printEmpleados(allEmpleados);
    }

    //VER TODOS LOS PRODUCTOS
    private static void verProductos(DataAccessManager dam) throws SQLException {
        List<Productos> allProductos = dam.loadAllProductos();
        printProductos(allProductos);
    }

    // PONGO UN CODIGO (NUMERO) Y ME DARA EL NOMBRE DEL CLIENTE CORREPONDIENTE A ESE CODIGO
     private static void verClientesPorCodigo(DataAccessManager dam) throws SQLException {
         System.out.println("--------------------En desarrollo---------------------");
    }
     
     //AGRUPAR CLIENTES POR PAISES - que salgan los paises de cada cliente (hashMap)
      private static void paisesClientes(DataAccessManager dam) throws SQLException {
         System.out.println("--------------------En desarrollo---------------------");
    }
      
     //CodigoEmpleadoRepVentas - CodigoEmpleados (tabla empleados)
     //Doy un codigo de empleado - me dará el nombre del empleado enlazado a ese cliente
      private static void empleadoCliente(DataAccessManager dam) throws SQLException {
         System.out.println("--------------------En desarrollo---------------------");
    }
    
    //AGRUPAR PRODUCTOS POR GAMA
    private static void verProductosPorGama(DataAccessManager dam) throws SQLException {
         System.out.println("--------------------En desarrollo---------------------");
    }

    //**************** MÉTODOS DE LECTURA DE DATOS VÁLIDOS POR TECLADO ********************
    private static String readNotEmptyString() {
        String input = null;
        //prevenir texto vacío
        while (input == null || input.length() == 0) {
            input = tcl.nextLine();
            if (input.length() == 0) {
                System.out.println("escriba algo...");
            }
        }
        return input;
    }

    private static short readShort() {
        Short response = null;
        do {
            try {
                response = Short.valueOf(tcl.nextLine());
            } catch (RuntimeException re) {
                System.out.println("Valor inválido... Inténtelo otra vez.");
            }
        } while (response == null);

        return response;
    }

    private static MenuOption readChoice() {
        try {
            int choiceInt = Integer.valueOf(tcl.nextLine());
            return MenuOption.values()[choiceInt - 1];
        } catch (RuntimeException re) {
            System.out.println("Opción inválida... Inténtelo otra vez.");
            return readChoice();
        }
    }

    //**************** MÉTODOS PRINTEAR ********************
    
    private static void printOptions() {
        StringBuilder sb = new StringBuilder()
                .append("\n\n\nElija una opción:\n")
                .append("\t1)Consultar todos los clientes\n")
                .append("\t2)Consultar todos los Empleados\n")
                .append("\t3)Consultar todos los productos\n")
                .append("\t5)Salir\n")
                .append("Opción: ");
        System.out.print(sb.toString());
    }

    private static void printClientes(List<Clientes> clientes) {
        if (clientes == null || clientes.isEmpty()) {
            System.out.println("No hay registros...");
            return;
        }

        for (Clientes cliente : clientes) {
            System.out.println("\t" + cliente);
        }
        System.out.println();
    }
    
     private static void printEmpleados(List<Empleados> empleados) {
        if (empleados == null || empleados.isEmpty()) {
            System.out.println("No hay registros...");
            return;
        }

        for (Empleados empleado : empleados) {
            System.out.println("\t" + empleado);
        }
        System.out.println();
    }
     
     private static void printProductos(List<Productos> productos) {
        if (productos == null || productos.isEmpty()) {
            System.out.println("No hay registros...");
            return;
        }

        for (Productos producto : productos) {
            System.out.println("\t" + producto);
        }
        System.out.println();
    }
}
