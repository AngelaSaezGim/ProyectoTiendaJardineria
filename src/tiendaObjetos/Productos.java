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
    private String gama;

    public Productos(String codigoProducto, String nombreProducto, String gama) {
        this.setCodigoProducto(codigoProducto);
        this.setNombreProducto(nombreProducto);
        this.setGama(gama);
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
    
     public String getGama() {
        return gama;
    }

    public void setGama(String gama) {
        this.gama = gama;
    }
    
     @Override
    public String toString() {
        return "-> Producto [" + codigoProducto + "] " + nombreProducto + " - " + gama;
    } 

}
