/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package PopUp;

import Frame.Login;
import Main.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tria
 */
public class Profile extends javax.swing.JDialog {

    /**
     * Creates new form Profile
     */
    public Profile(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        showData(Login.namaUser);
    }

    private void showData(String username) {
        try {
            String query = "SELECT * FROM karyawan WHERE username = '" + username + "'";
            Connection conn = (Connection) Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery(query);

            if (r.next()) {
                this.lbl_kodeK.setText(r.getString("kode_karyawan"));
                this.txt_nama.setText(r.getString("nama_karyawan"));
                lbl_username.setText(Login.namaUser);
                this.txt_noTelp.setText(r.getString("no_telp"));
                this.txt_alamat.setText(r.getString("alamat"));
                this.lbl_user.setText(r.getString("level_user"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_user = new javax.swing.JLabel();
        lbl_username = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        txt_noTelp = new javax.swing.JTextField();
        lbl_kodeK = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        kmbali = new javax.swing.JButton();
        btnGanti = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_user.setBackground(new java.awt.Color(255, 255, 255));
        lbl_user.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_user.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_user.setOpaque(true);
        getContentPane().add(lbl_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 180, 28));

        lbl_username.setBackground(new java.awt.Color(255, 255, 255));
        lbl_username.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_username.setOpaque(true);
        getContentPane().add(lbl_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 192, 180, 28));

        txt_nama.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_nama.setBorder(null);
        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, 270, 25));

        txt_alamat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_alamat.setBorder(null);
        getContentPane().add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 300, 20));

        txt_noTelp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_noTelp.setBorder(null);
        txt_noTelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_noTelpActionPerformed(evt);
            }
        });
        txt_noTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_noTelpKeyTyped(evt);
            }
        });
        getContentPane().add(txt_noTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, 180, 20));

        lbl_kodeK.setBackground(new java.awt.Color(255, 255, 255));
        lbl_kodeK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_kodeK.setOpaque(true);
        getContentPane().add(lbl_kodeK, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 107, 170, 24));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/profile.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnSimpan.setOpaque(false);
        btnSimpan.setContentAreaFilled(false);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 400, 130, 50));

        kmbali.setOpaque(false);
        kmbali.setContentAreaFilled(false);
        kmbali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kmbaliActionPerformed(evt);
            }
        });
        getContentPane().add(kmbali, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 160, 40));

        btnGanti.setOpaque(false);
        btnGanti.setContentAreaFilled(false);
        btnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGantiActionPerformed(evt);
            }
        });
        getContentPane().add(btnGanti, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, 220, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void kmbaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kmbaliActionPerformed
        this.dispose();
    }//GEN-LAST:event_kmbaliActionPerformed

    private void btnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGantiActionPerformed
new PopUp.GantiPassword(null, rootPaneCheckingEnabled).setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnGantiActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        String kode = lbl_kodeK.getText();
        String nama = txt_nama.getText();
        String username = lbl_username.getText();
        String notelp = txt_noTelp.getText();
        String alamat = txt_alamat.getText();

        if (kode.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (nama.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (username.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (notelp.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (alamat.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else {

            try {
                Statement statement = Main.Koneksi.configDB().createStatement();
                statement.executeUpdate(String.format("UPDATE karyawan SET nama_karyawan = '%s', no_telp = '%s', alamat = '%s' "
                        + "WHERE kode_karyawan = '%s' ", nama, notelp, alamat, kode));
                statement.close();
                new Informasi.BerhasilEditData(null, rootPaneCheckingEnabled).setVisible(true);
                this.dispose();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                new Informasi.GagalEditData(null, rootPaneCheckingEnabled).setVisible(true);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txt_noTelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_noTelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_noTelpActionPerformed

    private void txt_noTelpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_noTelpKeyTyped

        if(txt_noTelp.getText().equals("")){
            txt_noTelp.setText("+");
        }
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();}          // TODO add your handling code here:
    }//GEN-LAST:event_txt_noTelpKeyTyped

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
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Profile dialog = new Profile(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnGanti;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton kmbali;
    private javax.swing.JLabel lbl_kodeK;
    private javax.swing.JLabel lbl_user;
    private javax.swing.JLabel lbl_username;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_noTelp;
    // End of variables declaration//GEN-END:variables
}
