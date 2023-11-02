/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package PopUp;

import Frame.*;
import Frame.Login.*;
import Main.Koneksi;
import com.mysql.cj.xdevapi.Statement;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class GantiPassword extends javax.swing.JDialog {

    public GantiPassword(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        refresh();
    }

    public void refresh() {
        txt_konfirmasi.setText(null);
        txt_username.setText(null);
        jLabel1.setText(null);
        txt_passwordLama.setText(null);
        showData(Login.username);
        this.ic_hidden.setVisible(false);
        ic_show.setVisible(true);
        showPassword();
        hiddenPassword();
        this.ic_hidden1.setVisible(false);
        ic_show1.setVisible(true);
        showKonfirmasi();
        hiddenKonfirmasi();
        this.ic_hidden2.setVisible(false);
        ic_show2.setVisible(true);
        showPasswordLama();
        hiddenPasswordLama();
    }

    private void showData(String username) {
        try {
            String query = "SELECT * FROM karyawan WHERE username = '" + username + "'";
            Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery(query);

            if (r.next()) {
                this.txt_username.setText(r.getString("username"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    public void showPasswordLama() {
        this.ic_show2.setVisible(false);
        this.ic_show2.setEnabled(false);
        this.ic_hidden2.setVisible(true);
        this.ic_hidden2.setEnabled(true);
        this.txt_passwordLama.setEchoChar(((char) 0));
    }

    public void hiddenPasswordLama() {
        this.ic_hidden2.setVisible(false);
        this.ic_hidden2.setEnabled(false);
        this.ic_show2.setVisible(true);
        this.ic_show2.setEnabled(true);
        this.txt_passwordLama.setEchoChar('*');
    }
    
    public void showPassword() {
        this.ic_show.setVisible(false);
        this.ic_show.setEnabled(false);
        this.ic_hidden.setVisible(true);
        this.ic_hidden.setEnabled(true);
        this.txt_password.setEchoChar(((char) 0));
    }

    public void hiddenPassword() {
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
        this.txt_konfirmasi.setEchoChar(((char) 0));
    }

    public void hiddenKonfirmasi() {
        this.ic_hidden1.setVisible(false);
        this.ic_hidden1.setEnabled(false);
        this.ic_show1.setVisible(true);
        this.ic_show1.setEnabled(true);
        this.txt_konfirmasi.setEchoChar('*');
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_passwordLama = new javax.swing.JPasswordField();
        txt_konfirmasi = new javax.swing.JPasswordField();
        txt_password = new javax.swing.JPasswordField();
        txt_username = new javax.swing.JTextField();
        ic_show2 = new javax.swing.JLabel();
        ic_hidden2 = new javax.swing.JLabel();
        ic_show1 = new javax.swing.JLabel();
        ic_hidden1 = new javax.swing.JLabel();
        ic_show = new javax.swing.JLabel();
        ic_hidden = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_passwordLama.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_passwordLama.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_passwordLama.setBorder(null);
        txt_passwordLama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_passwordLamaKeyPressed(evt);
            }
        });
        getContentPane().add(txt_passwordLama, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 172, 180, 25));

        txt_konfirmasi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_konfirmasi.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_konfirmasi.setBorder(null);
        txt_konfirmasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_konfirmasiKeyPressed(evt);
            }
        });
        getContentPane().add(txt_konfirmasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 295, 180, 25));

        txt_password.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_password.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_password.setBorder(null);
        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_passwordKeyPressed(evt);
            }
        });
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 233, 180, 25));

        txt_username.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_username.setBorder(null);
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 115, 180, 20));

        ic_show2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ic_show2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Ic_Show.png"))); // NOI18N
        ic_show2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ic_show2MouseClicked(evt);
            }
        });
        getContentPane().add(ic_show2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 40, 30));

        ic_hidden2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ic_hidden2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Ic_Hidden.png"))); // NOI18N
        ic_hidden2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ic_hidden2MouseClicked(evt);
            }
        });
        getContentPane().add(ic_hidden2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 40, 30));

        ic_show1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ic_show1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Ic_Show.png"))); // NOI18N
        ic_show1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ic_show1MouseClicked(evt);
            }
        });
        getContentPane().add(ic_show1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 40, 30));

        ic_hidden1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ic_hidden1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Ic_Hidden.png"))); // NOI18N
        ic_hidden1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ic_hidden1MouseClicked(evt);
            }
        });
        getContentPane().add(ic_hidden1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 40, 30));

        ic_show.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ic_show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Ic_Show.png"))); // NOI18N
        ic_show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ic_showMouseClicked(evt);
            }
        });
        getContentPane().add(ic_show, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 40, 30));

        ic_hidden.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ic_hidden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Ic_Hidden.png"))); // NOI18N
        ic_hidden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ic_hiddenMouseClicked(evt);
            }
        });
        getContentPane().add(ic_hidden, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 40, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/gantiPassword.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btn_simpan.setOpaque(false);
        btn_simpan.setContentAreaFilled(false);
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, 90, 30));

        btn_batal.setOpaque(false);
        btn_batal.setContentAreaFilled(false);
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });
        getContentPane().add(btn_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 90, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        String username = txt_username.getText();
        String pwLama = txt_passwordLama.getText();
        String password = txt_password.getText();
        String Konfirmasi = txt_konfirmasi.getText();
        System.out.println("Password = " + password);
        System.out.println("Konfirmasi = " + Konfirmasi);
        if (username.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (pwLama.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (password.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (Konfirmasi.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else {
            try {
                int barisTerubah = 0;
                String sql = "UPDATE karyawan \n"
                        + "SET `password` = '%s' \n"
                        + "WHERE `username` = '%s' AND `password` = '%s'";
                java.sql.Statement statement = Main.Koneksi.configDB().createStatement();
                
                if(txt_password.getText().equals(txt_konfirmasi.getText())){
                barisTerubah = statement.executeUpdate(String.format(sql, Konfirmasi, username, pwLama));
                statement.close();
                
                if (barisTerubah == 0) {
                    new Informasi.GagalEditData(null, rootPaneCheckingEnabled).setVisible(true);
                } else {
                    new Informasi.BerhasilGantiPassword(null, rootPaneCheckingEnabled).setVisible(true);
                    System.out.println("BERHASIL =" + barisTerubah);
                }}else{
                    new Informasi.KonfirmasiPassword(null, rootPaneCheckingEnabled).setVisible(true);
                }
                this.dispose();
            } catch (Exception e) {
                new Informasi.GagalEditData(null, rootPaneCheckingEnabled).setVisible(true);
            }

        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void txt_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
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
    }//GEN-LAST:event_txt_passwordKeyPressed

    private void txt_konfirmasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_konfirmasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String username = txt_username.getText();
            String notelp = txt_passwordLama.getText();
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
                    java.sql.Statement statement = Main.Koneksi.configDB().createStatement();
                                    if(txt_password.getText().equals(txt_konfirmasi.getText())){

                    statement.executeUpdate(String.format("UPDATE karyawan \n"
                            + "SET `password` = '%s' \n"
                            + "WHERE `username` = '%s' AND `no_telp` = '%s'", Konfirmasi, username, notelp));
                    statement.close();
                    new Informasi.BerhasilGantiPassword(null, rootPaneCheckingEnabled).setVisible(true);
                    this.dispose();}else{
                    new Informasi.KonfirmasiPassword(null, rootPaneCheckingEnabled).setVisible(true);
                                    }
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

    private void ic_show1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_show1MouseClicked
        this.showKonfirmasi();
    }//GEN-LAST:event_ic_show1MouseClicked

    private void ic_hidden1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_hidden1MouseClicked
        this.hiddenKonfirmasi();
    }//GEN-LAST:event_ic_hidden1MouseClicked

    private void ic_showMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_showMouseClicked
        this.showPassword();
    }//GEN-LAST:event_ic_showMouseClicked

    private void ic_hiddenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_hiddenMouseClicked
        this.hiddenPassword();
    }//GEN-LAST:event_ic_hiddenMouseClicked

    private void txt_passwordLamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordLamaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordLamaKeyPressed

    private void ic_show2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_show2MouseClicked
        this.showPasswordLama();
    }//GEN-LAST:event_ic_show2MouseClicked

    private void ic_hidden2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_hidden2MouseClicked
        this.hiddenPasswordLama();
    }//GEN-LAST:event_ic_hidden2MouseClicked

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
            java.util.logging.Logger.getLogger(GantiPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GantiPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GantiPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GantiPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GantiPassword dialog = new GantiPassword(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel ic_hidden2;
    private javax.swing.JLabel ic_show;
    private javax.swing.JLabel ic_show1;
    private javax.swing.JLabel ic_show2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField txt_konfirmasi;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JPasswordField txt_passwordLama;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
