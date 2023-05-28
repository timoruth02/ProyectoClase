package Formularios;

import javax.swing.JOptionPane;

public class JFrm_Menu extends javax.swing.JFrame {

    public JFrm_Menu() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar_Principal = new javax.swing.JMenuBar();
        jMenu_MantenimientoUsuario = new javax.swing.JMenu();
        jMenuItem_MantenimientoCliente = new javax.swing.JMenuItem();
        jMenuItem_MantenimientoEmpleado = new javax.swing.JMenuItem();
        jMenuItem_MantenimientoHabitacion = new javax.swing.JMenuItem();
        jMenuItem_MantenimientoProducto = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_MantenimientoUsuario = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_MantenimientoSalir = new javax.swing.JMenuItem();
        jMenu_Procesos = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu_Reporter = new javax.swing.JMenu();
        jMenu_Consultas = new javax.swing.JMenu();
        jMenuItem_ReservaHabitacion = new javax.swing.JMenuItem();
        jMenuItem_Alquiler = new javax.swing.JMenuItem();
        jMenuItem_Productos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenuBar_Principal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuBar_Principal.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jMenu_MantenimientoUsuario.setForeground(new java.awt.Color(0, 0, 0));
        jMenu_MantenimientoUsuario.setText("Mantenimiento");

        jMenuItem_MantenimientoCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenuItem_MantenimientoCliente.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem_MantenimientoCliente.setText("Cliente");
        jMenuItem_MantenimientoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu_MantenimientoUsuario.add(jMenuItem_MantenimientoCliente);

        jMenuItem_MantenimientoEmpleado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenuItem_MantenimientoEmpleado.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem_MantenimientoEmpleado.setText("Empleado");
        jMenuItem_MantenimientoEmpleado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu_MantenimientoUsuario.add(jMenuItem_MantenimientoEmpleado);

        jMenuItem_MantenimientoHabitacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenuItem_MantenimientoHabitacion.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem_MantenimientoHabitacion.setText("Habitacion");
        jMenuItem_MantenimientoHabitacion.setContentAreaFilled(false);
        jMenuItem_MantenimientoHabitacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu_MantenimientoUsuario.add(jMenuItem_MantenimientoHabitacion);

        jMenuItem_MantenimientoProducto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenuItem_MantenimientoProducto.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem_MantenimientoProducto.setText("Producto");
        jMenuItem_MantenimientoProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu_MantenimientoUsuario.add(jMenuItem_MantenimientoProducto);
        jMenu_MantenimientoUsuario.add(jSeparator1);

        jMenuItem_MantenimientoUsuario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenuItem_MantenimientoUsuario.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem_MantenimientoUsuario.setText("Usuario");
        jMenuItem_MantenimientoUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu_MantenimientoUsuario.add(jMenuItem_MantenimientoUsuario);
        jMenu_MantenimientoUsuario.add(jSeparator2);

        jMenuItem_MantenimientoSalir.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenuItem_MantenimientoSalir.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem_MantenimientoSalir.setText("Salir del Sistema");
        jMenuItem_MantenimientoSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem_MantenimientoSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_MantenimientoSalirActionPerformed(evt);
            }
        });
        jMenu_MantenimientoUsuario.add(jMenuItem_MantenimientoSalir);

        jMenuBar_Principal.add(jMenu_MantenimientoUsuario);

        jMenu_Procesos.setForeground(new java.awt.Color(0, 0, 0));
        jMenu_Procesos.setText("Procesos");

        jMenuItem8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenuItem8.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem8.setText("Reserva de Habitacion");
        jMenuItem8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu_Procesos.add(jMenuItem8);

        jMenuItem9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenuItem9.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem9.setText("Alquiler de Habitacion");
        jMenuItem9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu_Procesos.add(jMenuItem9);

        jMenuItem10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenuItem10.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem10.setText("Venta de producto");
        jMenuItem10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu_Procesos.add(jMenuItem10);

        jMenuItem11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenuItem11.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem11.setText("Facturacion de Alquiler");
        jMenuItem11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu_Procesos.add(jMenuItem11);

        jMenu_Reporter.setForeground(new java.awt.Color(0, 0, 0));
        jMenu_Reporter.setText("Reportes");
        jMenu_Procesos.add(jMenu_Reporter);

        jMenuBar_Principal.add(jMenu_Procesos);

        jMenu_Consultas.setForeground(new java.awt.Color(0, 0, 0));
        jMenu_Consultas.setText("Consultas");

        jMenuItem_ReservaHabitacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenuItem_ReservaHabitacion.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem_ReservaHabitacion.setText("Reserva de Habitacion");
        jMenuItem_ReservaHabitacion.setContentAreaFilled(false);
        jMenuItem_ReservaHabitacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu_Consultas.add(jMenuItem_ReservaHabitacion);

        jMenuItem_Alquiler.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenuItem_Alquiler.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem_Alquiler.setText("Alquiler Habitacion");
        jMenuItem_Alquiler.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu_Consultas.add(jMenuItem_Alquiler);

        jMenuItem_Productos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenuItem_Productos.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem_Productos.setText("Productos Vendidos");
        jMenuItem_Productos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu_Consultas.add(jMenuItem_Productos);

        jMenuBar_Principal.add(jMenu_Consultas);

        setJMenuBar(jMenuBar_Principal);
        jMenuBar_Principal.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 895, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem_MantenimientoSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_MantenimientoSalirActionPerformed
        // Declarar una Variable de tipo Entero
        int Rpta;
        
        //Mostrar mensaje de Confirmacion
        Rpta = JOptionPane.showConfirmDialog(null, "¿Desea Salir del Sistema?","Confirmación...",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        //Evaluar si el Usuario dio como respuesta afirmativo
        if(Rpta==0)
        {
            //Cerrar el Programa
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem_MantenimientoSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrm_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrm_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrm_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrm_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                //CREAR CONTROLADOR DE ERROR
                try
                {
                    //CREAR UN OBJETO QUE INSTANCIE EL FORMULARIO
                    JFrm_Menu formulario = new JFrm_Menu();
                          
                    //UBICAR EL FORMULARIO EN EL CENTRO DE LA PANTALLA
                    formulario.setLocationRelativeTo(null);

                    //MAXIMIZAR LA VENTANA
                    formulario.setExtendedState(MAXIMIZED_BOTH);
                    
                    //DESHABILITAR EL BOTON CERRA(X) DEL FORMULARIO
                    formulario.setDefaultCloseOperation(0);

                    //EVITAR QUE EL USUARIO REDIMENSIONE EL TAMAÑO DE LA VENTANA
                    formulario.setResizable(false);

                    //MOSTRAR FORMULARIO
                    formulario.setVisible(true);
                }
                catch(Exception Error)
                {
                   //MOSTRAR MENSAJE DE ERROR
                   JOptionPane.showMessageDialog(null,"Ha ocurrido un Error:"+ Error.getMessage()
                                               ,"Sistema de Reserva ", JOptionPane.ERROR_MESSAGE);
                } 
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar_Principal;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItem_Alquiler;
    private javax.swing.JMenuItem jMenuItem_MantenimientoCliente;
    private javax.swing.JMenuItem jMenuItem_MantenimientoEmpleado;
    private javax.swing.JMenuItem jMenuItem_MantenimientoHabitacion;
    private javax.swing.JMenuItem jMenuItem_MantenimientoProducto;
    private javax.swing.JMenuItem jMenuItem_MantenimientoSalir;
    private javax.swing.JMenuItem jMenuItem_MantenimientoUsuario;
    private javax.swing.JMenuItem jMenuItem_Productos;
    private javax.swing.JMenuItem jMenuItem_ReservaHabitacion;
    private javax.swing.JMenu jMenu_Consultas;
    private javax.swing.JMenu jMenu_MantenimientoUsuario;
    private javax.swing.JMenu jMenu_Procesos;
    private javax.swing.JMenu jMenu_Reporter;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
