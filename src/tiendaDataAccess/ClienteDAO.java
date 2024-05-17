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
import tiendaObjetos.Cliente;

/**
 *
 * @author angsaegim
 */
public class ClienteDAO extends DataAccessObject {

    ClienteDAO(Connection cnt) {
        super(cnt);
    }

    private static Cliente readClientesFromResultSet(ResultSet rs) throws SQLException {
        Short codigoCliente = rs.getShort(ClientesTableColumns.COLUMN_NAME_CLIENTE_CODIGO);
        String nombreCliente = rs.getString(ClientesTableColumns.COLUMN_CLIENTE_NOMBRE);
        String telefonoCliente = rs.getString(ClientesTableColumns.COLUMN_CLIENTE_TELEFONO);
        String pais = rs.getString(ClientesTableColumns.COLUMN_CLIENTE_PAIS);
        Short CodigoEmpleadoCliente = rs.getShort(ClientesTableColumns.COLUMN_CLIENTE_EMPLEADO);
        Cliente cliente = new Cliente(codigoCliente, nombreCliente, telefonoCliente,pais,CodigoEmpleadoCliente);
        return cliente;
    }


    protected List<Cliente> loadAllClientes() throws SQLException {

        List<Cliente> clientes = new ArrayList<>();
        try ( PreparedStatement stmt = cnt.prepareStatement("SELECT * FROM Clientes");  ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                Cliente cliente = readClientesFromResultSet(result);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al cargar clientes: " + e.getMessage());
        }

        return clientes;
    }

    protected List<Cliente> loadClientesContaining(String content) throws SQLException {

        List<Cliente> clientes = new ArrayList<>();

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
