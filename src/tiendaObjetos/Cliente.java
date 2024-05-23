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

    private int codigoCliente;
    private String nombreCliente;
    private String telefono;
    private String fax;
    private String lineaDireccion1;
    private String ciudad;
    private String pais;
    private Short CodigoClienteEmpleado;

    public Cliente(int codigoCliente, String nombreCliente, String telefono, String fax, String lineaDireccion1, String ciudad, String pais, Short CodigoClienteEmpleado) {
        this.setCodigoCliente(codigoCliente);
        this.setNombreCliente(nombreCliente);
        this.setTelefono(telefono);
        this.setFax(fax);
        this.setLineaDireccion1(lineaDireccion1);
        this.setCiudad(ciudad);
        this.setPais(pais);
        this.setCodigoClienteEmpleado(CodigoClienteEmpleado);
    }

    public Cliente(String nombreCliente, String telefono, String fax, String lineaDireccion1, String ciudad, String pais, Short CodigoClienteEmpleado) {
        this.setCodigoCliente(codigoCliente);
        this.setNombreCliente(nombreCliente);
        this.setTelefono(telefono);
        this.setFax(fax);
        this.setLineaDireccion1(lineaDireccion1);
        this.setCiudad(ciudad);
        this.setPais(pais);
        this.setCodigoClienteEmpleado(CodigoClienteEmpleado);
    }
    
    public Cliente(int CodigoCliente) {
    }

    public final int getCodigoCliente() {
        return codigoCliente;
    }

    public final void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public final String getNombreCliente() {
        return nombreCliente;
    }

    public final void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getLineaDireccion1() {
        return lineaDireccion1;
    }

    public void setLineaDireccion1(String lineaDireccion1) {
        this.lineaDireccion1 = lineaDireccion1;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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
