package PopUp;

import Main.Koneksi;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Tria
 */
public class UpdateKaryawan extends javax.swing.JDialog {

    private boolean isClose = false;

    public UpdateKaryawan(java.awt.Frame parent, boolean modal, String kode) {
        super(parent, modal);
        initComponents();
        showData(kode);
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

    public boolean isClose() {
        return this.isClose;
    }

    private void showData(String kode_k) {
        try {
            Connection conn = (Connection) Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery("SELECT * FROM karyawan WHERE kode_karyawan = '" + kode_k + "'");

            if (r.next()) {
                this.txt_kode.setText(r.getString("kode_karyawan"));
                this.txt_username.setText(r.getString("username"));
                this.txt_password.setText(r.getString("password"));
                this.txt_nama.setText(r.getString("nama_karyawan"));
                this.txt_noTelp.setText(r.getString("no_telp"));
                this.txt_alamat.setText(r.getString("alamat"));
                this.level_user.setSelectedItem(r.getString("level_user"));
//                this.level_user.setText(r.getString("foto"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        simpanlevel = new javax.swing.JTextField();
        ic_show = new javax.swing.JLabel();
        ic_hidden = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        txt_noTelp = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        txt_kode = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        level_user = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();

        simpanlevel.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ic_show.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ic_show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Ic_Show.png"))); // NOI18N
        ic_show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ic_showMouseClicked(evt);
            }
        });
        getContentPane().add(ic_show, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, 40, 30));

        ic_hidden.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ic_hidden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Ic_Hidden.png"))); // NOI18N
        ic_hidden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ic_hiddenMouseClicked(evt);
            }
        });
        getContentPane().add(ic_hidden, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, 40, 30));

        txt_username.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_username.setBorder(null);
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 170, -1));

        txt_nama.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_nama.setBorder(null);
        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 260, -1));

        txt_noTelp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_noTelp.setBorder(null);
        txt_noTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_noTelpKeyTyped(evt);
            }
        });
        getContentPane().add(txt_noTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 247, 170, 20));

        txt_alamat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_alamat.setBorder(null);
        getContentPane().add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 430, 20));

        txt_kode.setEditable(false);
        txt_kode.setBackground(new java.awt.Color(255, 255, 255));
        txt_kode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_kode.setBorder(null);
        getContentPane().add(txt_kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 100, 20));

        txt_password.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_password.setBorder(null);
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 170, 20));

        level_user.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        level_user.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "owner", "karyawan" }));
        level_user.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        level_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                level_userActionPerformed(evt);
            }
        });
        getContentPane().add(level_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, 190, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/popupDataKaryawan.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btn_back.setOpaque(false);
        btn_back.setContentAreaFilled(false);
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, 140, 50));

        btn_simpan.setOpaque(false);
        btn_simpan.setContentAreaFilled(false);
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 380, 120, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        String kode = txt_kode.getText();
        String nama = txt_nama.getText();
        String username = txt_username.getText();
        String password = txt_password.getText();
        String notelp = txt_noTelp.getText();
        String alamat = txt_alamat.getText();
        String leveluser = level_user.getSelectedItem().toString();
        String foto = "null";

        if (kode.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (nama.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (username.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (password.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (notelp.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (alamat.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (leveluser.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else {

            try {
                Statement statement = Main.Koneksi.configDB().createStatement();
                statement.executeUpdate(String.format("UPDATE karyawan SET username = '%s', password= '%s', "
                        + "nama_karyawan = '%s', no_telp = '%s', alamat = '%s', level_user = '%s' WHERE "
                        + "kode_karyawan = '%s'", username, password, nama, notelp, alamat,
                        leveluser, kode));
                statement.close();
                new Informasi.BerhasilEditData(null, rootPaneCheckingEnabled).setVisible(true);
                this.dispose();
            } catch (Exception e) {
                new Informasi.GagalEditData(null, rootPaneCheckingEnabled).setVisible(true);
            }
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void level_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_level_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_level_userActionPerformed

    private void ic_showMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_showMouseClicked
        this.showPassword();
    }//GEN-LAST:event_ic_showMouseClicked

    private void ic_hiddenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_hiddenMouseClicked
        this.hiddenPassword();
    }//GEN-LAST:event_ic_hiddenMouseClicked

    private void txt_noTelpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_noTelpKeyTyped

        if(txt_noTelp.getText().equals("")){
            txt_noTelp.setText("+");
        }
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();}          // TODO add your handling code here:
    }//GEN-LAST:event_txt_noTelpKeyTyped
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
            java.util.logging.Logger.getLogger(UpdateKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdateKaryawan dialog = new UpdateKaryawan(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JLabel ic_hidden;
    private javax.swing.JLabel ic_show;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox<String> level_user;
    private javax.swing.JTextField simpanlevel;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_noTelp;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
