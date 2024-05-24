/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendaAppUI;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import tiendaDataAccess.DataAccessManager;
import tiendaUI.TiendaManagementWindow;

/**
 *
 * @author angsaegim
 */
/*
**
     * Ejecuta la aplicación de acceso a datos desde con entrada y salida estándar. 
     * <p><b>
     * Ampliar cada alumno según necesidades
     * </b></p>
     * @param args the command line arguments
 */
public class TiendaUIApp {

    public static void main(String[] args) {

        TiendaManagementWindow mainWindow = null;

        try {

            mainWindow = new TiendaManagementWindow();
        } catch (SQLException sqle) {
            System.out.println("Error al iniciar la aplicación. " + sqle.getMessage() + ". Saliendo...");
            return;
        }

        mainWindow.addWindowListener(new WindowListener() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                
                if (DataAccessManager.connectionEnabled()) {
                    DataAccessManager.getInstance().close();
                }
            }

            @Override
            public void windowOpened(WindowEvent arg0) {
                //nada que hacer
            }

            @Override
            public void windowClosed(WindowEvent arg0) {
                //nada que hacer
            }

            @Override
            public void windowIconified(WindowEvent arg0) {
                //nada que hacer
            }

            @Override
            public void windowDeiconified(WindowEvent arg0) {
                //nada que hacer
            }

            @Override
            public void windowActivated(WindowEvent arg0) {
                //nada que hacer
            }

            @Override
            public void windowDeactivated(WindowEvent arg0) {
                //nada que hacer
            }
        });

        mainWindow.setVisible(true);
    }

}
