package Formularios;

import java.awt.Color;

public class Login extends javax.swing.JFrame {

    int xMouse , yMouse;
    
    
    public Login() {
       initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        LogoName = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        Citybg = new javax.swing.JLabel();
        header = new javax.swing.JPanel();
        ExitBTN = new javax.swing.JPanel();
        ExitTXT = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        userTxt = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        passLabel = new javax.swing.JLabel();
        passTxt = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        LoginBTN = new javax.swing.JPanel();
        LoginBtnTXT = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LogoName.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        LogoName.setForeground(new java.awt.Color(255, 255, 255));
        LogoName.setText("Hostal \"El Amanecer\"");
        jPanel1.add(LogoName, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, -1, -1));

        Logo.setBackground(new java.awt.Color(0, 0, 0));
        Logo.setForeground(new java.awt.Color(255, 255, 255));
        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Imagen/Sin título lo.png"))); // NOI18N
        Logo.setText("jLabel3");
        jPanel1.add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 160, 140));

        Citybg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Imagen/city.png"))); // NOI18N
        jPanel1.add(Citybg, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, -20, 240, 500));

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setForeground(new java.awt.Color(255, 255, 255));
        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });

        ExitTXT.setBackground(new java.awt.Color(0, 0, 0));
        ExitTXT.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        ExitTXT.setForeground(new java.awt.Color(0, 0, 0));
        ExitTXT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ExitTXT.setText("X");
        ExitTXT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ExitTXT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ExitTXT.setPreferredSize(new java.awt.Dimension(40, 40));
        ExitTXT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitTXTMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ExitTXTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ExitTXTMouseExited(evt);
            }
        });

        javax.swing.GroupLayout ExitBTNLayout = new javax.swing.GroupLayout(ExitBTN);
        ExitBTN.setLayout(ExitBTNLayout);
        ExitBTNLayout.setHorizontalGroup(
            ExitBTNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExitBTNLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ExitTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ExitBTNLayout.setVerticalGroup(
            ExitBTNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ExitTXT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(ExitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 624, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(ExitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jPanel1.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 50));

        title.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setText("INICIAR SESIÓN");
        jPanel1.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, 40));

        userLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        userLabel.setForeground(new java.awt.Color(51, 51, 51));
        userLabel.setText("USUARIO");
        jPanel1.add(userLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        userTxt.setBackground(new java.awt.Color(255, 255, 255));
        userTxt.setForeground(new java.awt.Color(0, 0, 0));
        userTxt.setBorder(null);
        userTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                userTxtMousePressed(evt);
            }
        });
        jPanel1.add(userTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 380, 40));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 310, 10));

        passLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        passLabel.setForeground(new java.awt.Color(51, 51, 51));
        passLabel.setText("CONTRASEÑA");
        jPanel1.add(passLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, -1));

        passTxt.setBackground(new java.awt.Color(255, 255, 255));
        passTxt.setForeground(new java.awt.Color(0, 0, 0));
        passTxt.setBorder(null);
        passTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                passTxtMousePressed(evt);
            }
        });
        jPanel1.add(passTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 320, 40));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 310, 10));

        LoginBTN.setBackground(new java.awt.Color(20, 113, 176));

        LoginBtnTXT.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LoginBtnTXT.setForeground(new java.awt.Color(255, 255, 255));
        LoginBtnTXT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LoginBtnTXT.setText("ENTRAR");
        LoginBtnTXT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginBtnTXTMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LoginBtnTXTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LoginBtnTXTMouseExited(evt);
            }
        });

        javax.swing.GroupLayout LoginBTNLayout = new javax.swing.GroupLayout(LoginBTN);
        LoginBTN.setLayout(LoginBTNLayout);
        LoginBTNLayout.setHorizontalGroup(
            LoginBTNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LoginBtnTXT, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        LoginBTNLayout.setVerticalGroup(
            LoginBTNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LoginBtnTXT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jPanel1.add(LoginBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 150, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoginBtnTXTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginBtnTXTMouseExited

    }//GEN-LAST:event_LoginBtnTXTMouseExited

    private void LoginBtnTXTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginBtnTXTMouseEntered
        LoginBTN.setBackground(new Color(0, 156, 223));
    }//GEN-LAST:event_LoginBtnTXTMouseEntered

    private void LoginBtnTXTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginBtnTXTMouseClicked
        javax.swing.JOptionPane.showMessageDialog(this, "Intento de login con los datos:\nUsuario: " +
            userTxt.getText() + "\nContraseña: " + String.valueOf(passTxt.getPassword()),
            "LOGIN", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_LoginBtnTXTMouseClicked

    private void passTxtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passTxtMousePressed
        if (String.valueOf(passTxt.getPassword()).equals("********")) {
            passTxt.setText("");
            passTxt.setForeground(Color.black);
        }
        if (userTxt.getText().isEmpty()) {
            userTxt.setText("Ingrese su nombre de usuario");
            userTxt.setForeground(Color.gray);
        }
    }//GEN-LAST:event_passTxtMousePressed

    private void userTxtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTxtMousePressed
        if (userTxt.getText().equals("Ingrese su nombre de usuario")) {
            userTxt.setText("");
            userTxt.setForeground(Color.black);
        }
        if (String.valueOf(passTxt.getPassword()).isEmpty()) {
            passTxt.setText("********");
            passTxt.setForeground(Color.gray);
        }
    }//GEN-LAST:event_userTxtMousePressed

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_headerMouseDragged

    private void ExitTXTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitTXTMouseExited
        ExitBTN.setBackground(Color.white);
        ExitTXT.setForeground(Color.black);
    }//GEN-LAST:event_ExitTXTMouseExited

    private void ExitTXTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitTXTMouseEntered
        ExitBTN.setBackground(Color.red);
        ExitTXT.setForeground(Color.white);
    }//GEN-LAST:event_ExitTXTMouseEntered

    private void ExitTXTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitTXTMouseClicked
        System.exit(0);
    }//GEN-LAST:event_ExitTXTMouseClicked

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Citybg;
    private javax.swing.JPanel ExitBTN;
    private javax.swing.JLabel ExitTXT;
    private javax.swing.JPanel LoginBTN;
    private javax.swing.JLabel LoginBtnTXT;
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel LogoName;
    private javax.swing.JPanel header;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel passLabel;
    private javax.swing.JPasswordField passTxt;
    private javax.swing.JLabel title;
    private javax.swing.JLabel userLabel;
    private javax.swing.JTextField userTxt;
    // End of variables declaration//GEN-END:variables
}
