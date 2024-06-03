/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendaUI;

/**
 *
 * @author angel
 */
import javax.swing.*;

// PARA NO PODER ARRASTRAR LA VENTANA - ARREGLAR LOS INTERNAL FRAMES

public class CustomDesktopPane extends JDesktopPane {
    @Override
    public DesktopManager getDesktopManager() {
        return new NoDraggingDesktopManager();
    }

    private static class NoDraggingDesktopManager extends DefaultDesktopManager {
        @Override
        public void beginDraggingFrame(JComponent f) {
            // No hacemos nada para deshabilitar el arrastre de la ventana interna
        }
    }
}