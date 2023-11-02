/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Frame;

import Frame.Login.*;
import com.mysql.cj.xdevapi.Statement;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class LupaPassword extends javax.swing.JDialog {

    public LupaPassword(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        refresh();      ImageIcon img = new ImageIcon("src/Image/LogoAplikasi.png");
            this.setIconImage(img.getImage());
        this.ic_hidden.setVisible(false);
        ic_show.setVisible(true);
        showPassword();
        hiddenPassword();
        this.ic_hidden1.setVisible(false);
        ic_show1.setVisible(true);
        showKonfirmasi();
        hiddenKonfirmasi();
    }
    
    public void refresh() {
        txt_konfirmasi.setText(null);
        txt_username.setText(null);
        jLabel1.setText(null);
        txt_noTelp.setText(null);
    }
    
    public void showPassword() {
        this.ic_show.setVisible(false);
        this.ic_show.setEnabled(false);
        this.ic_hidden.setVisible(true);
        this.ic_hidden.setEnabled(true);
        this.txt_password.setEchoChar(((char)0));
    }

    public void hiddenPassword(){
       this.ic_hidden.setVisible(false);
       this.ic_hidden.setEnabled(false);
       this.ic_show.setVisible(true);
       this.ic_show.setEnabled(true);
       this.txt_password.setEchoChar('*');
    }
    
    public void showKonfirmasi() {
        this.ic_show1.setVisible(false);
        this.ic_show1.setEnabled(false);
        this.ic_hidden1.setVisible(true);
        this.ic_hidden1.setEnabled(true);
        this.txt_konfirmasi.setEchoChar(((char)0));
    }

    public void hiddenKonfirmasi(){
       this.ic_hidden1.setVisible(false);
       this.ic_hidden1.setEnabled(false);
       this.ic_show1.setVisible(true);
       this.ic_show1.setEnabled(true);
       this.txt_konfirmasi.setEchoChar('*');
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ic_show1 = new javax.swing.JLabel();
        ic_hidden1 = new javax.swing.JLabel();
        ic_show = new javax.swing.JLabel();
        ic_hidden = new javax.swing.JLabel();
        txt_konfirmasi = new javax.swing.JPasswordField();
        txt_password = new javax.swing.JPasswordField();
        txt_username = new javax.swing.JTextField();
        txt_noTelp = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ic_show1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ic_show1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Ic_Show.png"))); // NOI18N
        ic_show1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ic_show1MouseClicked(evt);
            }
        });
        getContentPane().add(ic_show1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 398, 40, 30));

        ic_hidden1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ic_hidden1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Ic_Hidden.png"))); // NOI18N
        ic_hidden1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ic_hidden1MouseClicked(evt);
            }
        });
        getContentPane().add(ic_hidden1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 398, 40, 30));

        ic_show.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ic_show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Ic_Show.png"))); // NOI18N
        ic_show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ic_showMouseClicked(evt);
            }
        });
        getContentPane().add(ic_show, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, 40, 30));

        ic_hidden.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ic_hidden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Ic_Hidden.png"))); // NOI18N
        ic_hidden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ic_hiddenMouseClicked(evt);
            }
        });
        getContentPane().add(ic_hidden, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, 40, 30));

        txt_konfirmasi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_konfirmasi.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_konfirmasi.setBorder(null);
        txt_konfirmasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_konfirmasiKeyPressed(evt);
            }
        });
        getContentPane().add(txt_konfirmasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 398, 220, 27));

        txt_password.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_password.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_password.setBorder(null);
        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_passwordKeyPressed(evt);
            }
        });
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 220, 30));

        txt_username.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_username.setBorder(null);
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 260, 20));

        txt_noTelp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_noTelp.setBorder(null);
        getContentPane().add(txt_noTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 260, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/lupaPassword.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btn_simpan.setOpaque(false);
        btn_simpan.setContentAreaFilled(false);
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 480, 100, 40));

        btn_batal.setOpaque(false);
        btn_batal.setContentAreaFilled(false);
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });
        getContentPane().add(btn_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 480, 100, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        String username = txt_username.getText();
        String notelp = txt_noTelp.getText();
        String password = txt_password.getText();
        String Konfirmasi = txt_konfirmasi.getText();
        System.out.println("Password = " + password);
        System.out.println("Konfirmasi = " + Konfirmasi);
        if (username.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (notelp.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (password.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (Konfirmasi.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else {
            try {
                 if(txt_password.getText().equals(txt_konfirmasi.getText())){
                java.sql.Statement statement = Main.Koneksi.configDB().createStatement();
                statement.executeUpdate(String.format("UPDATE karyawan \n"
                        + "SET `password` = '%s' \n"
                        + "WHERE `username` = '%s' AND `no_telp` = '%s'", Konfirmasi, username, notelp));
                statement.close();
                new Informasi.BerhasilGantiPassword(null, rootPaneCheckingEnabled).setVisible(true);
                }else{
                    new Informasi.KonfirmasiPassword(null, rootPaneCheckingEnabled).setVisible(true);
                                    }
                 this.dispose();
            } catch (Exception e) {
                new Informasi.GagalEditData(null, rootPaneCheckingEnabled).setVisible(true);
            }
            this.dispose();
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void txt_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyPressed

    }//GEN-LAST:event_txt_passwordKeyPressed

    private void txt_konfirmasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_konfirmasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String username = txt_username.getText();
            String notelp = txt_noTelp.getText();
            String password = txt_password.getText();
            String Konfirmasi = txt_konfirmasi.getText();
            System.out.println("Password = " + password);
            System.out.println("Konfirmasi = " + Konfirmasi);
            if (username.equals("")) {
                new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
            } else if (notelp.equals("")) {
                new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
            } else if (password.equals("")) {
                new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
            } else if (Konfirmasi.equals("")) {
                new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
            } else {
                try {
                                                        if(txt_password.getText().equals(txt_konfirmasi.getText())){

                    java.sql.Statement statement = Main.Koneksi.configDB().createStatement();
                    statement.executeUpdate(String.format("UPDATE karyawan \n"
                            + "SET `password` = '%s' \n"
                            + "WHERE `username` = '%s' AND `no_telp` = '%s'", Konfirmasi, username, notelp));
                    statement.close();
                    new Informasi.BerhasilGantiPassword(null, rootPaneCheckingEnabled).setVisible(true);
                    
                     }else{
                    new Informasi.KonfirmasiPassword(null, rootPaneCheckingEnabled).setVisible(true);
                                    }
                    this.dispose();
                } catch (Exception e) {
                    new Informasi.GagalEditData(null, rootPaneCheckingEnabled).setVisible(true);
                }
                this.dispose();
            }

        }
//            String Username = txt_username.getText();
//            String Password = txt_password.getText();
//            username = Username;
//            this.setVisible(true);
//
//            try{
//                String sql = "SELECT * FROM karyawan WHERE username="+"'"+Username+"'"+" AND password='"+Password+"'";
//                Connection conn = (Connection) Main.Koneksi.configDB();
//                Statement s = conn.createStatement();
//                ResultSet r = s.executeQuery(sql);
//                System.out.println("Koneksi Berhasil!!");
//
//                if(r.next()){
//                    namaUser = r.getString("username");
//                    BerhasilLogin p = new BerhasilLogin(this, true);
//                    p.setVisible(true);
//                    if(r.getString("level_user").equalsIgnoreCase("owner")) {
//                        System.out.println("user admin");
//                        admin = true;
//                    } else {
//                        System.out.println("user karyawan");
//                        admin = false;
//                    }
//                    new ProgressBar().setVisible(true);
//                    this.dispose();
//                } else {
//                    GagalLogin q = new GagalLogin(this, true);
//                    q.setVisible(true);
//                }
//            } catch(Exception e) {
//                GagalLogin r = new GagalLogin(this, true);
//                r.setVisible(true);
//            }
//        }
    }//GEN-LAST:event_txt_konfirmasiKeyPressed

    private void ic_showMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_showMouseClicked
        this.showPassword();
    }//GEN-LAST:event_ic_showMouseClicked

    private void ic_hiddenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_hiddenMouseClicked
        this.hiddenPassword();
    }//GEN-LAST:event_ic_hiddenMouseClicked

    private void ic_show1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_show1MouseClicked
        this.showKonfirmasi();
    }//GEN-LAST:event_ic_show1MouseClicked

    private void ic_hidden1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_hidden1MouseClicked
        this.hiddenKonfirmasi();
    }//GEN-LAST:event_ic_hidden1MouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LupaPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LupaPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LupaPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LupaPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LupaPassword dialog = new LupaPassword(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JLabel ic_hidden;
    private javax.swing.JLabel ic_hidden1;
    private javax.swing.JLabel ic_show;
    private javax.swing.JLabel ic_show1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField txt_konfirmasi;
    private javax.swing.JTextField txt_noTelp;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
