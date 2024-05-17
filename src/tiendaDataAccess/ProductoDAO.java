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
import tiendaObjetos.Producto;

/**
 *
 * @author angsaegim
 */
public class ProductoDAO extends DataAccessObject {
    
    ProductoDAO(Connection cnt){
        super(cnt);
    }
    
      protected List<Producto> loadAllProductos() throws SQLException {

        List<Producto> productos = new ArrayList<>();
        try ( PreparedStatement stmt = cnt.prepareStatement("SELECT * FROM Productos");  
                ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                Producto producto = readProductosFromResultSet(result);
                productos.add(producto);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al cargar productos: " + e.getMessage());
        }  
        return productos;
    }
     
     private static Producto readProductosFromResultSet(ResultSet rs) throws SQLException{
        String codigoProducto = rs.getString(ProductosTableColumns.COLUMN_NAME_PRODUCTO_CODIGO);
        String nombreProducto = rs.getString(ProductosTableColumns.COLUMN_PRODUCTO_NOMBRE);
        String gamaProducto = rs.getString(ProductosTableColumns.COLUMN_PRODUCTO_GAMA);
        Producto producto = new Producto(codigoProducto, nombreProducto, gamaProducto);
        return producto;
    }
     
      protected List<Producto> loadProductosContaining(String content) throws SQLException {

        List<Producto> productos = new ArrayList<>();

        PreparedStatement stmt = cnt.prepareStatement("SELECT * FROM productos WHERE Gama LIKE ?");
        stmt.setString(1, content);
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            productos.add(readProductosFromResultSet(result));
        }
        return productos;
    }
     
     private class ProductosTableColumns{
            
        private final static String COLUMN_NAME_PRODUCTO_CODIGO = "CodigoProducto";
        private final static String COLUMN_PRODUCTO_NOMBRE = "Nombre";
        private final static String COLUMN_PRODUCTO_GAMA = "Gama";
    }
    
}
