/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendaDataAccess;
import java.sql.Connection;
import tiendaObjetos.Empleados;

/**
 *
 * @author angsaegim
 */
public class EmpleadosDAO extends DataAccessObject {
    
    EmpleadosDAO(Connection cnt){
        super(cnt);
    }
}
