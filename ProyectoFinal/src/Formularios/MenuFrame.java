package Formularios;

import Entidades.Utilidades;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import org.netbeans.lib.awtextra.AbsoluteConstraints;


public class MenuFrame extends javax.swing.JFrame {


    public MenuFrame() {
        initComponents();
        
        content.setPreferredSize(new Dimension(1040, 670));
        
        InitStyles();
        SetDate();
        InitContent();

        String m_Nombres = Utilidades.getM_UsuarioSistema().getNombres();
        String m_Apellidos = Utilidades.getM_UsuarioSistema().getApellidos();

        String NombreCompletos = m_Nombres + ", " + m_Apellidos;

        lb_EmpleadoNombreCompletos.setText("Empleados(a) : " + NombreCompletos);

        lb_NombreSede.setText("Sede : " + Utilidades.getM_UsuarioSistema().getNombre_Sede());
    }

    private void InitStyles() {

//        appName.putClientProperty("FlatLaf.style", "font: bold $h1.regular.font");
//        appName.setForeground(Color.white);
    }

    private void SetDate() {
        LocalDate now = LocalDate.now();
        Locale spanishLocale = new Locale("es", "ES");
    }

    private void InitContent() {
        ShowJPanel(new Panel.Principal());
    }

    public void ShowJPanel(JPanel p) {
        p.setSize(750, 430);
        p.setLocation(0, 0);

        content.removeAll();
        content.add(p, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        btn_prin = new javax.swing.JButton();
        btn_lends = new javax.swing.JButton();
        btn_mantenimiento = new javax.swing.JButton();
        btn_mantenimientocliente = new javax.swing.JButton();
        btn_DatosCliente = new javax.swing.JButton();
        lb_NombreSede = new javax.swing.JLabel();
        btn_Prod = new javax.swing.JButton();
        content = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        BTN_Salir = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        lb_EmpleadoNombreCompletos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1050, 660));

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu.setBackground(new java.awt.Color(13, 71, 161));
        menu.setPreferredSize(new java.awt.Dimension(270, 640));
        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 5));
        menu.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 190, 20));

        btn_prin.setBackground(new java.awt.Color(21, 101, 192));
        btn_prin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_prin.setForeground(new java.awt.Color(255, 255, 255));
        btn_prin.setText("Principal");
        btn_prin.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
        btn_prin.setBorderPainted(false);
        btn_prin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_prin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_prin.setIconTextGap(13);
        btn_prin.setInheritsPopupMenu(true);
        btn_prin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prinActionPerformed(evt);
            }
        });
        menu.add(btn_prin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 280, 52));

        btn_lends.setBackground(new java.awt.Color(21, 101, 192));
        btn_lends.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_lends.setForeground(new java.awt.Color(255, 255, 255));
        btn_lends.setText("Alquiler de Habitacion");
        btn_lends.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
        btn_lends.setBorderPainted(false);
        btn_lends.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_lends.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_lends.setIconTextGap(13);
        btn_lends.setInheritsPopupMenu(true);
        btn_lends.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lendsActionPerformed(evt);
            }
        });
        menu.add(btn_lends, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 280, 52));

        btn_mantenimiento.setBackground(new java.awt.Color(21, 101, 192));
        btn_mantenimiento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_mantenimiento.setForeground(new java.awt.Color(255, 255, 255));
        btn_mantenimiento.setText("Mantenimiento Usuario");
        btn_mantenimiento.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
        btn_mantenimiento.setBorderPainted(false);
        btn_mantenimiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_mantenimiento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_mantenimiento.setIconTextGap(13);
        btn_mantenimiento.setInheritsPopupMenu(true);
        btn_mantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mantenimientoActionPerformed(evt);
            }
        });
        menu.add(btn_mantenimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 280, 52));

        btn_mantenimientocliente.setBackground(new java.awt.Color(21, 101, 192));
        btn_mantenimientocliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_mantenimientocliente.setForeground(new java.awt.Color(255, 255, 255));
        btn_mantenimientocliente.setText("Mantenimiento Cliente");
        btn_mantenimientocliente.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
        btn_mantenimientocliente.setBorderPainted(false);
        btn_mantenimientocliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_mantenimientocliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_mantenimientocliente.setIconTextGap(13);
        btn_mantenimientocliente.setInheritsPopupMenu(true);
        btn_mantenimientocliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mantenimientoclienteActionPerformed(evt);
            }
        });
        menu.add(btn_mantenimientocliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 280, 52));

        btn_DatosCliente.setBackground(new java.awt.Color(21, 101, 192));
        btn_DatosCliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_DatosCliente.setForeground(new java.awt.Color(255, 255, 255));
        btn_DatosCliente.setText("Datos del Cliente");
        btn_DatosCliente.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
        btn_DatosCliente.setBorderPainted(false);
        btn_DatosCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_DatosCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_DatosCliente.setIconTextGap(13);
        btn_DatosCliente.setInheritsPopupMenu(true);
        btn_DatosCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DatosClienteActionPerformed(evt);
            }
        });
        menu.add(btn_DatosCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 280, 52));

        lb_NombreSede.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lb_NombreSede.setForeground(new java.awt.Color(255, 255, 255));
        lb_NombreSede.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_NombreSede.setText("jLabel10");
        menu.add(lb_NombreSede, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 251, 25));

        btn_Prod.setBackground(new java.awt.Color(21, 101, 192));
        btn_Prod.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Prod.setForeground(new java.awt.Color(255, 255, 255));
        btn_Prod.setText("Productos");
        btn_Prod.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
        btn_Prod.setBorderPainted(false);
        btn_Prod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Prod.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_Prod.setIconTextGap(13);
        btn_Prod.setInheritsPopupMenu(true);
        btn_Prod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ProdActionPerformed(evt);
            }
        });
        menu.add(btn_Prod, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 280, 52));

        background.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 729));

        content.setBackground(new java.awt.Color(255, 255, 255));
        content.setLayout(new java.awt.BorderLayout());
        background.add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 59, 1040, 670));

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        BTN_Salir.setBackground(new java.awt.Color(153, 0, 0));
        BTN_Salir.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BTN_Salir.setForeground(new java.awt.Color(255, 255, 255));
        BTN_Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerrar-sesion.png"))); // NOI18N
        BTN_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SalirActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel7.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jPanel7.setVerifyInputWhenFocusTarget(false);

        lb_EmpleadoNombreCompletos.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lb_EmpleadoNombreCompletos.setForeground(new java.awt.Color(255, 255, 255));
        lb_EmpleadoNombreCompletos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_EmpleadoNombreCompletos, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lb_EmpleadoNombreCompletos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BTN_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(275, 275, 275)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BTN_Salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        background.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 0, 1040, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
void Salir() {
        //DECLARAMOS LA VARIABLE DE TIPO ENTERO
        int Rpta;

        //MOSTRAR MENSAJE DE CONFIRMACION
        Rpta = JOptionPane.showConfirmDialog(null, "¿Desea Salir de la Ventana?", this.getTitle(),
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        //EVALUAR SI EL USUARIO RESPONDIO DE FORMA AFIRMATIVA
        Rpta = 0;
        if (Rpta == 0) {
            //CERRRAR FORMULARIO
            System.exit(0);
        }
    }
    private void BTN_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SalirActionPerformed

        this.Salir();
    }//GEN-LAST:event_BTN_SalirActionPerformed

    private void btn_DatosClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DatosClienteActionPerformed
        ShowJPanel(new Panel.JFrm_Datos_Cliente());
    }//GEN-LAST:event_btn_DatosClienteActionPerformed

    private void btn_mantenimientoclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mantenimientoclienteActionPerformed

       ShowJPanel(new Panel.JFrm_mantenimiento_Cliente());
    }//GEN-LAST:event_btn_mantenimientoclienteActionPerformed

    private void btn_mantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mantenimientoActionPerformed

        ShowJPanel(new Panel.JFrm_mantenimiento_usuario());
    }//GEN-LAST:event_btn_mantenimientoActionPerformed

    private void btn_lendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lendsActionPerformed
        ShowJPanel(new Panel.JFrm_Alquiler_Habitacion());
    }//GEN-LAST:event_btn_lendsActionPerformed

    private void btn_prinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prinActionPerformed
        ShowJPanel(new Panel.Principal());
    }//GEN-LAST:event_btn_prinActionPerformed

    private void btn_ProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ProdActionPerformed
        ShowJPanel(new Panel.JFrm_RegistroProductos());
    }//GEN-LAST:event_btn_ProdActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatMaterialLighterIJTheme.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Salir;
    private javax.swing.JPanel background;
    private javax.swing.JButton btn_DatosCliente;
    private javax.swing.JButton btn_Prod;
    private javax.swing.JButton btn_lends;
    private javax.swing.JButton btn_mantenimiento;
    private javax.swing.JButton btn_mantenimientocliente;
    private javax.swing.JButton btn_prin;
    private static javax.swing.JPanel content;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lb_EmpleadoNombreCompletos;
    private javax.swing.JLabel lb_NombreSede;
    private javax.swing.JPanel menu;
    // End of variables declaration//GEN-END:variables
}
