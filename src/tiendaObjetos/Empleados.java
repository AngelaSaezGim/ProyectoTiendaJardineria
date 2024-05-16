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
    private String apellido1;
    private String apellido2;
    private String email;
    private String codigoOficina;

    public Empleados(Short codigoEmpleado, String nombreEmpleado, String puestoEmpleado, String apellido1,
            String apellido2, String email, String codigoOficina) {
        this.setCodigoEmpleado(codigoEmpleado);
        this.setNombreEmpleado(nombreEmpleado);
        this.setPuestoEmpleado(puestoEmpleado);
        this.setApellido1(apellido1);
        this.setApellido2(apellido2);
        this.setEmail(email);
        this.setCodigoOficina(codigoOficina);
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
    
    
    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigoOficina() {
        return codigoOficina;
    }

    public void setCodigoOficina(String codigoOficina) {
        this.codigoOficina = codigoOficina;
    }

    @Override
    public String toString() {
        return "-> Empleado [" + codigoEmpleado + "] " + nombreEmpleado + "  - " + puestoEmpleado;
    }

}
