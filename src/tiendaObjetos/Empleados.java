/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendaObjetos;

/**
 *
 * @author angsaegim
 */
public class Empleados {

    private Short codigoEmpleado;
    private String nombreEmpleado;
    private String puestoEmpleado;

    public Empleados(Short codigoEmpleado, String nombreEmpleado, String puestoEmpleado) {
        this.setCodigoEmpleado(codigoEmpleado);
        this.setNombreEmpleado(nombreEmpleado);
        this.setPuestoEmpleado(puestoEmpleado);
    }

    public Short getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(Short codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getPuestoEmpleado() {
        return puestoEmpleado;
    }

    public void setPuestoEmpleado(String puestoEmpleado) {
        this.puestoEmpleado = puestoEmpleado;
    }

    @Override
    public String toString() {
        return "-> Empleado [" + codigoEmpleado + "] " + nombreEmpleado + "  - " + puestoEmpleado;
    }
}
