/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendaDataAccess;

import java.sql.Connection;

/**
 *
 * @author angsaegim
 */
public class DataAccessObject {
    
     
    protected Connection cnt;
    
    DataAccessObject(Connection cnt){
        if(cnt==null)
            throw new IllegalArgumentException("Conexión obligatoria");
        this.cnt = cnt;
    }
    
}
