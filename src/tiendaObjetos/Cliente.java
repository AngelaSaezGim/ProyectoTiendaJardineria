/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendaObjetos;

/**
 *
 * @author angsaegim
 */
public class Cliente {

    private Short codigoCliente;
    private String nombreCliente;
    private String telefono;
    private String pais;
    private Short CodigoClienteEmpleado;

    public Cliente(Short codigoCliente, String nombreCliente, String telefono, String pais, Short CodigoClienteEmpleado) {
        this.setCodigoCliente(codigoCliente);
        this.setNombreCliente(nombreCliente);
        this.setTelefono(telefono);
        this.setPais(pais);
        this.setCodigoClienteEmpleado(CodigoClienteEmpleado);
    }

    public final Short getCodigoCliente() {
        return codigoCliente;
    }

    public final void setCodigoCliente(Short codigoCliente) {
        if (codigoCliente == null) {
            throw new IllegalArgumentException("No se admite valor nulo");
        }
        this.codigoCliente = codigoCliente;
    }

    public final String getNombreCliente() {
        return nombreCliente;
    }

    public final void setNombreCliente(String nombreCliente) {
        if (nombreCliente == null) {
            throw new IllegalArgumentException("No se admite valor nulo");
        }
        this.nombreCliente = nombreCliente;
    }
    
        public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Short getCodigoClienteEmpleado() {
        return CodigoClienteEmpleado;
    }

    public void setCodigoClienteEmpleado(Short CodigoClienteEmpleado) {
        this.CodigoClienteEmpleado = CodigoClienteEmpleado;
    }
    
    @Override
    public String toString() {
        return "-> Cliente [" + codigoCliente + "] " + nombreCliente;
    }
    
}
