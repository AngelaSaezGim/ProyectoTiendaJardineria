/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendaDataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tiendaObjetos.Clientes;

/**
 *
 * @author angsaegim
 */
public class ClientesDAO extends DataAccessObject {
    
     ClientesDAO(Connection cnt){
        super(cnt);
    }
     
     protected List<Clientes> loadAllClientes() throws SQLException {
        
        List<Clientes> clientes = new ArrayList<>();
        PreparedStatement stmt = cnt.prepareStatement("SELECT * FROM Clientes");
        ResultSet result = stmt.executeQuery();

        while(result.next()){
            clientes.add(readClientesFromResultSet(result));
        }
        
        return clientes;
    }
     
     /**
     * Lee de un <code>ResultSet</code> un registro de la base de datos. El result set debe contener las columnas de la tabla
     * <code>clientes</code>
     * @param rs ResultSet SQL
     * @return Un cleinte con los datos del registro actual del result set
     * @throws SQLException Si ocurre algún error SQL
     */
    private static Clientes readClientesFromResultSet(ResultSet rs) throws SQLException{
        Short codigoCliente = rs.getShort(ClientesTableColumns.COLUMN_NAME__CLIENTE_CODIGO);
        String nombreCliente = rs.getString(ClientesTableColumns.COLUMN_CLIENTE__NOMBRE);
        Clientes cliente = new Clientes(codigoCliente, nombreCliente);
        return cliente;
    }
    
     private class ClientesTableColumns{
            
        /**
         * Nombre de la columna con el identificador del registro
         */
        private final static String COLUMN_NAME__CLIENTE_CODIGO = "CodigoCliente";

        /**
         * Nombre de la columna que contiene el nombre de la ciudad
         */
        private final static String COLUMN_CLIENTE__NOMBRE = "NombreCliente";

    }
    
}
