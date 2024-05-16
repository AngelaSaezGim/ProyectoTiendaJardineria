/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendaObjetos;

import tiendaDataAccess.DataAccessObject;

/**
 *
 * @author angsaegim
 */
public class Productos{
    
    private String codigoProducto;
    private String nombreProducto;

    public Productos(String codigoProducto, String nombreProducto) {
        this.setCodigoProducto(codigoProducto);
        this.setNombreProducto(nombreProducto);
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
     @Override
    public String toString() {
        return "-> Producto [" + codigoProducto + "] " + nombreProducto ;
    } 
}
