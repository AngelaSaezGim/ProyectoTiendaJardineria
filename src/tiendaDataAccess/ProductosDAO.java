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
import tiendaObjetos.Productos;

/**
 *
 * @author angsaegim
 */
public class ProductosDAO extends DataAccessObject {
    
    ProductosDAO(Connection cnt){
        super(cnt);
    }
    
      protected List<Productos> loadAllProductos() throws SQLException {

        List<Productos> productos = new ArrayList<>();
        try ( PreparedStatement stmt = cnt.prepareStatement("SELECT * FROM Productos");  
                ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                Productos producto = readProductosFromResultSet(result);
                productos.add(producto);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al cargar productos: " + e.getMessage());
        }  
        return productos;
    }
     
     private static Productos readProductosFromResultSet(ResultSet rs) throws SQLException{
        String codigoProducto = rs.getString(ProductosTableColumns.COLUMN_NAME_PRODUCTO_CODIGO);
        String nombreProducto = rs.getString(ProductosTableColumns.COLUMN_PRODUCTO_NOMBRE);
        Productos producto = new Productos(codigoProducto, nombreProducto);
        return producto;
    }
     
     private class ProductosTableColumns{
            
        private final static String COLUMN_NAME_PRODUCTO_CODIGO = "CodigoProducto";

        private final static String COLUMN_PRODUCTO_NOMBRE = "Nombre";
    }
    
}
