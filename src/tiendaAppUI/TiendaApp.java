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
    private enum MenuOption{
      QUERY_CLIENTES, QUERY_EMPLEADOS, QUERY_PRODUCTOS_BY_GAMA, EXIT
    };
    
    public static void main(String[] args) {
        
         MenuOption opcionElegida = null;
         
         //instrucción try-con-recurso (el recurso es el objeto DataAccessManager declarado en el paréntesis). 
        // Automáticamente, tras el try-con-recurso, la JDK invoca al método AutoCloseable.close()
        //implementado en DataAcessManager.
        try(DataAccessManager dam = DataAccessManager.getInstance()){
            
            do{
                printOptions();
                opcionElegida = readChoice();

                switch(opcionElegida){
                    case QUERY_CLIENTES:
                        verClientes(dam);
                        break;
                    case QUERY_EMPLEADOS:
                        verEmpleados(dam);
                        break;
                    case QUERY_PRODUCTOS_BY_GAMA:
                        verProductosPorGama(dam);
                        break;
                    case EXIT:                    
                        //nada que hacer
                }

            }while(opcionElegida!=MenuOption.EXIT);
            
        }
        catch(SQLException sqe){
            System.out.println("Error de acceso a datos: " + sqe.getMessage());
        }  
        System.out.println("\n\n  ADIOS ! \n\n");
        tcl.close();  
    }
    
    //***************************** FUNCIONES LANZADAS DESDE LA ELECCIÓN DEL MENÚ DE LA APLICACIÓN *****************************
    
    private static void verClientes(DataAccessManager dam) throws SQLException {
        List<Clientes> allClientes = dam.loadAllClientes();
            printClientes(allClientes);
    }
    
    private static void verEmpleados(DataAccessManager dam) throws SQLException{
    }
    
    private static void verProductosPorGama(DataAccessManager dam) throws SQLException{
    }
    
    //**************** MÉTODOS DE LECTURA DE DATOS VÁLIDOS POR TECLADO ********************
    
     private static MenuOption readChoice() {
        try{
            int choiceInt = Integer.valueOf(tcl.nextLine());
            return MenuOption.values()[choiceInt-1];
        }
        catch(RuntimeException re){
            System.out.println("Opción inválida... Inténtelo otra vez.");
            return readChoice();
        }
    }
    
    //**************** MÉTODOS PRINTEAR ********************
    
     private static void printOptions() {
        StringBuilder sb = new StringBuilder()
                .append("\n\n\nElija una opción:\n")
                .append("\t1)Consultar todos los clientes\n")
                .append("\t1)Consultar todos los empleados\n")
                .append("\t1)Consultar productos por gama\n")
                .append("\t5)Salir\n")
                .append("Opción: ");
        System.out.print(sb.toString());
    }
     
      private static void printClientes(List<Clientes> clientes) {
        if(clientes==null || clientes.isEmpty()){
            System.out.println("No hay registros...");
            return;
        }
        
        for(Clientes cliente:clientes){
            System.out.println("\t"+cliente);
        }
        System.out.println();
    }
}
