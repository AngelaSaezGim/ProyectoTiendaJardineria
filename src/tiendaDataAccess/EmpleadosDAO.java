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
import tiendaObjetos.Empleados;

/**
 *
 * @author angsaegim
 */
public class EmpleadosDAO extends DataAccessObject {

    EmpleadosDAO(Connection cnt) {
        super(cnt);
    }

    protected List<Empleados> loadAllEmpleados() throws SQLException {

        List<Empleados> empleados = new ArrayList<>();
        try ( PreparedStatement stmt = cnt.prepareStatement("SELECT * FROM Empleados");  ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                Empleados empleado = readEmpleadosFromResultSet(result);
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al cargar empleados: " + e.getMessage());
        }
        return empleados;
    }

    private static Empleados readEmpleadosFromResultSet(ResultSet rs) throws SQLException {
        Short codigoEmpleado = rs.getShort(EmpleadosTableColumns.COLUMN_NAME__EMPLEADO_CODIGO);
        String nombreEmpleado = rs.getString(EmpleadosTableColumns.COLUMN_EMPLEADO__NOMBRE);
        String puestoEmpleado = rs.getString(EmpleadosTableColumns.COLUMN_EMPLEADO__PUESTO);
        Empleados empleado = new Empleados(codigoEmpleado, nombreEmpleado, puestoEmpleado);
        return empleado;
    }

    private class EmpleadosTableColumns {

        private final static String COLUMN_NAME__EMPLEADO_CODIGO = "CodigoEmpleado";

        private final static String COLUMN_EMPLEADO__NOMBRE = "Nombre";

        private final static String COLUMN_EMPLEADO__PUESTO = "Puesto";

    }
}
