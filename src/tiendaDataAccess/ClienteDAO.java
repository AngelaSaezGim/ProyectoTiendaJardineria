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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tiendaObjetos.Cliente;

/**
 *
 * @author angsaegim
 */
public class ClienteDAO extends DataAccessObject{

    ClienteDAO(Connection cnt) {
        super(cnt);
    }

    private static Cliente readClientesFromResultSet(ResultSet rs) throws SQLException {
        int codigoCliente = rs.getInt(ClientesTableColumns.COLUMN_NAME_CLIENTE_CODIGO);
        String nombreCliente = rs.getString(ClientesTableColumns.COLUMN_CLIENTE_NOMBRE);
        String telefonoCliente = rs.getString(ClientesTableColumns.COLUMN_CLIENTE_TELEFONO);
        String faxCliente = rs.getString(ClientesTableColumns.COLUMN_CLIENTE_TELEFONO);
        String lineaDireccion1 = rs.getString(ClientesTableColumns.COLUMN_CLIENTE_LINEADIRECCION1);
        String ciudadCliente = rs.getString(ClientesTableColumns.COLUMN_CLIENTE_CIUDAD);
        String pais = rs.getString(ClientesTableColumns.COLUMN_CLIENTE_PAIS);
        Short CodigoEmpleadoCliente = rs.getShort(ClientesTableColumns.COLUMN_CLIENTE_EMPLEADO);
        Cliente cliente = new Cliente(codigoCliente, nombreCliente, telefonoCliente, faxCliente, lineaDireccion1, ciudadCliente, pais, CodigoEmpleadoCliente);
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

    protected Cliente loadClientesByCode(String codigoCliente) throws SQLException {

        List<Cliente> clientes = new ArrayList<>();

        PreparedStatement stmt = cnt.prepareStatement("SELECT * FROM clientes WHERE CodigoCliente = ?");
        stmt.setString(1, codigoCliente);
        ResultSet result = stmt.executeQuery();

        if (result.next()) {
            return readClientesFromResultSet(result);
        }
        return null;
    }

    protected int deleteClient(String codigoCliente) throws SQLException {

        int filasAfectadas = 0;

        try ( PreparedStatement stmt = cnt.prepareStatement("DELETE FROM Clientes WHERE CodigoCliente = ?")) {
            stmt.setString(1, codigoCliente);
            // NO USAR executeQuery
            // NO USAR resultSet
            // USAR executeUpdate();
            filasAfectadas = stmt.executeUpdate();
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado al borrar el cliente" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

        // DEVUELVE LAS FILAS AFECTADAS por la eliminación
        return filasAfectadas;
    }

    protected int insertClient(Cliente cliente) throws SQLException {
        
        int filasAfectadas = 0;
        
        String sentenciaSQL = "INSERT INTO clientes ("
                + ClientesTableColumns.COLUMN_NAME_CLIENTE_CODIGO + ", "
                + ClientesTableColumns.COLUMN_CLIENTE_NOMBRE + ", "
                + ClientesTableColumns.COLUMN_CLIENTE_TELEFONO + ", "
                + ClientesTableColumns.COLUMN_CLIENTE_FAX + ", "
                + ClientesTableColumns.COLUMN_CLIENTE_LINEADIRECCION1 + ", "
                + ClientesTableColumns.COLUMN_CLIENTE_CIUDAD + ", "
                + ClientesTableColumns.COLUMN_CLIENTE_PAIS + ", "
                + ClientesTableColumns.COLUMN_CLIENTE_EMPLEADO
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement stmt = cnt.prepareStatement(sentenciaSQL)) {

            int codCliente = obtenerMaxId() + 1;
            stmt.setInt(1, codCliente);
            stmt.setString(2, cliente.getNombreCliente());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getFax());
            stmt.setString(5, cliente.getLineaDireccion1());
            stmt.setString(6, cliente.getCiudad());
            stmt.setString(7, cliente.getPais());
            stmt.setShort(8, cliente.getCodigoClienteEmpleado());

            filasAfectadas = stmt.executeUpdate();

            cliente.setCodigoCliente(codCliente);
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado al insertar el cliente" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return filasAfectadas;
    }

    private Integer obtenerMaxId() throws SQLException {

        PreparedStatement stmt = cnt.prepareStatement("SELECT max(CodigoCliente) FROM clientes");
        ResultSet result = stmt.executeQuery();

        if (result.next()) {
            return result.getInt(1);
        } else {
            return 0; //no hay datos en la tabla
        }
    }

    //Recibir cliente - un solo prepareStatenent
    protected int updateClient(String codigoCliente, Cliente clienteActualizar) throws SQLException {

        int filasAfectadas = 0;

        String sql = "UPDATE Clientes SET NombreCliente = ?, Telefono = ?, Pais = ?, CodigoEmpleadoRepVentas = ? WHERE CodigoCliente = ?";

        try ( PreparedStatement stmt = cnt.prepareStatement(sql)) {
            stmt.setString(1, clienteActualizar.getNombreCliente());
            stmt.setString(2, clienteActualizar.getTelefono());
            stmt.setString(3, clienteActualizar.getPais());
            stmt.setShort(4, clienteActualizar.getCodigoClienteEmpleado());
            stmt.setString(5, codigoCliente);

            filasAfectadas = stmt.executeUpdate();
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado al actualizar el cliente" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        // DEVUELVE LAS FILAS AFECTADAS por la actualización
        return filasAfectadas;
    }

    private class ClientesTableColumns {

        private final static String COLUMN_NAME_CLIENTE_CODIGO = "CodigoCliente";
        private final static String COLUMN_CLIENTE_NOMBRE = "NombreCliente";
        private final static String COLUMN_CLIENTE_TELEFONO = "Telefono";
        private final static String COLUMN_CLIENTE_FAX = "Fax";
        private final static String COLUMN_CLIENTE_LINEADIRECCION1 = "LineaDireccion1";
        private final static String COLUMN_CLIENTE_CIUDAD = "Ciudad";
        private final static String COLUMN_CLIENTE_PAIS = "Pais";
        private final static String COLUMN_CLIENTE_EMPLEADO = "CodigoEmpleadoRepVentas";
    }

}
