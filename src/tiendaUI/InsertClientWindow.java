/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package tiendaUI;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import tiendaAppUI.TiendaUIApp;

import tiendaDataAccess.DataAccessManager;
import tiendaObjetos.Cliente;

/**
 *
 * @author angsaegim
 */
// INTERNAL FRAME
public class InsertClientWindow extends javax.swing.JInternalFrame {

    private TiendaManagementWindow mainMenu;
    // Para que el frame NO SE MUEVA
    private final int fixedX = 0;
    private final int fixedY = 0;

    /**
     * Creates new form insertClientWindow
     */
    public InsertClientWindow(TiendaManagementWindow mainMenu) {

        this.setBorder(null);
        this.mainMenu = mainMenu;
        initComponents();
        this.setResizable(false);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null); // Eliminar el borde superior
        this.setLocation(fixedX, fixedY);

        // Desactivar el listener de arrastre
        this.removeMouseListener(this.getMouseListeners()[0]);

        // Para no poder arrastrar y mover el frame
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                setLocation(fixedX, fixedY);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // Ventana fija
     @Override
     public void setLocation(int x, int y) {
        // No hagas nada para evitar que el JInternalFrame se mueva
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ingresarBoton = new javax.swing.JButton();
        borrarBoton = new javax.swing.JButton();
        regresarBoton = new javax.swing.JButton();
        txtNombreCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        txtFaxCliente = new javax.swing.JTextField();
        txtLineaDireccionCliente = new javax.swing.JTextField();
        txtCiudadCliente = new javax.swing.JTextField();
        txtPaisCliente = new javax.swing.JTextField();
        txtCodigoEmpleadoRelacionado = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setPreferredSize(new java.awt.Dimension(950, 522));

        ingresarBoton.setText("Ingresar");
        ingresarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarBotonActionPerformed(evt);
            }
        });

        borrarBoton.setText("Borrar");
        borrarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarBotonActionPerformed(evt);
            }
        });

        regresarBoton.setText("Atrás");
        regresarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarBotonActionPerformed(evt);
            }
        });

        txtCiudadCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCiudadClienteActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre del cliente");

        jLabel2.setText("Telefono del cliente");

        jLabel3.setText("Fax del cliente");

        jLabel4.setText("Direccion del cliente");

        jLabel5.setText("Ciudad del cliente");

        jLabel6.setText("Pais del cliente");

        jLabel7.setText("Codigo Empleado Relacionado al cliente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(regresarBoton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ingresarBoton)
                                .addGap(18, 18, 18)
                                .addComponent(borrarBoton)))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoEmpleadoRelacionado, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFaxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLineaDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCiudadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPaisCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(510, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(regresarBoton)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFaxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLineaDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCiudadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPaisCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoEmpleadoRelacionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ingresarBoton)
                    .addComponent(borrarBoton))
                .addContainerGap(138, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void regresarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarBotonActionPerformed
        // Hacer invisible la ventana actual
        this.dispose(); //cerramos
        mainMenu.setVisible(true); //enseñar menu 
    }//GEN-LAST:event_regresarBotonActionPerformed

    private void ingresarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarBotonActionPerformed

        String nombreCliente = txtNombreCliente.getText();
        String telefonoCliente = txtTelefonoCliente.getText();
        String faxCliente = txtFaxCliente.getText();
        String lineaDireccionCliente = txtLineaDireccionCliente.getText();
        String ciudadCliente = txtCiudadCliente.getText();
        String paisCliente = txtPaisCliente.getText();
        Short codigoEmpleadoRelacionado = Short.parseShort(txtCodigoEmpleadoRelacionado.getText());

        if (nombreCliente.equals("")) {
            JOptionPane.showMessageDialog(null, "Debes llenar el campo Nombre: ", "Campo vacío", JOptionPane.WARNING_MESSAGE);
            txtNombreCliente.setBackground(Color.red);
        } else if (telefonoCliente.equals("")) {
            JOptionPane.showMessageDialog(null, "Debes llenar el campo Telefono: ", "Campo vacío", JOptionPane.WARNING_MESSAGE);
            txtTelefonoCliente.setBackground(Color.red);
        } else if (faxCliente.equals("")) {
            JOptionPane.showMessageDialog(null, "Debes llenar el campo Fax: ", "Campo vacío", JOptionPane.WARNING_MESSAGE);
            txtFaxCliente.setBackground(Color.red);
        }else if (lineaDireccionCliente.equals("")) {
            JOptionPane.showMessageDialog(null, "Debes llenar el campo Direccion: ", "Campo vacío", JOptionPane.WARNING_MESSAGE);
            txtFaxCliente.setBackground(Color.red);
        }else if (ciudadCliente.equals("")) {
            JOptionPane.showMessageDialog(null, "Debes llenar el campo Ciudad: ", "Campo vacío", JOptionPane.WARNING_MESSAGE);
            txtCiudadCliente.setBackground(Color.red);
        }else if (paisCliente.equals("")) {
            JOptionPane.showMessageDialog(null, "Debes llenar el campo Pais: ", "Campo vacío", JOptionPane.WARNING_MESSAGE);
            txtPaisCliente.setBackground(Color.red);
        }else if (codigoEmpleadoRelacionado.toString().equals("")) {
            JOptionPane.showMessageDialog(null, "Debes llenar el campo Codigo Empleado Relacionado: ", "Campo vacío", JOptionPane.WARNING_MESSAGE);
            txtPaisCliente.setBackground(Color.red);
        }
        
        Cliente nuevoCliente = new Cliente(nombreCliente, telefonoCliente, faxCliente, lineaDireccionCliente, ciudadCliente, paisCliente, codigoEmpleadoRelacionado);
        int filasAfectadas = 0; // = insertarCliente(nuevoCliente);

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Datos insertados correctamente !!!");
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ingresarBotonActionPerformed

    private void borrarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_borrarBotonActionPerformed

    private void txtCiudadClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCiudadClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCiudadClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrarBoton;
    private javax.swing.JButton ingresarBoton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton regresarBoton;
    private javax.swing.JTextField txtCiudadCliente;
    private javax.swing.JTextField txtCodigoEmpleadoRelacionado;
    private javax.swing.JTextField txtFaxCliente;
    private javax.swing.JTextField txtLineaDireccionCliente;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtPaisCliente;
    private javax.swing.JTextField txtTelefonoCliente;
    // End of variables declaration//GEN-END:variables
}
