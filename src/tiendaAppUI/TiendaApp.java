/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendaAppUI;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import tiendaDataAccess.DataAccessManager;
import tiendaObjetos.Cliente;
import tiendaObjetos.Empleado;
import tiendaObjetos.Producto;

/**
 *
 * @author angsaegim
 */
public class TiendaApp {

    private static Scanner tcl = new Scanner(System.in);

    //Opciones del menú principal
    private enum MenuOption {
        QUERY_ALL, QUERY_BY_CODE, QUERY_CLIENTES_INSERT, QUERY_CLIENTES_UPDATE, QUERY_CLIENTES_DELETE, EXIT
    };

    private enum MenuOption1 {
        QUERY_CLIENTES, QUERY_EMPLEADOS, QUERY_PRODUCTOS, ATRAS
    };

    private enum MenuOption2 {
        QUERY_CODE_CLIENTES, QUERY_CODE_EMPLEADOS, QUERY_CODE_PRODUCTOS, ATRAS
    };

    public static void main(String[] args) {

        MenuOption opcionElegida = null;
        MenuOption1 opcionElegidaAll = null;
        MenuOption2 opcionElegidaCode = null;
        Cliente nuevoCliente = null;
        Short opcionElegidaActualizacion = 0;

        //instrucción try-con-recurso (el recurso es el objeto DataAccessManager declarado en el paréntesis). 
        // Automáticamente, tras el try-con-recurso, la JDK invoca al método AutoCloseable.close()
        //implementado en DataAcessManager.
        try ( DataAccessManager dam = DataAccessManager.getInstance()) {

            do {
                printOptions();
                opcionElegida = readChoice();

                switch (opcionElegida) {
                    case QUERY_ALL:
                        do {
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
                        } while (opcionElegidaAll != MenuOption1.ATRAS);
                        break;
                    case QUERY_BY_CODE:
                        do {
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
                        } while (opcionElegidaCode != MenuOption2.ATRAS);
                        break;
                    case QUERY_CLIENTES_INSERT:
                        insertarCliente(dam);
                        break;
                    case QUERY_CLIENTES_UPDATE:
                        actualizarCliente(dam);
                        printOptionsUpdate();
                        break;
                    case QUERY_CLIENTES_DELETE:
                        borrarCliente(dam);
                        break;
                    case EXIT:
                }

            } while (opcionElegida != MenuOption.EXIT);

        } catch (SQLException sqe) {
            System.out.println("Error de acceso a datos: " + sqe.getMessage());
        }
        System.out.println("\n\n  ADIOS !!!! \n\n");
        tcl.close();
    }

    //***************************** FUNCIONES LANZADAS DESDE LA ELECCIÓN DEL MENÚ DE LA APLICACIÓN *****************************
    //VER TODOS LOS CLIENTES
    private static void verClientes(DataAccessManager dam) throws SQLException {
        List<Cliente> allClientes = dam.loadAllClientes();
        printClientes(allClientes);
    }

    //VER TODOS LOS EMPLEADOS
    private static void verEmpleados(DataAccessManager dam) throws SQLException {
        List<Empleado> allEmpleados = dam.loadAllEmpleados();
        printEmpleados(allEmpleados);
    }

    //VER TODOS LOS PRODUCTOS
    private static void verProductos(DataAccessManager dam) throws SQLException {
        List<Producto> allProductos = dam.loadAllProductos();
        printProductos(allProductos);
    }

    // PONGO UN CODIGO (NUMERO) Y ME DARA EL NOMBRE DEL CLIENTE CORREPONDIENTE A ESE CODIGO
    private static void searchClientesByCode(DataAccessManager dam) throws SQLException {
        String codigoCliente = requestClientContentLike();
        Cliente clientesFilteredByCode = dam.loadClientesByCode(codigoCliente);
        if (clientesFilteredByCode != null) {
            printClienteCompleto(clientesFilteredByCode);
        } else {
            System.out.println("No se encontraron clientes con el código especificado.");
        }
    }

    // PARA EL UPDATE
    private static String filtrarClientesByCode(DataAccessManager dam) throws SQLException {
        System.out.print(" [Cliente que se actualizará] - ");
        String codigoCliente = requestClientContentLike();
        Cliente clientesFilteredByCode = dam.loadClientesByCode(codigoCliente);
        if (clientesFilteredByCode != null) {
            printClienteCompleto(clientesFilteredByCode);
        } else {
            System.out.println("No se encontraron clientes con el código especificado.");
        }
        return codigoCliente;
    }

    //BUSCAR EMPLEADOS POR CODIGO
    private static void searchEmpleadosByCode(DataAccessManager dam) throws SQLException {
        String codigoEmpleado = requestEmpleadoContentLike();
        List<Empleado> empleadosFilteredByCode = dam.loadEmpleadosContaining(codigoEmpleado);
        if (!empleadosFilteredByCode.isEmpty()) {
            printEmpleadoCompleto(empleadosFilteredByCode);
        } else {
            System.out.println("No se encontraron empleados con el código especificado.");
        }
    }

    //BUSCAR PRODUCTOS POR GAMA
    private static void searchProductosByCode(DataAccessManager dam) throws SQLException {
        String codigoProducto = requestProductoContentLike();
        List<Producto> productosFilteredByCode = dam.loadProductosContaining(codigoProducto);
        if (!productosFilteredByCode.isEmpty()) {
            printProductos(productosFilteredByCode);
        } else {
            System.out.println("No se encontraron productos de la gama especificada.");
        }
    }

    private static void borrarCliente(DataAccessManager dam) throws SQLException {
        String codigoCliente = requestClientContentLike();
        int columnasAfectadas = dam.deleteClient(codigoCliente);
        if (columnasAfectadas > 0) {
            System.out.println("Cliente borrado exitosamente");
        } else {
            System.out.println("No se encontraron clientes con el código especificado para borrar.");
        }
    }

    private static void insertarCliente(DataAccessManager dam) throws SQLException {

        String nombreCliente = requestNombreCliente();
        String telefono = requestTelefonoCliente();
        String fax = requestFaxCliente();
        String lineaDireccion1 = requestLineaDireccion1();
        String ciudad = requestCiudad();
        String pais = requestPaisCliente();
        Short codigoEmpleado = requestCodigoEmpleadoCliente();

        Cliente nuevoCliente = new Cliente(nombreCliente, telefono, fax, lineaDireccion1, ciudad, pais, codigoEmpleado);
        dam.insertarCliente(nuevoCliente);
    }

    //ACTUALIZAR CLIENTE
    private static void actualizarCliente(DataAccessManager dam) throws SQLException {

        String codigoClienteAActualizarStr = filtrarClientesByCode(dam);

        // Carga el cliente actual desde la base de datos
        Cliente clienteActualizar = dam.loadClientesByCode(codigoClienteAActualizarStr);

        System.out.println("(Dejar vacío para no cambiar):");
        String nuevoNombre = requestNombreCliente();
        if (!nuevoNombre.isEmpty()) {
            clienteActualizar.setNombreCliente(nuevoNombre);
        }

        System.out.println("(Dejar vacío para no cambiar):");
        String nuevoTelefono = requestTelefonoCliente();
        if (!nuevoTelefono.isEmpty()) {
            clienteActualizar.setTelefono(nuevoTelefono);
        }

        System.out.println("Ingrese el nuevo país del cliente (dejar vacío para no cambiar):");
        String nuevoPais = tcl.nextLine();
        if (!nuevoPais.isEmpty()) {
            clienteActualizar.setPais(nuevoPais);
        }

        System.out.println("Ingrese el nuevo código del empleado representante de ventas (dejar vacío para no cambiar):");
        String nuevoCodigoEmpleadoRepVentasStr = tcl.nextLine();
        if (!nuevoCodigoEmpleadoRepVentasStr.isEmpty()) {
            short nuevoCodigoEmpleadoRepVentas = Short.parseShort(nuevoCodigoEmpleadoRepVentasStr);
            clienteActualizar.setCodigoEmpleadoRepVentas(nuevoCodigoEmpleadoRepVentas);
        }

        int columnasAfectadas = dam.updateClient(codigoClienteAActualizarStr, clienteActualizar);
        if (columnasAfectadas > 0) {
            System.out.println("Cliente actualizado exitosamente");
            Cliente clientesFilteredByCode = dam.loadClientesByCode(codigoClienteAActualizarStr);
            printClienteCompleto(clientesFilteredByCode);
        } else {
            System.out.println("No se actualizó nada");
        }
    }


    /*GESTOR DE CLIENTES*/
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
                .append("\t3) Insertar un nuevo cliente \n")
                .append("\t4) Actualizar cliente \n")
                .append("\t5) Borrar nuevos clientes por codigo\n")
                .append("\t6)Salir\n")
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

    private static void printOptionsUpdate() {
        StringBuilder sb = new StringBuilder()
                .append("\n\n\nElija que quieres ACTUALIZAR:\n")
                .append("\t1)Actualizar Nombre\n")
                .append("\t2)Actualizar Telefono\n")
                .append("\t3)Actualizar Pais \n")
                .append("\t4)Actualizar Codigo del empleado al que esta vinculado \n")
                .append("\t5)Atrás\n")
                .append("Opción: ");
        System.out.print(sb.toString());
    }

    private static void printClientes(List<Cliente> clientes) {
        if (clientes == null || clientes.isEmpty()) {
            System.out.println("No hay registros...");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println("\t" + cliente);
        }
        System.out.println();
    }

    private static void printClienteCompleto(Cliente clientes) {
        if (clientes == null) {
            System.out.println("No hay registros...");
            return;
        }
        System.out.println("\t" + clientes);
        System.out.println("\t Telefono de contacto = " + clientes.getTelefono());
        System.out.println("\t Pais = " + clientes.getPais());
        System.out.println("\t Codigo de empleado al que está relacionado = " + clientes.getCodigoClienteEmpleado());
    }

    private static void printEmpleados(List<Empleado> empleados) {
        if (empleados == null || empleados.isEmpty()) {
            System.out.println("No hay registros...");
            return;
        }

        for (Empleado empleado : empleados) {
            System.out.println("\t" + empleado);
        }
        System.out.println();
    }

    private static void printEmpleadoCompleto(List<Empleado> empleados) {
        if (empleados == null || empleados.isEmpty()) {
            System.out.println("No hay registros...");
            return;
        }

        for (Empleado empleado : empleados) {
            System.out.println("\t" + empleado);
            System.out.println("\t Nombre Completo = " + empleado.getNombreEmpleado() + " " + empleado.getApellido1() + " " + empleado.getApellido2());
            System.out.println("\t Email de contacto = " + empleado.getEmail());
            System.out.println("\t Codigo Oficina = " + empleado.getCodigoOficina());
        }
    }

    private static void printProductos(List<Producto> productos) {
        if (productos == null || productos.isEmpty()) {
            System.out.println("No hay registros...");
            return;
        }

        for (Producto producto : productos) {
            System.out.println("\t" + producto);
        }
        System.out.println();
    }

    private static String requestClientContentLike() {
        System.out.print("Escriba el codigo del cliente; ");
        return readNotEmptyString();
    }

    private static String requestEmpleadoContentLike() {
        System.out.print("Escriba el codigo del empleado; ");
        return readNotEmptyString();

    }

    private static String requestProductoContentLike() {
        System.out.print("Escriba el codigo del producto ");
        return readNotEmptyString();

    }

    /*
        String nombreCliente = requestNombreCliente();
        String telefono = requestTelefonoCliente();
        String pais = requestPaisCliente();
        Short codigoEmpleado = requestCodigoEmpleadoCliente();
    
     */
    private static String requestNombreCliente() {
        System.out.print("Escriba el nombre del Cliente; ");
        return readNotEmptyString();

    }

    private static String requestTelefonoCliente() {
        System.out.print("Escriba el telefono del cliente; ");
        return readNotEmptyString();

    }

    private static String requestFaxCliente() {
        System.out.print("Escriba el fax del cliente; ");
        return readNotEmptyString();

    }

    private static String requestLineaDireccion1() {
        System.out.print("Dime la direccion del cliente; ");
        return readNotEmptyString();

    }

    private static String requestCiudad() {
        System.out.print("Dime la ciudad del cliente; ");
        return readNotEmptyString();
    }

    private static String requestPaisCliente() {
        System.out.print("Escriba el pais del cliente; ");
        return readNotEmptyString();

    }

    private static Short requestCodigoEmpleadoCliente() {
        System.out.print("Escriba el codigo del empleado vinculado al cliente; ");
        return readShort();

    }
}
