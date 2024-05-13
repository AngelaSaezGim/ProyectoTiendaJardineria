/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendaObjetos;

/**
 *
 * @author angsaegim
 */
public class Clientes {

    private Short codigoCliente;
    private String nombreCliente;

    public Clientes(Short codigoCliente, String nombreCliente) {
        this.setCodigoCliente(codigoCliente);
        this.setNombreCliente(nombreCliente);
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

    @Override
    public String toString() {
        return "-> Cliente ["+ codigoCliente + "] " + nombreCliente;
    }

}
