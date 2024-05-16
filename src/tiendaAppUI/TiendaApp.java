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
        QUERY_ALL,QUERY_BY_CODE,EXIT
    };
    
    private enum MenuOption1 {
        QUERY_CLIENTES, QUERY_EMPLEADOS,QUERY_PRODUCTOS,ATRAS
    };
    
    private enum MenuOption2 {
        QUERY_CODE_CLIENTES, QUERY_CODE_EMPLEADOS,QUERY_CODE_PRODUCTOS,ATRAS
    };

    public static void main(String[] args) {

        MenuOption opcionElegida = null;
        MenuOption1 opcionElegidaAll=null;
        MenuOption2 opcionElegidaCode = null;

        //instrucción try-con-recurso (el recurso es el objeto DataAccessManager declarado en el paréntesis). 
        // Automáticamente, tras el try-con-recurso, la JDK invoca al método AutoCloseable.close()
        //implementado en DataAcessManager.
        try ( DataAccessManager dam = DataAccessManager.getInstance()) {

            do {
                printOptions();
                opcionElegida = readChoice();

                switch (opcionElegida) {
                    case QUERY_ALL:
                        do{
                        printOptionsAll();
                        opcionElegidaAll = readChoice1();
                            switch (opcionElegidaAll) {
                                case QUERY_CLIENTES:
                                verClientes(dam);
                                break;
                                case QUERY_EMPLEADOS:
                                verEmpleados(dam);
                                break;
                                case QUERY_PRODUCTOS:
                                verProductos(dam);
                                break;
                                case ATRAS:
                            }
                        }while(opcionElegidaAll != MenuOption1.ATRAS);
                        break;
                    case QUERY_BY_CODE:
                        do{
                        printOptionsCode();
                        opcionElegidaCode = readChoice2();
                            switch (opcionElegidaCode) {
                                case QUERY_CODE_CLIENTES:
                                searchClientesByCode(dam);
                                break;
                                case QUERY_CODE_EMPLEADOS:
                                searchEmpleadosByCode(dam);
                                break;
                                case QUERY_CODE_PRODUCTOS:
                                searchProductosByCode(dam);
                                break;
                                case ATRAS:
                            }
                        }while(opcionElegidaCode != MenuOption2.ATRAS);
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
    private static void searchClientesByCode(DataAccessManager dam) throws SQLException {
        String content = requestClientContentLike();
        List<Clientes> clientesFilteredByCode = dam.loadClientesContaining(content);
        if (!clientesFilteredByCode.isEmpty()) {
            printClienteCompleto(clientesFilteredByCode);
        } else {
            System.out.println("No se encontraron clientes con el código especificado.");
        }
    }

    //BUSCAR EMPLEADOS POR CODIGO
    private static void searchEmpleadosByCode(DataAccessManager dam) throws SQLException {
        String content = requestEmpleadoContentLike();
        List<Empleados> empleadosFilteredByCode = dam.loadEmpleadosContaining(content);
        if (!empleadosFilteredByCode.isEmpty()) {
            printEmpleadoCompleto(empleadosFilteredByCode);
        } else {
            System.out.println("No se encontraron empleados con el código especificado.");
        }
    }

    //BUSCAR PRODUCTOS POR GAMA
    private static void searchProductosByCode(DataAccessManager dam) throws SQLException {
         String content = requestProductoContentLike();
        List<Productos> productosFilteredByCode = dam.loadProductosContaining(content);
        if (!productosFilteredByCode.isEmpty()) {
            printProductos(productosFilteredByCode);
        } else {
            System.out.println("No se encontraron productos de la gama especificada.");
        }
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
    
        
    private static MenuOption1 readChoice1() {
        try {
            int choiceInt = Integer.valueOf(tcl.nextLine());
            return MenuOption1.values()[choiceInt - 1];
        } catch (RuntimeException re) {
            System.out.println("Opción inválida... Inténtelo otra vez.");
            return readChoice1();
        }
    }
    
    private static MenuOption2 readChoice2() {
        try {
            int choiceInt = Integer.valueOf(tcl.nextLine());
            return MenuOption2.values()[choiceInt - 1];
        } catch (RuntimeException re) {
            System.out.println("Opción inválida... Inténtelo otra vez.");
            return readChoice2();
        }
    }

    //**************** MÉTODOS PRINTEAR ********************
    
    private static void printOptions() {
        StringBuilder sb = new StringBuilder()
                .append("\n\n\nElija una opción:\n")
                .append("\t1)Consultar todos... (clientes, empleados o productos)\n")
                .append("\t2)Consultar por codigos o gama (clientes, empleados o productos)\n")
                .append("\t3)Salir\n")
                .append("\t-) Gestor de clientes (INSERT,UPDATE,DELETE) \n")
                .append("Opción: ");
        System.out.print(sb.toString());
    }
    
    private static void printOptionsAll() {
        StringBuilder sb = new StringBuilder()
                .append("\n\n\nElija que quieres consultar:\n")
                .append("\t1)Consultar TODOS los clientes\n")
                .append("\t2)Consultar TODOS los Empleados\n")
                .append("\t3)Consultar TODOS los productos\n")
                .append("\t4)Atrás\n")
                .append("Opción: ");
        System.out.print(sb.toString());
    }
    
    private static void printOptionsCode() {
        StringBuilder sb = new StringBuilder()
                .append("\n\n\nElija que quieres consultar:\n")
                .append("\t1)Consultar clientes por codigo\n")
                .append("\t2)Consultar empleados por codigo\n")
                .append("\t3)Consultar productos por GAMA\n")
                .append("\t4)Atrás\n")
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

    private static void printClienteCompleto(List<Clientes> clientes) {
        if (clientes == null || clientes.isEmpty()) {
            System.out.println("No hay registros...");
            return;
        }
        for (Clientes cliente : clientes) {
            System.out.println("\t" + cliente);
            System.out.println("\t Telefono de contacto = " + cliente.getTelefono());
            System.out.println("\t Pais = " + cliente.getPais());
            System.out.println("\t Codigo de empleado al que está relacionado = " + cliente.getCodigoClienteEmpleado());
        }
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
    
    private static void printEmpleadoCompleto(List<Empleados> empleados) {
        if (empleados == null || empleados.isEmpty()) {
            System.out.println("No hay registros...");
            return;
        }

        for (Empleados empleado : empleados) {
            System.out.println("\t" + empleado);
            System.out.println("\t Nombre Completo = " + empleado.getNombreEmpleado() + " " + empleado.getApellido1() + " " + empleado.getApellido2());
            System.out.println("\t Email de contacto = " + empleado.getEmail());
            System.out.println("\t Codigo Oficina = " + empleado.getCodigoOficina());
        }
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
    

    private static String requestClientContentLike() {
        System.out.print("Escriba el codigo del cliente a consultar; ");
        return readNotEmptyString();

    }
    
    private static String requestEmpleadoContentLike() {
        System.out.print("Escriba el codigo del empleado a consultar; ");
        return readNotEmptyString();

    }
    
    private static String requestProductoContentLike() {
        System.out.print("Escriba el codigo del producto a consultar; ");
        return readNotEmptyString();

    }
}
