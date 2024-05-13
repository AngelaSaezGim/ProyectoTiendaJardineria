/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendaDataAccess;
import java.sql.DriverManager;
import java.sql.SQLException;

import static tiendaDataAccessConstants.Constants.*;

/**
 *
 * @author angsaegim
 */
public class DataAccessManager {
    
     /************************** PARTE ESTÁTICA *****************************/
    private static String dataBaseUser = DEFAULT_DATA_BASE__USER;
    private static String dataBasePwd = DEFAULT_DATA_BASE__PWD;
    private static String dataBaseURL = DEFAULT_DATA_BASE__URL;
    
    private static DataAccessManager singleton;
    
     // Instanciamos un único objeto DataAccessManager - SINGLETON
    
    private DataAccessManager(){
        
    }
    
}
