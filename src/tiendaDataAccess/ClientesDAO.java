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

    ClientesDAO(Connection cnt) {
        super(cnt);
    }

    private static Clientes readClientesFromResultSet(ResultSet rs) throws SQLException {
        Short codigoCliente = rs.getShort(ClientesTableColumns.COLUMN_NAME_CLIENTE_CODIGO);
        String nombreCliente = rs.getString(ClientesTableColumns.COLUMN_CLIENTE_NOMBRE);
        String telefonoCliente = rs.getString(ClientesTableColumns.COLUMN_CLIENTE_TELEFONO);
        String pais = rs.getString(ClientesTableColumns.COLUMN_CLIENTE_PAIS);
        Short CodigoEmpleadoCliente = rs.getShort(ClientesTableColumns.COLUMN_CLIENTE_EMPLEADO);
        Clientes cliente = new Clientes(codigoCliente, nombreCliente, telefonoCliente,pais,CodigoEmpleadoCliente);
        return cliente;
    }


    protected List<Clientes> loadAllClientes() throws SQLException {

        List<Clientes> clientes = new ArrayList<>();
        try ( PreparedStatement stmt = cnt.prepareStatement("SELECT * FROM Clientes");  ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                Clientes cliente = readClientesFromResultSet(result);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al cargar clientes: " + e.getMessage());
        }

        return clientes;
    }

    protected List<Clientes> loadClientesContaining(String content) throws SQLException {

        List<Clientes> clientes = new ArrayList<>();

        PreparedStatement stmt = cnt.prepareStatement("SELECT * FROM clientes WHERE CodigoCliente LIKE ?");
        stmt.setString(1, content);
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            clientes.add(readClientesFromResultSet(result));
        }
        return clientes;
    }

    private class ClientesTableColumns {

        private final static String COLUMN_NAME_CLIENTE_CODIGO = "CodigoCliente";
        private final static String COLUMN_CLIENTE_NOMBRE = "NombreCliente";
        private final static String COLUMN_CLIENTE_TELEFONO = "Telefono";
        private final static String COLUMN_CLIENTE_PAIS = "Pais";
        private final static String COLUMN_CLIENTE_EMPLEADO = "CodigoEmpleadoRepVentas";
    }

}
